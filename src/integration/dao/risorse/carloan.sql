create database if not exists carloan_lucarella_arcuri;

use carloan_lucarella_arcuri;

create table if not exists autenticazione(
id int not null auto_increment primary key,
username varchar(20) not null,
password varchar(32) not null,
agenzia varchar(5),
privilegi varchar(10));

create table if not exists cliente(
id int not null auto_increment primary key,
nome varchar(20) not null,
cognome varchar(20) not null,
telefono varchar(10) not null);

create table if not exists agenzia(
codice varchar(5) not null primary key,
nome varchar(25) not null,
indirizzo varchar(25) not null,
citta varchar(25) not null,
CAP varchar(5) not null);

create table if not exists fascia(
id varchar(1) not null primary key,
requisiti varchar(50) not null,
costo double(7,4) not null);

create table if not exists modello(
modello varchar(20) not null,
fascia varchar(1) not null,
primary key(modello,fascia));

create table if not exists macchina(
targa varchar(7) not null primary key,
fascia varchar(2),
modello varchar(20),
agenzia varchar(5),
noleggiata varchar(15),
manutenzione varchar(15),
chilometraggio double(7,4));

create table if not exists tariffa(
id int not null primary key auto_increment,
tipo varchar(30) not null,
chilometri double(7,4) not null,
giorni int not null,
tariffa double(7,4) not null);

create table if not exists contratto(
codice varchar(5) not null primary key,
nome varchar(30) not null,
cognome varchar(30) not null,
telefono varchar(15) not null,
data_inizio DATE not null,
data_fine DATE not null,
acconto double(9,2) not null,
agenzia varchar(5) not null,
agenzia_ritorno varchar(5) not null,
attivo varchar(5) not null
);

create table if not exists noleggio(
codice varchar(5) not null primary key,
base int not null,
chilometraggio varchar(20) not null,
fascia varchar(1) not null,
modello varchar(20) not null,
macchina varchar(10) not null
);

alter table autenticazione add constraint fk_autAgenzia foreign key(agenzia) references agenzia(codice) on delete cascade on update cascade;
alter table modello add constraint fk_fasciaModello foreign key(fascia) references fascia(id) on delete cascade on update cascade;
alter table macchina add constraint fk_macFascia foreign key(fascia) references fascia(id) on delete cascade on update cascade;
alter table macchina add constraint fk_macModello foreign key(modello) references modello(modello) on delete cascade on update cascade;
alter table macchina add constraint fk_macAgenzia foreign key(agenzia) references agenzia(codice) on delete cascade on update cascade;
alter table noleggio add contraint fk_nolMacchina foreign key(macchina) references macchina(targa);


insert into autenticazione(username,password,agenzia,privilegi) values('admin','63a9f0ea7bb98050796b649e85481845',null,'admin');