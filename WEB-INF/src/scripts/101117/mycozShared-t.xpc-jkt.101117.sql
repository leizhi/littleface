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
INSERT INTO `Account` VALUES (9,'1.16.162.00.5','Other Sales A/R USD',NULL,NULL,NULL,'Other Sales A/R USD'),(10,'1.11.111.01.3','Saving USD',NULL,NULL,NULL,'Saving in USD'),(11,'6.61.614.00.5','AE Cost of Sales Trade IDR',NULL,NULL,NULL,'AE Cost of Sales Trade IDR'),(12,'7.72.721.00.5','Tax IDR',NULL,NULL,NULL,'Tax'),(13,'6.61.611.00.5','SI Cost of Sales Trade IDR',NULL,NULL,NULL,'SI Cost of Sales Trade IDR'),(14,'9.93.931.00.3','Costs USD',NULL,NULL,NULL,'Costs USD'),(15,'7.71.711.00.3','Other Sales A/P USD',NULL,NULL,NULL,'Other Sales A/P USD'),(16,'7.71.711.00.5','Other Sales A/P IDR',NULL,NULL,NULL,'Other Sales A/P IDR'),(20,'1.11.111.00.3','Cash USD',NULL,NULL,NULL,'Cash in USD'),(30,'8.83.833.00.5','Other Sales IDR',NULL,NULL,NULL,'Other Sales IDR'),(31,'6.61.613.00.5','Cost of Other Sales IDR',NULL,NULL,NULL,'Cost of Other Sales IDR'),(33,'6.62.621.00.5','SI Cost of Sales IDR',NULL,NULL,NULL,'SI Cost of Sales IDR'),(34,'1.12.123.00.3','Citibank USD 5933',NULL,NULL,NULL,'Citibank USD Account'),(36,'1.12.121.00.5','BCA IDR 5315',NULL,NULL,NULL,'BCA Rupiah'),(37,'1.12.123.00.5','Citibank Rp',NULL,NULL,NULL,'Citibank ID Rupiah Account'),(39,'1.11.111.00.5','Cash IDR',NULL,NULL,NULL,'Cash in ID Rupiah'),(42,'8.83.831.00.5','SI Sales Trade IDR',NULL,NULL,NULL,'SI Sales Trade IDR'),(85,'1.12.122.00.5','HSBC IDR',NULL,NULL,NULL,'HSBC ID Rupiah Account'),(86,'1.12.122.00.3','HSBC USD',NULL,NULL,NULL,'HSBC USD Account'),(94,'8.86.862.00.5','SE Sales IDR',NULL,NULL,NULL,'SE Sales IDR'),(124,'6.62.622.00.5','SE Cost of Sales IDR',NULL,NULL,NULL,'SE Cost of Sales IDR'),(167,'4.45.451.00.3','Other Liabilities USD',NULL,NULL,NULL,'Other Liabilities USD'),(168,'4.45.451.00.5','Other Liabilities IDR',NULL,NULL,NULL,'Other Liabilities IDR'),(169,'2.23.263.00.5','Vehicle',NULL,NULL,NULL,'Vehicle'),(170,'2.23.261.01.5','Desk',NULL,NULL,NULL,'Desk Account'),(171,'2.23.261.02.5','Chair',NULL,NULL,NULL,'Chair Accounts'),(172,'2.23.261.03.5','Filing Cabinet',NULL,NULL,NULL,'Filing Cabinet Account'),(173,'2.23.262.01.5','Computer and Printer',NULL,NULL,NULL,'Computer and Printer Account'),(174,'2.23.262.02.5','Fax/Scanner and Telephone',NULL,NULL,NULL,'Fax/Scanner and Telephone Account'),(175,'2.23.262.03.5','Other Equipments',NULL,NULL,NULL,'Other Equipments Account'),(177,'6.63.633.00.3','Air Import Refund USD',NULL,NULL,NULL,'Air Import Refund Account in USD'),(178,'6.63.633.00.5','Air Import Refund IDR',NULL,NULL,NULL,'Air Import Refund Account in ID Rupiah'),(179,'6.63.631.00.3','Sea Import Refund USD',NULL,NULL,NULL,'Sea Import Refund Account in USD'),(180,'6.63.631.00.5','Sea Import Refund IDR',NULL,NULL,NULL,'Sea Import Refund Account in ID Rupiah'),(181,'6.63.634.00.3','Air Export Refund USD',NULL,NULL,NULL,'Air Export Refund Account in USD'),(182,'6.63.634.00.5','Air Export Refund IDR',NULL,NULL,NULL,'Air Export Refund Account in ID Rupiah'),(183,'6.63.632.00.3','Sea Export Refund USD',NULL,NULL,NULL,'Sea Export Refund Account in USD'),(185,'9.92.921.00.5','Telephone, Internet & Fax Expense',NULL,NULL,NULL,'Telephone, Internet & Fax Expense Account'),(186,'9.92.926.00.5','Gasoline Expense',NULL,NULL,NULL,'Gasoline Expense Account'),(187,'9.92.928.00.5','Transportation Expense',NULL,NULL,NULL,'Transportation Expense'),(188,'9.92.927.00.5','Handling Expense',NULL,NULL,NULL,'Handling Expense Account'),(189,'9.92.9212.00.5','Postage and Delivery Expense',NULL,NULL,NULL,'Postage and Delivery Expense Account'),(190,'9.92.929.00.5','Advertising Expense IDR',NULL,NULL,NULL,'Advertising Expense Account in ID Rupiah'),(191,'9.92.9210.00.5','Entertainment Expense',NULL,NULL,NULL,'Entertainment Expense Account'),(192,'9.92.9211.00.5','Travel Expense IDR',NULL,NULL,NULL,'Travel Expense Account in ID Rupiah'),(193,'9.92.924.00.5','Bad Debt-Domestic Expense',NULL,NULL,NULL,'Bad Debt-Domestic Expense Account'),(194,'9.92.923.00.3','Bad Debt-Overseas Expense',NULL,NULL,NULL,'Bad Debt-Overseas Expense Account'),(195,'9.92.9215.00.5','Insurance Expense',NULL,NULL,NULL,'Insurance Expense Account'),(196,'9.92.922.00.5','Vehicle Depreciation Expense',NULL,NULL,NULL,'Vehicle Depreciation Expense Account'),(197,'9.92.925.00.5','Bonus Expense IDR',NULL,NULL,NULL,'Bonus Expense Account in ID Rupiah'),(198,'9.92.9216.00.5','Miscellaneous Sales Expense',NULL,NULL,NULL,'Miscellaneous Sales Expense Account'),(199,'9.91.911.00.5','Printing & Reproduction Expense',NULL,NULL,NULL,'Printing & Reproduction Expense Account'),(200,'9.91.912.00.5','Office Supplies Expense',NULL,NULL,NULL,'Office Supplies Expense Account'),(201,'9.91.913.00.5','Building Rent Expense',NULL,NULL,NULL,'Building Rent Expense Account'),(202,'9.91.914.00.5','Tax Expense',NULL,NULL,NULL,'Tax Expense Account'),(203,'9.91.915.00.5','Janitorial Expense',NULL,NULL,NULL,'Janitorial Expense Account'),(204,'9.91.916.00.5','Drinking Water Expense',NULL,NULL,NULL,'Drinking Water Expense Account'),(205,'9.91.917.00.5','Computer Repairs Expense',NULL,NULL,NULL,'Computer Repairs Expense Account'),(206,'9.91.918.00.5','Payroll Expense',NULL,NULL,NULL,'Payroll Expense Account'),(207,'9.91.919.00.5','Depreciation Expense',NULL,NULL,NULL,'Depreciation Expense Account'),(208,'9.91.9110.00.5','Miscellaneous Gen & Adm Expense',NULL,NULL,NULL,'Miscellaneous Gen & Adm Expense Account'),(209,'9.91.9111.00.5','Bank Service Charges IDR',NULL,NULL,NULL,'Bank Service Charges Account in ID Rupiah'),(210,'9.92.925.00.3','Bonus Expense USD',NULL,NULL,NULL,'Bonus Expense Account in USD'),(211,'9.91.9111.00.3','Bank Service Charges USD',NULL,NULL,NULL,'Bank Service Charges Account in USD'),(212,'9.92.9211.00.3','Travel Expense USD',NULL,NULL,NULL,'Travel Expense Account in USD'),(213,'9.92.929.00.3','Advertising Expense USD',NULL,NULL,NULL,'Advertising Expense Account in USD'),(214,'7.71.718.00.3','Air Export A/P USD',NULL,NULL,NULL,'Air Export A/P USD'),(215,'7.71.717.00.3','Air Import A/P USD',NULL,NULL,NULL,'Air Import A/P USD'),(216,'7.71.715.00.3','Sea Import A/P USD',NULL,NULL,NULL,'Sea Import A/P USD'),(217,'7.71.716.00.3','Sea Export A/P USD',NULL,NULL,NULL,'Sea Export A/P USD'),(222,'1.16.168.00.3','Air Export A/R USD',NULL,NULL,NULL,'Air Export A/R USD'),(223,'1.16.167.00.3','Air Import A/R USD',NULL,NULL,NULL,'Air Import A/R USD'),(224,'1.16.166.00.3','Sea Export A/R USD',NULL,NULL,NULL,'Sea Export A/R USD'),(225,'1.16.165.00.3','Sea Import A/R USD',NULL,NULL,NULL,'Sea Import A/R USD'),(228,'6.62.624.00.3','AE Cost of Sales USD',NULL,NULL,NULL,'AE Cost of Sales USD'),(229,'6.62.623.00.3','AI Cost of Sales USD',NULL,NULL,NULL,'AI Cost of Sales USD'),(230,'6.62.622.00.3','SE Cost of Sales USD',NULL,NULL,NULL,'SE Cost of Sales USD'),(231,'6.62.621.00.3','SI Cost of Sales USD',NULL,NULL,NULL,'SI Cost of Sales USD'),(233,'6.61.613.00.3','Cost of Other Sales USD',NULL,NULL,NULL,'Cost of Other Sales USD'),(236,'8.86.864.00.3','AE Sales USD',NULL,NULL,NULL,'AE Sales USD'),(237,'8.86.863.00.3','AI Sales USD',NULL,NULL,NULL,'AI Sales USD'),(238,'8.86.862.00.3','SE Sales USD',NULL,NULL,NULL,'SE Sales USD'),(239,'8.86.861.00.3','SI Sales USD',NULL,NULL,NULL,'SI Sales USD'),(241,'8.83.833.00.3','Other Sales USD',NULL,NULL,NULL,'Other Sales USD'),(244,'1.16.168.00.5','Air Export A/R IDR',NULL,NULL,NULL,'Air Export A/R IDR'),(245,'1.16.165.00.5','Sea Import A/R IDR',NULL,NULL,NULL,'Sea Import A/R IDR'),(248,'1.16.167.00.5','Air Import A/R IDR',NULL,NULL,NULL,'Air Import A/R IDR'),(249,'8.86.861.00.5','SI Sales IDR',NULL,NULL,NULL,'SI Sales IDR'),(250,'8.86.864.00.5','AE Sales IDR',NULL,NULL,NULL,'AE Sales IDR'),(251,'8.86.863.00.5','AI Sales IDR',NULL,NULL,NULL,'AI Sales IDR'),(252,'6.63.632.00.5','Sea Export Refund IDR',NULL,NULL,NULL,'Sea Export Refund IDR'),(253,'6.62.624.00.5','AE Cost of Sales IDR',NULL,NULL,NULL,'AE Cost of Sales IDR'),(254,'6.62.623.00.5','AI Cost of Sales IDR',NULL,NULL,NULL,'AI Cost of Sales IDR'),(255,'1.16.166.00.5','Sea Export A/R IDR',NULL,NULL,NULL,'Sea Export A/R IDR'),(256,'7.71.718.00.5','Air Export A/P IDR',NULL,NULL,NULL,'Air Export A/P IDR'),(260,'7.71.717.00.5','Air Import A/P IDR',NULL,NULL,NULL,'Air Import A/P IDR'),(261,'7.71.716.00.5','Sea Export A/P IDR',NULL,NULL,NULL,'Sea Export A/P IDR'),(262,'7.71.715.00.5','Sea Import A/P IDR',NULL,NULL,NULL,'Sea Import A/P IDR'),(263,'7.72.721.00.3','Tax USD',NULL,NULL,NULL,'Tax'),(265,'8.86.863.00.7','Air Import Sales SGD',NULL,NULL,NULL,'Air Import Sales SGD'),(266,'1.16.167.00.7','Air Import A/R SGD',NULL,NULL,NULL,'Air Import A/R SGD'),(267,'5.51.511.00.5','Gain/Loss on ForEx',NULL,NULL,NULL,'Gain/Loss on Foreign Exchange'),(268,'5.51.512.00.5','Interest Income IDR',NULL,NULL,NULL,'Interest Income IDR'),(269,'5.51.512.00.3','Interest Income USD',NULL,NULL,NULL,'Interest Income USD'),(270,'1.17.171.00.5','Container Guarantee Receivable IDR',NULL,NULL,NULL,'Container Guarantee Receivable IDR'),(271,'1.17.171.00.3','Container Guarantee Receivable USD',NULL,NULL,NULL,'Container Guarantee Receivable USD'),(272,'4.42.421.00.5','Container Guarantee Payable IDR',NULL,NULL,NULL,'Container Guarantee Payable IDR'),(273,'4.42.421.00.3','Container Guarantee Payable USD',NULL,NULL,NULL,'Container Guarantee Payable USD'),(274,'9.91.9113.00.5','Repair & Maintenance Vehicle',NULL,NULL,NULL,'Repair & Maintenance Vehicle'),(275,'9.91.9112.00.5','Repair & Maintenance Expense',NULL,NULL,NULL,'Repair & Maintenance Expense'),(277,'4.42.442.00.5','Advance Received IDR',NULL,NULL,NULL,'Advance Received IDR'),(278,'4.42.442.00.3','Advance Received USD',NULL,NULL,NULL,'Advance Received USD'),(279,'1.17.172.00.5','Advance Payment IDR',NULL,NULL,NULL,'Advance Payment IDR'),(280,'1.17.172.00.3','Advance Payment USD',NULL,NULL,NULL,'Advance Payment USD'),(281,'6.64.641.01.3','Sea Import Handling Expense USD',NULL,NULL,NULL,'SI Handling Expense USD'),(282,'6.64.641.01.5','Sea Import Handling Expense IDR',NULL,NULL,NULL,'SI Handling Expense USD'),(283,'6.64.642.01.3','Sea Export Handling Expense USD',NULL,NULL,NULL,'SE Handling Expense USD'),(284,'6.64.642.01.5','Sea Export Handling Expense IDR',NULL,NULL,NULL,'SE Handling Expense IDR'),(285,'6.64.643.01.3','Air Import Handling Expense USD',NULL,NULL,NULL,'AI Handling Expense USD'),(286,'6.64.644.01.3','Air Export Handling Expense USD',NULL,NULL,NULL,'AE Handling Expense USD'),(287,'6.64.643.01.5','Air Import Handling Expense IDR',NULL,NULL,NULL,'AI Handling Expense IDR'),(288,'6.64.644.01.5','Air Export Handling Expense IDR',NULL,NULL,NULL,'AE Handling Expense IDR'),(289,'6.64.645.01.3','CSE Handling Expense USD',NULL,NULL,NULL,'CSE Handling Expense USD'),(290,'6.64.645.01.5','CSE Handling Expense IDR',NULL,NULL,NULL,'CSE Handling Expense IDR'),(291,'6.64.646.01.3','CAE Handling Expense USD',NULL,NULL,NULL,'CAE Handling Expense USD'),(292,'6.64.646.01.5','CAE Handling Expense IDR',NULL,NULL,NULL,'CAE Handling Expense IDR'),(293,'1.17.173.01.3','Other Receivable USD',NULL,NULL,NULL,'Other Receivable USD'),(294,'1.17.173.01.5','Other Receivable IDR',NULL,NULL,NULL,'Other Receivable IDR'),(295,'9.92.9212.01.3','Postage and Delivery Expense USD',NULL,NULL,NULL,'Postage and Delivery Expense USD'),(296,'1.12.124.01.5','BII-339',NULL,NULL,NULL,'BII Account-IDR'),(297,'5.51.511.01.3','OTHER INCOME USD',NULL,NULL,NULL,'OTHER INCOME USD'),(298,'5.51.511.01.5','OTHER INCOME IDR',NULL,NULL,NULL,'OTHER INCOME IDR'),(299,'9.93.932.01.3','OTHER EXPENSE USD',NULL,NULL,NULL,'OTHER EXPENSE USD'),(300,'9.93.932.01.5','OTHER EXPENSE IDR',NULL,NULL,NULL,'OTHER EXPENSE IDR'),(301,'9.92.9210.01.3','Entertainment Expense USD',NULL,NULL,NULL,'Entertainment Expense USD'),(302,'9.91.9112.01.3','Repair & Maintenance Vehicle USD',NULL,NULL,NULL,'Repair & Maintenance Vehicle USD'),(303,'9.91.9114.01.3','DEVELOPMENT EXPENSE USD',NULL,NULL,NULL,'DEVELOPMENT EXPENSE USD'),(304,'1.12.124.02.5','BII-889',NULL,NULL,NULL,'BII IDR'),(305,'1.12.124.01.3','BII USD',NULL,NULL,NULL,'BII USD'),(306,'1.12.125.01.5','NISP',NULL,NULL,NULL,'NISP'),(307,'1.17.174.01.3','Piutang Direksi USD',NULL,NULL,NULL,'Piutang Direksi USD'),(308,'1.17.174.01.5','Piutang Direksi IDR',NULL,NULL,NULL,'Piutang Direksi IDR'),(309,'1.12.121.01.3','BCA USD',NULL,NULL,NULL,'BCA USD'),(310,'1.12.125.02.5','NISP 833',NULL,NULL,NULL,'NISP 833'),(311,'7.71.719.01.2','Test other AP account',NULL,NULL,NULL,'Test for other AP account'),(312,'1.16.164.04.2','Test other A/R account',NULL,NULL,NULL,'Test other A/R account'),(313,'1.16.164.01.3','A/R Other USD',NULL,NULL,NULL,'A/R Other USD'),(314,'1.16.164.01.5','A/R Other IDR',NULL,NULL,NULL,'A/R Other IDR'),(315,'7.71.719.01.3','A/P Other USD',NULL,NULL,NULL,'A/P Other USD'),(316,'7.71.719.01.5','A/P Other IDR',NULL,NULL,NULL,'A/P Other IDR'),(317,'1.12.121.02.3','BCA 0052-USD',NULL,NULL,NULL,'BCA 0052-USD'),(319,'1.12.121.03.5','BCA IDR 2288 (SE)',NULL,NULL,NULL,'BCA RUPIAH (SE)'),(320,'1.12.121.01.5','BCA IDR 7799 (AE)',NULL,NULL,NULL,'BCA RUPIAH (AE)'),(321,'1.12.121.04.5','BCA IDR 1898 SI AI',NULL,NULL,NULL,'BCA RUPIAH SI AI'),(322,'1.12.121.02.5','BCA Dipo',NULL,NULL,NULL,''),(323,'1.12.123.02.3','Citibank USD 5055 ( SE )',NULL,NULL,NULL,'Citibank SE (USD)'),(324,'1.12.123.03.3','Citibank USD 5123 (AE)',NULL,NULL,NULL,'Citibank AE (USD)'),(325,'1.12.123.04.3','Citibank USD 3373 SI AI',NULL,NULL,NULL,'Citibank SI AI (USD)'),(326,'1.11.112.02.3','Cash USD (SI / AI)',NULL,NULL,NULL,'Cash in USD (SI / AI)'),(327,'1.11.112.03.3','Cash USD (AE)',NULL,NULL,NULL,'Cash in USD (AE)'),(328,'1.11.112.04.3','Cash USD (SE)',NULL,NULL,NULL,'Cash in USD (SE)'),(329,'1.12.124.03.5','BII137',NULL,NULL,NULL,'BII IDR Semarang'),(330,'1.12.124.02.3','BII USD7799',NULL,NULL,NULL,'BII USD  Semarang'),(331,'1.12.124.03.3','BIIUSD7799',NULL,NULL,NULL,'BII USD Semarang'),(332,'1.11.111.01.5','PETTY  CASH',NULL,NULL,NULL,'PETTY CASH (IDR)'),(333,'1.12.123.01.3','CITISRG No.3000531133',NULL,NULL,NULL,'CITISRG No.3000531133'),(334,'1.12.127.01.3','Lippo Bank USD',NULL,NULL,NULL,'Lippo Bank USD'),(335,'1.12.127.01.5','Lippo Bank IDR',NULL,NULL,NULL,'Lippo Bank IDR'),(336,'1.11.112.01.5','Cash IDR (AR)',NULL,NULL,NULL,'Cash IDR (AR)');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountCategory`
--

LOCK TABLES `AccountCategory` WRITE;
/*!40000 ALTER TABLE `AccountCategory` DISABLE KEYS */;
INSERT INTO `AccountCategory` VALUES (1,1,'Current Assets','流动资产','1'),(2,1,'Fixed Assets','固定资产','2'),(3,2,'Current Liabilities','流动负债','3'),(4,2,'Other Liabilities','其他负债','4'),(5,3,'Capital','资本','5'),(6,4,'Sales','销售收入','6'),(7,4,'Other Sales','其他销售收入','7'),(8,5,'Operational & Other Expenses','其他费用','8'),(9,5,'Costs of Sales','销售成本','9');
/*!40000 ALTER TABLE `AccountCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountElement`
--

LOCK TABLES `AccountElement` WRITE;
/*!40000 ALTER TABLE `AccountElement` DISABLE KEYS */;
INSERT INTO `AccountElement` VALUES (1,'Asset','资产','1'),(2,'Liability','负债','2'),(3,'Equity','权益','3'),(4,'Revenue','收入','4'),(5,'Expense','费用','5');
/*!40000 ALTER TABLE `AccountElement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountGroup`
--

LOCK TABLES `AccountGroup` WRITE;
/*!40000 ALTER TABLE `AccountGroup` DISABLE KEYS */;
INSERT INTO `AccountGroup` VALUES (100,3,'100','CitiBank','CitiBank Accounts'),(101,1,'101','Cash','Cash Accounts'),(102,NULL,'102','Sea Import A/R','Sea Import A/R'),(109,NULL,'109','Sea Export Sales Trade','Sea Export Sales Trade'),(110,NULL,'110','Costs','Costs'),(111,NULL,'111','Tax','Tax collected'),(112,NULL,'112','Tax Paid','Tax Paid'),(113,1,'113','Safty Cash','Safty Cash Accounts'),(114,NULL,'114','HSBC','HSBC Accounts'),(115,NULL,'115','BCA','BCA Accounts'),(124,7,'124','Other Sales','Other Sales'),(132,9,'132','Cost of Other Sales','Cost of Other Sales'),(133,NULL,'133','Air Export Cost of Sales Trade','Air Export Cost of Sales Trade'),(134,NULL,'134','Sea Import Cost of Sales Trade','Sea Import Cost of Sales Trade'),(156,NULL,'156','Air Import Refund','Refund Accounts for Air Import'),(157,NULL,'157','Air Export Refund','Refund Accounts for Air Export'),(158,NULL,'158','Sea Import Refund','Refund Accounts for Sea Import'),(159,NULL,'159','Sea Export Refund','Refund Accounts for Sea Export'),(160,NULL,'160','Tools','Tools Accounts'),(161,NULL,'161','Equipment','Equipment Accounts'),(162,NULL,'162','Vehicle','Vehicle Accounts'),(163,NULL,'163','Accumulated Depreciation of Tools','Accumulated Depreciation of Tools Account'),(164,NULL,'164','Accumulated Depreciation of Equipment','Accumulated Depreciation of Equipment Account'),(165,NULL,'165','Accumulated Depreciation of Vehicle','Accumulated Depreciation of Vehicle Account'),(166,NULL,'166','Sea Export A/R','Sea Export A/R'),(167,NULL,'167','Air Import A/R','Air Import A/R'),(168,NULL,'168','Air Export A/R','Air Export A/R'),(169,NULL,'169','Sea Import A/P','Sea Import A/P'),(170,NULL,'170','Sea Export A/P','Sea Export A/P'),(171,NULL,'171','Air Import A/P','Air Import A/P'),(172,NULL,'172','Air Export A/P','Air Export A/P'),(173,NULL,'173','Sea Import Sales','Sea Import Sales'),(174,NULL,'174','Sea Export Sales','Sea Export Sales'),(175,NULL,'175','Air Import Sales','Air Import Sales'),(176,NULL,'176','Air Export Sales','Air Export Sales'),(178,7,'178','Other Sales A/R','Other Sales A/R'),(179,NULL,'179','Air Import A/R Trade','Air Import A/R Trade'),(181,NULL,'181','Sea Import Cost of Sales','Sea Import Cost of Sales'),(182,NULL,'182','Sea Export Cost of Sales','Sea Export Cost of Sales'),(183,NULL,'183','Air Import Cost of Sales','Air Import Cost of Sales'),(184,NULL,'184','Air Export A/P Others Domestic','A/P Others Domestic Accounts for Air Export'),(185,NULL,'185','Air Export Cost of Sales','Air Export Cost of Sales'),(186,7,'186','Other Sales A/P','Other Sales A/P'),(191,NULL,'191','Other Liabilities','Other Liabilities'),(192,NULL,'192','Telephone, Internet & Fax Expense','Telephone, Internet & Fax Expense Account'),(193,NULL,'193','Gasoline Expense','Gasoline Expense Account'),(194,NULL,'194','Handling Expense','Handling Expense Account'),(195,NULL,'195','Transportation Expense','Transportation Expense Account'),(196,NULL,'196','Advertising Expense','Advertising Expense Account'),(197,NULL,'197','Entertainment Expense','Entertainment Expense Account'),(198,NULL,'198','Travel Expense','Travel Expense Account'),(199,NULL,'199','Postage and Delivery Expense','Postage and Delivery Expense Account'),(200,NULL,'200','Insurance Expense','Insurance Expense Account'),(201,NULL,'201','Vehicle Depreciation Expense','Vehicle Depreciation Expense Account'),(202,NULL,'202','Bad Debt Expense-Overseas','Bad Debt Expense-Overseas Account'),(203,NULL,'203','Bad Debt Expense-Domestic','Bad Debt Expense-Domestic Account'),(204,NULL,'204','Bonus Expense','Bonus Expense Account'),(205,NULL,'205','Miscellaneous Sales Expense','Miscellaneous Sales Expense Account'),(206,NULL,'206','Printing & Reproduction Expense','Printing & Reproduction Expense Account'),(207,NULL,'207','Office Supplies Expense','Office Supplies Expense Account'),(208,NULL,'208','Building Rent','Building Rent Account'),(209,NULL,'209','Tax Expense','Tax Expense Account'),(210,NULL,'210','Janitorial Expense','Janitorial Expense Account'),(211,NULL,'211','Drinking Water Expense','Drinking Water Expense Account'),(212,NULL,'212','Computer Repairs Expense','Computer Repairs Expense Account'),(213,NULL,'213','Payroll Expense','Payroll Expense Account'),(214,NULL,'214','Depreciation Expense','Depreciation Expense Account'),(215,NULL,'215','Miscellaneous Gen & Admin Expense','Miscellaneous Gen & Admin Expense Account'),(216,3,'216','Bank Service Charges','Bank Service Charges Account'),(217,NULL,'217','Other Income','Other Income'),(218,NULL,'218','Container Guarantee Receivable','Container Guarantee Receivable'),(219,NULL,'219','Container Guarantee Payable','Container Guarantee Payable'),(220,NULL,'220','Interest Income','Interest Income'),(221,NULL,'221','Repair & Maintenance Expense','Repair & Maintenance Expense'),(222,NULL,'222','Repair & Maintenance Vehicle','Repair & Maintenance Vehicle'),(223,NULL,'223','Advance Payment','Advance Payment'),(224,NULL,'224','Advance Received','Advance Received'),(225,NULL,'225','Sea Import Handling Expense','Sea Import Handling Expense'),(226,NULL,'226','Sea Export Handling Expense','Sea Export Handling Expense'),(227,NULL,'227','Air Import Handling Expense','Air Import Handling Expense'),(228,NULL,'228','Air Export Handling Expense','Air Export Handling Expense'),(229,NULL,'229','Consol Sea Export Handling Expense','Consol SE Handling Expense'),(230,NULL,'230','Consol Air Export Handling Expense','Consol AE Handling Expense'),(231,5,'231','Other Receivable','Other Receivable'),(232,NULL,'232','BII','BII Account'),(233,NULL,'233','OTHER EXPENSE','OTHER EXPENSE'),(234,NULL,'234','GEN & ADM EXPENSE','GEN & ADM EXPENSE'),(235,NULL,'235','NISP','NISP'),(236,NULL,'236','Piutang Direksi','Piutang Direksi'),(238,NULL,'238','A/P Other','A/P Other'),(239,NULL,'239','A/R Other','A/R Other'),(240,NULL,'240','Piutang Direksi','Piutang Direksi'),(241,3,'241','Lippo Bank','Lippo Bank Accounts');
/*!40000 ALTER TABLE `AccountGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AccountType`
--

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
INSERT INTO `AccountType` VALUES (1,1,'1','Cash','库存现金'),(2,1,'2','Prepaid Expense','预付费用'),(3,1,'3','Bank','银行存款'),(4,1,'4','Account Receivable','应收帐款'),(5,1,'5','Other Receivable','其他应收帐款'),(6,3,'6','Tax collected','税费集'),(7,6,'7','Other Sales','其他收入'),(8,8,'8','Other Expenses','其他费用'),(9,9,'9','Cost of Other Sales','其他业务成本'),(10,8,'10','Gen & Adm Expenses','行政费用'),(11,8,'11','Sales Expenses','主营费用'),(12,2,'12','Inventory','库存'),(13,2,'13','Accumulated Depreciation','累计折旧'),(14,4,'14','Other Liabilities','其他负债'),(15,5,'15','Common Stock','股票'),(16,5,'16','Retained Earning','留存收益'),(17,9,'17','Refund','退'),(18,6,'18','Sales','主营收入'),(19,9,'19','Cost of Sales','业务成本'),(20,3,'20','Account Payable','应付帐款'),(21,2,'21','Properties','财产'),(22,7,'22','Other Income','其他收入'),(23,4,'23','Other Payable','其他应付款'),(24,9,'24','Handling Expense','处理费用');
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Action`
--

LOCK TABLES `Action` WRITE;
/*!40000 ALTER TABLE `Action` DISABLE KEYS */;
INSERT INTO `Action` VALUES (1,'Login','Login'),(2,'Operation','Operation'),(3,'My','My'),(4,'AccountCategory','AccountCategory'),(5,'File','File'),(6,'Account','Account'),(7,'Profile','Profile'),(8,'Setup','Setup'),(9,'Accounting','Accounting'),(10,'AccountGroup','AccountGroup'),(11,'Main','Main'),(12,'Index','Index'),(13,'Activity','Activity'),(14,'ForumThread','ForumThread'),(15,'AccountElement','AccountElement'),(16,'AccountType','AccountType');
/*!40000 ALTER TABLE `Action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AuthGroup`
--

LOCK TABLES `AuthGroup` WRITE;
/*!40000 ALTER TABLE `AuthGroup` DISABLE KEYS */;
INSERT INTO `AuthGroup` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,1,31),(32,1,32),(33,1,33),(34,1,34),(35,1,35),(36,1,36),(37,1,37),(38,1,38),(39,1,39);
/*!40000 ALTER TABLE `AuthGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AuthRole`
--

LOCK TABLES `AuthRole` WRITE;
/*!40000 ALTER TABLE `AuthRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `AuthRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Branch`
--

LOCK TABLES `Branch` WRITE;
/*!40000 ALTER TABLE `Branch` DISABLE KEYS */;
INSERT INTO `Branch` VALUES (1,'00001','成都','CD','2010-11-17','2010-11-17');
/*!40000 ALTER TABLE `Branch` ENABLE KEYS */;
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
-- Dumping data for table `Married`
--

LOCK TABLES `Married` WRITE;
/*!40000 ALTER TABLE `Married` DISABLE KEYS */;
INSERT INTO `Married` VALUES (1,'单身'),(2,'已婚'),(3,'离异');
/*!40000 ALTER TABLE `Married` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Method`
--

LOCK TABLES `Method` WRITE;
/*!40000 ALTER TABLE `Method` DISABLE KEYS */;
INSERT INTO `Method` VALUES (1,1,'processRegister','processRegister','Y'),(2,1,'processLogin','processLogin','Y'),(3,1,'promptRegister','promptRegister','Y'),(4,1,'processLogout','processLogout','Y'),(5,1,'promptLogin','promptLogin','Y'),(6,2,'menu','menu','Y'),(7,3,'general','general','Y'),(8,3,'promptChangePassword','promptChangePassword','Y'),(9,3,'promptUploadImages','promptUploadImages','Y'),(10,3,'edit','edit','Y'),(11,3,'processUploadImages','processUploadImages','Y'),(12,3,'update','update','Y'),(13,4,'list','list','Y'),(14,5,'tree','tree','Y'),(15,5,'processDelete','processDelete','Y'),(16,5,'download','download','Y'),(17,5,'upload','upload','Y'),(18,5,'processUpload','processUpload','Y'),(19,5,'mkdir','mkdir','Y'),(20,5,'retrieve','retrieve','Y'),(21,6,'list','list','Y'),(22,7,'menu','menu','Y'),(23,8,'menu','menu','Y'),(24,9,'menu','menu','Y'),(25,10,'list','list','Y'),(26,11,'menu','menu','Y'),(27,12,'promptLogin','promptLogin','Y'),(28,12,'promptIndex','promptIndex','Y'),(29,13,'forum','forum','Y'),(30,13,'search','search','Y'),(31,13,'message','message','Y'),(32,13,'blog','blog','Y'),(33,13,'talk','talk','Y'),(34,14,'listThread','listThread','Y'),(35,14,'promptCreateThread','promptCreateThread','Y'),(36,14,'promptCreateMessage','promptCreateMessage','Y'),(37,14,'listMessage','listMessage','Y'),(38,15,'list','list','Y'),(39,16,'list','list','Y');
/*!40000 ALTER TABLE `Method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `NoteType`
--

LOCK TABLES `NoteType` WRITE;
/*!40000 ALTER TABLE `NoteType` DISABLE KEYS */;
INSERT INTO `NoteType` VALUES (1,'Invoice','Invoice','','Invoice','ACTR'),(2,'DebitNote','Debit Note','DN','DebitNote','ACTR'),(3,'FO','Formulir Order','FO','Formulir Order','ACTP'),(4,'CreditNote','Credit Note','CN','CreditNote','ACTP'),(5,'OfficialReceipt','Official Receipt','OR','Official Receipt','ACTR'),(6,'Refund','Refund','CNR','Refund credit for job accounting.','ACTP'),(7,'Adjustment','Account Adjustment','ADJ','Account Adjustment',''),(8,'GeneralReceipt','General Receipt','GR','General Accounting Receipt ','GGAR'),(9,'GeneralVoucher','General Voucher','GV',' General Accounting Payment','GGAP'),(10,'Handling','Handling','HDL','Airport and/or Seaport Handling Costs','ACTP'),(11,'Voucher','Voucher','V','Voucher used by accounting sector only','GACTP'),(12,'Receipt','Receipt','REC','Receipt used in accounting sector only.','GACTR');
/*!40000 ALTER TABLE `NoteType` ENABLE KEYS */;
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
-- Dumping data for table `UserGroup`
--

LOCK TABLES `UserGroup` WRITE;
/*!40000 ALTER TABLE `UserGroup` DISABLE KEYS */;
INSERT INTO `UserGroup` VALUES (1,'admin',NULL);
/*!40000 ALTER TABLE `UserGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
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

-- Dump completed on 2010-11-17 18:54:42
