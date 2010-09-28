-- MySQL dump 10.13  Distrib 5.1.48, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: mycozBranch
-- ------------------------------------------------------
-- Server version	5.1.48

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
-- Table structure for table `AccessLog`
--

DROP TABLE IF EXISTS `AccessLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessLog` (
  `id` int(11) NOT NULL DEFAULT '0',
  `ip` varchar(100) DEFAULT 'NULL',
  `startdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `enddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `ip` (`ip`),
  KEY `startdate` (`startdate`),
  KEY `enddate` (`enddate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AddressBook`
--

DROP TABLE IF EXISTS `AddressBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AddressBook` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL DEFAULT '0',
  `countryId` int(11) NOT NULL DEFAULT '0',
  `languageId` int(11) NOT NULL DEFAULT '0',
  `cityId` int(11) NOT NULL DEFAULT '0',
  `address` varchar(200) DEFAULT NULL,
  `postalCode` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tel` varchar(60) DEFAULT NULL,
  `mobileNo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `countryId` (`countryId`),
  KEY `cityId` (`cityId`),
  KEY `languageId` (`languageId`),
  KEY `address` (`address`),
  KEY `postalCode` (`postalCode`),
  KEY `email` (`email`),
  KEY `tel` (`tel`),
  KEY `mobileNo` (`mobileNo`),
  CONSTRAINT `AddressBook_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`ID`),
  CONSTRAINT `AddressBook_ibfk_2` FOREIGN KEY (`countryId`) REFERENCES `mycozShared`.`Country` (`ID`),
  CONSTRAINT `AddressBook_ibfk_3` FOREIGN KEY (`languageId`) REFERENCES `mycozShared`.`Language` (`ID`),
  CONSTRAINT `AddressBook_ibfk_4` FOREIGN KEY (`cityId`) REFERENCES `mycozShared`.`City` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Blog`
--

DROP TABLE IF EXISTS `Blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Blog` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `UserID` int(11) NOT NULL DEFAULT '0',
  `CategoryID` int(11) NOT NULL DEFAULT '0',
  `Title` varchar(50) DEFAULT 'NULL',
  `Description` text,
  `LastDate` date DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Title` (`Title`),
  KEY `Blog_ibfk_1` (`UserID`),
  KEY `Blog_ibfk_2` (`CategoryID`),
  CONSTRAINT `Blog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`),
  CONSTRAINT `Blog_ibfk_2` FOREIGN KEY (`CategoryID`) REFERENCES `mycozShared`.`BlogCategory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Example`
--

DROP TABLE IF EXISTS `Example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Example` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `age` int(11) NOT NULL DEFAULT '0',
  `school` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `Example_ibfk_1` (`age`),
  KEY `Example_ibfk_2` (`school`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FileInfo`
--

DROP TABLE IF EXISTS `FileInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FileInfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT 'NULL',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filepath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`typeid`),
  KEY `typeid` (`typeid`),
  KEY `name` (`name`),
  KEY `dateTime` (`datetime`),
  KEY `filepath` (`filepath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `JobAccounting`
--

DROP TABLE IF EXISTS `JobAccounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JobAccounting` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `JobNoteID` int(11) NOT NULL DEFAULT '0',
  `ItemName` varchar(100) DEFAULT NULL,
  `ItemRate` decimal(25,4) DEFAULT NULL,
  `ItemUnit` varchar(10) DEFAULT NULL,
  `ItemQuantity` double DEFAULT '1',
  `CurrencyID` int(11) NOT NULL DEFAULT '0',
  `AccountID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ItemName` (`ItemName`,`JobNoteID`,`AccountID`),
  KEY `JobNoteID` (`JobNoteID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `JobAccounting_ibfk_3` (`AccountID`),
  CONSTRAINT `JobAccounting_ibfk_1` FOREIGN KEY (`JobNoteID`) REFERENCES `JobNote` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_2` FOREIGN KEY (`CurrencyID`) REFERENCES `mycozShared`.`Currency` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_3` FOREIGN KEY (`AccountID`) REFERENCES `mycozShared`.`Account` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `JobJournal`
--

DROP TABLE IF EXISTS `JobJournal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JobJournal` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `AccountID` int(11) NOT NULL DEFAULT '0',
  `JobNoteID` int(11) NOT NULL DEFAULT '0',
  `DCType` enum('Credit','Debit') DEFAULT NULL,
  `Amount` decimal(25,4) DEFAULT NULL,
  `OrgCurrencyID` int(11) NOT NULL DEFAULT '0',
  `OrgRate` double DEFAULT NULL,
  `JobAccountingID` int(11) NOT NULL DEFAULT '0',
  `ExchangeRate` double DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `AccountID` (`AccountID`),
  KEY `JobNoteID` (`JobNoteID`),
  KEY `OrgCurrencyID` (`OrgCurrencyID`),
  KEY `JobAccountingID` (`JobAccountingID`),
  CONSTRAINT `JobJournal_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `mycozShared`.`Account` (`ID`),
  CONSTRAINT `JobJournal_ibfk_2` FOREIGN KEY (`JobNoteID`) REFERENCES `JobNote` (`ID`),
  CONSTRAINT `JobJournal_ibfk_3` FOREIGN KEY (`OrgCurrencyID`) REFERENCES `mycozShared`.`Currency` (`ID`),
  CONSTRAINT `JobJournal_ibfk_4` FOREIGN KEY (`JobAccountingID`) REFERENCES `JobAccounting` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `JobNote`
--

DROP TABLE IF EXISTS `JobNote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JobNote` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `NoteNo` varchar(60) DEFAULT 'NULL',
  `NoteTypeID` int(11) NOT NULL DEFAULT '0',
  `OperatorID` int(11) NOT NULL DEFAULT '0',
  `ChargeToID` int(11) NOT NULL DEFAULT '0',
  `IsPosted` enum('Y','N','O','A') DEFAULT NULL,
  `PostDate` date DEFAULT NULL,
  `PrintCount` smallint(6) DEFAULT '0',
  `Date` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  `Description` text,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NoteNo` (`NoteNo`),
  KEY `NoteTypeID` (`NoteTypeID`),
  KEY `OperatorID` (`OperatorID`),
  KEY `ChargeToID` (`ChargeToID`),
  CONSTRAINT `JobNote_ibfk_1` FOREIGN KEY (`NoteTypeID`) REFERENCES `mycozShared`.`NoteType` (`ID`),
  CONSTRAINT `JobNote_ibfk_2` FOREIGN KEY (`OperatorID`) REFERENCES `mycozShared`.`OperatorUser` (`ID`),
  CONSTRAINT `JobNote_ibfk_3` FOREIGN KEY (`ChargeToID`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Message` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `UserID` int(11) NOT NULL DEFAULT '0',
  `BlogID` int(11) NOT NULL DEFAULT '0',
  `MessageDate` date DEFAULT NULL,
  `Title` varchar(50) DEFAULT 'NULL',
  `Description` text,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Title` (`Title`),
  KEY `Message_ibfk_1` (`UserID`),
  KEY `Message_ibfk_2` (`BlogID`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`),
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`BlogID`) REFERENCES `Blog` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Property`
--

DROP TABLE IF EXISTS `Property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Property` (
  `id` int(11) NOT NULL DEFAULT '0',
  `reference` varchar(50) DEFAULT 'NULL',
  `userId` int(11) NOT NULL DEFAULT '0',
  `transaction` varchar(50) DEFAULT 'NULL',
  `propertyStatus` int(11) NOT NULL DEFAULT '0',
  `propertyType` int(11) NOT NULL DEFAULT '0',
  `offer` decimal(25,4) DEFAULT NULL,
  `bedrooms` int(11) NOT NULL DEFAULT '0',
  `createdDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `bathroom` int(11) NOT NULL DEFAULT '0',
  `validTo` varchar(50) DEFAULT 'NULL',
  `swimmingPool` varchar(50) DEFAULT 'NULL',
  `price` decimal(25,4) DEFAULT NULL,
  `areaM2` decimal(25,4) DEFAULT NULL,
  `plotM2` decimal(25,4) DEFAULT NULL,
  `terraceM2` decimal(25,4) DEFAULT NULL,
  `carPark` decimal(25,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `reference` (`reference`),
  KEY `userId` (`userId`),
  KEY `transaction` (`transaction`),
  KEY `propertyStatus` (`propertyStatus`),
  KEY `propertyType` (`propertyType`),
  KEY `offer` (`offer`),
  KEY `bedrooms` (`bedrooms`),
  KEY `createdDate` (`createdDate`),
  KEY `bathroom` (`bathroom`),
  KEY `swimmingPool` (`swimmingPool`),
  KEY `price` (`price`),
  KEY `areaM2` (`areaM2`),
  KEY `plotM2` (`plotM2`),
  KEY `terraceM2` (`terraceM2`),
  KEY `carPark` (`carPark`),
  CONSTRAINT `Property_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`ID`),
  CONSTRAINT `Property_ibfk_2` FOREIGN KEY (`propertyStatus`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `Property_ibfk_3` FOREIGN KEY (`propertyType`) REFERENCES `mycozShared`.`LinearCode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TreeExample`
--

DROP TABLE IF EXISTS `TreeExample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TreeExample` (
  `id` int(11) NOT NULL DEFAULT '0',
  `treeId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `age` int(11) NOT NULL DEFAULT '0',
  `school` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `Example_ibfk_1` (`age`),
  KEY `Example_ibfk_2` (`school`),
  KEY `Example_ibfk_3` (`treeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `StateID` int(11) NOT NULL DEFAULT '0',
  `Name` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `StateID` (`StateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'mycozBranch'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-09-28 13:17:18
