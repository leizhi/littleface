CREATE TABLE `UserImage` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `filepath` (`filepath`),
  CONSTRAINT `UserImage_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;