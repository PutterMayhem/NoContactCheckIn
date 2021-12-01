-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: uCheckIn
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Booking` (
  `conf_ID` int NOT NULL,
  `cust_email` varchar(100) DEFAULT NULL,
  `room_num` int DEFAULT NULL,
  `stay_length` int DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `cctoken` int DEFAULT NULL,
  PRIMARY KEY (`conf_ID`),
  KEY `cust_email` (`cust_email`),
  KEY `room_num` (`room_num`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`cust_email`) REFERENCES `Customer` (`cust_Email`) ON DELETE CASCADE,
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`room_num`) REFERENCES `Room` (`room_num`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
INSERT INTO `Booking` VALUES (36648,'mahad@gmail.com',200,11,'2021-11-29','2021-12-10',NULL),(50804,'mahad@gmail.com',200,4,'2021-11-28','2021-12-02',NULL),(51655,'mahad@gmail.com',200,7,'2021-12-05','2021-12-12',NULL),(54675,'mahad@gmail.com',310,3,'2021-12-05','2021-12-08',NULL),(108850,'test@email.com',200,7,'2021-11-17','2021-11-24',NULL),(126846,'mahad@gmail.com',100,5,'2021-12-05','2021-12-10',NULL),(152409,'mahad@gmail.com',200,2,'2021-11-23','2021-11-25',NULL),(225538,'mahad@gmail.com',100,2,'2021-12-05','2021-12-07',NULL),(231099,'mahad@gmail.com',310,5,'2021-12-12','2021-12-17',NULL),(245211,'mahad@gmail.com',200,16,'2021-12-12','2021-12-28',NULL),(259357,'mahad@gmail.com',220,5,'2021-12-05','2021-12-10',NULL),(294140,'mahad@gmail.com',100,10,'2021-12-05','2021-12-15',NULL),(307909,'mahad@gmail.com',100,7,'2021-11-21','2021-11-28',NULL),(370756,'mahad@gmail.com',220,3,'2021-12-05','2021-12-08',NULL),(379091,'mahad@gmail.com',200,5,'2021-12-05','2021-12-10',NULL),(404688,'mahad@gmail.com',101,14,'2021-12-01','2021-12-15',NULL),(428089,'mahad@gmail.com',101,4,'2021-11-28','2021-12-02',NULL),(430270,'mahad@gmail.com',100,10,'2021-11-30','2021-12-10',NULL),(436352,'mahad@gmail.com',101,11,'2021-11-28','2021-12-09',NULL),(475549,'mahad@gmail.com',101,3,'2021-11-28','2021-12-01',NULL),(485750,'mahad@gmail.com',200,3,'2021-11-28','2021-12-01',NULL),(608650,'mahad@gmail.com',101,5,'2021-12-05','2021-12-10',NULL),(630340,'mahad@gmail.com',220,5,'2021-11-28','2021-12-03',NULL),(689662,'mahad@gmail.com',200,4,'2021-11-28','2021-12-02',NULL),(715559,'mahad@gmail.com',100,5,'2021-12-05','2021-12-10',NULL),(729094,'mahad@gmail.com',101,4,'2021-12-05','2021-12-09',NULL),(776521,'mahad@gmail.com',220,10,'2021-12-06','2021-12-16',NULL),(788154,'mahad@gmail.com',101,2,'2021-12-05','2021-12-07',NULL),(816996,'mahad@gmail.com',100,3,'2021-11-24','2021-11-27',NULL),(842080,'mahad@gmail.com',101,4,'2021-11-21','2021-11-25',NULL),(877268,'mahad@gmail.com',200,5,'2021-11-28','2021-12-03',NULL),(898974,'mahad@gmail.com',101,4,'2021-11-28','2021-12-02',NULL),(902003,'mahad@gmail.com',100,18,'2021-12-05','2021-12-23',NULL),(927442,'mahad@gmail.com',101,5,'2021-11-28','2021-12-03',NULL),(987949,'mahad@gmail.com',220,5,'2021-11-21','2021-11-26',NULL),(998637,'mahad@gmail.com',101,9,'2021-12-05','2021-12-14',NULL);
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `cust_ID` int NOT NULL AUTO_INCREMENT,
  `cust_Fname` varchar(20) DEFAULT NULL,
  `cust_Lname` varchar(20) DEFAULT NULL,
  `cust_Phone` varchar(10) DEFAULT NULL,
  `cust_Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cust_ID`),
  KEY `cust_Email` (`cust_Email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Test','Testingson','null','test@email.com'),(6,'Mahad','Hussein','null','mahad@gmail.com');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `emp_ID` int NOT NULL AUTO_INCREMENT,
  `emp_Fname` varchar(20) DEFAULT NULL,
  `emp_Lname` varchar(20) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`emp_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Chuck','Gustner',1),(2,'Mahad','Hussein',0);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Items` (
  `item_ID` int NOT NULL AUTO_INCREMENT,
  `item_Name` varchar(30) DEFAULT NULL,
  `item_price` float DEFAULT NULL,
  PRIMARY KEY (`item_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Items`
--

LOCK TABLES `Items` WRITE;
/*!40000 ALTER TABLE `Items` DISABLE KEYS */;
INSERT INTO `Items` VALUES (11,'Chicken Kiev',13.99),(12,'Caesar Salad',8.99),(13,'New York Strip Steak',16.99),(14,'Pork Ribs',17.99),(15,'Talapia',6.99),(16,'Shrimp Scampi',9.99),(17,'Pepperoni Pizza',12.99),(18,'Chocolate Cake',6.99),(19,'New York Cheese Cake',9.99),(20,'French Silk Pie',8.99),(21,'Toiletries',NULL),(22,'Personal Care Items',NULL),(23,'Coffee Kit',NULL),(24,'Tissue Box',NULL),(25,'Bathroom Robe and Slippers',NULL),(26,'Fresh Pillows',NULL);
/*!40000 ALTER TABLE `Items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Request`
--

DROP TABLE IF EXISTS `Request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Request` (
  `req_ID` int NOT NULL AUTO_INCREMENT,
  `req_DateTime` datetime DEFAULT NULL,
  `req_FullfillDateTime` datetime DEFAULT NULL,
  `fulfilled` tinyint(1) DEFAULT NULL,
  `conf_ID` int DEFAULT NULL,
  PRIMARY KEY (`req_ID`),
  KEY `conf_ID` (`conf_ID`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`conf_ID`) REFERENCES `Booking` (`conf_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Request`
--

LOCK TABLES `Request` WRITE;
/*!40000 ALTER TABLE `Request` DISABLE KEYS */;
INSERT INTO `Request` VALUES (1,'2021-11-30 00:00:00',NULL,0,987949),(2,'2021-11-30 23:57:48',NULL,0,51655),(3,'2021-12-01 00:00:17',NULL,0,259357),(4,'2021-12-01 00:04:58',NULL,0,231099),(5,'2021-12-01 00:07:23',NULL,0,259357);
/*!40000 ALTER TABLE `Request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RequestItems`
--

DROP TABLE IF EXISTS `RequestItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RequestItems` (
  `reqitem_ID` int NOT NULL AUTO_INCREMENT,
  `req_ID` int DEFAULT NULL,
  `item_ID` int DEFAULT NULL,
  PRIMARY KEY (`reqitem_ID`),
  KEY `item_ID_idx` (`item_ID`),
  KEY `reqID_idx` (`req_ID`),
  CONSTRAINT `item_ID` FOREIGN KEY (`item_ID`) REFERENCES `Items` (`item_ID`) ON UPDATE RESTRICT,
  CONSTRAINT `reqID` FOREIGN KEY (`req_ID`) REFERENCES `Request` (`req_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RequestItems`
--

LOCK TABLES `RequestItems` WRITE;
/*!40000 ALTER TABLE `RequestItems` DISABLE KEYS */;
INSERT INTO `RequestItems` VALUES (3,3,23),(4,4,22),(5,4,13),(6,5,23);
/*!40000 ALTER TABLE `RequestItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RequestType`
--

DROP TABLE IF EXISTS `RequestType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RequestType` (
  `reqType_ID` int NOT NULL,
  `reqType_Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`reqType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RequestType`
--

LOCK TABLES `RequestType` WRITE;
/*!40000 ALTER TABLE `RequestType` DISABLE KEYS */;
INSERT INTO `RequestType` VALUES (1,'Food Service'),(2,'Hotel Amenities');
/*!40000 ALTER TABLE `RequestType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `room_num` int NOT NULL,
  `roomType_ID` varchar(4) DEFAULT NULL,
  `room_status` tinyint(1) DEFAULT NULL,
  `room_active` tinyint(1) DEFAULT NULL,
  `floor` int DEFAULT NULL,
  PRIMARY KEY (`room_num`),
  KEY `roomType_ID` (`roomType_ID`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`roomType_ID`) REFERENCES `RoomType` (`roomType_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (100,'SK',1,1,1),(101,'DQ',0,1,1),(200,'DQ',0,1,2),(220,'SKS',0,1,2),(310,'DQS',0,1,3);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RoomType`
--

DROP TABLE IF EXISTS `RoomType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RoomType` (
  `roomType_ID` varchar(4) NOT NULL,
  `king` int DEFAULT NULL,
  `queen` int DEFAULT NULL,
  `full` int DEFAULT NULL,
  `pull_Out` int DEFAULT NULL,
  `suite` tinyint(1) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  PRIMARY KEY (`roomType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RoomType`
--

LOCK TABLES `RoomType` WRITE;
/*!40000 ALTER TABLE `RoomType` DISABLE KEYS */;
INSERT INTO `RoomType` VALUES ('DQ',0,2,0,0,0,110),('DQS',0,2,0,1,1,200),('SK',1,0,0,0,0,100),('SKS',1,0,0,1,1,175);
/*!40000 ALTER TABLE `RoomType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'uCheckIn'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-01 11:06:05
