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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_name` varchar(100) NOT NULL,
  `email_id` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `date_of_birth` varchar(50) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `session_id` varchar(100) DEFAULT NULL,
  `profile_pic` text,
  `joint_account_id` varchar(100) DEFAULT NULL,
  `gcm_device_id` varchar(100) DEFAULT NULL,
  `follow_count` int(11) NOT NULL DEFAULT '0',
  `oauth_uid` varchar(200) DEFAULT NULL,
  `oauth_provider` varchar(200) DEFAULT NULL,
  `user_id` varchar(100) NOT NULL DEFAULT '',
  `invite_request_pending` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `session_id` (`session_id`),
  UNIQUE KEY `oauth_uid` (`oauth_uid`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('neha 143','genius_neha14@yahoo.com','neham143','18/10/2015','7836820952','11a63a3d-4818-40a5-ac1f-efa5ee4aaa59','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/e471772c-3b8b-441b-9855-ab6c7b04df0b-received_1049853141714407.jpeg',NULL,NULL,0,NULL,NULL,'11a63a3d-4818-40a5-ac1f-efa5ee4aaa59',0),('Sandeep Agrawal',NULL,NULL,NULL,NULL,'21034108-c511-4ee4-87b2-da3afbe9e6de','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/2594de0d-9975-427c-af11-a6c388ca475e-IMG-20151012-WA0005.jpg','e2ab95db-8447-4537-a102-66ef47ded497',NULL,1,'10206688326221077','facebook','21034108-c511-4ee4-87b2-da3afbe9e6de',0),('dsdsdsds','harneetsingh17@gmail.com','facebook',NULL,NULL,'eb3624df-9d00-465f-b611-d4735a88b531','sandeep.jpeg','f0709726-982d-4a10-b631-831233cbd609',NULL,0,NULL,NULL,'4d5ac211-e79b-4d21-8435-3445b55aa9ed',0),('sandeep','sandeepkalangi@gmail.com','sandeepkalangi','05/16/1994','9959437667','5487b054-2973-4740-89bd-592515cce587','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/e45e0e89-f0bd-4c87-8954-fc40d6ca716a-temp.png','5c360c82-afb9-4b4e-a4d2-06bff44dfa62',NULL,1,NULL,NULL,'5487b054-2973-4740-89bd-592515cce587',1),('Jasneet Singh',NULL,NULL,NULL,NULL,'629d035f-64f6-4edb-b0de-463f4fd4bec3',NULL,'f0709726-982d-4a10-b631-831233cbd609',NULL,1,'sdsdsdds','facebook','629d035f-64f6-4edb-b0de-463f4fd4bec3',0),('Pinky Kumari',NULL,NULL,NULL,NULL,'7308c24d-37e2-4efd-8b6b-0285a0745b1f',NULL,'5c360c82-afb9-4b4e-a4d2-06bff44dfa62',NULL,0,'1501603440165602','facebook','7308c24d-37e2-4efd-8b6b-0285a0745b1f',0),('Sandeep Agrawal','sandeepmnit35@gmail.com','Accellion#1','03/04/1986','7042333148','fea4fe4e-ef75-46c0-a226-bdaa35ed307d','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/bcf43e18-8684-4387-a6cc-2858978f2973-IMG-20151012-WA0008.jpg','ad9748a2-3332-4dc3-98b6-b8e1ff6ae45a',NULL,1,NULL,NULL,'7df06243-9a21-4587-a09b-5daa397734d3',0),('Mayank','sandeepmnit351@gmail.com','Accellion#2','03/04/1986','7042333148','9fdca370-5e34-436d-9b8e-a43799edbd63',NULL,'e2ab95db-8447-4537-a102-66ef47ded497',NULL,0,NULL,NULL,'9fdca370-5e34-436d-9b8e-a43799edbd63',0),('Mayank','mayank@gmail.com','Accellion#1','18/10/1993','7042333148','b8445e41-d389-4c39-8f5a-ca6e3d258ecc','http://ec2-54-148-202-59.us-west-2.compute.amazonaws.com/GFYSocial/file/download/aa2b0b95-373d-4e7d-a3e7-0a631f5b46e2-IMG-20151012-WA0010.jpg','ad9748a2-3332-4dc3-98b6-b8e1ff6ae45a',NULL,0,NULL,NULL,'b8445e41-d389-4c39-8f5a-ca6e3d258ecc',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-25 18:43:50
