prompt PL/SQL Developer import file
prompt Created on 2010年5月24日 星期一 by Administrator
set feedback off
set define off
prompt Dropping fl_pushrecord_bk...

drop table fl_pushrecord_bk cascade constraints;
--start

prompt Creating fl_pushrecord_bk
create table fl_pushrecord_bk as select * from fl_pushrecord;

prompt Delete <  2010-05-08
delete from fl_pushrecord where filltime < to_date('2010-05-08','yyyy-mm-dd');

prompt Creating fl_rest 
create table fl_rest as 
select * from fl_pushrecord where (CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI'),ID) in
 ( select CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI') ,MAX(ID)
 from fl_pushrecord group by CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI') having count(*) > 1
);

prompt delete fl_pushrecord 
delete from fl_pushrecord where (CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI')) in
 ( select CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI')
 from fl_pushrecord group by CARCODE,CNGSATIONNO,CNGMACHINENO,TAGTYPE,to_char(filltime,'YYYY-MM-DD HH24:MI') having count(*) > 1
);

insert into fl_pushrecord select * from fl_rest;
update fl_pushrecord set CARCODE='外地车辆' where TAGTYPE=6;

drop table fl_rest cascade constraints;

--select * from fl_pushrecord t where t.tagtype=6;
--delete from fl_pushrecord where carcode is null;
--end

set feedback on
set define on
prompt Done.
