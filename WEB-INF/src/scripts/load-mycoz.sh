#!/bin/sh

DBDIR="`pwd`"

if [ -z $1 ] ; then
	echo "load-mycoz [HostName] [Date] [Password]"
	echo "[HostName Null]"
	exit 1
fi

if [ -z $2 ] ; then
      echo "load-mycoz [HostName] [Date] [Password]"
      echo "[Date Null]"
      exit 1
fi

if [ -z $3 ] ; then
	echo "load-mycoz [HostName] [Date] [Password]"
	echo "[Password Null]"
	exit 1
fi

HEXT=$1
DEXT=$2
PASSWORD=$3

LoadDatabases() {
mysql -uroot -p$PASSWORD << FLAG
set FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS mycozBranch;
DROP DATABASE IF EXISTS mycozShared;

CREATE DATABASE mycozBranch;
use mycozBranch;
source mycozBranch-d.$HEXT.$DEXT;
source mycozBranch-t.$HEXT.$DEXT;

CREATE DATABASE mycozShared;
use mycozShared;
source mycozShared-d.$HEXT.$DEXT;
source mycozShared-t.$HEXT.$DEXT;

set FOREIGN_KEY_CHECKS=1;
FLAG
}

if [ ! -d $DEXT ] ; then
mkdir $DEXT
fi

cd $DEXT
LoadDatabases
