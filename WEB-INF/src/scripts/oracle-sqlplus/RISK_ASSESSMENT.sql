drop table RISK_ASSESSMENT cascade constraints;
-- Create table
create table RISK_ASSESSMENT
(
  ID                      NUMBER not null,
  TITLE                   VARCHAR2(100),
  MESSAGE_TYPE_ID         NUMBER not null,
  RISK_TYPE_ID            NUMBER not null,
  RISK_DATE               DATE default SYSDATE,
  POPULATION              NUMBER not null,
  PROJECT_LOCATION        VARCHAR2(100) not null,
  ACCOUNTABILITY_UNIT     VARCHAR2(100),
  ACCOUNTABILITY_POSITION VARCHAR2(100),
  CONTACT                 VARCHAR2(100),
  RISK_BODY               VARCHAR2(100),
  RISK_SUGGEST            VARCHAR2(100),
  RISK_RESULT             VARCHAR2(100),
  ACCESSORY_ID            NUMBER,
  RISK_AT_POSITION        VARCHAR2(50)
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
comment on table RISK_ASSESSMENT
  is '风险评估';
-- Add comments to the columns 
comment on column RISK_ASSESSMENT.ID
  is '主键';
comment on column RISK_ASSESSMENT.TITLE
  is '评估标题';
comment on column RISK_ASSESSMENT.MESSAGE_TYPE_ID
  is '信息类型ID';
comment on column RISK_ASSESSMENT.RISK_TYPE_ID
  is '评估类型ID';
comment on column RISK_ASSESSMENT.RISK_DATE
  is '评估时间';
comment on column RISK_ASSESSMENT.POPULATION
  is '涉及人数';
comment on column RISK_ASSESSMENT.PROJECT_LOCATION
  is '项目地点';
comment on column RISK_ASSESSMENT.ACCOUNTABILITY_UNIT
  is '责任单位';
comment on column RISK_ASSESSMENT.ACCOUNTABILITY_POSITION
  is '责任人职务';
comment on column RISK_ASSESSMENT.CONTACT
  is '责任人联系方式';
comment on column RISK_ASSESSMENT.RISK_BODY
  is '评估内容';
comment on column RISK_ASSESSMENT.RISK_SUGGEST
  is '评估建议';
comment on column RISK_ASSESSMENT.RISK_RESULT
  is '评估结果';
comment on column RISK_ASSESSMENT.ACCESSORY_ID
  is '附件ID';
comment on column RISK_ASSESSMENT.RISK_AT_POSITION
  is '评估组负责人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RISK_ASSESSMENT
  add constraint PK_RISK_ASSESSMENT_ID primary key (ID)
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
-- Create/Recreate indexes 
create index INDEX_MESSAGE_TYPE_ID on RISK_ASSESSMENT (MESSAGE_TYPE_ID)
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
create index INDEX_TITLE on RISK_ASSESSMENT (TITLE)
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