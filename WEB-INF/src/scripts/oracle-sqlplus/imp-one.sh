#You must have select privileges on the v$parameter
#v$logfile v$datafile and v$controlfile data
#dictionary views belonging to SYS to run this program

# export tablename =a3

export EXP_BACK_DIR = e:\oradb\expbackups
export SCRIPT_FILE = e:\back\imprevtab.bat
export LOG_FILE= e:\back\imprevtab.log
export SQL_FILE = e:\back\truntab.sql
export TRIG_FILE = e:\back\entrig.sql
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
spool &SQL_FILE
select 'alter table &&1 disable  all triggers;' from dual;
select 'truncate table &&1 ;' from dual;
select 'exit' from dual;
spool off
spool &TRIG_FILE
select 'alter table &&1 enable  all triggers;' from dual;
select 'exit' from dual;
spool off
spool &SCRIPT_FILE
prompt rem ***** IMP ORACLE DATABASE FOR TABLE OF &INT_USER USER OF &b ON WINDOWS NT ON &d*****
prompt sqlplus gas/gas@sqcdb.sqc.com @&SQL_FILE
select 'imp Userid=&INT_USER/&INT_PWD fromuser=&INT_USER touser=&TO_USER commit=y ignore=y  Buffer=102400 tables=&&1 file=&EXP_BACK_DIR\exp&INT_USER.dmp ' from dual;
prompt sqlplus gas/gas@sqcdb.sqc.com @&TRIG_FILE
prompt set LogFile=&LOG_FILE
prompt echo COMPLETE IMP RECOVER FOR TABLE OF &INT_USER USER OF "&b" DATABASE STARTED ON &d ...> %logFile%
prompt exit 
spool off

$&SCRIPT_FILE
$del &SQL_FILE
$del &TRIG_FILE
$del &SCRIPT_FILE
