-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.23 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for christmas
CREATE DATABASE IF NOT EXISTS `christmas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `christmas`;

-- Dumping structure for table christmas.dare
DROP TABLE IF EXISTS `dare`;
CREATE TABLE IF NOT EXISTS `dare` (
  `dare_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `is_completed` int(1),
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dare_id`),
  KEY `FKfpng7hs9o6rf28qchgcb99ibd` (`friend_id`),
  CONSTRAINT `FKfpng7hs9o6rf28qchgcb99ibd` FOREIGN KEY (`friend_id`) REFERENCES `friend` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table christmas.friend
DROP TABLE IF EXISTS `friend`;
CREATE TABLE IF NOT EXISTS `friend` (
  `friend_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
