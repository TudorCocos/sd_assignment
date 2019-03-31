-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema banking-service-test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema banking-service-test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `banking-service-test` DEFAULT CHARACTER SET utf8 ;
USE `banking-service-test` ;

-- -----------------------------------------------------
-- Table `banking-service-test`.`client_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banking-service-test`.`client_table` (
  `SSN` VARCHAR(13) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `identity_card_number` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SSN`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `client_table` WRITE;
/*!40000 ALTER TABLE `client_table` DISABLE KEYS */;
INSERT INTO `client_table` 
VALUES 	('1670616120684','Radu','Popescu','KX808580','Cluj-Napoca','popescu@yahoo.com'),
		('1670616120685','Radu2','Popescu2','CJ123456','Cluj-Napoca2','popescu2@yahoo.com'),
		('1950608125780','Cipri','Ionescu','CX999999','Strada Libertatii','ionescu@yahoo.com'),
		('1990111180056','Titi','Vasilescu','CJ654321','Observatorului, Cluj-Napoca','titi@yahoo.com'),
		('1880807123456','Marcel','Ionescu','BH123456','Oradea','marcel@mail.com');
/*!40000 ALTER TABLE `client_table` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `banking-service-test`.`account_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banking-service-test`.`account_table` (
  `account_id` INT(11) NOT NULL AUTO_INCREMENT,
  `client_ssn` VARCHAR(13) NULL DEFAULT NULL,
  `account_type` VARCHAR(45) NOT NULL,
  `date_of_creation` VARCHAR(45) NOT NULL,
  `balance` FLOAT NOT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `client_FK_idx` (`client_ssn` ASC),
  CONSTRAINT `client_FK`
    FOREIGN KEY (`client_ssn`)
    REFERENCES `banking-service-test`.`client_table` (`SSN`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `account_table` WRITE;
/*!40000 ALTER TABLE `account_table` DISABLE KEYS */;
INSERT INTO `account_table` 
VALUES 	(2,'1990111180056','SAVINGS','2019-03-20 16:18:25.861',98000),
		(3,'1990111180056','CREDIT','2019-03-20 16:18:25.861',52500),
		(5,'1950608125780','CREDIT','2019-03-20 16:18:25.861',1500),
		(6,'1950608125780','SAVINGS','2019-03-29 15:15:36.307',1000000),
		(7,'1670616120685','EXPENSES','2019-03-29 21:35:36.817',1500),
		(8,'1670616120685','SAVINGS','2017-03-30 18:05:03.853',12345),
		(9,'1670616120684','CREDIT','2019-03-20 16:17:15.278',1234),
		(10,'1670616120684','SAVINGS','2019-03-20 16:18:22.959',1234),
		(11,'1670616120684','CREDIT','2019-03-20 16:18:25.119',1234),
		(12,'1990111180056','SAVINGS','2019-03-20 16:18:25.861',1234),
		(14,'1990111180056','SAVINGS','2019-03-20 17:42:04.452',0);
/*!40000 ALTER TABLE `account_table` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `banking-service-test`.`employee_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banking-service-test`.`employee_table` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `employee_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `employee_table` WRITE;
/*!40000 ALTER TABLE `employee_table` DISABLE KEYS */;
INSERT INTO `employee_table` 
VALUES 	(2,'admin1','$2a$10$6xWiWasKfpqNmEeYDmXo5u9feJbZmxPM2rEAh3MNxp/s2/acoqUIy','Maria','Tanase','ADMIN'),
		(3,'ionica','$2a$10$ctYBnJUbqE8j.Yjg5.6HkuRbKKO34iIjpm2bXuu6/CTi/m6KkKObm','Ion','Popescu','EMPLOYEE');
/*!40000 ALTER TABLE `employee_table` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `banking-service-test`.`activity_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banking-service-test`.`activity_table` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_employee` INT(11) NULL DEFAULT NULL,
  `activity` VARCHAR(200) NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `id_idx` (`id_employee` ASC),
  CONSTRAINT `id_employee`
    FOREIGN KEY (`id_employee`)
    REFERENCES `banking-service-test`.`employee_table` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `activity_table` WRITE;
/*!40000 ALTER TABLE `activity_table` DISABLE KEYS */;
INSERT INTO `activity_table` 
VALUES 	(1,3,'getAllClients','2019-03-30 23:22:12.755'),
		(2,3,'createdClient with ssn: 1880807123456','2019-03-30 23:24:32.06'),
		(3,3,'createdClient with ssn: 1880807123456','2019-03-30 23:24:52.665'),
		(4,3,'getAllClients','2019-03-31 01:29:47.44'),
		(5,2,'getAllEmployees','2019-03-31 01:30:38.404'),
		(6,3,'payedBill from: 3 the sum: 1000.0, details: electrica','2019-03-31 11:50:34.788'),
		(7,3,'transferedMoney from: 3 to: 5 the sum: 1500.0','2019-03-31 11:51:37.569');
/*!40000 ALTER TABLE `activity_table` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `banking-service-test`.`transaction_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banking-service-test`.`transaction_table` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `acc_from` INT(11) NULL DEFAULT NULL,
  `acc_to` INT(11) NULL DEFAULT NULL,
  `bill` VARCHAR(45) NULL DEFAULT NULL,
  `amount` FLOAT NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `transaction_table` WRITE;
/*!40000 ALTER TABLE `transaction_table` DISABLE KEYS */;
INSERT INTO `transaction_table` 
VALUES 	(3,3,-1,'electrica',1000,'2019-03-31 11:50:34.301'),
		(4,3,5,null,1500,'2019-03-31 11:51:37.539');
/*!40000 ALTER TABLE `transaction_table` ENABLE KEYS */;
UNLOCK TABLES;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
