CREATE DATABASE  IF NOT EXISTS `banking-service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banking-service`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: banking-service
-- ------------------------------------------------------
-- Server version	5.5.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client_table`
--

DROP TABLE IF EXISTS `client_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_table` (
  `SSN` varchar(13) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `identity_card_number` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_table`
--

LOCK TABLES `client_table` WRITE;
/*!40000 ALTER TABLE `client_table` DISABLE KEYS */;
INSERT INTO `client_table` VALUES ('1670616120684','Radu','Popescu','KX808580','Cluj-Napoca','popescu@yahoo.com'),('1670616120685','Radu2','Popescu2','1670616120685','Cluj-Napoca2','popescu2@yahoo.com'),('1950608125780','Cipri','Ionescu','12345678','Strada Libertatii','ionescu@yahoo.com'),('1990111180056','Titi','Vasilescu','string','Observatorului, Cluj-Napoca','titi@yahoo.com');
/*!40000 ALTER TABLE `client_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_table`
--

DROP TABLE IF EXISTS `account_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_table` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_ssn` varchar(45) DEFAULT NULL,
  `account_type` varchar(45) NOT NULL,
  `date_of_creation` varchar(45) NOT NULL,
  `balance` float NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `client_FK_idx` (`client_ssn`),
  CONSTRAINT `client_FK` FOREIGN KEY (`client_ssn`) REFERENCES `client_table` (`SSN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_table`
--

LOCK TABLES `account_table` WRITE;
/*!40000 ALTER TABLE `account_table` DISABLE KEYS */;
INSERT INTO `account_table` VALUES (2,'1990111180056','SAVINGS','2019-03-20 16:18:25.861',98000),(3,'1990111180056','CREDIT','2019-03-20 16:18:25.861',52500),(5,'1950608125780','CREDIT','2019-03-20 16:18:25.861',1500),(6,'1950608125780','SAVINGS','2019-03-29 15:15:36.307',1000000),(7,'1670616120685','EXPENSES','2019-03-29 21:35:36.817',1500),(8,'1670616120685','SAVINGS','2017-03-30 18:05:03.853',12345),(9,'1670616120684','CREDIT','2019-03-20 16:17:15.278',1234),(10,'1670616120684','SAVINGS','2019-03-20 16:18:22.959',1234),(11,'1670616120684','CREDIT','2019-03-20 16:18:25.119',1234),(12,'1990111180056','SAVINGS','2019-03-20 16:18:25.861',1234),(14,'1990111180056','SAVINGS','2019-03-20 17:42:04.452',0);
/*!40000 ALTER TABLE `account_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-20 19:08:55
