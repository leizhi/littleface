#You must have select privileges on the v$parameter
#v$logfile v$datafile and v$controlfile data
#dictionary views belonging to SYS to run this program

#!/bin/sh

export EXP_BACK_DIR = e:\oradb\expbackups
export SCRIPT_FILE = e:\back\expbackup.bat
export LOG_FILE= e:\back\expbackup.log
export INT_USER = gas
export INT_PWD = gas
set feedback off
set heading off
set pagesize 0
set linesize 128
set verify off
set echo off
col a new_value b
col c new_value d
select value a,to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') c from v$parameter where name ='db_name';

spool &SCRIPT_FILE
prompt rem ***** EXP ORACLE DATABASE FOR &INT_USER USER of &b ON WINDOWS NT ON &d*****
prompt
prompt rem ***** SET BACKUP FILES DIRECTORY *****
prompt md e:\oradb
prompt md e:\oradb\expbackups
prompt
select 'del &EXP_BACK_DIR\exp&INT_USER.dmp' from dual;
prompt
select 'exp Userid=&INT_USER/&INT_PWD file=&EXP_BACK_DIR\exp&INT_USER.dmp Buffer=102400 log=&EXP_BACK_DIR\exp&INT_USER grants=y indexes=y' from dual;
prompt
select 'copy &EXP_BACK_DIR\exp&INT_USER.dmp &EXP_BACK_DIR\exp&INT_USER'||to_char(sysdate, 'MMDDHH24MI')||'.dmp' from dual;
prompt
prompt set LogFile=&LOG_FILE
prompt echo COMPLETE EXP BACKUP FOR &INT_USER USER "&b" DATABASE STARTED ON &d ...> %logFile%
prompt exit 

spool off

$&SCRIPT_FILE
$del &SCRIPT_FILE
