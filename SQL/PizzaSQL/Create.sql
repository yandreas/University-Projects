CREATE TABLE Mitarbeiter
(PersonalID INTEGER Primary Key,
Gehalt Decimal(8,2) NOT NULL,
Einstelldatum Date NOT NULL,
MVorname VARCHAR2(20) NOT NULL,
MNachname VARCHAR2(20) NOT NULL,
Geburtsdatum Date NOT NULL,
Geschlecht VARCHAR2(1) CHECK( Geschlecht IN ('M','F','U')) NOT NULL,
MAdresse Varchar2(30)NOT NULL,
Überstunden INTEGER CHECK(Überstunden > -1)
);

--

CREATE TABLE Koch
(KochID INTEGER REFERENCES Mitarbeiter(PersonalID),
Primary Key (KochID),
Sterne INTEGER CHECK(Sterne > -1 and Sterne < 6) NOT NULL,
Arbeitserfahrung INTEGER NOT NULL
);

--

CREATE TABLE Lieferant
(LieferantID INTEGER REFERENCES Mitarbeiter(PersonalID),
Primary Key (LieferantID),
Transportmittel VARCHAR2(2) CHECK(Transportmittel IN ('Au','Fa','Fu', 'Mo')) NOT NULL
);

--

CREATE TABLE Bestellnehmer
(BestellnehmerID INTEGER REFERENCES Mitarbeiter(PersonalID),
Primary Key (BestellnehmerID),
Telefonnummer Varchar2(10) NOT NULL
);

--

CREATE TABLE Kunden
(KundenID INTEGER PRIMARY KEY,
Registrierungsdatum DATE NOT NULL,
KVorname VARCHAR2(20)NOT NULL,
KNachname VARCHAR2(20)NOT NULL,
KAdresse Varchar2(30)NOT NULL,
Telefonnummer Varchar2(10) NOT NULL
);

--

CREATE TABLE Bestellung
(BID INTEGER PRIMARY KEY,
BDatum TIMESTAMP NOT NULL,
Lieferzeit VARCHAR2(20) NOT NULL,
Bezahlt VARCHAR2(1) CHECK( Bezahlt IN ('Y', 'N')) NOT NULL,
BeKundenID INTEGER,
CONSTRAINT fkeyBeKundenID FOREIGN KEY (BeKundenID) REFERENCES Kunden(KundenID),
Pizzaanzahl INTEGER NOT NULL
);

--

CREATE TABLE Pizzen
(PName VARCHAR2(20) PRIMARY KEY,
Größe VARCHAR2(6) CHECK( Größe IN ('small','medium','big')) NOT NULL
);

--

CREATE TABLE Zutaten
(ZName VARCHAR2(20) PRIMARY KEY,
Tierisch VARCHAR2(1) CHECK( Tierisch IN ('Y', 'N')) NOT NULL
);

--

CREATE TABLE Enthält
(EPName VARCHAR(20),
CONSTRAINT fkeyEPName FOREIGN KEY (EPName) REFERENCES Pizzen(PName),
EZName VARCHAR(20),
CONSTRAINT fkeyEZName FOREIGN KEY (EZName) REFERENCES Zutaten(ZName),
PRIMARY KEY (EPName, EZName)
);

--

CREATE TABLE Arbeitszeit
(AID INTEGER PRIMARY KEY,
DatumBeginn TIMESTAMP NOT NULL,
DatumEnde TIMESTAMP NOT NULL
);

--

CREATE TABLE Arbeitet
(WPersonalID INTEGER,
CONSTRAINT fkeyWPersonalID FOREIGN KEY (WPersonalID) REFERENCES Mitarbeiter(PersonalID),
WAID INTEGER,
CONSTRAINT fkeyWAID FOREIGN KEY (WAID) REFERENCES Arbeitszeit(AID),
PRIMARY KEY (WPersonalID, WAID)
);

--

CREATE TABLE Bestehend_aus
(CPName VARCHAR(20),
CONSTRAINT fkeyCPName FOREIGN KEY (CPName) REFERENCES Pizzen(PName),
CBID INTEGER,
CONSTRAINT fkeyCBID FOREIGN KEY (CBID) REFERENCES Bestellung(BID),
Anzahl Integer NOT NULL,
PRIMARY KEY (CPName, CBID)
);

--

CREATE TABLE bereitet_zu(
BKochID INTEGER,
CONSTRAINT fkeyBKochID FOREIGN KEY (BKochID) REFERENCES Koch(KochID),
BBID INTEGER,
CONSTRAINT fkeyBBID FOREIGN KEY (BBID) REFERENCES Bestellung(BID),
PRIMARY KEY (BKochID, BBID)
);

--

CREATE TABLE liefert_aus(
NLieferantID INTEGER,
CONSTRAINT fkeyNLieferantID FOREIGN KEY (NLieferantID) REFERENCES Lieferant(LieferantID),
NBID INTEGER,
CONSTRAINT fkeyNBID FOREIGN KEY (NBID) REFERENCES Bestellung(BID),
PRIMARY KEY (NLieferantID, NBID)
);

--

CREATE TABLE nimmt_auf(
LBestellnehmerID INTEGER,
CONSTRAINT fkeyLBestellnehmerID FOREIGN KEY (LBestellnehmerID) REFERENCES Bestellnehmer(BestellnehmerID),
LBID INTEGER,
CONSTRAINT fkeyLBID FOREIGN KEY (LBID) REFERENCES Bestellung(BID),
PRIMARY KEY (LBestellnehmerID, LBID)
);