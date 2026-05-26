use employees;

/*
AUTO INCREMENT
- 행이 INSERT 될 때마다 자동으로 1씩 증가하는 고유번호 생성
- 적용하기 위해서는 PK UNIQUE INDEX 이어야 함.

*/


create table board
(
    board_no   int          not null auto_increment,
    title      varchar(100) not null,
    content    text,
    author     varchar(50)  not null,
    created_at datetime default now(),
    primary key (board_no)
);

insert into board (title, content, author)
values ('첫번째 글', '안녕하세요, 첫번쨰 글입니다.', 'author');

insert into board (title, content, author)
values ('두번째 글', '안녕하세요, 두번째 글입니다.', 'author');

insert into board (title, content, author)
values ('세번째 글', '안녕하세요, 세번째 글입니다.', 'author');

-- null 또는 0을 넣어도 Auto_increment가 동작한다.
insert into board
values (null, '네번째 글', '안녕하세요, 네번째 글입니다.', '홍길동', now());

insert into board
values (0, '다섯번째 글', '안녕하세요, 다섯번째 글입니다.', '홍길동', now());

select * from board;

delete from board where board_no = 7;


-- auto increment 시작값 변경
alter table board auto_increment = 100;

insert into board (title, content, author)
values ('100부터 시작', '시작값을 100으로 변경 후 사입', 'bear')

select * from board;

-- last_inset_id()
-- 현재 세션에서 마지막으로 insert 된 auto_increment 값을 반환
insert into board (title, content, author)
values ('100부터 시작', '시작값을 100으로 변경 후 삽입', 'bear');
select last_insert_id()



