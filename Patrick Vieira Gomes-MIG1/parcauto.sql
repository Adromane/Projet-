-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 24 juil. 2023 à 15:08
-- Version du serveur : 8.0.30
-- Version de PHP : 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `parcauto`
--

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `numPlaque` varchar(50) NOT NULL DEFAULT '',
  `marque` varchar(50) NOT NULL,
  `modele` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `couleur` varchar(50) NOT NULL,
  `typeTrans` varchar(50) NOT NULL,
  `location` tinyint NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`numPlaque`, `marque`, `modele`, `couleur`, `typeTrans`, `location`) VALUES
('ABC123', 'Toyota', 'Camry', 'Rouge', 'Automatique', 0),
('BCD890', 'Hyundai', 'Elantra', 'Gris', 'Automatique', 1),
('DEF456', 'Honda', 'Civic', 'Bleu', 'Manuelle', 0),
('GHI789', 'Ford', 'Mustang', 'Jaune', 'Automatique', 1),
('JKL012', 'BMW', 'X5', 'Gris', 'Automatique', 0),
('MNO345', 'Audi', 'A4', 'Noir', 'Automatique', 1),
('OSD023', 'BMW', 'X5', 'Blanc', 'Automatique', 0),
('PQR678', 'Volkswagen', 'Golf', 'Rouge', 'Manuelle', 1),
('UIH020', 'Honda', 'Civic', 'Noir', 'Manuelle', 0),
('VWX234', 'Audi', 'A4', 'Bleu', 'Automatique', 1),
('YDF394', 'Toyota', 'Camry', 'Vert', 'Manuelle', 0),
('YZA567', 'Nissan', 'Sentra', 'Noir', 'Automatique', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`numPlaque`),
  ADD UNIQUE KEY `numPlaque` (`numPlaque`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
