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
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountCategory`
--

LOCK TABLES `AccountCategory` WRITE;
/*!40000 ALTER TABLE `AccountCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccountCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountExtension`
--

LOCK TABLES `AccountExtension` WRITE;
/*!40000 ALTER TABLE `AccountExtension` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccountExtension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountGroup`
--

LOCK TABLES `AccountGroup` WRITE;
/*!40000 ALTER TABLE `AccountGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccountGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountType`
--

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Career`
--

LOCK TABLES `Career` WRITE;
/*!40000 ALTER TABLE `Career` DISABLE KEYS */;
INSERT INTO `Career` VALUES (1,'医生'),(2,'教师'),(3,'企业家');
/*!40000 ALTER TABLE `Career` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES (1,1,'CD','ChengDu','ChengDu'),(2,1,'SH','ShangHai','ShangHai'),(3,1,'CQ','ChongQing','ChongQing');
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CodeType`
--

LOCK TABLES `CodeType` WRITE;
/*!40000 ALTER TABLE `CodeType` DISABLE KEYS */;
INSERT INTO `CodeType` VALUES (0,'NULL',NULL),(1,'文件类型','Linear'),(2,'论坛类型','Linear');
/*!40000 ALTER TABLE `CodeType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Country`
--

LOCK TABLES `Country` WRITE;
/*!40000 ALTER TABLE `Country` DISABLE KEYS */;
INSERT INTO `Country` VALUES (1,'CN','CHINA','CHINA'),(2,'CA','CANADA','CANADA'),(3,'USA','USA','USA'),(4,'ID','INDONESIA','INDONESIA');
/*!40000 ALTER TABLE `Country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Currency`
--

LOCK TABLES `Currency` WRITE;
/*!40000 ALTER TABLE `Currency` DISABLE KEYS */;
INSERT INTO `Currency` VALUES (1,'USD',3,'US Dollar','$'),(2,'RMB',1,'Reminbi','¥'),(3,'CND',2,'Canadian Dollar','$');
/*!40000 ALTER TABLE `Currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Education`
--

LOCK TABLES `Education` WRITE;
/*!40000 ALTER TABLE `Education` DISABLE KEYS */;
INSERT INTO `Education` VALUES (1,'文盲'),(2,'小学'),(3,'初中'),(4,'高中'),(5,'大专'),(6,'本科'),(7,'硕士'),(8,'研究生'),(9,'博士'),(10,'专家'),(11,'科学家');
/*!40000 ALTER TABLE `Education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `HeightUnit`
--

LOCK TABLES `HeightUnit` WRITE;
/*!40000 ALTER TABLE `HeightUnit` DISABLE KEYS */;
INSERT INTO `HeightUnit` VALUES (1,'cm');
/*!40000 ALTER TABLE `HeightUnit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Language`
--

LOCK TABLES `Language` WRITE;
/*!40000 ALTER TABLE `Language` DISABLE KEYS */;
INSERT INTO `Language` VALUES (1,'CN','Chinese','Chinese Language'),(2,'CA','English','Caina English'),(3,'USA','English','USA English'),(4,'ID','English','Indonesia Enilsh');
/*!40000 ALTER TABLE `Language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `LinearCode`
--

LOCK TABLES `LinearCode` WRITE;
/*!40000 ALTER TABLE `LinearCode` DISABLE KEYS */;
INSERT INTO `LinearCode` VALUES (3,1,'mp3'),(0,0,'NULL'),(4,2,'军事'),(2,1,'压缩'),(1,1,'图片'),(7,2,'技术联盟'),(5,2,'文艺');
/*!40000 ALTER TABLE `LinearCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Married`
--

LOCK TABLES `Married` WRITE;
/*!40000 ALTER TABLE `Married` DISABLE KEYS */;
INSERT INTO `Married` VALUES (1,'单身'),(2,'已婚'),(3,'离异');
/*!40000 ALTER TABLE `Married` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `NoteType`
--

LOCK TABLES `NoteType` WRITE;
/*!40000 ALTER TABLE `NoteType` DISABLE KEYS */;
INSERT INTO `NoteType` VALUES (1,'IV','Debit','Invoice','发票'),(2,'RC','Debit','Receipt','收据'),(3,'TS','Debit','Transfer','转帐'),(4,'CN','Credit','Credit Note','贷方票据'),(5,'DN','Debit','Debit Note','借方票据'),(6,'NN','','','无');
/*!40000 ALTER TABLE `NoteType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OperatorUser`
--

LOCK TABLES `OperatorUser` WRITE;
/*!40000 ALTER TABLE `OperatorUser` DISABLE KEYS */;
/*!40000 ALTER TABLE `OperatorUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Sex`
--

LOCK TABLES `Sex` WRITE;
/*!40000 ALTER TABLE `Sex` DISABLE KEYS */;
INSERT INTO `Sex` VALUES (1,'男'),(2,'女'),(3,'保密'),(4,'其他');
/*!40000 ALTER TABLE `Sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `State`
--

LOCK TABLES `State` WRITE;
/*!40000 ALTER TABLE `State` DISABLE KEYS */;
INSERT INTO `State` VALUES (0,'Root','Root','root state'),(1,'Common','common','common state');
/*!40000 ALTER TABLE `State` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `WeightUnit`
--

LOCK TABLES `WeightUnit` WRITE;
/*!40000 ALTER TABLE `WeightUnit` DISABLE KEYS */;
INSERT INTO `WeightUnit` VALUES (1,'KG');
/*!40000 ALTER TABLE `WeightUnit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-11-09 13:08:34
