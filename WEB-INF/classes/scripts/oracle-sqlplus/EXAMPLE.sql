-- Create table
create table EXAMPLE
(
  ID     NUMBER not null,
  NAME   VARCHAR2(50),
  SCHOOL VARCHAR2(50),
  AGE    NUMBER
)
tablespace SPDEMO
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EXAMPLE
  is '例子';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXAMPLE
  add constraint ID primary key (ID)
  using index 
  tablespace SPDEMO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table EXAMPLE
  add constraint NAME unique (NAME)
  using index 
  tablespace SPDEMO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  create sequence EXAMPLE_ID increment by 1 start with 1;