#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>
#include <stdint.h>
#include <sys/ioctl.h>
#include <unistd.h>

// Opens the specified I2C device.  Returns a non-negative file descriptor
// on success, or -1 on failure.
int open_i2c_device(const char * device)
{
  int fd = open(device, O_RDWR);
  if (fd == -1)
  {
    perror(device);
    return -1;
  }
  return fd;
}



void draw2 (int fd, uint8_t address, uint8_t command[8]){

 struct i2c_msg message = {address, 0, 8, command};
 struct i2c_rdwr_ioctl_data ioctl_data = {&message,1};
 ioctl(fd, I2C_RDWR, &ioctl_data);

}


void draw (int fd, uint8_t address, uint8_t command1, uint8_t command2){

 uint8_t command[2]={command1, command2};
 struct i2c_msg message = {address, 0, 2, command};
 struct i2c_rdwr_ioctl_data ioctl_data = {&message,1};
 ioctl(fd, I2C_RDWR, &ioctl_data);

}

void clear (int fd, uint8_t address){

 int i = 0;

 for(i=0;i<127;i++) draw(fd, address, 0x40, 0x00);

 draw(fd, address, 0x80, 0x21);

 draw(fd, address, 0x80, 40);

 draw(fd, address, 0x80, 127);


 draw(fd, address, 0x80, 0x22);

 draw(fd, address, 0x80, 4);

 draw(fd, address, 0x80, 4);

}


int main()
{

//Zeichen 0-9 + . + ' + C + %
uint8_t font8x8_basic[14][8] = {
{0x40, 0b00000000, 0b00111110, 0b01010001, 0b01001001, 0b01000101, 0b00111110, 0x00},
{0x40, 0b00000000, 0b00000000, 0b00000001, 0b01111111, 0b00100001, 0b00000000, 0x00},
{0x40, 0b00000000, 0b00110001, 0b01001001, 0b01000101, 0b01000011, 0b00100001, 0x00},
{0x40, 0b00000000, 0b01000110, 0b01101001, 0b01010001, 0b01000001, 0b01000010, 0x00},
{0x40, 0b00000000, 0b00000100, 0b01111111, 0b00100100, 0b00010100, 0b00001100, 0x00},
{0x40, 0b00000000, 0b01001110, 0b01010001, 0b01010001, 0b01010001, 0b01110010, 0x00},
{0x40, 0b00000000, 0b00000110, 0b01001001, 0b01001001, 0b00101001, 0b00011110, 0x00},
{0x40, 0b00000000, 0b01100000, 0b01010000, 0b01001000, 0b01000100, 0b01000011, 0x00},
{0x40, 0b00000000, 0b00110110, 0b01001001, 0b01001001, 0b01001001, 0b00110110, 0x00},
{0x40, 0b00000000, 0b00111100, 0b01001010, 0b01001001, 0b01001001, 0b00110000, 0x00},
{0x40, 0x00, 0x00, 0x00, 0b00000011, 0b00000011, 0x00, 0x00},
{0x40, 0x00, 0b11000000, 0b11000000, 0x00, 0x00, 0x00, 0x00},
{0x40, 0x00, 0b00100010, 0b01000001, 0b10000001, 0b01000001, 0b00111110, 0x00},
{0x40, 0x00, 0x00, 0b01100011, 0b00010011, 0b00001000, 0b01100100, 0b01100011},
};



  // Choose the I2C device.
  const char * device = "/dev/i2c-0";

  // Set the I2C address
  const uint8_t address = 0x3c;

 FILE * fPointer;
 char temp[5];
 char humidity[5];
 char taster[2];
 int tasterInt;
 uint8_t charToInt;

  int fd = open_i2c_device(device);
  if (fd < 0) { return 1; }


 while(1){

 clear(fd, address);

 fPointer = fopen("/sys/class/gpio/gpio17/value","r");
 fgets(taster, 2, fPointer);
 fclose(fPointer);
 tasterInt = (int)taster[0]-48;

 if(!tasterInt){

 fPointer = fopen("/sys/class/hwmon/hwmon2/humidity1_input","r");
 fgets(humidity, 5, fPointer);
 fclose(fPointer);


 draw2(fd, address, font8x8_basic[13]);

 charToInt = (int)humidity[3] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 charToInt = (int)humidity[2] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 draw2(fd, address, font8x8_basic[10]);

 charToInt = (int)humidity[1] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 charToInt = (int)humidity[0] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);
}

 if(tasterInt){

 fPointer = fopen("/sys/class/hwmon/hwmon2/temp1_input","r");
 fgets(temp, 5, fPointer);
 fclose(fPointer);


 draw2(fd, address, font8x8_basic[12]);

 draw2(fd, address, font8x8_basic[11]);

 charToInt = (int)temp[3] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 charToInt = (int)temp[2] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 draw2(fd, address, font8x8_basic[10]);

 charToInt = (int)temp[1] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);

 charToInt = (int)temp[0] - 48;
 draw2(fd, address, font8x8_basic[charToInt]);


}

sleep(2);
}
  close(fd);
  return 0;
}

