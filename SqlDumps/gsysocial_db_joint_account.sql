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
-- Table structure for table `joint_account`
--

DROP TABLE IF EXISTS `joint_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `joint_account` (
  `joint_account_id` varchar(100) NOT NULL,
  `joint_account_name` varchar(100) NOT NULL,
  `first_user_id` varchar(100) NOT NULL,
  `first_user_name` varchar(100) NOT NULL,
  `second_user_id` varchar(100) NOT NULL,
  `second_user_name` varchar(100) NOT NULL,
  `joint_account_story` text,
  `follower_count` int(11) NOT NULL DEFAULT '0',
  `post_count` int(11) unsigned NOT NULL DEFAULT '0',
  `profile_pic` tinytext,
  PRIMARY KEY (`joint_account_id`),
  UNIQUE KEY `first_email_id` (`first_user_id`),
  UNIQUE KEY `second_email_id` (`second_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joint_account`
--

LOCK TABLES `joint_account` WRITE;
/*!40000 ALTER TABLE `joint_account` DISABLE KEYS */;
INSERT INTO `joint_account` VALUES ('5c360c82-afb9-4b4e-a4d2-06bff44dfa62','My New Joint Account','5487b054-2973-4740-89bd-592515cce587','sandeep','7308c24d-37e2-4efd-8b6b-0285a0745b1f','Pinky Kumari',NULL,1,1,NULL),('ad9748a2-3332-4dc3-98b6-b8e1ff6ae45a','jjj','7df06243-9a21-4587-a09b-5daa397734d3','Sandeep Agrawal','b8445e41-d389-4c39-8f5a-ca6e3d258ecc','Mayank',NULL,0,0,NULL),('e2ab95db-8447-4537-a102-66ef47ded497','skdhdhh','21034108-c511-4ee4-87b2-da3afbe9e6de','Sandeep Agrawal','9fdca370-5e34-436d-9b8e-a43799edbd63','Mayank',NULL,0,0,NULL),('f0709726-982d-4a10-b631-831233cbd609','My Joint Account','629d035f-64f6-4edb-b0de-463f4fd4bec3','Jasneet Singh','4d5ac211-e79b-4d21-8435-3445b55aa9ed','Harneet Singh',NULL,3,2,NULL);
/*!40000 ALTER TABLE `joint_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-25 18:43:03
