-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 05 Février 2018 à 15:27
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
-- Base de données
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `article_id` int(5) NOT NULL,
  `title` varchar(200) CHARACTER SET latin1 NOT NULL,
  `category` varchar(100) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`article_id`, `title`, `category`) VALUES
(1, 'Angular 2 Tutorial using CLI', 'Angular'),
(2, 'Spring Boot Getting Started', 'Spring Boot'),
(3, 'Lambda Expressions Java 8 Example', 'Java 8'),
(4, 'Android AsyncTask Example', 'Android'),
(6, 'Yii2', 'PHP'),
(7, 'RAM DDR3', 'HardWare'),
(44, 'ggg', 'ggg'),
(45, '???????', '??????');

-- --------------------------------------------------------

--
-- Structure de la table `authority`
--

CREATE TABLE `authority` (
  `id_authority` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `authority`
--

INSERT INTO `authority` (`id_authority`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

CREATE TABLE `document` (
  `id_document` int(10) NOT NULL,
  `titre_doc` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `chemin` varchar(255) CHARACTER SET latin1 NOT NULL,
  `article_id_article` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `document`
--

INSERT INTO `document` (`id_document`, `titre_doc`, `description`, `chemin`, `article_id_article`) VALUES
(74, 'referenceAngular.pdf', NULL, 'ressources/referenceAngular.pdf', 44);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 NOT NULL,
  `firstname` varchar(50) CHARACTER SET latin1 NOT NULL,
  `lastname` varchar(50) CHARACTER SET latin1 NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `lastpasswordresetdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `firstname`, `lastname`, `email`, `enabled`, `lastpasswordresetdate`) VALUES
(1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2017-08-02'),
(2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2017-08-02'),
(3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2017-08-02'),
(4, 'Hazem', '$2a$10$jKdPByJ3wI9V14s8Qm8gmONUWO91RsqdH9vqwWPqQb2oM.1aXJ.la', 'Hazem', 'Haouari', 'hazem.elhaouari@gmail.com', 1, '2017-08-04'),
(5, 'Hamed', '$2a$10$PzDC7U4c.CJal.hs3CJ.uO7Bb5NANaRGt8DeRfeCYCDuvaXSmU3Eq', 'Hamed', 'Haouari', 'hazem.elhaouari@enis.tn', 1, '2017-08-04'),
(6, 'HamedHaouari', '$2a$10$aTBYpEySqcqgV.P/c9oZbOutLilU5282teE4o2HFOiyRwbOygnAlm', 'Hamed', 'Haouari', 'hamed.haouari@gmail.com', 1, '2017-08-04');

-- --------------------------------------------------------

--
-- Structure de la table `user_authority`
--

CREATE TABLE `user_authority` (
  `user_id_user` int(11) NOT NULL,
  `authority_id_authority` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `user_authority`
--

INSERT INTO `user_authority` (`user_id_user`, `authority_id_authority`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`article_id`);

--
-- Index pour la table `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id_authority`);

--
-- Index pour la table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id_document`),
  ADD KEY `article_id_article` (`article_id_article`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `user_authority`
--
ALTER TABLE `user_authority`
  ADD KEY `user_id_user` (`user_id_user`),
  ADD KEY `authority_id_authority` (`authority_id_authority`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `article_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT pour la table `authority`
--
ALTER TABLE `authority`
  MODIFY `id_authority` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `document`
--
ALTER TABLE `document`
  MODIFY `id_document` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`article_id_article`) REFERENCES `articles` (`article_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user_authority`
--
ALTER TABLE `user_authority`
  ADD CONSTRAINT `user_authority_ibfk_1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `user_authority_ibfk_2` FOREIGN KEY (`authority_id_authority`) REFERENCES `authority` (`id_authority`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
