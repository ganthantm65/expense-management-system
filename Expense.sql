-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: expense_management
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Ravi','ravi@gmail.com','$2a$10$u2UuPTH/g9NVBC9DYF3Pj.24P/a7BI.8vcf3wuJKroVEa9.yUisdy'),(2,'Ganthan','tmganthan@gmail.com','$2a$10$xlx2kh..0.mwebdtJ.j8jeJgQMT73M1L.c3DaytH.VMIniOAZ0xIe');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `expenses` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'johndoe@gmail.com','$2a$10$M.QHQMau6MlsvkW1ZbCEluf8rIw39bus0TW80377582QNH7UJ.BKG','John Doe',NULL,'Manager','[{\"amount\": 450.0, \"category\": \"Travel\", \"description\": \"Bussiness Trip\", \"dateOfExpense\": \"2025-03-25\"}, {\"amount\": 650.0, \"category\": \"Food\", \"description\": \"Dinner meet\", \"dateOfExpense\": \"2025-03-28\"}, {\"amount\": 6500.0, \"category\": \"Management\", \"description\": \"Supplying products\", \"dateOfExpense\": \"2025-03-29\"}, {\"amount\": 3500.0, \"category\": \"Travel\", \"description\": \"Client Meeting\", \"dateOfExpense\": \"2025-03-30\"}]'),(2,'varun@gmail.com','$2a$10$e3az6p22BINhSsDGk3X70uXlSLQ9cO8YJh70B/13NLgyH3YxzcdUq','Varun',NULL,NULL,'[{\"amount\": 1500.0, \"category\": \"Travel\", \"description\": \"Client Meeting in Australia\", \"dateOfExpense\": \"2025-03-26\"}]'),(3,'ganesh@gmail.com','$2a$10$iLvAG1uNkTA8M.Rga3zw5.cJW.aHc/qytlLMBAuZEswgaMDytw73C','Ganesh','Production','Assistant Manager','[]'),(4,'vikram@gmail.com','$2a$10$fbmLSwCl6CddxJdX00HUQ.HHvvP01PY1zVddeJ6z1IHXw5CYTb67G','Vikram','Marketing','Sale Executive','[]');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-31 20:58:44
