conn /as sysdba;
startup;
conn /as sysdba;
create user sddb identified by weather;
grant connect,resource to sddb;
conn sddb/weather@orcl;
select sysdate from dual;
