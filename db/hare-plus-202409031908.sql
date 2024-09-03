-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hare-plus
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `fast_id` varchar(255) DEFAULT NULL,
  `level` tinyint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `pid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2a1m5wqtsh16bmr3uu2b2id3s` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,'','',NULL,NULL,NULL,NULL,NULL,NULL,'2024-08-22 15:19:59.214000',NULL,NULL,'1',0,'Hare集团',NULL,1,'10000',0),(2,'',NULL,NULL,'2024-08-22 15:20:30.200000',NULL,NULL,NULL,NULL,'2024-08-22 17:09:32.138000',NULL,NULL,'1-2',1,'青岛分公司',NULL,1,'11000',1),(3,NULL,NULL,NULL,'2024-08-22 15:26:44.533000',NULL,NULL,NULL,NULL,'2024-08-22 17:10:03.730000',NULL,NULL,'1-3',1,'北京分公司',NULL,2,'12000',1),(4,NULL,NULL,NULL,'2024-08-22 16:51:21.953000',NULL,NULL,NULL,NULL,'2024-08-22 17:09:39.978000',NULL,NULL,'1-2-4',2,'研发部',NULL,1,'11100',2),(6,NULL,NULL,NULL,'2024-08-22 18:25:51.812000',NULL,NULL,NULL,NULL,'2024-08-22 18:26:16.068000',NULL,NULL,'1-2-4-6',3,'Java组','3',1,'11110',4);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seq` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mm01p14ohevumirdwn80h64mw` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_item`
--

DROP TABLE IF EXISTS `sys_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `dict_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdem7dtr28pt4kqu9rk7strqdj` (`dict_id`),
  CONSTRAINT `FKdem7dtr28pt4kqu9rk7strqdj` FOREIGN KEY (`dict_id`) REFERENCES `sys_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_item`
--

LOCK TABLES `sys_dict_item` WRITE;
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_employee`
--

DROP TABLE IF EXISTS `sys_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `age` tinyint DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `entry_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `dept_id` bigint DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `job_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXhoehbcup37vhnlponfka2xd21` (`name`),
  KEY `IDX1c7v4t7k1ix3rr07k7vvqcoge` (`user_id`),
  KEY `FKaaiiwnuts37504elm8awbd7d3` (`dept_id`),
  KEY `FKn3y3hs4iudyc1f1o0bi7qm7j2` (`job_id`),
  CONSTRAINT `FKaaiiwnuts37504elm8awbd7d3` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`),
  CONSTRAINT `FKn3y3hs4iudyc1f1o0bi7qm7j2` FOREIGN KEY (`job_id`) REFERENCES `sys_job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_employee`
--

LOCK TABLES `sys_employee` WRITE;
/*!40000 ALTER TABLE `sys_employee` DISABLE KEYS */;
INSERT INTO `sys_employee` VALUES (1,NULL,NULL,NULL,'2024-08-29 22:06:00.342000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'J007','2024-08-28 00:00:00.000000','谷三超','123456','男',NULL,6,NULL,'2024-08-28','备注',1,NULL);
/*!40000 ALTER TABLE `sys_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seq` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,NULL,NULL,NULL,'2024-08-29 12:34:19.531000',NULL,NULL,NULL,NULL,'2024-09-03 11:51:32.050000',NULL,NULL,'J007',8,'坐在办公室编码','Java高级开发工程师',1,'active');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `pid` bigint DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'Setting','系统管理','_self',NULL,0,1,10,NULL),(2,NULL,'菜单管理','_self',NULL,1,1,10,'sys/Menu'),(3,NULL,'编辑',NULL,'sys:menu:save',2,1,20,'1'),(6,NULL,'test','_self',NULL,0,2,10,NULL),(7,NULL,'角色管理','_self',NULL,1,2,10,NULL),(8,NULL,'删除',NULL,NULL,2,2,20,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `data_scope` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `seq` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,NULL,NULL,NULL,'2024-08-21 11:32:00.947000',NULL,NULL,NULL,NULL,'2024-08-24 15:27:26.936000',NULL,NULL,'custom','管理员','管理员',1),(2,NULL,NULL,NULL,'2024-08-24 15:27:52.938000',NULL,NULL,NULL,NULL,'2024-08-24 15:28:09.429000',NULL,NULL,'all','总经理',NULL,2),(3,NULL,NULL,NULL,'2024-08-24 15:34:20.222000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3',NULL,3),(4,NULL,NULL,NULL,'2024-08-24 15:34:23.258000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4',NULL,4),(5,NULL,NULL,NULL,'2024-08-24 15:34:27.067000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5',NULL,5),(6,NULL,NULL,NULL,'2024-08-24 15:34:31.731000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'6',NULL,6),(7,NULL,NULL,NULL,'2024-08-24 15:34:38.235000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'7',NULL,7),(8,NULL,NULL,NULL,'2024-08-24 15:34:42.827000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'8',NULL,8),(9,NULL,NULL,NULL,'2024-08-24 15:34:49.883000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9',NULL,9),(10,NULL,NULL,NULL,'2024-08-24 15:34:54.052000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'10',NULL,10),(11,NULL,NULL,NULL,'2024-08-24 15:34:58.315000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'11',NULL,11);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL,
  `dept_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`dept_id`),
  KEY `FKp8ajq80m63s361m1pq3isls5t` (`dept_id`),
  CONSTRAINT `FKmdoybh4v5t2ooi48m3307n7fx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKp8ajq80m63s361m1pq3isls5t` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (1,1),(1,3);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,6);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_dept` varchar(255) DEFAULT NULL,
  `create_dept_fast_id` varchar(255) DEFAULT NULL,
  `create_dept_id` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `modified_dept` varchar(255) DEFAULT NULL,
  `modified_dept_id` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_user_id` bigint DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(150) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'管理员','$2a$10$qxPghqfHsc.SpQZ6iLKB3OkwMqXnbu0A6hfttHp1g.5VS.i/gn5QC',NULL,'正常',NULL,'admin'),(2,NULL,NULL,NULL,'2024-08-29 22:06:00.527000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谷三超','$2a$10$j47gW.nCxCy49sJuPNb1XOP9EfK8vj15nN9E8d/KsRnu0LUx5Rpre',NULL,'正常','employee','123456');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (2,3),(2,4),(2,5);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hare-plus'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-03 19:08:13
