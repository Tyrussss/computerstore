-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: computerstore
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `BrandID` int NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `DelStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`BrandID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Dell',1),(2,'Apple',1),(3,'HP',1),(4,'Google',1);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `ProductID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `TotalPrice` float DEFAULT NULL,
  PRIMARY KEY (`CartID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (5,3,36,1,1188,1188),(6,1,36,1,1499,1499),(18,6,47,1,1188,1188),(19,5,47,1,1249,1249),(23,6,11,1,1188,1188),(24,5,11,1,1249,1249),(32,3,12,1,1188,1188),(33,5,12,1,1249,1249),(34,7,54,1,2999,2999),(36,3,54,1,1188,1188);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `DelStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Laptops',1),(2,'Desktops',1),(3,'Accessories',1),(4,'Test1',1),(5,'Test',1),(6,'Tesst 3',1),(7,'abc',1),(8,'Phone',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `DiscountID` int NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Percent` int DEFAULT NULL,
  PRIMARY KEY (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discountdetail`
--

DROP TABLE IF EXISTS `discountdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discountdetail` (
  `ProductID` int NOT NULL,
  `DiscountID` int NOT NULL,
  `Percent` int NOT NULL,
  PRIMARY KEY (`ProductID`,`DiscountID`),
  KEY `DiscountID` (`DiscountID`),
  CONSTRAINT `discountdetail_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  CONSTRAINT `discountdetail_ibfk_2` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discountdetail`
--

LOCK TABLES `discountdetail` WRITE;
/*!40000 ALTER TABLE `discountdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `discountdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `ProductID` int NOT NULL,
  `OrderID` int NOT NULL,
  `Quantity` int NOT NULL,
  `DiscountID` int DEFAULT NULL,
  `SubTotal` decimal(10,2) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ProductID`,`OrderID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `OrderStatus` smallint NOT NULL,
  `Created_Date` date NOT NULL,
  `payment` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'2024-04-25','4'),(2,1,1,'2024-04-25','4'),(3,1,1,'2024-04-25','78329270'),(4,1,1,'2024-04-25','4'),(5,1,1,'2024-04-25','74745464'),(6,1,1,'2024-04-25','41146597'),(7,1,1,'2024-04-25','87501573'),(8,1,1,'2024-05-10','86035449');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`csdb`@`localhost`*/ /*!50003 TRIGGER `orders_BEFORE_INSERT` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
	IF NEW.Created_Date IS NULL THEN
        SET NEW.Created_Date = CURDATE();
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `ProductDetails` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ReleasedDate` date NOT NULL,
  `Stock` int NOT NULL,
  `Warranty` int DEFAULT NULL,
  `Price` decimal(10,2) NOT NULL,
  `DelStatus` tinyint(1) NOT NULL,
  `BrandID` int NOT NULL,
  `CategoryID` int NOT NULL,
  `DiscountID` int DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `product_name_index` (`ProductName`),
  KEY `BrandID` (`BrandID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`BrandID`) REFERENCES `brand` (`BrandID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dell XPS 13 Laptop','Latest 12th Gen Intel Core i7 processor, 16GB RAM, 512GB SSD','2023-05-03',10,12,1499.99,1,1,1,NULL),(2,'Apple MacBook Air','M2 chip, 8GB RAM, 256GB SSD','2023-05-03',15,24,1249.00,1,2,1,NULL),(3,'Samsung Galaxy S23 Ultra','1TB storage, 120Hz display, Quad camera system','2023-05-03',20,12,1188.88,1,3,2,NULL),(4,'Dell XPS 13 Laptop','Latest 12th Gen Intel Core i7 processor, 16GB RAM, 512GB SSD','2023-05-03',10,12,1499.00,1,1,1,NULL),(5,'Apple MacBook Air','M2 chip, 8GB RAM, 256GB SSD','2023-05-03',15,24,1249.00,1,2,1,2),(6,'Samsung Galaxy S23 Ultra','1TB storage, 120Hz display, Quad camera system','2023-05-03',20,12,1188.88,1,3,2,2),(7,'iPhone 15 Pro Max','Titanium','2024-05-08',20,12,2999.00,1,2,8,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`csdb`@`localhost`*/ /*!50003 TRIGGER `product_BEFORE_INSERT` BEFORE INSERT ON `product` FOR EACH ROW BEGIN
	IF NEW.ReleasedDate IS NULL THEN
        SET NEW.ReleasedDate = CURDATE();
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `productimage`
--

DROP TABLE IF EXISTS `productimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productimage` (
  `ProductimageID` int NOT NULL AUTO_INCREMENT,
  `ProductID` int NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Main` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ProductimageID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productimage_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productimage`
--

LOCK TABLES `productimage` WRITE;
/*!40000 ALTER TABLE `productimage` DISABLE KEYS */;
INSERT INTO `productimage` VALUES (1,1,'product06.png',1),(2,1,'product06.png',0),(3,1,'product06.png',0),(4,1,'product06.png',0),(5,1,'product06.png',0),(6,1,'product06.png',0),(7,2,'product01.png',1),(8,2,'product03.png',0),(9,2,'product01.png',0),(10,2,'product03.png',0),(11,2,'product01.png',0),(12,2,'product03.png',0),(13,2,'product01.png',0),(14,3,'product07.png',1),(15,3,'product07.png',0),(16,3,'product07.png',0),(17,3,'product07.png',0),(18,3,'product07.png',0),(19,3,'product07.png',0),(20,3,'product07.png',0),(21,3,'product07.png',0),(22,4,'product06.png',1),(23,4,'product06.png',0),(24,4,'product06.png',0),(25,4,'product06.png',0),(26,4,'product06.png',0),(27,4,'product06.png',0),(28,4,'product06.png',0),(29,4,'product06.png',0),(30,5,'product01.png',1),(31,5,'product03.png',0),(32,5,'product01.png',0),(33,5,'product03.png',0),(34,5,'product01.png',0),(35,5,'product03.png',0),(36,5,'product01.png',0),(37,6,'product07.png',1),(38,6,'product07.png',0),(39,6,'product07.png',0),(40,6,'product07.png',0),(41,6,'product07.png',0),(42,6,'product07.png',0),(43,6,'product07.png',0),(44,6,'product07.png',0),(45,7,'iphone_15.jpg',1),(46,7,'iphone_15.jpg',0),(47,7,'iphone_15.jpg',0),(48,7,'iphone_15.jpg',0),(49,7,'iphone_15.jpg',0),(50,7,'iphone_15.jpg',0),(51,7,'iphone_15.jpg',0),(52,7,'iphone_15.jpg',0),(53,7,'iphone_15.jpg',0);
/*!40000 ALTER TABLE `productimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productinfo`
--

DROP TABLE IF EXISTS `productinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productinfo` (
  `ProductInfoID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `ProductID` int DEFAULT NULL,
  PRIMARY KEY (`ProductInfoID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productinfo_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productinfo`
--

LOCK TABLES `productinfo` WRITE;
/*!40000 ALTER TABLE `productinfo` DISABLE KEYS */;
INSERT INTO `productinfo` VALUES (23,'Dell XPS','12th Gen Intel Core i7 processor',1),(24,'Dell XPS','16GB RAM',1),(25,'Dell XPS','512GB SSD',1),(26,'Dell XPS','RTX 4060 16GB',1),(27,'Apple MacBook Air','M2 chip',2),(28,'Apple MacBook Air','8GB RAM',2),(29,'Apple MacBook Air','256GB SSD',2),(30,'Samsung Galaxy S23 Ultra','1TB storage',3),(31,'Samsung Galaxy S23 Ultra','120Hz display',3),(32,'Samsung Galaxy S23 Ultra','Quad camera system',3),(33,'Dell XPS','12th Gen Intel Core i7 processor',4),(34,'Dell XPS','16GB RAM',4),(35,'Dell XPS','512GB SSD',4),(36,'Apple MacBook Air','M2 chip',5),(37,'Apple MacBook Air','8GB RAM',5),(38,'Apple MacBook Air','256GB SSD',5),(39,'Samsung Galaxy S23 Ultra','1TB storage',6),(40,'Samsung Galaxy S23 Ultra','120Hz display',6),(41,'Samsung Galaxy S23 Ultra','Quad camera system',6),(42,'iPhone 15 Pro Max','Apple A17 Pro 6 nhân',7),(43,'iPhone 15 Pro Max','8 GB',7),(44,'iPhone 15 Pro Max','Super Retina XDR OLED',7),(45,'iPhone 15 Pro Max','12MP, ƒ/1.9',7);
/*!40000 ALTER TABLE `productinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `ReviewContent` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Reply` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `FullName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Phone` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Address` varchar(125) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Role` tinyint(1) DEFAULT NULL,
  `Newsletter` smallint DEFAULT NULL,
  `Avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'phong','','user1@example.com','Phong Cry','987654321','',0,0,'Phong1.png'),(2,'user2','','user2@example.com','Jane Smith','111 111 1111','',0,0,'avatar.png'),(3,'teotony','','tyrvszx@gamil.com','Tony Teo','111 222 3456','1738 RemyMartin',0,0,'avatar.png'),(11,'pepsi','123','pepsi@dog.cat','Lionel Pepsi','1111112345','Buenos Aires',0,0,'logo1.png'),(12,'coke','12345','coke@dog.cat','Coca Cola','999 666 6789','111 Senter Road',1,1,'logo1.png'),(14,'test5','kocopass','test5@cat.dog',NULL,NULL,NULL,2,1,'avatar.png'),(15,NULL,NULL,'test5@cat.dog6',NULL,NULL,NULL,2,1,'avatar.png'),(17,NULL,'123','test10@cat.dog','Lionel Pep','123456789','HCM',0,0,'logo3.png'),(18,'cris','','test7@cat.dog',NULL,'0987654321,HCM',NULL,0,0,''),(19,'cr7','123','test8@cat.dog',NULL,'0987654321,Lisbon',NULL,0,0,'logo1.png'),(20,'cris9','123','test9@cat.dog','Cris Ronaldo','1111111111','HCM',0,0,NULL),(21,NULL,NULL,'test11@cat.dog',NULL,NULL,NULL,2,1,NULL),(22,'test12','123','test12@cat.dog','Lionel Pep','123456789','HCM',0,1,'e8174dc7-f129-4d70-becc-7c2286c7c5a8_logo1.png'),(23,'cris10',NULL,NULL,'Cris Ronaldo',NULL,NULL,0,0,NULL),(24,'test13','123','test13@cat.dog','Cris Ronaldo','1111111111',NULL,0,0,'logo3.png'),(25,'test14','123','test14@cat.dog','Cris Ronaldo','1111111111',NULL,0,0,'logo1.png'),(28,'test16','123','test16@cat.dog','Lionel Pep','123456789','HCM',1,1,'logo1.png'),(33,'test15','123','test15@cat.dog','Lionel Pep','123456789','HCM',0,1,'logo2.png'),(36,'coca','123','coca@pepsi.inn','Cris Ronaldo','66666666','Pensylvania',0,1,'e8174dc7-f129-4d70-becc-7c2286c7c5a8_logo1.png'),(42,'cocacola','123','coca@pepsi','Cris Ronaldo','1111111111','Arab Saudia',0,1,'avatar.png'),(43,NULL,NULL,'tyrus.la@outlook.com',NULL,NULL,NULL,2,1,NULL),(44,NULL,NULL,'tyrvszx@gmail.com',NULL,NULL,NULL,2,1,NULL),(45,NULL,NULL,'tyrvszx@gmail.com',NULL,NULL,NULL,2,1,NULL),(47,'Phong Cris','123','tanphong06072001@gmail.com','Phong Cris','0842980607','606 Nguyễn Văn Quá - Đông Hưng Thuận - Quận 12',0,1,'_380918ac-9d18-47b8-81f8-72d31c74941a.jpeg'),(48,NULL,NULL,'test16@cat.dog',NULL,NULL,NULL,2,1,NULL),(51,'test17','123','test17@cat.dog','Phong Cris','0842980607','Phong Nha',0,1,'Phong1.png'),(53,'test18','123','test18@cat.dog','Phong Tự Kỷ','0842980607','Cộng Hòa',0,1,'1715334374232_Phong1.png'),(54,'test19','123','test19@cat.dog','Phong Đẹp Trai','0842980607','Arab Saudia',0,1,'1715337780432_Phong1.png'),(55,'test20','123','test20@cat.dog','Phong Cris','0842980607','Cộng Hòa',0,1,'1715340714963_Phong1.png'),(56,'test21','123','test21@cat.dog','Phong TK','0842980607','Bố Trạch',0,1,'1715346210308_Phong1.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'computerstore'
--

--
-- Dumping routines for database 'computerstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-13 18:07:00
