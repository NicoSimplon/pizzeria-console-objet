-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 05 avr. 2019 à 05:25
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

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
  `libelle` varchar(60) NOT NULL,
  `prix` double NOT NULL,
  `categorie` enum('Viande','Poisson','Sans_Viande','Default') NOT NULL DEFAULT 'Default',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pizzas`
--

INSERT INTO `pizzas` (`id`, `code`, `libelle`, `prix`, `categorie`) VALUES
(22, 'PEP', 'Pépéroni', 12.5, 'Viande'),
(23, 'MAR', 'Margherita', 14, 'Viande'),
(24, 'REI', 'La Reine', 11.5, 'Viande'),
(25, 'FRO', 'Les 4 fromages', 12, 'Sans_Viande'),
(26, 'CAN', 'La canibale', 12.5, 'Viande'),
(27, 'SAV', 'La savoyarde', 13, 'Viande'),
(28, 'ORI', 'L\'orientale', 13.5, 'Viande'),
(29, 'IND', 'L\'indienne', 14, 'Poisson'),
(31, 'AZA', 'Azara', 15, 'Default');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
