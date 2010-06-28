-- MySQL dump 10.11
--
-- Host: localhost    Database: xpcBranch
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
-- Table structure for table `AirBookInfo`
--

DROP TABLE IF EXISTS `AirBookInfo`;
CREATE TABLE `AirBookInfo` (
  `BookDetailID` int(11) NOT NULL default '0',
  `FlightNoExPOL` varchar(20) default NULL,
  `FlightNoExTS` varchar(20) default NULL,
  `FlightNo` varchar(20) default NULL,
  `BidRate` decimal(20,2) default NULL,
  `PickupDate` date default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`BookDetailID`,`BranchID`),
  KEY `BookDetailID` (`BookDetailID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `AirBookInfo_ibfk_1` FOREIGN KEY (`BookDetailID`, `BranchID`) REFERENCES `BookDetail` (`ID`, `BranchID`),
  CONSTRAINT `AirBookInfo_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `AirHouseBill`
--

DROP TABLE IF EXISTS `AirHouseBill`;
CREATE TABLE `AirHouseBill` (
  `ID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `HouseBillNo` varchar(20) default NULL,
  `DetailNo` smallint(6) default NULL,
  `NoOfOrg` int(6) default NULL,
  `ConsolRef` varchar(20) default NULL,
  `AccountInfo` text,
  `Currency` varchar(10) default NULL,
  `Ppd` varchar(30) default NULL,
  `Coll` varchar(30) default NULL,
  `PpdOther` varchar(30) default NULL,
  `CollOther` varchar(30) default NULL,
  `DeclarValueCarr` varchar(30) default NULL,
  `DecalarValueCust` varchar(30) default NULL,
  `InsureAmount` varchar(30) default NULL,
  `HandleInfo` text,
  `ChargeRate` decimal(14,3) default NULL,
  `AwbFee` varchar(30) default NULL,
  `Cartage` varchar(60) default NULL,
  `ExeDate` varchar(30) default NULL,
  `ExePlace` varchar(30) default NULL,
  `Prepaid1` decimal(14,3) default NULL,
  `Prepaid2` decimal(14,3) default NULL,
  `Prepaid3` decimal(14,3) default NULL,
  `Prepaid4` decimal(14,3) default NULL,
  `Prepaid5` decimal(14,3) default NULL,
  `Collect1` decimal(14,3) default NULL,
  `Collect2` decimal(14,3) default NULL,
  `Collect3` decimal(14,3) default NULL,
  `Collect4` decimal(14,3) default NULL,
  `Collect5` decimal(14,3) default NULL,
  `Segment` smallint(6) NOT NULL default '1',
  `Shipper` varchar(255) default NULL,
  `Consignee` varchar(255) default NULL,
  `NotifyParty` varchar(255) default NULL,
  `DeliveryPlace` varchar(80) default NULL,
  `GrossWeight` float default NULL,
  `ChargeableWeight` float default NULL,
  `WeightUnit` varchar(6) default NULL,
  `FinalMeasure` float default NULL,
  `MeasureUnit` varchar(6) default NULL,
  `NoOfPack` int(11) default NULL,
  `PackUnit` varchar(32) default 'Piece',
  `ChargeTotal` decimal(20,3) default NULL,
  `GoodsDescription` text,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `Segment` (`Segment`,`ClientJobID`,`BranchID`),
  UNIQUE KEY `HouseBillNo` (`HouseBillNo`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `AirHouseBill_ibfk_1` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `AirHouseBill_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `AirMasterBill`
--

DROP TABLE IF EXISTS `AirMasterBill`;
CREATE TABLE `AirMasterBill` (
  `ID` int(11) NOT NULL default '0',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `DetailNo` smallint(6) default NULL,
  `NoOfOrg` int(6) default NULL,
  `ConsolRef` varchar(20) default NULL,
  `AccountInfo` text,
  `Currency` varchar(10) default NULL,
  `ChargeCode` varchar(10) default NULL,
  `Ppd` varchar(30) default NULL,
  `Coll` varchar(30) default NULL,
  `PpdOther` varchar(30) default NULL,
  `CollOther` varchar(30) default NULL,
  `DeclarValueCarr` varchar(30) default NULL,
  `DecalarValueCust` varchar(30) default NULL,
  `InsureAmount` varchar(30) default NULL,
  `HandleInfo` text,
  `ChargeRate` decimal(14,3) default NULL,
  `AwbFee` varchar(30) default NULL,
  `Cartage` varchar(60) default NULL,
  `ExeDate` varchar(30) default NULL,
  `ExePlace` varchar(30) default NULL,
  `Prepaid1` decimal(14,3) default NULL,
  `Prepaid2` decimal(14,3) default NULL,
  `Prepaid3` decimal(14,3) default NULL,
  `Prepaid4` decimal(14,3) default NULL,
  `Prepaid5` decimal(14,3) default NULL,
  `Collect1` decimal(14,3) default NULL,
  `Collect2` decimal(14,3) default NULL,
  `Collect3` decimal(14,3) default NULL,
  `Collect4` decimal(14,3) default NULL,
  `Collect5` decimal(14,3) default NULL,
  `ShipperAccountNo` varchar(30) default NULL,
  `IATACode` varchar(30) default NULL,
  `AgentAccountNo` varchar(30) default NULL,
  `ConsigneeAccountNo` varchar(30) default NULL,
  `Total` varchar(30) default NULL,
  `Shipper` varchar(255) default NULL,
  `Consignee` varchar(255) default NULL,
  `GrossWeight` float default NULL,
  `ChargeableWeight` float default NULL,
  `WeightUnit` varchar(6) default NULL,
  `FinalMeasure` float default NULL,
  `MeasureUnit` varchar(6) default NULL,
  `NoOfPack` int(11) default NULL,
  `PackUnit` varchar(32) default 'Piece',
  `ChargeTotal` decimal(20,3) default NULL,
  `GoodsDescription` text,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `RefJobID` (`RefJobID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `AirMasterBill_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `AirMasterBill_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `BookDetail`
--

DROP TABLE IF EXISTS `BookDetail`;
CREATE TABLE `BookDetail` (
  `ID` int(11) NOT NULL default '0',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `JobTypeID` smallint(6) NOT NULL default '0',
  `RequestNo` smallint(6) default NULL,
  `MasterBillID` varchar(20) default NULL,
  `VendorID` int(11) NOT NULL default '0',
  `ConsigneeID` int(11) NOT NULL default '0',
  `NotifyID` int(11) NOT NULL default '0',
  `ConfirmationDate` date default NULL,
  `ConfirmationNo` varchar(30) default NULL,
  `FreightStatusID` smallint(6) NOT NULL default '0',
  `LoadPortID` int(11) NOT NULL default '0',
  `PrintedPOL` varchar(128) default NULL,
  `DischargePortID` int(11) NOT NULL default '0',
  `TSPortID` int(11) NOT NULL default '0',
  `LoadDate` date default NULL,
  `DischargeDate` date default NULL,
  `PickupDate` date default NULL,
  `ETDPOL` date default NULL,
  `ETDTS` date default NULL,
  `ETAPOD` date default NULL,
  `IsConfirmed` char(1) default 'N',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `RefJobID` (`RefJobID`,`RequestNo`,`BranchID`),
  KEY `LoadPortID` (`LoadPortID`),
  KEY `DischargePortID` (`DischargePortID`),
  KEY `TSPortID` (`TSPortID`),
  KEY `VendorID` (`VendorID`),
  KEY `ConsigneeID` (`ConsigneeID`),
  KEY `NotifyID` (`NotifyID`),
  KEY `JobTypeID` (`JobTypeID`),
  KEY `BranchID` (`BranchID`),
  KEY `RefJobID_2` (`RefJobID`,`BranchID`),
  CONSTRAINT `BookDetail_ibfk_1` FOREIGN KEY (`LoadPortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `BookDetail_ibfk_2` FOREIGN KEY (`DischargePortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `BookDetail_ibfk_3` FOREIGN KEY (`TSPortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `BookDetail_ibfk_4` FOREIGN KEY (`VendorID`) REFERENCES `GeneralBody` (`ID`),
  CONSTRAINT `BookDetail_ibfk_5` FOREIGN KEY (`ConsigneeID`) REFERENCES `GeneralBody` (`ID`),
  CONSTRAINT `BookDetail_ibfk_6` FOREIGN KEY (`NotifyID`) REFERENCES `xpcShared`.`SharedNotifyParty` (`ID`),
  CONSTRAINT `BookDetail_ibfk_7` FOREIGN KEY (`JobTypeID`) REFERENCES `xpcShared`.`JobType` (`ID`),
  CONSTRAINT `BookDetail_ibfk_8` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `BookDetail_ibfk_9` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `ClientJob`
--

DROP TABLE IF EXISTS `ClientJob`;
CREATE TABLE `ClientJob` (
  `ID` varchar(20) NOT NULL default '',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `CusID` int(11) NOT NULL default '0',
  `ConsigneeID` int(11) NOT NULL default '0',
  `NotifyID` int(11) NOT NULL default '0',
  `JobDate` date default NULL,
  `ShipperSINo` varchar(50) default NULL,
  `Category` enum('AE','AI','SE','SI','PE','PI') default NULL,
  `ReceiptPlace` varchar(50) default NULL,
  `PlaceOfIssue` varchar(50) default NULL,
  `DeliveryPlace` varchar(50) default NULL,
  `FinalDest` varchar(50) default NULL,
  `LoadPortID` int(11) NOT NULL default '0',
  `DischargePortID` int(11) NOT NULL default '0',
  `DeliveryPortID` int(11) NOT NULL default '0',
  `PONo` varchar(50) default NULL,
  `OrgCopyNo` varchar(10) default ' THREE',
  `PODDate` date default NULL,
  `DateOfIssue` date default NULL,
  `StuffDate` date default NULL,
  `SalesPersonID` smallint(6) NOT NULL default '0',
  `OperatorID` smallint(6) NOT NULL default '0',
  `DeliveryAgentID` int(11) NOT NULL default '0',
  `SelectedBookNo` smallint(6) default NULL,
  `ModifyDate` date default NULL,
  `IsFCL` char(1) default 'Y',
  `FreightStatusID` smallint(6) NOT NULL default '0',
  `IsAssigned` char(1) default 'N',
  `Comment` text,
  `JobStatusID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  `JobTypeID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `RefJobID` (`RefJobID`,`BranchID`),
  KEY `CusID` (`CusID`,`BranchID`),
  KEY `ConsigneeID` (`ConsigneeID`,`BranchID`),
  KEY `NotifyID` (`NotifyID`,`BranchID`),
  KEY `LoadPortID` (`LoadPortID`),
  KEY `DischargePortID` (`DischargePortID`),
  KEY `SalesPersonID` (`SalesPersonID`,`BranchID`),
  KEY `OperatorID` (`OperatorID`,`BranchID`),
  KEY `DeliveryAgentID` (`DeliveryAgentID`),
  KEY `FreightStatusID` (`FreightStatusID`),
  KEY `JobStatusID` (`JobStatusID`),
  KEY `BranchID` (`BranchID`),
  KEY `DeliveryPortID` (`DeliveryPortID`),
  KEY `JobTypeID` (`JobTypeID`),
  CONSTRAINT `ClientJob_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_10` FOREIGN KEY (`FreightStatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `ClientJob_ibfk_11` FOREIGN KEY (`JobStatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `ClientJob_ibfk_12` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `ClientJob_ibfk_13` FOREIGN KEY (`DeliveryPortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `ClientJob_ibfk_14` FOREIGN KEY (`JobTypeID`) REFERENCES `xpcShared`.`JobType` (`ID`),
  CONSTRAINT `ClientJob_ibfk_2` FOREIGN KEY (`CusID`, `BranchID`) REFERENCES `GeneralBody` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_3` FOREIGN KEY (`ConsigneeID`, `BranchID`) REFERENCES `GeneralBody` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_4` FOREIGN KEY (`NotifyID`, `BranchID`) REFERENCES `NotifyParty` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_5` FOREIGN KEY (`LoadPortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `ClientJob_ibfk_6` FOREIGN KEY (`DischargePortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `ClientJob_ibfk_7` FOREIGN KEY (`SalesPersonID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_8` FOREIGN KEY (`OperatorID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `ClientJob_ibfk_9` FOREIGN KEY (`DeliveryAgentID`) REFERENCES `GeneralBody` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `ClientJobContainer`
--

DROP TABLE IF EXISTS `ClientJobContainer`;
CREATE TABLE `ClientJobContainer` (
  `ID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `ContainerTypeID` smallint(6) NOT NULL default '0',
  `ContainerNo` varchar(50) default NULL,
  `ItemID` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `ClientJobID` (`ClientJobID`,`ContainerTypeID`,`ItemID`,`BranchID`),
  KEY `ClientJobID_2` (`ClientJobID`),
  KEY `ContainerTypeID` (`ContainerTypeID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `ClientJobContainer_ibfk_1` FOREIGN KEY (`ClientJobID`) REFERENCES `ClientJob` (`ID`),
  CONSTRAINT `ClientJobContainer_ibfk_2` FOREIGN KEY (`ContainerTypeID`) REFERENCES `xpcShared`.`ContainerType` (`ID`),
  CONSTRAINT `ClientJobContainer_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `ClientJobFreight`
--

DROP TABLE IF EXISTS `ClientJobFreight`;
CREATE TABLE `ClientJobFreight` (
  `FreightID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `ItemID` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`FreightID`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `FreightID` (`FreightID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `ClientJobFreight_ibfk_1` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `ClientJobFreight_ibfk_2` FOREIGN KEY (`FreightID`, `BranchID`) REFERENCES `Freight` (`ID`, `BranchID`),
  CONSTRAINT `ClientJobFreight_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `ClientJobTrack`
--

DROP TABLE IF EXISTS `ClientJobTrack`;
CREATE TABLE `ClientJobTrack` (
  `ID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `Event` varchar(60) default NULL,
  `Place` varchar(30) default NULL,
  `Date` date default NULL,
  `Comments` text,
  `StatusID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `StatusID` (`StatusID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `ClientJobTrack_ibfk_1` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `ClientJobTrack_ibfk_2` FOREIGN KEY (`StatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `ClientJobTrack_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `ContainerClientInfo`
--

DROP TABLE IF EXISTS `ContainerClientInfo`;
CREATE TABLE `ContainerClientInfo` (
  `ID` int(11) NOT NULL default '0',
  `RefJobContainerID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `RefJobContainerID` (`RefJobContainerID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `ContainerClientInfo_ibfk_1` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `ContainerClientInfo_ibfk_2` FOREIGN KEY (`RefJobContainerID`, `BranchID`) REFERENCES `RefJobContainer` (`ID`, `BranchID`),
  CONSTRAINT `ContainerClientInfo_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (
  `ID` int(11) NOT NULL default '0',
  `ShortName` varchar(10) default NULL,
  `Name` varchar(50) default NULL,
  `CreateDate` date default NULL,
  `ModifyDate` date default NULL,
  `SalesPersonID` smallint(6) NOT NULL default '0',
  `ContactPerson` varchar(50) default NULL,
  `LocalAddressID` int(11) NOT NULL default '0',
  `Type` enum('Consignee','Customer') default NULL,
  `AccountNoID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `BranchID` (`BranchID`,`ShortName`),
  UNIQUE KEY `ShortName` (`ShortName`,`BranchID`),
  KEY `AccountNoID` (`AccountNoID`,`BranchID`),
  KEY `SalesPersonID` (`SalesPersonID`,`BranchID`),
  KEY `LocalAddressID` (`LocalAddressID`,`BranchID`),
  KEY `BranchID_2` (`BranchID`),
  CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`AccountNoID`, `BranchID`) REFERENCES `LocalPartnerAccount` (`ID`, `BranchID`),
  CONSTRAINT `Customer_ibfk_2` FOREIGN KEY (`SalesPersonID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `Customer_ibfk_3` FOREIGN KEY (`LocalAddressID`, `BranchID`) REFERENCES `LocalAddressBook` (`ID`, `BranchID`),
  CONSTRAINT `Customer_ibfk_4` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `ID` smallint(6) NOT NULL default '0',
  `ShortName` varchar(10) default NULL,
  `LocalPersonID` int(11) NOT NULL default '0',
  `LoginName` varchar(20) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `ShortName` (`ShortName`,`BranchID`),
  UNIQUE KEY `LoginName` (`LoginName`,`BranchID`),
  KEY `LocalPersonID` (`LocalPersonID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`LocalPersonID`, `BranchID`) REFERENCES `LocalPerson` (`ID`, `BranchID`),
  CONSTRAINT `Employee_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Freight`
--

DROP TABLE IF EXISTS `Freight`;
CREATE TABLE `Freight` (
  `ID` int(11) NOT NULL default '0',
  `CommodityID` int(11) NOT NULL default '0',
  `GrossWeight` double default NULL,
  `FinalWeight` double default NULL,
  `NetWeight` double default NULL,
  `WeightUnit` varchar(6) default NULL,
  `FinalMeasure` double default NULL,
  `Measurement` double default NULL,
  `ChargeableTypeID` smallint(6) NOT NULL default '0',
  `MeasureUnit` varchar(6) default NULL,
  `NoOfPack` int(11) default NULL,
  `PackUnit` varchar(32) default 'Piece',
  `ChargeRate` double default NULL,
  `Marks` text,
  `Description` text,
  `CommodityRemark` varchar(100) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `CommodityID` (`CommodityID`),
  KEY `ChargeableTypeID` (`ChargeableTypeID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `Freight_ibfk_1` FOREIGN KEY (`CommodityID`) REFERENCES `xpcShared`.`Commodity` (`ID`),
  CONSTRAINT `Freight_ibfk_2` FOREIGN KEY (`ChargeableTypeID`) REFERENCES `xpcShared`.`ChargeableType` (`ID`),
  CONSTRAINT `Freight_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `GeneralBody`
--

DROP TABLE IF EXISTS `GeneralBody`;
CREATE TABLE `GeneralBody` (
  `ID` int(11) NOT NULL default '0',
  `CustomerID` int(11) NOT NULL default '0',
  `PartnerID` int(11) NOT NULL default '0',
  `Type` enum('Customer','Partner','LocalPartner') default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  `LocalPartnerID` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `CustomerID` (`CustomerID`,`BranchID`),
  KEY `PartnerID` (`PartnerID`),
  KEY `BranchID` (`BranchID`),
  KEY `LocalPartnerID` (`LocalPartnerID`,`BranchID`),
  CONSTRAINT `GeneralBody_ibfk_1` FOREIGN KEY (`LocalPartnerID`, `BranchID`) REFERENCES `LocalPartner` (`ID`, `BranchID`),
  CONSTRAINT `GeneralBody_ibfk_2` FOREIGN KEY (`PartnerID`) REFERENCES `xpcShared`.`Partner` (`ID`),
  CONSTRAINT `GeneralBody_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `GeneralBody_ibfk_4` FOREIGN KEY (`CustomerID`, `BranchID`) REFERENCES `Customer` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Haulage`
--

DROP TABLE IF EXISTS `Haulage`;
CREATE TABLE `Haulage` (
  `ID` int(11) NOT NULL default '0',
  `PickupRefNo` int(11) default NULL,
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `TruckingCompany` varchar(255) default NULL,
  `CreateDate` date default NULL,
  `ModifiedDate` date default NULL,
  `ByCompany` varchar(48) default NULL,
  `DeliveryRefNo` int(11) default NULL,
  `PickupTime` varchar(32) default NULL,
  `DeliveryTime` varchar(32) default NULL,
  `PickupAt` varchar(255) default NULL,
  `DeliveryTo` varchar(255) default NULL,
  `ContainerNo` varchar(255) default NULL,
  `BillTo` varchar(255) default NULL,
  `ReferenceNo` varchar(32) default NULL,
  `SpecialInstruction` text,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `PickupRefNo` (`PickupRefNo`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  CONSTRAINT `Haulage_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `Haulage_ibfk_2` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `JobAccounting`
--

DROP TABLE IF EXISTS `JobAccounting`;
CREATE TABLE `JobAccounting` (
  `ID` int(11) NOT NULL default '0',
  `JobNoteID` int(11) NOT NULL default '0',
  `RateType` varchar(20) default 'FreightRateID',
  `PartnerRateID` int(11) NOT NULL default '0',
  `LocalPartnerRateID` int(11) NOT NULL default '0',
  `FreightRateID` int(11) NOT NULL default '0',
  `ChargeType` varchar(10) default NULL,
  `NoteDetailID` smallint(6) default '0',
  `ItemName` varchar(100) default NULL,
  `ItemRate` decimal(25,4) default NULL,
  `ItemUnit` varchar(10) default NULL,
  `ItemQuantity` double default '1',
  `CurrencyID` smallint(6) NOT NULL default '0',
  `ExchangeRate` double NOT NULL default '1',
  `ByAgentID` int(11) NOT NULL default '0',
  `AirHouseBillID` int(11) NOT NULL default '0',
  `SeaHouseBillID` int(11) NOT NULL default '0',
  `RateCategoryID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `ItemName` (`ItemName`,`JobNoteID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `ByAgentID` (`ByAgentID`),
  KEY `LocalPartnerRateID` (`LocalPartnerRateID`,`BranchID`),
  KEY `PartnerRateID` (`PartnerRateID`),
  KEY `FreightRateID` (`FreightRateID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `JobNoteID` (`JobNoteID`,`BranchID`),
  KEY `RateCategoryID` (`RateCategoryID`),
  KEY `AirHouseBillID` (`AirHouseBillID`,`BranchID`),
  KEY `SeaHouseBillID` (`SeaHouseBillID`,`BranchID`),
  CONSTRAINT `JobAccounting_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_10` FOREIGN KEY (`SeaHouseBillID`, `BranchID`) REFERENCES `SeaHouseBill` (`ID`, `BranchID`),
  CONSTRAINT `JobAccounting_ibfk_2` FOREIGN KEY (`ByAgentID`) REFERENCES `xpcShared`.`Partner` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_3` FOREIGN KEY (`LocalPartnerRateID`, `BranchID`) REFERENCES `LocalPartnerRate` (`ID`, `BranchID`),
  CONSTRAINT `JobAccounting_ibfk_4` FOREIGN KEY (`PartnerRateID`) REFERENCES `xpcShared`.`PartnerRate` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_5` FOREIGN KEY (`FreightRateID`) REFERENCES `xpcShared`.`VendorRate` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_6` FOREIGN KEY (`CurrencyID`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_7` FOREIGN KEY (`JobNoteID`, `BranchID`) REFERENCES `JobNote` (`ID`, `BranchID`),
  CONSTRAINT `JobAccounting_ibfk_8` FOREIGN KEY (`RateCategoryID`) REFERENCES `xpcShared`.`RateCategory` (`ID`),
  CONSTRAINT `JobAccounting_ibfk_9` FOREIGN KEY (`AirHouseBillID`, `BranchID`) REFERENCES `AirHouseBill` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `OrgCurrencyID` smallint(6) NOT NULL default '0',
  `ExchangeRate` double default '1',
  `OrgRate` double default NULL,
  `JobAccountingID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `OrgCurrencyID` (`OrgCurrencyID`),
  KEY `AccountID` (`AccountID`),
  KEY `JobNoteID` (`JobNoteID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `JobAccountingID` (`JobAccountingID`,`BranchID`),
  CONSTRAINT `JobJournal_ibfk_1` FOREIGN KEY (`OrgCurrencyID`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `JobJournal_ibfk_2` FOREIGN KEY (`AccountID`) REFERENCES `xpcShared`.`Account` (`ID`),
  CONSTRAINT `JobJournal_ibfk_3` FOREIGN KEY (`JobNoteID`, `BranchID`) REFERENCES `JobNote` (`ID`, `BranchID`),
  CONSTRAINT `JobJournal_ibfk_4` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `JobJournal_ibfk_5` FOREIGN KEY (`JobAccountingID`, `BranchID`) REFERENCES `JobAccounting` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `JobNote`
--

DROP TABLE IF EXISTS `JobNote`;
CREATE TABLE `JobNote` (
  `ID` int(11) NOT NULL default '0',
  `GroupID` int(11) NOT NULL default '0',
  `NoteNo` varchar(20) NOT NULL default '',
  `NoteTypeID` smallint(6) default NULL,
  `CustomerID` int(11) NOT NULL default '0',
  `PartnerID` int(11) NOT NULL default '0',
  `LocalPartnerID` int(11) NOT NULL default '0',
  `FullName` varchar(50) default NULL,
  `SentVia` varchar(10) default NULL,
  `SplitRatio` double default '0.5',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `IsPosted` enum('Y','N','O','A') default NULL,
  `PrintCount` smallint(6) default '0',
  `Date` date default NULL,
  `DueDate` date default NULL,
  `OperatorID` smallint(6) NOT NULL default '0',
  `Description` varchar(200) default NULL,
  `ChargeTo` varchar(20) default 'Customer',
  `NoteMark` varchar(10) default NULL,
  `ChargeRateCategoryID` smallint(6) NOT NULL default '0',
  `CostRateCategoryID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  `PostDate` date default NULL,
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `NoteNo` (`NoteNo`,`BranchID`),
  KEY `PartnerID` (`PartnerID`),
  KEY `CustomerID` (`CustomerID`,`BranchID`),
  KEY `LocalPartnerID` (`LocalPartnerID`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `RefJobID` (`RefJobID`,`BranchID`),
  KEY `OperatorID` (`OperatorID`,`BranchID`),
  KEY `NoteTypeID` (`NoteTypeID`),
  KEY `BranchID` (`BranchID`),
  KEY `ChargeRateCategoryID` (`ChargeRateCategoryID`),
  KEY `CostRateCategoryID` (`CostRateCategoryID`),
  CONSTRAINT `JobNote_ibfk_1` FOREIGN KEY (`PartnerID`) REFERENCES `xpcShared`.`Partner` (`ID`),
  CONSTRAINT `JobNote_ibfk_10` FOREIGN KEY (`CostRateCategoryID`) REFERENCES `xpcShared`.`RateCategory` (`ID`),
  CONSTRAINT `JobNote_ibfk_2` FOREIGN KEY (`CustomerID`, `BranchID`) REFERENCES `Customer` (`ID`, `BranchID`),
  CONSTRAINT `JobNote_ibfk_3` FOREIGN KEY (`LocalPartnerID`, `BranchID`) REFERENCES `LocalPartner` (`ID`, `BranchID`),
  CONSTRAINT `JobNote_ibfk_4` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `JobNote_ibfk_5` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `JobNote_ibfk_6` FOREIGN KEY (`OperatorID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `JobNote_ibfk_7` FOREIGN KEY (`NoteTypeID`) REFERENCES `xpcShared`.`NoteType` (`ID`),
  CONSTRAINT `JobNote_ibfk_8` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `JobNote_ibfk_9` FOREIGN KEY (`ChargeRateCategoryID`) REFERENCES `xpcShared`.`RateCategory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `JobRoute`
--

DROP TABLE IF EXISTS `JobRoute`;
CREATE TABLE `JobRoute` (
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `SegmentNo` smallint(6) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `JobTypeID` smallint(6) default NULL,
  `RouteType` varchar(20) default NULL,
  `AgentID` int(11) default NULL,
  `DetailNo` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`RefJobID`,`DetailNo`,`BranchID`),
  UNIQUE KEY `RefJobID` (`RefJobID`,`ClientJobID`,`SegmentNo`,`BranchID`),
  KEY `RefJobID_2` (`RefJobID`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `AgentID` (`AgentID`),
  KEY `JobTypeID` (`JobTypeID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `JobRoute_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `JobRoute_ibfk_2` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `JobRoute_ibfk_3` FOREIGN KEY (`AgentID`) REFERENCES `xpcShared`.`Partner` (`ID`),
  CONSTRAINT `JobRoute_ibfk_4` FOREIGN KEY (`JobTypeID`) REFERENCES `xpcShared`.`JobType` (`ID`),
  CONSTRAINT `JobRoute_ibfk_5` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `LocalAddressBook`
--

DROP TABLE IF EXISTS `LocalAddressBook`;
CREATE TABLE `LocalAddressBook` (
  `ID` int(11) NOT NULL default '0',
  `Line1` varchar(100) default NULL,
  `Line2` varchar(100) default NULL,
  `City` varchar(30) default NULL,
  `Province` varchar(50) default NULL,
  `CountryID` smallint(6) NOT NULL default '0',
  `PostCode` varchar(20) default NULL,
  `Phone1` varchar(30) default NULL,
  `Phone2` varchar(30) default NULL,
  `Fax` varchar(30) default NULL,
  `Cell` varchar(30) default NULL,
  `Email` varchar(50) default NULL,
  `WebSite` varchar(50) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `CountryID` (`CountryID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `LocalAddressBook_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `xpcShared`.`Country` (`ID`),
  CONSTRAINT `LocalAddressBook_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `LocalPartner`
--

DROP TABLE IF EXISTS `LocalPartner`;
CREATE TABLE `LocalPartner` (
  `ID` int(11) NOT NULL default '0',
  `ShortName` varchar(10) default NULL,
  `Name` varchar(50) default NULL,
  `Type` varchar(50) default NULL,
  `PartnerTypeID` smallint(6) NOT NULL default '0',
  `ContactPerson` varchar(50) default NULL,
  `SalesPersonID` smallint(6) NOT NULL default '0',
  `CreateDate` date default NULL,
  `ModifyDate` date default NULL,
  `LocalAddressID` int(11) NOT NULL default '0',
  `AccountNoID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `BranchID` (`BranchID`,`ShortName`),
  UNIQUE KEY `ShortName` (`ShortName`,`BranchID`),
  KEY `LocalAddressID` (`LocalAddressID`,`BranchID`),
  KEY `SalesPersonID` (`SalesPersonID`,`BranchID`),
  KEY `PartnerTypeID` (`PartnerTypeID`),
  KEY `AccountNoID` (`AccountNoID`,`BranchID`),
  KEY `BranchID_2` (`BranchID`),
  CONSTRAINT `LocalPartner_ibfk_1` FOREIGN KEY (`LocalAddressID`, `BranchID`) REFERENCES `LocalAddressBook` (`ID`, `BranchID`),
  CONSTRAINT `LocalPartner_ibfk_2` FOREIGN KEY (`SalesPersonID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `LocalPartner_ibfk_3` FOREIGN KEY (`PartnerTypeID`) REFERENCES `xpcShared`.`PartnerType` (`ID`),
  CONSTRAINT `LocalPartner_ibfk_4` FOREIGN KEY (`AccountNoID`, `BranchID`) REFERENCES `LocalPartnerAccount` (`ID`, `BranchID`),
  CONSTRAINT `LocalPartner_ibfk_5` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `LocalPartnerAccount`
--

DROP TABLE IF EXISTS `LocalPartnerAccount`;
CREATE TABLE `LocalPartnerAccount` (
  `ID` int(11) NOT NULL default '0',
  `AccountNo` varchar(20) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `LocalPartnerAccount_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `LocalPartnerRate`
--

DROP TABLE IF EXISTS `LocalPartnerRate`;
CREATE TABLE `LocalPartnerRate` (
  `ID` int(11) NOT NULL default '0',
  `RateCode` varchar(20) default NULL,
  `LocalPartnerID` int(11) NOT NULL default '0',
  `Rate` decimal(14,2) default NULL,
  `Guide` decimal(14,2) default NULL,
  `GuideTypeID` smallint(6) NOT NULL default '0',
  `Unit` varchar(10) default NULL,
  `CurrencyID` smallint(6) NOT NULL default '0',
  `ValidDate` date default NULL,
  `Remark` varchar(40) default NULL,
  `AccountID` int(11) NOT NULL default '0',
  `PortID` int(11) NOT NULL default '0',
  `RateCategoryID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `UK2` (`LocalPartnerID`,`PortID`,`CurrencyID`,`AccountID`,`BranchID`),
  UNIQUE KEY `RateCode` (`RateCode`,`BranchID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `GuideTypeID` (`GuideTypeID`),
  KEY `LocalPartnerID` (`LocalPartnerID`,`BranchID`),
  KEY `AccountID` (`AccountID`),
  KEY `PortID` (`PortID`),
  KEY `BranchID` (`BranchID`),
  KEY `RateCategoryID` (`RateCategoryID`),
  CONSTRAINT `LocalPartnerRate_ibfk_1` FOREIGN KEY (`CurrencyID`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `LocalPartnerRate_ibfk_2` FOREIGN KEY (`GuideTypeID`) REFERENCES `xpcShared`.`GuideMapping` (`ID`),
  CONSTRAINT `LocalPartnerRate_ibfk_3` FOREIGN KEY (`LocalPartnerID`, `BranchID`) REFERENCES `LocalPartner` (`ID`, `BranchID`),
  CONSTRAINT `LocalPartnerRate_ibfk_4` FOREIGN KEY (`AccountID`) REFERENCES `xpcShared`.`Account` (`ID`),
  CONSTRAINT `LocalPartnerRate_ibfk_5` FOREIGN KEY (`PortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `LocalPartnerRate_ibfk_6` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `LocalPartnerRate_ibfk_7` FOREIGN KEY (`RateCategoryID`) REFERENCES `xpcShared`.`RateCategory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `LocalPerson`
--

DROP TABLE IF EXISTS `LocalPerson`;
CREATE TABLE `LocalPerson` (
  `ID` int(11) NOT NULL default '0',
  `FamilyName` varchar(30) default NULL,
  `GivenName` varchar(30) default NULL,
  `Gender` char(1) default NULL,
  `Title` varchar(30) default NULL,
  `Occupation` varchar(30) default NULL,
  `BirthDate` date default NULL,
  `SIN` varchar(30) default NULL,
  `LocalAddressID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `LocalAddressID` (`LocalAddressID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `LocalPerson_ibfk_1` FOREIGN KEY (`LocalAddressID`, `BranchID`) REFERENCES `LocalAddressBook` (`ID`, `BranchID`),
  CONSTRAINT `LocalPerson_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `NoteDetail`
--

DROP TABLE IF EXISTS `NoteDetail`;
CREATE TABLE `NoteDetail` (
  `DetailID` smallint(6) NOT NULL default '0',
  `JobNoteID` int(11) NOT NULL default '0',
  `Name` varchar(100) default NULL,
  `ChargeType` varchar(10) default NULL,
  `Rate` decimal(20,4) default NULL,
  `Unit` varchar(10) default NULL,
  `Quantity` double default '1',
  `Amount` double default '1',
  `CurrencyID` smallint(6) NOT NULL default '0',
  `ExchangeRate` double default NULL,
  `Remark` varchar(100) default NULL,
  `OrderNo` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`DetailID`,`JobNoteID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `JobNoteID` (`JobNoteID`,`BranchID`),
  CONSTRAINT `NoteDetail_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `NoteDetail_ibfk_2` FOREIGN KEY (`CurrencyID`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `NoteDetail_ibfk_3` FOREIGN KEY (`JobNoteID`, `BranchID`) REFERENCES `JobNote` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `NotifyParty`
--

DROP TABLE IF EXISTS `NotifyParty`;
CREATE TABLE `NotifyParty` (
  `ID` int(11) NOT NULL default '0',
  `ShortName` varchar(10) default NULL,
  `ConsigneeID` int(11) NOT NULL default '0',
  `ContactPerson` varchar(50) default NULL,
  `Name` varchar(50) default NULL,
  `LocalAddressID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `ShortName` (`ShortName`,`BranchID`),
  KEY `LocalAddressID` (`LocalAddressID`,`BranchID`),
  KEY `ConsigneeID` (`ConsigneeID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `NotifyParty_ibfk_1` FOREIGN KEY (`LocalAddressID`, `BranchID`) REFERENCES `LocalAddressBook` (`ID`, `BranchID`),
  CONSTRAINT `NotifyParty_ibfk_2` FOREIGN KEY (`ConsigneeID`, `BranchID`) REFERENCES `Customer` (`ID`, `BranchID`),
  CONSTRAINT `NotifyParty_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `PositionRecord`
--

DROP TABLE IF EXISTS `PositionRecord`;
CREATE TABLE `PositionRecord` (
  `ID` int(11) NOT NULL default '0',
  `EmpID` smallint(6) NOT NULL default '0',
  `Position` varchar(30) default NULL,
  `Salary1` decimal(14,2) default NULL,
  `CurrencyID1` smallint(6) NOT NULL default '0',
  `Salary2` decimal(20,2) default NULL,
  `CurrencyID2` smallint(6) NOT NULL default '0',
  `CommissionRate` decimal(14,2) default NULL,
  `StartDate` date default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `EmpID` (`EmpID`,`BranchID`),
  KEY `CurrencyID1` (`CurrencyID1`),
  KEY `CurrencyID2` (`CurrencyID2`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `PositionRecord_ibfk_1` FOREIGN KEY (`EmpID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `PositionRecord_ibfk_2` FOREIGN KEY (`CurrencyID1`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `PositionRecord_ibfk_3` FOREIGN KEY (`CurrencyID2`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `PositionRecord_ibfk_4` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Printer`
--

DROP TABLE IF EXISTS `Printer`;
CREATE TABLE `Printer` (
  `ID` smallint(6) NOT NULL default '0',
  `PrinterName` varchar(50) default NULL,
  `HostName` varchar(50) default NULL,
  `WinGroupName` varchar(50) default NULL,
  `UserName` varchar(50) default NULL,
  `Password` varchar(50) default NULL,
  `PrinterType` varchar(50) default NULL,
  `CommandLine` varchar(250) default NULL,
  `DefaultCommandLine` varchar(250) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `PrinterName` (`PrinterName`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `Printer_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `QuotDetail`
--

DROP TABLE IF EXISTS `QuotDetail`;
CREATE TABLE `QuotDetail` (
  `ID` int(11) NOT NULL default '0',
  `QuotJobID` varchar(20) NOT NULL default 'NA',
  `ChargeType` varchar(10) default NULL,
  `ItemID` smallint(6) NOT NULL default '0',
  `ItemName` varchar(50) default NULL,
  `ItemRate` decimal(14,2) default NULL,
  `ItemUnit` varchar(10) default NULL,
  `ItemAmount` double default '1',
  `CurrencyID` smallint(6) NOT NULL default '0',
  `ExchangeRate` double default NULL,
  `PartnerRateID` int(11) NOT NULL default '0',
  `LocalPartnerRateID` int(11) NOT NULL default '0',
  `FreightRateID` int(11) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `ItemID` (`ItemID`,`QuotJobID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `CurrencyID` (`CurrencyID`),
  KEY `QuotJobID` (`QuotJobID`,`BranchID`),
  KEY `LocalPartnerRateID` (`LocalPartnerRateID`,`BranchID`),
  KEY `PartnerRateID` (`PartnerRateID`),
  KEY `FreightRateID` (`FreightRateID`),
  CONSTRAINT `QuotDetail_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `QuotDetail_ibfk_2` FOREIGN KEY (`CurrencyID`) REFERENCES `xpcShared`.`Currency` (`ID`),
  CONSTRAINT `QuotDetail_ibfk_3` FOREIGN KEY (`QuotJobID`, `BranchID`) REFERENCES `QuotJob` (`ID`, `BranchID`),
  CONSTRAINT `QuotDetail_ibfk_4` FOREIGN KEY (`LocalPartnerRateID`, `BranchID`) REFERENCES `LocalPartnerRate` (`ID`, `BranchID`),
  CONSTRAINT `QuotDetail_ibfk_5` FOREIGN KEY (`PartnerRateID`) REFERENCES `xpcShared`.`PartnerRate` (`ID`),
  CONSTRAINT `QuotDetail_ibfk_6` FOREIGN KEY (`FreightRateID`) REFERENCES `xpcShared`.`VendorRate` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `QuotJob`
--

DROP TABLE IF EXISTS `QuotJob`;
CREATE TABLE `QuotJob` (
  `ID` varchar(20) NOT NULL default '',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `CusID` int(11) NOT NULL default '0',
  `ConsigneeID` int(11) NOT NULL default '0',
  `NotifyID` int(11) NOT NULL default '0',
  `JobDate` date default NULL,
  `ShipperSINo` varchar(50) default NULL,
  `Category` enum('AE','AI','SE','SI') default NULL,
  `ReceiptPlace` varchar(50) default NULL,
  `PlaceOfIssue` varchar(50) default NULL,
  `DeliveryPlace` varchar(50) default NULL,
  `FinalDest` varchar(50) default NULL,
  `LoadPortID` int(11) NOT NULL default '0',
  `DischargePortID` int(11) NOT NULL default '0',
  `PONo` varchar(50) default NULL,
  `PODDate` date default NULL,
  `DateOfIssue` date default NULL,
  `StuffDate` date default NULL,
  `SalesPersonID` smallint(6) NOT NULL default '0',
  `OperatorID` smallint(6) NOT NULL default '0',
  `ModifyDate` date default NULL,
  `IsFCL` char(1) default 'Y',
  `IsDeal` char(1) default 'N',
  `QuotStatusID` smallint(6) NOT NULL default '0',
  `Comment` text,
  `BranchID` smallint(6) NOT NULL default '0',
  `JobTypeID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `CusID` (`CusID`,`BranchID`),
  KEY `ConsigneeID` (`ConsigneeID`,`BranchID`),
  KEY `NotifyID` (`NotifyID`,`BranchID`),
  KEY `LoadPortID` (`LoadPortID`),
  KEY `DischargePortID` (`DischargePortID`),
  KEY `SalesPersonID` (`SalesPersonID`,`BranchID`),
  KEY `OperatorID` (`OperatorID`,`BranchID`),
  KEY `QuotStatusID` (`QuotStatusID`),
  KEY `JobTypeID` (`JobTypeID`),
  CONSTRAINT `QuotJob_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `QuotJob_ibfk_10` FOREIGN KEY (`QuotStatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `QuotJob_ibfk_11` FOREIGN KEY (`JobTypeID`) REFERENCES `xpcShared`.`JobType` (`ID`),
  CONSTRAINT `QuotJob_ibfk_2` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `QuotJob_ibfk_3` FOREIGN KEY (`CusID`, `BranchID`) REFERENCES `GeneralBody` (`ID`, `BranchID`),
  CONSTRAINT `QuotJob_ibfk_4` FOREIGN KEY (`ConsigneeID`, `BranchID`) REFERENCES `GeneralBody` (`ID`, `BranchID`),
  CONSTRAINT `QuotJob_ibfk_5` FOREIGN KEY (`NotifyID`, `BranchID`) REFERENCES `NotifyParty` (`ID`, `BranchID`),
  CONSTRAINT `QuotJob_ibfk_6` FOREIGN KEY (`LoadPortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `QuotJob_ibfk_7` FOREIGN KEY (`DischargePortID`) REFERENCES `xpcShared`.`Port` (`ID`),
  CONSTRAINT `QuotJob_ibfk_8` FOREIGN KEY (`SalesPersonID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`),
  CONSTRAINT `QuotJob_ibfk_9` FOREIGN KEY (`OperatorID`, `BranchID`) REFERENCES `Employee` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `QuotJobContainer`
--

DROP TABLE IF EXISTS `QuotJobContainer`;
CREATE TABLE `QuotJobContainer` (
  `ID` int(11) NOT NULL default '0',
  `QuotJobID` varchar(20) NOT NULL default 'NA',
  `ContainerTypeID` smallint(6) NOT NULL default '0',
  `ItemID` smallint(6) default '1',
  `Quantity` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `QuotJobID` (`QuotJobID`,`ContainerTypeID`,`ItemID`,`BranchID`),
  KEY `QuotJobID_2` (`QuotJobID`),
  KEY `BranchID` (`BranchID`),
  KEY `ContainerTypeID` (`ContainerTypeID`),
  CONSTRAINT `QuotJobContainer_ibfk_1` FOREIGN KEY (`QuotJobID`) REFERENCES `QuotJob` (`ID`),
  CONSTRAINT `QuotJobContainer_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `QuotJobContainer_ibfk_3` FOREIGN KEY (`ContainerTypeID`) REFERENCES `xpcShared`.`ContainerType` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `QuotJobFreight`
--

DROP TABLE IF EXISTS `QuotJobFreight`;
CREATE TABLE `QuotJobFreight` (
  `FreightID` int(11) NOT NULL default '0',
  `QuotJobID` varchar(20) NOT NULL default 'NA',
  `ItemID` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`FreightID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  KEY `QuotJobID` (`QuotJobID`,`BranchID`),
  KEY `FreightID` (`FreightID`,`BranchID`),
  CONSTRAINT `QuotJobFreight_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `QuotJobFreight_ibfk_2` FOREIGN KEY (`QuotJobID`, `BranchID`) REFERENCES `QuotJob` (`ID`, `BranchID`),
  CONSTRAINT `QuotJobFreight_ibfk_3` FOREIGN KEY (`FreightID`, `BranchID`) REFERENCES `Freight` (`ID`, `BranchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `RefJob`
--

DROP TABLE IF EXISTS `RefJob`;
CREATE TABLE `RefJob` (
  `ID` varchar(20) NOT NULL default '',
  `JobDate` date default NULL,
  `Category` enum('AE','AI','SE','SI','PE','PI') default NULL,
  `ModifyDate` date default NULL,
  `SelectedBookNo` smallint(6) default NULL,
  `ShipperID` int(11) NOT NULL default '0',
  `ReceiptPlace` varchar(50) default NULL,
  `PlaceOfIssue` varchar(50) default NULL,
  `DeliveryPlace` varchar(50) default NULL,
  `DeliveryPortID` int(11) NOT NULL default '0',
  `PONo` varchar(50) default NULL,
  `PODDate` date default NULL,
  `DateOfIssue` date default NULL,
  `StuffDate` date default NULL,
  `Attn` varchar(50) default NULL,
  `FinalDest` varchar(50) default NULL,
  `Comment` text,
  `JobStatusID` smallint(6) NOT NULL default '0',
  `Warehouse` varchar(255) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `ShipperID` (`ShipperID`),
  KEY `JobStatusID` (`JobStatusID`),
  KEY `BranchID` (`BranchID`),
  KEY `DeliveryPortID` (`DeliveryPortID`),
  CONSTRAINT `RefJob_ibfk_1` FOREIGN KEY (`ShipperID`) REFERENCES `GeneralBody` (`ID`),
  CONSTRAINT `RefJob_ibfk_2` FOREIGN KEY (`JobStatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `RefJob_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`),
  CONSTRAINT `RefJob_ibfk_4` FOREIGN KEY (`DeliveryPortID`) REFERENCES `xpcShared`.`Port` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `RefJobContainer`
--

DROP TABLE IF EXISTS `RefJobContainer`;
CREATE TABLE `RefJobContainer` (
  `ID` int(11) NOT NULL default '0',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `ContainerTypeID` smallint(6) NOT NULL default '0',
  `ContainerNo` varchar(50) default NULL,
  `SealNo` varchar(50) default NULL,
  `ContainerStatusID` smallint(6) NOT NULL default '0',
  `ContainerCount` smallint(6) default '0',
  `ItemID` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `RefJobID` (`RefJobID`,`ItemID`,`ContainerTypeID`,`BranchID`),
  KEY `RefJobID_2` (`RefJobID`,`BranchID`),
  KEY `ContainerTypeID` (`ContainerTypeID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `RefJobContainer_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `RefJobContainer_ibfk_2` FOREIGN KEY (`ContainerTypeID`) REFERENCES `xpcShared`.`ContainerType` (`ID`),
  CONSTRAINT `RefJobContainer_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `RefJobFreight`
--

DROP TABLE IF EXISTS `RefJobFreight`;
CREATE TABLE `RefJobFreight` (
  `FreightID` int(11) NOT NULL default '0',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `ItemID` smallint(6) default '1',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`FreightID`,`BranchID`),
  KEY `RefJobID` (`RefJobID`,`BranchID`),
  KEY `FreightID` (`FreightID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `RefJobFreight_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `RefJobFreight_ibfk_2` FOREIGN KEY (`FreightID`, `BranchID`) REFERENCES `Freight` (`ID`, `BranchID`),
  CONSTRAINT `RefJobFreight_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `RefJobTrack`
--

DROP TABLE IF EXISTS `RefJobTrack`;
CREATE TABLE `RefJobTrack` (
  `ID` int(11) NOT NULL default '0',
  `RefJobID` varchar(20) NOT NULL default 'NA',
  `Event` varchar(60) default NULL,
  `Place` varchar(30) default NULL,
  `Date` date default NULL,
  `Comments` text,
  `StatusID` smallint(6) NOT NULL default '0',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  KEY `RefJobID` (`RefJobID`,`BranchID`),
  KEY `StatusID` (`StatusID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `RefJobTrack_ibfk_1` FOREIGN KEY (`RefJobID`, `BranchID`) REFERENCES `RefJob` (`ID`, `BranchID`),
  CONSTRAINT `RefJobTrack_ibfk_2` FOREIGN KEY (`StatusID`) REFERENCES `xpcShared`.`StatusDefinition` (`ID`),
  CONSTRAINT `RefJobTrack_ibfk_3` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `SeaBookInfo`
--

DROP TABLE IF EXISTS `SeaBookInfo`;
CREATE TABLE `SeaBookInfo` (
  `BookDetailID` int(11) NOT NULL default '0',
  `PreCarriage` varchar(20) default NULL,
  `PreVoyage` varchar(20) default NULL,
  `Voyage` varchar(20) default NULL,
  `VoyageExTS` varchar(20) default NULL,
  `Vessel` varchar(20) default NULL,
  `VesselExTS` varchar(20) default NULL,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`BookDetailID`,`BranchID`),
  KEY `BookDetailID` (`BookDetailID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `SeaBookInfo_ibfk_1` FOREIGN KEY (`BookDetailID`, `BranchID`) REFERENCES `BookDetail` (`ID`, `BranchID`),
  CONSTRAINT `SeaBookInfo_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `SeaHouseBill`
--

DROP TABLE IF EXISTS `SeaHouseBill`;
CREATE TABLE `SeaHouseBill` (
  `ID` int(11) NOT NULL default '0',
  `ClientJobID` varchar(20) NOT NULL default 'NA',
  `HouseBillNo` varchar(20) default NULL,
  `IssueDate` date default NULL,
  `DetailNo` smallint(6) default NULL,
  `NoOfOrg` int(6) default NULL,
  `DocNo` varchar(20) default NULL,
  `ExpRefNo` varchar(20) default NULL,
  `SCACPrefix` varchar(4) default '',
  `Notify2Name` varchar(50) default NULL,
  `Notify2AddLine` varchar(50) default NULL,
  `Notify2City` varchar(30) default NULL,
  `Notify2Province` varchar(30) default NULL,
  `Notify2Country` varchar(30) default NULL,
  `ValueDecl` varchar(120) default NULL,
  `WordPacks` varchar(120) default NULL,
  `Charges` text,
  `Prepaid` text,
  `Collect` text,
  `Msg1` varchar(120) default NULL,
  `Msg2` varchar(120) default NULL,
  `Msg3` varchar(120) default NULL,
  `Segment` smallint(6) NOT NULL default '1',
  `Shipper` varchar(255) default NULL,
  `Consignee` varchar(255) default NULL,
  `DeliveryAgent` varchar(255) default NULL,
  `NotifyParty` varchar(255) default NULL,
  `DeliveryPlace` varchar(80) default NULL,
  `POLPlace` varchar(80) default NULL,
  `PODPlace` varchar(80) default NULL,
  `GrossWeight` float default NULL,
  `ChargeableWeight` float default NULL,
  `WeightUnit` varchar(6) default NULL,
  `FinalMeasure` float default NULL,
  `MeasureUnit` varchar(6) default NULL,
  `NoOfPack` int(11) default NULL,
  `PackUnit` varchar(32) default 'Piece',
  `ChargeRate` decimal(14,3) default NULL,
  `ChargeTotal` decimal(20,3) default NULL,
  `GoodsDescription` text,
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`ID`,`BranchID`),
  UNIQUE KEY `Segment` (`Segment`,`ClientJobID`,`BranchID`),
  UNIQUE KEY `HouseBillNo` (`HouseBillNo`,`BranchID`),
  KEY `ClientJobID` (`ClientJobID`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `SeaHouseBill_ibfk_1` FOREIGN KEY (`ClientJobID`, `BranchID`) REFERENCES `ClientJob` (`ID`, `BranchID`),
  CONSTRAINT `SeaHouseBill_ibfk_2` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `SysConfig`
--

DROP TABLE IF EXISTS `SysConfig`;
CREATE TABLE `SysConfig` (
  `KeyName` varchar(128) NOT NULL default '',
  `StringValue` varchar(128) default NULL,
  `TextValue` text,
  `IntValue` int(11) default '0',
  `FloatValue` decimal(8,2) default '0.00',
  `BranchID` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`KeyName`,`BranchID`),
  KEY `BranchID` (`BranchID`),
  CONSTRAINT `SysConfig_ibfk_1` FOREIGN KEY (`BranchID`) REFERENCES `xpcShared`.`Branch` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping routines for database 'xpcBranch'
--
DELIMITER ;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_AccountingUtil` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_AccountingUtil`(
IN upperCategory VARCHAR(20),
IN noteID VARCHAR(20),
IN curr VARCHAR(20),
IN branchID smallint)
AccountingUtil:BEGIN

SET @SearchTable="";
SET @ForeignKey="";
SET @AccountingUtil = "SELECT SUM(jJournal.Amount) AS Amount FROM JobNote AS jn,JobJournal AS jJournal,xpcShared.Account AS account,xpcShared.Currency AS currency,xpcShared.AccountGroup AS accountGroup,xpcShared.AccountType AS accountType,xpcShared.AccountCategory AS accountCategory";

SET @ForeignKey=CONCAT(@ForeignKey," jn.BranchID=",branchID," AND jn.ID=jJournal.JobNoteID AND jJournal.AccountID=account.ID AND account.CurrencyID=currency.ID AND account.GroupID=accountGroup.ID AND accountGroup.TypeID=accountType.ID AND accountType.CategoryID=accountCategory.ID ");

IF upperCategory IS NOT NULL THEN
SET @ForeignKey=CONCAT(@ForeignKey," AND jn.IsPosted='Y' AND accountCategory.CategoryCategory='",upperCategory,"'");
	IF !STRCMP(upperCategory, 'Revenue') THEN
		SET @ForeignKey=CONCAT(@ForeignKey," AND jJournal.DCType='Credit'");
	ELSEIF !STRCMP(upperCategory, 'Expense') THEN
		SET @ForeignKey=CONCAT(@ForeignKey," AND jJournal.DCType='Debit'");
	END IF;
END IF;

IF noteID IS NOT NULL THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.ID='",noteID,"'");
END IF;

IF curr IS NOT NULL THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND currency.ISOCode='",curr,"'");
END IF;
SET @AccountingUtil=CONCAT(@AccountingUtil,@SearchTable," WHERE",@ForeignKey);

PREPARE AccountingUtil FROM @AccountingUtil;
EXECUTE AccountingUtil;
END AccountingUtil */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_AUgetWC` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_AUgetWC`(
IN clientJobID VARCHAR(20),
IN refJobID VARCHAR(20),
IN branchID smallint)
AUgetWC:BEGIN
SET @AUgetWC="";

IF clientJobID IS NOT NULL && STRCMP(clientJobID, 'NA') THEN
	SET @AUgetWC = CONCAT(@AUgetWC,"SELECT fg.GrossWeight,fg.WeightUnit,fg.Measurement,fg.MeasureUnit FROM ClientJob AS cj,ClientJobFreight AS cjf,Freight AS fg WHERE cj.ID=cjf.ClientJobID AND cj.BranchID=cjf.BranchID AND cjf.FreightID=fg.ID AND cjf.BranchID=fg.BranchID AND cj.ID='",clientJobID,"' AND cj.BranchID=",branchID);
ELSEIF refJobID IS NOT NULL && STRCMP(refJobID, 'NA') THEN 
	SET @AUgetWC = CONCAT(@AUgetWC,"SELECT fg.GrossWeight,fg.WeightUnit,fg.Measurement,fg.MeasureUnit FROM RefJob AS rj,RefJobFreight AS rjf,Freight AS fg WHERE rj.ID=rjf.RefJobID AND rj.BranchID=rjf.BranchID AND rjf.FreightID=fg.ID AND rjf.BranchID=fg.BranchID AND rj.ID='",refJobID,"' AND rj.BranchID=",branchID);
END IF;
PREPARE AUgetWC FROM @AUgetWC;
EXECUTE AUgetWC;
END AUgetWC */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_JNReport` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_JNReport`(
IN jobCategory VARCHAR(20),
IN jobNoteNo VARCHAR(20),
IN refJobID VARCHAR(20),
IN clientJobID VARCHAR(20),
IN masterBillNo VARCHAR(20),
IN houseBillNo VARCHAR(20),
IN flightNo VARCHAR(20),
IN vessel VARCHAR(20),
IN shipper VARCHAR(20),
IN consignee VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN noteTypeID smallint,
IN branchID smallint,
OUT retID smallint)
JNReportEnd:BEGIN

SET retID=-1;

IF !STRCMP(jobCategory, '') THEN
SET jobCategory = NULL;
END IF;
IF !STRCMP(jobNoteNo, '') THEN
SET jobNoteNo = NULL;
END IF;
IF !STRCMP(refJobID, '') THEN
SET refJobID = NULL;
END IF;
IF !STRCMP(clientJobID, '') THEN
SET clientJobID = NULL;
END IF;
IF !STRCMP(masterBillNo, '') THEN
SET masterBillNo = NULL;
END IF;
IF !STRCMP(houseBillNo, '') THEN
SET houseBillNo = NULL;
END IF;
IF !STRCMP(flightNo, '') THEN
SET flightNo = NULL;
END IF;
IF !STRCMP(vessel, '') THEN
SET vessel = NULL;
END IF;
IF !STRCMP(shipper, '') THEN
SET shipper = NULL;
END IF;
IF !STRCMP(consignee, '') THEN
SET consignee = NULL;
END IF;

SET @SearchTable="";
SET @ForeignKey="";
SET @OrForeignKey=" (";
SET @JNREPORT = "SELECT jn.ID,jn.ClientJobID,jn.NoteNo,jn.RefjobID,jn.IsPosted,
jn.CostRateCategoryID,jn.Date,jn.ChargeTo,nt.TypeKey,nt.Name,nt.Category,jn.ChargeRateCategoryID FROM JobNote AS jn,xpcShared.NoteType AS nt";

SET @ForeignKey = CONCAT(@ForeignKey," jn.BranchID=",branchID," AND jn.Date >='",startDate,"' AND jn.Date <='",endDate,"' AND jn.NoteTypeID=nt.ID AND (nt.Category='ACTR' OR nt.Category = 'ACTP')");
IF noteTypeID IS NOT NULL && noteTypeID<>0  THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.NoteTypeID=",noteTypeID);
END IF;
IF !STRCMP(jobCategory, 'AE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AE%' OR jn.RefJobID  LIKE 'AE%'  OR jn.RefJobID  LIKE 'CAE%')");
ELSEIF !STRCMP(jobCategory, 'AI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AI%' OR jn.RefJobID  LIKE 'AI%'  OR jn.RefJobID  LIKE 'CAI%')");
ELSEIF !STRCMP(jobCategory, 'SE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SE%' OR jn.RefJobID  LIKE 'SE%'  OR jn.RefJobID  LIKE 'CSE%')");
ELSEIF !STRCMP(jobCategory, 'SI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SI%' OR jn.RefJobID  LIKE 'SI%'  OR jn.RefJobID  LIKE 'CSI%')");
ELSE
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID <> 'NA' OR jn.RefJobID  <> 'NA')");
END IF;

IF jobNoteNo IS NOT NULL  THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.NoteNo LIKE '",jobNoteNo,"%'");
END IF;

IF refJobID IS NOT NULL THEN
IF INSTR(@OrForeignKey, '=') > 0 THEN
	SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
END IF;
SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj WHERE cj.BranchID =",branchID," AND cj.RefJobID LIKE '",refJobID,"%' )");
END IF;

IF clientJobID IS NOT NULL THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.ClientJobID LIKE '",clientJobID,"%'");
END IF;

IF masterBillNo IS NOT NULL THEN
IF INSTR(@OrForeignKey, '=') > 0 THEN
	SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
END IF;
SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,ClientJob AS cj WHERE  bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = ",branchID," AND bd.MasterBillID LIKE '",masterBillNo,"%' )");
END IF;

IF houseBillNo IS NOT NULL THEN
	IF !STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'AI')  THEN
		IF INSTR(@OrForeignKey, '=') > 0 THEN
			SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
		END IF;
		SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT ah.ClientJobID FROM AirHouseBill AS ah WHERE ah.BranchID=jn.BranchID AND ah.HouseBillNo LIKE '",houseBillNo,"%' )");
	ELSEIF  !STRCMP(jobCategory, 'SE') || !STRCMP(jobCategory, 'SI') THEN
		IF INSTR(@OrForeignKey, '=') > 0 THEN
			SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
		END IF;
		SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT sh.ClientJobID FROM SeaHouseBill AS sh WHERE sh.BranchID=jn.BranchID AND sh.HouseBillNo LIKE '",houseBillNo,"%' )");
	ELSE
		IF INSTR(@OrForeignKey, '=') > 0 THEN
			SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
		END IF;
		SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT ah.ClientJobID FROM AirHouseBill AS ah WHERE ah.BranchID=jn.BranchID AND ah.HouseBillNo LIKE '",houseBillNo,"%' )");
		SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT sh.ClientJobID FROM SeaHouseBill AS sh WHERE sh.BranchID=jn.BranchID AND sh.HouseBillNo LIKE '",houseBillNo,"%' )");
	END IF;
END IF;

IF flightNo IS NOT NULL && (!STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'AI')) THEN
	IF INSTR(@OrForeignKey, '=') > 0 THEN
		SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
	END IF;
	SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,AirBookInfo AS ab,ClientJob AS cj WHERE bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = ab.BranchID AND bd.ID=ab.BookDetailID AND ab.FlightNo LIKE '",flightNo,"%')");
END IF;

IF vessel IS NOT NULL && (!STRCMP(jobCategory, 'SE') || !STRCMP(jobCategory, 'SI')) THEN
	IF INSTR(@OrForeignKey, '=') > 0 THEN
		SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
	END IF;
	SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,SeaBookInfo AS sb,ClientJob AS cj WHERE bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = sb.BranchID AND bd.ID=sb.BookDetailID AND sb.Vessel LIKE '",vessel,"%')");
END IF;

IF shipper IS NOT NULL && (!STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'SE'))  THEN
	IF INSTR(@OrForeignKey, '=') > 0 THEN
		SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
	END IF;
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj,GeneralBody AS gb,Customer AS ct WHERE  cj.BranchID = gb.BranchID AND gb.BranchID=ct.BranchID AND cj.CusID=gb.ID AND gb.CustomerID = ct.ID AND cj.BranchID = ",branchID," AND ct.ShortName LIKE '",shipper,"%')");
END IF;

IF consignee IS NOT NULL && (!STRCMP(jobCategory, 'AI') || !STRCMP(jobCategory, 'SI')) THEN
	IF INSTR(@OrForeignKey, '=') > 0 THEN
		SET @OrForeignKey=CONCAT(@OrForeignKey," OR ");
	END IF;
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj,GeneralBody AS gb,Customer AS ct WHERE  cj.BranchID = gb.BranchID AND gb.BranchID=ct.BranchID AND cj.ConsigneeID=gb.ID AND gb.CustomerID = ct.ID AND cj.BranchID = ",branchID," AND ct.ShortName LIKE '",consignee,"%') ");
END IF;
SET @ForeignKey = CONCAT(@ForeignKey," ORDER BY jn.ID DESC");

IF INSTR(@OrForeignKey, '=') > 0 THEN
	SET @JNREPORT=CONCAT(@JNREPORT,@SearchTable," WHERE",@OrForeignKey,") AND ",@ForeignKey);
ELSE 
	SET @JNREPORT=CONCAT(@JNREPORT,@SearchTable," WHERE",@ForeignKey);
END IF;

PREPARE JNREPORT FROM @JNREPORT;
EXECUTE JNREPORT;
END JNReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_ListJobNote` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_ListJobNote`(
IN jobCategory VARCHAR(20),
IN jobNoteNo VARCHAR(20),
IN refJobID VARCHAR(20),
IN clientJobID VARCHAR(20),
IN masterBillNo VARCHAR(20),
IN houseBillNo VARCHAR(20),
IN flightNo VARCHAR(20),
IN vessel VARCHAR(20),
IN shipper VARCHAR(20),
IN consignee VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN noteTypeID smallint,
IN offset smallint,
IN rowcount smallint,
IN branchID smallint,
OUT retID smallint)
JNReportEnd:BEGIN

SET retID=-1;


SET @SearchTable="";
SET @ForeignKey="";
SET @OrForeignKey=" (";
SET @JNREPORT = "SELECT jn.ID,jn.ClientJobID,jn.NoteNo,jn.RefjobID,jn.IsPosted,jn.CostRateCategoryID,jn.Date,jn.ChargeTo,nt.TypeKey,nt.Name,
nt.Category,jn.ChargeRateCategoryID FROM JobNote AS jn,xpcShared.NoteType AS nt";
SET @JNCOUNT = "SELECT COUNT(jn.ID) AS JnCount FROM JobNote AS jn,xpcShared.NoteType AS nt";

IF refJobID IS NULL && masterBillNo IS NULL && houseBillNo IS NULL && flightNo IS NULL && vessel IS NULL && shipper IS NULL && consignee IS NULL THEN
	SET @ForeignKey = CONCAT(@ForeignKey," jn.BranchID=",branchID," AND jn.Date >='",startDate,"' AND jn.Date <='",endDate,"' AND jn.NoteTypeID=nt.ID AND (nt.Category='ACTR' OR nt.Category = 'ACTP')");
ELSE 
	SET @ForeignKey = CONCAT(@ForeignKey," AND jn.BranchID=",branchID," AND jn.Date >='",startDate,"' AND jn.Date <='",endDate,"' AND jn.NoteTypeID=nt.ID AND (nt.Category='ACTR' OR nt.Category = 'ACTP')");
END IF;
IF noteTypeID IS NOT NULL && noteTypeID<>0  THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.NoteTypeID=",noteTypeID);
END IF;
IF !STRCMP(jobCategory, 'AE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AE%' OR jn.RefJobID  LIKE 'AE%'  OR jn.RefJobID  LIKE 'CAE%')");
ELSEIF !STRCMP(jobCategory, 'AI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AI%' OR jn.RefJobID  LIKE 'AI%'  OR jn.RefJobID  LIKE 'CAI%')");
ELSEIF !STRCMP(jobCategory, 'SE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SE%' OR jn.RefJobID  LIKE 'SE%'  OR jn.RefJobID  LIKE 'CSE%')");
ELSEIF !STRCMP(jobCategory, 'SI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SI%' OR jn.RefJobID  LIKE 'SI%'  OR jn.RefJobID  LIKE 'CSI%')");
ELSE
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID <> 'NA' OR jn.RefJobID  <> 'NA')");
END IF;

IF jobNoteNo IS NOT NULL  THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.NoteNo LIKE '",jobNoteNo,"%'");
END IF;

IF refJobID IS NOT NULL  THEN
SET @OrForeignKey = CONCAT(@OrForeignKey," jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj WHERE cj.BranchID =",branchID," AND cj.RefJobID LIKE '",refJobID,"%' )");
END IF;

IF clientJobID IS NOT NULL  THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND jn.ClientJobID LIKE '",clientJobID,"%'");
END IF;

IF masterBillNo IS NOT NULL  THEN
SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,ClientJob AS cj WHERE  bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = ",branchID," AND bd.MasterBillID LIKE '",masterBillNo,"%' )");
END IF;

IF houseBillNo IS NOT NULL  THEN
	IF !STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'AI')  THEN
		SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT ah.ClientJobID FROM AirHouseBill AS ah WHERE ah.BranchID=jn.BranchID AND ah.HouseBillNo LIKE '",houseBillNo,"%' )");
	ELSEIF  !STRCMP(jobCategory, 'SE') || !STRCMP(jobCategory, 'SI') THEN
		SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT sh.ClientJobID FROM SeaHouseBill AS sh WHERE sh.BranchID=jn.BranchID AND sh.HouseBillNo LIKE '",houseBillNo,"%' )");
	ELSE
		SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT ah.ClientJobID FROM AirHouseBill AS ah WHERE ah.BranchID=jn.BranchID AND ah.HouseBillNo LIKE '",houseBillNo,"%' )");
		SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT sh.ClientJobID FROM SeaHouseBill AS sh WHERE sh.BranchID=jn.BranchID AND sh.HouseBillNo LIKE '",houseBillNo,"%' )");
	END IF;
END IF;

IF flightNo IS NOT NULL && (!STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'AI')) THEN
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,AirBookInfo AS ab,ClientJob AS cj WHERE bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = ab.BranchID AND bd.ID=ab.BookDetailID AND ab.FlightNo LIKE '",flightNo,"%')");
END IF;

IF vessel IS NOT NULL && (!STRCMP(jobCategory, 'SE') || !STRCMP(jobCategory, 'SI')) THEN
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM BookDetail AS bd,SeaBookInfo AS sb,ClientJob AS cj WHERE bd.BranchID = cj.BranchID AND bd.RefJobID=cj.RefJobID AND bd.BranchID = sb.BranchID AND bd.ID=sb.BookDetailID AND sb.Vessel LIKE '",vessel,"%')");
END IF;

IF shipper IS NOT NULL && (!STRCMP(jobCategory, 'AE') || !STRCMP(jobCategory, 'SE'))  THEN
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj,GeneralBody AS gb,Customer AS ct WHERE  cj.BranchID = gb.BranchID AND gb.BranchID=ct.BranchID AND cj.CusID=gb.ID AND gb.CustomerID = ct.ID AND cj.BranchID = ",branchID," AND ct.ShortName LIKE '",shipper,"%')");
END IF;

IF consignee IS NOT NULL && (!STRCMP(jobCategory, 'AI') || !STRCMP(jobCategory, 'SI')) THEN
	SET @OrForeignKey = CONCAT(@OrForeignKey," OR jn.ClientJobID = ANY (SELECT cj.ID FROM ClientJob AS cj,GeneralBody AS gb,Customer AS ct WHERE  cj.BranchID = gb.BranchID AND gb.BranchID=ct.BranchID AND cj.ConsigneeID=gb.ID AND gb.CustomerID = ct.ID AND cj.BranchID = ",branchID," AND ct.ShortName LIKE '",consignee,"%')");
END IF;
IF refJobID IS NULL && masterBillNo IS NULL && houseBillNo IS NULL && flightNo IS NULL && vessel IS NULL && shipper IS NULL && consignee IS NULL THEN
	SET @JNCOUNT=CONCAT(@JNCOUNT,@SearchTable," WHERE",@ForeignKey);
	SET @JNREPORT=CONCAT(@JNREPORT,@SearchTable," WHERE",@ForeignKey);
ELSE 
	SET @JNCOUNT=CONCAT(@JNCOUNT,@SearchTable," WHERE",@OrForeignKey,")",@ForeignKey);
	SET @JNREPORT=CONCAT(@JNREPORT,@SearchTable," WHERE",@OrForeignKey,")",@ForeignKey);
END IF;



IF rowcount > 0 THEN
SET @JNREPORT=CONCAT(@JNREPORT," LIMIT ?,?");
PREPARE JNREPORT FROM @JNREPORT;
SET @offset = offset;
SET @rowcount = rowcount;
EXECUTE JNREPORT using @offset,@rowcount;
ELSE 
PREPARE JNCOUNT FROM @JNCOUNT;
EXECUTE JNCOUNT;
END IF;
END JNReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_Sales` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_Sales`(
IN jobCategory VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN branchID smallint)
SalesReportEnd:BEGIN

SET @Retrieve="rj.ID,rj.JobDate,bd.JobTypeID,bd.MasterBillID";
SET @SearchTable="RefJob AS rj,BookDetail AS bd";
SET @ForeignKey="bd.RefJobID=rj.ID AND bd.BranchID=rj.BranchID AND bd.RequestNo=rj.SelectedBookNo";

SET @ForeignKey=CONCAT(@ForeignKey," AND rj.BranchID=",branchID);
SET @ForeignKey = CONCAT(@ForeignKey," AND rj.JobDate >='",startDate,"' AND rj.JobDate <='",endDate,"'");
IF jobCategory IS NOT NULL && STRCMP(jobCategory, 'All') && STRCMP(jobCategory, '') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND rj.Category='",jobCategory,"'");
END IF;
SET @SALES=CONCAT("SELECT ",@Retrieve," FROM ",@SearchTable," WHERE ",@ForeignKey);
PREPARE SALES FROM @SALES;
EXECUTE SALES;
END SalesReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_SalesJobNote` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_SalesJobNote`(
IN jobCategory VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN branchID smallint,
IN refJobID VARCHAR(20))
SalesReportEnd:BEGIN

SET @Retrieve="jn.NoteNo,jn.ClientJobID,jn.RefJobID";
SET @SearchTable="JobNote AS jn,JobJournal AS jJournal,xpcShared.Account AS account,xpcShared.Currency AS currency,xpcShared.AccountGroup AS accountGroup,xpcShared.AccountType AS accountType,xpcShared.AccountCategory AS accountCategory";
SET @ForeignKey="jn.IsPosted='Y' AND account.GroupID=accountGroup.ID AND account.CurrencyID=currency.ID AND accountGroup.TypeID=accountType.ID AND accountType.CategoryID=accountCategory.ID AND jJournal.AccountID=account.ID AND jn.ID=jJournal.JobNoteID AND jn.BranchID=jJournal.BranchID AND jJournal.DCType='Credit' AND accountCategory.CategoryCategory='Revenue'";

SET @ForeignKey=CONCAT(@ForeignKey," AND jn.BranchID=",branchID);

IF !STRCMP(jobCategory, 'AE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AE%' OR jn.RefJobID  LIKE 'AE%'  OR jn.RefJobID  LIKE 'CAE%')");
ELSEIF !STRCMP(jobCategory, 'AI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AI%' OR jn.RefJobID  LIKE 'AI%'  OR jn.RefJobID  LIKE 'CAI%')");
ELSEIF !STRCMP(jobCategory, 'SE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SE%' OR jn.RefJobID  LIKE 'SE%'  OR jn.RefJobID  LIKE 'CSE%')");
ELSEIF !STRCMP(jobCategory, 'SI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SI%' OR jn.RefJobID  LIKE 'SI%'  OR jn.RefJobID  LIKE 'CSI%')");
END IF;
SET @ForeignKey=CONCAT(@ForeignKey," AND (jn.ClientJobID IN (SELECT ID FROM ClientJob WHERE RefJobID ='",refJobID,"') OR jn.RefJobID = '",refJobID,"')");
SET @SALESJOBNOTE=CONCAT("SELECT ",@Retrieve," FROM ",@SearchTable," WHERE ",@ForeignKey," GROUP BY jn.ID ORDER BY jn.Date");

PREPARE SALESJOBNOTE FROM @SALESJOBNOTE;
EXECUTE SALESJOBNOTE;
END SalesReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_SalesReport` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_SalesReport`(
IN jobCategory VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN branchID smallint,
IN tbType VARCHAR(20))
SalesReportEnd:BEGIN

SET @SearchTable="";
SET @ForeignKey="";
SET @SALESREPORT = "SELECT jn.ID,jn.NoteNo,jn.CustomerID,jn.PartnerID,customer.ShortName,partner.ShortName,jn.ClientJobID,
jn.RefJobID,jn.Date,cj.JobDate,rj.JobDate,bd.JobTypeID,bd.MasterBillID,hb.HouseBillNo FROM JobNote AS jn,JobJournal AS jJournal,ClientJob AS cj,RefJob AS rj,BookDetail AS bd,Customer AS customer,xpcShared.Partner AS partner,xpcShared.Account AS account,xpcShared.Currency AS currency,xpcShared.AccountGroup AS accountGroup,xpcShared.AccountType AS accountType,xpcShared.AccountCategory AS accountCategory";
SET @ForeignKey=CONCAT(@ForeignKey," jn.BranchID=",branchID," AND jn.CustomerID=customer.ID AND jn.BranchID=customer.BranchID AND jn.ClientJobID=cj.ID AND jn.BranchID=cj.BranchID AND jn.RefJobID=rj.ID AND jn.BranchID=rj.BranchID AND jn.PartnerID=partner.ID AND bd.RefJobID=rj.ID AND bd.BranchID=rj.BranchID AND bd.RequestNo=rj.SelectedBookNo AND jn.IsPosted='Y' AND account.GroupID=accountGroup.ID AND account.CurrencyID=currency.ID AND accountGroup.TypeID=accountType.ID AND accountType.CategoryID=accountCategory.ID AND jJournal.AccountID=account.ID AND jn.ID=jJournal.JobNoteID AND jn.BranchID=jJournal.BranchID AND jJournal.DCType='Credit' AND accountCategory.CategoryCategory='Revenue'");

SET @ForeignKey = CONCAT(@ForeignKey," AND jn.BranchID=",branchID);
IF !STRCMP(tbType, 'byJobDate') THEN
	SET @ForeignKey = CONCAT(@ForeignKey," AND (rj.JobDate >='",startDate,"' OR cj.JobDate >='",
startDate,"' ) AND (rj.JobDate <='",endDate,"' OR cj.JobDate <='",endDate,"' )");
ELSEIF !STRCMP(tbType, 'byPostingDate') THEN
	SET @ForeignKey = CONCAT(@ForeignKey," AND jn.PostDate >='",startDate,"' AND jn.PostDate <='",endDate,"' ");
ELSE 
	SET @ForeignKey = CONCAT(@ForeignKey," AND jn.Date >='",startDate,"' AND jn.Date <='",endDate,"' ");
END IF;
IF !STRCMP(jobCategory, 'SE') || !STRCMP(jobCategory, 'SI') THEN
	SET @SearchTable = CONCAT(@SearchTable," ,SeaHouseBill AS hb");
	SET @ForeignKey = CONCAT(@ForeignKey," AND jn.BranchID=hb.BranchID AND jn.ClientJobID=hb.ClientJobID");
ELSE 
	SET @SearchTable = CONCAT(@SearchTable," ,AirHouseBill AS hb");
	SET @ForeignKey = CONCAT(@ForeignKey," AND jn.BranchID=hb.BranchID AND jn.ClientJobID=hb.ClientJobID");	
END IF;
IF !STRCMP(jobCategory, 'AE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AE%' OR jn.RefJobID  LIKE 'AE%'  OR jn.RefJobID  LIKE 'CAE%')");
ELSEIF !STRCMP(jobCategory, 'AI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'AI%' OR jn.RefJobID  LIKE 'AI%'  OR jn.RefJobID  LIKE 'CAI%')");
ELSEIF !STRCMP(jobCategory, 'SE') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SE%' OR jn.RefJobID  LIKE 'SE%'  OR jn.RefJobID  LIKE 'CSE%')");
ELSEIF !STRCMP(jobCategory, 'SI') THEN
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID LIKE 'SI%' OR jn.RefJobID  LIKE 'SI%'  OR jn.RefJobID  LIKE 'CSI%')");
ELSE
SET @ForeignKey = CONCAT(@ForeignKey," AND (jn.ClientJobID <> 'NA' OR jn.RefJobID  <> 'NA')");
END IF;
SET @ForeignKey=CONCAT(@ForeignKey," GROUP BY jn.ID, jJournal.JobNoteID");
SET @SALESREPORT=CONCAT(@SALESREPORT,@SearchTable," WHERE",@ForeignKey);



PREPARE SALESREPORT FROM @SALESREPORT;
EXECUTE SALESREPORT;
END SalesReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `xpcBranch_SalesTest` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`dasuser`@`localhost`*/ /*!50003 PROCEDURE `xpcBranch_SalesTest`(
IN jobCategory VARCHAR(20),
IN startDate VARCHAR(20),
IN endDate VARCHAR(20),
IN branchID smallint,
IN refJobID VARCHAR(20))
SalesReportEnd:BEGIN

SET @Retrieve="rj.ID,rj.JobDate,bd.JobTypeID,bd.MasterBillID";
SET @SearchTable="RefJob AS rj,BookDetail AS bd";
SET @ForeignKey="bd.RefJobID=rj.ID AND bd.BranchID=rj.BranchID AND bd.RequestNo=rj.SelectedBookNo";

SET @ForeignKey=CONCAT(@ForeignKey," AND rj.BranchID=",branchID);
SET @ForeignKey = CONCAT(@ForeignKey," AND rj.JobDate >='",startDate,"' AND rj.JobDate <='",endDate,"'");
SET @SALESREPORT=CONCAT("SELECT ",@Retrieve," FROM ",@SearchTable," WHERE ",@ForeignKey);
SET @SALESBR=CONCAT("SELECT NoteNo FROM JobNote WHERE ClientJobID IN ","(SELECT ID FROM ClientJob WHERE RefJobID = ANY (SELECT rj.ID FROM ",@SearchTable," WHERE ",@ForeignKey,") OR RefJobID IN (SELECT rj.ID FROM ",@SearchTable," WHERE ",@ForeignKey,") )");
SELECT @SALESBR;

END SalesReportEnd */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
DELIMITER ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-06-11 23:45:52
