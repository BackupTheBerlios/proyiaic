create table Usuarios (
	nombre varchar(20) primary key,
	password varchar(10) not null,
	tipo varchar(6));

create table AutoresEditores (
	idAut INTEGER UNSIGNED NOT NULL AUTO_INCREMENT primary key,
	nombre varchar(20),
	apellidos varchar(50),
	web varchar(512));


create table escrito_editado_por (
	idDoc integer unsigned not null,
	idPer integer unsigned not null,
	escrito_o_editado boolean not null default true,
	primary key (idDoc, idPer, escrito_o_editado),
	foreign key (idPer) references AutoresEditores(idAut));

create table Article (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	journal varchar(100) not null,
	year varchar(20) not null,
	volume varchar(20),
	number varchar(20),
	pages varchar(20),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));

create table Book (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	publisher varchar(50) not null,
	year varchar(20) not null,
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	address varchar(100),
	edition varchar(20),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));

create table Booklet (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	howpublished varchar(100),
	address varchar(100),
	month varchar(20),
	year varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Conference (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	booktitle varchar(100) not null,
	year varchar(20) not null,
	crossref varchar(100),
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	pages varchar(20),
	address varchar(100),
	month varchar(20),
	organization varchar(50),
	publisher varchar(50),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Inbook (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	chapter varchar(20),
	pages varchar(20),
	publisher varchar(50) not null,
	year varchar(20) not null,
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	type varchar(20),
	edition varchar(20),
	address varchar(100),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Incollection (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	booktitle varchar(100) not null,
	publisher varchar(50) not null,
	year varchar(20) not null,
	crossref varchar(100),
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	type varchar(20),
	chapter varchar(20),
	pages varchar(20),
	address varchar(100),
	edition varchar(20),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Inproceedings (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	booktitle varchar(100) not null,
	year varchar(20) not null,
	crossref varchar(100),
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	pages varchar(20),
	address varchar(100),
	month varchar(20),
	organization varchar(50),
	publisher varchar(50),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Manual (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	organization varchar(50),
	address varchar(100),
	edition varchar(20),
	month varchar(20),
	year varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));	


create table Mastersthesis (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	school varchar(50) not null,
	year varchar(20) not null,
	type varchar(20),
	address varchar(100),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Misc (
	idDoc integer unsigned primary key, 
	title varchar(100),
	howpublished varchar(100),
	month varchar(20),
	year varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Phdthesis (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	school varchar(50) not null,
	year varchar(20) not null,
	type varchar(20),
	address varchar(100),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Proceedings (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	year varchar(20) not null,
	booktitle varchar(20),
	volume varchar(20),
	number varchar(20),
	series varchar(100),
	address varchar(100),
	month varchar(20),
	organization varchar(50),
	publisher varchar(50),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Techreport (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	institution varchar(50) not null,
	year varchar(20) not null,
	type varchar(20),
	number integer unsigned,
	address varchar(100),
	month varchar(20),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Unpublished (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	note text not null,
	month varchar(20),
	year varchar(20),
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));	


create table Claves (
	clave varchar(50) primary key);


create table tieneKey (
	idDoc integer unsigned not null,
	clave varchar(50) not null,
	primary key(idDoc, clave),
	foreign key (clave) references Claves(clave));


create table Proyectos (
	nombre varchar(100) primary key,
	jefe varchar(20) not null,
	foreign key (jefe) references Usuarios(nombre));


create table perteneceA (
	idDoc integer unsigned not null,
	proyecto varchar(100) not null,
	primary key(idDoc, proyecto),
	foreign key (proyecto) references Proyectos(nombre));


create table participaEn (
	usuario varchar(20) not null,
	proyecto varchar(100) not null,
	primary key(usuario, proyecto),
	foreign key (usuario) references Usuarios(nombre),
	foreign key (proyecto) references Proyectos(nombre));


create table Id (
	nextId integer unsigned primary key);
	
create table tipoPublicacion (
	idDoc integer unsigned not null primary key,
	tipo varchar(30) not null);