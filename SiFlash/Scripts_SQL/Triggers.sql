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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'article');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'book');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'booklet');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'conference');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'inbook');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'incollection');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'inproceedings');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'manual');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'mastersthesis');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'misc');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'phdthesis');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'proceedings');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'techreport');
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
    END;
  END IF;
  INSERT INTO tipopublicacion VALUES (NEW.idDoc, 'unpublished');
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


DROP TRIGGER IF EXISTS actualizarJefe $$
CREATE TRIGGER actualizarJefe BEFORE UPDATE ON Proyectos
FOR EACH ROW
BEGIN
  DECLARE tipo VARCHAR(6);
  SELECT usuarios.tipo INTO tipo FROM Usuarios WHERE Usuarios.nombre = NEW.jefe;
  IF (tipo = 'user') THEN
    UPDATE Usuarios SET tipo='jefe' WHERE nombre = NEW.jefe;  
  END IF;
  DELETE FROM participaEn WHERE usuario = NEW.jefe AND proyecto = NEW.nombre;
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
  DELETE FROM participaen WHERE proyecto = OLD.nombre;
END $$


DROP TRIGGER IF EXISTS borraUsuario $$
CREATE TRIGGER borraUsuario BEFORE DELETE ON Usuarios
FOR EACH ROW
BEGIN
  DELETE FROM participaen WHERE usuario= OLD.nombre;
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

DELIMITER ;