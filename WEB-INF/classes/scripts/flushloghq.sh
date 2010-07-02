#!/bin/sh
set -x

echo "Stariting flushlog.sh at `date` ... "

OPERATIONDIR=/home/xpc/webapps/nick/WEB-INF/src/scripts
LOGDIR=/var/lib/mysql
UPLOADDIR=$OPERATIONDIR/upload
DOWNLOADDIR=$OPERATIONDIR/download
ARCHIVEDIR=$OPERATIONDIR/archive

OWNER=xpc
MAXTRIES=5
RET=-1

if [ ! -d $OPERTIONDIR ]; then
   mkdir $OPERTIONDIR
fi
if [ ! -d $UPLOADDIR ]; then
   mkdir $UPLOADDIR
   chmod 775 $UPLOADDIR
   chown $OWNER.root $UPLOADDIR
fi
if [ ! -d $DOWNLOADDIR ]; then
   mkdir $DOWNLOADDIR
   chmod 775 $DOWNLOADDIR
   chown $OWNER.root $DOWNLOADDIR
fi
if [ ! -d $ARCHIVEDIR ]; then
   mkdir $ARCHIVEDIR
   chmod 775 $ARCHIVEDIR
   chown $OWNER.root $ARCHIVEDIR
fi

flush() {
  RET=9
  if [ -f $UPLOADDIR/syncingbranch ]; then
     echo " Updating logs in process. Will try it later. "
     return $RET 
  else
    date > $UPLOADDIR/flushinglog
    cd $LOGDIR
    loglist=`ls logbin.[0-9]*`
    if [ "$loglist" ]; then
      mysqladmin -uroot -pJZworkb0x flush-logs 
      for logf in $loglist
      do
        echo "cp $logf $UPLOADDIR"
        cp -p $logf $UPLOADDIR
        chown $OWNER.root $UPLOADDIR/$logf
        gzip $logf
        mv $logf.gz $ARCHIVEDIR
      done
    fi
    /bin/rm -f $UPLOADDIR/flushinglog
    RET=0
    return $RET
  fi  
}    

idx=0
while [ $RET -ne 0 -a "$idx" -lt $MAXTRIES ]; do
  flush
  idx=`expr $idx + 1`
done
echo "Ending flushlog.sh at `date` ... "
exit $RET
