CREATE DATABASE  IF NOT EXISTS `arm` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `arm`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: arm
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `carder`
--

DROP TABLE IF EXISTS `carder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carder` (
  `idcarder` int NOT NULL,
  `limt` varchar(45) DEFAULT NULL,
  `current` varchar(45) DEFAULT NULL,
  `Subjects_id` int NOT NULL,
  PRIMARY KEY (`idcarder`),
  KEY `fk_carder_Subjects1_idx` (`Subjects_id`),
  CONSTRAINT `fk_carder_Subjects1` FOREIGN KEY (`Subjects_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carder`
--

LOCK TABLES `carder` WRITE;
/*!40000 ALTER TABLE `carder` DISABLE KEYS */;
/*!40000 ALTER TABLE `carder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `idcity` int NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  `district_iddistrict` int NOT NULL,
  `postalcode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcity`),
  KEY `fk_city_district1_idx` (`district_iddistrict`),
  CONSTRAINT `fk_city_district1` FOREIGN KEY (`district_iddistrict`) REFERENCES `district` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `province_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_district_province1_idx` (`province_id`),
  CONSTRAINT `fk_district_province1` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra`
--

DROP TABLE IF EXISTS `extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra` (
  `idextra` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idextra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra`
--

LOCK TABLES `extra` WRITE;
/*!40000 ALTER TABLE `extra` DISABLE KEYS */;
/*!40000 ALTER TABLE `extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_has_teacher`
--

DROP TABLE IF EXISTS `extra_has_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_has_teacher` (
  `extra_idextra` int NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`extra_idextra`,`teacher_id`),
  KEY `fk_extra_has_teacher_teacher1_idx` (`teacher_id`),
  KEY `fk_extra_has_teacher_extra1_idx` (`extra_idextra`),
  CONSTRAINT `fk_extra_has_teacher_extra1` FOREIGN KEY (`extra_idextra`) REFERENCES `extra` (`idextra`),
  CONSTRAINT `fk_extra_has_teacher_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_has_teacher`
--

LOCK TABLES `extra_has_teacher` WRITE;
/*!40000 ALTER TABLE `extra_has_teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `extra_has_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formerexperiance`
--

DROP TABLE IF EXISTS `formerexperiance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formerexperiance` (
  `idformerexperiance` int NOT NULL AUTO_INCREMENT,
  `appointntdate` date DEFAULT NULL,
  `appointmentenddate` date DEFAULT NULL,
  `school_idschool` int NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`idformerexperiance`),
  KEY `fk_formerexperiance_school1_idx` (`school_idschool`),
  KEY `fk_formerexperiance_teacher1_idx` (`teacher_id`),
  CONSTRAINT `fk_formerexperiance_school1` FOREIGN KEY (`school_idschool`) REFERENCES `school` (`idschool`),
  CONSTRAINT `fk_formerexperiance_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formerexperiance`
--

LOCK TABLES `formerexperiance` WRITE;
/*!40000 ALTER TABLE `formerexperiance` DISABLE KEYS */;
/*!40000 ALTER TABLE `formerexperiance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `idgender` int NOT NULL AUTO_INCREMENT,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idgender`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_data`
--

DROP TABLE IF EXISTS `image_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_data` (
  `idimage_data` int NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idimage_data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_data`
--

LOCK TABLES `image_data` WRITE;
/*!40000 ALTER TABLE `image_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marital_status`
--

DROP TABLE IF EXISTS `marital_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marital_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marital_status`
--

LOCK TABLES `marital_status` WRITE;
/*!40000 ALTER TABLE `marital_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `marital_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qulification`
--

DROP TABLE IF EXISTS `qulification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qulification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `discription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qulification`
--

LOCK TABLES `qulification` WRITE;
/*!40000 ALTER TABLE `qulification` DISABLE KEYS */;
/*!40000 ALTER TABLE `qulification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `idrequest` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `requestprovince` varchar(45) DEFAULT NULL,
  `requestschool` varchar(45) DEFAULT NULL,
  `requestdistrict` varchar(45) DEFAULT NULL,
  `requeststatus` varchar(45) DEFAULT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`idrequest`),
  KEY `fk_request_teacher1_idx` (`teacher_id`),
  CONSTRAINT `fk_request_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_permission_role1_idx` (`role_id`),
  KEY `fk_role_permission_permission1_idx` (`permission_id`),
  CONSTRAINT `fk_role_permission_permission1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `fk_role_permission_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salutation`
--

DROP TABLE IF EXISTS `salutation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salutation` (
  `idsalutation` int NOT NULL AUTO_INCREMENT,
  `salutation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsalutation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salutation`
--

LOCK TABLES `salutation` WRITE;
/*!40000 ALTER TABLE `salutation` DISABLE KEYS */;
/*!40000 ALTER TABLE `salutation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `idschool` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `carder_idcarder` int NOT NULL,
  `city_idcity` int NOT NULL,
  `zonal_idzonal` int NOT NULL,
  `school_type_id` int NOT NULL,
  PRIMARY KEY (`idschool`),
  KEY `fk_school_carder1_idx` (`carder_idcarder`),
  KEY `fk_school_city1_idx` (`city_idcity`),
  KEY `fk_school_zonal1_idx` (`zonal_idzonal`),
  KEY `fk_school_school_type1_idx` (`school_type_id`),
  CONSTRAINT `fk_school_carder1` FOREIGN KEY (`carder_idcarder`) REFERENCES `carder` (`idcarder`),
  CONSTRAINT `fk_school_city1` FOREIGN KEY (`city_idcity`) REFERENCES `city` (`idcity`),
  CONSTRAINT `fk_school_school_type1` FOREIGN KEY (`school_type_id`) REFERENCES `school_type` (`id`),
  CONSTRAINT `fk_school_zonal1` FOREIGN KEY (`zonal_idzonal`) REFERENCES `zonal` (`idzonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_type`
--

DROP TABLE IF EXISTS `school_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_type`
--

LOCK TABLES `school_type` WRITE;
/*!40000 ALTER TABLE `school_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `school_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `idstatus` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `discription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `user_nic` varchar(45) NOT NULL,
  `school_idschool` int NOT NULL,
  `appointmentdate` date DEFAULT NULL,
  `retiredate` varchar(45) DEFAULT NULL COMMENT 'check if the age is 55 -for female\n65 for male',
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL COMMENT 'principal\nteacher combine --> all sri lanka\nteacher provincial -> provice',
  `teacher_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teacher_user1_idx` (`user_nic`),
  KEY `fk_teacher_school1_idx` (`school_idschool`),
  KEY `fk_teacher_teacher_type1_idx` (`teacher_type_id`),
  CONSTRAINT `fk_teacher_school1` FOREIGN KEY (`school_idschool`) REFERENCES `school` (`idschool`),
  CONSTRAINT `fk_teacher_teacher_type1` FOREIGN KEY (`teacher_type_id`) REFERENCES `teacher_type` (`id`),
  CONSTRAINT `fk_teacher_user1` FOREIGN KEY (`user_nic`) REFERENCES `user` (`nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_type`
--

DROP TABLE IF EXISTS `teacher_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_type`
--

LOCK TABLES `teacher_type` WRITE;
/*!40000 ALTER TABLE `teacher_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers_qulification`
--

DROP TABLE IF EXISTS `teachers_qulification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers_qulification` (
  `qulification_idqulification` int NOT NULL,
  `teacher_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_qulification_has_teacher_teacher1_idx` (`teacher_id`),
  KEY `fk_qulification_has_teacher_qulification1_idx` (`qulification_idqulification`),
  CONSTRAINT `fk_qulification_has_teacher_qulification1` FOREIGN KEY (`qulification_idqulification`) REFERENCES `qulification` (`id`),
  CONSTRAINT `fk_qulification_has_teacher_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers_qulification`
--

LOCK TABLES `teachers_qulification` WRITE;
/*!40000 ALTER TABLE `teachers_qulification` DISABLE KEYS */;
/*!40000 ALTER TABLE `teachers_qulification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers_subject`
--

DROP TABLE IF EXISTS `teachers_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers_subject` (
  `Subjects_idSubjects` int NOT NULL,
  `teacher_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_Subjects_has_teacher_teacher1_idx` (`teacher_id`),
  KEY `fk_Subjects_has_teacher_Subjects1_idx` (`Subjects_idSubjects`),
  CONSTRAINT `fk_Subjects_has_teacher_Subjects1` FOREIGN KEY (`Subjects_idSubjects`) REFERENCES `subjects` (`id`),
  CONSTRAINT `fk_Subjects_has_teacher_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers_subject`
--

LOCK TABLES `teachers_subject` WRITE;
/*!40000 ALTER TABLE `teachers_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `teachers_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `nic` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address_no` varchar(25) DEFAULT NULL,
  `address_street` varchar(75) DEFAULT NULL,
  `address_street2` varchar(75) DEFAULT NULL,
  `contact_number1` int DEFAULT NULL,
  `contact_number2` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `user_type_iduser_type` int NOT NULL,
  `status_idstatus` int NOT NULL,
  `image_data_idimage_data` int DEFAULT NULL,
  `salutation_idsalutation` int NOT NULL,
  `gender_idgender` int NOT NULL,
  `city_idcity` int NOT NULL,
  `email` varchar(75) DEFAULT NULL,
  `marital_status_id` int NOT NULL,
  PRIMARY KEY (`nic`),
  KEY `fk_user_user_type_idx` (`user_type_iduser_type`),
  KEY `fk_user_status1_idx` (`status_idstatus`),
  KEY `fk_user_image_data1_idx` (`image_data_idimage_data`),
  KEY `fk_user_salutation1_idx` (`salutation_idsalutation`),
  KEY `fk_user_gender1_idx` (`gender_idgender`),
  KEY `fk_user_city1_idx` (`city_idcity`),
  KEY `fk_user_marital_status1_idx` (`marital_status_id`),
  CONSTRAINT `fk_user_city1` FOREIGN KEY (`city_idcity`) REFERENCES `city` (`idcity`),
  CONSTRAINT `fk_user_gender1` FOREIGN KEY (`gender_idgender`) REFERENCES `gender` (`idgender`),
  CONSTRAINT `fk_user_image_data1` FOREIGN KEY (`image_data_idimage_data`) REFERENCES `image_data` (`idimage_data`),
  CONSTRAINT `fk_user_marital_status1` FOREIGN KEY (`marital_status_id`) REFERENCES `marital_status` (`id`),
  CONSTRAINT `fk_user_salutation1` FOREIGN KEY (`salutation_idsalutation`) REFERENCES `salutation` (`idsalutation`),
  CONSTRAINT `fk_user_status1` FOREIGN KEY (`status_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `fk_user_user_type` FOREIGN KEY (`user_type_iduser_type`) REFERENCES `user_type` (`iduser_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `user_nic` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_role1_idx` (`role_id`),
  KEY `fk_user_role_user1_idx` (`user_nic`),
  CONSTRAINT `fk_user_role_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user1` FOREIGN KEY (`user_nic`) REFERENCES `user` (`nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_data` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_nic` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_token_user1_idx` (`user_nic`),
  CONSTRAINT `fk_user_token_user1` FOREIGN KEY (`user_nic`) REFERENCES `user` (`nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `iduser_type` int NOT NULL AUTO_INCREMENT,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`iduser_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonal`
--

DROP TABLE IF EXISTS `zonal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonal` (
  `idzonal` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idzonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonal`
--

LOCK TABLES `zonal` WRITE;
/*!40000 ALTER TABLE `zonal` DISABLE KEYS */;
/*!40000 ALTER TABLE `zonal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-19 22:26:48
