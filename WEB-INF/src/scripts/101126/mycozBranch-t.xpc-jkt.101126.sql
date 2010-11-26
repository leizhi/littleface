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
INSERT INTO `AddressBook` VALUES (1,1,1,1,2,'四川成都','610504','028-12345678','13812345678'),(2,2,1,1,2,'四川成都','610504','028-12345678','13812345678'),(3,3,1,1,2,'四川成都','610504','028-12345678','13812345678'),(4,4,1,1,2,'四川成都','610504','028-12345678','13812345678'),(5,5,1,1,2,'四川成都','610504','028-12345678','13812345678'),(6,6,1,1,2,'四川成都','610504','028-12345678','13812345678'),(7,7,1,1,2,'四川成都','610504','028-12345678','13812345678'),(8,8,1,1,2,'四川成都','610504','028-12345678','13812345678'),(9,9,1,1,2,'四川成都','610504','028-12345678','13812345678'),(10,10,1,1,2,'四川成都','610504','028-12345678','13812345678'),(11,11,1,1,2,'四川成都','610504','028-12345678','13812345678'),(12,12,1,1,2,'四川成都','610504','028-12345678','13812345678'),(13,13,1,1,2,'四川成都','610504','028-12345678','13812345678'),(14,14,1,1,2,'四川成都','610504','028-12345678','13812345678'),(15,15,1,1,2,'四川成都','610504','028-12345678','13812345678'),(16,16,1,1,2,'四川成都','610504','028-12345678','13812345678'),(17,17,1,1,2,'四川成都','610504','028-12345678','13812345678'),(18,18,1,1,2,'四川成都','610504','028-12345678','13812345678'),(19,19,1,1,2,'四川成都','610504','028-12345678','13812345678'),(20,20,1,1,2,'四川成都','610504','028-12345678','13812345678'),(21,21,1,1,2,'四川成都','610504','028-12345678','13812345678'),(22,22,1,1,2,'四川成都','610504','028-12345678','13812345678'),(23,23,1,1,2,'四川成都','610504','028-12345678','13812345678'),(24,24,1,1,2,'四川成都','610504','028-12345678','13812345678'),(25,25,1,1,2,'四川成都','610504','028-12345678','13812345678'),(26,26,1,1,2,'四川成都','610504','028-12345678','13812345678'),(27,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,29,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,30,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `AddressBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `FileInfo`
--

LOCK TABLES `FileInfo` WRITE;
/*!40000 ALTER TABLE `FileInfo` DISABLE KEYS */;
INSERT INTO `FileInfo` VALUES (0,'NULL','2010-10-28 14:48:46',NULL,'N'),(1,'grub 启动','2010-10-03 22:45:11','boot20101003224504761.jpg','N'),(2,'信息库项目','2010-10-12 14:29:54','2010092020101012142842631.doc','N'),(3,'eclipse.tomcat.3.2.1','2010-10-25 13:24:49','com20101025132349425.sysdeo.eclipse.tomcat_3.2.1.tar.gz','N'),(4,'压缩','2010-10-28 14:55:21',NULL,'Y'),(5,'首页效果图库','2010-10-29 14:00:18',NULL,'Y'),(6,'images.jpg','2010-10-29 14:01:10','images20101029140104363.jpg','N'),(7,'源代码下载','2010-11-01 20:51:20',NULL,'Y'),(8,'littleface-2.3-beta.tar.gz','2010-11-17 04:07:10','littleface-220101117120709621.3-beta.tar.gz','N'),(9,'uu','2010-11-17 19:54:48',NULL,'Y'),(10,'yed.jar','2010-11-24 14:11:08','yed.jar','N'),(11,'bash_profile.profile','2010-11-24 14:14:47','bash_profile.profile','N');
/*!40000 ALTER TABLE `FileInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `FileTree`
--

LOCK TABLES `FileTree` WRITE;
/*!40000 ALTER TABLE `FileTree` DISABLE KEYS */;
INSERT INTO `FileTree` VALUES (1,0,4,0),(2,4,1,0),(3,4,3,0),(4,4,2,0),(5,0,5,0),(6,5,6,0),(7,0,7,NULL),(8,7,8,NULL),(9,0,9,NULL),(10,5,10,NULL),(11,5,11,NULL);
/*!40000 ALTER TABLE `FileTree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Forum`
--

LOCK TABLES `Forum` WRITE;
/*!40000 ALTER TABLE `Forum` DISABLE KEYS */;
INSERT INTO `Forum` VALUES (5,'XPC System'),(3,'公告'),(0,'其他'),(4,'开发人员'),(2,'社区'),(1,'问题');
/*!40000 ALTER TABLE `Forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ForumThread`
--

LOCK TABLES `ForumThread` WRITE;
/*!40000 ALTER TABLE `ForumThread` DISABLE KEYS */;
INSERT INTO `ForumThread` VALUES (1,3,1,'ddddddd','dddddddddd','2010-10-08 19:05:53','2010-10-03 00:00:00','N',0,'N',2),(2,3,1,'技术交流','技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流技术交流','2010-11-16 16:21:36','2010-10-03 00:00:00','N',0,'N',28),(3,3,1,'dasjkt','thanks come here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,'N',1),(4,3,1,'公告','有大家的支持就是维护和完善的动力. 尽量做到最完善的最友好的论坛.','2010-10-27 17:52:38','2010-10-03 00:00:00','N',0,'N',8),(5,3,11,'LFS 6.7 already launched','LFS 6.7 already launched.\r\nYou can see it on www.distrowatch.com.\r\nDocuments on PDF, HTML and .tar.bz\r\nStill try to take this as my big project.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,'N',11),(6,3,2,'源代码下载','git clone git://github.com/leizhi/littleface.git','2010-10-15 08:59:33','2010-10-15 08:41:33','N',0,'N',2),(7,3,2,'LInux 内核调整','vi /etc/sysctl.conf','2010-10-18 08:43:15','2010-10-18 08:35:10','N',0,'N',2),(8,3,3,'呼叫师傅','师傅我在这里跟你说话你会看到吗？','2010-10-29 09:24:05','2010-10-29 09:19:04','N',0,'N',8),(9,3,3,'我也来找茬，O(∩_∩)O',NULL,'2010-10-29 14:21:38','2010-10-29 09:45:31','N',0,'N',8),(10,1,8,'地震可以欲知','美国最新报道 地震可以提前半个月探测','2010-10-31 17:30:49','2010-10-31 17:30:15','N',0,'N',8),(11,4,8,'论坛积分算法','用户等级采取公式:\r\nSn = a0 * (2^n) 条件:(n>=0)\r\nSn为总积分,a0为常数,n为等级.\r\nn=0时 S0=a0\r\nn=1时 S1=a0*2\r\nn=2时 s2=a0*2*2','2010-11-01 20:40:59','2010-11-01 20:40:59','N',0,'N',8),(12,6,8,'Problem','1.Xpc system in use now for refund function ?\r\n2.job refund, how to handle financial. accounts receivable credit and  account Payable debit ?\r\n3.Account Title detail Whether it is necessary for Customer/Parter ?','2010-11-16 16:22:45','2010-11-10 15:52:26','N',0,'N',28),(13,6,8,'财务','总资产\r\n\r\n总资产增加方向 = 总资产减少方向\r\n\r\n但是 平衡\r\n\r\nCredit Note和Debit Note都可以理解为收付款的\r\n书面通知书。 说两个借贷的流程就明白了： A公司\r\n向我公司买东西，A公司是借方。对于我公司来说，\r\n费用上是一笔应收帐款。我公司开DEBIT NOTE给A\r\n公司，[等于是催A公司付款，可以理解为催款通知\r\n书]。A公司在收到我公司的DEBIT NOTE后，就可以\r\n开CREDIT NOTE返回我公司。我公司收到后开\r\nINVOICE给A公司。 我公司买B公司东西，B公司是\r\n贷方。对于我公司来说，费用上是一笔应付帐款。我\r\n公司开CREDIT NOTE给B公司，告诉B公司，我们付\r\n这么些钱给你们 [等于是给B公司的我公司的付款通\r\n知] ，这里CREDIT NOTE就是L/C信用证。B公司收\r\n到后开INVOICE给我公司。\r\n\r\n发票 \r\n应收账款 Debit 主营也收入 Credit\r\n退\r\n销售成本 Debit 应付账款 Credit\r\n\r\njournal for account receivable should be\r\n debit ---> Account receivable\r\n credit ---> sales\r\n\r\njournal account payable should be\r\n debit ---> cost of sales\r\n credit ---> account payable\r\n\r\n借:应收帐款  贷:主营业务收入-销售收入 应交税费-增值税\r\n\r\n借:销售成本 贷:应付账款\r\n\r\n|  1 | Asset       | 资产        | 1           |\r\n|  2 | Liability   | 负债        | 2           |\r\n|  3 | Equity      | 权益        | 3           |\r\n|  4 | Revenue     | 收入        | 4           |\r\n|  5 | Expense     | 费用        | 5           |\r\n\r\n资产 借 debit\r\n负债 贷 credit\r\n权益 贷 credit\r\n收入 借 debit\r\n费用 贷 credit\r\n\r\n银行存款 Debit 劳务成本 Credit\r\n\r\n水费 Debit 应付账款:Credit\r\n\r\n借:应收帐款  贷:销售收入 /应交税费/增值税\r\n借:银行存款/备用现金 贷:应收帐款\r\n\r\n借:银行存款/备用现金 贷:销售收入 /应交税费/增值税\r\n\r\n借:主营业务成本(销售成本) 贷:应付账款\r\n借:应付账款 贷:银行存款/备用现金\r\n\r\n借:主营业务成本(销售成本) 贷:银行存款/备用现金\r\n\r\n借:管理费用-水费	贷:银行存款/备用现金','2010-11-12 10:02:14','2010-11-12 10:02:14','N',0,'N',8);
/*!40000 ALTER TABLE `ForumThread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GroupMember`
--

LOCK TABLES `GroupMember` WRITE;
/*!40000 ALTER TABLE `GroupMember` DISABLE KEYS */;
INSERT INTO `GroupMember` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30);
/*!40000 ALTER TABLE `GroupMember` ENABLE KEYS */;
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
INSERT INTO `Message` VALUES (1,1,1,'ffffffff','ffffffff','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(2,1,1,'dddddddd','dsafdsa','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(3,1,1,'sdafasd','sadfsdaf','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(4,2,1,'太好了','我就喜欢','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(5,2,1,'给大家一个好地址','ftp://cxrd.mooo.com','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(6,2,1,'好家伙','http://localhost/good','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,1),(7,3,8,'up','good , i love here','2010-10-03 00:00:00','2010-10-03 00:00:00','N',0,8),(8,5,8,'like LFS','up like LFS, i like too.','2010-10-04 00:00:00','2010-10-04 00:00:00','N',0,8),(9,1,8,'yy','yyy','2010-10-08 09:03:14','2010-10-08 09:03:14','N',0,8),(10,3,8,'test','test test','2010-10-08 09:15:36','2010-10-08 09:15:36','N',0,8),(11,1,8,'sdfsa','safdsaf','2010-10-08 12:35:31','2010-10-08 12:35:31','N',0,8),(12,1,8,'ffff','ffff','2010-10-08 12:36:34','2010-10-08 12:36:34','N',0,8),(13,2,8,'顶起','顶起顶起顶起顶起','2010-10-08 19:02:50','2010-10-08 19:02:50','N',0,8),(14,1,2,'sadfdsaf','sfdasdfsa','2010-10-08 19:05:53','2010-10-08 19:05:53','N',0,2),(15,6,2,'linux安装方法','1.首先安装tomcat或者其他服务器\r\n2.把源代码放在$CATALINA_HOME/webapps\r\n3.执行$./compile.sh编译项目 或者$ant\r\n4.WEB-INF/src/scripts/下导入数据库 例如:\r\n./backup-mycoz xpc-jkt 101008 [database Password]\r\n5.WEB-INF/src/mypool.xml配置数据库和一些基本信息\r\n6.启动tomcat\r\n7.测试http://localhost:8080/项目名称','2010-10-15 08:59:33','2010-10-15 08:59:33','N',0,2),(16,7,2,'1#','sysctl -a 打印内核全部参数','2010-10-18 08:35:55','2010-10-18 08:35:55','N',0,2),(17,7,2,'2#','cat sysctl.conf \r\n#Instal Oracle Config\r\nfs.aio-max-nr = 1048576\r\nfs.file-max = 6815744\r\nkernel.shmall = 2097152\r\nkernel.shmmax = 2147483648\r\nkernel.shmmni = 4096\r\nkernel.sem = 250 32000 100 128\r\nnet.ipv4.ip_local_port_range = 9000 65500\r\nnet.core.rmem_default = 262144\r\nnet.core.rmem_max = 4194304\r\nnet.core.wmem_default = 262144\r\nnet.core.wmem_max = 1048576\r\nvm.swappiness = 10','2010-10-18 08:36:37','2010-10-18 08:36:37','N',0,2),(18,7,2,'#3','sysctl -p 配置文件生效','2010-10-18 08:37:06','2010-10-18 08:37:06','N',0,2),(19,7,2,'#4','sysctl -w net.ipv4.route.flush=1 生效','2010-10-18 08:43:15','2010-10-18 08:43:15','N',0,2),(20,4,8,'NULL',NULL,'2010-10-27 17:52:38','2010-10-27 17:52:38','N',0,8),(21,8,3,'NULL',NULL,'2010-10-29 09:20:27','2010-10-29 09:20:27','N',0,3),(22,8,8,'师傅来了','徒弟很不错的','2010-10-29 09:21:36','2010-10-29 09:21:36','N',0,8),(23,8,3,'NULL','我是顶顶大名人见人爱花见花开江湖人称小魔女的师傅的徒弟，哈哈','2010-10-29 09:21:44','2010-10-29 09:21:44','N',0,3),(24,8,8,':-)','^_^ 多谢夸讲','2010-10-29 09:24:05','2010-10-29 09:24:05','N',0,8),(25,9,8,'bug修复','修复空回复和发表','2010-10-29 13:34:52','2010-10-29 13:34:52','N',0,8),(26,9,8,'bug1 修复','文件上传日期 显示 错误','2010-10-29 14:21:37','2010-10-29 14:21:37','N',0,8),(27,10,8,'太好了','是真的吗','2010-10-31 17:30:48','2010-10-31 17:30:48','N',0,8),(28,2,28,'sdfasdf','sdf','2010-11-16 16:21:36','2010-11-16 16:21:36','N',0,28),(29,12,28,'dfasd','sdfasdf','2010-11-16 16:22:45','2010-11-16 16:22:45','N',0,28);
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `NoteDetail`
--

LOCK TABLES `NoteDetail` WRITE;
/*!40000 ALTER TABLE `NoteDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `NoteDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderDetail`
--

LOCK TABLES `OrderDetail` WRITE;
/*!40000 ALTER TABLE `OrderDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderItem`
--

LOCK TABLES `OrderItem` WRITE;
/*!40000 ALTER TABLE `OrderItem` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ProductImage`
--

LOCK TABLES `ProductImage` WRITE;
/*!40000 ALTER TABLE `ProductImage` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProductImage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Property`
--

LOCK TABLES `Property` WRITE;
/*!40000 ALTER TABLE `Property` DISABLE KEYS */;
/*!40000 ALTER TABLE `Property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RoleMember`
--

LOCK TABLES `RoleMember` WRITE;
/*!40000 ALTER TABLE `RoleMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `RoleMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ThreadType`
--

LOCK TABLES `ThreadType` WRITE;
/*!40000 ALTER TABLE `ThreadType` DISABLE KEYS */;
INSERT INTO `ThreadType` VALUES (1,'新闻',2),(2,'帮助',2),(3,'捉虫(Bug)',4),(4,'需求策划',4),(5,'市场推广',4),(6,'Financial',5);
/*!40000 ALTER TABLE `ThreadType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'root','e10adc3949ba59abbe56e057f20f883e',NULL,'Y'),(2,'zlei','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(3,'小魔女','61fa9b32ce3bd11e3b5c6d3b469a8f76',NULL,'Y'),(4,'kid_lyj','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(5,'蕲春人','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(6,'admin','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(7,'0252118','63a9f0ea7bb98050796b649e85481845',NULL,'Y'),(8,'das','2a6571da26602a67be14ea8c5ab82349',NULL,'Y'),(9,'honeygo','c8a796aca556400561999a1bf0cc3cb0',NULL,'Y'),(10,'123123aa','4297f44b13955235245b2497399d7a93',NULL,'Y'),(11,'langsepsatu','ceca2271fe893c965a5b02d977201245',NULL,'Y'),(12,'Jeep max','9e4514ce6535c47c8cfaafb17717119a',NULL,'Y'),(13,'beijing','354b500e3a784a489a27ee50d19ba328',NULL,'Y'),(14,'Masterkid','48a9cc2c11f307d0be00b37f5bb421d7',NULL,'Y'),(15,'sunyuz','96e79218965eb72c92a549dd5a330112',NULL,'Y'),(16,'test','098f6bcd4621d373cade4e832627b4f6',NULL,'Y'),(17,'test1','ad0234829205b9033196ba818f7a872b',NULL,'Y'),(18,'kaka','96e79218965eb72c92a549dd5a330112',NULL,'Y'),(19,'fesker','8b8f5433fb4fd6282392fa4a6da28269',NULL,'Y'),(20,'ycm','20b9b2f7193aa45e2b8ac6b311d27926',NULL,'Y'),(21,'s','03c7c0ace395d80182db07ae2c30f034',NULL,'Y'),(22,'ss','3691308f2a4c2f6983f2880d32e29c84',NULL,'Y'),(23,'258595660','ca21b2f197822a9e89bec3d9dd5394e3',NULL,'Y'),(24,'as','f970e2767d0cfe75876ea857f92e319b',NULL,'Y'),(25,'inaspless','f3b744a27938ae9dd3e8daebda3ae6a9',NULL,'Y'),(26,'小哈歪歪','e10adc3949ba59abbe56e057f20f883e',NULL,'Y'),(27,'lingxiaoyi','4fa5c95cfce904dbd22b7b9d776c6d91',NULL,'Y'),(28,'te','569ef72642be0fadd711d6a468d68ee1',NULL,'Y'),(29,'aaa','47bce5c74f589f4867dbd57e9ca9f808',NULL,'Y'),(30,'konser','a4e3f3b7e32c3964e0d79e38d22458d3',NULL,'Y');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserImage`
--

LOCK TABLES `UserImage` WRITE;
/*!40000 ALTER TABLE `UserImage` DISABLE KEYS */;
INSERT INTO `UserImage` VALUES (1,1,'u=3866481873,818653773&fm=0&gp=020101104151159757.jpg'),(2,2,'u=3838268131,2182876840&fm=0&gp=020101104151201491.jpg'),(3,3,'u=3645290530,3192856555&fm=3&gp=020101104151203037.jpg'),(4,4,'u=3499500655,2848436478&fm=0&gp=020101104151203330.jpg'),(5,5,'u=3149673571,3828632236&fm=0&gp=020101104151203409.jpg'),(6,6,'u=2703075846,2784696224&fm=3&gp=020101104151204690.jpg'),(7,7,'u=2275682719,1831179390&fm=0&gp=020101104151205033.jpg'),(8,8,'u=2275682719,1831179390&fm=0&gp=020101104151205273.jpg'),(9,9,'u=686314898,655064919&fm=0&gp=020101104151206578.jpg'),(10,10,'u=900324182,3484884813&fm=0&gp=020101104151206635.jpg'),(11,11,'u=1035888119,945798161&fm=9&gp=020101104151206901.jpg'),(12,12,'u=1152720128,1301771348&fm=0&gp=020101104151206978.jpg'),(13,13,'u=1350353025,608268203&fm=0&gp=020101104151207928.jpg'),(14,14,'u=2102337334,3601644220&fm=0&gp=020101104151208338.jpg'),(15,15,'u=4123854008,2514991823&fm=9&gp=020101104152106098.jpg'),(16,16,'u=3646840853,1778488215&fm=3&gp=020101104152107851.jpg'),(17,17,'u=3223084462,1412376783&fm=9&gp=020101104152107903.jpg'),(18,18,'u=2999069305,3720664645&fm=9&gp=020101104152111520.jpg'),(19,19,'u=2776203912,1786122049&fm=3&gp=020101104152111522.jpg'),(20,20,'u=2672964033,2746903633&fm=3&gp=020101104152111523.jpg'),(21,21,'u=2495547801,359492537&fm=3&gp=020101104152111524.jpg'),(22,22,'u=2216476517,1820953482&fm=9&gp=020101104152111524.jpg'),(23,23,'u=949664971,2373657507&fm=3&gp=020101104152116839.jpg'),(24,24,'u=1287215685,1977918703&fm=9&gp=020101104152116841.jpg'),(25,25,'u=2014883691,810954229&fm=3&gp=020101104152122090.jpg'),(26,26,'u=2216476517,1820953482&fm=9&gp=020101104152131227.jpg'),(27,8,'u=4123854008,2514991823&fm=9&gp=020101104152132956.jpg'),(28,8,'u=1006431872,236152635&fm=0&gp=020101105213644475.jpg'),(29,8,'boot20101117144613939.jpg');
/*!40000 ALTER TABLE `UserImage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
INSERT INTO `UserInfo` VALUES (1,1,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N','gg@gmail.com',NULL),(2,2,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(3,3,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(4,4,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(5,5,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(6,6,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(7,7,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(8,8,2,'198.00',1,'55.00',1,'2010-10-31 03:38:26',1,1,1,'2334','N','das@gmail.com',NULL),(9,9,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(10,10,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(11,11,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(12,12,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(13,13,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(14,14,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(15,15,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(16,16,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N','gg1@gmail.com',NULL),(17,17,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(18,18,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,NULL),(19,19,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N','2134','2010-11-02 09:55:41'),(20,20,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-03 11:08:00'),(21,21,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-03 19:23:07'),(22,22,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-03 19:36:50'),(23,23,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-03 19:50:38'),(24,24,2,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-03 20:03:50'),(25,25,1,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-08 11:50:55'),(26,26,1,'198.00',1,'55.00',1,'2010-10-31 19:38:26',1,1,1,'2334','N',NULL,'2010-11-08 15:30:29'),(27,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'2010-11-15 16:00:29'),(28,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'2010-11-16 16:18:14'),(29,29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'2010-11-19 14:54:13'),(30,30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'2010-11-23 10:59:01');
/*!40000 ALTER TABLE `UserInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Warehouse`
--

LOCK TABLES `Warehouse` WRITE;
/*!40000 ALTER TABLE `Warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `Warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `WarehouseDetail`
--

LOCK TABLES `WarehouseDetail` WRITE;
/*!40000 ALTER TABLE `WarehouseDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `WarehouseDetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-11-26 11:12:08
