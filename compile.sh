#!/bin/bash

cdir=`pwd`
JAVA_HOME=/usr/java/j2sdk
CATALINA_HOME=/home/xpc
srcdir=$cdir/WEB-INF/src
classdir=$cdir/WEB-INF/classes/

export CLASSPATH=.:$classdir
libfilelist=`find $cdir/WEB-INF/lib -name "*.jar" -print`
for lf in $libfilelist
do
  export CLASSPATH=$CLASSPATH:$lf
done
echo $CLASSPATH
cd $srcdir

jflist=`find  -name "*.java" -print`
for jf in $jflist
do
  cf0=`echo $jf | sed s/\.java$/\.class/`
#  cf=`echo $cf0 | sed s/^\.//`
  cf=${classdir}${cf0}
  if [ -f $cf -a $cf -nt $jf ]; then
     echo "$cf0 is up to date" > /dev/null
     # echo "$cf0 is up to date"
  else
     echo "javac $jf to $cdir/WEB-INF/classes..."
     $JAVA_HOME/bin/javac -d $cdir/WEB-INF/classes/ $jf
  fi
# Alternative way
#  bjf=`basename $jf`
#  njf=`find . -name $bjf -newer $cf -print`
#  if [ -n $njf -a $njf == $jf ]; then
#     javac $jf
#  fi
done

#cd $cdir

