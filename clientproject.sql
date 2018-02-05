-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 13 Juillet 2017 à 12:38
-- Version du serveur :  10.1.13-MariaDB
-- Version de PHP :  7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `clientproject`
--

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`article_id`, `title`, `category`) VALUES
(1, 'Angular 2 Tutorial using CLI', 'Angular'),
(2, 'Spring Boot Getting Started', 'Spring Boot'),
(3, 'Lambda Expressions Java 8 Example', 'Java 8'),
(4, 'Android AsyncTask Example', 'Android'),
(6, 'Yii2', 'PHP'),
(7, 'RAM DDR3', 'HardWare');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;