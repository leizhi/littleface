CREATE TABLE `Product` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT 'NULL',
  `code` varchar(50) DEFAULT 'NULL',
  `placeOfOrigin` varchar(100) DEFAULT 'NULL',
  `brand` varchar(100) DEFAULT 'NULL',
  `manufacturers` varchar(100) DEFAULT 'NULL',
  `releaseDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `upDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `downDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `shelfLife` int(11) NOT NULL DEFAULT '0',
  `purchasePrice` DECIMAL(65,5) DEFAULT 'NULL',
  `retailPrice` DECIMAL(65,5) DEFAULT 'NULL',
  `memberPrice` DECIMAL(65,5) DEFAULT 'NULL',
  `wholesalePrices` DECIMAL(65,5) DEFAULT 'NULL',
  `specialPrice` DECIMAL(65,5) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `code` (`code`),
  KEY `placeOfOrigin` (`placeOfOrigin`),
  KEY `brand` (`brand`),
  KEY `manufacturers` (`manufacturers`),
  KEY `releaseDate` (`releaseDate`),
  KEY `upDate` (`upDate`),
  KEY `downDate` (`downDate`),
  KEY `purchasePrice` (`purchasePrice`),
  KEY `retailPrice` (`retailPrice`),
  KEY `memberPrice` (`memberPrice`),
  KEY `wholesalePrices` (`wholesalePrices`),
  KEY `specialPrice` (`specialPrice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ProductImages` (
  `id` int(11) NOT NULL DEFAULT '0',
  `productId` int(11) DEFAULT NULL,
  `path` varchar(100) DEFAULT 'NULL',
  `name` varchar(20) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `path` (`path`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Purchase` (
  `id` int(11) NOT NULL DEFAULT '0',
  `batchCode` varchar(50) DEFAULT 'NULL',
  `purchaseDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `batchCode` (`batchCode`),
  KEY `purchaseDate` (`purchaseDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PurchaseDetail` (
  `id` int(11) NOT NULL DEFAULT '0',
  `purchaseId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `purchaseId` (`purchaseId`),
  KEY `goodsId` (`goodsId`),
  KEY `quantity` (`quantity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Cart` (
  `id` int(11) NOT NULL DEFAULT '0',
  `number` varchar(50) DEFAULT 'NULL',
  `byDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `number` (`number`),
  KEY `byDate` (`byDate`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CartItem` (
  `id` int(11) NOT NULL DEFAULT '0',
  `cartId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `amount` double DEFAULT '1',
  `quantity` double DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `cartId` (`cartId`),
  KEY `productId` (`productId`),
  KEY `quantity` (`quantity`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Orders` (
  `id` int(11) NOT NULL DEFAULT '0',
  `number` varchar(50) DEFAULT 'NULL',
  `byDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account` varchar(50) DEFAULT 'NULL',
  `request` enum('CommitOrder','CommitInvalid','IsDelivery','CommitEnd','IsRefund','IsClose'),
  `requestDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `response` enum('IsCart','IsOrder','IsInvalid','IsDelivery','IsEnd','IsRefund','IsClose'),
  `responseDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `number` (`number`),
  KEY `byDate` (`byDate`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OrderTrack` (
  `id` int(11) NOT NULL DEFAULT '0',
  `orderId` int(11) DEFAULT NULL,
  `changeTo` enum('IsCart','IsOrder','IsInvalid','IsDelivery','IsEnd','IsRefund','IsClose'),
  `changeDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  KEY `changeDate` (`changeDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OrderItem` (
  `id` int(11) NOT NULL DEFAULT '0',
  `orderId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `quantity` double DEFAULT '1',
  `amount` double DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  KEY `productId` (`productId`),
  KEY `quantity` (`quantity`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `JobNote` (
  `id` int(11) NOT NULL DEFAULT '0',
  `number` varchar(50) DEFAULT 'NULL',
  `orderId` int(11) DEFAULT NULL,
  `chargeTo` varchar(50) DEFAULT 'NULL',
  `chargeBy` varchar(50) DEFAULT 'NULL',
  `noteDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount` double DEFAULT '1',
  `printCount` int(11) DEFAULT '0',
  `description` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `number` (`number`),
  KEY `orderId` (`orderId`),
  KEY `chargeTo` (`chargeTo`),
  KEY `chargeBy` (`chargeBy`),
  KEY `noteDate` (`noteDate`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `NoteDetail` (
  `id` int(11) NOT NULL DEFAULT '0',
  `productId` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `quantity` double DEFAULT '1',
  `amount` double DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `unit` (`unit`),
  KEY `quantity` (`quantity`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `JobAccounting` (
  `id` int(11) NOT NULL DEFAULT '0',
  `productId` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `quantity` double DEFAULT '1',
  `amount` double DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `unit` (`unit`),
  KEY `quantity` (`quantity`),
  KEY `amount` (`amount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `JobJournal` (
  `id` int(11) NOT NULL DEFAULT '0',
  `accountId` int(11) DEFAULT NULL,
  `jobNoteId` int(11) DEFAULT NULL,
  `typeDC` enum('Credit','Debit') DEFAULT NULL,
  `amount` decimal(65,5) DEFAULT NULL,
  `jobAccountingId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `jobNoteId` (`jobNoteId`),
  CONSTRAINT `JobJournal_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `xpcShared`.`Account` (`id`),
  CONSTRAINT `JobJournal_ibfk_2` FOREIGN KEY (`jobAccountingID`) REFERENCES `JobAccounting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Warehouse` (
  `id` int(11) NOT NULL DEFAULT '0',
  `goodsId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goodsId` (`goodsId`),
  KEY `quantity` (`quantity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;