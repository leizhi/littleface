-- MySQL dump 10.13  Distrib 5.1.47, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: mycozBranch
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
-- Dumping data for table `Blog`
--

LOCK TABLES `Blog` WRITE;
/*!40000 ALTER TABLE `Blog` DISABLE KEYS */;
INSERT INTO `Blog` VALUES (1,2,10,'web开发','来人来人 ok 解放了',NULL,'2008-09-04'),(2,2,12,'H','讨论',NULL,'2008-09-04'),(3,2,10,'桌面','桌面桌面桌面桌面',NULL,'2008-09-04');
/*!40000 ALTER TABLE `Blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Download`
--

LOCK TABLES `Download` WRITE;
/*!40000 ALTER TABLE `Download` DISABLE KEYS */;
INSERT INTO `Download` VALUES (1,4,'verygodd','upload/78620080701070532249.mp3','upload/pub116487412484120080701070532997.mp3','2008-06-30',''),(2,4,'kk','upload/New Stories (Highway Blues)20080701072550052.wma','upload/exp_mbar20080701072550677.jpg','2008-06-30',''),(3,4,'哈哈','upload/Shania Twain - 09 Raining On Our Love 20080706074057287.mp3','upload/120080706074101658.jpg','2008-07-05','very good'),(4,3,'fdsafdsa','upload/2915020090125062224517.mp3','upload/000120090125062226124.jpg','2009-01-25','');
/*!40000 ALTER TABLE `Download` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Example`
--

LOCK TABLES `Example` WRITE;
/*!40000 ALTER TABLE `Example` DISABLE KEYS */;
INSERT INTO `Example` VALUES (12,'小猪',12,'七中'),(13,'小猪f',12,'七中'),(14,'小猪猪',12,'七中'),(15,'小猪猪1',12,'七中'),(16,'小猪猪3',12,'七中'),(17,'小猪猪4',12,'七中'),(18,'小猪猪5',12,'七中'),(19,'小猪猪6',12,'七中'),(20,'小猪猪7',12,'七中'),(21,'小猪猪8',12,'七中'),(22,'小猪猪9',12,'七中');
/*!40000 ALTER TABLE `Example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `JobAccounting`
--

LOCK TABLES `JobAccounting` WRITE;
/*!40000 ALTER TABLE `JobAccounting` DISABLE KEYS */;
INSERT INTO `JobAccounting` VALUES (1,3,'kk','10.0000','月',2.3,1,6),(2,3,'yy','10.0000','月',2.4,1,6),(3,3,'kk','100.0000','月',1,2,7),(4,3,'jj','1002.0000','月',2.3,2,7),(5,4,'jj','10.0000','月',2.3,2,5),(6,4,'kk','100.0000','月',2.4,2,4),(7,4,'jj','100.0000','月',2.1,2,4);
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
INSERT INTO `JobNote` VALUES (1,'DN800011',5,1,4,'N',NULL,0,'2008-01-26','2008-01-26',''),(2,'DN800012',5,1,4,'N',NULL,0,'2008-01-26','2008-01-26',''),(3,'DN800013',5,1,4,'N',NULL,0,'2008-01-26','2008-01-26',''),(4,'CN800011',4,1,4,'N',NULL,0,'2008-01-26','2008-01-26','');
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
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,1,0,1,0,'root','67457e226a1a15bd','','aa','aa','aa',''),(2,3,0,1,1,'zlei','*E2B91693F9EED6BD199AF53AB0E72BCE4435F5CE','','','','',''),(3,1,0,1,1,'小魔女','36947cd0171b6250','','','','',''),(4,1,0,1,1,'kid_lyj','2984941e0e090905','','kid_lyj@126.com','13541066041','614200',''),(5,1,0,1,1,'蕲春人','2648b7361202c38f','','boy.365@126.com','13658241719','400000',''),(6,1,1,1,1,'admin','*4ACFE3202A5FF5CF467898FC58AAB1D615029441','dd','leizhifesker@gmail.com','dd','dd',NULL),(7,4,4,3,1,'0252118','*23AE809DDACAF96AF0FD78ED04B6A265E05AA257','','','','',NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
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
