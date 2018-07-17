SQL> CREATE TABLE ADMIN_LOGIN
  2  (username varchar(40) not null,
  3  name varchar(40) not null,
  4  password varchar(40) not null);

Table created.

SQL> alter table admin_login
  2  add constraint admin_prKey primary key(username);

Table altered.

SQL> spool off;
