-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2017 at 04:12 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coursegame`
--

-- --------------------------------------------------------

--
-- Table structure for table `batch`
--

CREATE TABLE `batch` (
  `batchId` varchar(15) NOT NULL,
  `batchName` varchar(20) NOT NULL,
  `facultyId` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `facultyId` varchar(15) NOT NULL,
  `userId` varchar(15) NOT NULL,
  `degree` varchar(50) NOT NULL,
  `isAppeoved` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gameanimation`
--

CREATE TABLE `gameanimation` (
  `gameId` varchar(15) NOT NULL,
  `fileName` text NOT NULL,
  `gameName` varchar(20) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `batchId` varchar(15) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `quizId` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  `batchId` varchar(15) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `createdBy` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `quizcontent`
--

CREATE TABLE `quizcontent` (
  `quizContentId` varchar(15) NOT NULL,
  `quizId` varchar(15) NOT NULL,
  `questionContent` text NOT NULL,
  `option1` text NOT NULL,
  `option2` text NOT NULL,
  `option3` text NOT NULL,
  `option4` text NOT NULL,
  `answer` varchar(8) NOT NULL,
  `mark` int(11) NOT NULL DEFAULT '1',
  `difficulty` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentId` varchar(15) NOT NULL,
  `batchId` varchar(15) NOT NULL,
  `userId` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentgame`
--

CREATE TABLE `studentgame` (
  `studentGameId` varchar(15) NOT NULL,
  `gameId` varchar(15) NOT NULL,
  `studentId` varchar(15) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentquiz`
--

CREATE TABLE `studentquiz` (
  `studentQuizId` varchar(15) NOT NULL,
  `studentId` varchar(15) NOT NULL,
  `quizId` varchar(15) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentquizcontent`
--

CREATE TABLE `studentquizcontent` (
  `studentQuizContentId` varchar(15) NOT NULL,
  `studentId` varchar(15) NOT NULL,
  `quizContentId` varchar(15) NOT NULL,
  `selectedOption` varchar(10) NOT NULL,
  `isCorrect` varchar(1) NOT NULL DEFAULT 'y'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ta`
--

CREATE TABLE `ta` (
  `taId` varchar(15) NOT NULL,
  `userId` varchar(15) NOT NULL,
  `batchId` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` varchar(15) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `role` varchar(10) NOT NULL,
  `isAvailable` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `batch`
--
ALTER TABLE `batch`
  ADD PRIMARY KEY (`batchId`),
  ADD KEY `batch_ibfk_1` (`facultyId`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`facultyId`),
  ADD KEY `faculty_ibfk_1` (`userId`);

--
-- Indexes for table `gameanimation`
--
ALTER TABLE `gameanimation`
  ADD PRIMARY KEY (`gameId`),
  ADD KEY `gameanimation_ibfk_1` (`batchId`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`quizId`),
  ADD KEY `batchId` (`batchId`),
  ADD KEY `createdBy` (`createdBy`);

--
-- Indexes for table `quizcontent`
--
ALTER TABLE `quizcontent`
  ADD PRIMARY KEY (`quizContentId`),
  ADD KEY `quizId` (`quizId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `batchId` (`batchId`),
  ADD KEY `student_ibfk_2` (`userId`);

--
-- Indexes for table `studentgame`
--
ALTER TABLE `studentgame`
  ADD PRIMARY KEY (`studentGameId`),
  ADD KEY `studentgame_ibfk_1` (`gameId`),
  ADD KEY `studentgame_ibfk_2` (`studentId`);

--
-- Indexes for table `studentquiz`
--
ALTER TABLE `studentquiz`
  ADD PRIMARY KEY (`studentQuizId`),
  ADD KEY `studentId` (`studentId`),
  ADD KEY `quizId` (`quizId`);

--
-- Indexes for table `studentquizcontent`
--
ALTER TABLE `studentquizcontent`
  ADD PRIMARY KEY (`studentQuizContentId`),
  ADD KEY `quizContentId` (`quizContentId`),
  ADD KEY `studentId` (`studentId`);

--
-- Indexes for table `ta`
--
ALTER TABLE `ta`
  ADD PRIMARY KEY (`taId`),
  ADD KEY `batchId` (`batchId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `emailId` (`emailId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `batch`
--
ALTER TABLE `batch`
  ADD CONSTRAINT `batch_ibfk_1` FOREIGN KEY (`facultyId`) REFERENCES `faculty` (`facultyId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `gameanimation`
--
ALTER TABLE `gameanimation`
  ADD CONSTRAINT `gameanimation_ibfk_1` FOREIGN KEY (`batchId`) REFERENCES `batch` (`batchId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`batchId`) REFERENCES `batch` (`batchId`),
  ADD CONSTRAINT `quiz_ibfk_2` FOREIGN KEY (`createdBy`) REFERENCES `user` (`userId`);

--
-- Constraints for table `quizcontent`
--
ALTER TABLE `quizcontent`
  ADD CONSTRAINT `quizcontent_ibfk_1` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`quizId`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`batchId`) REFERENCES `batch` (`batchId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studentgame`
--
ALTER TABLE `studentgame`
  ADD CONSTRAINT `studentgame_ibfk_1` FOREIGN KEY (`gameId`) REFERENCES `gameanimation` (`gameId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `studentgame_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studentquiz`
--
ALTER TABLE `studentquiz`
  ADD CONSTRAINT `studentquiz_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`),
  ADD CONSTRAINT `studentquiz_ibfk_2` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`quizId`);

--
-- Constraints for table `studentquizcontent`
--
ALTER TABLE `studentquizcontent`
  ADD CONSTRAINT `studentquizcontent_ibfk_1` FOREIGN KEY (`quizContentId`) REFERENCES `quizcontent` (`quizContentId`),
  ADD CONSTRAINT `studentquizcontent_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`);

--
-- Constraints for table `ta`
--
ALTER TABLE `ta`
  ADD CONSTRAINT `ta_ibfk_1` FOREIGN KEY (`batchId`) REFERENCES `batch` (`batchId`),
  ADD CONSTRAINT `ta_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
