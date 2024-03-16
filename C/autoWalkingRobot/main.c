#include <stdio.h>
#include "driver/adc.h"
#include "driver/gpio.h"
#include "esp_log.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include <driver/ledc.h>
#include <string.h>
#include "freertos/event_groups.h"
#include "esp_system.h"
#include "esp_wifi.h"
#include "esp_event.h"
#include "nvs_flash.h"
#include "esp_http_server.h"
#include "math.h"
#include <sys/time.h>
#include "esp_task_wdt.h"

//MOTOR PINS

#define MOTOR_PIN GPIO_NUM_18
#define MOTOR_PIN2 GPIO_NUM_19

/** DIRECTION CALCULATION **/

float Movevector[2] = {0,0};
int direction = 2;
float timer = 0;
float distance = 0;
struct timeval start_time;
struct timeval end_time;
bool tracking = false;

//PT
int phototransistor_value_PT6 = 0;
int phototransistor_value_right = 0; 
int phototransistor_value_left = 0;

//SETUP VARIABLES AND GPIOS

void setup() {


    //links (von oben)PT2
    gpio_pad_select_gpio(GPIO_NUM_25);
    gpio_set_direction(GPIO_NUM_25, GPIO_MODE_OUTPUT);

    //rechts(von oben)PT3
    gpio_pad_select_gpio(GPIO_NUM_26);
    gpio_set_direction(GPIO_NUM_26, GPIO_MODE_OUTPUT);

    //hinten PT4
    gpio_pad_select_gpio(GPIO_NUM_27);
    gpio_set_direction(GPIO_NUM_27, GPIO_MODE_OUTPUT);

    //vorne PT1
    gpio_pad_select_gpio(GPIO_NUM_32);
    gpio_set_direction(GPIO_NUM_32, GPIO_MODE_OUTPUT);

    //ADC1
    gpio_pad_select_gpio(GPIO_NUM_35);
    gpio_set_direction(GPIO_NUM_35, GPIO_MODE_INPUT);

    //PT ganz vorne (Daylight)
    gpio_pad_select_gpio(GPIO_NUM_16);
    gpio_set_direction(GPIO_NUM_16, GPIO_MODE_OUTPUT);

    //PT (ground Erkennung)
    gpio_pad_select_gpio(GPIO_NUM_17);
    gpio_set_direction(GPIO_NUM_17, GPIO_MODE_OUTPUT);

    //Alle auf 0 damit ADC keins abliest
    gpio_set_level(GPIO_NUM_16, 0);
    gpio_set_level(GPIO_NUM_17, 0);
    gpio_set_level(GPIO_NUM_25, 0);
    gpio_set_level(GPIO_NUM_26, 0);
    gpio_set_level(GPIO_NUM_27, 0);
    gpio_set_level(GPIO_NUM_32, 0);

    //ADC config
    adc1_config_width(ADC_WIDTH_BIT_12);
    adc1_config_channel_atten(ADC1_CHANNEL_7, ADC_ATTEN_DB_0);


    //LED7 Infrarot Signal
    gpio_pad_select_gpio(GPIO_NUM_12);
    gpio_set_direction(GPIO_NUM_12, GPIO_MODE_OUTPUT);


    //Motor

    //Motor1
    gpio_pad_select_gpio(MOTOR_PIN);
    gpio_set_direction(MOTOR_PIN, GPIO_MODE_OUTPUT);
    
    // Configure LEDC timer
    ledc_timer_config_t ledc_timer = {
        .duty_resolution = LEDC_TIMER_10_BIT, // 10-bit resolution for PWM duty
        .freq_hz = 50000,                      // set frequency of PWM
        .speed_mode = LEDC_HIGH_SPEED_MODE,     // timer mode
        .timer_num = LEDC_TIMER_0              // timer index
    };
    ledc_timer_config(&ledc_timer);
    
    // Configure LEDC channel
    ledc_channel_config_t ledc_channel = {
        .channel = LEDC_CHANNEL_0,
        .duty = 0,
        .gpio_num = MOTOR_PIN,
        .speed_mode = LEDC_HIGH_SPEED_MODE,
        .timer_sel = LEDC_TIMER_0
    };
    ledc_channel_config(&ledc_channel);

    //Motor2
    gpio_pad_select_gpio(MOTOR_PIN2);
    gpio_set_direction(MOTOR_PIN2, GPIO_MODE_OUTPUT);
    
    // Configure LEDC timer
    ledc_timer_config_t ledc_timer2 = {
        .duty_resolution = LEDC_TIMER_10_BIT, // 10-bit resolution for PWM duty
        .freq_hz = 50000,                      // set frequency of PWM
        .speed_mode = LEDC_HIGH_SPEED_MODE,     // timer mode
        .timer_num = LEDC_TIMER_1              // timer index
    };
    ledc_timer_config(&ledc_timer2);
    
    // Configure LEDC channel
    ledc_channel_config_t ledc_channel2 = {
        .channel = LEDC_CHANNEL_1,
        .duty = 0,
        .gpio_num = MOTOR_PIN2,
        .speed_mode = LEDC_HIGH_SPEED_MODE,
        .timer_sel = LEDC_TIMER_1
    };
    ledc_channel_config(&ledc_channel2);

}



//sets the direction the robot is facing in relation to the starting point
void change_direction(char dir){
    if (direction == 0){
        if(dir == 'l'){direction++;}
        if(dir == 'r'){direction = 3;}
    }
    else if(direction == 4){
        if(dir == 'l'){direction = 1;}
        if(dir == 'r'){direction--;}
    }
    else{
        if(dir == 'l'){direction++;}
        if(dir == 'r'){direction--;}
    }

}

//calculates and logs the distance to the starting point
void calc_distance(){
    if(tracking == false){
    gettimeofday(&end_time, NULL);
    timer = (float)(end_time.tv_sec - start_time.tv_sec) + ((float)(end_time.tv_usec - start_time.tv_usec) / 1000000);
    }
    switch(direction){
        case 2:
        Movevector[1] = Movevector[1] + timer * 2;
        break;
        case 1:
        Movevector[0] = Movevector[0] + timer * 2;
        break;
        case 3:
        Movevector[0] = Movevector[0] - timer * 2;
        break;
        default:
        Movevector[1] = Movevector[1] - timer * 2;

    }
    distance = sqrt(Movevector[0]*Movevector[0]+Movevector[1]*Movevector[1]);

    timer=0;
    tracking = true;

    ESP_LOGI("Position     ", "Distanz: %.2f cm    Koordinate x: %.2f    Koordinate y: %.2f    Alte Richtung: %d"
    , distance, Movevector[0], Movevector[1], direction);
}


/** HTTP Server **/

#define MIN(a, b) (((a) < (b)) ? (a) : (b))

/* Our URI handler function to be called during GET /uri request */
esp_err_t get_handler(httpd_req_t *req)
{
    /* Send a simple response */
    const char resp[] = "<!DOCTYPE HTML><html><head>\
                            <title>ESP Input Form</title>\
                            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\
                            </head><body>\
                            <form action=\"/Distanz/\">\
                                <input type=\"submit\" value=\"get Distance\">\
                            </form><br>\
                            </body></html>";;
    httpd_resp_send(req, resp, strlen(resp));
    return ESP_OK;
}

esp_err_t get_handler2(httpd_req_t *req)
{
    /* Send a simple response */
    const char resp[] = "Test";;
    httpd_resp_send(req, resp, strlen(resp));
    return ESP_OK;
}

/* Our URI handler function to be called during POST /uri request */
esp_err_t post_handler(httpd_req_t *req)
{
    /* Destination buffer for content of HTTP POST request.
     * httpd_req_recv() accepts char* only, but content could
     * as well be any binary data (needs type casting).
     * In case of string data, null termination will be absent, and
     * content length would give length of string */
    char content[100];

    /* Truncate if content length larger than the buffer */
    size_t recv_size = MIN(req->content_len, sizeof(content));

    int ret = httpd_req_recv(req, content, recv_size);
    if (ret <= 0) {  /* 0 return value indicates connection closed */
        /* Check if timeout occurred */
        if (ret == HTTPD_SOCK_ERR_TIMEOUT) {
            /* In case of timeout one can choose to retry calling
             * httpd_req_recv(), but to keep it simple, here we
             * respond with an HTTP 408 (Request Timeout) error */
            httpd_resp_send_408(req);
        }
        /* In case of error, returning ESP_FAIL will
         * ensure that the underlying socket is closed */
        return ESP_FAIL;
    }

    /* Send a simple response */
    const char resp[] = "URI POST Response";
    httpd_resp_send(req, resp, strlen(resp));
    return ESP_OK;
}

/* URI handler structure for GET / */
httpd_uri_t uri_get = {
    .uri      = "/",
    .method   = HTTP_GET,
    .handler  = get_handler,
    .user_ctx = NULL
};

/* URI handler structure for GET /uri */
httpd_uri_t uri_get2 = {
    .uri      = "/Distanz/",
    .method   = HTTP_GET,
    .handler  = get_handler2,
    .user_ctx = NULL
};


/* URI handler structure for POST / */
httpd_uri_t uri_post = {
    .uri      = "/",
    .method   = HTTP_POST,
    .handler  = post_handler,
    .user_ctx = NULL
};

/* Function for starting the webserver */
httpd_handle_t start_webserver(void)
{
    /* Generate default configuration */
    httpd_config_t config = HTTPD_DEFAULT_CONFIG();

    /* Empty handle to esp_http_server */
    httpd_handle_t server = NULL;

    /* Start the httpd server */
    if (httpd_start(&server, &config) == ESP_OK) {
        /* Register URI handlers */
        httpd_register_uri_handler(server, &uri_get);
        httpd_register_uri_handler(server, &uri_get2);
        httpd_register_uri_handler(server, &uri_post);
    }
    /* If server failed to start, handle will be NULL */
    return server;
}

/* Function for stopping the webserver */
void stop_webserver(httpd_handle_t server)
{
    if (server) {
        /* Stop the httpd server */
        httpd_stop(server);
    }
}








/** WIFI **/

#define WIFI_SUCCESS 1 << 0
#define WIFI_FAILURE 1 << 1
#define MAX_FAILURES 10

// event group to contain status information
static EventGroupHandle_t wifi_event_group;

// retry tracker
static int s_retry_num = 0;

// task tag
static const char *TAG = "WIFI";
/** FUNCTIONS **/

//event handler for wifi events
static void wifi_event_handler(void* arg, esp_event_base_t event_base,
                                int32_t event_id, void* event_data)
{
	if (event_base == WIFI_EVENT && event_id == WIFI_EVENT_STA_START)
	{
		ESP_LOGI(TAG, "Connecting to AP...");
		esp_wifi_connect();
	} else if (event_base == WIFI_EVENT && event_id == WIFI_EVENT_STA_DISCONNECTED)
	{
		if (s_retry_num < MAX_FAILURES)
		{
			ESP_LOGI(TAG, "Reconnecting to AP...");
			esp_wifi_connect();
			s_retry_num++;
		} else {
			xEventGroupSetBits(wifi_event_group, WIFI_FAILURE);
		}
	}
}

//event handler for ip events
static void ip_event_handler(void* arg, esp_event_base_t event_base,
                                int32_t event_id, void* event_data)
{
	if (event_base == IP_EVENT && event_id == IP_EVENT_STA_GOT_IP)
	{
        ip_event_got_ip_t* event = (ip_event_got_ip_t*) event_data;
        ESP_LOGI(TAG, "STA IP: " IPSTR, IP2STR(&event->ip_info.ip));
        s_retry_num = 0;
        xEventGroupSetBits(wifi_event_group, WIFI_SUCCESS);
    }

}

// connect to wifi and return the result
esp_err_t connect_wifi()
{
	int status = WIFI_FAILURE;

	/** INITIALIZE ALL THE THINGS **/
	//initialize the esp network interface
	ESP_ERROR_CHECK(esp_netif_init());

	//initialize default esp event loop
	ESP_ERROR_CHECK(esp_event_loop_create_default());

	//create wifi station in the wifi driver
	esp_netif_create_default_wifi_sta();

	//setup wifi station with the default wifi configuration
	wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
    ESP_ERROR_CHECK(esp_wifi_init(&cfg));

    /** EVENT LOOP CRAZINESS **/
	wifi_event_group = xEventGroupCreate();

    esp_event_handler_instance_t wifi_handler_event_instance;
    ESP_ERROR_CHECK(esp_event_handler_instance_register(WIFI_EVENT,
                                                        ESP_EVENT_ANY_ID,
                                                        &wifi_event_handler,
                                                        NULL,
                                                        &wifi_handler_event_instance));

    esp_event_handler_instance_t got_ip_event_instance;
    ESP_ERROR_CHECK(esp_event_handler_instance_register(IP_EVENT,
                                                        IP_EVENT_STA_GOT_IP,
                                                        &ip_event_handler,
                                                        NULL,
                                                        &got_ip_event_instance));

    /** START THE WIFI DRIVER **/
    wifi_config_t wifi_config = {
        .sta = {
            .ssid = "Andy",
            .password = "bonobo27",
	     .threshold.authmode = WIFI_AUTH_WPA2_PSK,
            .pmf_cfg = {
                .capable = true,
                .required = false
            },
        },
    };

    // set the wifi controller to be a station
    ESP_ERROR_CHECK(esp_wifi_set_mode(WIFI_MODE_STA) );

    // set the wifi config
    ESP_ERROR_CHECK(esp_wifi_set_config(WIFI_IF_STA, &wifi_config) );

    // start the wifi driver
    ESP_ERROR_CHECK(esp_wifi_start());

    ESP_LOGI(TAG, "STA initialization complete");

    /** NOW WE WAIT **/
    EventBits_t bits = xEventGroupWaitBits(wifi_event_group,
            WIFI_SUCCESS | WIFI_FAILURE,
            pdFALSE,
            pdFALSE,
            portMAX_DELAY);

    /* xEventGroupWaitBits() returns the bits before the call returned, hence we can test which event actually
     * happened. */
    if (bits & WIFI_SUCCESS) {
        ESP_LOGI(TAG, "Connected to ap");
        status = WIFI_SUCCESS;
    } else if (bits & WIFI_FAILURE) {
        ESP_LOGI(TAG, "Failed to connect to ap");
        status = WIFI_FAILURE;
    } else {
        ESP_LOGE(TAG, "UNEXPECTED EVENT");
        status = WIFI_FAILURE;
    }

    /* The event will not be processed after unregister */
    ESP_ERROR_CHECK(esp_event_handler_instance_unregister(IP_EVENT, IP_EVENT_STA_GOT_IP, got_ip_event_instance));
    ESP_ERROR_CHECK(esp_event_handler_instance_unregister(WIFI_EVENT, ESP_EVENT_ANY_ID, wifi_handler_event_instance));
    vEventGroupDelete(wifi_event_group);

    return status;
}





//FUNCTION TO SET MOTOR SPEED

//MOTOR RIGHT
//1023 max, 512Half
void motor_set_speed(uint16_t speed) {
    // Set duty cycle of LEDC channel
    ledc_set_duty(LEDC_HIGH_SPEED_MODE, LEDC_CHANNEL_0, speed);
    ledc_update_duty(LEDC_HIGH_SPEED_MODE, LEDC_CHANNEL_0);
}

//MOTOR LEFT
void motor_set_speed2(uint16_t speed) {
    // Set duty cycle of LEDC channel
    ledc_set_duty(LEDC_HIGH_SPEED_MODE, LEDC_CHANNEL_1, speed);
    ledc_update_duty(LEDC_HIGH_SPEED_MODE, LEDC_CHANNEL_1);
}


//Robot turns right
void go_right(){
    motor_set_speed(300);
    motor_set_speed2(0);
}

//Robot turns left
void go_left(){
    motor_set_speed(0);
    motor_set_speed2(300);
}


//Robot stays still
void go_still(){
    motor_set_speed(0);
    motor_set_speed2(0);
}

//Robot goes straight
void go_straight(){
    motor_set_speed(500);
    motor_set_speed2(390);
}

//ADC reads no PT
void reset_PTvalues(){
    gpio_set_level(GPIO_NUM_16, 0);
    gpio_set_level(GPIO_NUM_17, 0);
    gpio_set_level(GPIO_NUM_25, 0);
    gpio_set_level(GPIO_NUM_26, 0);
    gpio_set_level(GPIO_NUM_27, 0);
    gpio_set_level(GPIO_NUM_32, 0);
}


//Main Program
void app_main(void)
{

//Setup und Variablen setzen
setup();

//Motoren auf 0 setzen
go_still();

//Infrarotsignal on an Led7
gpio_set_level(GPIO_NUM_12,1);


///////////////////////////////////////////////////////
// esp_err_t status = WIFI_FAILURE;

// 	//initialize storage
//     esp_err_t ret = nvs_flash_init();
//     if (ret == ESP_ERR_NVS_NO_FREE_PAGES || ret == ESP_ERR_NVS_NEW_VERSION_FOUND) {
//       ESP_ERROR_CHECK(nvs_flash_erase());
//       ret = nvs_flash_init();
//     }
//     ESP_ERROR_CHECK(ret);

//     // connect to wireless AP
// 	status = connect_wifi();
// 	if (WIFI_SUCCESS != status)
// 	{
// 		ESP_LOGI(TAG, "Failed to associate to AP, dying...");
// 		return;
// 	}


// start_webserver();

//////////////////////////////////////////////////////

gettimeofday(&start_time,NULL);

 while(1){

    

    //GET PT Values
    gpio_set_level(GPIO_NUM_16, 1);
    phototransistor_value_PT6 = adc1_get_raw(ADC1_CHANNEL_7);
    reset_PTvalues();

    gpio_set_level(GPIO_NUM_25, 1);
    phototransistor_value_left = adc1_get_raw(ADC1_CHANNEL_7);
    reset_PTvalues();

    gpio_set_level(GPIO_NUM_26, 1);
    phototransistor_value_right = adc1_get_raw(ADC1_CHANNEL_7);
    reset_PTvalues();

/*
    gpio_set_level(GPIO_NUM_27, 1);
    int phototransistor_value_back = adc1_get_raw(ADC1_CHANNEL_7);
    reset_PTvalues();


    gpio_set_level(GPIO_NUM_32, 1);
    int phototransistor_value_front = adc1_get_raw(ADC1_CHANNEL_7);
    reset_PTvalues();
*/


/*
Dezibot going straight while no object in front and turns to either left or right depending on which side recognizes a higher PT value
which signalizes an obstacle
*/

    if(phototransistor_value_PT6 < 3500){
        if(tracking){
            gettimeofday(&start_time,NULL);
            tracking = false;
        }
        //go_straight();
    }
    else if(phototransistor_value_right > phototransistor_value_left){
        ESP_LOGI("Going","left");

        //go_left();
        calc_distance();
        change_direction('l');
        vTaskDelay(11000 / portTICK_PERIOD_MS);

    }
    else{
        ESP_LOGI("Going","right");

        //go_right();
        calc_distance();
        change_direction('r');
        vTaskDelay(11000 / portTICK_PERIOD_MS);
    }


  // Gib den Wert auf der Konsole aus
  /*
  ESP_LOGI("main", "Vorne(PT6): %d Links: %d Rechts: %d Hinten: %d Vorne: %d", phototransistor_value_PT6, phototransistor_value_left, phototransistor_value_right, phototransistor_value_back, phototransistor_value_front);
  */

  // Kurze Pause
  vTaskDelay(10 / portTICK_PERIOD_MS);

 }

}
