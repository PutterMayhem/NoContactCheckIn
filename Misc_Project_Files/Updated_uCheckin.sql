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
INSERT INTO `Items` VALUES (11,'Chicken Kiev',13.99),(12,'Caesar Salad',8.99),(13,'New York Strip Steak',16.99),(14,'Pork Ribs',17.99),(15,'Talapia',6.99),(16,'Shrimp Scampi',9.99),(17,'Pepperoni Pizza',12.99),(18,'Chocolate Cake',6.99),(19,'New York Cheese Cake',9.99),(20,'French Silk Pie',8.99),(21,'Toiletries',0),(22,'Personal Care Items',0),(23,'Coffee Kit',0),(24,'Tissue Box',0),(25,'Bathroom Robe and Slippers',0,(26,'Fresh Pillows',0));
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


ALTER TABLE `uCheckIn`.`RequestItems` 
ADD COLUMN `fulfilled` TINYINT(1) NULL AFTER `item_ID`;

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
