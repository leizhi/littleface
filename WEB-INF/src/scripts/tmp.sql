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
