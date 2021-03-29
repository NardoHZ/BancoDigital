CREATE DATABASE  IF NOT EXISTS `bancodigital` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bancodigital`;
-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: bancodigital
-- ------------------------------------------------------
-- Server version	10.1.38-MariaDB

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
-- Table structure for table `agencia`
--

DROP TABLE IF EXISTS `agencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencia` (
  `ID_Agencia` int(11) NOT NULL,
  `Nome` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `Numero` int(4) DEFAULT '0',
  `Saldo` double DEFAULT NULL,
  PRIMARY KEY (`ID_Agencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia`
--

LOCK TABLES `agencia` WRITE;
/*!40000 ALTER TABLE `agencia` DISABLE KEYS */;
INSERT INTO `agencia` VALUES (1,'Banco Digital',3037,1502395.1999999997);
/*!40000 ALTER TABLE `agencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto` (
  `ID_Boleto` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) NOT NULL,
  `Valor` double DEFAULT NULL,
  `Codigo` varchar(45) DEFAULT NULL,
  `Data_Emissao` date DEFAULT NULL,
  `Validade` date DEFAULT NULL,
  `Data_Pagamento` varchar(45) DEFAULT NULL,
  `Status_Boleto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_Boleto`),
  KEY `ID_Cliente` (`ID_Cliente`),
  CONSTRAINT `boleto_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
INSERT INTO `boleto` VALUES (3,2,2000,'12167343102962923455362190204677756094978659','2019-06-04','2019-06-07','2019-06-04 11:45:50','Pago'),(4,1,539.75,'25580121277625803309257018081185972660751771','2019-06-04','2019-06-07','2019-06-04 12:25:10','Pago');
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tExecutar_pGerarExtratoDeposito`
  AFTER UPDATE ON `boleto`
  FOR EACH ROW
BEGIN
  CALL pGerarExtratoDeposito();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `ID_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) DEFAULT NULL,
  `Sobrenome` varchar(45) DEFAULT NULL,
  `CPF` varchar(45) DEFAULT NULL,
  `Data_Nascimento` date DEFAULT NULL,
  `Renda` varchar(45) DEFAULT NULL,
  `Profissao` varchar(45) DEFAULT NULL,
  `Sexo` varchar(45) DEFAULT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Senha` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`ID_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Cliente','Teste','111.111.111-11','1993-05-11','Entre R$ 1.996,00 e R$ 3.992,00','Nenhuma','Masculino','(73)98894-1423','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),(2,'asdfasdf','sadfasdf','222.222.222-22','2019-06-11','Entre R$ 1.996,00 e R$ 3.992,00','sdafasd','Feminino','(44)44444-4444','a5ad7e6d5225ad00c5f05ddb6bb3b1597a843cc92f6cf188490ffcb88a1ef4ef');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conta` (
  `ID_Conta` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) NOT NULL,
  `Agencia` varchar(45) NOT NULL DEFAULT '3037',
  `Conta` varchar(45) NOT NULL,
  `Digito` varchar(45) NOT NULL,
  `Saldo` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Conta`),
  KEY `fk_Conta_Cliente1_idx` (`ID_Cliente`),
  CONSTRAINT `fk_Conta_Cliente1` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (1,1,'3037','1001526','0',8144.549999999996),(2,2,'3037','1005051','3',2000);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `ID_Departamento` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Agencia` int(11) DEFAULT '1',
  `NomeDepartamento` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Departamento`),
  KEY `ID_Agencia_idx` (`ID_Agencia`),
  CONSTRAINT `ID_Agencia` FOREIGN KEY (`ID_Agencia`) REFERENCES `agencia` (`ID_Agencia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,1,'Administrativo'),(2,1,'Relações Humanas'),(3,1,'Atendente'),(4,1,'Financeiro');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprestimo` (
  `ID_Emprestimo` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) NOT NULL,
  `Valor_Emprestimo` double DEFAULT NULL,
  `Divida_Total` double DEFAULT NULL,
  `Divida_Parcial` double DEFAULT NULL,
  `Parcelas_Total` int(11) DEFAULT NULL,
  `Parcelas_Parcial` int(11) DEFAULT NULL,
  `Valor_Parcelas` double DEFAULT NULL,
  `Juros` double DEFAULT '0.2',
  `Status` varchar(45) DEFAULT 'Aguardando aprovação',
  PRIMARY KEY (`ID_Emprestimo`),
  KEY `fk_Emprestimo_Cliente1_idx` (`ID_Cliente`),
  CONSTRAINT `fk_Emprestimo_Cliente1` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (1,1,11976,14371.2,0,6,0,2395.2000000000003,0.2,'Pago'),(2,1,12,14.4,14.4,6,6,2.4,0.2,'Negado');
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco_cliente`
--

DROP TABLE IF EXISTS `endereco_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco_cliente` (
  `ID_EnderecoCliente` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Logradouro` varchar(45) NOT NULL,
  `Bairro` varchar(45) NOT NULL,
  `CEP` varchar(45) NOT NULL,
  `UF` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID_EnderecoCliente`),
  KEY `ID_Cliente` (`ID_Cliente`),
  CONSTRAINT `endereco_cliente_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_cliente`
--

LOCK TABLES `endereco_cliente` WRITE;
/*!40000 ALTER TABLE `endereco_cliente` DISABLE KEYS */;
INSERT INTO `endereco_cliente` VALUES (1,1,'Rua C, Nº21','Júlio Aderne','45550-000','BA'),(2,2,'safads','sadfasdf','11111-111','ES');
/*!40000 ALTER TABLE `endereco_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco_funcionario`
--

DROP TABLE IF EXISTS `endereco_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco_funcionario` (
  `ID_EnderecoFuncionario` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ID_Funcionario` int(11) DEFAULT NULL,
  `Logradouro` varchar(45) NOT NULL,
  `Bairro` varchar(45) NOT NULL,
  `CEP` varchar(45) NOT NULL,
  `UF` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID_EnderecoFuncionario`),
  KEY `ID_Funcionario` (`ID_Funcionario`),
  CONSTRAINT `endereco_funcionario_ibfk_1` FOREIGN KEY (`ID_Funcionario`) REFERENCES `funcionario` (`ID_Funcionario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_funcionario`
--

LOCK TABLES `endereco_funcionario` WRITE;
/*!40000 ALTER TABLE `endereco_funcionario` DISABLE KEYS */;
INSERT INTO `endereco_funcionario` VALUES (1,1,'Rua 2','Bairro','55555-555','BA');
/*!40000 ALTER TABLE `endereco_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extrato`
--

DROP TABLE IF EXISTS `extrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extrato` (
  `ID_Extrato` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Conta` int(11) NOT NULL,
  `Data` date DEFAULT NULL,
  `Descricao` varchar(45) DEFAULT NULL,
  `Entrada` double DEFAULT NULL,
  `Saida` double DEFAULT NULL,
  `Saldo` double DEFAULT NULL,
  PRIMARY KEY (`ID_Extrato`),
  KEY `fk_Extrato_Conta1_idx` (`ID_Conta`),
  CONSTRAINT `fk_Extrato_Conta1` FOREIGN KEY (`ID_Conta`) REFERENCES `conta` (`ID_Conta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extrato`
--

LOCK TABLES `extrato` WRITE;
/*!40000 ALTER TABLE `extrato` DISABLE KEYS */;
INSERT INTO `extrato` VALUES (1,2,'2019-06-04','Transferência',539.75,NULL,0),(2,1,'2019-06-04','Transferência',NULL,539.75,12000),(3,2,'2019-06-04','Depósito',2000,NULL,2539.75),(4,1,'2019-06-04','Depósito',NULL,2000,10000),(5,1,'2019-06-04','Empréstimo',11976,NULL,10000),(6,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,21976),(7,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,19580.8),(8,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,17185.6),(9,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,14790.399999999998),(10,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,12395.199999999997),(11,1,'2019-06-04','Empréstimo',NULL,2395.2000000000003,9999.999999999996),(12,2,'2019-06-04','Depósito',2000,NULL,2000),(13,1,'2019-06-04','Depósito',539.75,NULL,8144.549999999996),(14,2,'2019-06-04','Depósito',NULL,539.75,2000);
/*!40000 ALTER TABLE `extrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `ID_Funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Departamento` int(11) DEFAULT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Sobrenome` varchar(45) DEFAULT NULL,
  `CPF` varchar(45) DEFAULT NULL,
  `Data_Nascimento` date DEFAULT NULL,
  `Salario` double DEFAULT NULL,
  `Sexo` varchar(45) DEFAULT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Senha` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`ID_Funcionario`),
  KEY `ID_Departamento` (`ID_Departamento`),
  CONSTRAINT `ID_Departamento` FOREIGN KEY (`ID_Departamento`) REFERENCES `departamento` (`ID_Departamento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,4,'Gerente','Financeiro','555.555.555-55','1985-09-07',27500,'Masculino','(55)55555-5555','9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatorio_financeiro`
--

DROP TABLE IF EXISTS `relatorio_financeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relatorio_financeiro` (
  `ID_RelatorioFinanceiro` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Departamento` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Descricao` varchar(45) DEFAULT NULL,
  `Entrada` double DEFAULT NULL,
  `Saida` double DEFAULT NULL,
  `Saldo` double DEFAULT NULL,
  PRIMARY KEY (`ID_RelatorioFinanceiro`),
  KEY `departamentoID` (`ID_Departamento`),
  CONSTRAINT `departamentoID` FOREIGN KEY (`ID_Departamento`) REFERENCES `departamento` (`ID_Departamento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatorio_financeiro`
--

LOCK TABLES `relatorio_financeiro` WRITE;
/*!40000 ALTER TABLE `relatorio_financeiro` DISABLE KEYS */;
INSERT INTO `relatorio_financeiro` VALUES (1,4,'2019-06-04','Empréstimo',NULL,11976,NULL),(2,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL),(3,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL),(4,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL),(5,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL),(6,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL),(7,4,'2019-06-04','Empréstimo',2395.2000000000003,NULL,NULL);
/*!40000 ALTER TABLE `relatorio_financeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacao`
--

DROP TABLE IF EXISTS `transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transacao` (
  `ID_Transacao` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Conta` int(11) NOT NULL,
  `Conta` varchar(45) DEFAULT NULL,
  `Agencia` varchar(45) DEFAULT NULL,
  `Digito` varchar(45) DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Data` date DEFAULT NULL,
  PRIMARY KEY (`ID_Transacao`),
  KEY `fk_Transferencia_Conta1_idx` (`ID_Conta`),
  CONSTRAINT `fk_Transferencia_Conta1` FOREIGN KEY (`ID_Conta`) REFERENCES `conta` (`ID_Conta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacao`
--

LOCK TABLES `transacao` WRITE;
/*!40000 ALTER TABLE `transacao` DISABLE KEYS */;
INSERT INTO `transacao` VALUES (1,1,'1005051','3037','3',539.75,'2019-06-04');
/*!40000 ALTER TABLE `transacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tExecutar_pGerarExtratoTransferencia`
  AFTER INSERT ON `transacao` FOR EACH ROW
BEGIN
  CALL pGerarExtratoTransferencia();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary table structure for view `vextrato`
--

DROP TABLE IF EXISTS `vextrato`;
/*!50001 DROP VIEW IF EXISTS `vextrato`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vextrato` (
  `idConta` tinyint NOT NULL,
  `totalEntrada` tinyint NOT NULL,
  `totalSaida` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vrelatoriofinanceiro`
--

DROP TABLE IF EXISTS `vrelatoriofinanceiro`;
/*!50001 DROP VIEW IF EXISTS `vrelatoriofinanceiro`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vrelatoriofinanceiro` (
  `ID_Departamento` tinyint NOT NULL,
  `totalEntrada` tinyint NOT NULL,
  `totalSaida` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'bancodigital'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `eExcluirBoletoExpirado` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `eExcluirBoletoExpirado` ON SCHEDULE EVERY 1 MINUTE STARTS '2019-05-31 12:48:14' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Responsável por excluir boletos expirados' DO BEGIN
  DELETE FROM BOLETO
  WHERE Status_Boleto = 'Expirado';
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `etPagamentoEmprestimo` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `etPagamentoEmprestimo` ON SCHEDULE EVERY 10 SECOND STARTS '2019-05-17 23:19:52' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Responsável por realizar o desconto das parcelas diretamente da' DO BEGIN
  START TRANSACTION;
    SET @minID = (SELECT MIN(ID_Emprestimo) FROM Emprestimo);
    SET @maxID = (SELECT MAX(ID_Emprestimo) FROM Emprestimo);
    
    WHILE @minID <= @maxID DO
    
      SET @idCliente = (SELECT ID_Cliente FROM Emprestimo WHERE ID_Emprestimo = @minID AND Parcelas_Parcial > 0 AND Status = 'Aprovado');
      SET @valor = (SELECT Valor_Parcelas FROM Emprestimo WHERE ID_Emprestimo = @minID AND Parcelas_Parcial > 0 AND Status = 'Aprovado');
      SET @idConta = (SELECT ID_Conta FROM Conta WHERE ID_Cliente = @idCliente);
      SET @saldoConta = (SELECT Saldo FROM Conta WHERE ID_Conta = @idConta);
      SET @saldoAgencia = (SELECT Saldo FROM Agencia WHERE ID_Agencia = 1);
      
      IF @idCliente > 0 THEN
        UPDATE CONTA 
        SET Saldo = Saldo - @valor
        WHERE ID_Cliente = @idCliente;
        
        UPDATE Emprestimo 
        SET Divida_Parcial = Divida_Parcial - Valor_Parcelas,
        Parcelas_Parcial = Parcelas_Parcial - 1
        WHERE ID_Emprestimo = @minID;
        
        UPDATE Agencia
        SET Saldo = Saldo + @valor
        WHERE ID_Agencia = 1;
        
        INSERT INTO Extrato(ID_Conta, Data, Descricao, Saida, Saldo)
        VALUES(@idConta, CURDATE(), 'Empréstimo', @valor, @saldoConta);
        
        INSERT INTO Relatorio_Financeiro(ID_Departamento, Data, Descricao, Entrada, Saldo)
        VALUES(1, CURDATE(), 'Empréstimo', @valor, @saldoCaixa);
      END IF;
      
      SET @minID = @minID + 1;
    END WHILE;
    
    UPDATE Emprestimo
    SET Status = 'Pago',
    Divida_Parcial = 0
    WHERE Parcelas_Parcial = 0;
  COMMIT;
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
/*!50106 DROP EVENT IF EXISTS `eValidadeBoleto` */;;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `eValidadeBoleto` ON SCHEDULE EVERY 5 SECOND STARTS '2019-05-12 20:06:05' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Responsável por verificar a validade do boleto e atualizar o sta' DO BEGIN
  UPDATE Boleto
  SET Status_Boleto = 'Expirado'
  WHERE Validade < CURDATE();
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'bancodigital'
--
/*!50003 DROP PROCEDURE IF EXISTS `pBackupExtrato` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pBackupExtrato`(
idConta int(11),
nomeE varchar(45),
sobrenomeE varchar(45)
)
BEGIN
  SET @nomeSobrenome = CONCAT(nomeE, ' ' , sobrenomeE);
  SET @extrato = CONCAT('SELECT Data, Descricao, Entrada, Saida, Saldo INTO OUTFILE ',
  "'",'C:/Users/Public/Desktop/Extrato de ', @nomeSobrenome ,' - ' ,DATE_FORMAT(now(), '%d-%l-%Y %H.%i.%s'), + 0, '.csv',"'",
  "FIELDS TERMINATED BY ','" ,
  " ENCLOSED BY '\'" ,' FROM bancodigital.Extrato WHERE ID_Conta = ', idConta) ;
  
  PREPARE stmt FROM @extrato; 
  EXECUTE stmt; 
  DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pBackupRelatorio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pBackupRelatorio`(
idDepartamento int(11)
)
BEGIN
  SET @extrato = CONCAT('SELECT Data, Descricao, Entrada, Saida, Saldo INTO OUTFILE ',
  "'",'C:/Users/Public/Desktop/Extrato - Relatório Financeiro - ' ,DATE_FORMAT(now(), '%d-%l-%Y %H.%i.%s'), + 0, '.csv',"'",
  "FIELDS TERMINATED BY ','" ,
  " ENCLOSED BY '\'" ,' FROM bancodigital.Relatorio_Financeiro WHERE ID_Departamento = ', idDepartamento) ;
  
  PREPARE stmt FROM @extrato; 
  EXECUTE stmt; 
  DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pBuscarClientePorCPF` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pBuscarClientePorCPF`(
cpf varchar(45)
)
BEGIN
  SELECT * FROM Endereco_Cliente AS e 
  INNER JOIN Cliente AS cl 
  ON e.ID_Cliente = cl.ID_Cliente 
  INNER JOIN Conta AS co 
  ON cl.ID_Cliente = co.ID_Cliente
  AND cl.CPF = cpf;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pBuscarFuncionarioPorCPF` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pBuscarFuncionarioPorCPF`(
cpf varchar(45)
)
BEGIN
  SELECT * FROM Endereco_Funcionario AS e 
  INNER JOIN Funcionario AS f 
  ON e.ID_Funcionario = f.ID_Funcionario 
  INNER JOIN Departamento AS d 
  ON d.ID_Departamento = f.ID_Departamento
  AND f.CPF = cpf;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pCancelarSolicitacaoEmprestimo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pCancelarSolicitacaoEmprestimo`(
idEmprestimo int(11)
)
BEGIN
  SET @status = (SELECT Status FROM Emprestimo WHERE ID_Emprestimo = idEmprestimo);
  IF @status = 'Aguardando aprovação' THEN
    UPDATE Emprestimo
    SET Status = 'Cancelado'
    WHERE ID_Emprestimo = idEmprestimo;
  END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pDeletarBoleto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pDeletarBoleto`(
idBoleto int(11)
)
BEGIN
  DELETE FROM Boleto
  WHERE ID_Boleto = idBoleto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pDeletarConta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pDeletarConta`(
idCliente int(11)
)
BEGIN
  DELETE FROM Cliente
  WHERE ID_Cliente = idCliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pGerarBoleto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pGerarBoleto`(
idCliente int(11),
Valor double,
Codigo varchar(45),
dataEmissao date,
Validade date,
statusBoleto varchar(45)
)
BEGIN
  INSERT INTO Boleto(ID_Cliente, Valor, Codigo, Data_Emissao, Validade, Status_Boleto)
          VALUES(idCliente, Valor, Codigo, dataEmissao, Validade, statusBoleto);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pGerarExtratoDeposito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pGerarExtratoDeposito`()
BEGIN
  SET @pagamento = (SELECT Data_Pagamento FROM Boleto WHERE Data_Pagamento = (SELECT MAX(Data_Pagamento) FROM Boleto));
  SET @idCliente = (SELECT ID_Cliente FROM Boleto WHERE Data_Pagamento = @pagamento);
  SET @idConta = (SELECT ID_Conta FROM Conta WHERE ID_Cliente = @idCliente);
  SET @valor = (SELECT Valor FROM Boleto WHERE Data_Pagamento = @pagamento);
  SET @saldo = (SELECT Saldo FROM Conta WHERE ID_Cliente = @idCliente);
  SET @statusBoleto = (SELECT Status_Boleto FROM Boleto WHERE Data_Pagamento = @pagamento);
  IF @statusBoleto = 'Pago' THEN
    INSERT INTO Extrato(ID_Conta, Data, Descricao, Entrada, Saldo) 
    VALUES (@idConta, CURDATE(), 'Depósito', @valor, @saldo);
  END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pGerarExtratoTransferencia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pGerarExtratoTransferencia`()
BEGIN
  SET @idTransacao = (SELECT MAX(ID_Transacao) FROM Transacao);
  SET @valor = (SELECT Valor FROM Transacao WHERE ID_Transacao = @idTransacao);
  SET @conta = (SELECT Conta FROM Transacao WHERE ID_Transacao = @idTransacao);
  SET @digito = (SELECT Digito FROM Transacao WHERE ID_Transacao = @idTransacao);
  SET @idConta = (SELECT ID_Conta FROM Conta WHERE Conta = @conta AND Digito = @digito);
  SET @saldo = (SELECT Saldo FROM Conta WHERE ID_Conta = @idConta);
  
  INSERT INTO Extrato(ID_Conta, Data, Descricao, Entrada, Saldo)
  VALUES(@idConta, CURDATE(), 'Transferência', @valor, @saldo);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pIdentificarFavorecido` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pIdentificarFavorecido`(
conta varchar(45),
digito varchar(45)
)
BEGIN
  SELECT Cliente.Nome, Cliente.Sobrenome, Cliente.ID_Cliente
  FROM Conta, Cliente
  WHERE Conta.Conta = conta
  AND Conta.Digito = digito
  AND Conta.ID_Cliente = Cliente.ID_Cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pIdentificaTaxaJuros` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pIdentificaTaxaJuros`()
BEGIN
  SELECT 
  COLUMN_DEFAULT AS taxaJuros
  FROM
  INFORMATION_SCHEMA.COLUMNS
  WHERE
  TABLE_SCHEMA = 'BancoDigital' 
  AND TABLE_NAME = 'Emprestimo'
  AND COLUMN_NAME = 'Juros';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pInnerJoinClientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pInnerJoinClientes`()
BEGIN
  SELECT * FROM Endereco_Cliente AS e 
  INNER JOIN Cliente AS cl 
  ON e.ID_Cliente = cl.ID_Cliente 
  INNER JOIN Conta AS co 
  ON cl.ID_Cliente = co.ID_Cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pInnerJoinEmprestimo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pInnerJoinEmprestimo`()
BEGIN
  SELECT * FROM Emprestimo AS e 
  INNER JOIN Cliente AS cl 
  ON e.ID_Cliente = cl.ID_Cliente 
  INNER JOIN Conta AS co 
  ON cl.ID_Cliente = co.ID_Cliente
  INNER JOIN Endereco_Cliente AS en
  ON en.ID_Cliente = co.ID_Cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pInnerJoinEmprestimoPorID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pInnerJoinEmprestimoPorID`(

idEmprestimo int(11)

)
BEGIN

  SELECT * FROM Emprestimo AS e 

  INNER JOIN Cliente AS cl 

  ON e.ID_Cliente = cl.ID_Cliente 

  INNER JOIN Conta AS co 

  ON cl.ID_Cliente = co.ID_Cliente

  INNER JOIN Endereco_Cliente AS en

  ON en.ID_Cliente = co.ID_Cliente

  AND e.ID_Emprestimo = idEmprestimo;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pInnerJoinFuncionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pInnerJoinFuncionario`(
idFuncionario int(11)
)
BEGIN
  SELECT * FROM Funcionario AS f 
  INNER JOIN Endereco_Funcionario AS e 
  ON f.ID_Funcionario = e.ID_Funcionario
  INNER JOIN Departamento AS d 
  ON f.ID_Departamento = d.ID_Departamento
  AND f.ID_Funcionario = idFuncionario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pNegarEmprestimo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pNegarEmprestimo`(
idEmprestimo int(11)
)
BEGIN
  UPDATE Emprestimo
  SET Status = 'Negado'
  WHERE ID_Emprestimo = idEmprestimo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pSolicitarEmprestimo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pSolicitarEmprestimo`(
idCliente int(11),
valor double,
parcelas int(11)
)
BEGIN
  INSERT INTO Emprestimo(ID_Cliente, Valor_Emprestimo, Divida_Total, Divida_Parcial, Parcelas_Total, Parcelas_Parcial, Valor_Parcelas)
  VALUES(idCliente, valor, valor + valor * Juros, Divida_Total, parcelas, parcelas, Divida_Total / parcelas);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ptAprovarEmprestimo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ptAprovarEmprestimo`(
idEmprestimo int(11),
valor double,
idConta int
)
BEGIN
  START TRANSACTION;
    SET @saldoConta = (SELECT Saldo FROM Conta WHERE ID_Conta = idConta);
    SET @saldoAgencia = (SELECT Saldo FROM Agencia WHERE ID_Agencia = 1);
  
    UPDATE Emprestimo
    SET Status = 'Aprovado'
    WHERE ID_Emprestimo = idEmprestimo;
    
    UPDATE Conta
    SET Saldo = Saldo + valor
    WHERE ID_Conta = idConta;
    
    UPDATE Agencia 
    SET Saldo = Saldo - valor
    WHERE ID_Agencia = 1;
    
    INSERT INTO Extrato(ID_Conta, Data, Descricao, Entrada, Saldo)
    VALUES(idConta, CURDATE(), 'Empréstimo', valor, @saldoConta);
    
    INSERT INTO Relatorio_Financeiro(ID_Departamento, Data, Descricao, Saida, Saldo)
    VALUES(4, CURDATE(), 'Empréstimo', valor, @saldoCaixa);
  COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ptCadastro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ptCadastro`(
Nome varchar(45),
Sobrenome varchar(45),
CPF varchar(45),
Data_Nascimento date,
Renda varchar(45),
Profissao varchar(45),
Sexo varchar(45),
Telefone varchar(45),
Senha varchar(245),
Logradouro varchar(45),
Bairro varchar(45),
CEP varchar(45),
UF varchar(2),
Conta varchar(45),
Digito varchar(45)
)
BEGIN
  START TRANSACTION;
    INSERT INTO CLIENTE (Nome, Sobrenome, CPF, Data_Nascimento, Renda, Profissao, Sexo, Telefone, Senha)
            VALUES (Nome, Sobrenome, CPF, Data_Nascimento, Renda, Profissao, Sexo, Telefone, Senha);
    SET @ultimoID = (SELECT MAX(ID_Cliente) FROM Cliente);
    INSERT INTO Endereco_Cliente (ID_Cliente, Logradouro, Bairro, CEP, UF) VALUES (@ultimoID, Logradouro, Bairro, CEP, UF);
    INSERT INTO Conta (ID_Cliente, Conta, Digito) VALUES (@ultimoID,Conta, Digito);
  COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ptPagamento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ptPagamento`(
codigoBoleto varchar(45),
idDepositante int(11)
)
BEGIN
  START TRANSACTION;
	SET SQL_SAFE_UPDATES=0;
    SET @valorBoleto = (SELECT Valor FROM Boleto WHERE Validade > CURDATE() AND Codigo = codigoBoleto AND Status_Boleto = 'Pendente');
    SET @idCliente = (SELECT ID_Cliente FROM Boleto WHERE Validade > CURDATE() AND Codigo = codigoBoleto AND Status_Boleto = 'Pendente');
    IF @valorBoleto > 20 THEN
		SET @saldoAtual = (SELECT Saldo FROM Conta WHERE ID_Cliente = idDepositante);
    SET @idContaDepositante = (SELECT ID_Conta FROM Conta WHERE ID_Cliente = idDepositante);
		IF @saldoAtual >= @valorBoleto THEN
			UPDATE Conta
			SET Saldo = @saldoAtual - @valorBoleto
			WHERE ID_Cliente = idDepositante;
			
      UPDATE Conta
			SET Saldo = Saldo + @valorBoleto
			WHERE ID_Cliente = @idCliente;
			
      UPDATE Boleto
			SET Status_Boleto = 'Pago'
			WHERE Codigo = codigoBoleto;
      
      UPDATE Boleto
      SET Data_Pagamento = NOW()
      WHERE Codigo = codigoBoleto;
      
      INSERT INTO Extrato(ID_Conta, Data, Descricao, Saida, Saldo)
      VALUES(@idContaDepositante, CURDATE(), 'Depósito', @valorBoleto, @saldoAtual - @valorBoleto);
		END IF;
	END IF;
  COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ptTransacao` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ptTransacao`(
idTransmitente int(11),#ID_Conta De quem está transferindo
contaT varchar (45), #Conta favorecido
agenciaT varchar (45), #Agencia favorecido
digitoT varchar (45), #Digito favorecido
valorT double #Valor transferencia
)
BEGIN
  START TRANSACTION;
    SET @saldoTransmitente = (SELECT Saldo FROM Conta WHERE ID_Conta = idTransmitente);
      IF @saldoTransmitente >= valorT THEN
        SET @idTransmissario = (SELECT ID_Conta FROM Conta WHERE Conta = contaT AND Digito = digitoT);
        SET @saldoTransmissario = (SELECT Saldo FROM Conta WHERE ID_Conta = @idTransmissario);
        IF idTransmitente <> @idTransmissario THEN
          IF @idTransmissario > 0 THEN
            INSERT INTO Transacao (ID_Conta, Conta, Agencia, Digito, Valor, Data) 
            VALUES (idTransmitente, contaT, agenciaT, digitoT, valorT, CURDATE());
            
            UPDATE Conta
            SET Saldo = @saldoTransmitente - valorT
            WHERE ID_Conta = idTransmitente;
            
            UPDATE Conta
            SET Saldo = @saldoTransmissario + valorT
            WHERE ID_Conta = @idTransmissario;
            
            INSERT INTO Extrato(ID_Conta, Data, Descricao, Saida, Saldo)
            VALUES (idTransmitente, CURDATE(), 'Transferência', valorT, @saldoTransmitente - valorT);
          END IF;
        END IF;    
      END IF;
  COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pUpdateCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pUpdateCliente`(
IN ID_Cliente_P int(11),
IN Renda varchar(45),
IN Profissao varchar(45),
IN Telefone varchar(45)
)
BEGIN
  UPDATE Cliente
  SET Renda = Renda,
    Profissao = Profissao,
    Telefone = Telefone
  WHERE ID_Cliente = ID_Cliente_P;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pUpdateEndereco` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pUpdateEndereco`(
ID_Cliente_P int(11),
Logradouro varchar(45),
Bairro varchar(45),
CEP varchar(45),
UF varchar (2)
)
BEGIN
  UPDATE endereco_Cliente
  SET Logradouro = Logradouro,
    Bairro = Bairro,
    CEP = CEP,
    UF = UF
  WHERE ID_EnderecoCliente = ID_Cliente_P;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pVerificarEmprestimoExistente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pVerificarEmprestimoExistente`(
idCliente int(11)
)
BEGIN
  SET @idEmprestimo = (SELECT MAX(ID_Emprestimo) FROM Emprestimo WHERE ID_Cliente = idCliente);
  SELECT * FROM Emprestimo WHERE ID_Emprestimo = @idEmprestimo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `vextrato`
--

/*!50001 DROP TABLE IF EXISTS `vextrato`*/;
/*!50001 DROP VIEW IF EXISTS `vextrato`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vextrato` AS select `extrato`.`ID_Conta` AS `idConta`,sum(`extrato`.`Entrada`) AS `totalEntrada`,sum(`extrato`.`Saida`) AS `totalSaida` from `extrato` group by `extrato`.`ID_Conta` order by `extrato`.`Data` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vrelatoriofinanceiro`
--

/*!50001 DROP TABLE IF EXISTS `vrelatoriofinanceiro`*/;
/*!50001 DROP VIEW IF EXISTS `vrelatoriofinanceiro`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vrelatoriofinanceiro` AS select `relatorio_financeiro`.`ID_Departamento` AS `ID_Departamento`,sum(`relatorio_financeiro`.`Entrada`) AS `totalEntrada`,sum(`relatorio_financeiro`.`Saida`) AS `totalSaida` from `relatorio_financeiro` group by `relatorio_financeiro`.`ID_Departamento` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 13:28:22
