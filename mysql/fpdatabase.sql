CREATE TABLE Kalender(
KalenderID AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Person(
Brukernavn VARCHAR(70) PRIMARY KEY,
Passord VARCHAR(70),
KalenderID INT,
FOREIGN KEY (KalenderID) REFERENCES Kalender(KalenderID)
);

CREATE TABLE Gruppe(
GruppeID INT AUTO_INCREMENT PRIMARY KEY,
Gruppenavn VARCHAR(70)
);

CREATE TABLE Rom(
Romnavn VARCHAR(70) PRIMARY KEY,
Antall INT
);

CREATE TABLE Kalendergruppe(
KalenderID INT, 
GruppeID INT,
PRIMARY KEY(KalenderID, GruppeID),
FOREIGN KEY (KalenderID) REFERENCES Kalender(KalenderID),
FOREIGN KEY (GruppeID) REFERENCES Gruppe(GruppeID)
);

CREATE TABLE Brukergruppe(
Brukernavn VARCHAR(70),
GruppeID INT,
PRIMARY KEY(Brukernavn, GruppeID),
FOREIGN KEY (Brukernavn) REFERENCES Person(Brukernavn),
FOREIGN KEY (GruppeID) REFERENCES Gruppe(GruppeID)
);

CREATE TABLE Subgruppe(
Sub INT,
Super INT,
FOREIGN KEY (Sub) REFERENCES Gruppe(GruppeID),
FOREIGN KEY (Super) REFERENCES Gruppe(GruppeID)
);

CREATE TABLE Avtale(
AvtaleID INT AUTO_INCREMENT PRIMARY KEY,
tilTid DATETIME,
fraTid DATETIME,
Dato DATE,
Tittel VARCHAR(50),
Beskrivelse VARCHAR(255),
Oppdatert TIMESTAMP,
KalenderID INT, 
leder VARCHAR(70),
Romnavn VARCHAR(70),

FOREIGN KEY (KalenderID) REFERENCES Kalender(KalenderID),
FOREIGN KEY (Romnavn) REFERENCES Rom(Romnavn)
);

CREATE TABLE Brukeravtale(
Brukernavn VARCHAR(70),
AvtaleID INT,
statusKommer BOOL,
alarmtid DATETIME,
PRIMARY KEY (Brukernavn, AvtaleID),
FOREIGN KEY (Brukernavn) REFERENCES Person(Brukernavn),
FOREIGN KEY (AvtaleID) REFERENCES Avtale(AvtaleID)
);

CREATE TABLE Varsel(
Brukernavn VARCHAR(70),
AvtaleID INT,
avtaleEndret VARCHAR(30),
kommerIkke VARCHAR(70),
PRIMARY KEY (Brukernavn, AvtaleID),
FOREIGN KEY (AvtaleID) REFERENCES Avtale(AvtaleID),
FOREIGN KEY (Brukernavn) REFERENCES Person(Brukernavn)
FOREIGN KEY (kommerIkke) REFERENCES Person
(Brukernavn)
);