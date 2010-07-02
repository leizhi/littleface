-- Create table
create table GENERAL_TYPE
(
  ID          NUMBER not null,
  NAME        VARCHAR2(50) not null,
  CATEGORY    VARCHAR2(20),
  DESCRIPTION VARCHAR2(200)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GENERAL_TYPE
  is '通用类型';
-- Add comments to the columns 
comment on column GENERAL_TYPE.ID
  is '主键';
comment on column GENERAL_TYPE.NAME
  is '名称';
comment on column GENERAL_TYPE.CATEGORY
  is '类别';
comment on column GENERAL_TYPE.DESCRIPTION
  is '描述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GENERAL_TYPE
  add constraint PK_GENERAL_TYPE_ID primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate check constraints 
alter table GENERAL_TYPE
  add constraint SYS_GENERAL_TYPE_CATEGORY
  check (CATEGORY IN ('information','processing'));
-- Create/Recreate indexes 
create index INDEX_DESCRIPTION on GENERAL_TYPE (DESCRIPTION)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index INDEX_NAME on GENERAL_TYPE (NAME)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );