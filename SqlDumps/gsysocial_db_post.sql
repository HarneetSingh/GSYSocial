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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `post_id` varchar(100) NOT NULL,
  `joint_account_id` varchar(100) NOT NULL,
  `file_url` varchar(200) DEFAULT NULL,
  `file_type` varchar(100) DEFAULT NULL,
  `post_text` text NOT NULL,
  `total_rating` bigint(20) NOT NULL DEFAULT '0',
  `no_of_ratings` int(11) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL,
  `last_updated_time` datetime NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `profile_pic` text,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('04d53a7f-35fb-4d02-811b-e04784af7797','f0709726-982d-4a10-b631-831233cbd609',NULL,NULL,'Our Second Post',0,0,'2015-10-11 13:09:08','2015-10-11 13:09:08','dsdsdsds','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/f3962fed-a93a-48a9-938f-7a63a8cc1a8f-20150918_194458.jpg'),('8e113727-7caa-4bfd-ab05-f6e04d6a0f97','5c360c82-afb9-4b4e-a4d2-06bff44dfa62','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/f9e25a52-44e6-4c5f-96c5-e64c54713042-20151018_210148.jpg','image','kirrikeettt',0,0,'2015-10-18 15:32:54','2015-10-18 15:32:54','sandeep','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/e45e0e89-f0bd-4c87-8954-fc40d6ca716a-temp.png'),('dd8cc4e8-49cc-4ede-a476-46ff68ed206e','f0709726-982d-4a10-b631-831233cbd609','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/f3962fed-a93a-48a9-938f-7a63a8cc1a8f-20150918_194458.jpg','image/jpg','My Second Post',0,0,'2015-10-11 07:45:31','2015-10-11 07:45:31','sandeep','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/f3962fed-a93a-48a9-938f-7a63a8cc1a8f-20150918_194458.jpg');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-25 18:43:10
