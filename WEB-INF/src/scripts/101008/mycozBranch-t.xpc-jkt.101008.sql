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
-- Dumping data for table `AccessLog`
--

LOCK TABLES `AccessLog` WRITE;
/*!40000 ALTER TABLE `AccessLog` DISABLE KEYS */;
INSERT INTO `AccessLog` VALUES (1,'113.108.81.48','2010-10-08 07:15:40','0000-00-00 00:00:00'),(2,'112.90.138.77','2010-10-08 07:15:46','0000-00-00 00:00:00'),(3,'222.212.74.13','2010-10-08 07:28:25','0000-00-00 00:00:00'),(4,'222.212.74.13','2010-10-08 07:28:38','0000-00-00 00:00:00'),(5,'222.212.74.13','2010-10-08 09:01:36','0000-00-00 00:00:00'),(6,'222.212.74.13','2010-10-08 09:06:26','0000-00-00 00:00:00'),(7,'222.212.74.13','2010-10-08 09:10:20','0000-00-00 00:00:00'),(8,'222.212.74.13','2010-10-08 09:11:12','0000-00-00 00:00:00'),(9,'222.212.74.13','2010-10-08 09:11:38','0000-00-00 00:00:00'),(10,'222.212.74.13','2010-10-08 09:14:57','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `AccessLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AddressBook`
--

LOCK TABLES `AddressBook` WRITE;
/*!40000 ALTER TABLE `AddressBook` DISABLE KEYS */;
INSERT INTO `AddressBook` VALUES (1,1,1,0,1,'','aa','aa','aa',''),(2,2,3,0,1,'','','','',''),(3,3,1,0,1,'','','','',''),(4,4,1,0,1,'','614200','kid_lyj@126.com','13541066041',''),(5,5,1,0,1,'','400000','boy.365@126.com','13658241719',''),(6,6,1,1,1,'dd','dd','leizhifesker@gmail.com','dd',NULL),(7,7,4,4,3,'','','','',NULL);
/*!40000 ALTER TABLE `AddressBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Example`
--

LOCK TABLES `Example` WRITE;
/*!40000 ALTER TABLE `Example` DISABLE KEYS */;
/*!40000 ALTER TABLE `Example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `FileInfo`
--

LOCK TABLES `FileInfo` WRITE;
/*!40000 ALTER TABLE `FileInfo` DISABLE KEYS */;
INSERT INTO `FileInfo` VALUES (1,1,'grub 启动','2010-10-03 22:45:11','boot20101003224504761.jpg');
/*!40000 ALTER TABLE `FileInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Forum`
--

LOCK TABLES `Forum` WRITE;
/*!40000 ALTER TABLE `Forum` DISABLE KEYS */;
INSERT INTO `Forum` VALUES (0,6,'NULL',NULL,NULL,NULL);
/*!40000 ALTER TABLE `Forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ForumThread`
--

LOCK TABLES `ForumThread` WRITE;
/*!40000 ALTER TABLE `ForumThread` DISABLE KEYS */;
INSERT INTO `ForumThread` VALUES (1,0,1,'ddddddd','dddddddddd','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(2,0,1,'技术交流','技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(3,0,1,'dasjkt','thanks come here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(4,0,1,'公告','有大家的支持就是维护和完善的动力. 尽量做到最完善的最友好的论坛.','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(5,0,11,'LFS 6.7 already launched','LFS 6.7 already launched.\r\nYou can see it on www.distrowatch.com.\r\nDocuments on PDF, HTML and .tar.bz\r\nStill try to take this as my big project.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,'N',11);
/*!40000 ALTER TABLE `ForumThread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `JobAccounting`
--

LOCK TABLES `JobAccounting` WRITE;
/*!40000 ALTER TABLE `JobAccounting` DISABLE KEYS */;
/*!40000 ALTER TABLE `JobAccounting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `JobJournal`
--

LOCK TABLES `JobJournal` WRITE;
/*!40000 ALTER TABLE `JobJournal` DISABLE KEYS */;
/*!40000 ALTER TABLE `JobJournal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `JobNote`
--

LOCK TABLES `JobNote` WRITE;
/*!40000 ALTER TABLE `JobNote` DISABLE KEYS */;
/*!40000 ALTER TABLE `JobNote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,1,1,'ffffffff','ffffffff','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(2,1,1,'dddddddd','dsafdsa','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(3,1,1,'sdafasd','sadfsdaf','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(4,2,1,'太好了','我就喜欢','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(5,2,1,'给大家一个好地址','ftp://cxrd.mooo.com','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(6,2,1,'好家伙','http://localhost/good','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(7,3,8,'up','good , i love here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,8),(8,5,8,'like LFS','up like LFS, i like too.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,8),(9,1,8,'yy','yyy','2010-10-08 09:03:14','2010-10-08 09:03:14','N',0,8),(10,3,8,'test','test test','2010-10-08 09:15:36','2010-10-08 09:15:36','N',0,8);
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Property`
--

LOCK TABLES `Property` WRITE;
/*!40000 ALTER TABLE `Property` DISABLE KEYS */;
/*!40000 ALTER TABLE `Property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TreeExample`
--

LOCK TABLES `TreeExample` WRITE;
/*!40000 ALTER TABLE `TreeExample` DISABLE KEYS */;
/*!40000 ALTER TABLE `TreeExample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'root','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(2,'zlei','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(3,'小魔女','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(4,'kid_lyj','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(5,'蕲春人','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(6,'admin','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(7,'0252118','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(8,'das','2a6571da26602a67be14ea8c5ab82349',NULL,'Y'),(9,'honeygo','c8a796aca556400561999a1bf0cc3cb0',NULL,'Y'),(10,'123123aa','4297f44b13955235245b2497399d7a93',NULL,'Y'),(11,'langsepsatu','ceca2271fe893c965a5b02d977201245',NULL,'Y');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-10-08  9:51:50
