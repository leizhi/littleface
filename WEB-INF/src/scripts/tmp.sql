
CREATE TABLE `Harlot` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nickName` varchar(50) DEFAULT 'NULL',
  `quantity` int(11) DEFAULT NULL,
  `phone` varchar(50) DEFAULT 'NULL',
  `mobile` varchar(50) DEFAULT 'NULL',
  `address` varchar(250) DEFAULT 'NULL',
  `navigation` varchar(250) DEFAULT 'NULL',
  `comments` varchar(250) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `nickName` (`nickName`),
  KEY `quantity` (`quantity`),
  KEY `phone` (`phone`),
  KEY `mobile` (`mobile`),
  KEY `address` (`address`),
  KEY `navigation` (`navigation`),
  KEY `comments` (`comments`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `HarlotImages` (
  `id` int(11) NOT NULL DEFAULT '0',
  `harlotId` int(11) DEFAULT NULL,
  `path` varchar(100) DEFAULT 'NULL',
  `name` varchar(20) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `harlotId` (`harlotId`),
  CONSTRAINT `HarlotImages_ibfk_1` FOREIGN KEY (`harlotId`) REFERENCES `Harlot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;