#!/bin/sh

DBDIR="`pwd`"

if [ -z $1 ] ; then
	HEXT=`hostname`
elif [ -n $1 ] ; then
	HEXT=$1
fi

if [ -z $2 ] ; then
	DEXT=`date +'%y%m%d'`
else 
	DEXT=$2
fi

if [ -z $3 ] ; then
	PASSWORD=JZworkb0x
else 
	PASSWORD=$3
fi

echo "backup-mycoz $HEXT $DEXT $PASSWORD"

BackupDatabases() {

mysqldump -uroot -p$PASSWORD -t mysql > mysql-t.$HEXT.$DEXT
mysqldump -uroot -p$PASSWORD -d mysql > mysql-d.$HEXT.$DEXT
mysqldump -uroot -p$PASSWORD -t mycozBranch > mycozBranch-t.$HEXT.$DEXT
mysqldump -uroot -p$PASSWORD -d -R mycozBranch > mycozBranch-d.$HEXT.$DEXT
mysqldump -uroot -p$PASSWORD -t mycozShared > mycozShared-t.$HEXT.$DEXT
mysqldump -uroot -p$PASSWORD -d -R mycozShared > mycozShared-d.$HEXT.$DEXT

}


if [ ! -d $DEXT ] ; then
mkdir $DEXT
fi

cd $DEXT
BackupDatabases
