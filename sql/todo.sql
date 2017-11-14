create database todo;
create user dbuser@localhost identified by 'DBuser01+';
grant all on todo.* to dbuser@localhost identified by 'DBuser01+';
CREATE TABLE todo(
  id int not null primary key auto_increment,
  userId int not null,
  work varchar(100) not null,
  cond int not null,
  memo varchar(100),
  estimated_start_date date,
  esitmated_end_date date,
  create_date datetime,
  update_date datetime
);

CREATE TABLE user(
  userId int not null primary key auto_increment,
  mail varchar(100) not null,
  name varchar(100) not null,
  password varchar(255) not null
);
