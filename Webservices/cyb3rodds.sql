-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22-Nov-2017 às 23:54
-- Versão do servidor: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cyb3rodds`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `info_utilizadores`
--

CREATE TABLE `info_utilizadores` (
  `id` int(11) NOT NULL,
  `id_utilizador` int(11) NOT NULL,
  `firstname` varchar(100) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `date_created` varchar(100) COLLATE utf8_bin NOT NULL,
  `profile_img` varchar(100) COLLATE utf8_bin NOT NULL,
  `sex` varchar(20) COLLATE utf8_bin NOT NULL,
  `country` varchar(100) COLLATE utf8_bin NOT NULL,
  `answer` varchar(100) COLLATE utf8_bin NOT NULL,
  `question` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `info_utilizadores`
--

INSERT INTO `info_utilizadores` (`id`, `id_utilizador`, `firstname`, `lastname`, `email`, `date_created`, `profile_img`, `sex`, `country`, `answer`, `question`) VALUES
(1, 1, 'miguel', 'santos', 'miguel@gmail.com', '22/11/2017', 'Default.png', 'male', 'Afghanistan', 'pikapi', 'what is your pokemon?');

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilizadores`
--

CREATE TABLE `utilizadores` (
  `id` int(11) NOT NULL,
  `username` varchar(200) COLLATE utf8_bin NOT NULL,
  `password` varchar(200) COLLATE utf8_bin NOT NULL,
  `isDeleted` tinyint(4) NOT NULL,
  `banned` tinyint(4) NOT NULL,
  `group_id` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `utilizadores`
--

INSERT INTO `utilizadores` (`id`, `username`, `password`, `isDeleted`, `banned`, `group_id`) VALUES
(1, 'miguel', '$2y$11$9EiB38oucBLrFwt9XD5Nf.HXV54iaV/d6pHu/noaG6J1tCMPN1qau', 0, 0, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilizador_carteira`
--

CREATE TABLE `utilizador_carteira` (
  `id` int(11) NOT NULL,
  `id_utilizador` int(11) NOT NULL,
  `cyb3rmoney` int(11) NOT NULL,
  `realmoney` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `utilizador_carteira`
--

INSERT INTO `utilizador_carteira` (`id`, `id_utilizador`, `cyb3rmoney`, `realmoney`) VALUES
(1, 1, 49600, 50);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `info_utilizadores`
--
ALTER TABLE `info_utilizadores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilizadores`
--
ALTER TABLE `utilizadores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `utilizador_carteira`
--
ALTER TABLE `utilizador_carteira`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `info_utilizadores`
--
ALTER TABLE `info_utilizadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `utilizadores`
--
ALTER TABLE `utilizadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `utilizador_carteira`
--
ALTER TABLE `utilizador_carteira`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
