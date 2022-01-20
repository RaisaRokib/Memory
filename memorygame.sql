-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2020 at 06:15 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `memorygame`
--

-- --------------------------------------------------------

--
-- Table structure for table `doubleplayerdraw`
--

CREATE TABLE `doubleplayerdraw` (
  `ID` int(11) NOT NULL,
  `Player1` varchar(30) NOT NULL,
  `Player2` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doubleplayerdraw`
--

INSERT INTO `doubleplayerdraw` (`ID`, `Player1`, `Player2`) VALUES
(1, 'Samina', 'Shayla'),
(2, 'Tonny', 'Suhi');

-- --------------------------------------------------------

--
-- Table structure for table `doubleplayernormal`
--

CREATE TABLE `doubleplayernormal` (
  `ID` int(11) NOT NULL,
  `Winner` varchar(30) NOT NULL,
  `Loser` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doubleplayernormal`
--

INSERT INTO `doubleplayernormal` (`ID`, `Winner`, `Loser`) VALUES
(1, 'Raisa', 'Zuthi'),
(2, 'Anika', 'Afroza');

-- --------------------------------------------------------

--
-- Table structure for table `doubleplayeruserentry`
--

CREATE TABLE `doubleplayeruserentry` (
  `ID` int(11) NOT NULL,
  `Player1` varchar(30) NOT NULL,
  `Player2` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `singleloser`
--

CREATE TABLE `singleloser` (
  `ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `singleloser`
--

INSERT INTO `singleloser` (`ID`, `Name`) VALUES
(1, 'Raisa'),
(2, 'Zuthi');

-- --------------------------------------------------------

--
-- Table structure for table `singleplayeruserentry`
--

CREATE TABLE `singleplayeruserentry` (
  `ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `singlewinner`
--

CREATE TABLE `singlewinner` (
  `ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `singlewinner`
--

INSERT INTO `singlewinner` (`ID`, `Name`) VALUES
(1, 'Modina'),
(2, 'Punni');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doubleplayerdraw`
--
ALTER TABLE `doubleplayerdraw`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `doubleplayernormal`
--
ALTER TABLE `doubleplayernormal`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `doubleplayeruserentry`
--
ALTER TABLE `doubleplayeruserentry`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `singleloser`
--
ALTER TABLE `singleloser`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `singleplayeruserentry`
--
ALTER TABLE `singleplayeruserentry`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `singlewinner`
--
ALTER TABLE `singlewinner`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doubleplayerdraw`
--
ALTER TABLE `doubleplayerdraw`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doubleplayernormal`
--
ALTER TABLE `doubleplayernormal`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doubleplayeruserentry`
--
ALTER TABLE `doubleplayeruserentry`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `singleloser`
--
ALTER TABLE `singleloser`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `singleplayeruserentry`
--
ALTER TABLE `singleplayeruserentry`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `singlewinner`
--
ALTER TABLE `singlewinner`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
