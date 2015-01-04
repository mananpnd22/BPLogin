-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 18, 2014 at 09:24 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `bp_data`
--

CREATE TABLE IF NOT EXISTS `bp_data` (
  `bp_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `bp_high` int(11) NOT NULL,
  `bp_low` int(11) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`bp_data_id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `bp_data`
--

INSERT INTO `bp_data` (`bp_data_id`, `userid`, `bp_high`, `bp_low`, `time`) VALUES
(1, 1, 100, 70, '2014-09-01 00:00:00'),
(2, 2, 80, 50, '2014-09-02 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `csvdata`
--

CREATE TABLE IF NOT EXISTS `csvdata` (
  `maxbp` int(6) NOT NULL,
  `minbp` int(6) NOT NULL,
  `time` datetime(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `csvdata`
--

INSERT INTO `csvdata` (`maxbp`, `minbp`, `time`) VALUES
(130, 78, '0000-00-00 00:00:00.00000'),
(150, 100, '0000-00-00 00:00:00.00000');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userid`, `username`, `password`) VALUES
(1, 'user', 'user'),
(2, 'admin', 'admin'),
(3, '', ''),
(4, '', ''),
(5, 'abc', ''),
(6, 'a', ''),
(7, '123', ''),
(8, '123', ''),
(9, 'abc', ''),
(10, 'sagar', ''),
(11, 'sagar', ''),
(12, 'sagar', ''),
(13, 'sagar', ''),
(14, 'sagar', ''),
(15, '1', ''),
(16, '121', ''),
(17, '23432', ''),
(18, '892588', ''),
(19, '12', ''),
(20, '2', ''),
(21, 'w', ''),
(22, 'dharmesh', ''),
(23, 'yuvraj', 'yuvi');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bp_data`
--
ALTER TABLE `bp_data`
  ADD CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `login` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
