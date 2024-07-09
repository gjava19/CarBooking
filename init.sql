create schema quizwebdb;
DROP TABLE IF EXISTS quizwebdb.user;
DROP TABLE IF EXISTS quizwebdb.friendship;
DROP TABLE IF EXISTS quizwebdb.quiz;
DROP TABLE IF EXISTS quizwebdb.quizHistory;

create table quizwebdb.user (
              id int primary key auto_increment,
              username char(128),
              password char(128),
              secret_word char(128)
);

create table quizwebdb.friendship (
                     id int primary key auto_increment,
                     id1 int,
                     id2 int,
                     status char(128)
);

create table quizwebdb.quiz(
                 id                 int primary key,
                 creator_id         int not null,
                 name               char(128) not null unique,
                 description        char(128),
                 create_time mediumtext,
                 mode_random        tinyint(1),
                 mode_pages         tinyint(1),
                 mode_immediate     tinyint(1),
                 quid_data          JSON
);

create table quizwebdb.quizHistory(
                      id           int NOT NULL,
                      writer_id    int NOT NULL,
                      time         int NOT NULL,
                      score        int NOT NULL
);

alter table quizwebdb.friendship add constraint fk_id1 foreign key(id1) references user(id);
alter table quizwebdb.friendship add constraint fk_id2 foreign key(id2) references user(id);
