-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 31 mars 2019 à 18:52
-- Version du serveur :  10.1.31-MariaDB
-- Version de PHP :  7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mastermind`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` int(10) NOT NULL,
  `pseudo` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id`, `pseudo`, `mdp`) VALUES
(1, 'ad/min1', 'yeey');

-- --------------------------------------------------------

--
-- Structure de la table `classementglobal`
--

CREATE TABLE `classementglobal` (
  `id` int(10) NOT NULL,
  `classement` int(100) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classementglobal`
--

INSERT INTO `classementglobal` (`id`, `classement`) VALUES
(5, 5),
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(16, 16),
(17, 17);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(10) NOT NULL,
  `pseudo` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL,
  `score` int(100) NOT NULL DEFAULT '0',
  `nbrJeuxEffectue` int(100) NOT NULL DEFAULT '0',
  `scoreDerPartie` int(100) DEFAULT '0',
  `nbrJeuxGagne` int(100) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id`, `pseudo`, `mdp`, `score`, `nbrJeuxEffectue`, `scoreDerPartie`, `nbrJeuxGagne`) VALUES
(1, 'Ieshi', 'blabla', 0, 4, 10, 3),
(2, 'Ieshii', 'blablaa', 0, 0, 0, 0),
(3, 'Ieshiii', 'blablaa', 0, 0, 0, 0),
(4, 'bb', 'bb', 0, 0, 0, 0),
(5, 'hh', 'hh', 10, 7, 10, 4),
(16, 'hey', 'hey', 0, 0, 0, 0),
(17, '', '', 0, 0, 0, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classementglobal`
--
ALTER TABLE `classementglobal`
  ADD KEY `id` (`id`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `classementglobal`
--
ALTER TABLE `classementglobal`
  ADD CONSTRAINT `classementglobal_ibfk_1` FOREIGN KEY (`id`) REFERENCES `joueur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
