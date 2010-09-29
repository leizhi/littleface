CREATE TABLE `CodeType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `category` enum('Linear','Tree') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`category`),
  KEY `name` (`name`),
  KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `LinearCode` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`typeId`),
  KEY `typeId` (`typeId`),
  KEY `name` (`name`),
  CONSTRAINT `LinearCode_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `CodeType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `FileInfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `typeid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT 'NULL',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filepath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`typeid`),
  KEY `typeid` (`typeid`),
  KEY `name` (`name`),
  KEY `dateTime` (`datetime`),
  KEY `filepath` (`filepath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `AccessLog` (
  `id` int(11) NOT NULL DEFAULT '0',
  `ip` varchar(100) DEFAULT 'NULL',
  `startdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `ip` (`ip`),
  KEY `startdate` (`startdate`),
  KEY `enddate` (`enddate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 

CREATE TABLE `Account` (
  `id` int(11) NOT NULL DEFAULT '0',
  `account` varchar(50) DEFAULT NULL,
  `alias` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
   `active` char(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `AccountInfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `accountId` int(11) DEFAULT NULL,
  `sexId` int(11) DEFAULT NULL,
  `height` DECIMAL(5,2) DEFAULT NULL,
  `heightUnitId` int(11) DEFAULT NULL,
  `weight` DECIMAL(5,2) DEFAULT NULL,
  `weightUnitId` int(11) DEFAULT NULL,
  `birthday` DATETIME DEFAULT NULL,
  `careerId` int(11) DEFAULT NULL,
  `educationId` int(11) DEFAULT NULL,
  `marriedId` int(11) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `secret` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountId` (`accountId`),
  KEY `sexId` (`sexId`),
  KEY `height` (`height`),
  KEY `heightUnitId` (`heightUnitId`),
  KEY `weight` (`weight`),
  KEY `weightUnitId` (`weightUnitId`),
  KEY `birthday` (`birthday`),
  KEY `careerId` (`careerId`),
  KEY `educationId` (`educationId`),
  KEY `marriedId` (`marriedId`),
  KEY `qq` (`qq`),
  CONSTRAINT `AccountInfo_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_2` FOREIGN KEY (`sexId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_3` FOREIGN KEY (`heightUnitId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_4` FOREIGN KEY (`weightUnitId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_5` FOREIGN KEY (`careerId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_6` FOREIGN KEY (`educationId`) REFERENCES `mycozShared`.`LinearCode` (`id`),
  CONSTRAINT `AccountInfo_ibfk_7` FOREIGN KEY (`marriedId`) REFERENCES `mycozShared`.`LinearCode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8