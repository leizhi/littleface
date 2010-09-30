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
-- Dumping data for table `AccessLog`
--

LOCK TABLES `AccessLog` WRITE;
/*!40000 ALTER TABLE `AccessLog` DISABLE KEYS */;
INSERT INTO `AccessLog` VALUES (1,'127.0.0.1','2010-09-30 06:20:14','0000-00-00 00:00:00'),(2,'127.0.0.1','2010-09-30 06:53:46','0000-00-00 00:00:00'),(3,'127.0.0.1','2010-09-30 07:05:18','0000-00-00 00:00:00'),(4,'127.0.0.1','2010-09-30 07:06:00','0000-00-00 00:00:00'),(5,'127.0.0.1','2010-09-30 07:09:20','0000-00-00 00:00:00'),(6,'127.0.0.1','2010-09-30 07:23:54','0000-00-00 00:00:00');
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
INSERT INTO `FileInfo` VALUES (2,2,'sdfsaf','2010-09-30 07:34:42','springside-320100910093050063.3.3-all-in-one.zip'),(3,2,'plsql','2010-09-30 07:34:42','plsql20100910093421403.rar'),(4,2,'oracle 客户端','2010-09-30 07:34:42','client20100910101431757.rar'),(5,3,'传销用户','2010-09-30 07:35:33','a009s0120100926082123712.mp3');
/*!40000 ALTER TABLE `FileInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Forum`
--

LOCK TABLES `Forum` WRITE;
/*!40000 ALTER TABLE `Forum` DISABLE KEYS */;
/*!40000 ALTER TABLE `Forum` ENABLE KEYS */;
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
-- Dumping data for table `Thread`
--

LOCK TABLES `Thread` WRITE;
/*!40000 ALTER TABLE `Thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `Thread` ENABLE KEYS */;
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
INSERT INTO `User` VALUES (1,'root','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(2,'zlei','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(3,'小魔女','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(4,'kid_lyj','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(5,'蕲春人','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(6,'admin','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(7,'0252118','63a9f0ea7bb98050796b649e85481845',NULL,'Y');
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

-- Dump completed on 2010-09-30 15:38:06
