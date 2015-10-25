-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: gfysocial.c5l4tznddpos.us-west-2.rds.amazonaws.com    Database: gsysocial_db
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `follower`
--

DROP TABLE IF EXISTS `follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follower` (
  `follow_id` varchar(100) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `joint_account_id` varchar(100) NOT NULL,
  PRIMARY KEY (`follow_id`),
  UNIQUE KEY `unique_constraint` (`user_id`,`joint_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follower`
--

LOCK TABLES `follower` WRITE;
/*!40000 ALTER TABLE `follower` DISABLE KEYS */;
INSERT INTO `follower` VALUES ('000146f3-54a5-4c46-a1b6-bc1bf75f4a0a','21034108-c511-4ee4-87b2-da3afbe9e6de','f0709726-982d-4a10-b631-831233cbd609'),('b2facad8-32cc-4b9a-969e-ab33b26eb61f','5487b054-2973-4740-89bd-592515cce587','5c360c82-afb9-4b4e-a4d2-06bff44dfa62'),('1339575a-a2cc-482f-926d-0cee73b5f2fa','629d035f-64f6-4edb-b0de-463f4fd4bec3','f0709726-982d-4a10-b631-831233cbd609'),('efbea26e-c8b5-4eb4-86d8-86b685ec9ad0','7df06243-9a21-4587-a09b-5daa397734d3','f0709726-982d-4a10-b631-831233cbd609');
/*!40000 ALTER TABLE `follower` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-25 18:42:52
