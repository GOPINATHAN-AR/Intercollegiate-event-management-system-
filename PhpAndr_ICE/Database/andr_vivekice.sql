-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 20, 2020 at 10:42 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `andr_vivekice`
--
CREATE DATABASE IF NOT EXISTS `andr_vivekice` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `andr_vivekice`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `userid` varchar(100) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`userid`, `pwd`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE IF NOT EXISTS `applications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) NOT NULL,
  `dept` varchar(100) NOT NULL,
  `studname` varchar(200) NOT NULL,
  `event` varchar(100) NOT NULL,
  `selectflag` varchar(20) NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `applications`
--

INSERT INTO `applications` (`id`, `userid`, `dept`, `studname`, `event`, `selectflag`) VALUES
(1, 'ac@gmail.com', 'BSC CS', 'Anand', 'Debugging', 'mains'),
(2, 'ac@gmail.com', 'BSC CS', 'Ram Kumar', 'Debugging', 'pending'),
(3, 'ac@gmail.com', 'BSC CS', 'Samuel', 'Debugging', 'pending'),
(4, 'ac@gmail.com', 'BSC CS', 'Umar', 'Paper Presentation', 'prelims'),
(5, 'ac@gmail.com', 'BSC CS', 'Rahul', 'Quiz', 'mains'),
(6, 'ac@gmail.com', 'BSC CS', 'Khan', 'Quiz', 'pending'),
(7, 'ac@gmail.com', 'BCA', 'Kumar', 'Debugging', 'prelims'),
(8, 'ac@gmail.com', 'BCA', 'Selva', 'Debugging', 'pending'),
(9, 'ac@gmail.com', 'BCA', 'Hari', 'Debugging', 'pending'),
(10, 'tc@gmail.com', 'BCA', 'Shankar', 'Debugging', 'pending'),
(11, 'tc@gmail.com', 'BCA', 'Ganesh', 'Debugging', 'pending'),
(12, 'tc@gmail.com', 'BCA', 'Guru', 'Debugging', 'pending'),
(13, 'tc@gmail.com', 'BCA', 'Kamal', 'Debugging', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `feedbacks`
--

CREATE TABLE IF NOT EXISTS `feedbacks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dt` date NOT NULL,
  `userid` varchar(200) NOT NULL,
  `feedback` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `feedbacks`
--

INSERT INTO `feedbacks` (`id`, `dt`, `userid`, `feedback`) VALUES
(1, '2020-03-11', 'ac@gmail.com', 'Nice application...!');

-- --------------------------------------------------------

--
-- Table structure for table `newevent`
--

CREATE TABLE IF NOT EXISTS `newevent` (
  `eventname` varchar(200) NOT NULL,
  PRIMARY KEY (`eventname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newevent`
--

INSERT INTO `newevent` (`eventname`) VALUES
('Debugging'),
('Paper Presentation'),
('Quiz');

-- --------------------------------------------------------

--
-- Table structure for table `newuser`
--

CREATE TABLE IF NOT EXISTS `newuser` (
  `uname` varchar(200) NOT NULL,
  `city` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `userid` varchar(200) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newuser`
--

INSERT INTO `newuser` (`uname`, `city`, `mobile`, `userid`, `pwd`) VALUES
('The American College', 'Madurai', '9859589090', 'ac@gmail.com', 'ac'),
('Thiyagarajar College', 'Madurai', '9849900989', 'tc@gmail.com', 'tc');

-- --------------------------------------------------------

--
-- Table structure for table `rules`
--

CREATE TABLE IF NOT EXISTS `rules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruletxt` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `rules`
--

INSERT INTO `rules` (`id`, `ruletxt`) VALUES
(1, 'Students should appear in the morning itself.\r\nStudents mush have their college Ids.\r\nEnrollment rules are decided by College.');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
