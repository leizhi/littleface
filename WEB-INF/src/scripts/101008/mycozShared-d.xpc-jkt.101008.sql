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
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT '',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  `ExtensionID` int(11) NOT NULL DEFAULT '0',
  `CurrencyID` int(11) NOT NULL DEFAULT '0',
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`),
  KEY `GroupID` (`GroupID`),
  KEY `ExtensionID` (`ExtensionID`),
  KEY `CurrencyID` (`CurrencyID`),
  CONSTRAINT `Account_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `AccountGroup` (`ID`),
  CONSTRAINT `Account_ibfk_2` FOREIGN KEY (`ExtensionID`) REFERENCES `AccountExtension` (`ID`),
  CONSTRAINT `Account_ibfk_3` FOREIGN KEY (`CurrencyID`) REFERENCES `Currency` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountCategory`
--

DROP TABLE IF EXISTS `AccountCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountCategory` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountExtension`
--

DROP TABLE IF EXISTS `AccountExtension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountExtension` (
  `ID` int(6) NOT NULL DEFAULT '0',
  `Code` varchar(20) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountGroup`
--

DROP TABLE IF EXISTS `AccountGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountGroup` (
  `ID` int(6) NOT NULL DEFAULT '0',
  `TypeID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`),
  KEY `TypeID` (`TypeID`),
  CONSTRAINT `AccountGroup_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `AccountType` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AccountType`
--

DROP TABLE IF EXISTS `AccountType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountType` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `CategoryID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Description` varchar(50) DEFAULT NULL,
  `NoteTypeID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`),
  KEY `CategoryID` (`CategoryID`),
  KEY `NoteTypeID` (`NoteTypeID`),
  CONSTRAINT `AccountType_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `AccountCategory` (`ID`),
  CONSTRAINT `AccountType_ibfk_2` FOREIGN KEY (`NoteTypeID`) REFERENCES `NoteType` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BlogCategory`
--

DROP TABLE IF EXISTS `BlogCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BlogCategory` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `ParentID` int(11) NOT NULL DEFAULT '0',
  `LayerID` int(11) NOT NULL DEFAULT '0',
  `Name` varchar(20) NOT NULL DEFAULT '',
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ParentID` (`ParentID`),
  KEY `LayerID` (`LayerID`),
  KEY `Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `CountryID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Name` varchar(50) NOT NULL DEFAULT 'NULL',
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CountryID` (`CountryID`),
  CONSTRAINT `City_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `Country` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CodeType`
--

DROP TABLE IF EXISTS `CodeType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CodeType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `category` enum('Linear','Tree') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`category`),
  KEY `name` (`name`),
  KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Country` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Name` varchar(50) NOT NULL DEFAULT 'NULL',
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Currency`
--

DROP TABLE IF EXISTS `Currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Currency` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `ISOCode` varchar(20) DEFAULT NULL,
  `CountryID` int(11) NOT NULL DEFAULT '0',
  `Name` varchar(50) DEFAULT NULL,
  `Symbol` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISOCode` (`ISOCode`),
  KEY `CountryID` (`CountryID`),
  CONSTRAINT `Currency_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `Country` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Language`
--

DROP TABLE IF EXISTS `Language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Language` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Name` varchar(50) NOT NULL DEFAULT 'NULL',
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `LinearCode`
--

DROP TABLE IF EXISTS `LinearCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LinearCode` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`typeId`),
  KEY `typeId` (`typeId`),
  KEY `name` (`name`),
  CONSTRAINT `LinearCode_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `CodeType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NoteType`
--

DROP TABLE IF EXISTS `NoteType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NoteType` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) DEFAULT 'NULL',
  `Category` enum('Debit','Credit') DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OperatorUser`
--

DROP TABLE IF EXISTS `OperatorUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OperatorUser` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `CountryID` int(11) NOT NULL DEFAULT '0',
  `City` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Tel` varchar(60) DEFAULT NULL,
  `Zip` varchar(20) DEFAULT NULL,
  `States` smallint(6) NOT NULL DEFAULT '7',
  `LanguageID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserName` (`UserName`),
  KEY `CountryID` (`CountryID`),
  KEY `LanguageID` (`LanguageID`),
  CONSTRAINT `OperatorUser_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `Country` (`ID`),
  CONSTRAINT `OperatorUser_ibfk_2` FOREIGN KEY (`LanguageID`) REFERENCES `Language` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `State`
--

DROP TABLE IF EXISTS `State`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `State` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Code` varchar(20) NOT NULL DEFAULT 'NULL',
  `Name` varchar(50) NOT NULL DEFAULT 'NULL',
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tree`
--

DROP TABLE IF EXISTS `Tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tree` (
  `parentId` int(11) NOT NULL DEFAULT '0',
  `childId` int(11) NOT NULL DEFAULT '0',
  `typeId` int(11) NOT NULL DEFAULT '0',
  KEY `parentId` (`parentId`),
  KEY `childId` (`childId`),
  KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'mycozShared'
--
