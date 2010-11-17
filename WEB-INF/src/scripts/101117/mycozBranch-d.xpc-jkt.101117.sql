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
  `userId` int(11) DEFAULT NULL,
  `countryId` int(11) DEFAULT NULL,
  `languageId` int(11) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `postalCode` varchar(20) DEFAULT NULL,
  `tel` varchar(60) DEFAULT NULL,
  `mobileNo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `countryId` (`countryId`),
  KEY `cityId` (`cityId`),
  KEY `languageId` (`languageId`),
  KEY `address` (`address`),
  KEY `postalCode` (`postalCode`),
  KEY `tel` (`tel`),
  KEY `mobileNo` (`mobileNo`),
  CONSTRAINT `AddressBook_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `AddressBook_ibfk_2` FOREIGN KEY (`countryId`) REFERENCES `mycozShared`.`Country` (`ID`),
  CONSTRAINT `AddressBook_ibfk_3` FOREIGN KEY (`languageId`) REFERENCES `mycozShared`.`Language` (`ID`),
  CONSTRAINT `AddressBook_ibfk_4` FOREIGN KEY (`cityId`) REFERENCES `mycozShared`.`City` (`ID`)
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
  `name` varchar(100) DEFAULT 'NULL',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filepath` varchar(200) DEFAULT NULL,
  `folder` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `dateTime` (`datetime`),
  KEY `filepath` (`filepath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FileTree`
--

DROP TABLE IF EXISTS `FileTree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FileTree` (
  `id` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) DEFAULT NULL,
  `childId` int(11) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_id` (`parentId`,`childId`),
  KEY `parentId` (`parentId`),
  KEY `childId` (`childId`),
  KEY `levelId` (`levelId`),
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
  `name` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
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
  `typeId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT 'NULL',
  `body` text,
  `modifiedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creationDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `approved` char(1) NOT NULL DEFAULT 'N',
  `ranking` int(11) DEFAULT '0',
  `closed` char(1) NOT NULL DEFAULT 'N',
  `replyPrivateUserId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `subject` (`subject`),
  KEY `modifiedDate` (`modifiedDate`),
  KEY `creationDate` (`creationDate`),
  KEY `ranking` (`ranking`),
  KEY `replyPrivateUserId` (`replyPrivateUserId`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `ForumThread_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `ThreadType` (`id`),
  CONSTRAINT `ForumThread_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `ForumThread_ibfk_3` FOREIGN KEY (`replyPrivateUserId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GroupMember`
--

DROP TABLE IF EXISTS `GroupMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupMember` (
  `id` int(11) NOT NULL DEFAULT '0',
  `groupId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_gu` (`groupId`,`userId`),
  KEY `groupId` (`groupId`),
  KEY `userId` (`userId`),
  CONSTRAINT `GroupMember_ibfk_1` FOREIGN KEY (`groupId`) REFERENCES `mycozShared`.`UserGroup` (`id`),
  CONSTRAINT `GroupMember_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
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
  `threadId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
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
  `userId` int(11) DEFAULT NULL,
  `transaction` varchar(50) DEFAULT 'NULL',
  `propertyStatus` int(11) DEFAULT NULL,
  `propertyType` int(11) DEFAULT NULL,
  `offer` decimal(25,4) DEFAULT NULL,
  `bedrooms` int(11) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `bathroom` int(11) DEFAULT NULL,
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
  CONSTRAINT `Property_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoleMember`
--

DROP TABLE IF EXISTS `RoleMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoleMember` (
  `id` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ru` (`roleId`,`userId`),
  KEY `roleId` (`roleId`),
  KEY `userId` (`userId`),
  CONSTRAINT `RoleMember_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `mycozShared`.`UserRole` (`id`),
  CONSTRAINT `RoleMember_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ThreadType`
--

DROP TABLE IF EXISTS `ThreadType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ThreadType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `forumId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `forumId` (`forumId`),
  CONSTRAINT `ThreadType_ibfk_1` FOREIGN KEY (`forumId`) REFERENCES `Forum` (`id`)
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
-- Table structure for table `UserImage`
--

DROP TABLE IF EXISTS `UserImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserImage` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `filepath` (`filepath`),
  CONSTRAINT `UserImage_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
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
  `email` varchar(50) DEFAULT NULL,
  `joinTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `email` (`email`),
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
  KEY `joinTime` (`joinTime`),
  CONSTRAINT `UserInfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `UserInfo_ibfk_2` FOREIGN KEY (`sexId`) REFERENCES `mycozShared`.`Sex` (`id`),
  CONSTRAINT `UserInfo_ibfk_3` FOREIGN KEY (`heightUnitId`) REFERENCES `mycozShared`.`HeightUnit` (`id`),
  CONSTRAINT `UserInfo_ibfk_4` FOREIGN KEY (`weightUnitId`) REFERENCES `mycozShared`.`WeightUnit` (`id`),
  CONSTRAINT `UserInfo_ibfk_5` FOREIGN KEY (`careerId`) REFERENCES `mycozShared`.`Career` (`id`),
  CONSTRAINT `UserInfo_ibfk_6` FOREIGN KEY (`educationId`) REFERENCES `mycozShared`.`Education` (`id`),
  CONSTRAINT `UserInfo_ibfk_7` FOREIGN KEY (`marriedId`) REFERENCES `mycozShared`.`Married` (`id`)
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

-- Dump completed on 2010-11-17 16:53:16
