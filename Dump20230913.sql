-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: automatic_irrigation
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `plots_of_land`
--

DROP TABLE IF EXISTS plots_of_land;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE plots_of_land (
  id bigint NOT NULL AUTO_INCREMENT,
  plot_name varchar(255) NOT NULL,
  water_required int NOT NULL,
  area double NOT NULL,
  type_of_agriculture_crop varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_kkgtrysoxva4av3t4xqp1ckdw (plot_name)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plots_of_land`
--

LOCK TABLES plots_of_land WRITE;
/*!40000 ALTER TABLE plots_of_land DISABLE KEYS */;
INSERT INTO plots_of_land VALUES (1,'plot1',200,130,'oil'),(2,'Plot2',350,200,'fiber'),(3,'Plot3',500,400,'oil');
/*!40000 ALTER TABLE plots_of_land ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slots_of_time`
--

DROP TABLE IF EXISTS slots_of_time;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE slots_of_time (
  id bigint NOT NULL AUTO_INCREMENT,
  end_time int DEFAULT NULL,
  irrigation_duration int DEFAULT NULL,
  start_time int DEFAULT NULL,
  slot_status varchar(255) DEFAULT NULL,
  plots_of_land_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKtm88dx46fjl1b161uei927on6 (plots_of_land_id),
  CONSTRAINT FKtm88dx46fjl1b161uei927on6 FOREIGN KEY (plots_of_land_id) REFERENCES plots_of_land (id)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slots_of_time`
--

LOCK TABLES slots_of_time WRITE;
/*!40000 ALTER TABLE slots_of_time DISABLE KEYS */;
INSERT INTO slots_of_time VALUES (1,4,2,2,'Down',1),(2,4,3,1,'Down',2),(3,4,3,1,'Down',2),(4,4,3,1,'UP',1),(5,4,1,3,'Down',1),(6,9,4,5,'Down',2),(7,7,1,6,'Down',1),(8,8,2,6,'Down',3),(9,8,2,6,'Down',3),(10,6,4,2,'Down',2),(12,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE slots_of_time ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-13  1:44:06
