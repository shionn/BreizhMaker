-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Lun 24 Février 2020 à 11:31
-- Version du serveur :  10.1.26-MariaDB-0+deb9u1
-- Version de PHP :  7.0.33-0+deb9u5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `wow`
--

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `dkp`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `dkp` (
`id` int(11)
,`name` varchar(64)
,`class` varchar(16)
,`dkp` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Structure de la table `dkp-entry`
--

CREATE TABLE `dkp-entry` (
  `id` int(11) NOT NULL,
  `player` int(11) NOT NULL,
  `raid` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `reason` varchar(512) DEFAULT NULL,
  `value-type` varchar(32) NOT NULL,
  `value` int(11) NOT NULL,
  `value-percent` int(11) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `class` varchar(16) NOT NULL,
  `rank` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `raid`
--

CREATE TABLE `raid` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `start` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end` datetime DEFAULT NULL,
  `running` tinyint(1) NOT NULL DEFAULT '0',
  `boss` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `raid-entry`
--

CREATE TABLE `raid-entry` (
  `raid` int(11) NOT NULL,
  `player` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `pass` varchar(256) NOT NULL,
  `role` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la vue `dkp`
--
DROP TABLE IF EXISTS `dkp`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dkp`  AS  select `p`.`id` AS `id`,`p`.`name` AS `name`,`p`.`class` AS `class`,ifnull(sum(`e`.`value`),0) AS `dkp` from (`player` `p` left join `dkp-entry` `e` on((`p`.`id` = `e`.`player`))) group by `p`.`id` ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `dkp-entry`
--
ALTER TABLE `dkp-entry`
  ADD PRIMARY KEY (`id`),
  ADD KEY `player` (`player`),
  ADD KEY `raid` (`raid`),
  ADD KEY `user` (`user`);

--
-- Index pour la table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `raid`
--
ALTER TABLE `raid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `running` (`running`);

--
-- Index pour la table `raid-entry`
--
ALTER TABLE `raid-entry`
  ADD PRIMARY KEY (`raid`,`player`),
  ADD KEY `raid-entry-player` (`player`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `dkp-entry`
--
ALTER TABLE `dkp-entry`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `player`
--
ALTER TABLE `player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `raid`
--
ALTER TABLE `raid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `dkp-entry`
--
ALTER TABLE `dkp-entry`
  ADD CONSTRAINT `dkp-entry-player` FOREIGN KEY (`player`) REFERENCES `player` (`id`),
  ADD CONSTRAINT `dkp-entry-raid` FOREIGN KEY (`raid`) REFERENCES `raid` (`id`),
  ADD CONSTRAINT `dkp-entry-user` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `raid-entry`
--
ALTER TABLE `raid-entry`
  ADD CONSTRAINT `raid-entry-player` FOREIGN KEY (`player`) REFERENCES `player` (`id`),
  ADD CONSTRAINT `raid-entry-raid` FOREIGN KEY (`raid`) REFERENCES `raid` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
