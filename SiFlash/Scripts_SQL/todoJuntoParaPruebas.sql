drop table Id;
drop table participaEn;
drop table perteneceA;
drop table tieneKey;
drop table Claves;
drop table Proyectos;
drop table Article;
drop table Book;
drop table Booklet;
drop table Conference;
drop table Inbook;
drop table Incollection;
drop table Inproceedings;
drop table Manual;
drop table Mastersthesis;
drop table Misc;
drop table Phdthesis;
drop table Proceedings;
drop table Techreport;
drop table Unpublished;
drop table TipoPublicacion;
drop table escrito_editado_por;
drop table Usuarios;
drop table AutoresEditores;





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
	title varchar(100) not null,
	journal varchar(100) not null,
	year varchar(50) not null,
	volume varchar(50),
	number varchar(50),
	pages varchar(50),
	month varchar(50),
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
	year varchar(50) not null,
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	address varchar(100),
	edition varchar(50),
	month varchar(50),
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
	month varchar(50),
	year varchar(50),
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
	year varchar(50) not null,
	crossref varchar(100),
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	pages varchar(50),
	address varchar(100),
	month varchar(50),
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
	chapter varchar(50),
	pages varchar(50),
	publisher varchar(50) not null,
	year varchar(50) not null,
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	type varchar(50),
	edition varchar(50),
	address varchar(100),
	month varchar(50),
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
	year varchar(50) not null,
	crossref varchar(100),
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	type varchar(50),
	chapter varchar(50),
	pages varchar(50),
	address varchar(100),
	edition varchar(50),
	month varchar(50),
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
	year varchar(50) not null,
	crossref varchar(100),
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	pages varchar(50),
	address varchar(100),
	month varchar(50),
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
	edition varchar(50),
	month varchar(50),
	year varchar(50),
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
	year varchar(50) not null,
	type varchar(50),
	address varchar(100),
	month varchar(50),
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
	month varchar(50),
	year varchar(50),
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
	year varchar(50) not null,
	type varchar(50),
	address varchar(100),
	month varchar(50),
	note text,
	abstract text,
	URL varchar(512),
	user varchar(20) not null,
	referencia varchar(50),
	foreign key(user) references Usuarios(nombre));


create table Proceedings (
	idDoc integer unsigned primary key, 
	title varchar(100) not null,
	year varchar(50) not null,
	booktitle varchar(50),
	volume varchar(50),
	number varchar(50),
	series varchar(100),
	address varchar(100),
	month varchar(50),
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
	year varchar(50) not null,
	type varchar(50),
	number varchar(50),
	address varchar(100),
	month varchar(50),
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
	month varchar(50),
	year varchar(50),
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




INSERT INTO Id VALUES(1,1);

DELIMITER $$

DROP TRIGGER IF EXISTS insertaArticle $$
CREATE TRIGGER insertaArticle BEFORE INSERT ON Article
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'article');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaBook $$
CREATE TRIGGER insertaBook BEFORE INSERT ON Book
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'book');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaBooklet $$
CREATE TRIGGER insertaBooklet BEFORE INSERT ON Booklet
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'booklet');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaConference $$
CREATE TRIGGER insertaConference BEFORE INSERT ON Conference
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'conference');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaInbook $$
CREATE TRIGGER insertaInbook BEFORE INSERT ON Inbook
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'inbook');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaIncollection $$
CREATE TRIGGER insertaIncollection BEFORE INSERT ON Incollection
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'incollection');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaInproceedings $$
CREATE TRIGGER insertaInproceedings BEFORE INSERT ON Inproceedings
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'inproceedings');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaManual $$
CREATE TRIGGER insertaManual BEFORE INSERT ON Manual
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'manual');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaMastersthesis $$
CREATE TRIGGER insertaMastersthesis BEFORE INSERT ON Mastersthesis
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'mastersthesis');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaMisc $$
CREATE TRIGGER insertaMisc BEFORE INSERT ON Misc
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'misc');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaPhdthesis $$
CREATE TRIGGER insertaPhdthesis BEFORE INSERT ON Phdthesis
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'phdthesis');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaProceedings $$
CREATE TRIGGER insertaProceedings BEFORE INSERT ON Proceedings
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;

      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'proceedings');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaTechreport $$
CREATE TRIGGER insertaTechreport BEFORE INSERT ON Techreport
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'techreport');
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaUnpublished $$
CREATE TRIGGER insertaUnpublished BEFORE INSERT ON Unpublished
FOR EACH ROW
BEGIN
  IF (NEW.idDoc = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextId INTO num FROM Id;
      SET NEW.idDoc = num;
      UPDATE Id SET nextId = nextId + 1;
      INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'unpublished');
    END;
  END IF;
END $$

DROP TRIGGER IF EXISTS insertaAutorEditor $$
CREATE TRIGGER insertaAutorEditor BEFORE INSERT ON AutoresEditores
FOR EACH ROW
BEGIN
  IF (NEW.idAut = 0) THEN
    BEGIN
      DECLARE num INTEGER;
      SELECT nextIdAut INTO num FROM Id;
      SET NEW.idAut = num;
      UPDATE Id SET nextIdAut = nextIdAut + 1;
    END;
  END IF;
END $$

DROP TRIGGER IF EXISTS insertaUsuario $$
CREATE TRIGGER insertaUsuario BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
  IF ((NEW.tipo != 'user') and (NEW.tipo != 'jefe') and (NEW.tipo != 'admin')) THEN
    SET NEW.tipo = 'user';  
  END IF;
END $$


DROP TRIGGER IF EXISTS cambiarUserAJefe $$
CREATE TRIGGER cambiarUserAJefe BEFORE INSERT ON Proyectos
FOR EACH ROW
BEGIN
  DECLARE tipo VARCHAR(6);
  SELECT usuarios.tipo INTO tipo FROM Usuarios WHERE Usuarios.nombre = NEW.jefe;
  IF (tipo = 'user') THEN
    UPDATE Usuarios SET tipo='jefe' WHERE nombre = NEW.jefe;  
  END IF;
END $$


DROP TRIGGER IF EXISTS cambiarJefeAUser $$
CREATE TRIGGER cambiarJefeAUser BEFORE DELETE ON Proyectos
FOR EACH ROW
BEGIN
  DECLARE tipo VARCHAR(6);
  SELECT usuarios.tipo INTO tipo FROM Usuarios WHERE Usuarios.nombre = OLD.jefe;
  IF ((tipo = 'jefe') and (NOT EXISTS (SELECT * FROM Proyectos WHERE nombre != OLD.nombre and jefe = OLD.jefe))) THEN
    UPDATE Usuarios SET tipo='user' WHERE nombre = OLD.jefe;
  END IF;
  DELETE FROM pertenecea WHERE proyecto = OLD.nombre;
END $$


DROP TRIGGER IF EXISTS borraArticle $$
CREATE TRIGGER borraArticle BEFORE DELETE ON Article
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraBook $$
CREATE TRIGGER borraBook BEFORE DELETE ON Book
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraBooklet $$
CREATE TRIGGER borraBooklet BEFORE DELETE ON Booklet
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraConference $$
CREATE TRIGGER borraConference BEFORE DELETE ON Conference
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraInbook $$
CREATE TRIGGER borraInbook BEFORE DELETE ON Inbook
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraIncollection $$
CREATE TRIGGER borraIncollection BEFORE DELETE ON Incollection
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraInproceedings $$
CREATE TRIGGER borraInproceedings BEFORE DELETE ON Inproceedings
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraManual $$
CREATE TRIGGER borraManual BEFORE DELETE ON Manual
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$

DROP TRIGGER IF EXISTS borraMastersthesis $$
CREATE TRIGGER borraMastersthesis BEFORE DELETE ON Mastersthesis
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraMisc $$
CREATE TRIGGER borraMisc BEFORE DELETE ON Misc
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraPhdthesis $$
CREATE TRIGGER borraPhdthesis BEFORE DELETE ON Phdthesis
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraProceedings $$
CREATE TRIGGER borraProceedings BEFORE DELETE ON Proceedings
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraTechreport $$
CREATE TRIGGER borraTechreport BEFORE DELETE ON Techreport
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DROP TRIGGER IF EXISTS borraUnpublished $$
CREATE TRIGGER borraUnpublished BEFORE DELETE ON Unpublished
FOR EACH ROW
BEGIN
  DELETE FROM tienekey WHERE idDoc = OLD.idDoc;
  DELETE FROM pertenecea WHERE idDoc = OLD.idDoc;
  DELETE FROM escrito_editado_por WHERE idDoc = OLD.idDoc;
  DELETE FROM tipopublicacion WHERE idDoc = OLD.idDoc;
END $$


DELIMITER ;


INSERT INTO usuarios VALUES('user1', 'user1', 'user');
INSERT INTO proyectos VALUES('Proyecto1', 'user1');