-- MySQL dump 10.13  Distrib 5.1.49, for unknown-linux-gnu (x86_64)
--
-- Host: localhost    Database: mycozBranch
-- ------------------------------------------------------
-- Server version	5.1.49-log

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

CREATE TABLE `FileTree` (
  `id` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) NOT NULL DEFAULT '0',
  `childId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_id` (`parentId`,`childId`),
  KEY `parentId` (`parentId`),
  KEY `childId` (`childId`),
  CONSTRAINT `FileTree_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `FileInfo` (`id`),
  CONSTRAINT `FileTree_ibfk_2` FOREIGN KEY (`childId`) REFERENCES `FileInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Forum`
--

DROP TABLE IF EXISTS `Forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Forum` (
  `id` int(11) NOT NULL DEFAULT '0',
  `categoryId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `modifiedDate` date DEFAULT NULL,
  `creationDate` date DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `categoryId` (`categoryId`),
  KEY `modifiedDate` (`modifiedDate`),
  KEY `creationDate` (`creationDate`),
  CONSTRAINT `Forum_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `mycozShared`.`LinearCode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ForumThread`
--

DROP TABLE IF EXISTS `ForumThread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ForumThread` (
  `id` int(11) NOT NULL DEFAULT '0',
  `forumId` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL DEFAULT '0',
  `subject` varchar(255) DEFAULT 'NULL',
  `body` text,
  `modifiedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creationDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `approved` char(1) NOT NULL DEFAULT 'N',
  `ranking` int(11) DEFAULT '0',
  `closed` char(1) NOT NULL DEFAULT 'N',
  `replyPrivateUserId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `forumId` (`forumId`),
  KEY `userId` (`userId`),
  KEY `subject` (`subject`),
  KEY `modifiedDate` (`modifiedDate`),
  KEY `creationDate` (`creationDate`),
  KEY `ranking` (`ranking`),
  KEY `replyPrivateUserId` (`replyPrivateUserId`),
  CONSTRAINT `ForumThread_ibfk_1` FOREIGN KEY (`forumId`) REFERENCES `Forum` (`id`),
  CONSTRAINT `ForumThread_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `ForumThread_ibfk_3` FOREIGN KEY (`replyPrivateUserId`) REFERENCES `User` (`id`)
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
  `id` int(11) NOT NULL DEFAULT '0',
  `threadId` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL DEFAULT '0',
  `subject` varchar(255) DEFAULT 'NULL',
  `body` text,
  `modifiedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creationDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `approved` char(1) NOT NULL DEFAULT 'N',
  `ranking` int(11) DEFAULT '0',
  `replyPrivateUserId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `threadId` (`threadId`),
  KEY `userId` (`userId`),
  KEY `subject` (`subject`),
  KEY `modifiedDate` (`modifiedDate`),
  KEY `creationDate` (`creationDate`),
  KEY `ranking` (`ranking`),
  KEY `replyPrivateUserId` (`replyPrivateUserId`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`threadId`) REFERENCES `ForumThread` (`id`),
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `Message_ibfk_3` FOREIGN KEY (`replyPrivateUserId`) REFERENCES `User` (`id`)
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
  KEY `TreeExample_ibfk_1` (`age`),
  KEY `TreeExample_ibfk_2` (`school`),
  KEY `TreeExample_ibfk_3` (`treeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `alias` varchar(50) DEFAULT NULL,
  `active` char(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserInfo`
--

DROP TABLE IF EXISTS `UserInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  `sexId` int(11) DEFAULT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `heightUnitId` int(11) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `weightUnitId` int(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `careerId` int(11) DEFAULT NULL,
  `educationId` int(11) DEFAULT NULL,
  `marriedId` int(11) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `secret` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `sexId` (`sexId`),
  KEY `height` (`height`),
  KEY `heightUnitId` (`heightUnitId`),
  KEY `weight` (`weight`),
  KEY `weightUnitId` (`weightUnitId`),
  KEY `birthday` (`birthday`),
  KEY `careerId` (`careerId`),
  KEY `educationId` (`educationId`),
  KEY `marriedId` (`marriedId`),
  KEY `qq` (`qq`),
  CONSTRAINT `UserInfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `UserInfo_ibfk_2` FOREIGN KEY (`sexId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `UserInfo_ibfk_3` FOREIGN KEY (`heightUnitId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `UserInfo_ibfk_4` FOREIGN KEY (`weightUnitId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `UserInfo_ibfk_5` FOREIGN KEY (`careerId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `UserInfo_ibfk_6` FOREIGN KEY (`educationId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `UserInfo_ibfk_7` FOREIGN KEY (`marriedId`) REFERENCES `mycozShared`.`LinearCode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'mycozBranch'
--
