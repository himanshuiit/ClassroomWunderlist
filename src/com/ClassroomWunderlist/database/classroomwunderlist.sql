-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: classroomdbms
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentuser` (
  `fullName` varchar(500) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `college` varchar(100) NOT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentuser`
--

LOCK TABLES `currentuser` WRITE;
/*!40000 ALTER TABLE `currentuser` DISABLE KEYS */;
INSERT INTO `currentuser` VALUES ('Shubham Madheysia','submiitr07@gmail.com','9756526651','male','IIT Roorkee');
/*!40000 ALTER TABLE `currentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speakouts`
--

DROP TABLE IF EXISTS `speakouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speakouts` (
  `timestamp` varchar(70) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `message` varchar(900) NOT NULL,
  PRIMARY KEY (`timestamp`,`emailId`,`message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speakouts`
--

LOCK TABLES `speakouts` WRITE;
/*!40000 ALTER TABLE `speakouts` DISABLE KEYS */;
INSERT INTO `speakouts` VALUES ('2017.07.20.03.19.35','subm07@gmail.com','Hi, this is first message in this group.'),('2017.07.21.03.19.35','subm07@gmail.com','It feels good to develop Classroom products such as ClassroomDBMS. Have fun and happy Coding !!'),('2017.07.22.03.19.35','new@new.new','Hi, even I could see it useful. Incorrect email id account holder. :D :D'),('2017.07.23.13.19.35','subm07@gmail.com','finally testing'),('2017.07.24.03.19.35','hkbansal@gmail.com','Hello everyone. I am pre final year CSE student. '),('2017.07.26.03.15.43','subm07@gmail.com','testing with timestamp\n'),('2017.07.26.03.19.35','neha@neha.neha','This is femal first post.'),('2017.07.26.03.19.35','subm07@gmail.com','testing with timestamp\n'),('2017.07.26.10.51.25','subm07@gmail.com','ok'),('2017.07.26.12.19.35','abc@def.ghi','Just increasing the testing code. Junk lines'),('2017.07.26.12.33.36','subm07@gmail.com','this is long testing. So lonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnng'),('2017.07.26.12.45.55','subm07@gmail.com','This is again fresh testing'),('2017.07.26.12.46.02','subm07@gmail.com','This is again fresh testing'),('2017.07.26.12.46.04','subm07@gmail.com','This is again fresh testing'),('2017.07.26.12.46.05','subm07@gmail.com','This is again fresh testing'),('2017.07.26.12.46.06','subm07@gmail.com','This is again fresh testing'),('2017.07.26.13.00.59','subm07@gmail.com','TimePass Testing. Happy Coding'),('2017.07.26.13.02.29','subm07@gmail.com','Scroll testing'),('2017.07.26.13.09.07','rkrana@gmail.com','Hello World!! \n\nI am Rahul Rana from NSIT. Full Stack Developer '),('2017.07.26.13.11.48','rkrana@gmail.com','I am also doing Testing for this app'),('2017.07.26.13.45.10','rkrana@gmail.com','Final Testing here'),('2017.07.26.13.45.47','submiitr07@gmail.com','Hello World'),('2017.07.26.13.48.44','submiitr07@gmail.com','I am JAVAFX and React-redus developer'),('2017.07.26.13.53.22','subm07@gmail.com','Testing for some small features'),('2017.07.26.15.14.17','submiitr07@gmail.com','Attending Kamals class'),('2017.07.26.16.18.11','submiitr07@gmail.com','new announcement'),('2017.07.26.18.40.32','submiitr07@gmail.com','Continuous Testing'),('2017.07.26.18.42.59','submiitr07@gmail.com','Testing whether UI is good'),('2017.07.26.18.43.16','submiitr07@gmail.com','Again Same. target not achieved'),('2017.07.26.18.47.39','submiitr07@gmail.com','Scrollbar Changes made. Testing'),('2017.07.26.19.02.28','submiitr07@gmail.com','Scrollbar testing'),('2017.07.26.19.02.47','submiitr07@gmail.com','Continous changes check. Bear it.'),('2017.07.26.19.02.50','submiitr07@gmail.com','Continous changes check. Bear it.'),('2017.07.26.19.02.53','submiitr07@gmail.com','Continous changes check. Bear it.'),('2017.07.26.19.05.24','submiitr07@gmail.com','what now'),('2017.07.26.19.55.28','submiitr07@gmail.com','Seeing what happens when I add here just after keyword search made'),('2017.07.26.21.03.00','hkbansal@gmail.com','Hey, I am logged in. And this is my part of testing. Happy Coding folks');
/*!40000 ALTER TABLE `speakouts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submissions` (
  `timestamp` varchar(100) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `answer` varchar(8000) NOT NULL,
  `tutorialsName` varchar(100) NOT NULL,
  PRIMARY KEY (`timestamp`,`tutorialsName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES ('2017.07.27.01.27.22','subm07@gmail.com','fsdfsfsdfsfsdfsdfsdfsfsdfsfdfsfsffffffffffffffffffffffffffffffffffffffssssssssssssssssssssssssssssssssssss','Tutorial 2'),('2017.07.28.19.55.28','subm07@gmail.com','Classroom Products are submission to DBMS tuts','Tutorial 1');
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorials`
--

DROP TABLE IF EXISTS `tutorials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorials` (
  `timestamp` varchar(100) NOT NULL,
  `tutorialsName` varchar(100) NOT NULL,
  `deadline` varchar(100) DEFAULT NULL,
  `question` varchar(8000) NOT NULL,
  PRIMARY KEY (`timestamp`,`tutorialsName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorials`
--

LOCK TABLES `tutorials` WRITE;
/*!40000 ALTER TABLE `tutorials` DISABLE KEYS */;
INSERT INTO `tutorials` VALUES ('2017.07.26.19.55.28','Tutorial 1','2017.07.30.19.55.28','Let us imagine a database for storing ODI (One Day International) Cricket Matches played between National Men Cricket Teams. The information you need to store in the database are as follows: Each ODI match will have match id, match date, match location, umpire name and two national teams (e.g. India, England etc.) who played the match. match winner decides which team won the match; if empty, the match was tied/drawn. Each national team has 15 players (11 active and 4 substitutes) at any given time, one captain (chosen from the 11 active players) and one coach. So, for each team you need to store nation, player names, player statuses (active or substitute), player dobs, player skills (batsman, bowler, and wicketkeeper; can be multiple). player start date (when a player first starts playing ODIs for the national team), player end date (when a player retires from or last played ODIs for the national team in his life), coach name, coach start date, coach end date, captain name, captain start date, captain end date. Names of persons can be considered as unique. Now, for each ODI match played, you also need to store batting score (runs made) for each player who has batted, number of balls played, out type (e.g. not-out, caught, bowled, LBW etc.), out by (if some player from opponent side is involved in the out) for each of these batsmen. Similarly, for all the bowlers in a match, you need to store balls bowled, runs given, and wickets taken. [a] Draw an ER diagram for this database with cardinality and participation constraints shown in an unambiguous manner. All the necessary attributes are given in typewriter font in the description. Clearly list down all the extra assumptions you have made about the design while drawing the ER diagram. [b] Is there any information given in the question which you were not able to represent using the ER diagram? If yes, what are those? If not, give an example of some condition relevant to the given scenario which cannot be represented by an ER diagram. '),('2017.08.06.19.55.28','Tutorial 2','2017.08.15.19.55.28','Suppose you are given the following requirements for a simple database for the National Hockey League (NHL): · the NHL has many teams, · each team has a name, a city, a coach, a captain, and a set of players, · each player belongs to only one team, · each player has a name, a position (such as left wing or goalie), a skill level, and a set of injury records, · a team captain is also a player, · a game is played between two teams (referred to as host_team and guest_team) and has a date (such as May 11th, 1999) and a score (such as 4 to 2). Construct a clean and concise ER diagram for the NHL database using the Chen notation as in your textbook. List your assumptions and clearly indicate the cardinality mappings as well as any role indicators in your ER diagram ');
/*!40000 ALTER TABLE `tutorials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetail`
--

DROP TABLE IF EXISTS `userdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetail` (
  `fullName` varchar(500) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `college` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetail`
--

LOCK TABLES `userdetail` WRITE;
/*!40000 ALTER TABLE `userdetail` DISABLE KEYS */;
INSERT INTO `userdetail` VALUES ('anything','any@any.any','any','1234567890','female','anyIIT'),('final testing','fianlly@testing.done','iitr','9756526651','Male','IIT Roorkee'),('final','final@final.final','final','9756526651','Male','IIT'),('Harsh bansal','hkbansal@gmail.com','iitr','7897878978','male','IITR'),('nehha ','neha@neha.neha','neha','1511451115','female','DU'),('newTest','new@new.com','new','9756526651','Female','kuch bhi'),('ravi sharma','ravi@gmail.com','ravi','9876543210','male','IIIT'),('Rana','rkrana@gmail.com','iitr','2321213212','male','NSIT'),('shubham','subm07@gmail.com','iitr','9756526651','male','IIT Roorkee'),('Shubham Madheysia','subm@gmail.com','iitr','9756526651','male','IIT Roorkee'),('Shubham Madheysia','submiitr07@gmail.com','iitr','9756526651','male','IIT Roorkee'),('vikrant','vkthakur@yahoo.co','iitr','1231545646','male','IITR');
/*!40000 ALTER TABLE `userdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-27 11:14:36
