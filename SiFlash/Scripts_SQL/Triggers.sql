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
END $$

DROP PROCEDURE IF EXISTS errorHandler $$
CREATE PROCEDURE errorHandler()
BEGIN
  DECLARE EXIT HANDLER FOR SQLSTATE '23000' SET @err=23000;
  CREATE TABLE errorHandler(i integer primary key);
  insert into errorHandler values(NULL);
END $$


DROP TRIGGER IF EXISTS insertaInbook $$
CREATE TRIGGER insertaInbook BEFORE INSERT ON Inbook
FOR EACH ROW
BEGIN
  DECLARE EXIT HANDLER FOR SQLSTATE '23000' SET @err=23000;
  IF (NEW.idDoc = 0) THEN
    BEGIN
      IF (not((NEW.chapter = null) AND (NEW.pages = null))) THEN
        BEGIN
          DECLARE num INTEGER;
          SELECT nextId INTO num FROM Id;
          SET NEW.idDoc = num;
          UPDATE Id SET nextId = nextId + 1;
        END;
      ELSE
        CALL errorHandler();
      END IF;
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
    END;
  END IF;
END $$


DROP TRIGGER IF EXISTS insertaUsuario $$
CREATE TRIGGER insertaUsuario BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
  SET NEW.password = DES_ENCRYPT(NEW.password);
  IF ((NEW.tipo != 'user') and (NEW.tipo != 'jefe') and (NEW.tipo != 'admin')) THEN
    SET NEW.tipo = 'user';  
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

DELIMITER ;