-- MySQL dump 10.13  Distrib 5.1.47, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: mycozShared
-- ------------------------------------------------------
-- Server version	5.1.47

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
INSERT INTO `Account` VALUES (1,'转入帐号(RMB)',3,1,2,''),(2,'袁利工资',6,1,2,''),(3,'雷智工资',6,1,2,''),(4,'腾玉波',5,1,2,''),(5,'袁有德',5,1,2,''),(6,'房租',7,1,1,''),(7,'应付杂费',9,1,2,''),(8,'物流软件',15,1,2,''),(9,'在线购物系统',15,1,2,''),(10,'个人日志系统',15,1,2,''),(11,'电脑耗材',16,1,2,''),(12,'袁利现金',17,1,2,''),(13,'雷智现金',1,1,2,'');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountCategory`
--

LOCK TABLES `AccountCategory` WRITE;
/*!40000 ALTER TABLE `AccountCategory` DISABLE KEYS */;
INSERT INTO `AccountCategory` VALUES (1,'现在资产','现在的资产帐目'),(2,'资金','可支配帐目'),(3,'固定资产','固定资产帐目'),(4,'其他的负债','其他的负债帐目'),(5,'其他的营业费用','其他的营业费用帐目'),(6,'营业费用','营业费用帐目'),(7,'现在的债务','现在的债务帐目'),(8,'营业金额','营业金额帐目'),(9,'其他的费用','其他的费用帐目');
/*!40000 ALTER TABLE `AccountCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountExtension`
--

LOCK TABLES `AccountExtension` WRITE;
/*!40000 ALTER TABLE `AccountExtension` DISABLE KEYS */;
INSERT INTO `AccountExtension` VALUES (1,'无','');
/*!40000 ALTER TABLE `AccountExtension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountGroup`
--

LOCK TABLES `AccountGroup` WRITE;
/*!40000 ALTER TABLE `AccountGroup` DISABLE KEYS */;
INSERT INTO `AccountGroup` VALUES (1,1,'雷智现金',''),(2,2,'成都商业银行',''),(3,2,'中国农业银行',''),(4,2,'成都工商银行',''),(5,3,'单间租金(RMB)',''),(6,1,'工资收入',''),(7,18,'整套房租',''),(8,12,'日用品开支',''),(9,12,'杂费开支','水电气'),(10,13,'雷智营业外收入',''),(11,13,'袁利营业外收入',''),(12,24,'人员工资',''),(13,24,'操作员费用',''),(14,17,'布告费用',''),(15,21,'软件销售金额',''),(16,20,'附属品销售',''),(17,1,'袁利现金',''),(18,5,'定金','');
/*!40000 ALTER TABLE `AccountGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountType`
--

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
INSERT INTO `AccountType` VALUES (1,1,'现金','现金帐目',2),(2,1,'银行存款','银行存款帐目',2),(3,1,'帐户应收帐款','',4),(4,1,'其他应收帐款','',4),(5,1,'预付的费用','',2),(6,2,'储备利润','',6),(7,2,'应收股利','',6),(8,3,'库存商品','',6),(9,3,'累计折旧','',6),(10,3,'财产','',6),(11,4,'其他的债务','',5),(12,4,'其他应付款','',5),(13,8,'其他收入','',2),(14,6,'销售费用','',5),(15,6,'其他营业费用','',5),(16,6,'退款','',6),(17,6,'处理费用','',5),(18,7,'帐户应付款','',5),(19,7,'应交税金','',5),(20,8,'其他销售金额','',1),(21,8,'销售金额','',1),(22,9,'一般布告费用','',5),(23,9,'其他费用','',5),(24,9,'营业费用','',5);
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `BlogCategory`
--

LOCK TABLES `BlogCategory` WRITE;
/*!40000 ALTER TABLE `BlogCategory` DISABLE KEYS */;
INSERT INTO `BlogCategory` VALUES (0,0,0,'',NULL),(1,0,1,'Music',NULL),(2,0,1,'Move','电影'),(3,1,2,'mp3',NULL),(4,1,2,'mp4',NULL),(5,2,2,'HongKong','香港武打'),(6,0,1,'USA',NULL),(7,6,2,'越狱',''),(8,3,3,'孙燕枝',''),(9,0,1,'技术联盟','论坛'),(10,9,2,'java',''),(11,9,2,'c/c++',''),(12,0,1,'股票','财经');
/*!40000 ALTER TABLE `BlogCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES (0,0,'NULL','NULL',NULL),(1,1,'CD','ChengDu','ChengDu'),(2,1,'SH','ShangHai','ShangHai'),(3,1,'CQ','ChongQing','ChongQing');
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Country`
--

LOCK TABLES `Country` WRITE;
/*!40000 ALTER TABLE `Country` DISABLE KEYS */;
INSERT INTO `Country` VALUES (0,'','',NULL),(1,'CN','CHINA','CHINA'),(2,'CA','CANADA','CANADA'),(3,'USA','USA','USA'),(4,'ID','INDONESIA','INDONESIA');
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
-- Dumping data for table `DownloadType`
--

LOCK TABLES `DownloadType` WRITE;
/*!40000 ALTER TABLE `DownloadType` DISABLE KEYS */;
INSERT INTO `DownloadType` VALUES (1,'手机软件','手机软件'),(2,'手机游戏','手机游戏'),(3,'手机彩铃','手机彩铃'),(4,'Music','MP3/MP4');
/*!40000 ALTER TABLE `DownloadType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Language`
--

LOCK TABLES `Language` WRITE;
/*!40000 ALTER TABLE `Language` DISABLE KEYS */;
INSERT INTO `Language` VALUES (0,'','',NULL),(1,'CN','Chinese','Chinese Language'),(2,'CA','English','Caina English'),(3,'USA','English','USA English'),(4,'ID','English','Indonesia Enilsh');
/*!40000 ALTER TABLE `Language` ENABLE KEYS */;
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
INSERT INTO `OperatorUser` VALUES (1,'zlei','7645b7c673f30a68',2,'dd','dd','dd','dd','dd',7,4),(2,'yl','0761b625491239ff',1,'aa','aa','aa','aa','aa',7,4);
/*!40000 ALTER TABLE `OperatorUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `State`
--

LOCK TABLES `State` WRITE;
/*!40000 ALTER TABLE `State` DISABLE KEYS */;
INSERT INTO `State` VALUES (0,'Root','Root','root state'),(1,'Common','common','common state');
/*!40000 ALTER TABLE `State` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-06-30  8:02:31
