#!/bin/sh

mysql -uroot -pJZworkb0x << FLAG
set FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS mycozShared;
CREATE DATABASE IF NOT EXISTS mycozBranch;

use mysql;

CREATE USER mycoz;
GRANT ALL ON mycozBranch.* TO 'mycoz';
GRANT ALL ON mycozShared.* TO 'mycoz';

SET PASSWORD FOR mycoz = PASSWORD('xmlpj0#');
update user set Host='localhost' where User='mycoz';

FLUSH PRIVILEGES;

set FOREIGN_KEY_CHECKS=1;

FLAG
