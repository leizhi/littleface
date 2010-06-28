#!/bin/sh
#  1. Remember to change StrictHostKeyChecking to no in file ssh_config!
#  2. quotes "" become '' in line of scp and rm when executed through web/java
set -x 

echo "Starting syncbranch.sh at `date` ..."

HQSERVER=$1
BRANCHCODE=$2
LOGDIR=
OPERATIONDIR=/home/xpc/webapps/nick/WEB-INF/src/scripts
ARCHIVEHQ=$OPERATIONDIR/archivehq
UPLOADDIR=$OPERATIONDIR/upload
DOWNLOADDIR=$OPERATIONDIR/download
HQHOST=xpc@192.168.1.188
#HQHOST="das-$BRANCHCODE@$HQSERVER"
#HQUPLOAD=$OPERATIONDIR/upload/$BRANCHCODE
#HQDOWNLOAD=$OPERATIONDIR/download/$BRANCHCODE
HQUPLOAD=$OPERATIONDIR/upload
HQDOWNLOAD=$OPERATIONDIR/download
MAXTRIES=3
RET=-1
SYNCERRORLOCK=$OPERATIONDIR/SyncErrorLock

echo $HQHOST
echo $HQDOWNLOAD

if [ ! -d $ARCHIVEHQ ]; then
   mkdir $ARCHIVEHQ
fi

upload() {
  RET=9
  if [ -s $SYNCERRORLOCK ]; then
     cat $SYNCERRORLOCK
     return 99
  fi

  if [ -f $UPLOADDIR/flushinglog ]; then
     echo " Flushing logs in process. Please try it later. "
     return $RET
  else
     date > $UPLOADDIR/syncingbranch
     cd $UPLOADDIR
     ssh $HQHOST "ls $HQUPLOAD/ok"
     RET=`echo $?`
     if [ $RET -eq 0 ]; then
       scp $UPLOADDIR/syncingbranch $HQHOST:$HQUPLOAD
       flist=`ls -rt logbin.[0-9]*`
       for fl in $flist
       do
         localmd5=`/usr/bin/md5sum $fl|awk '{print $1}'` 
         scp -pC $fl $HQHOST:$HQUPLOAD
         remotemd5=`ssh  $HQHOST "/usr/bin/md5sum $HQUPLOAD/$fl"|awk '{print $1}'` 
         if [ "X$localmd5" = "X$remotemd5" ]; then
           /bin/rm -f $fl
         else
           echo "ERROR: md5sum fails on $fl, remote $remotemd5 local $localmd5" > $SYNCERRORLOCK
           echo "ERROR: md5sum fails on $fl, remote $remotemd5 local $localmd5"
           ssh $HQHOST "/bin/rm -f $HQUPLOAD/$fl"
           ssh $HQHOST "/bin/rm -f $HQUPLOAD/syncingbranch"
           /bin/rm -f $UPLOADDIR/syncingbranch
           return 99
         fi
       done
       ssh $HQHOST "/bin/rm -f $HQUPLOAD/syncingbranch"
     else
       /bin/rm -f $UPLOADDIR/syncingbranch
       return $RET
     fi
  fi
  /bin/rm -f $UPLOADDIR/syncingbranch
  RET=0
  return $RET
}


echo "HQHOST=$HQHOST"
idx=0
RET=8
while [ $RET -ne 0 -a "$idx" -lt $MAXTRIES ]; do
  upload
  idx=`expr $idx + 1`
done

echo "Ending syncbranch.sh at `date` ..."
exit $RET  
