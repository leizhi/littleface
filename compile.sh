#!/bin/bash

PWD=`pwd`
JAVA_HOME=/usr/java/j2sdk/
CATALINA_HOME=/home/zlei/apache-tomcat-5.5.30/
SRC=$PWD/WEB-INF/src/
CLASSES=$PWD/WEB-INF/classes/
LIB=$PWD/WEB-INF/lib/

export CLASSPATH=.:$CLASSES

libfilelist=`find $CATALINA_HOME/common -name "*.jar" -print`
for lf in $libfilelist
do
  export CLASSPATH=$CLASSPATH:$lf
done

libfilelist=`find $CATALINA_HOME/shared -name "*.jar" -print`
for lf in $libfilelist
do
  export CLASSPATH=$CLASSPATH:$lf
done

libfilelist=`find $LIB -name "*.jar" -print`
for lf in $libfilelist
do
  export CLASSPATH=$CLASSPATH:$lf
done

echo "export CLASSPATH=$CLASSPATH"

cd $SRC

jflist=`find -name "*.java" -print`
for jf in $jflist
do
  cf0=`echo $jf | sed s/\.java$/\.class/`
#  cf=`echo $cf0 | sed s/^\.//`
  cf=${CLASSES}${cf0}
  #if [ -f $cf -a $cf -nt $jf ]; then
   #  echo "$cf0 is up to date" > /dev/null
     # echo "$cf0 is up to date"
  #else
   #  echo "javac $jf to $cdir/WEB-INF/classes..."
    # $JAVA_HOME/bin/javac -d $CLASSES $jf
  #fi
  if [ $cf -ot $jf ]; then
     echo "javac $jf to $PWD/WEB-INF/classes..."
     $JAVA_HOME/bin/javac -d $CLASSES $jf
  fi
done

cd $PWD
