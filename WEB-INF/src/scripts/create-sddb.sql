conn /as sysdba;
startup;
conn /as sysdba;
create user sddb identified by sddb;
grant connect,resource to sddb;
conn sddb/sddb@orcl;
select * from dual;