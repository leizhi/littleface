-- MySQL dump 10.11
--
-- Host: localhost    Database: mycozBranch
-- ------------------------------------------------------
-- Server version	5.0.45-log

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
-- Table structure for table `Blog`
--

DROP TABLE IF EXISTS `Blog`;
CREATE TABLE `Blog` (
  `ID` int(11) NOT NULL default '0',
  `UserID` int(11) NOT NULL default '0',
  `BlogTypeID` int(11) NOT NULL default '0',
  `BlogDate` date default NULL,
  `Title` varchar(50) default 'NULL',
  `Description` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `Title` (`Title`),
  KEY `Blog_ibfk_1` (`UserID`),
  KEY `Blog_ibfk_2` (`BlogTypeID`),
  CONSTRAINT `Blog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`),
  CONSTRAINT `Blog_ibfk_2` FOREIGN KEY (`BlogTypeID`) REFERENCES `mycozShared`.`BlogType` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `Download`
--

DROP TABLE IF EXISTS `Download`;
CREATE TABLE `Download` (
  `ID` int(11) NOT NULL default '0',
  `TypeID` int(11) NOT NULL default '0',
  `Name` varchar(20) default NULL,
  `DownloadPath` varchar(200) default NULL,
  `ImagePath` varchar(200) default NULL,
  `Date` date default NULL,
  `Description` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `TypeID` (`TypeID`),
  CONSTRAINT `Download_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `ebgoShared`.`DownloadType` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `JobAccounting`
--

DROP TABLE IF EXISTS `JobAccounting`;
CREATE TABLE `JobAccounting` (
  `ID` int(11) NOT NULL default '0',
  `JobNoteID` int(11) NOT NULL default '0',
  `ItemName` varchar(100) default NULL,
  `ItemRate` decimal(25,4) default NULL,
  `ItemUnit` varchar(10) default NULL,
  `ItemQuantity` double default '1',
  `CurrencyID` int(11) NOT NULL default '0',
  `AccountID` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ItemName` (`ItemName`,`JobNoteID`,`AccountID`),
  KEY `JobNoteID` (`JobNoteID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `JobAccounting_ibfk_3` (`AccountID`),
  CONSTRAINT `JobAccounting_ibfk_1` FOREIGN KEY (`JobNoteID`) REFERENCES `JobNote` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_2` FOREIGN KEY (`CurrencyID`) REFERENCES `mycozShared`.`Currency` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_3` FOREIGN KEY (`AccountID`) REFERENCES `mycozShared`.`Account` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `JobJournal`
--

DROP TABLE IF EXISTS `JobJournal`;
CREATE TABLE `JobJournal` (
  `ID` int(11) NOT NULL default '0',
  `AccountID` int(11) NOT NULL default '0',
  `JobNoteID` int(11) NOT NULL default '0',
  `DCType` enum('Credit','Debit') default NULL,
  `Amount` decimal(25,4) default NULL,
  `OrgCurrencyID` int(11) NOT NULL default '0',
  `OrgRate` double default NULL,
  `JobAccountingID` int(11) NOT NULL default '0',
  `ExchangeRate` double default '1',
  PRIMARY KEY  (`ID`),
  KEY `AccountID` (`AccountID`),
  KEY `JobNoteID` (`JobNoteID`),
  KEY `OrgCurrencyID` (`OrgCurrencyID`),
  KEY `JobAccountingID` (`JobAccountingID`),
  CONSTRAINT `JobJournal_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `mycozShared`.`Account` (`ID`),
  CONSTRAINT `JobJournal_ibfk_2` FOREIGN KEY (`JobNoteID`) REFERENCES `JobNote` (`ID`),
  CONSTRAINT `JobJournal_ibfk_3` FOREIGN KEY (`OrgCurrencyID`) REFERENCES `mycozShared`.`Currency` (`ID`),
  CONSTRAINT `JobJournal_ibfk_4` FOREIGN KEY (`JobAccountingID`) REFERENCES `JobAccounting` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `JobNote`
--

DROP TABLE IF EXISTS `JobNote`;
CREATE TABLE `JobNote` (
  `ID` int(11) NOT NULL default '0',
  `NoteNo` varchar(60) default 'NULL',
  `NoteTypeID` int(11) NOT NULL default '0',
  `OperatorID` int(11) NOT NULL default '0',
  `ChargeToID` int(11) NOT NULL default '0',
  `IsPosted` enum('Y','N','O','A') default NULL,
  `PostDate` date default NULL,
  `PrintCount` smallint(6) default '0',
  `Date` date default NULL,
  `DueDate` date default NULL,
  `Description` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NoteNo` (`NoteNo`),
  KEY `NoteTypeID` (`NoteTypeID`),
  KEY `OperatorID` (`OperatorID`),
  KEY `ChargeToID` (`ChargeToID`),
  CONSTRAINT `JobNote_ibfk_1` FOREIGN KEY (`NoteTypeID`) REFERENCES `mycozShared`.`NoteType` (`ID`),
  CONSTRAINT `JobNote_ibfk_2` FOREIGN KEY (`OperatorID`) REFERENCES `mycozShared`.`OperatorUser` (`ID`),
  CONSTRAINT `JobNote_ibfk_3` FOREIGN KEY (`ChargeToID`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
CREATE TABLE `Message` (
  `ID` int(11) NOT NULL default '0',
  `UserID` int(11) NOT NULL default '0',
  `BlogID` int(11) NOT NULL default '0',
  `MessageDate` date default NULL,
  `Title` varchar(50) default 'NULL',
  `Description` text,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `Title` (`Title`),
  KEY `Message_ibfk_1` (`UserID`),
  KEY `Message_ibfk_2` (`BlogID`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`ID`),
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`BlogID`) REFERENCES `Blog` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL default '0',
  `Password` varchar(50) default NULL,
  `CountryID` int(11) NOT NULL default '0',
  `Address` varchar(200) default NULL,
  `Email` varchar(50) default NULL,
  `Tel` varchar(60) default NULL,
  `Zip` varchar(20) default NULL,
  `LanguageID` int(11) NOT NULL default '0',
  `TypeID` int(11) NOT NULL default '0',
  `CityID` int(11) NOT NULL default '0',
  `StateID` int(11) NOT NULL default '0',
  `MobileNo` varchar(60) default NULL,
  `Name` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `CountryID` (`CountryID`),
  KEY `LanguageID` (`LanguageID`),
  KEY `TypeID` (`TypeID`),
  KEY `CityID` (`CityID`),
  KEY `StateID` (`StateID`),
  CONSTRAINT `User_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `mycozShared`.`Country` (`ID`),
  CONSTRAINT `User_ibfk_2` FOREIGN KEY (`LanguageID`) REFERENCES `mycozShared`.`Language` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-06-03 12:39:26
