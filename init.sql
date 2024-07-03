create schema quizwebdb;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS friendship;

create table user (
              id int primary key auto_increment,
              username char(128),
              password char(128),
              secret_word char(128)
);

create table friendship (
                     id int primary key auto_increment,
                     id1 int,
                     id2 int,
                     status char(128)
);

alter table friendship add constraint fk_id1 foreign key(id1) references user(id);
alter table friendship add constraint fk_id2 foreign key(id2) references user(id);
