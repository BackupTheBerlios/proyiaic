-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ssii
--

CREATE DATABASE IF NOT EXISTS ssii;
USE ssii;

--
-- Definition of table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `journal` varchar(100) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `pages` int(10) unsigned default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article`
--

/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;


--
-- Definition of trigger `insertaArticle`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaArticle`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaArticle` BEFORE INSERT ON `article` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `autores`
--

DROP TABLE IF EXISTS `autores`;
CREATE TABLE `autores` (
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `web` varchar(512) default NULL,
  PRIMARY KEY  (`nombre`,`apellidos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `autores`
--

/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` (`nombre`,`apellidos`,`web`) VALUES 
 ('n2','ap2',NULL),
 ('nombre1','apellidos1',NULL);
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;


--
-- Definition of table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `edition` varchar(10) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`idDoc`,`title`,`publisher`,`year`,`volume`,`number`,`series`,`address`,`edition`,`month`,`note`,`abstract`,`URL`,`user`) VALUES 
 (1,'title','publisher',1,'volume',1,'series','address','edition','month','note','abstractt','url','user'),
 (2,'titulo2','publisher',1,'volume',1,'series','address','edition','month','note','abstractt','url','user'),
 (3,'titulo4','publisher',1,'volume',1,'series','address','edition','month','note','abstractt','url','user'),
 (5,'45641653','156',15,'1351351',31,'321','32132132','132132','132132','132132','132132','1321','user'),
 (6,'4564165','13132132123',3,'165',65413,'3201465465','456123132132','465','1231321321','84654654165','1321321321','6546545646','user'),
 (7,'','',0,'',0,'','','','','','','','user'),
 (8,'','',0,'',0,'','','','','','','','user'),
 (9,'489','23',1,'2',3,'4','5','6','9','8','7','4','user'),
 (10,'','',0,'',0,'','','','','','','','user'),
 (11,'','',0,'',0,'','','','','','','','user'),
 (12,'','',0,'',0,'','','','','','','','user'),
 (13,'','',0,'',0,'','','','','','','','user'),
 (14,'','',0,'',0,'','','','','','','','user'),
 (15,'','',0,'',0,'','','','','','','','user'),
 (16,'','',0,'',0,'','','','','','','','user'),
 (17,'','',0,'',0,'','','','','','','','user'),
 (18,'','',0,'',0,'','','','','','','','user'),
 (19,'','',0,'',0,'','','','','','','','user'),
 (20,'','',0,'',0,'','','','','','','','user'),
 (21,'','',0,'',0,'','','','','','','','user'),
 (22,'','',0,'',0,'','','','','','','','user'),
 (23,'','',0,'',0,'','','','','','','','user'),
 (24,'','',0,'',0,'','','','','','','','user'),
 (25,'','',0,'',0,'','','','','','','','user'),
 (26,'','',0,'',0,'','','','','','','','user'),
 (27,'','',0,'',0,'','','','','','','','user'),
 (28,'','',0,'',0,'','','','','','','','user'),
 (29,'','',0,'',0,'','','','','','','','user'),
 (30,'','',0,'',0,'','','','','','','','user'),
 (31,'','',0,'',0,'','','','','','','','user'),
 (32,'','',0,'',0,'','','','','','','','user'),
 (33,'','',0,'',0,'','','','','','','','user'),
 (34,'','',0,'',0,'','','','','','','','user'),
 (35,'','',0,'',0,'','','','','','','','user'),
 (36,'','',0,'',0,'','','','','','','','user'),
 (37,'','',0,'',0,'','','','','','','','user'),
 (38,'','',0,'',0,'','','','','','','','user'),
 (39,'','',0,'',0,'','','','','','','','user'),
 (40,'','',0,'',0,'','','','','','','','user'),
 (41,'','',0,'',0,'','','','','','','','user'),
 (42,'','',0,'',0,'','','','','','','','user'),
 (43,'','',0,'',0,'','','','','','','','user'),
 (44,'','',0,'',0,'','','','','','','','user'),
 (45,'','',0,'',0,'','','','','','','','user'),
 (46,'456','1',23,'6',8,'41','5','97','465','1','3','8598','user'),
 (47,'489','123',456,'74645',132,'1231','10','1','0','156','dfhuasidhfioasdhfidahfkhdaihsdaiohs ahsai fhdhf fdhaui asdjf safhuishiuashfjkadsha fhsfhsadf sdahfuisdafjkdfhksdahf sdahfjklsaf','5','user'),
 (48,'TItulo456','156',4,'456',132,'146','45','1321','456','4561','321','456','user'),
 (49,'TItuloHOy','4564',456456,'1453456',454,'5646','456','1534','5346','5465','45','45','user');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


--
-- Definition of trigger `insertaBook`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaBook`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaBook` BEFORE INSERT ON `book` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `booklet`
--

DROP TABLE IF EXISTS `booklet`;
CREATE TABLE `booklet` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `howpublished` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `year` int(10) unsigned default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `booklet_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booklet`
--

/*!40000 ALTER TABLE `booklet` DISABLE KEYS */;
/*!40000 ALTER TABLE `booklet` ENABLE KEYS */;


--
-- Definition of trigger `insertaBooklet`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaBooklet`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaBooklet` BEFORE INSERT ON `booklet` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `claves`
--

DROP TABLE IF EXISTS `claves`;
CREATE TABLE `claves` (
  `clave` varchar(20) NOT NULL,
  PRIMARY KEY  (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claves`
--

/*!40000 ALTER TABLE `claves` DISABLE KEYS */;
/*!40000 ALTER TABLE `claves` ENABLE KEYS */;


--
-- Definition of table `conference`
--

DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `booktitle` varchar(100) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `pages` varchar(10) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `organization` varchar(50) default NULL,
  `publisher` varchar(50) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `conference_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conference`
--

/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;


--
-- Definition of trigger `insertaConference`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaConference`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaConference` BEFORE INSERT ON `conference` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `editadopor`
--

DROP TABLE IF EXISTS `editadopor`;
CREATE TABLE `editadopor` (
  `idDoc` int(10) unsigned NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  PRIMARY KEY  (`idDoc`,`nombre`,`apellidos`),
  KEY `nombre` (`nombre`,`apellidos`),
  CONSTRAINT `editadopor_ibfk_1` FOREIGN KEY (`nombre`, `apellidos`) REFERENCES `editores` (`nombre`, `apellidos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `editadopor`
--

/*!40000 ALTER TABLE `editadopor` DISABLE KEYS */;
INSERT INTO `editadopor` (`idDoc`,`nombre`,`apellidos`) VALUES 
 (1,'ne1','ae1'),
 (2,'ne1','ae1'),
 (1,'ne2','aee'),
 (2,'ne2','aee');
/*!40000 ALTER TABLE `editadopor` ENABLE KEYS */;


--
-- Definition of table `editores`
--

DROP TABLE IF EXISTS `editores`;
CREATE TABLE `editores` (
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `web` varchar(512) default NULL,
  PRIMARY KEY  (`nombre`,`apellidos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `editores`
--

/*!40000 ALTER TABLE `editores` DISABLE KEYS */;
INSERT INTO `editores` (`nombre`,`apellidos`,`web`) VALUES 
 ('ne1','ae1',NULL),
 ('ne2','aee',NULL);
/*!40000 ALTER TABLE `editores` ENABLE KEYS */;


--
-- Definition of table `escritopor`
--

DROP TABLE IF EXISTS `escritopor`;
CREATE TABLE `escritopor` (
  `idDoc` int(10) unsigned NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  PRIMARY KEY  (`idDoc`,`nombre`,`apellidos`),
  KEY `nombre` (`nombre`,`apellidos`),
  CONSTRAINT `escritopor_ibfk_1` FOREIGN KEY (`nombre`, `apellidos`) REFERENCES `autores` (`nombre`, `apellidos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `escritopor`
--

/*!40000 ALTER TABLE `escritopor` DISABLE KEYS */;
INSERT INTO `escritopor` (`idDoc`,`nombre`,`apellidos`) VALUES 
 (1,'n2','ap2'),
 (2,'n2','ap2'),
 (5,'n2','ap2'),
 (1,'nombre1','apellidos1'),
 (3,'nombre1','apellidos1');
/*!40000 ALTER TABLE `escritopor` ENABLE KEYS */;


--
-- Definition of table `id`
--

DROP TABLE IF EXISTS `id`;
CREATE TABLE `id` (
  `nextId` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`nextId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `id`
--

/*!40000 ALTER TABLE `id` DISABLE KEYS */;
INSERT INTO `id` (`nextId`) VALUES 
 (50);
/*!40000 ALTER TABLE `id` ENABLE KEYS */;


--
-- Definition of table `inbook`
--

DROP TABLE IF EXISTS `inbook`;
CREATE TABLE `inbook` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `chapter` varchar(20) default NULL,
  `pages` varchar(10) default NULL,
  `publisher` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `type` varchar(20) default NULL,
  `edition` varchar(10) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `inbook_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inbook`
--

/*!40000 ALTER TABLE `inbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `inbook` ENABLE KEYS */;


--
-- Definition of trigger `insertaInbook`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaInbook`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaInbook` BEFORE INSERT ON `inbook` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `incollection`
--

DROP TABLE IF EXISTS `incollection`;
CREATE TABLE `incollection` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `booktitle` varchar(100) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `type` varchar(20) default NULL,
  `chapter` varchar(20) default NULL,
  `pages` varchar(10) default NULL,
  `address` varchar(100) default NULL,
  `edition` varchar(10) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `incollection_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `incollection`
--

/*!40000 ALTER TABLE `incollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `incollection` ENABLE KEYS */;


--
-- Definition of trigger `insertaIncollection`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaIncollection`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaIncollection` BEFORE INSERT ON `incollection` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `inproceedings`
--

DROP TABLE IF EXISTS `inproceedings`;
CREATE TABLE `inproceedings` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `booktitle` varchar(100) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `pages` varchar(10) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `organization` varchar(50) default NULL,
  `publisher` varchar(50) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `inproceedings_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inproceedings`
--

/*!40000 ALTER TABLE `inproceedings` DISABLE KEYS */;
/*!40000 ALTER TABLE `inproceedings` ENABLE KEYS */;


--
-- Definition of trigger `insertaInproceedings`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaInproceedings`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaInproceedings` BEFORE INSERT ON `inproceedings` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `manual`
--

DROP TABLE IF EXISTS `manual`;
CREATE TABLE `manual` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `organization` varchar(50) default NULL,
  `address` varchar(100) default NULL,
  `edition` varchar(10) default NULL,
  `month` varchar(10) default NULL,
  `year` int(10) unsigned default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `manual_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manual`
--

/*!40000 ALTER TABLE `manual` DISABLE KEYS */;
/*!40000 ALTER TABLE `manual` ENABLE KEYS */;


--
-- Definition of trigger `insertaManual`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaManual`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaManual` BEFORE INSERT ON `manual` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `mastersthesis`
--

DROP TABLE IF EXISTS `mastersthesis`;
CREATE TABLE `mastersthesis` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `school` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `type` varchar(20) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `mastersthesis_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mastersthesis`
--

/*!40000 ALTER TABLE `mastersthesis` DISABLE KEYS */;
/*!40000 ALTER TABLE `mastersthesis` ENABLE KEYS */;


--
-- Definition of trigger `insertaMastersthesis`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaMastersthesis`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaMastersthesis` BEFORE INSERT ON `mastersthesis` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `misc`
--

DROP TABLE IF EXISTS `misc`;
CREATE TABLE `misc` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) default NULL,
  `howpublished` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `year` int(10) unsigned default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `misc_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `misc`
--

/*!40000 ALTER TABLE `misc` DISABLE KEYS */;
/*!40000 ALTER TABLE `misc` ENABLE KEYS */;


--
-- Definition of trigger `insertaMisc`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaMisc`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaMisc` BEFORE INSERT ON `misc` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `participaen`
--

DROP TABLE IF EXISTS `participaen`;
CREATE TABLE `participaen` (
  `usuario` varchar(10) NOT NULL,
  `proyecto` varchar(20) NOT NULL,
  PRIMARY KEY  (`usuario`,`proyecto`),
  KEY `proyecto` (`proyecto`),
  CONSTRAINT `participaen_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nombre`),
  CONSTRAINT `participaen_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyectos` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participaen`
--

/*!40000 ALTER TABLE `participaen` DISABLE KEYS */;
/*!40000 ALTER TABLE `participaen` ENABLE KEYS */;


--
-- Definition of table `pertenecea`
--

DROP TABLE IF EXISTS `pertenecea`;
CREATE TABLE `pertenecea` (
  `idDoc` int(10) unsigned NOT NULL,
  `proyecto` varchar(20) NOT NULL,
  PRIMARY KEY  (`idDoc`,`proyecto`),
  KEY `proyecto` (`proyecto`),
  CONSTRAINT `pertenecea_ibfk_1` FOREIGN KEY (`proyecto`) REFERENCES `proyectos` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pertenecea`
--

/*!40000 ALTER TABLE `pertenecea` DISABLE KEYS */;
/*!40000 ALTER TABLE `pertenecea` ENABLE KEYS */;


--
-- Definition of table `phdthesis`
--

DROP TABLE IF EXISTS `phdthesis`;
CREATE TABLE `phdthesis` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `school` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `type` varchar(20) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `phdthesis_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `phdthesis`
--

/*!40000 ALTER TABLE `phdthesis` DISABLE KEYS */;
/*!40000 ALTER TABLE `phdthesis` ENABLE KEYS */;


--
-- Definition of trigger `insertaPhdthesis`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaPhdthesis`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaPhdthesis` BEFORE INSERT ON `phdthesis` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `proceedings`
--

DROP TABLE IF EXISTS `proceedings`;
CREATE TABLE `proceedings` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `volume` varchar(10) default NULL,
  `number` int(10) unsigned default NULL,
  `series` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `organization` varchar(50) default NULL,
  `publisher` varchar(50) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `proceedings_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proceedings`
--

/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
/*!40000 ALTER TABLE `proceedings` ENABLE KEYS */;


--
-- Definition of trigger `insertaProceedings`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaProceedings`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaProceedings` BEFORE INSERT ON `proceedings` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
CREATE TABLE `proyectos` (
  `nombre` varchar(20) NOT NULL,
  `administrador` varchar(10) NOT NULL,
  PRIMARY KEY  (`nombre`),
  KEY `administrador` (`administrador`),
  CONSTRAINT `proyectos_ibfk_1` FOREIGN KEY (`administrador`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proyectos`
--

/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;


--
-- Definition of table `techreport`
--

DROP TABLE IF EXISTS `techreport`;
CREATE TABLE `techreport` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `institution` varchar(50) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `type` varchar(20) default NULL,
  `number` int(10) unsigned default NULL,
  `address` varchar(100) default NULL,
  `month` varchar(10) default NULL,
  `note` text,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `techreport_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `techreport`
--

/*!40000 ALTER TABLE `techreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `techreport` ENABLE KEYS */;


--
-- Definition of trigger `insertaTechreport`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaTechreport`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaTechreport` BEFORE INSERT ON `techreport` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `tienekey`
--

DROP TABLE IF EXISTS `tienekey`;
CREATE TABLE `tienekey` (
  `idDoc` int(10) unsigned NOT NULL,
  `clave` varchar(20) NOT NULL,
  PRIMARY KEY  (`idDoc`,`clave`),
  KEY `clave` (`clave`),
  CONSTRAINT `tienekey_ibfk_1` FOREIGN KEY (`clave`) REFERENCES `claves` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tienekey`
--

/*!40000 ALTER TABLE `tienekey` DISABLE KEYS */;
/*!40000 ALTER TABLE `tienekey` ENABLE KEYS */;


--
-- Definition of table `tipopublication`
--

DROP TABLE IF EXISTS `tipopublication`;
CREATE TABLE `tipopublication` (
  `idDoc` int(10) unsigned NOT NULL,
  `tabla` varchar(45) NOT NULL,
  PRIMARY KEY  (`idDoc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipopublication`
--

/*!40000 ALTER TABLE `tipopublication` DISABLE KEYS */;
INSERT INTO `tipopublication` (`idDoc`,`tabla`) VALUES 
 (5,'book');
/*!40000 ALTER TABLE `tipopublication` ENABLE KEYS */;


--
-- Definition of table `unpublished`
--

DROP TABLE IF EXISTS `unpublished`;
CREATE TABLE `unpublished` (
  `idDoc` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `note` text NOT NULL,
  `month` varchar(10) default NULL,
  `year` int(10) unsigned default NULL,
  `abstract` text,
  `URL` varchar(512) default NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY  (`idDoc`),
  KEY `user` (`user`),
  CONSTRAINT `unpublished_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unpublished`
--

/*!40000 ALTER TABLE `unpublished` DISABLE KEYS */;
/*!40000 ALTER TABLE `unpublished` ENABLE KEYS */;


--
-- Definition of trigger `insertaUnpublished`
--

DROP TRIGGER /*!50030 IF EXISTS */ `insertaUnpublished`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `insertaUnpublished` BEFORE INSERT ON `unpublished` FOR EACH ROW BEGIN
  DECLARE num INTEGER;
  SELECT nextId INTO num FROM Id;
  SET NEW.idDoc = num;
  UPDATE Id SET nextId = nextId + 1;
END $$

DELIMITER ;

--
-- Definition of table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `nombre` varchar(10) NOT NULL,
  `contrasea` varchar(10) NOT NULL,
  `tipo` varchar(20) default NULL,
  PRIMARY KEY  (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`nombre`,`contrasea`,`tipo`) VALUES 
 ('user','123456','prueba');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
