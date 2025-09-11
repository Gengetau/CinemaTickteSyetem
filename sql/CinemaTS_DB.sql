-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinemats
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_info` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) DEFAULT NULL,
  `adminPass` varchar(20) DEFAULT NULL,
  `adminPermission` int(11) DEFAULT NULL,
  `adminState` enum('正常','注销') NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

LOCK TABLES `admin_info` WRITE;
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES (1001,'Admin001#','123456',1,'正常'),(1002,'Admin002!','112233',2,'正常'),(1003,'Admin003$','123432',3,'正常'),(1004,'Admin003%','122323',4,'正常');
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema_info`
--

DROP TABLE IF EXISTS `cinema_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cinema_info` (
  `cinemaId` bigint(20) NOT NULL AUTO_INCREMENT,
  `cinemaName` varchar(20) DEFAULT NULL,
  `releaseTime` date NOT NULL,
  `cinemaPrice` decimal(10,2) DEFAULT NULL,
  `movieTime` datetime DEFAULT NULL,
  `cinemaState` enum('上架','下架') DEFAULT NULL,
  `cinemaSeats` int(11) DEFAULT NULL,
  PRIMARY KEY (`cinemaId`),
  UNIQUE KEY `cinemaName` (`cinemaName`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema_info`
--

LOCK TABLES `cinema_info` WRITE;
/*!40000 ALTER TABLE `cinema_info` DISABLE KEYS */;
INSERT INTO `cinema_info` VALUES (101,'Neko的奇幻冒险','2025-09-09',50.00,'2025-09-12 14:30:00','上架',20),(102,'代码世界的猫','2025-09-15',65.50,'2025-09-16 19:00:00','上架',20),(103,'爪哇岛传说','2025-08-20',45.00,'2025-09-01 10:00:00','下架',20);
/*!40000 ALTER TABLE `cinema_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_info`
--

DROP TABLE IF EXISTS `log_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_info` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `logInfo` varchar(500) DEFAULT NULL,
  `logDate` datetime DEFAULT NULL,
  `logName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_info`
--

LOCK TABLES `log_info` WRITE;
/*!40000 ALTER TABLE `log_info` DISABLE KEYS */;
INSERT INTO `log_info` VALUES (1,'管理员 [Admin002!] 添加了新电影 [Neko的奇幻冒险]','2025-09-09 14:00:00','Admin002!'),(2,'管理员 [Admin004%] 将电影 [爪哇岛传说] 的状态修改为 [下架]','2025-09-09 16:25:10','Admin004%'),(3,'管理员 [Admin003$] 修改了用户 [NekoChan#] 的VIP等级','2025-09-09 17:10:05','Admin003$');
/*!40000 ALTER TABLE `log_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_order`
--

DROP TABLE IF EXISTS `my_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_order` (
  `orderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `cinemaId` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `fk_cinema_info_cinemaId` (`cinemaId`),
  KEY `fk_user_info_userid` (`userid`),
  CONSTRAINT `fk_cinema_info_cinemaId` FOREIGN KEY (`cinemaId`) REFERENCES `cinema_info` (`cinemaId`),
  CONSTRAINT `fk_user_info_userid` FOREIGN KEY (`userid`) REFERENCES `my_user_info` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_order`
--

LOCK TABLES `my_order` WRITE;
/*!40000 ALTER TABLE `my_order` DISABLE KEYS */;
INSERT INTO `my_order` VALUES (1,101,10001,99.80),(2,102,10001,65.37),(3,102,10003,127.07);
/*!40000 ALTER TABLE `my_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_order_info`
--

DROP TABLE IF EXISTS `my_order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_order_info` (
  `orderInfoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `orderState` enum('未观看','已观看','订单已取消') DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  PRIMARY KEY (`orderInfoId`),
  KEY `fk_MY_ORDER_orderId` (`orderId`),
  CONSTRAINT `fk_MY_ORDER_orderId` FOREIGN KEY (`orderId`) REFERENCES `my_order` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_order_info`
--

LOCK TABLES `my_order_info` WRITE;
/*!40000 ALTER TABLE `my_order_info` DISABLE KEYS */;
INSERT INTO `my_order_info` VALUES (1,1,'22','未观看','2025-09-09'),(2,1,'23','未观看','2025-09-09'),(3,2,'11','未观看','2025-09-09'),(4,3,'33','已观看','2025-09-08'),(5,3,'34','已观看','2025-09-08');
/*!40000 ALTER TABLE `my_order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_user`
--

DROP TABLE IF EXISTS `my_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `userpass` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_user`
--

LOCK TABLES `my_user` WRITE;
/*!40000 ALTER TABLE `my_user` DISABLE KEYS */;
INSERT INTO `my_user` VALUES (10001,'Master01@','666888'),(10002,'NekoChan#','123123'),(10003,'MovieLover66!','555555');
/*!40000 ALTER TABLE `my_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_user_info`
--

DROP TABLE IF EXISTS `my_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_user_info` (
  `userinfoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `userMoney` decimal(10,2) DEFAULT NULL,
  `userPoint` bigint(20) DEFAULT NULL,
  `userVip` enum('无','白银VIP','黄金VIP','钻石VIP','黑钻VIP') NOT NULL,
  `userPhone` varchar(11) DEFAULT NULL,
  `userState` enum('正常','注销') NOT NULL,
  PRIMARY KEY (`userinfoId`),
  UNIQUE KEY `userid` (`userid`),
  CONSTRAINT `fk_userinfo_userid` FOREIGN KEY (`userid`) REFERENCES `my_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_user_info`
--

LOCK TABLES `my_user_info` WRITE;
/*!40000 ALTER TABLE `my_user_info` DISABLE KEYS */;
INSERT INTO `my_user_info` VALUES (10001,10001,150.50,1280,'钻石VIP','13800138001','正常'),(10002,10002,25.00,150,'无','13800138002','正常'),(10003,10003,520.00,666,'黄金VIP','13800138003','正常');
/*!40000 ALTER TABLE `my_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_info`
--

DROP TABLE IF EXISTS `vip_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_info` (
  `vipId` int(11) NOT NULL AUTO_INCREMENT,
  `vipPoint` int(11) DEFAULT NULL,
  `vipName` enum('无','白银VIP','黄金VIP','钻石VIP','黑钻VIP') DEFAULT NULL,
  `vipDiscount` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`vipId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_info`
--

LOCK TABLES `vip_info` WRITE;
/*!40000 ALTER TABLE `vip_info` DISABLE KEYS */;
INSERT INTO `vip_info` VALUES (1,0,'无',1.00),(2,200,'白银VIP',0.99),(3,500,'黄金VIP',0.97),(4,1000,'钻石VIP',0.95),(5,3000,'黑钻VIP',0.93);
/*!40000 ALTER TABLE `vip_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-11 13:32:30
