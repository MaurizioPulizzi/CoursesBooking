CREATE DATABASE  IF NOT EXISTS `evolutiondb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `evolutiondb`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: evolutiondb
-- ------------------------------------------------------
-- Server version	5.7.32-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `idcourse` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `max_people_number` int(11) DEFAULT NULL,
  `remaining_people_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcourse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,'ThaiBoxe','2021-05-02','18:00:00','19:00:00',16,16),(3,'Spinning','2021-04-02','18:00:00','19:00:00',10,10),(4,'Spinning','2021-04-03','18:00:00','19:00:00',10,10),(5,'Spinning','2021-04-04','18:00:00','19:00:00',10,10),(8,'Spinning','2021-04-07','18:00:00','19:00:00',10,9),(9,'Spinning','2021-04-08','18:00:00','19:00:00',10,10),(10,'Spinning','2021-04-09','18:00:00','19:00:00',10,9),(11,'Spinning','2021-04-10','18:00:00','19:00:00',10,10),(12,'Spinning','2021-04-11','18:00:00','19:00:00',10,10),(13,'Spinning','2021-04-12','18:00:00','19:00:00',10,10),(14,'Spinning','2021-04-13','18:00:00','19:00:00',10,10),(15,'Spinning','2021-04-14','18:00:00','19:00:00',10,10),(16,'Spinning','2021-04-15','18:00:00','19:00:00',10,10),(17,'Spinning','2021-04-16','18:00:00','19:00:00',10,10),(18,'Spinning','2021-04-17','18:00:00','19:00:00',10,10),(19,'Spinning','2021-04-18','18:00:00','19:00:00',10,10),(20,'Spinning','2021-04-19','18:00:00','19:00:00',10,10),(21,'Spinning','2021-04-20','18:00:00','19:00:00',10,10),(22,'Spinning','2021-04-21','18:00:00','19:00:00',10,10),(23,'Spinning','2021-04-22','18:00:00','19:00:00',10,10),(24,'Spinning','2021-04-23','18:00:00','19:00:00',10,10),(25,'Spinning','2021-04-24','18:00:00','19:00:00',10,10),(26,'Spinning','2021-04-25','18:00:00','19:00:00',10,10),(27,'Spinning','2021-04-26','18:00:00','19:00:00',10,10),(28,'Spinning','2021-04-27','18:00:00','19:00:00',10,10),(29,'Spinning','2021-04-28','18:00:00','19:00:00',10,10),(30,'Spinning','2021-04-29','18:00:00','19:00:00',10,10),(31,'Spinning','2021-04-30','18:00:00','19:00:00',10,10),(32,'Spinning','2021-04-02','18:00:00','19:00:00',10,10),(33,'Spinning','2021-04-03','18:00:00','19:00:00',10,10),(34,'Spinning','2021-04-04','18:00:00','19:00:00',10,10),(35,'Spinning','2021-04-05','18:00:00','19:00:00',10,10),(36,'GAG','2021-04-06','22:00:00','23:00:00',1,0),(37,'Spinning','2021-04-06','19:00:00','20:00:00',16,14),(38,'Boxe','2021-04-06','10:00:00','11:00:00',6,6),(39,'TRX','2021-04-06','11:00:00','12:00:00',10,9),(40,'Postural gym','2021-04-06','12:00:00','13:00:00',20,20),(41,'Core Training','2021-04-06','14:00:00','15:00:00',19,19),(42,'Danza Caraibica','2021-04-06','15:00:00','16:00:00',12,12);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `idmanager` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmanager`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (10000,'evfit','0000');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `idreservation` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) DEFAULT NULL,
  `idcourse` int(11) DEFAULT NULL,
  PRIMARY KEY (`idreservation`),
  KEY `iduser_idx` (`iduser`),
  KEY `idcourse_idx` (`idcourse`),
  CONSTRAINT `idcourse` FOREIGN KEY (`idcourse`) REFERENCES `course` (`idcourse`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `iduser` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (207,1,NULL),(224,1,NULL),(234,1,10),(237,1,36),(238,1,37),(239,2,37),(240,2,39),(241,1,8);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mauri','0000'),(2,'mario','0000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-07  0:43:05
