-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.0.67-community-nt - MySQL Community Edition (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para wsnufs
CREATE DATABASE IF NOT EXISTS `wsnufs` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `wsnufs`;

-- Copiando estrutura para tabela wsnufs.sensors
CREATE TABLE IF NOT EXISTS `sensors` (
  `id` int(11) NOT NULL auto_increment,
  `idSensor` int(11) NOT NULL COMMENT 'Codigo do sensor no CupCarbon',
  `nome` varchar(50) default NULL COMMENT 'Nome do sensor baseado no codigo',
  `Command` varchar(255) default NULL COMMENT 'Comando a ser enviado para o sensor',
  `Type` char(5) default NULL COMMENT 'Tipo de sensor, b = base station, s=sensor, ch=Cluster Head',
  `baterypercent` tinyint(4) NOT NULL COMMENT 'Percentual da Bateria',
  `baterycons` double NOT NULL COMMENT 'Consumo de energia do processo',
  `bateryini` double NOT NULL COMMENT 'Bateria no inicio da simulação',
  `baterylevel` double NOT NULL COMMENT 'Nivel Atual da Bateria',
  `deltanext` double NOT NULL COMMENT 'DeltaNext (eLiteSense)',
  `lgx1` double NOT NULL COMMENT 'Longitude X do Sensor',
  `lgx2` double NOT NULL COMMENT 'Longitude X do Comunicador',
  `lgy1` double NOT NULL COMMENT 'Longitude Y do Sensor',
  `lgy2` double NOT NULL COMMENT 'Longitude Y do Comunicador',
  `Vf` double NOT NULL COMMENT 'Sensoriamento atual (eLiteSense)',
  `Vi` double NOT NULL COMMENT 'Sensoriamento anterior (eLiteSense)',
  `Xf` double NOT NULL COMMENT 'Parametro de sensoriamento atual (eLiteSense)',
  `Xi` double NOT NULL COMMENT 'Parametro de sensoriamento anterior (eLiteSense)',
  `Data` datetime NOT NULL COMMENT 'Data e hora da recepção do dados sensor',
  PRIMARY KEY  (`id`),
  KEY `DATA_INDEX` (`Data`,`idSensor`,`baterypercent`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Tabela dos sensores';

-- Exportação de dados foi desmarcado.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
