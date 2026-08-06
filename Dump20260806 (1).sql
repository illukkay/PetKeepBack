CREATE DATABASE  IF NOT EXISTS `petkeep` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `petkeep`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: petkeep
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `avaliacoes`
--

DROP TABLE IF EXISTS `avaliacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacoes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tarefa_id` bigint(20) NOT NULL,
  `avaliador_id` bigint(20) NOT NULL,
  `avaliado_id` bigint(20) NOT NULL,
  `nota` int(11) NOT NULL CHECK (`nota` between 1 and 5),
  `comentario` text DEFAULT NULL,
  `data_avaliacao` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_avaliacao_tarefa` (`tarefa_id`),
  KEY `fk_avaliador` (`avaliador_id`),
  KEY `fk_avaliado` (`avaliado_id`),
  CONSTRAINT `fk_avaliacao_tarefa` FOREIGN KEY (`tarefa_id`) REFERENCES `tarefas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_avaliado` FOREIGN KEY (`avaliado_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_avaliador` FOREIGN KEY (`avaliador_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacoes`
--

LOCK TABLES `avaliacoes` WRITE;
/*!40000 ALTER TABLE `avaliacoes` DISABLE KEYS */;
INSERT INTO `avaliacoes` VALUES (1,1,1,2,5,'Excelente atendimento.','2026-08-06 17:30:40'),(2,2,2,3,4,'Muito bom serviço.','2026-08-06 17:30:40'),(3,3,3,5,5,'Prestador muito cuidadoso.','2026-08-06 17:30:40'),(4,4,4,6,4,'Chegou no horário.','2026-08-06 17:30:40'),(5,5,5,8,3,'Serviço razoável.','2026-08-06 17:30:40'),(6,6,6,9,5,'Recomendo bastante.','2026-08-06 17:30:40'),(7,7,7,10,5,'Ótimo profissional.','2026-08-06 17:30:40'),(8,8,8,1,4,'Muito educado.','2026-08-06 17:30:40'),(9,9,9,4,5,'Excelente experiência.','2026-08-06 17:30:40'),(10,10,10,7,4,'Bom atendimento.','2026-08-06 17:30:40');
/*!40000 ALTER TABLE `avaliacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `especie` enum('CACHORRO','GATO','PASSARO','CALOPSITA','PERIQUITO','PAPAGAIO','COELHO','HAMSTER','PORQUINHO_DA_INDIA','CHINCHILA','FURAO','TARTARUGA','CAGADO','IGUANA','LAGARTO','COBRA','PEIXE','MINI_PIG','CAVALO') NOT NULL,
  `raca` varchar(50) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `porte` enum('PEQUENO','MEDIO','GRANDE','GIGANTE') NOT NULL,
  `observacoes` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pet_usuario` (`usuario_id`),
  CONSTRAINT `fk_pet_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES (1,1,'Rex','CACHORRO','Labrador',5,'GRANDE','Muito dócil'),(2,2,'Mimi','GATO','Siamês',2,'PEQUENO','Brincalhona'),(3,3,'Thor','CACHORRO','Golden Retriever',4,'GRANDE','Muito ativo'),(4,4,'Luna','COELHO','Lion Head',1,'PEQUENO',''),(5,5,'Pipoca','CALOPSITA','Calopsita',3,'PEQUENO','Gosta de cantar'),(6,6,'Nemo','PEIXE','Betta',1,'PEQUENO',''),(7,7,'Bolinha','HAMSTER','Sírio',1,'PEQUENO',''),(8,8,'Mel','CACHORRO','Shih Tzu',6,'PEQUENO','Usa remédio'),(9,9,'Fred','PAPAGAIO','Papagaio',9,'MEDIO','Fala bastante'),(10,10,'Trovão','CAVALO','Mangalarga',8,'GIGANTE','Muito calmo');
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestadores`
--

DROP TABLE IF EXISTS `prestadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestadores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `aceita_hospedagem` tinyint(1) NOT NULL,
  `aceita_passeio` tinyint(1) NOT NULL,
  `aceita_banho` tinyint(1) NOT NULL,
  `aceita_pequeno` tinyint(1) NOT NULL,
  `aceita_medio` tinyint(1) NOT NULL,
  `aceita_grande` tinyint(1) NOT NULL,
  `aceita_gigante` tinyint(1) NOT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `valor_hora` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `fk_prestador_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestadores`
--

LOCK TABLES `prestadores` WRITE;
/*!40000 ALTER TABLE `prestadores` DISABLE KEYS */;
INSERT INTO `prestadores` VALUES (1,2,1,1,1,1,1,0,0,'Cuidadora de cães e gatos',35.00),(2,3,1,1,0,1,1,1,0,'Passeios e hospedagem',45.00),(3,5,0,1,1,1,1,1,1,'Especialista em cães grandes',55.00),(4,6,1,0,1,1,0,0,0,'Hospedagem para pequenos pets',40.00),(5,8,1,1,1,1,1,1,0,'Banho e passeio',38.00),(6,9,1,1,0,1,1,1,1,'Cuidados completos',60.00),(7,1,0,1,0,1,0,0,0,'Passeios rápidos',30.00),(8,4,1,0,0,1,1,0,0,'Hospedagem em sítio',42.00),(9,7,0,1,1,1,1,0,0,'Passeios diários',34.00),(10,10,1,1,1,1,1,1,1,'Todos os serviços',65.00);
/*!40000 ALTER TABLE `prestadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarefas`
--

DROP TABLE IF EXISTS `tarefas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tutor_id` bigint(20) NOT NULL,
  `prestador_id` bigint(20) DEFAULT NULL,
  `pet_id` bigint(20) NOT NULL,
  `tipo_servico` enum('PASSEIO','BANHO','HOSPEDAGEM','CUIDADO_DOMICILIAR') NOT NULL,
  `status` enum('ABERTA','EM_ANDAMENTO','CONCLUIDA','CANCELADA') NOT NULL,
  `valor` double NOT NULL,
  `data_servico` datetime NOT NULL,
  `descricao` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tutor_id` (`tutor_id`),
  KEY `prestador_id` (`prestador_id`),
  KEY `pet_id` (`pet_id`),
  CONSTRAINT `tarefas_ibfk_1` FOREIGN KEY (`tutor_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `tarefas_ibfk_2` FOREIGN KEY (`prestador_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `tarefas_ibfk_3` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefas`
--

LOCK TABLES `tarefas` WRITE;
/*!40000 ALTER TABLE `tarefas` DISABLE KEYS */;
INSERT INTO `tarefas` VALUES (1,1,3,1,'PASSEIO','EM_ANDAMENTO',40,'2026-08-10 09:00:00','Passeio de 1 hora'),(2,2,3,2,'BANHO','EM_ANDAMENTO',60,'2026-08-11 14:00:00','Banho completo'),(3,3,5,3,'HOSPEDAGEM','CONCLUIDA',250,'2026-08-12 10:00:00','Hospedagem por 3 dias'),(4,4,6,4,'CUIDADO_DOMICILIAR','ABERTA',80,'2026-08-13 08:00:00','Trocar água e comida'),(5,5,8,5,'PASSEIO','CANCELADA',35,'2026-08-14 17:00:00','Passeio no parque'),(6,6,9,6,'HOSPEDAGEM','ABERTA',300,'2026-08-15 09:00:00','Viagem do tutor'),(7,7,10,7,'BANHO','CONCLUIDA',45,'2026-08-16 15:00:00','Banho simples'),(8,8,1,8,'PASSEIO','EM_ANDAMENTO',50,'2026-08-17 18:00:00','Passeio noturno'),(9,9,4,9,'CUIDADO_DOMICILIAR','ABERTA',120,'2026-08-18 10:00:00','Alimentação e limpeza'),(10,10,7,10,'HOSPEDAGEM','ABERTA',400,'2026-08-19 08:00:00','Hospedagem por 5 dias'),(11,1,NULL,1,'PASSEIO','ABERTA',35,'2026-08-10 14:00:00','Passear com o cachorro por 1 hora.');
/*!40000 ALTER TABLE `tarefas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `tipo_residencia` enum('CASA','APARTAMENTO','SITIO') NOT NULL,
  `tipo_usuario` enum('CONTRATANTE','PRESTADOR','AMBOS') NOT NULL,
  `reputacao_media` decimal(3,2) DEFAULT 5.00,
  `data_cadastro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'João Silva','joao@gmail.com','123456','43999990001','Rua A','Centro','Londrina','PR','CASA','CONTRATANTE',5.00,'2026-08-06 17:30:40'),(2,'Maria Souza','maria@gmail.com','123456','43999990002','Rua B','Jardim','Londrina','PR','APARTAMENTO','PRESTADOR',5.00,'2026-08-06 17:30:40'),(3,'Carlos Mendes','carlos@gmail.com','123456','43999990003','Rua C','Centro','Cambé','PR','CASA','AMBOS',5.00,'2026-08-06 17:30:40'),(4,'Ana Oliveira','ana@gmail.com','123456','43999990004','Rua D','Centro','Ibiporã','PR','SITIO','CONTRATANTE',5.00,'2026-08-06 17:30:40'),(5,'Pedro Santos','pedro@gmail.com','123456','43999990005','Rua E','Jardim','Londrina','PR','CASA','PRESTADOR',5.00,'2026-08-06 17:30:40'),(6,'Julia Costa','julia@gmail.com','123456','43999990006','Rua F','Centro','Rolândia','PR','APARTAMENTO','AMBOS',5.00,'2026-08-06 17:30:40'),(7,'Lucas Pereira','lucas@gmail.com','123456','43999990007','Rua G','Centro','Arapongas','PR','CASA','CONTRATANTE',5.00,'2026-08-06 17:30:40'),(8,'Fernanda Alves','fernanda@gmail.com','123456','43999990008','Rua H','Jardim','Londrina','PR','APARTAMENTO','PRESTADOR',5.00,'2026-08-06 17:30:40'),(9,'Ricardo Gomes','ricardo@gmail.com','123456','43999990009','Rua I','Centro','Cambé','PR','CASA','AMBOS',5.00,'2026-08-06 17:30:40'),(10,'Patricia Lima','patricia@gmail.com','123456','43999990010','Rua J','Jardim','Londrina','PR','SITIO','CONTRATANTE',5.00,'2026-08-06 17:30:40');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-06 14:39:22
