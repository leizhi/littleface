-- MySQL dump 10.13  Distrib 5.1.49, for unknown-linux-gnu (x86_64)
--
-- Host: localhost    Database: mycozShared
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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `id` int(11) NOT NULL DEFAULT '0',
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `currencyId` int(11) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  `extensionId` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  KEY `name` (`name`),
  KEY `currencyId` (`currencyId`),
  KEY `groupId` (`groupId`),
  KEY `extensionId` (`extensionId`),
  KEY `description` (`description`),
  CONSTRAINT `Account_ibfk_1` FOREIGN KEY (`currencyId`) REFERENCES `Currency` (`ID`),
  CONSTRAINT `Account_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `AccountGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountCategory`
--

DROP TABLE IF EXISTS `AccountCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountCategory` (
  `id` int(11) NOT NULL DEFAULT '0',
  `elementId` int(11) NOT NULL DEFAULT '0',
  `categoryName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `categoryCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `elementId` (`elementId`),
  KEY `description` (`description`),
  KEY `categoryName` (`categoryName`),
  KEY `categoryCode` (`categoryCode`),
  CONSTRAINT `AccountCategory_ibfk_1` FOREIGN KEY (`elementId`) REFERENCES `AccountElement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountElement`
--

DROP TABLE IF EXISTS `AccountElement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountElement` (
  `id` int(11) NOT NULL DEFAULT '0',
  `elementName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `elementCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `description` (`description`),
  KEY `elementName` (`elementName`),
  KEY `elementCode` (`elementCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountGroup`
--

DROP TABLE IF EXISTS `AccountGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountGroup` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeId` int(11) DEFAULT NULL,
  `groupCode` varchar(100) DEFAULT NULL,
  `groupName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`),
  KEY `groupCode` (`groupCode`),
  KEY `groupName` (`groupName`),
  KEY `description` (`description`),
  CONSTRAINT `AccountGroup_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `AccountType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountType`
--

DROP TABLE IF EXISTS `AccountType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `categoryId` int(11) DEFAULT NULL,
  `typeCode` varchar(100) DEFAULT NULL,
  `typeName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  KEY `typeCode` (`typeCode`),
  KEY `typeName` (`typeName`),
  KEY `description` (`description`),
  CONSTRAINT `AccountType_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `AccountCategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Action`
--

DROP TABLE IF EXISTS `Action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Action` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AuthGroup`
--

DROP TABLE IF EXISTS `AuthGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthGroup` (
  `id` int(11) NOT NULL DEFAULT '0',
  `groupId` int(11) DEFAULT NULL,
  `methodId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_gm` (`groupId`,`methodId`),
  KEY `groupId` (`groupId`),
  KEY `methodId` (`methodId`),
  CONSTRAINT `AuthGroup_ibfk_1` FOREIGN KEY (`groupId`) REFERENCES `UserGroup` (`id`),
  CONSTRAINT `AuthGroup_ibfk_2` FOREIGN KEY (`methodId`) REFERENCES `Method` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AuthRole`
--

DROP TABLE IF EXISTS `AuthRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthRole` (
  `id` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) DEFAULT NULL,
  `methodId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_rm` (`roleId`,`methodId`),
  KEY `roleId` (`roleId`),
  KEY `methodId` (`methodId`),
  CONSTRAINT `AuthRole_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `UserRole` (`id`),
  CONSTRAINT `AuthRole_ibfk_2` FOREIGN KEY (`methodId`) REFERENCES `Method` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Branch`
--

DROP TABLE IF EXISTS `Branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Branch` (
  `id` int(11) NOT NULL DEFAULT '0',
  `branchCode` varchar(5) DEFAULT NULL,
  `branchName` varchar(50) DEFAULT NULL,
  `branchPrefix` varchar(50) DEFAULT NULL,
  `enterDate` date DEFAULT NULL,
  `modifyDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `branchCode` (`branchCode`),
  UNIQUE KEY `branchName` (`branchName`),
  UNIQUE KEY `branchPrefix` (`branchPrefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Career`
--

DROP TABLE IF EXISTS `Career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Career` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `id` int(11) NOT NULL DEFAULT '0',
  `countryId` int(11) DEFAULT NULL,
  `code` varchar(20) NOT NULL DEFAULT 'NULL',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `City_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `Country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Country` (
  `id` int(11) NOT NULL DEFAULT '0',
  `code` varchar(20) NOT NULL DEFAULT 'NULL',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Currency`
--

DROP TABLE IF EXISTS `Currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Currency` (
  `id` int(11) NOT NULL DEFAULT '0',
  `codeISO` varchar(20) DEFAULT NULL,
  `countryId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `symbol` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeISO` (`codeISO`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `Currency_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `Country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Education`
--

DROP TABLE IF EXISTS `Education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Education` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `HeightUnit`
--

DROP TABLE IF EXISTS `HeightUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HeightUnit` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Language`
--

DROP TABLE IF EXISTS `Language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Language` (
  `id` int(11) NOT NULL DEFAULT '0',
  `code` varchar(20) NOT NULL DEFAULT 'NULL',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Married`
--

DROP TABLE IF EXISTS `Married`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Married` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Method`
--

DROP TABLE IF EXISTS `Method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Method` (
  `id` int(11) NOT NULL DEFAULT '0',
  `actionId` int(11) DEFAULT NULL,
  `methodName` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` char(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `actionId` (`actionId`),
  KEY `methodName` (`methodName`),
  KEY `description` (`description`),
  CONSTRAINT `Method_ibfk_1` FOREIGN KEY (`actionId`) REFERENCES `Action` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NoteType`
--

DROP TABLE IF EXISTS `NoteType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NoteType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeKey` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT NULL,
  `prefix` varchar(5) DEFAULT '',
  `description` text,
  `Category` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `typeKey` (`typeKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Sex`
--

DROP TABLE IF EXISTS `Sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sex` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserGroup`
--

DROP TABLE IF EXISTS `UserGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserGroup` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `description` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `description` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WeightUnit`
--

DROP TABLE IF EXISTS `WeightUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WeightUnit` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'mycozShared'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-11-17 18:54:42
