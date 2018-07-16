SQL> CREATE TABLE DOCTOR
  2  (doc_id int not null,
  3  doc_name varchar(128) not null,
  4  doc_mobile varchar(10) not null,
  5  doc_specialization varchar(50) not null);

Table created.

SQL> alter table DOCTOR
  2  add constraint doctor_prKey primary key(doc_id);

Table altered.

SQL> create sequence doctor_seq start with 1;

Sequence created.

SQL> create or replace trigger doctor_tri
  2  before insert on DOCTOR
  3  for each row
  4  begin
  5  select doctor_seq.nextval into :new.doc_id
  6  from dual;
  7  end;
  8  /

Trigger created.

SQL> spool off;
