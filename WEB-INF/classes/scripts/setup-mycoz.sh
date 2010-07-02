#!/bin/sh

DBDIR="`pwd`"
SETUPDIR="mycoz-db"
if [ -z $1 ] ; then
	PASSWORD=JZworkb0x
else 
	PASSWORD=$1
fi

echo "setup-mycoz $PASSWORD"

BackupDatabases() {

mysqldump -uroot -p$PASSWORD -t mycozBranch > mycozBranch-t.sql
mysqldump -uroot -p$PASSWORD -d -R mycozBranch > mycozBranch-d.sql
mysqldump -uroot -p$PASSWORD -t mycozShared > mycozShared-t.sql
mysqldump -uroot -p$PASSWORD -d -R mycozShared > mycozShared-d.sql

}


if [ ! -d $SETUPDIR ] ; then
mkdir $SETUPDIR
fi

cd $SETUPDIR
BackupDatabases
