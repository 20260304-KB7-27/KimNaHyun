
-- DDL (Data Definition Language)
-- 데이터베이스 구조를 정의하는 언어

drop database if exists tabledb;  -- 삭제

create database tabledb;  -- 생성

use tabledb;


create table usertbl
(
    userID    char(8)     not null
        primary key,
    name      varchar(10) not null,
    birthYear int         not null,
    addr      char(2)     not null,
    mobile1   char(3)     null,
    mobile2   char(8)     null,
    height    smallint    null,
    mDate     date        null
);
