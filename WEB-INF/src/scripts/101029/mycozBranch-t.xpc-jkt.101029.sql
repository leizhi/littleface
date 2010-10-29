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
/*!40000 ALTER TABLE `AccessLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AddressBook`
--

LOCK TABLES `AddressBook` WRITE;
/*!40000 ALTER TABLE `AddressBook` DISABLE KEYS */;
INSERT INTO `AddressBook` VALUES (1,1,1,0,1,'','aa','aa','aa',''),(2,2,3,0,1,'','','','',''),(3,3,1,0,1,'','','','',''),(4,4,1,0,1,'','614200','kid_lyj@126.com','13541066041',''),(5,5,1,0,1,'','400000','boy.365@126.com','13658241719',''),(6,6,1,1,1,'dd','dd','leizhifesker@gmail.com','dd',NULL),(7,7,4,4,3,'','','','',NULL),(8,12,0,0,0,NULL,NULL,NULL,NULL,NULL),(9,13,0,0,0,NULL,NULL,NULL,NULL,NULL),(10,14,0,0,0,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `FileInfo` VALUES (0,'NULL','2010-10-28 14:48:46',NULL,'N'),(1,'grub 启动','2010-10-03 22:45:11','boot20101003224504761.jpg','N'),(2,'信息库项目','2010-10-12 14:29:54','2010092020101012142842631.doc','N'),(3,'eclipse.tomcat.3.2.1','2010-10-25 13:24:49','com20101025132349425.sysdeo.eclipse.tomcat_3.2.1.tar.gz','N'),(4,'压缩','2010-10-28 14:55:21',NULL,'Y'),(5,'首页效果图库','2010-10-29 14:00:18',NULL,'Y'),(6,'images.jpg','2010-10-29 14:01:10','images20101029140104363.jpg','N');
/*!40000 ALTER TABLE `FileInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `FileTree`
--

LOCK TABLES `FileTree` WRITE;
/*!40000 ALTER TABLE `FileTree` DISABLE KEYS */;
INSERT INTO `FileTree` VALUES (1,0,4,0),(2,4,1,0),(3,4,3,0),(4,4,2,0),(5,0,5,0),(6,5,6,0);
/*!40000 ALTER TABLE `FileTree` ENABLE KEYS */;
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
INSERT INTO `ForumThread` VALUES (1,0,1,'ddddddd','dddddddddd','2010-10-08 19:05:53','2010-10-03 00:00:00','N',0,'N',2),(2,0,1,'技术交流','技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流','2010-10-08 19:02:50','2010-10-03 00:00:00','N',0,'N',8),(3,0,1,'dasjkt','thanks come here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(4,0,1,'公告','有大家的支持就是维护和完善的动力. 尽量做到最完善的最友好的论坛.','2010-10-27 17:52:38','2010-10-03 00:00:00','N',0,'N',8),(5,0,11,'LFS 6.7 already launched','LFS 6.7 already launched.\r\nYou can see it on www.distrowatch.com.\r\nDocuments on PDF, HTML and .tar.bz\r\nStill try to take this as my big project.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,'N',11),(6,0,2,'源代码下载','git clone git://github.com/leizhi/littleface.git','2010-10-15 08:59:33','2010-10-15 08:41:33','N',0,'N',2),(7,0,2,'LInux 内核调整','vi /etc/sysctl.conf','2010-10-18 08:43:15','2010-10-18 08:35:10','N',0,'N',2),(8,0,3,'呼叫师傅','师傅我在这里跟你说话你会看到吗？','2010-10-29 09:24:05','2010-10-29 09:19:04','N',0,'N',8),(9,0,3,'我也来找茬，O(∩_∩)O',NULL,'2010-10-29 14:21:38','2010-10-29 09:45:31','N',0,'N',8);
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
INSERT INTO `Message` VALUES (1,1,1,'ffffffff','ffffffff','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(2,1,1,'dddddddd','dsafdsa','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(3,1,1,'sdafasd','sadfsdaf','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(4,2,1,'太好了','我就喜欢','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(5,2,1,'给大家一个好地址','ftp://cxrd.mooo.com','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(6,2,1,'好家伙','http://localhost/good','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(7,3,8,'up','good , i love here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,8),(8,5,8,'like LFS','up like LFS, i like too.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,8),(9,1,8,'yy','yyy','2010-10-08 09:03:14','2010-10-08 09:03:14','N',0,8),(10,3,8,'test','test test','2010-10-08 09:15:36','2010-10-08 09:15:36','N',0,8),(11,1,8,'sdfsa','safdsaf','2010-10-08 12:35:31','2010-10-08 12:35:31','N',0,8),(12,1,8,'ffff','ffff','2010-10-08 12:36:34','2010-10-08 12:36:34','N',0,8),(13,2,8,'顶起','顶起顶起顶起顶起','2010-10-08 19:02:50','2010-10-08 19:02:50','N',0,8),(14,1,2,'sadfdsaf','sfdasdfsa','2010-10-08 19:05:53','2010-10-08 19:05:53','N',0,2),(15,6,2,'linux安装方法','1.首先安装tomcat或者其他服务器\r\n2.把源代码放在$CATALINA_HOME/webapps\r\n3.执行$./compile.sh编译项目 或者$ant\r\n4.WEB-INF/src/scripts/下导入数据库 例如:\r\n./backup-mycoz xpc-jkt 101008 [database Password]\r\n5.WEB-INF/src/mypool.xml配置数据库和一些基本信息\r\n6.启动tomcat\r\n7.测试http://localhost:8080/项目名称','2010-10-15 08:59:33','2010-10-15 08:59:33','N',0,2),(16,7,2,'1#','sysctl -a 打印内核全部参数','2010-10-18 08:35:55','2010-10-18 08:35:55','N',0,2),(17,7,2,'2#','cat sysctl.conf \r\n#Instal Oracle Config\r\nfs.aio-max-nr = 1048576\r\nfs.file-max = 6815744\r\nkernel.shmall = 2097152\r\nkernel.shmmax = 2147483648\r\nkernel.shmmni = 4096\r\nkernel.sem = 250 32000 100 128\r\nnet.ipv4.ip_local_port_range = 9000 65500\r\nnet.core.rmem_default = 262144\r\nnet.core.rmem_max = 4194304\r\nnet.core.wmem_default = 262144\r\nnet.core.wmem_max = 1048576\r\nvm.swappiness = 10','2010-10-18 08:36:37','2010-10-18 08:36:37','N',0,2),(18,7,2,'#3','sysctl -p 配置文件生效','2010-10-18 08:37:06','2010-10-18 08:37:06','N',0,2),(19,7,2,'#4','sysctl -w net.ipv4.route.flush=1 生效','2010-10-18 08:43:15','2010-10-18 08:43:15','N',0,2),(20,4,8,'NULL',NULL,'2010-10-27 17:52:38','2010-10-27 17:52:38','N',0,8),(21,8,3,'NULL',NULL,'2010-10-29 09:20:27','2010-10-29 09:20:27','N',0,3),(22,8,8,'师傅来了','徒弟很不错的','2010-10-29 09:21:36','2010-10-29 09:21:36','N',0,8),(23,8,3,'NULL','我是顶顶大名人见人爱花见花开江湖人称小魔女的师傅的徒弟，哈哈','2010-10-29 09:21:44','2010-10-29 09:21:44','N',0,3),(24,8,8,':-)','^_^ 多谢夸讲','2010-10-29 09:24:05','2010-10-29 09:24:05','N',0,8),(25,9,8,'bug修复','修复空回复和发表','2010-10-29 13:34:52','2010-10-29 13:34:52','N',0,8),(26,9,8,'bug1 修复','文件上传日期 显示 错误','2010-10-29 14:21:37','2010-10-29 14:21:37','N',0,8);
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
INSERT INTO `User` VALUES (1,'root','e10adc3949ba59abbe56e057f20f883e',NULL,'Y'),(2,'zlei','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(3,'小魔女','61fa9b32ce3bd11e3b5c6d3b469a8f76',NULL,'Y'),(4,'kid_lyj','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(5,'蕲春人','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(6,'admin','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(7,'0252118','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(8,'das','2a6571da26602a67be14ea8c5ab82349',NULL,'Y'),(9,'honeygo','c8a796aca556400561999a1bf0cc3cb0',NULL,'Y'),(10,'123123aa','4297f44b13955235245b2497399d7a93',NULL,'Y'),(11,'langsepsatu','ceca2271fe893c965a5b02d977201245',NULL,'Y'),(12,'Jeep max','9e4514ce6535c47c8cfaafb17717119a',NULL,'Y'),(13,'beijing','354b500e3a784a489a27ee50d19ba328',NULL,'Y'),(14,'Masterkid','48a9cc2c11f307d0be00b37f5bb421d7',NULL,'Y');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
INSERT INTO `UserInfo` VALUES (1,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N'),(2,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N'),(3,14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N');
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

-- Dump completed on 2010-10-29 17:56:02
