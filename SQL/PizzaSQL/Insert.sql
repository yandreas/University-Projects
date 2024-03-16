execute NeuerKoch (2000, '01.07.2010', 'Gustav', 'Neuer', '16.08.1970', 'M', 'Pariser Weg 3a', 0, 2, 10);
execute NeuerKoch (1800, '14.03.2012', 'Frank', 'Baum', '29.11.1989', 'M', 'Baumstraße 23', 3, 1, 8);
execute NeuerKoch (2300, '04.10.2009', 'Hans', 'Brunnenbauer', '03.02.1973', 'M', 'Gartenstraße 14', 2, 3, 10);

execute neuerlieferant(1600, '08.09.2009', 'Fridolin', 'Morgenstern', '01.07.1984', 'M', 'Am Zoo 54', 1, 'Mo');
execute neuerlieferant(1500, '06.03.2011', 'Anke', 'Augenthaler', '04.06.1987', 'F', 'Dornenfelder Straße 12', 0, 'Mo');
execute neuerlieferant(1700, '16.07.2010', 'Mathilda', 'Degen', '16.04.1986', 'F', 'Bananenstraße 11a', 0, 'Mo');

execute neuerbestellnehmer(1450, '14.03.2011', 'John', 'Dorian', '11.02.1976', 'M', 'Günther-Netzer-Straße 123', 1, '012463876');
execute neuerbestellnehmer(1400, '08.06.2012', 'Elliot', 'Reid', '08.09.1978', 'F', 'Hafenstraße 69', 0, '01496565');

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
insert into zutaten values ('Tomatensoße', 'N');
insert into zutaten values ('Käse', 'N');
insert into zutaten values ('Salami', 'Y');
insert into zutaten values ('Pilze', 'N');
insert into zutaten values ('Pepperoni', 'N');
insert into zutaten values ('Paprika', 'N');
insert into zutaten values ('Thunfisch', 'Y');
insert into zutaten values ('Zwiebeln', 'N');
insert into zutaten values ('Meeresfrüchte', 'Y');
insert into zutaten values ('Hackfleisch', 'Y');
insert into zutaten values ('Sardellen', 'Y');
insert into zutaten values ('Oliven', 'N');
insert into zutaten values ('Spinat', 'N');
insert into zutaten values ('Ei', 'N');

insert into ENTHÄLT values ('Hawaii', 'Schinken');
insert into ENTHÄLT values ('Hawaii', 'Ananas');
insert into ENTHÄLT values ('Hawaii', 'Tomatensoße');
insert into ENTHÄLT values ('Hawaii', 'Käse');

insert into ENTHÄLT values ('Salami', 'Salami');
insert into ENTHÄLT values ('Salami', 'Tomatensoße');
insert into ENTHÄLT values ('Salami', 'Käse');

insert into ENTHÄLT values ('Prosciutto', 'Schinken');
insert into ENTHÄLT values ('Prosciutto', 'Tomatensoße');
insert into ENTHÄLT values ('Prosciutto', 'Käse');

insert into ENTHÄLT values ('Funghi', 'Pilze');
insert into ENTHÄLT values ('Funghi', 'Tomatensoße');
insert into ENTHÄLT values ('Funghi', 'Käse');

insert into ENTHÄLT values ('Peperoni', 'Pepperoni');
insert into ENTHÄLT values ('Peperoni', 'Tomatensoße');
insert into ENTHÄLT values ('Peperoni', 'Käse');

insert into ENTHÄLT values ('Quattro Stagioni', 'Salami');
insert into ENTHÄLT values ('Quattro Stagioni', 'Tomatensoße');
insert into ENTHÄLT values ('Quattro Stagioni', 'Käse');
insert into ENTHÄLT values ('Quattro Stagioni', 'Paprika');
insert into ENTHÄLT values ('Quattro Stagioni', 'Schinken');
insert into ENTHÄLT values ('Quattro Stagioni', 'Pilze');

insert into ENTHÄLT values ('Tonno', 'Thunfisch');
insert into ENTHÄLT values ('Tonno', 'Zwiebeln');
insert into ENTHÄLT values ('Tonno', 'Tomatensoße');
insert into ENTHÄLT values ('Tonno', 'Käse');

insert into ENTHÄLT values ('Margherita', 'Tomatensoße');
insert into ENTHÄLT values ('Margherita', 'Käse');

insert into ENTHÄLT values ('Frutti di Mare', 'Tomatensoße');
insert into ENTHÄLT values ('Frutti di Mare', 'Käse');
insert into ENTHÄLT values ('Frutti di Mare', 'Meeresfrüchte');

insert into ENTHÄLT values ('Bolognese', 'Tomatensoße');
insert into ENTHÄLT values ('Bolognese', 'Käse');
insert into ENTHÄLT values ('Bolognese', 'Hackfleisch');
insert into ENTHÄLT values ('Bolognese', 'Zwiebeln');

insert into ENTHÄLT values ('Napoli', 'Tomatensoße');
insert into ENTHÄLT values ('Napoli', 'Käse');
insert into ENTHÄLT values ('Napoli', 'Sardellen');
insert into ENTHÄLT values ('Napoli', 'Oliven');

insert into ENTHÄLT values ('Verdi', 'Tomatensoße');
insert into ENTHÄLT values ('Verdi', 'Käse');
insert into ENTHÄLT values ('Verdi', 'Spinat');
insert into ENTHÄLT values ('Verdi', 'Ei');


insert into Kunden values (KIDZähler.nextval, '09.11.2014', 'Franz', 'Wiesel', 'Ringstraße 2', '018765245');
insert into Kunden values (KIDZähler.nextval, '12.01.2011', 'Benedigt', 'Bohnenstau', 'Dorfweg 45', '01535632');
insert into Kunden values (KIDZähler.nextval, '24.12.2013', 'Wilhelm', 'Haselnuss', 'Am Berg 14', '0124565879');
insert into Kunden values (KIDZähler.nextval, '08.06.2016', 'Joachim', 'Svensson', 'Pandaweg 65', '015786231');
insert into Kunden values (KIDZähler.nextval, '06.03.2014', 'Egon', 'Uhlig', 'Ringstraße 2', '0148786536');
insert into Kunden values (KIDZähler.nextval, '30.08.2018', 'Hildegard', 'Weißenfels', 'Merkelweg 7', '0126358758');
insert into Kunden values (KIDZähler.nextval, '14.02.2011', 'Johanna', 'Hotzenplotz', 'Fuchsstraße 26', '01235896');
insert into Kunden values (KIDZähler.nextval, '18.04.2010', 'Olga', 'Ibertsen', 'Ringstraße 17', '01668533,4');
insert into Kunden values (KIDZähler.nextval, '21.07.2018', 'Heinz', 'Müller', 'Katerweg 11', '0132456878');
insert into Kunden values (KIDZähler.nextval, '15.07.2017', 'Steve', 'Dümbel', 'Bielefelder Allee 48', '013248654');
insert into Kunden values (KIDZähler.nextval, '01.08.2016', 'Charlotte', 'Rasenmäher', 'Blindenstraße 25', '012465545');
insert into Kunden values (KIDZähler.nextval, '29.06.2014', 'Manfred', 'Wurstmann', 'Rosenring 29', '0123684');
insert into Kunden values (KIDZähler.nextval, '03.10.2013', 'Ronny', 'Danger', 'Am Venediger Platz 15', '015684532');
insert into Kunden values (KIDZähler.nextval, '28.09.2012', 'Mercutio', 'Musikant', 'Dissonanzenstraße 11', '0158765453');
insert into Kunden values (KIDZähler.nextval, '24.01.2017', 'Romeo', 'Shakespeare', 'Schwertweg 9', '0127585512');
insert into Kunden values (KIDZähler.nextval, '19.07.2018', 'Julia', 'William', 'Bauernweg 29', '012654532');


execute neuebestellung('29.01.2020 10:00:00', 20, 'N', 1, 1, 4, 7); 
insert into bestehend_aus values ('Hawaii', bidzähler.currval, 2);

execute neuebestellung('29.01.2020 11:00:00', 40, 'Y', 1, 2, 5, 7);
insert into bestehend_aus values ('Quattro Stagioni', bidzähler.currval, 1);
insert into bestehend_aus values ('Napoli', bidzähler.currval, 2);

execute neuebestellung('01.02.2020 17:45:00', 70, 'Y', 7, 2, 5, 8);
insert into bestehend_aus values ('Funghi', bidzähler.currval, 1);
insert into bestehend_aus values ('Bolognese', bidzähler.currval, 2);
insert into bestehend_aus values ('Verdi', bidzähler.currval, 1);
insert into bestehend_aus values ('Hawaii', bidzähler.currval, 3);

execute neuebestellung('02.02.2020 09:10:00', 40, 'N', 10, 1, 6, 7);
insert into bestehend_aus values ('Tonno', bidzähler.currval, 2);
insert into bestehend_aus values ('Prosciutto', bidzähler.currval, 2);

execute neuebestellung('04.02.2020 22:30:00', 60, 'Y', 6, 3, 4, 8);
insert into bestehend_aus values ('Tonno', bidzähler.currval, 3);
insert into bestehend_aus values ('Salami', bidzähler.currval, 1);

execute neueArbeitszeit('29.01.2020 8:00', '29.01.2020 22:00:00', 1);
insert into Arbeitet values (2, AIDZähler.currval);
insert into Arbeitet values (4, AIDZähler.currval);
insert into Arbeitet values (5, AIDZähler.currval);
insert into Arbeitet values (7, AIDZähler.currval);

execute neueArbeitszeit('01.02.2020 8:00', '01.02.2020 22:00:00', 2);
insert into Arbeitet values (5, AIDZähler.currval);
insert into Arbeitet values (8, AIDZähler.currval);

execute neueArbeitszeit('02.02.2020 8:00', '02.02.2020 22:00:00', 1);
insert into Arbeitet values (2, AIDZähler.currval);
insert into Arbeitet values (4, AIDZähler.currval);
insert into Arbeitet values (5, AIDZähler.currval);
insert into Arbeitet values (7, AIDZähler.currval);

execute neueArbeitszeit('04.02.2020 8:00', '04.02.2020 22:00:00', 1);
insert into Arbeitet values (6, AIDZähler.currval);
insert into Arbeitet values (7, AIDZähler.currval);

execute neueArbeitszeit('29.01.2020 8:00', '29.01.2020 22:00:00', 3);
insert into Arbeitet values (4, AIDZähler.currval);
insert into Arbeitet values (8, AIDZähler.currval);
