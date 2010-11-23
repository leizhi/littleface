#!/bin/sh

set pagesize 0
set long 90000
set feedback off
set echo off

spool sddb_schema.sql
connect sddb/weather;
SELECT DBMS_METADATA.GET_DDL('TABLE',u.table_name) FROM USER_TABLES u;
SELECT DBMS_METADATA.GET_DDL('INDEX',u.index_name) FROM USER_INDEXES u;
spool off;

select (SELECT * FROM u.TABLE_NAME) from USER_TABLES u;

--select 'exp sddb/weather@orcl TABLES='||table_name||' FILE='||table_name||'.dmp TRIGGERS=N' from user_tables;

--select 'select * from '||table_name||' ' from user_tables;

set feedback off;
set pagesize 0;
set heading off;
set verify off;
set linesize 200;
set trimspool on;
spool c:\数据库备份.bat;

select 'exp sddb/weather@orcl TABLES='||table_name||' FILE='||table_name||'.dmp TRIGGERS=N' from user_tables;

spool off;
set feedback on;
set pagesize 9999;
set heading on;
set verify on;

exit
