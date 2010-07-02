#!/bin/bash
cdir=`pwd`
mv e.sql e
sqllist=`find -name "*.sql" -print`
for sqlfile in $sqllist
do
 echo "install mysql database  procedure  $sqlfile"
 mysql -uxpcwuser -pclick89 <$sqlfile
done
mv e e.sql
cd $cdir

