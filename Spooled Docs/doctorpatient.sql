SQL> create table DOCTOR_PATIENT
  2  (record_no int not null,
  3  doc_id int not null,
  4  p_id int not null,
  5  description varchar(128) not null,
  6  prescription varchar(128) not null,
  7  dt date not null);

Table created.

SQL> alter table DOCTOR_PATIENT
  2  add constraint doc_pat_prKey primary key(record_no);

Table altered.

SQL> create sequence doc_pat_seq start with 1;

Sequence created.

SQL> create or replace trigger doc_pat_tri
  2  before insert on DOCTOR_PATIENT
  3  for each row
  4  begin
  5  select doc_pat_seq.nextval into :new.record_no
  6  from dual;
  7  end;
  8  /

Trigger created.

SQL> alter table DOCTOR_PATIENT
  2  add constraint doc_frKey foreign key(doc_id)
  3  references DOCTOR(doc_id);

Table altered.

SQL> alter table DOCTOR_PATIENT
  2  add constraint p_frKey foreign key(p_id)
  3  references PATIENT(p_id);

Table altered.

SQL> spool off;
