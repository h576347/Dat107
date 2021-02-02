DROP SCHEMA IF EXISTS oblig3 CASCADE; 
CREATE SCHEMA oblig3;
SET search_path TO oblig3;

create table avdeling(
	avdID		SERIAL,
	avdNavn		VARCHAR,
	sjefID		INTEGER not null,
	constraint avdelingPK primary key (avdID)
);

CREATE TABLE ansatt(
	ansID		SERIAL,
	brukerNavn	VARCHAR(4) not null,
	fornavn		VARCHAR,
	etternavn	VARCHAR,
	datoAnsatt	DATE,
	stilling	VARCHAR,
	mndsLonn	INTEGER,
	avdeling	INTEGER not null,	
	CONSTRAINT ansattPK PRIMARY KEY (ansID),
	constraint avdeling_FK foreign key (avdeling) references avdeling(avdID)

);

create table prosjekt(
	pID SERIAL,
	pNavn VARCHAR,
	info VARCHAR,
	constraint prosjektPK primary key(pID)
	
);

create table prosjektDeltagelse(
	ansID Integer,
	pID Integer,
	timer float,
	ansattRolle VARCHAR,
	constraint prosjektDeltagelse_PK primary key (ansID,pID),
	constraint ansatt_Fk foreign key (ansID) references ansatt(ansID),
	constraint prosjekt_FK foreign key (pID) references prosjekt(pID),
	constraint prosjektDeltagelse_Unique unique (ansID, pID)
);


insert into
	avdeling(avdNavn, sjefID)
values
	('Kjøkken', 1),
	('Kafeteria', 2),
	('Hage', 8);
	
select*from avdeling;


INSERT INTO
	ansatt(brukerNavn, fornavn, etternavn, datoAnsatt, stilling, mndsLonn,avdeling)
VALUES
	('kar', 'Karoline', 'Mikkelsen', '2019-01-01', 'Kokk', '50000', 1),
	('emi','Emil', 'Svensson', '2018-01-02','Servitør', '45000', 1),
	('ola', 'Olaf', 'Snow', '2017-09-01', 'Sjef', '75000', 1),
	('eli', 'Eli', 'Hagen', '2018-09-01', 'Gartner', '35000',2),
	('han', 'Hans', 'Olai', '2015-08-02', 'Kokk', '50000',2),
	('hol', 'Hege', 'Kai', '2018-03-02', 'Seiler', '45000',2),
	('kai', 'Lege', 'Til', '2017-06-05', 'Kaptein', '60000',3),
	('sol', 'Gunn', 'Hege', '2016-05-04', 'Korolla', '45000',3),
	('ho', 'Gunn', 'Vor', '2018-05-03', 'Frisør', '25000',3),
	('is', 'Lolli', 'Pop', '2019-03-04', 'Kjøkkenvert', '55000',3)
	;

select * from ansatt;


alter table avdeling add constraint sjef_FK foreign key(sjefID) references ansatt(ansID) ;

insert into 
	prosjekt(pNavn,info)
values
	('Hagestell', 'Trimme plenen, gjødsle, vanne planter'),
	('Kjøkkentjeneste', 'Koke mat, steke mat, oppvask, rengjøring av kjøkken')
	
	;

select*from prosjektdeltagelse;
