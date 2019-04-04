-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 04 avr. 2019 à 09:55
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bdd_pizzeria`
--

-- --------------------------------------------------------

--
-- Structure de la table `pizzas`
--

DROP TABLE IF EXISTS `pizzas`;
CREATE TABLE IF NOT EXISTS `pizzas` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `libelle` varchar(30) NOT NULL,
  `prix` double NOT NULL,
  `categorie` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pizzas`
--

INSERT INTO `pizzas` (`id`, `code`, `libelle`, `prix`, `categorie`) VALUES
(1, 'PEP', 'Pépéroni', 12.5, 'Viande'),
(2, 'MAR', 'Margherita', 14, 'Viande'),
(3, 'REI', 'Les 4 fromages', 11.5, 'Viande'),
(4, 'FRO', 'La canibale', 12, 'Viande'),
(5, 'CAN', 'La canibale', 12.5, 'Viande'),
(6, 'SAV', 'La savoyarde', 13, 'Viande'),
(7, 'ORI', 'L\'orientale', 13.5, 'Viande'),
(8, 'IND', 'L\'indienne', 14, 'Viande'),
(9, 'AZE', 'Azénor', 15, 'Viande'),
(11, 'AZR', 'Azrnon', 50, 'Viande');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
