#!/bin/sh

mysql -uroot -pJZworkb0x << FLAG
set FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS mycozShared;
CREATE DATABASE IF NOT EXISTS mycozBranch;

GRANT ALL PRIVILEGES ON mycozBranch.* TO 'mycoz'@'localhost' IDENTIFIED BY 'xmlpj0#' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON mycozShared.* TO 'mycoz'@'localhost' IDENTIFIED BY 'xmlpj0#' WITH GRANT OPTION;

#GRANT ALL PRIVILEGES ON mycozBranch.* TO 'mycoz'@'%' IDENTIFIED BY 'xmlpj0#' WITH GRANT OPTION;
#GRANT ALL PRIVILEGES ON mycozShared.* TO 'mycoz'@'%' IDENTIFIED BY 'xmlpj0#' WITH GRANT OPTION;

#DROP USER 'mycoz'@'localhost';
#DROP USER 'mycoz'@'%';

FLUSH PRIVILEGES;
set FOREIGN_KEY_CHECKS=1;
FLAG
