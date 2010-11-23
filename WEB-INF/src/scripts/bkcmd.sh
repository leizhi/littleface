#!/bin/sh

PATH=$PATH:$HOME/bin

# Oracle Settings
TMP=/tmp; export TMP
TMPDIR=$TMP; export TMPDIR

ORACLE_HOSTNAME=localhost.localdomain; export ORACLE_HOSTNAME
ORACLE_UNQNAME=DB11G; export ORACLE_UNQNAME
ORACLE_BASE=/u01/app/oracle; export ORACLE_BASE
ORACLE_HOME=$ORACLE_BASE/product/11.2.0/dbhome_1; export ORACLE_HOME
ORACLE_SID=DB11G; export ORACLE_SID
ORACLE_TERM=xterm; export ORACLE_TERM
PATH=/usr/sbin:$PATH; export PATH
PATH=$ORACLE_HOME/bin:$PATH; export PATH

LD_LIBRARY_PATH=$ORACLE_HOME/lib:/lib:/usr/lib; export LD_LIBRARY_PATH
CLASSPATH=$ORACLE_HOME/JRE:$ORACLE_HOME/jlib:$ORACLE_HOME/rdbms/jlib; export CLASSPATH

export ORACLE_SID=orcl

export ORACLE_HOME_LISTNER=$ORACLE_HOME
export NLS_LANG=AMERICAN_AMERICA.UTF8

if [ $USER = "oracle" ]; then
  if [ $SHELL = "/bin/ksh" ]; then
    ulimit -p 16384
    ulimit -n 65536
  else
    ulimit -u 16384 -n 65536
  fi
fi

# Set up i18n variables
export LANG="en_US.UTF-8"
export LC_CTYPE="zh_CN.UTF-8"

export PATH

#define variable......
if [ -z $1 ] ; then
	BACKUP_HOME=/tmp/backup
else 
	BACKUP_HOME=$1
fi

DEXT=`date +'%y%m%d'`

################
if [ ! -d $BACKUP_HOME ] ; then
	mkdir -p $BACKUP_HOME
fi

BACKUP_DEST=$BACKUP_HOME/data
if [ ! -d $BACKUP_DEST ] ; then
	mkdir -p $BACKUP_DEST
fi

BACKUP_LOG=$BACKUP_HOME/log
if [ ! -d $BACKUP_LOG ] ; then
	mkdir -p $BACKUP_LOG
fi

RECIPT='leizhifesker@gmail.com'

# Check local disk space..................................
#USED=` df -k|grep /|awk '{print $5}'|awk -F% '{print $1}'`
#echo $USED

#if [ $USED -gt 85 ]; then
#       echo "Disk space is full\243\254please check it"|mail -s "exp full backup failed"  $RECIPT
#       exit 1
#fi

#starting exp backup.....
exp system/orcl owner=sddb file=$BACKUP_DEST/sddb_$DEXT.dmp log=$BACKUP_LOG/sddb_$DEXT.log

#delete expired  backup ...
list=` find $BACKUP_DEST -mtime +7`
echo $list
rm -f $list
if [ $? -ne 0 ]; then
       cat $BACKUP_LOG/data_$DEXT.log|mail -s "exp full backup failed,please check it"  $RECIPT
       exit 1
fi
cat $BACKUP_LOG/data_$DEXT.log|mail -s "$DEXT exp log "  $RECIPT
