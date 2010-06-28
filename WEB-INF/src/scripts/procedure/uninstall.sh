#!/bin/bash
cdir=`pwd`
mysql -uxpcwuser -pclick89 < e.sql
echo "uninstall end"
cd $cdir

