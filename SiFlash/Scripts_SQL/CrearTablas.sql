create table Usuarios (
	nombre varchar(20) primary key,
	password varchar(10) not null,
	tipo varchar(6));

create table AutoresEditores (
	idAut INTEGER UNSIGNED NOT NULL AUTO_INCREMENT primary key,
	nombre varchar(20),
	apellidos varchar(50));


create table escrito_editado_por (
	idDoc integer unsigned not null,
	idPer integer unsigned not null,
	escrito_o_editado boolean not null default true,
	primary key (idDoc, idPer, escrito_o_editado),
	foreign key (idPer) references AutoresEditores(idAut));

create table Article (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	journal varchar(150) not null,
	year varchar(150) not null,
	volume varchar(150), 
	number varchar(150), 
	pages varchar(150), 
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));

create table Book (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	publisher varchar(150) not null,
	year varchar(150) not null,
	volume varchar(150), 
	number varchar(150), 
	series varchar(150), 
	address varchar(150),
	edition varchar(150), 
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));

create table Booklet (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	howpublished varchar(150),
	address varchar(150),
	month varchar(150), 
	year varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Conference (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	booktitle varchar(150) not null,
	year varchar(150) not null,
	crossref varchar(150),
	volume varchar(150), 
	number varchar(150), 
	series varchar(150),
	pages varchar(150), 
	address varchar(150),
	month varchar(150), 
	organization varchar(150), 
	publisher varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Inbook (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	chapter varchar(150), 
	pages varchar(150), 
	publisher varchar(150) not null,
	year varchar(150) not null,
	volume varchar(150), 
	number varchar(150), 
	series varchar(150),
	type varchar(150), 
	edition varchar(150), 
	address varchar(150),
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Incollection (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	booktitle varchar(150) not null,
	publisher varchar(150) not null,
	year varchar(150) not null,
	crossref varchar(150),
	volume varchar(150), 
	number varchar(150), 
	series varchar(150),
	type varchar(150), 
	chapter varchar(150), 
	pages varchar(150), 
	address varchar(150),
	edition varchar(150), 
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Inproceedings (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	booktitle varchar(150) not null,
	year varchar(150) not null,
	crossref varchar(150),
	volume varchar(150), 
	number varchar(150), 
	series varchar(150),
	pages varchar(150), 
	address varchar(150),
	month varchar(150), 
	organization varchar(150), 
	publisher varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Manual (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	organization varchar(150), 
	address varchar(150),
	edition varchar(150), 
	month varchar(150), 
	year varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));	


create table Mastersthesis (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	school varchar(150) not null,
	year varchar(150) not null,
	type varchar(150), 
	address varchar(150),
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Misc (
	idDoc integer unsigned primary key, 
	title varchar(150),
	howpublished varchar(150),
	month varchar(150), 
	year varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Phdthesis (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	school varchar(150) not null,
	year varchar(150) not null,
	type varchar(150), 
	address varchar(150),
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Proceedings (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	year varchar(150) not null,
	booktitle varchar(150), 
	volume varchar(150), 
	number varchar(150), 
	series varchar(150),
	address varchar(150),
	month varchar(150), 
	organization varchar(150), 
	publisher varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Techreport (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	institution varchar(150) not null,
	year varchar(150) not null,
	type varchar(150), 
	number varchar(150), 
	address varchar(150),
	month varchar(150), 
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Unpublished (
	idDoc integer unsigned primary key, 
	title varchar(150) not null,
	note text not null,
	month varchar(150), 
	year varchar(150), 
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	doi varchar(50),
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
	nextId integer unsigned primary key,
	nextIdAut integer unsigned not null);
	
create table tipoPublicacion (
	idDoc integer unsigned not null primary key,
	tipo varchar(30) not null);


create or replace view proyectosAccesiblesJefe(jefe, proyecto) as
  select jefe, nombre from proyectos
  union
  select participaen.usuario, participaen.proyecto from participaen, usuarios
    where usuarios.tipo = 'jefe' and usuarios.nombre = participaen.usuario;