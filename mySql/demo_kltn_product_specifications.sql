-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: demo_kltn
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `product_specifications`
--

DROP TABLE IF EXISTS `product_specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_specifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specification_name` varchar(255) DEFAULT NULL,
  `specification_value` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `spec_category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbets5sov4bn9d2wy8vqathw6d` (`product_id`),
  KEY `FKkrk4in23xneoq1oai7lv9ytf3` (`spec_category_id`),
  CONSTRAINT `FKbets5sov4bn9d2wy8vqathw6d` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKkrk4in23xneoq1oai7lv9ytf3` FOREIGN KEY (`spec_category_id`) REFERENCES `specification_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_specifications`
--

LOCK TABLES `product_specifications` WRITE;
/*!40000 ALTER TABLE `product_specifications` DISABLE KEYS */;
INSERT INTO `product_specifications` VALUES (1,'Kích thước màn hình ','6.7 inches 2796 x 1290 pixel','SP3805',1);
/*!40000 ALTER TABLE `product_specifications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-09 13:55:12
