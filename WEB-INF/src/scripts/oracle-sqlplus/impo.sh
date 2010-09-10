#You must have select privileges on the v$parameter
#v$logfile v$datafile and v$controlfile data
#dictionary views belonging to SYS to run this program

export EXP_BACK_DIR = e:\oradb\expbackups
export SCRIPT_FILE = e:\back\imprevall.bat
export LOG_FILE= e:\back\imprevall.log
export INT_USER = gas
export INT_PWD = gas
export TO_USER = gas
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
prompt rem ***** IMP ORACLE DATABASE FOR &INT_USER USER OF &b ON WINDOWS NT ON &d*****
prompt
select 'imp Userid=&INT_USER/&INT_PWD fromuser=&INT_USER touser=&TO_USER commit=y ignore=y Buffer=102400 file=&EXP_BACK_DIR\exp&INT_USER.dmp log=&EXP_BACK_DIR\imp&TO_USER '  from dual;
prompt
prompt set LogFile=&LOG_FILE
prompt echo COMPLETE IMP RECOVER FOR &INT_USER USER OF "&b" DATABASE STARTED ON &d ...> %logFile%
prompt exit 

spool off

$&SCRIPT_FILE
$del &SCRIPT_FILE
