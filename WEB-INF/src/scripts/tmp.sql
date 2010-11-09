CREATE TABLE `AccountType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `categoryId` int(11)  DEFAULT NULL,
  `typeCode` varchar(100) DEFAULT NULL,
  `typeName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  KEY `typeCode` (`typeCode`),
  KEY `typeName` (`typeName`),
  KEY `description` (`description`),
  CONSTRAINT `AccountType_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `AccountCategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `AccountCategory` (
  `id` int(11) NOT NULL DEFAULT '0',
  `elementId` int(11) NOT NULL DEFAULT '0',
  `categoryCode` varchar(100) DEFAULT NULL,
  `categoryName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `elementId` (`elementId`),
  KEY `categoryCode` (`categoryCode`),
  KEY `categoryName` (`categoryName`),
  KEY `description` (`description`),
  CONSTRAINT `AccountCategory_ibfk_1` FOREIGN KEY (`elementId`) REFERENCES `AccountElement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8