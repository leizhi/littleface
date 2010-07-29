CREATE TABLE `CodeType` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT 'NULL',
  `category` enum('Linear','Tree') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`,`category`),
  KEY `name` (`name`),
  KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8