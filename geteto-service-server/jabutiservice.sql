--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `project` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL,
  `testsuite` varchar(500) default NULL,
  `selectedclasses` text,
  `ignoredclasses` text,
  `state` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
