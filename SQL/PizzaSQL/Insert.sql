execute NeuerKoch (2000, '01.07.2010', 'Gustav', 'Neuer', '16.08.1970', 'M', 'Pariser Weg 3a', 0, 2, 10);
execute NeuerKoch (1800, '14.03.2012', 'Frank', 'Baum', '29.11.1989', 'M', 'Baumstra�e 23', 3, 1, 8);
execute NeuerKoch (2300, '04.10.2009', 'Hans', 'Brunnenbauer', '03.02.1973', 'M', 'Gartenstra�e 14', 2, 3, 10);

execute neuerlieferant(1600, '08.09.2009', 'Fridolin', 'Morgenstern', '01.07.1984', 'M', 'Am Zoo 54', 1, 'Mo');
execute neuerlieferant(1500, '06.03.2011', 'Anke', 'Augenthaler', '04.06.1987', 'F', 'Dornenfelder Stra�e 12', 0, 'Mo');
execute neuerlieferant(1700, '16.07.2010', 'Mathilda', 'Degen', '16.04.1986', 'F', 'Bananenstra�e 11a', 0, 'Mo');

execute neuerbestellnehmer(1450, '14.03.2011', 'John', 'Dorian', '11.02.1976', 'M', 'G�nther-Netzer-Stra�e 123', 1, '012463876');
execute neuerbestellnehmer(1400, '08.06.2012', 'Elliot', 'Reid', '08.09.1978', 'F', 'Hafenstra�e 69', 0, '01496565');

insert into pizzen values ('Hawaii', 'big');
insert into pizzen values ('Salami', 'small');
insert into pizzen values ('Prosciutto', 'medium');
insert into pizzen values ('Funghi', 'medium');
insert into pizzen values ('Peperoni', 'big');
insert into pizzen values ('Quattro Stagioni', 'big');
insert into pizzen values ('Tonno', 'small');
insert into pizzen values ('Margherita', 'big');
insert into pizzen values ('Frutti di Mare', 'big');
insert into pizzen values ('Bolognese', 'medium');
insert into pizzen values ('Napoli', 'small');
insert into pizzen values ('Verdi', 'medium');

insert into zutaten values ('Ananas', 'N');
insert into zutaten values ('Schinken', 'Y');
insert into zutaten values ('Tomatenso�e', 'N');
insert into zutaten values ('K�se', 'N');
insert into zutaten values ('Salami', 'Y');
insert into zutaten values ('Pilze', 'N');
insert into zutaten values ('Pepperoni', 'N');
insert into zutaten values ('Paprika', 'N');
insert into zutaten values ('Thunfisch', 'Y');
insert into zutaten values ('Zwiebeln', 'N');
insert into zutaten values ('Meeresfr�chte', 'Y');
insert into zutaten values ('Hackfleisch', 'Y');
insert into zutaten values ('Sardellen', 'Y');
insert into zutaten values ('Oliven', 'N');
insert into zutaten values ('Spinat', 'N');
insert into zutaten values ('Ei', 'N');

insert into ENTH�LT values ('Hawaii', 'Schinken');
insert into ENTH�LT values ('Hawaii', 'Ananas');
insert into ENTH�LT values ('Hawaii', 'Tomatenso�e');
insert into ENTH�LT values ('Hawaii', 'K�se');

insert into ENTH�LT values ('Salami', 'Salami');
insert into ENTH�LT values ('Salami', 'Tomatenso�e');
insert into ENTH�LT values ('Salami', 'K�se');

insert into ENTH�LT values ('Prosciutto', 'Schinken');
insert into ENTH�LT values ('Prosciutto', 'Tomatenso�e');
insert into ENTH�LT values ('Prosciutto', 'K�se');

insert into ENTH�LT values ('Funghi', 'Pilze');
insert into ENTH�LT values ('Funghi', 'Tomatenso�e');
insert into ENTH�LT values ('Funghi', 'K�se');

insert into ENTH�LT values ('Peperoni', 'Pepperoni');
insert into ENTH�LT values ('Peperoni', 'Tomatenso�e');
insert into ENTH�LT values ('Peperoni', 'K�se');

insert into ENTH�LT values ('Quattro Stagioni', 'Salami');
insert into ENTH�LT values ('Quattro Stagioni', 'Tomatenso�e');
insert into ENTH�LT values ('Quattro Stagioni', 'K�se');
insert into ENTH�LT values ('Quattro Stagioni', 'Paprika');
insert into ENTH�LT values ('Quattro Stagioni', 'Schinken');
insert into ENTH�LT values ('Quattro Stagioni', 'Pilze');

insert into ENTH�LT values ('Tonno', 'Thunfisch');
insert into ENTH�LT values ('Tonno', 'Zwiebeln');
insert into ENTH�LT values ('Tonno', 'Tomatenso�e');
insert into ENTH�LT values ('Tonno', 'K�se');

insert into ENTH�LT values ('Margherita', 'Tomatenso�e');
insert into ENTH�LT values ('Margherita', 'K�se');

insert into ENTH�LT values ('Frutti di Mare', 'Tomatenso�e');
insert into ENTH�LT values ('Frutti di Mare', 'K�se');
insert into ENTH�LT values ('Frutti di Mare', 'Meeresfr�chte');

insert into ENTH�LT values ('Bolognese', 'Tomatenso�e');
insert into ENTH�LT values ('Bolognese', 'K�se');
insert into ENTH�LT values ('Bolognese', 'Hackfleisch');
insert into ENTH�LT values ('Bolognese', 'Zwiebeln');

insert into ENTH�LT values ('Napoli', 'Tomatenso�e');
insert into ENTH�LT values ('Napoli', 'K�se');
insert into ENTH�LT values ('Napoli', 'Sardellen');
insert into ENTH�LT values ('Napoli', 'Oliven');

insert into ENTH�LT values ('Verdi', 'Tomatenso�e');
insert into ENTH�LT values ('Verdi', 'K�se');
insert into ENTH�LT values ('Verdi', 'Spinat');
insert into ENTH�LT values ('Verdi', 'Ei');


insert into Kunden values (KIDZ�hler.nextval, '09.11.2014', 'Franz', 'Wiesel', 'Ringstra�e 2', '018765245');
insert into Kunden values (KIDZ�hler.nextval, '12.01.2011', 'Benedigt', 'Bohnenstau', 'Dorfweg 45', '01535632');
insert into Kunden values (KIDZ�hler.nextval, '24.12.2013', 'Wilhelm', 'Haselnuss', 'Am Berg 14', '0124565879');
insert into Kunden values (KIDZ�hler.nextval, '08.06.2016', 'Joachim', 'Svensson', 'Pandaweg 65', '015786231');
insert into Kunden values (KIDZ�hler.nextval, '06.03.2014', 'Egon', 'Uhlig', 'Ringstra�e 2', '0148786536');
insert into Kunden values (KIDZ�hler.nextval, '30.08.2018', 'Hildegard', 'Wei�enfels', 'Merkelweg 7', '0126358758');
insert into Kunden values (KIDZ�hler.nextval, '14.02.2011', 'Johanna', 'Hotzenplotz', 'Fuchsstra�e 26', '01235896');
insert into Kunden values (KIDZ�hler.nextval, '18.04.2010', 'Olga', 'Ibertsen', 'Ringstra�e 17', '01668533,4');
insert into Kunden values (KIDZ�hler.nextval, '21.07.2018', 'Heinz', 'M�ller', 'Katerweg 11', '0132456878');
insert into Kunden values (KIDZ�hler.nextval, '15.07.2017', 'Steve', 'D�mbel', 'Bielefelder Allee 48', '013248654');
insert into Kunden values (KIDZ�hler.nextval, '01.08.2016', 'Charlotte', 'Rasenm�her', 'Blindenstra�e 25', '012465545');
insert into Kunden values (KIDZ�hler.nextval, '29.06.2014', 'Manfred', 'Wurstmann', 'Rosenring 29', '0123684');
insert into Kunden values (KIDZ�hler.nextval, '03.10.2013', 'Ronny', 'Danger', 'Am Venediger Platz 15', '015684532');
insert into Kunden values (KIDZ�hler.nextval, '28.09.2012', 'Mercutio', 'Musikant', 'Dissonanzenstra�e 11', '0158765453');
insert into Kunden values (KIDZ�hler.nextval, '24.01.2017', 'Romeo', 'Shakespeare', 'Schwertweg 9', '0127585512');
insert into Kunden values (KIDZ�hler.nextval, '19.07.2018', 'Julia', 'William', 'Bauernweg 29', '012654532');


execute neuebestellung('29.01.2020 10:00:00', 20, 'N', 1, 1, 4, 7); 
insert into bestehend_aus values ('Hawaii', bidz�hler.currval, 2);

execute neuebestellung('29.01.2020 11:00:00', 40, 'Y', 1, 2, 5, 7);
insert into bestehend_aus values ('Quattro Stagioni', bidz�hler.currval, 1);
insert into bestehend_aus values ('Napoli', bidz�hler.currval, 2);

execute neuebestellung('01.02.2020 17:45:00', 70, 'Y', 7, 2, 5, 8);
insert into bestehend_aus values ('Funghi', bidz�hler.currval, 1);
insert into bestehend_aus values ('Bolognese', bidz�hler.currval, 2);
insert into bestehend_aus values ('Verdi', bidz�hler.currval, 1);
insert into bestehend_aus values ('Hawaii', bidz�hler.currval, 3);

execute neuebestellung('02.02.2020 09:10:00', 40, 'N', 10, 1, 6, 7);
insert into bestehend_aus values ('Tonno', bidz�hler.currval, 2);
insert into bestehend_aus values ('Prosciutto', bidz�hler.currval, 2);

execute neuebestellung('04.02.2020 22:30:00', 60, 'Y', 6, 3, 4, 8);
insert into bestehend_aus values ('Tonno', bidz�hler.currval, 3);
insert into bestehend_aus values ('Salami', bidz�hler.currval, 1);

execute neueArbeitszeit('29.01.2020 8:00', '29.01.2020 22:00:00', 1);
insert into Arbeitet values (2, AIDZ�hler.currval);
insert into Arbeitet values (4, AIDZ�hler.currval);
insert into Arbeitet values (5, AIDZ�hler.currval);
insert into Arbeitet values (7, AIDZ�hler.currval);

execute neueArbeitszeit('01.02.2020 8:00', '01.02.2020 22:00:00', 2);
insert into Arbeitet values (5, AIDZ�hler.currval);
insert into Arbeitet values (8, AIDZ�hler.currval);

execute neueArbeitszeit('02.02.2020 8:00', '02.02.2020 22:00:00', 1);
insert into Arbeitet values (2, AIDZ�hler.currval);
insert into Arbeitet values (4, AIDZ�hler.currval);
insert into Arbeitet values (5, AIDZ�hler.currval);
insert into Arbeitet values (7, AIDZ�hler.currval);

execute neueArbeitszeit('04.02.2020 8:00', '04.02.2020 22:00:00', 1);
insert into Arbeitet values (6, AIDZ�hler.currval);
insert into Arbeitet values (7, AIDZ�hler.currval);

execute neueArbeitszeit('29.01.2020 8:00', '29.01.2020 22:00:00', 3);
insert into Arbeitet values (4, AIDZ�hler.currval);
insert into Arbeitet values (8, AIDZ�hler.currval);
