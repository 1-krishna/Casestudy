SQL> create table PATIENT
  2  (p_id int not null,
  3  p_name varchar(128) not null,
  4  p_mobile varchar(10) not null);

Table created.

SQL> alter table PATIENT
  2  add constraint patient_prKey primary key(p_id);

Table altered.

SQL> create sequence p_seq start with 1;

Sequence created.

SQL> create or replace trigger patient_tri
  2  before insert on PATIENT
  3  for each row
  4  begin
  5  select p_seq.nextval into :new.p_id
  6  from dual;
  7  end;
  8  /

Trigger created.

SQL> spool off;
