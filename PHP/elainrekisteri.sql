-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 29.03.2023 klo 10:30
-- Palvelimen versio: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elainrekisteri`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `ilmoitukset`
--

CREATE TABLE `ilmoitukset` (
  `elain_id` int(6) NOT NULL,
  `elain_laji` int(5) NOT NULL,
  `elain_nimi` text NOT NULL,
  `elain_syntymaaika` text NOT NULL,
  `elain_rotu` text NOT NULL,
  `elain_sukupuoli` int(5) NOT NULL,
  `elain_vari` text NOT NULL,
  `elain_rekisteroity` int(5) NOT NULL,
  `elain_sijoitus` int(5) NOT NULL,
  `elaimen_isa` text NOT NULL,
  `elaimen_aiti` text NOT NULL,
  `astutus_pvm` text NOT NULL,
  `arvioitu_synnytys` text NOT NULL,
  `elain_poikueet` int(50) NOT NULL,
  `lisa_tietoja` text NOT NULL,
  `ilmoitus_paivays` date NOT NULL,
  `ilmoitus_id` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `ilmoitukset`
--

INSERT INTO `ilmoitukset` (`elain_id`, `elain_laji`, `elain_nimi`, `elain_syntymaaika`, `elain_rotu`, `elain_sukupuoli`, `elain_vari`, `elain_rekisteroity`, `elain_sijoitus`, `elaimen_isa`, `elaimen_aiti`, `astutus_pvm`, `arvioitu_synnytys`, `elain_poikueet`, `lisa_tietoja`, `ilmoitus_paivays`, `ilmoitus_id`) VALUES
(28, 1, 'Iines', '17/6/2021', 'Suurihopea / Belgianjätti mix', 1, 'musta', 1, 1, '', '', '', '', 2, '', '2023-03-25', 2),
(29, 1, 'Hertta', '17/6/2021', 'Suurihopea / Belgianjätti mix', 1, 'Ruskea', 1, 1, '', '', '10/11/2022', '11/12/2022', 1, '9 poikasta, hyvä pesä', '2023-03-25', 2),
(30, 1, 'Teppo', '9/1/2021', 'Suurihopea / Belgianjätti / Uudenseelannin punainen mix', 1, 'Ruskea', 1, 1, '', '', '', '', 0, '', '2023-03-25', 2),
(31, 1, 'Mörkö', '11/6/2022', 'Rex', 2, 'Castor (ruskea)', 1, 1, '', '', '', '', 0, '', '2023-03-25', 2),
(32, 1, 'Mimosa', '7/7/2022', 'Rex', 1, 'Musta', 1, 1, '', '', '', '', 1, '', '2023-03-25', 2);

-- --------------------------------------------------------

--
-- Rakenne taululle `kayttajat`
--

CREATE TABLE `kayttajat` (
  `kayttaja_id` int(6) NOT NULL,
  `kayttaja_taso` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'user',
  `kayttaja_tunnus` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kayttaja_salasana` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kayttaja_sahkoposti` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `kayttajat`
--

INSERT INTO `kayttajat` (`kayttaja_id`, `kayttaja_taso`, `kayttaja_tunnus`, `kayttaja_salasana`, `kayttaja_sahkoposti`) VALUES
(2, 'user', 'mikko', '7b9e4d4f3bc9b6658ee6de5a926cfb1eda204d20fc09d2798c4c1a0f06147f34', 'mikkotesti@gov'),
(4, 'user', 'teppo', '2372ecbf69830aa731157dbc424d9b58067a398ed8bd7302695bcbe5b469ff58', 'tepivaan@gov'),
(5, 'user', 'testaaja', '16a4fea74311aed7dcad4851d9e02e8a6aa14b7afeb6c99faa5d00d466a041e2', 'testaaja@gov');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ilmoitukset`
--
ALTER TABLE `ilmoitukset`
  ADD PRIMARY KEY (`elain_id`),
  ADD KEY `INDEX_ILMOITUS_PAIVAYS` (`ilmoitus_paivays`),
  ADD KEY `myyja_id` (`ilmoitus_id`);

--
-- Indexes for table `kayttajat`
--
ALTER TABLE `kayttajat`
  ADD PRIMARY KEY (`kayttaja_id`),
  ADD UNIQUE KEY `UNIQUE_KAYTTAJA_SAHKOPOSTI` (`kayttaja_sahkoposti`),
  ADD UNIQUE KEY `UNIQUE_KAYTTAJA_TUNNUS` (`kayttaja_tunnus`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ilmoitukset`
--
ALTER TABLE `ilmoitukset`
  MODIFY `elain_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `kayttajat`
--
ALTER TABLE `kayttajat`
  MODIFY `kayttaja_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Rajoitteet vedostauluille
--

--
-- Rajoitteet taululle `ilmoitukset`
--
ALTER TABLE `ilmoitukset`
  ADD CONSTRAINT `ilmoitukset_ibfk_1` FOREIGN KEY (`ilmoitus_id`) REFERENCES `kayttajat` (`kayttaja_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
