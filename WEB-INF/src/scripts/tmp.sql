CREATE TABLE `Goods` (
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

CREATE TABLE `GoodsImages` (
  `id` int(11) NOT NULL DEFAULT '0',
  `goodsId` int(11) DEFAULT NULL,
  `path` varchar(100) DEFAULT 'NULL',
  `name` varchar(20) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `goodsId` (`goodsId`),
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
  `batchCode` varchar(50) DEFAULT 'NULL',
  `saleDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL', 
  `status` enum('Cart','Order','Payment','Delivery','Close') DEFAULT 'New',
  PRIMARY KEY (`id`),
  KEY `batchCode` (`batchCode`),
  KEY `saleDate` (`saleDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `SaleJob` (
  `id` int(11) NOT NULL DEFAULT '0',
  `batchCode` varchar(50) DEFAULT 'NULL',
  `saleDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL', 
  `status` enum('Cart','Order','Payment','Delivery','Close') DEFAULT 'New',
  PRIMARY KEY (`id`),
  KEY `batchCode` (`batchCode`),
  KEY `saleDate` (`saleDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `SaleOrder` (
  `id` int(11) NOT NULL DEFAULT '0',
  `batchCode` varchar(50) DEFAULT 'NULL',
  `saleDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `batchCode` (`batchCode`),
  KEY `saleDate` (`saleDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Sale` (
  `id` int(11) NOT NULL DEFAULT '0',
  `batchCode` varchar(50) DEFAULT 'NULL',
  `saleDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(50) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  KEY `batchCode` (`batchCode`),
  KEY `saleDate` (`saleDate`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `SaleDetail` (
  `id` int(11) NOT NULL DEFAULT '0',
  `saleId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `saleId` (`saleId`),
  KEY `goodsId` (`goodsId`),
  KEY `quantity` (`quantity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Warehouse` (
  `id` int(11) NOT NULL DEFAULT '0',
  `goodsId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goodsId` (`goodsId`),
  KEY `quantity` (`quantity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;