#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <avr/sleep.h>
#include <avr/wdt.h>

//Zeit Variablen
volatile uint8_t sekunde1;
volatile uint8_t sekunde2;
volatile uint8_t minute1;
volatile uint8_t minute2;
volatile uint8_t stunde1;
volatile uint8_t stunde2;
//sleepmode counter
volatile uint8_t counter;
//ledanzeige counter
volatile uint8_t ledcounter;

void inittimer0(void);	//Initialisiert Timer0
void inittimer2(void);	//Initialisiert Timer2
void Aufruf(int zeit);	//schiebt Bits in Zeile
void zeitanzeige(void);	//bringt LEDs zum leuchten durch an und ausschalten der Zeilen
void clear(void);	//schaltet alle Zeilen aus


int main (void){
	//Watchdog ausschalten, wobei eigentlich sowieso nicht an
	wdt_disable();
	//auf Ausgang schalten
	DDRB = 0b11111;
	DDRC = 0b1111;
	DDRD = 0b11110000;
	//auf 1 setzen damit ext. Interrupt funktioniert
	PORTD |= (1 << PD2);
	//General Interrupt Control Register - erlaubt ext. Interrupt von Int0(PD2)
	GICR |= (1 << INT0);
	//ext. Interrupt bei fallender Flanke
	MCUCR |= (1 << ISC01);
	MCUCR &= ~(1 << ISC00); 
	//Initialisiere Timer0
	inittimer0();
	//Initialisiere Timer2
	inittimer2();
	//Zeit setzen
	sekunde1 = 0;
	sekunde2 = 5;
	minute1 = 9;
	minute2 = 5;
	stunde1 = 1;
	stunde2 = 1;
	//sleep mode auf power save setzen (CPU aus, Timer2 geht asynchron weiter, sowie ext.Interrupt erlaubt zum wecken)
	set_sleep_mode (SLEEP_MODE_PWR_SAVE);
	sei();	//Globalen Interrupt aufrufen

	while(1){
	//ab 30 Sekunden in sleepmode gehen
		if (counter == 30){
			clear(); //schaltet alle Zeilen aus
			//Da im sleepmode Flankensteuerung nicht funktioniert --> auf lowlvl interrupt
			MCUCR &= ~(1 << ISC01);
			MCUCR &= ~(1 << ISC00);
			sleep_mode();
			//wacht jede Sekunde bei Interrupt Timer2 auf dann wieder sleepmode
			//CPU aus test
			//zeitanzeige();
		}

		else{
			zeitanzeige();
		}
	}
}

void inittimer0(void){
	
	TCCR0 |= (1 << CS01) | (1 << CS00);	//Prescaler 64 (8000000/64 = 125000)
	TCNT0 = 256 - 125;      //1000 OVs (1000x125 = 125000)
	TIMSK |= (1 << TOIE0);	//Overflow intterupt enable
}
//Overflow Interrupt Händler von Timer0
ISR(TIMER0_OVF_vect){
	TCNT0 = 256 - 125;
	
	//zum schalten der Zeilen, 1000/13 = 76,9 also rund 77mal LEDs pro Sekunde
	ledcounter++;

	if (ledcounter == 13){
		ledcounter = 0;
		}


}

void inittimer2(void){
	ASSR |= (1 << AS2);	//Timer2 asynchron über externen Quarz/Real Timer Counter (RTC)
	OCR2 = 31; //32768/1024 - 1 = 31
	TCCR2 |= (1 << WGM21) | (1 << CS20) | (1 << CS21) | (1 << CS22); //CTC und 1024Prescaler
	TIMSK |= (1 << OCIE2); //Enable Compare Interrupt
	TCNT2 = 0x00;	//Register
}
//Compare Interrupt Händler von Timer2
ISR(TIMER2_COMP_vect){
		//Hochzählen für sleepmode
		if (counter < 30){
			counter++;
			}
		//Zeit zählen
		sekunde1++;
		if (sekunde1 == 10){
			sekunde2++;
			sekunde1 = 0;
			if (sekunde2 == 6){
				minute1++;
				sekunde2 = 0;
			}
			if (minute1 == 10){
				minute2++;
				minute1 = 0;
			}
			if (minute2 == 6){
				stunde1++;
				minute2 = 0;
			}
			if (stunde1 == 4 && stunde2 == 2){
				stunde1 = 0;
				stunde2 = 0;
			}
			else if(stunde1 == 10){
				stunde2++;
				stunde1 = 0;
			}
		}
}
//externer Interrupt Händler
ISR(INT0_vect){
	counter = 0; //Wenn Knopf gedrückt --> counter auf 0, d.h. wieder 30s warten für sleepmode
	//fallende Flanke wieder einstellen
	MCUCR |= (1 << ISC01);
	MCUCR &= ~(1 << ISC00);
}

//schiebt Bits in Zeile
//Strobe auf 1 dann CLK-Signale erzeugen und über steigende Flanke Bits in Register schieben
void Aufruf(int zeit){
		

		for (int i = 1; i < 13; i++){
			if ( i <= zeit){
				PORTB &= ~(1 << PB3);
				PORTB &= ~(1 << PB4);
				PORTB |= (1 << PB3);
			}
			else {
				PORTB &= ~(1 << PB3);
				PORTB |= (1 << PB4);
				PORTB |= (1 << PB3);
			}
		}
}

//schaltet Zeilen an und aus nach Aufruf
void zeitanzeige(void){

	if(ledcounter == 0){
	Aufruf(sekunde1);
	PORTC |= (1 << PC0);
	}
	else if(ledcounter == 2){
	PORTC &= ~(1 << PC0);
	Aufruf(sekunde2);
	PORTC |= (1 << PC1);
	}
	else if(ledcounter == 4){
	PORTC &= ~(1 << PC1);
	Aufruf(minute1);
	PORTD |= (1 << PD4);
	}
	else if(ledcounter == 6){
	PORTD &= ~(1 << PD4);
	Aufruf(minute2);
	PORTD |= (1 << PD5);
	}
	else if(ledcounter == 8){
	PORTD &= ~(1 << PD5);
	Aufruf(stunde1);
	PORTB |= (1 << PB0);
	}
	else if(ledcounter == 10){
	PORTB &= ~(1 << PB0);
	Aufruf(stunde2);
	PORTB |= (1 << PB1);
	}
	else if(ledcounter == 12){
	PORTB &= ~(1 << PB1);
	}
	//Strobe an und ausschalten
	PORTB |= (1 << PB2);
	PORTB &= ~(1 << PB2);
}

void clear(void){
	PORTB &= ~(1 << PB0);
	PORTB &= ~(1 << PB1);
	PORTC &= ~(1 << PC0);
	PORTC &= ~(1 << PC1);
	PORTD &= ~(1 << PD4);
	PORTD &= ~(1 << PD5);
}

	
