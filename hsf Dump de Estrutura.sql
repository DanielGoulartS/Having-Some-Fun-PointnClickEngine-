CREATE DATABASE  IF NOT EXISTS `hsf` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hsf`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: hsf
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `character`
--

DROP TABLE IF EXISTS `character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `character` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `ending` varchar(45) DEFAULT NULL,
  `isBoss` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `character_images`
--

DROP TABLE IF EXISTS `character_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `character_images` (
  `id` int NOT NULL,
  `images_id` int NOT NULL,
  `character_id` int NOT NULL,
  PRIMARY KEY (`images_id`,`character_id`,`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_images_has_character_character1_idx` (`character_id`),
  KEY `fk_images_has_character_images1_idx` (`images_id`),
  CONSTRAINT `fk_images_has_character_character1` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `fk_images_has_character_images1` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `id` int NOT NULL,
  `character_id` int NOT NULL,
  `speech` varchar(300) NOT NULL,
  `reaction_id` int NOT NULL,
  PRIMARY KEY (`id`,`character_id`),
  KEY `fk_conversation_character1_idx` (`character_id`),
  KEY `fk_conversation_character_images1_idx` (`reaction_id`),
  CONSTRAINT `fk_conversation_character1` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `fk_conversation_character_images1` FOREIGN KEY (`reaction_id`) REFERENCES `character_images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_UNIQUE` (`image`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction`
--

DROP TABLE IF EXISTS `interaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `w` int DEFAULT NULL,
  `h` int DEFAULT NULL,
  `text` varchar(60) DEFAULT NULL,
  `type` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_action`
--

DROP TABLE IF EXISTS `interaction_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_action` (
  `interaction_id` int NOT NULL,
  `scene_id` int NOT NULL,
  `next` int NOT NULL,
  PRIMARY KEY (`interaction_id`,`scene_id`),
  KEY `fk_interaction_action_scene1_idx` (`next`),
  KEY `fk_interaction_action_interaction2_idx` (`interaction_id`),
  KEY `fk_interaction_action_scene2_idx` (`scene_id`),
  CONSTRAINT `fk_interaction_action_interaction2` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`),
  CONSTRAINT `fk_interaction_action_scene1` FOREIGN KEY (`next`) REFERENCES `scene` (`id`),
  CONSTRAINT `fk_interaction_action_scene2` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_character`
--

DROP TABLE IF EXISTS `interaction_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_character` (
  `interaction_id` int NOT NULL,
  `scene_id` int NOT NULL,
  `character_id` int NOT NULL,
  PRIMARY KEY (`interaction_id`,`scene_id`,`character_id`),
  KEY `fk_interaction_character_scene1_idx` (`scene_id`),
  KEY `fk_interaction_character_character1_idx` (`character_id`),
  CONSTRAINT `fk_interaction_character_character1` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `fk_interaction_character_interaction1` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`),
  CONSTRAINT `fk_interaction_character_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_dialog`
--

DROP TABLE IF EXISTS `interaction_dialog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_dialog` (
  `interaction_id` int NOT NULL,
  `conversation_id` int NOT NULL,
  `character_id` int NOT NULL,
  `next_conversation` int NOT NULL,
  `next_character` int NOT NULL,
  `next_scene` int NOT NULL,
  PRIMARY KEY (`interaction_id`,`character_id`,`conversation_id`),
  KEY `fk_interaction_dialog_conversation1_idx` (`conversation_id`,`character_id`),
  KEY `fk_interaction_dialog_interaction1_idx` (`interaction_id`),
  KEY `fk_interaction_dialog_scene1_idx` (`next_scene`),
  CONSTRAINT `fk_interaction_dialog_conversation1` FOREIGN KEY (`conversation_id`, `character_id`) REFERENCES `conversation` (`id`, `character_id`),
  CONSTRAINT `fk_interaction_dialog_interaction1` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`),
  CONSTRAINT `fk_interaction_dialog_scene1` FOREIGN KEY (`next_scene`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_galery`
--

DROP TABLE IF EXISTS `interaction_galery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_galery` (
  `interaction_id` int NOT NULL,
  `scene_id` int NOT NULL,
  PRIMARY KEY (`interaction_id`,`scene_id`),
  KEY `fk_Interaction_Galery_scene1_idx` (`scene_id`),
  CONSTRAINT `fk_Interaction_Galery_interaction1` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`),
  CONSTRAINT `fk_Interaction_Galery_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_images`
--

DROP TABLE IF EXISTS `interaction_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_images` (
  `id` int NOT NULL,
  `images_id` int NOT NULL,
  `interaction_id` int NOT NULL,
  PRIMARY KEY (`id`,`images_id`,`interaction_id`),
  KEY `fk_images_has_interactions_interactions1_idx` (`interaction_id`),
  KEY `fk_images_has_interactions_images1_idx` (`images_id`),
  CONSTRAINT `fk_images_has_interactions_images1` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`),
  CONSTRAINT `fk_images_has_interactions_interactions1` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interaction_password`
--

DROP TABLE IF EXISTS `interaction_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interaction_password` (
  `interaction_id` int NOT NULL,
  `scene_id` int NOT NULL,
  `next` int NOT NULL,
  `code` char(1) NOT NULL,
  `kills` tinyint NOT NULL,
  PRIMARY KEY (`interaction_id`,`scene_id`),
  KEY `fk_interaction_password_scene1_idx` (`next`),
  KEY `fk_interaction_password_scene2_idx` (`scene_id`),
  CONSTRAINT `fk_interaction_password_interaction1` FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`id`),
  CONSTRAINT `fk_interaction_password_scene1` FOREIGN KEY (`next`) REFERENCES `scene` (`id`),
  CONSTRAINT `fk_interaction_password_scene2` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scene` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `narrationText` varchar(300) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scene_images`
--

DROP TABLE IF EXISTS `scene_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scene_images` (
  `id` int NOT NULL,
  `scene_id` int NOT NULL,
  `images_id` int NOT NULL,
  PRIMARY KEY (`id`,`scene_id`,`images_id`),
  KEY `fk_images_has_scene_scene1_idx` (`scene_id`),
  KEY `fk_scene_images_images1_idx` (`images_id`),
  CONSTRAINT `fk_images_has_scene_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`),
  CONSTRAINT `fk_scene_images_images1` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'hsf'
--

--
-- Dumping routines for database 'hsf'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-25 12:33:18
