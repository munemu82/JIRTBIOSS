-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2017 at 02:08 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

-- To run this script, open command line, login to mysql use the following
-- mysql> source <full directory path to the file>\jirtbios_v1.sql; 

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jirtbios_v1`
--
CREATE DATABASE IF NOT EXISTS jirtbios_v1;
-- --------------------------------------------------------

--
-- Create database user: `jirtbiosadmin` and grant all priveledges
--
GRANT USAGE ON *.* TO jirtbiosadmin@localhost IDENTIFIED BY 'jirtbiosadmin';
GRANT ALL PRIVILEGES ON jirtbios_v1.* TO jirtbiosadmin@localhost;
FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- use the database create tables and insert dummy records
--
use jirtbios_v1;
-- --------------------------------------------------------

--
-- Table structure for table `behavior`
--

CREATE TABLE IF NOT EXISTS `behavior` (
`behaviorId` int(11) NOT NULL,
  `behavior` varchar(100) NOT NULL,
  `studyId` varchar(50) NOT NULL,
  `status` varchar(3) NOT NULL DEFAULT 'N',
  `date_recorded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `behavior`
--

INSERT INTO `behavior` (`behaviorId`, `behavior`, `studyId`, `status`, `date_recorded`) VALUES
(1, 'Eating', 'EABIOS01', 'A', '2015-05-29 08:12:26'),
(2, 'Interacting', 'EABIOS01   ', 'A', '2015-05-29 08:12:26'),
(3, 'Running', 'EASBIOS03', 'N', '2015-05-29 08:12:26'),
(4, 'Fighting', 'EASBIOS01', 'N', '2015-05-29 08:12:26'),
(5, 'Standing', 'EABIOS01', 'A', '2015-05-29 08:12:26'),
(6, 'Moving', 'EABIOS01', 'A', '2015-05-29 08:12:26'),
(7, 'test', 'EABIOS01', 'N', '2015-05-29 08:12:26'),
(8, 'Eating', 'EABIOS04', 'N', '2015-05-29 12:45:48'),
(9, 'Amos', 'EABIOS02', 'N', '2015-07-01 11:53:58');

-- --------------------------------------------------------

--
-- Table structure for table `colour`
--

CREATE TABLE IF NOT EXISTS `colour` (
`colourId` int(11) NOT NULL,
  `colour` varchar(100) NOT NULL,
  `studyId` varchar(100) NOT NULL,
  `status` varchar(3) NOT NULL DEFAULT 'N',
  `date_recorded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `colour`
--

INSERT INTO `colour` (`colourId`, `colour`, `studyId`, `status`, `date_recorded`) VALUES
(1, 'Red', 'EABIOS01', 'A', '2015-05-29 08:14:43'),
(2, 'White', 'EABIOS01', 'A', '2015-05-29 08:14:43'),
(3, 'Black', 'EABIOS01', 'A', '2015-05-29 08:17:27'),
(4, 'Yellow', 'EABIOS04', 'N', '2015-05-29 12:45:01'),
(5, 'Gray', 'EABIOS04', 'N', '2015-05-29 12:45:26'),
(6, 'Grey', 'EABIOS01', 'A', '2016-04-10 08:21:09');

-- --------------------------------------------------------

--
-- Table structure for table `imagecaptures`
--

CREATE TABLE IF NOT EXISTS `imagecaptures` (
  `imageID` varchar(50) NOT NULL,
  `imagetimestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `location` varchar(100) NOT NULL,
  `sensorID` varchar(50) NOT NULL,
  `identificationStatus` char(1) NOT NULL DEFAULT 'n'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imagecaptures`
--

INSERT INTO `imagecaptures` (`imageID`, `imagetimestamp`, `location`, `sensorID`, `identificationStatus`) VALUES
('IMAG0003.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0004 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0004 2.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0004.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0005.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0006 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0006.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0007 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0007.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0008.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0009.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0010 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0010.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0011.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0012 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0012 (3).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0012.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0013 (2).JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'n'),
('IMAG0013 2.JPG', '2017-02-25 10:59:17', 'NSW', 'NSW Sensor', 'y'),
('IMAG0013 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'y'),
('IMAG0013.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0014 (2).JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'y'),
('IMAG0014 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0014.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0015 (2).JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'y'),
('IMAG0015 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'y'),
('IMAG0015.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0016 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0016.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0017 (2).JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0017 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0017 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0017.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0018 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0018 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0018.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0019 (2).JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0019.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0020 (2).JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0020.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0021.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230016.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230017.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230018.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230019.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230020.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230021.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230022.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230023.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230024.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230025.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230026.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230027.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230028.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG00230029.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0025 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0025 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0026 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0026 copy.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0028 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0028.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0029 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0029.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0030 2.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0030.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0031.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0032.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0033.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0036.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0040.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0041.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0042.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0043.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0044.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0045.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0058.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0087.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0097.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0100.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0101.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0102.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0114.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0118.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0119.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0120.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0121.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0122.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0134.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0139.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0148.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0151.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0154.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0155.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0191.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('IMAG0320.JPG', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730212.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730213.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730214.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730215.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730216.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730217.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730218.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730219.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730220.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('PICT00730221.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n'),
('test.jpg', '2017-02-25 10:59:18', 'NSW', 'NSW Sensor', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `imageidentity`
--

CREATE TABLE IF NOT EXISTS `imageidentity` (
`recordId` int(11) NOT NULL,
  `usercomment` text NOT NULL,
  `username` varchar(45) NOT NULL,
  `imageId` varchar(50) NOT NULL,
  `behavior` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL,
  `pose` varchar(50) NOT NULL,
  `children` varchar(1) DEFAULT NULL,
  `scale` varchar(50) DEFAULT NULL,
  `color` varchar(50) NOT NULL DEFAULT 'Gray',
  `species` varchar(50) NOT NULL DEFAULT 'unknown',
  `looksLike` varchar(50) NOT NULL,
  `studyId` varchar(25) NOT NULL DEFAULT 'DEFAULT00',
  `recordTimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imageidentity`
--

INSERT INTO `imageidentity` (`recordId`, `usercomment`, `username`, `imageId`, `behavior`, `number`, `pose`, `children`, `scale`, `color`, `species`, `looksLike`, `studyId`, `recordTimestamp`) VALUES
(184, 'Multiple sheep', 'munemu82', 'IMAG0010 (2).JPG', 'Eating|', '3', 'Left', 'n', 'Stocky', 'White', 'Sheep', 'Sheep', 'EABIOS01', '2017-02-25 01:03:00'),
(186, 'rabit', 'munemu82', 'IMAG0004 2.JPG', 'Eating|', '1', 'Left', 'n', 'Lanky', 'Grey', 'Rabbit', 'Rabbit', 'EABIOS01', '2017-02-25 04:25:08'),
(188, 'Kangaroo', 'munemu82', 'IMAG0012 (3).JPG', 'Eating|', '1', 'Left', 'n', 'Lanky', 'Red', 'Red Kangaroo', 'Red Kangaroo', 'EABIOS01', '2017-02-25 04:36:47'),
(189, 'Kangaroo', 'munemu82', 'IMAG0013 2.JPG', 'Eating|', '1', 'Left', 'n', 'Lanky', 'Red', 'Red Kangaroo', 'Red Kangaroo', 'EABIOS01', '2017-02-25 04:37:19'),
(190, 'Sheep', 'munemu82', 'IMAG0014 (2).JPG', 'Eating|', '1', 'Left', 'n', 'Lanky', 'Red', 'Sheep', 'Sheep', 'EABIOS01', '2017-02-25 04:37:47'),
(191, 'test', 'munemu82', 'IMAG0015 copy.JPG', 'Eating|', '1', 'Left', 'n', 'Lanky', 'Grey', 'Sheep', 'Sheep', 'EABIOS01', '2017-02-25 04:38:19'),
(192, 'test', 'munemu82', 'IMAG0004.JPG', 'Standing|', '1', 'Left', 'n', 'Lanky', 'N/A', 'Rabbit', 'Rabbit', 'EABIOS01', '2017-04-16 00:55:10'),
(193, 'Test', 'munemu82', 'IMAG0007 (2).JPG', 'Moving|', '1', 'Left', 'n', 'Lanky', 'Grey', 'Rabbit', 'Rabbit', 'EABIOS01', '2017-04-16 00:55:47'),
(194, 'Test', 'munemu82', 'IMAG0008.JPG', '', '2', 'Right', 'n', 'Stocky', 'White', 'Sheep', 'Sheep', 'EABIOS01', '2017-04-16 00:56:26'),
(195, 'j,mn,n', 'munemu82', 'image0', '', '2', 'N/A', 'y', 'N/A', 'N/A', 'Musky Rat-kangaroo', 'Musky Rat-kangaroo', 'EABIOS02', '2017-07-11 12:02:58');

-- --------------------------------------------------------

--
-- Table structure for table `lookslike`
--

CREATE TABLE IF NOT EXISTS `lookslike` (
`lookslikeId` int(11) NOT NULL,
  `speciesName` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lookslike`
--

INSERT INTO `lookslike` (`lookslikeId`, `speciesName`) VALUES
(16, 'Dog'),
(15, 'Echidna'),
(10, 'Goat'),
(1, 'Kangaroo'),
(6, 'Koala'),
(7, 'Possum'),
(8, 'Sheep'),
(2, 'Wallaby');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
`id` int(10) unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'A',
  `securityLevel` int(11) NOT NULL,
  `regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `first_name`, `last_name`, `email`, `uname`, `pass`, `status`, `securityLevel`, `regdate`) VALUES
(1, 'Amos', 'Munezero', 'munemu82@gmail.com', 'munemu82', 'K1DA9LRl9Rd9G+2TaEUg1Q==', 'A', 2, '2015-04-14 14:00:00'),
(2, 'Zuberi', 'Munezero', 'zub@gmail.com', 'zub2015', 'WKgtQG4fp2CewwRRIcO+5w==', 'A', 0, '2015-04-14 14:00:00'),
(5, 'mhkjhjkh', 'munezero', 'zuberi@gmail.com', 'zuberi', 'K1DA9LRl9Rd9G+2TaEUg1Q==', 'A', 0, '2015-04-25 08:15:38'),
(6, 'test', 'munezero', 'amosmunezero@gmail.com', 'amos', 'Xh3GrD+V8N8hj/T1/XyuQw==', 'A', 2, '2015-04-25 08:15:38'),
(7, 'TestUser 1', 'Munezero', 'testuser1@gmail.com', 'testuser1', 'hEqafSMfpTBdxkV8fzz8Hw==', 'A', 2, '2015-05-04 08:31:26'),
(8, 'testuser2', 'testuser2', 'testuser2@gmail.com', 'testuser2', '6+svyQaXf93JsatV/hjM1Q==', 'A', 1, '2015-05-04 08:41:30'),
(9, 'testuser3', 'testuser3', 'testuser3@gmail.com', 'testuser3', 'testuser3', 'A', 0, '2015-05-04 08:47:00'),
(10, 'testuser4', 'testuser4', 'testuser4@gmail.com', 'testuser4', 'testuser4', 'A', 0, '2015-05-04 08:48:27'),
(11, 'testuser5', 'testuser5', 'testuser5@gmail.com', 'testuser5', 'F3Dd9YxAyWENl3X7AoqWGQ==', 'A', 0, '2015-05-04 10:11:19'),
(13, 'testuser6', 'testuser6', 'testuser6@yahoo.com', 'testuser6', '3SOTc4IkrxLrjRMULviW7A==', 'A', 0, '2015-05-04 20:52:10'),
(14, 'testuser7', 'testuser7', 'theuser7@gmail.com', 'testuser7', 'WkShsEtExossiZcIouacloiAH/JqD5+loYm8+44VJU4=', 'A', 0, '2015-05-05 09:59:23'),
(16, 'testuser8', 'testuser8', 'testuser8@gmail.com', 'testuser8', 'SQfOrek523GImWpLQ59ahg==', 'A', 0, '2015-05-05 19:41:01'),
(17, 'testuser9', 'testuser9', 'testuser9@gmail.com', 'testuser9', 'hGGnpfwDiOaAVXN5GwtfsQ==', 'A', 0, '2015-05-05 19:47:21'),
(18, 'testuser10', 'testuser10', 'testuser10@gmail.com', 'testuser10', 'ZNeHVfGvhdrmnfvVtYtHTw==', 'A', 0, '2015-05-05 20:48:47'),
(19, 'testuser11', 'testuser11', 'testuser11@gmail.com', 'testuser11', 'DJ7Xqw0nQGJXs1VXzfL2bQ==', 'A', 0, '2015-05-07 20:32:29'),
(20, 'testuser12', 'testuser12', 'testuser12', 'testuser12', '+TtwBTv7S2dqzwH2XSfCTg==', 'A', 0, '2015-05-10 07:27:07'),
(21, 'testuser13', 'testuser13', 'testuser13@gmail.com', 'testuser13', '83K+KpwlawRDFZIK00sY6Q==', 'A', 0, '2015-05-10 07:52:05'),
(22, 'testuser14', 'testuser14', 'testuser14@gmail.com', 'testuser14', 'T7rFZ0Q0SY1LJZ6/t/rjaZEmmVNwl/c8Q1U5Wq9/hYI=', 'A', 1, '2015-05-10 08:03:13'),
(23, 'testuser15', 'testuser15', 'testuser15@gmail.com', 'testuser15', 'Rf4zRx8LpkKh1Y/ekd0me5zsmLpn5PCMEzSFvfDHZR8=', 'A', 1, '2015-06-27 21:25:55'),
(24, 'testuser16', 'testuser16', 'testuser16@gmail.com', 'testuser16', 'XMcK1XwZxoopJ7VyLg6Bew==', 'A', 1, '2015-06-27 21:26:42');

-- --------------------------------------------------------

--
-- Table structure for table `poses`
--

CREATE TABLE IF NOT EXISTS `poses` (
`poseId` int(11) NOT NULL,
  `pose` varchar(100) NOT NULL,
  `studyId` varchar(50) NOT NULL,
  `status` varchar(3) NOT NULL DEFAULT 'N',
  `date_recorded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `poses`
--

INSERT INTO `poses` (`poseId`, `pose`, `studyId`, `status`, `date_recorded`) VALUES
(1, 'Left', 'EABIOS01', 'A', '2015-05-29 08:13:54'),
(2, 'Right', 'EABIOS01', 'A', '2015-05-29 08:13:54'),
(3, 'Straight', 'EABIOS01', 'A', '2015-05-29 09:39:52'),
(4, 'Left', 'EABIOS04', 'N', '2015-05-29 12:46:05'),
(5, 'Back-sided', 'EABIOS01', 'N', '2015-07-01 11:51:35'),
(6, 'Amos', 'EABIOS02', 'A', '2015-07-01 11:54:58'),
(7, 'Test', 'EABIOS02', 'N', '2017-04-16 02:20:41');

-- --------------------------------------------------------

--
-- Table structure for table `scale`
--

CREATE TABLE IF NOT EXISTS `scale` (
`scaleId` int(11) NOT NULL,
  `scale` varchar(100) NOT NULL,
  `studyId` varchar(50) NOT NULL,
  `status` varchar(3) NOT NULL DEFAULT 'N',
  `date_recorded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scale`
--

INSERT INTO `scale` (`scaleId`, `scale`, `studyId`, `status`, `date_recorded`) VALUES
(1, 'Tall', 'EABIOS01', 'N', '2015-05-29 08:14:21'),
(2, 'Small', 'EABIOS01', 'N', '2015-05-29 08:14:21'),
(3, 'Stocky', 'EABIOS01', 'A', '2015-05-29 08:19:43'),
(4, 'Lanky', 'EABIOS01', 'A', '2015-05-29 23:06:06'),
(5, 'Amos', 'EABIOS01', 'N', '2015-07-02 08:18:55');

-- --------------------------------------------------------

--
-- Table structure for table `species`
--

CREATE TABLE IF NOT EXISTS `species` (
  `speciesId` varchar(100) NOT NULL,
  `speciesName` varchar(100) NOT NULL,
  `speciesDescription` text NOT NULL,
  `pose` varchar(50) NOT NULL,
  `colour` varchar(50) NOT NULL,
  `scale` varchar(50) NOT NULL,
  `lookslike1` text NOT NULL,
  `lookslike2` text NOT NULL,
  `lookslike3` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `species`
--

INSERT INTO `species` (`speciesId`, `speciesName`, `speciesDescription`, `pose`, `colour`, `scale`, `lookslike1`, `lookslike2`, `lookslike3`) VALUES
('Bet01', 'Rufous Bettong', '"""The rufous rat-kangaroo (Aepyprymnus rufescens), also known as the rufous bettong, is a small marsupial species of the family Potoroidae found in Australia. It is found in coastal and subcoastal regions from Newcastle in New South Wales to Cooktown in Queensland, and was formerly found in the Murray River Valley of New South Wales and Victoria.[3] It is not classified as threatened.[2] The rufous bettong is about the size of a full grown rabbit."""', 'Left', 'Red', 'Small', 'Rabbit', 'Potorro', 'Rat'),
('Bet02', 'Northern Bettong', '"""The northern bettong (Bettongia tropica) is a small potoroid marsupial which is restricted to some areas of mixed open Eucalyptus woodlands and Allocasuarina forests bordering rainforests in far northeastern Queensland, Australia.  This bettong is a solitary animal and is nocturnal. It spends the day in a well concealed nest constructed beneath either a tree within a clump of grass or from other litter collected at ground level. Nesting material is carried using its prehensile tail."""', 'Right', 'Red', 'Small', 'Rabbit', 'Potorro', 'Rat'),
('Bon01', 'Bond', '"""Bond sheep are an Australian sheep breed that was developed around 1909 near Lockhart, New South Wales by Thomas Bond when he mated Saxon-Peppin Merino ewes to stud Lincoln rams for primarily wool production."""', 'Left', 'Yellow', 'Small', 'NA', 'NA', 'NA'),
('Coo01', 'Coolalee', '"""short wool, meat sheep breed, developed from an eight-year breeding program commenced in 1968. The breed resulted from the crossing of the Wiltshire Horn, Suffolk, Hampshire Down, Poll Dorset, Lincoln and English Leicester sheep breeds."""', 'Right', 'Black', 'Small', 'Sheep', 'Goat', 'NA'),
('Cor01', 'Cormo', '"""The Cormo is an Australian breed of sheep developed in Tasmania by crossing Corriedale rams with superfine Saxon Merino ewes in the early 1960s. The name Cormo is derived from the names of two of the parent breeds, Corriedale and Merino."""', 'Straight', 'Black', 'Small', 'Sheep', 'NA', 'NA'),
('Ech01', 'Echidna', '"""Echidnas, sometimes known as spiny anteaters, belong to the family Tachyglossidae in the monotreme order of egg-laying mammals."""', 'Left', 'White', 'Stocky', 'Rabit', 'Rat', 'NA'),
('Ech02', 'Long-beaked echidna', 'NA', 'Right', 'White', 'Stocky', 'NA', 'NA', 'NA'),
('Ech03', 'short-beaked echidna', '"""maller of the species, and individuals vary in colour depending on their location. In the northern, hotter regions, echidnas are light brown, but they become darker with thicker hair further south. """', 'Back', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('Fox01', 'Red Fox', '"""The red fox, Vulpes vulpes, is the largest of the true foxes and the most abundant wild member of the Carnivora, being present across the entire Northern Hemisphere from the Arctic Circle to North Africa, Central America and Asia"""', 'Pose', 'Yellow', 'Tall', 'Dog', 'Wild cat', 'NA'),
('Gro01', 'Gromark', '"""Gromark sheep are a large-framed, plain bodied dual-purpose (meat and wool) breed of sheep that were under development in 1965 by Arthur C. Godlee at ""Marengo"", Tamworth, New South Wales. """', 'Pose', 'Black', 'Small', 'NA', 'NA', 'NA'),
('Harl01', 'Harlequin', '"""a muscular, firm, well-proportioned body. The head is longish with width between the eyes. Ears are upright and 10.16 to 12.7 (4 to 5 inches) in length."""', 'Back', 'Yellow', 'Tall', 'NA', 'NA', 'NA'),
('Kang01', 'Red Kangaroo', '"""This species is a very large kangaroo with long, pointed ears and a squared-off muzzle. Males have short, red-brown fur, fading to pale buff below and on the limbs. Females are smaller than males and are blue-grey with a brown tinge, pale grey below, although arid zone females are coloured more like males. It has two forelimbs with small claws, two muscular hind-limbs, which are used for jumping, and a strong tail which is often used to create a tripod when standing upright."""', 'Straight', 'Red', 'Stocky', 'Wallaby old', 'Common Wallaroo', 'Potoroo'),
('Kang02', 'Eastern Grey Kangaroo', '"""The Eastern grey kangaroo is the second largest and heaviest living marsupial and native land mammal in Australia. An adult male will commonly weigh around 50 to 66 kg (110 to 146 lb) whereas females commonly weigh around 17 to 40 kg (37 to 88 lb). Large males of this species are more heavily built and muscled than the lankier Red Kangaroo and can occasionally exceed normal dimensions. """', 'Left', 'Gray', 'Stocky', 'Wallaby', 'Common Wallaroo', 'Potoroo'),
('Kang03', 'Western Grey Kangaroo', '"""The western grey kangaroo is one of the largest kangaroos in Australia. It weighs 28–54 kg (62–120 lb) and its length is 0.84–1.1 m (2 ft 9 in–3 ft 7 in) with a 0.80–1.0 m (2 ft 7 in–3 ft 3 in) tail, standing approximately 1.3 m (4 ft 3 in) tall.[3] It exhibits sexual dimorphism with the male up to twice the size of female. It has thick, coarse fur with colour ranging from pale grey to brown; its throat, chest and belly have a paler colour.[4] It feeds at night, mainly on grasses but also on leafy shrubs and low trees. It has a nickname ""stinker"" because mature males have a distinctive curry-like odour."""', 'Right', 'Gray', 'Stocky', 'Wallaby', 'Common Wallaroo', 'Potoroo'),
('Kang04', 'Bennett''s Tree-kangaroo', '"""Bennett''s tree-kangaroo (Dendrolagus bennettianus) is a large tree-kangaroo. Males can weigh from 11.5 kg up to almost 14 kg (25 to 31 lbs), while the females range between about 8 to 10.6 kg (17.6 to 23 lbs). They are very agile and are able to leap 9 metres (30 ft) down to another branch and have been known to drop as far as 18 metres (59 ft) to the ground without injury"""', 'Left', 'Yellow', 'Stocky', 'Wallaby', 'Common Wallaroo', 'Red Kangaroo'),
('Kang05', 'Lumholtz''s Tree-kangaroo', '"""It is the smallest of all tree-kangaroos, with males weighing an average of 7.2 kg (16 lbs) and females 5.9 kg (13 lbs).[5] Its head and body length ranges from 480–650 mm, and its tail, 600–740 mm.[6] It has powerful limbs and has short, grizzled grey fur. Its muzzle, toes and tip of tail are black."""', 'Right', 'Green', 'Stocky', 'Red Kangaroo', 'Bennett''s Tree-kangaroo', 'Common Wallaroo'),
('Kang06', 'Musky Rat-kangaroo', '"""The musky rat-kangaroo (Hypsiprymnodon moschatus) is a marsupial species found only in the rainforests of northeast Australia. Although some scientists place this species as a subfamily (Hypsiprymnodontinae) of the family Potoroidae, the most recent classification[1] places it in the family Hypsiprymnodontidae with prehistoric rat-kangaroos."""', 'Pose', 'Red', 'Small', 'Rat', 'Rabbit', 'Kangaroo'),
('Koa01', 'Koala ', '"""The koala (Phascolarctos cinereus, or, inaccurately, koala bear[a]) is an arboreal herbivorous marsupial native to Australia. It is the only extant representative of the family Phascolarctidae, and its closest living relatives are the wombats.[3] The koala is found in coastal areas of the mainland''s eastern and southern regions, inhabiting Queensland, New South Wales, Victoria and South Australia. It is easily recognisable by its stout, tailless body; round, fluffy ears; and large, spoon-shaped nose. The koala has a body length of 60–85 cm (24–33 in) and weighs 4–15 kg (9–33 lb). Pelage colour ranges from silver grey to chocolate brown. Koalas from the northern populations are typically smaller and lighter in colour than their counterparts further south. It is possible that these populations are separate subspecies, but this is disputed."""', 'Straight', 'White', 'Small', 'Big mouse', 'Rabbit', 'NA'),
('Mon01', 'Monjon', '"""he monjon (Petrogale burbidgei), also known as the warabi and Burbridge''s rock weasel, is the smallest of the many species of rock-wallaby found in Australia. It is found in areas of the Kimberley region of Western Australia and also on some islands in the Bonaparte Archipelago."""', 'Right', 'Green', 'Lanky', 'NA', 'NA', 'NA'),
('Pol01', 'Poll Dorset', '"""The Poll Dorset is a short wool, meat producing sheep that was developed in Australia between 1937 and 1954 with the aim of breeding a true Dorset type sheep without horns."""', 'Back', 'Black', 'Small', 'NA', 'NA', 'NA'),
('Pos01', 'Possum', '"""A possum (plural form: possums) is any of about 70 small- to medium-sized arboreal marsupial species native to Australia, New Guinea, and Sulawesi (and introduced to New Zealand and China). The name derives from their resemblance to the opossums of the Americas (the name is from Algonquian wapathemwa, not Greek or Latin, so the plural is possums, not possa)."""', 'Left', 'White', 'Small', 'Rat', 'Rabbit', 'Mouse'),
('Pos02', 'Pygmy possums', '"""They eat nectar and insects, and are known to torpor (in some species even hibernate) during winter due to cold weather and shortage of food. They include Eastern Pygmy Possum (Cercartetus nanus), Western Pygmy Possum (Cercartetus concinnus), Mountain Pygmy Possum (Burramys parvus), Long-tailed Pygmy Possum (Cercartetus caudatus) and Little Pygmy Possum (Cercartetus lepidus). """', 'Right', 'White', 'Small', 'Mouse', 'Rat', 'Rabbit'),
('Pos03', 'Brushtail possums', '"""Brushtails live up to 11 years, and their species status is secure. They are successful animals and after they were intorduced to New Zealand from Australia, they become the worst pest over there. """', 'Back', 'White', 'Small', 'Mouse', 'NA', 'NA'),
('Pos04', 'Ringtail Possums', '""" They are all forest dwellers and nest in dreys, which are often in the fork of a tree and lined with grass, sticks and leaves. They are leaf eaters, and have a specialised digestive system to be able to eat eucaypt leaves (According to a reader, appartently they love Lemon Scented Gum)."""', 'Pose', 'White', 'Small', 'NA', 'NA', 'NA'),
('Pos05', 'Greater Glider', '"""Dark stripe along their back, and the gliders have membranes which help them glide. They are vocal animals, and most species live in groups although some are solitary. """', 'Straight', 'White', 'Small', 'Possum', 'NA', 'NA'),
('Pos06', 'Honey Possum', '"""the only member in its family and it is only found in South-western Wester Australia. As the name says, it feeds on nectar and pollen, and its Latin name points out that like tarsiers, it climbs without using claws (it hasn''t got any). They are social, nomadic animals and can enter torpor when food is scarce."""', 'Left', 'White', 'Small', 'NA', 'NA', 'NA'),
('Pos07', 'Cuscuses ', '"""The members of this group are primarily herbivores, but some species may occasionally eat small animals. They include Common Brushtail Possum (Trichosurus vulpecula), Short-eared Brushtail Possum (Trichosurus caninus), Mountain Brushtail Possum (Trichosurus cunninghami), Scaly-tailed Possum (Wyulda squamicaudata), Common Spotted Cuscus (Spilocuscus maculatus) and Southern Common Cuscus (Phalanger intercastellanus)."""', 'Right', 'White', 'Small', 'Possum', 'NA', 'NA'),
('Pos08', 'Feathertail Glider', '"""Has got a distinctive, feather like tail. It is a small glider, lives in loose groups, eats both insects and pollen, and is found in forests of eastern Australia."""', 'Back', 'White', 'Small', 'Possum', 'Mouse', 'Rabbit'),
('Pos09', 'Green Ringtail Possum', '"""The Green Ringtail Possum has thick soft greenish fur, with white patches below eyes and ears. Fur has mixture of black, grey, yellow and white giving it a greenish appearance"""', 'Pose', 'White', 'Small', 'Possum', 'Mouse', 'NA'),
('Pos10', 'Mountain Brushtail Possum', '"""The Mountain Brushtail Possum is grey above and whitish below. Some forms are black. Short rounded ears. The Common Brushtail Possum is similar but has more pointed ears."""', 'Straight', 'White', 'Small', 'NA', 'NA', 'NA'),
('Pot01', 'Gilbert''s Potoroo', '"""Gilbert''s potoroo, Potorous gilbertii, sometimes called the ""rat-kangaroo"", is Australia''s most endangered animal and one of the world''s most endangered mammals. It is a small nocturnal marsupial which lives in small groups or colonies. It has long hind feet and front feet with curved claws which it uses for digging food. Its body has large amounts of fur which helps with insulation, and its fur ranges between brown and grey; the color fading on its belly. This potoroo has a long, thin snout curving downward that it uses to smell its surroundings; this trait is common in all potoroo species."""', 'Back', 'Red', 'Small', 'Rabbit', 'Rat', 'Potoroo'),
('Quo01', 'Quokka', '"""The quokka (/?kw?k?/, Setonix brachyurus), the only member of the genus Setonix, is a small macropod about the size of a domestic cat.[2] Like other marsupials in the macropod family (such as kangaroos and wallabies), the quokka is herbivorous and mainly nocturnal. Quokkas can be found on some smaller islands off the coast of Western Australia, in particular on Rottnest Island just off Perth and Bald Island near Albany. A small mainland colony exists in the protected area of Two Peoples Bay Nature Reserve, where they co-exist with Gilbert''s potoroo."""', 'Straight', 'Red', 'Small', 'Potoroo', 'Rat', 'Rabbit'),
('Rab01', 'Tasmanian Pademelon', '"""The Tasmanian pademelon (Thylogale billardierii), also known as the rufous-bellied pademelon or red-bellied pademelon, is the sole endemic species of pademelon, marsupials found in Tasmania, and formerly throughout south-eastern Australia. This pademelon has developed heavier and bushier fur than its northern relatives, who inhabit northern Australia and Papua New Guinea."""', 'Pose', 'Red', 'Small', 'Rock Kangaroo', 'Rabbit', 'Kangaroo'),
('shp01', 'Australian Cashmere', '"""breed of domestic goat originating in Australia. Whilst retaining the fertility and hardiness of the bush goat, the Australian Cashmere is quite different in appearance and temperament. In mid winter it will have an excellent overall coverage of long, dense cashmere."""', 'Pose', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('shp02', 'Australian Miniature Goat', 'NA', 'Straight', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('shp03', 'Kalbian', 'NA', 'Left', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('shp04', 'Rabbit', 'NA', 'Right', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('shp05', 'Agente Bleu', 'NA', 'Back', 'Brown', 'Stocky', 'NA', 'NA', 'NA'),
('shp06', 'Cashmere Lop', 'NA', 'Pose', 'Brown', 'Tall', 'Rabbit', 'Mouse', 'NA'),
('shp07', 'Chinchilla Giganta', '"""finely boned, long, graceful body. Adult bucks heavier boned with bolder heads. Dewlap permissible in adult does."""', 'Straight', 'Yellow', 'Tall', 'NA', 'NA', 'NA'),
('shp08', 'Dwarf Lop', '"""Body compact (short) thickset and firm with wide shoulders, full chest, broad head, prominent crown and no visible neck. Ears are fully lopped, broad, thick, well furred with rounded ends."""', 'Left', 'Yellow', 'Tall', 'NA', 'NA', 'NA'),
('shp09', 'Jersey Wooly', '"""short, compact body, shoulders slightly narrower than the hips. The head is bold, well rounded and set close to the body. Ears are erect and short (63 mm, 2 ½ inches), well furred (without furnishings or tassels) with slightly rounded tips. """', 'Right', 'Yellow', 'Tall', 'NA', 'NA', 'NA'),
('Shp10', 'Sheep', '"""Sheep are quadrupedal, ruminant mammals typically kept as livestock. Like all ruminants, sheep are members of the order Artiodactyla, the even-toed ungulates"""', 'Straight', 'Yellow', 'Tall', 'NA', 'NA', 'NA'),
('Wal01', 'Agile Wallaby', '"""Male agile wallabies are considerably larger than females, having a head and body length of up to 85 cm (33 in) and weighing 16 to 27 kg (35 to 60 lb) while the females grow to 72 cm (28 in) in length and weigh 9 to 15 kg (20 to 33 lb). The tails of both sexes are long and flexible, giving a total length of double the head and body length. They have relatively large ears, which are edged with black, and the tip of the tail is also black. Their backs are sandy brown while their underparts are whitish. They have a dark stripe between the ears, a pale cheek stripe on each side of the face and another pale streak across the thighs"""', 'Back', 'Green', 'Tall', 'Red Kangaroo', 'Wallaby', 'Wallaroo'),
('Wal02', 'Black-striped Wallaby', '"""The black-striped wallaby (Macropus dorsalis), also known as the scrub wallaby, is a medium-sized wallaby found in Australia, from Townsville in Queensland to Narrabri in New South Wales. In New South Wales it is only found west of the Great Dividing Range. It is decreasing in these areas, but is not classified as threatened as a species yet.[3] The New South Wales population, however, is classified as endangered."""', 'Pose', 'Green', 'Tall', 'Red Kangaroo', 'Agile Wallaby', 'Tammar Wallaby'),
('Wal03', 'Tammar Wallaby', '"""The tammar wallaby (Macropus eugenii), also known as the dama wallaby or darma wallaby, is a small macropod native to South Australia and Western Australia.[2] Though its geographical range has been severely reduced since European colonisation, the tammar remains common within its reduced range and is listed as -Least Concern- by the International Union for Conservation of Nature (IUCN). It has been introduced to New Zealand and reintroduced to some areas of Australia where it had been previously eradicated. Skull differences distinguish tammars from Western Australia, Kangaroo Island and mainland South Australia, making them distinct population groups or possibly different subspecies"""', 'Straight', 'Green', 'Tall', 'Red Kangaroo', 'Agile Wallaby', 'Black-striped Wallaby'),
('Wal04', 'Kwoora (Western Brush Wallaby)', '"""The western brush wallaby (Macropus irma), also known as the black-gloved wallaby, is a species of wallaby found in the southwest coastal region of Western Australia. The wallaby''s main threat is predation by the introduced red fox (Vulpes vulpes).[3] The IUCN lists the western brush wallaby as Least Concern, as it remains fairly widespread and the population is believed to be stable or increasing, as a result of fox control programs."""', 'Left', 'Green', 'Tall', 'Red Kangaroo', 'Whiptail Wallaby', 'Kangaroo'),
('Wal05', 'Whiptail Wallaby', '"""It is distinguished by its paler colouring and white stripe under its face. Their faces have a chocolate-brown fur covering their muzzle. They are black and white on its chest and the rest is grey to brown fur. Males weigh from 14 to 26 kilograms and stand at a height from 70 to 93 cm. Females weigh from 7 to 15 kilograms and stand at a height from 65 to 75 cm."""', 'Right', 'Green', 'Tall', 'Kangaroo', 'Whiptail Wallaby', 'Red Kangaroo'),
('Wal06', 'Red-necked Wallaby', '"""Red-necked wallabies are distinguished by their black nose and paws, white stripe on the upper lip, and grizzled medium grey coat with a reddish wash across the shoulders. They can weigh 13.8 to 18.6 kilograms (30 to 41 lb) and attain a head-body length of 90 centimetres (35 in), although males are generally bigger than females. Red-necked wallabies are very similar in appearance to the Black-striped wallaby, the only difference being that Red-necked wallabies are larger, lack a black stripe down the back, and have softer fur.[3] Red-necked wallabies may live up to 9 years"""', 'Back', 'Green', 'Tall', 'Kangaroo', 'NA', 'NA'),
('Wal07', 'Swamp Wallaby', '"""The species name bicolor comes from the distinct colouring variation, with the typical grey coat of the macropods varied with a dark brown to black region on the back, and light yellow to rufous orange on the chest. A light coloured cheek stripe is usually present, and extremities of the body generally show a darker colouring, except for the tip of the tail, which is often white"""', 'Pose', 'Green', 'Tall', 'Kangaroo', 'NA', 'NA'),
('Wal08', 'Allied Rock-wallaby', '"""The species name bicolor comes from the distinct colouring variation, with the typical grey coat of the macropods varied with a dark brown to black region on the back, and light yellow to rufous orange on the chest. A light coloured cheek stripe is usually present, and extremities of the body generally show a darker colouring, except for the tip of the tail, which is often white"""', 'Straight', 'Green', 'Lanky', 'NA', 'NA', 'NA'),
('Wal09', 'Short-eared Rock-wallaby', '"""The short-eared rock-wallaby (Petrogale brachyotis) is a species of rock-wallaby found in northern Australia, in the northernmost parts of Northern Territory and Western Australia. It is much larger than its two closest relatives, the nabarlek (Petrogale concinna) and the monjon (Petrogale burbidgei)."""', 'Left', 'Green', 'Lanky', 'NA', 'NA', 'NA'),
('Wal10', 'Cape York Rock-wallaby', '"""The Cape York rock-wallaby (Petrogale coenensis) is a species of rock-wallaby restricted to Cape York Peninsula in northeastern Queensland, Australia. It is a member of a group of seven very closely related rock-wallabies, all found in northeastern Queensland, also including the Mount Claro rock-wallaby (P. sharmani), the Mareeba rock-wallaby (P. mareeba) and Godman''s rock-wallaby (P. godmani)."""', 'Back', 'Green', 'Lanky', 'NA', 'NA', 'NA'),
('Wal11', 'Unadorned Rock-Wallaby', '"""The unadorned rock-wallaby (Petrogale inornata) is a member of a group of closely related rock-wallabies found in northeastern Queensland, Australia. It is paler than most of its relatives and even plainer, hence its common name."""', 'Pose', 'Green', 'Lanky', 'NA', 'NA', 'NA'),
('Wal12', 'Black-footed Rock-wallaby', '"""The black-flanked rock-wallaby is a rather wary animal, with black and grey colouration to blend in with its rocky surroundings, later to lighten in colour during summer. It has short, thick, woolly fur that is particularly dense around the base of the tail, rump and flanks. Its long, brushy tail is quite useful for retaining balance as they hop from one rock to another, and the soles of its feet are highly textured to prevent slipping."""', 'Straight', 'Red', 'Lanky', 'NA', 'NA', 'NA'),
('Wal13', 'Brush-tailed Rock-wallaby', '"""The brush-tailed rock-wallaby or small-eared rock-wallaby (Petrogale penicillata) is a kind of wallaby, one of several rock-wallabies in the genus Petrogale. It inhabits rock piles and cliff lines along the Great Dividing Range from about 100 km north-west of Brisbane to northern Victoria, in vegetation ranging from rainforest to dry sclerophyl forests. Populations have declined seriously in the south and west of its range, but it remains locally common in northern New South Wales and southern Queensland"""', 'Left', 'Red', 'Lanky', 'NA', 'NA', 'NA'),
('Wal14', 'Bridled Nailtail Wallaby', '"""This small wallaby is named for two distinguishing characteristics: a white ""bridle"" line that runs down from the back of the neck around the shoulders, and the horny spur on the end of its tail. Other key physical features include a black stripe running down the dorsum of the neck between the scapulae, large eyes, and white stripes on the cheeks, which are often seen in other species of wallabies as well."""', 'Right', 'Red', 'Lanky', 'NA', 'NA', 'NA'),
('Wal15', 'Northern Nailtail Wallaby', '"""This small shy wallaby grows to a head-body size of 50 – 70 cm with a long 60 – 75 cm tail. Males are slightly larger (6 – 9 kg) than females (4.5 – 7 kg). They are sandy-brown to grey in colouring and have a dark stripe that extends along their spine to the end of the tail. The very end of the tail is equipped with a small nail."""', 'Back', 'Red', 'Lanky', 'Kangaroo', 'Bridled Nailtail Wallaby', 'Red Kangaroo'),
('War01', 'Common Wallaroo', '"""The common wallaroo (Macropus robustus) or wallaroo, also known as euro or hill wallaroo is a species of macropod. The word euro is particularly applied to one subspecies (M. r. erubescens). The eastern wallaroo is mostly nocturnal and solitary, and is one of the more common macropods. It makes a loud hissing noise and some subspecies are sexually dimorphic, like most wallaroos."""', 'Back', 'Gray', 'Stocky', 'Red Kangaroo', 'Kangaroo', 'Antilopine Wallaroo'),
('War02', 'Antilopine Wallaroo', '"""The Antilopine Wallaroo is a large and elegant looking kangaroo with a slender face and doe like eyes. It grows to a head-body length of up to 1.2 m in males and 0.8 m in females, and has a long tail (65 – 90 cm). Females weigh up to 20 kg, while males are on average 37 kg. Males are coloured reddish-tan above and white below, while females are usually pale grey above. - See more at: http://www.australianwildlife.org/wildlife/antilopine-wallaroo.aspx#sthash.P8V9r6Rz.dpuf"""', 'Pose', 'Black', 'Stocky', 'Red Kangaroo', 'Kangaroo', 'Common Wallaroo'),
('War03', 'Black Wallaroo', '"""The black wallaroo (Macropus bernardus), Bernard''s wallaroo[1] or Woodward''s wallaroo,[2] is a species of macropod restricted to a small, mountainous area in Arnhem Land, Northern Territory, between South Alligator River and Nabarlek. It classified as near threatened, mostly due to its limited distribution. A large proportion of the range is protected by Kakadu National Park. The black wallaroo is by far the smallest of the wallaroos as well as the most distinctive. It is sexually dimorphic, with the male being completely black or dark brown and the female a mid-grey colour. It is little known but is known to be a shy nocturnal grazer which does not gather in groups. It makes great use of the rocky escarpments where it lives to shelter and escape danger"""', 'Straight', 'Black', 'Stocky', 'Red Kangaroo', 'Kangaroo', 'NA');

-- --------------------------------------------------------

--
-- Table structure for table `speciesconfiguration`
--

CREATE TABLE IF NOT EXISTS `speciesconfiguration` (
`configurationId` int(11) NOT NULL,
  `configurationName` varchar(100) NOT NULL,
  `species` varchar(100) NOT NULL,
  `configurationType` varchar(25) NOT NULL,
  `configurationStatus` varchar(3) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `speciesconfiguration`
--

INSERT INTO `speciesconfiguration` (`configurationId`, `configurationName`, `species`, `configurationType`, `configurationStatus`) VALUES
(1, 'Kangaroo Filter', 'Kangaroo', 'LooksLike', 'A'),
(2, 'Koala Filter', 'Koala', 'LooksLike', 'A'),
(3, 'Possum Filter', 'Possum', 'LooksLike', 'A'),
(4, 'Kangaroo Filter\r\n', 'Eastern Grey Kangaroo\r\n', 'LooksLike', 'A'),
(5, 'Wallaroo Filter\r\n', 'Antilopine Wallaroo\r\n', 'LooksLike', 'A'),
(6, 'Wallaby Filter', 'Wallaby/Agile Wallaby', 'LooksLike', 'A'),
(7, 'Goat Filter', 'Goat', 'LooksLike', 'A'),
(8, 'Sheep Filter', 'Sheep', 'LookLike', 'A'),
(9, 'Rabbit Filter', 'Rabbit', 'LooksLike', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE IF NOT EXISTS `study` (
  `studyId` varchar(50) NOT NULL,
  `studyTitle` varchar(50) NOT NULL,
  `studyDescription` varchar(200) NOT NULL,
  `startDate` date NOT NULL,
  `finishDate` date NOT NULL,
  `species` varchar(50) NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'Inactive',
  `behavior` varchar(50) NOT NULL DEFAULT 'NotSet',
  `pose` varchar(50) NOT NULL DEFAULT 'NotSet',
  `colour` varchar(50) NOT NULL DEFAULT 'NotSet',
  `scale` varchar(50) NOT NULL DEFAULT 'NotSet',
  `date_activated` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_deactivated` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `study`
--

INSERT INTO `study` (`studyId`, `studyTitle`, `studyDescription`, `startDate`, `finishDate`, `species`, `status`, `behavior`, `pose`, `colour`, `scale`, `date_activated`, `date_deactivated`, `date_created`) VALUES
('DEFAULT00', 'Default Study', 'Default Study for general study of all species', '2015-05-20', '9999-06-16', 'NA', 'Completed', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '2015-06-11 05:59:18', '2015-06-11 06:10:34', '2015-06-10 20:02:00'),
('EABIOS01', 'Eastern Australian Biosecurity Study 1', 'Eastern Australian Biosecurity Study 1 which focus on Regional species in Eastern Australia region-UPDATED', '2015-07-01', '2015-10-15', 'Kangaroo', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '2015-07-01 22:10:52', '2017-07-11 21:59:26', '2015-06-10 20:02:00'),
('EABIOS02', 'Eastern Australian Biosecurity Study 2', 'Eastern Australian Biosecurity Study 2 which focus on Regional species in Eastern Australia region', '2017-07-11', '2016-01-20', 'Koala', 'Active', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '2017-07-11 21:59:26', '2015-07-01 22:10:52', '2015-06-10 20:02:00'),
('EABIOS04', 'Eastern Australian Biosecurity Study 4', 'TBS', '2015-05-27', '2015-05-30', 'Possum', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '2015-06-11 05:59:18', '2015-06-10 20:02:00'),
('EASBIOS03', 'Eastern Australian Biosecurity Study 3', 'Eastern Australian Biosecurity Study 3 for Kangaroo species', '2015-06-10', '2015-08-20', 'Kangaroo', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('EASBIOS04', 'Eastern Australian Biosecurity Study 4', 'Eastern Australian Biosecurity Study 4 for Koala', '2015-07-15', '2015-05-15', 'Koala', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('EASBIOS05', 'Eastern Australia Biosecurity study 5', 'TBS', '2015-09-30', '2015-05-30', 'Possum', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('EASBIOS06', 'Eastern Australian Biosecurity Study 6', 'TBS', '2015-07-15', '2015-09-25', 'Wallaby', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('NTHBIO04', 'Nothern Australia Biosecurity Study 4', 'This is a year long study of Rabbits', '2015-10-26', '2016-05-20', 'Rabbit', 'Completed', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('NTHBIOS01', 'Northern Australian Biosecurity Study 1', 'Northern Australian Biosecurity Study 1 for tropical diseases', '2015-06-13', '2015-09-30', 'Rabbit', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '2015-06-13 08:20:39', '2015-06-28 07:03:56', '2015-06-10 20:02:00'),
('NTHBIOS02', 'Northern Australian Biosecurity Study 2', 'Northern Australian Biosecurity Study 2 for ranforest diseases', '2015-09-30', '2015-11-30', 'Rabbit', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('NTHBIOS3', 'Nothern Australia Biosecurity Study 3', 'TBS', '2015-11-30', '2016-11-30', 'Wallaby', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('SOUBIOS01', 'Southern Kangaroo Disease Study', 'Southern Region study of Kangaroo Diseases', '2015-05-20', '2015-12-31', 'Kangaroo', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('SOUBIOS02', 'Southern Koala Disease Study', 'Southern Region study of Koala Diseases', '2015-06-30', '2015-12-31', 'Koala', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('SOUBIOS03', 'Southern Koala Disease Study', 'TBS', '2015-05-27', '2015-07-29', 'Koala', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('SOUBIOS04', 'Southern Region Kangaroo Study 4', 'TBS', '2015-05-30', '2015-06-30', 'Kangaroo', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('SOUBIOS05', 'Southern Australia Bioscurity Study 5', 'This is year long study', '2015-07-01', '2016-11-30', 'Kangaroo', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '2015-07-01 22:10:52', '2015-07-01 22:10:52', '2015-06-10 20:02:00'),
('SOUBIOS06', 'Southern Australia Biosecurity Study 6', 'TBS', '2015-06-10', '2015-05-30', 'Wallaby', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-10 20:02:00'),
('test', 'test', 'TBS', '9999-00-00', '9999-00-00', 'test', 'Inactive', 'NotSet', 'NotSet', 'NotSet', 'NotSet', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2015-06-27 22:52:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `behavior`
--
ALTER TABLE `behavior`
 ADD PRIMARY KEY (`behaviorId`);

--
-- Indexes for table `colour`
--
ALTER TABLE `colour`
 ADD PRIMARY KEY (`colourId`);

--
-- Indexes for table `imagecaptures`
--
ALTER TABLE `imagecaptures`
 ADD PRIMARY KEY (`imageID`);

--
-- Indexes for table `imageidentity`
--
ALTER TABLE `imageidentity`
 ADD PRIMARY KEY (`recordId`);

--
-- Indexes for table `lookslike`
--
ALTER TABLE `lookslike`
 ADD PRIMARY KEY (`lookslikeId`), ADD UNIQUE KEY `speciesName` (`speciesName`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `uname` (`uname`);

--
-- Indexes for table `poses`
--
ALTER TABLE `poses`
 ADD PRIMARY KEY (`poseId`);

--
-- Indexes for table `scale`
--
ALTER TABLE `scale`
 ADD PRIMARY KEY (`scaleId`);

--
-- Indexes for table `species`
--
ALTER TABLE `species`
 ADD PRIMARY KEY (`speciesId`), ADD UNIQUE KEY `speciesName` (`speciesName`);

--
-- Indexes for table `speciesconfiguration`
--
ALTER TABLE `speciesconfiguration`
 ADD PRIMARY KEY (`configurationId`);

--
-- Indexes for table `study`
--
ALTER TABLE `study`
 ADD PRIMARY KEY (`studyId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `behavior`
--
ALTER TABLE `behavior`
MODIFY `behaviorId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `colour`
--
ALTER TABLE `colour`
MODIFY `colourId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `imageidentity`
--
ALTER TABLE `imageidentity`
MODIFY `recordId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=196;
--
-- AUTO_INCREMENT for table `lookslike`
--
ALTER TABLE `lookslike`
MODIFY `lookslikeId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `poses`
--
ALTER TABLE `poses`
MODIFY `poseId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `scale`
--
ALTER TABLE `scale`
MODIFY `scaleId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `speciesconfiguration`
--
ALTER TABLE `speciesconfiguration`
MODIFY `configurationId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
