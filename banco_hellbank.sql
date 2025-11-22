-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: hellbank_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(20) NOT NULL,
  `method` varchar(30) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (8,2,'RECEITA','PIX',50.00,'Pagamento','2025-11-19 17:25:16'),(9,2,'DESPESA','PIX',25.00,'PIX enviado para Adriana Barbosa','2025-11-19 17:30:05'),(11,2,'DESPESA','PIX',5.00,'PIX enviado para Adriana Barbosa','2025-11-19 17:30:41'),(14,2,'RECEITA','PIX',15.00,'PIX recebido de Adriana Barbosa','2025-11-19 17:36:06'),(15,2,'DESPESA','PIX',5.00,'PIX enviado para Adriana Barbosa','2025-11-19 18:50:14'),(17,2,'DESPESA','PIX',5.00,'PIX enviado para Adriana Barbosa','2025-11-19 18:50:44'),(21,2,'RECEITA','PIX',500.00,'PIX recebido de Vinicius','2025-11-19 20:49:24'),(22,2,'DESPESA','DEBITO',25.00,'Água','2025-11-19 20:52:42'),(23,2,'DESPESA','CREDITO',250.00,'Água','2025-11-19 20:52:56'),(28,2,'RECEITA','PIX',25.00,'PIX recebido de Gustavo Matos','2025-11-19 21:24:36'),(29,2,'RECEITA','PIX',10.00,'Água','2025-11-19 23:18:31'),(30,2,'DESPESA','PIX',15.00,'Passagem','2025-11-19 23:23:12'),(31,2,'RECEITA','PIX',666.00,'Teste','2025-11-20 00:26:21'),(32,2,'DESPESA','PIX',666.00,'PIX enviado para Adriana Barbosa','2025-11-20 00:26:30'),(34,2,'RECEITA','PIX',600.00,'Pagamento','2025-11-20 18:48:45'),(35,2,'DESPESA','PIX',666.00,'PIX enviado para Vinicius','2025-11-20 18:49:02'),(37,2,'RECEITA','PIX',600.00,'Pagamento','2025-11-20 19:09:31'),(38,2,'DESPESA','PIX',666.00,'PIX enviado para Vinicius','2025-11-20 19:10:00');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `dob` varchar(15) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `data_cadastro` datetime DEFAULT CURRENT_TIMESTAMP,
  `tipo_permissao` varchar(20) DEFAULT 'USUARIO',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Daniel Barbosa','389.593.408-98','dani.ramalho0405@gmail.com','(11) 99521-1263','04/05/2006','Dam&041210',138.00,'2025-11-19 19:35:17','ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-20 19:42:36
