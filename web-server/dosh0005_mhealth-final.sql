-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 26, 2018 at 05:34 AM
-- Server version: 5.6.28
-- PHP Version: 5.6.25

SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dosh0005_mhealth`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `iddoctor` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `access_token` text,
  `sex` enum('male','female') NOT NULL DEFAULT 'male',
  `age` int(11) NOT NULL DEFAULT '20',
  `address` varchar(200) NOT NULL,
  `cell` varchar(20) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `oncreate` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_notes`
--

CREATE TABLE `doctor_notes` (
  `idnote` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `iddoctor` int(11) NOT NULL,
  `idsession` int(11) NOT NULL,
  `ondatetime` timestamp NOT NULL,
  `message` text NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_patient_journal`
--

CREATE TABLE `doctor_patient_journal` (
  `idjournal` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `iddoctor` int(11) DEFAULT NULL,
  `idpqa` int(11) DEFAULT NULL,
  `msg` text NOT NULL,
  `createdt` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `iduser` int(11) NOT NULL,
  `iddoctor` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `sex` enum('male','female') NOT NULL DEFAULT 'male',
  `age` int(20) NOT NULL DEFAULT '20',
  `address` varchar(200) NOT NULL DEFAULT 'Not Set',
  `cell` varchar(15) NOT NULL DEFAULT 'Not Set',
  `image` varchar(30) NOT NULL,
  `p_desc` text NOT NULL,
  `p_medical_history` text NOT NULL,
  `authkey` varchar(1000) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `oncreate` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_chat`
--

CREATE TABLE `patient_chat` (
  `idchat` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idsession` varchar(100) NOT NULL,
  `query` text,
  `lang` varchar(5) NOT NULL DEFAULT 'en',
  `response` text NOT NULL,
  `metadata` text NOT NULL,
  `respo_object` text NOT NULL,
  `chat_route` enum('in','out') NOT NULL DEFAULT 'in',
  `ondatecreate` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_notes`
--

CREATE TABLE `patient_notes` (
  `idnote` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idsession` int(11) NOT NULL,
  `ondatetime` timestamp NOT NULL,
  `message` text NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_question`
--

CREATE TABLE `patient_question` (
  `idpq` int(11) NOT NULL,
  `iduser` varchar(11) NOT NULL,
  `qno` int(11) NOT NULL,
  `isQ` tinyint(1) NOT NULL DEFAULT '0',
  `isF` tinyint(1) NOT NULL DEFAULT '0',
  `isJ` tinyint(1) NOT NULL DEFAULT '0',
  `qdate` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_question_answer`
--

CREATE TABLE `patient_question_answer` (
  `idpqa` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idquestion` int(11) NOT NULL DEFAULT '0',
  `idpq` int(11) NOT NULL DEFAULT '0',
  `question` text NOT NULL,
  `answer` varchar(20) NOT NULL,
  `createdt` timestamp NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `question_all`
--

CREATE TABLE `question_all` (
  `idquestion` int(11) NOT NULL,
  `question` text NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1'
) ;

--
-- Dumping data for table `question_all`
--

INSERT INTO `question_all` (`idquestion`, `question`, `enable`) VALUES
(1, 'Little interest or pleasure in doing things', 1),
(2, 'Feeling down, depressed or hopeless', 1),
(3, 'Trouble falling asleep, staying asleep, or sleeping too much', 1),
(4, 'Feeling tired or having little energy', 1),
(5, 'Poor appetite or overeating', 1),
(6, 'Feeling bad about yourself - or that you’re a failure or have let yourself or your family down', 1),
(7, 'Trouble concentrating on things, such as reading the newspaper or watching television', 1),
(8, 'Moving or speaking so slowly that other people could have noticed. Or, the opposite - being so fidgety or restless that you have been moving around a lot more than usual', 1),
(9, 'Thoughts that you would be better off dead or of hurting yourself in some way', 1);

-- --------------------------------------------------------

--
-- Table structure for table `userdata`
--

CREATE TABLE `userdata` (
  `iddata` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idnotify` int(11) DEFAULT NULL,
  `notify_interval` varchar(20) NOT NULL,
  `email` tinyint(1) NOT NULL DEFAULT '1',
  `sms` tinyint(1) NOT NULL DEFAULT '0',
  `oncreatedatetime` timestamp NOT NULL
) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`iddoctor`),
  ADD KEY `iddoctor` (`iddoctor`);

--
-- Indexes for table `doctor_notes`
--
ALTER TABLE `doctor_notes`
  ADD PRIMARY KEY (`idnote`),
  ADD KEY `iduser` (`iduser`,`idsession`),
  ADD KEY `iddoctor` (`iddoctor`);

--
-- Indexes for table `doctor_patient_journal`
--
ALTER TABLE `doctor_patient_journal`
  ADD PRIMARY KEY (`idjournal`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `iddoctore` (`iddoctor`);

--
-- Indexes for table `patient_chat`
--
ALTER TABLE `patient_chat`
  ADD PRIMARY KEY (`idchat`),
  ADD KEY `iduser` (`iduser`);

--
-- Indexes for table `patient_notes`
--
ALTER TABLE `patient_notes`
  ADD PRIMARY KEY (`idnote`),
  ADD KEY `iduser` (`iduser`,`idsession`);

--
-- Indexes for table `patient_question`
--
ALTER TABLE `patient_question`
  ADD PRIMARY KEY (`idpq`);

--
-- Indexes for table `patient_question_answer`
--
ALTER TABLE `patient_question_answer`
  ADD PRIMARY KEY (`idpqa`);

--
-- Indexes for table `question_all`
--
ALTER TABLE `question_all`
  ADD PRIMARY KEY (`idquestion`);

--
-- Indexes for table `userdata`
--
ALTER TABLE `userdata`
  ADD PRIMARY KEY (`iddata`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idnotify` (`idnotify`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `iddoctor` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `doctor_notes`
--
ALTER TABLE `doctor_notes`
  MODIFY `idnote` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `doctor_patient_journal`
--
ALTER TABLE `doctor_patient_journal`
  MODIFY `idjournal` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient_chat`
--
ALTER TABLE `patient_chat`
  MODIFY `idchat` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient_notes`
--
ALTER TABLE `patient_notes`
  MODIFY `idnote` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient_question`
--
ALTER TABLE `patient_question`
  MODIFY `idpq` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient_question_answer`
--
ALTER TABLE `patient_question_answer`
  MODIFY `idpqa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `question_all`
--
ALTER TABLE `question_all`
  MODIFY `idquestion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `userdata`
--
ALTER TABLE `userdata`
  MODIFY `iddata` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `doctor_notes`
--
ALTER TABLE `doctor_notes`
  ADD CONSTRAINT `doctor_notes_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `patient` (`iduser`) ON DELETE CASCADE;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`iddoctor`) REFERENCES `doctor` (`iddoctor`) ON DELETE CASCADE;

--
-- Constraints for table `patient_chat`
--
ALTER TABLE `patient_chat`
  ADD CONSTRAINT `patient_chat_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `patient` (`iduser`) ON DELETE CASCADE;

--
-- Constraints for table `patient_notes`
--
ALTER TABLE `patient_notes`
  ADD CONSTRAINT `patient_notes_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `patient` (`iduser`) ON DELETE CASCADE;

--
-- Constraints for table `userdata`
--
ALTER TABLE `userdata`
  ADD CONSTRAINT `userdata_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `patient` (`iduser`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
