delete from student
where syear='4'
and sclass='14'
and no='1';

rollback;

select m.id, m.name, m.area, a.area_name, m.age 
from member_어연진 m, area a 
where m.area = a.area_code;

insert into member_어연진
(id, name, area, age)
values
('401', '김연우', '40', 39);

rollback;

insert into member_어연진
(id, name, area, age)
values
('401', '김연우', '40', 35);

select m.id, m.name, m.area, a.area_name, m.age
from member_어연진 m, area a
where m.area = a.area_code
and m.id like '%401%';

update member_어연진 
set name='김건우', area='50', age=10
where id='501'; 

commit;

delete from member_어연진
where id='501';

select area_code
from area
where area_code='10';

select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age 
from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g 
where e.depart = d.depart_code
and e.grade = g.grade_code
order by e.no;

select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age 
from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g 
where e.depart = d.depart_code
and e.grade = g.grade_code
and grade_code like '%G01%';

select * from emp_어연진_depart
where depart_code like '%D01%';

select * from emp_어연진
where no like '%101%';

insert into emp_어연진
(no, name, depart, grade, age)
values
('102', '김연우', 'D02', 'G02', 39);

rollback;

update emp_어연진 
set name='김건우', depart='D04', grade='G03', age=10
where no='103'; 

commit;

delete from emp_어연진
where no='101';

update emp_어연진_depart
set depart_name = '법무'
where depart_code = 'D05';

delete from emp_어연진_depart
where depart_code like '%D01%';

create table shop_어연진_product(
    p_code VARCHAR2(4) not null primary key, 
    p_name VARCHAR2(20) not null,
    p_size VARCHAR2(4) not null,
    count number(3) not null,
    price number(8) not null
);

create table shop_어연진_sale(
    ordernum varchar2(4) not null primary key,
    p_code varchar2(4) not null,
    member_id varchar2(10) not null,
    buycount number(3) not null,
    payment varchar2(1) not null,
    buy_date date not null
);

insert into shop_어연진_product
(p_code, p_name, p_size, count, price)
values
('P345', '면티', 'xl', 10, 15000);

commit;

select nvl (max(p_code), 'P001') as p_code
from shop_어연진_product;

select nvl(max(no), '0000') + 1 as no
from JSL_어연진_NEWS;

delete from shop_어연진_product;

select * from SHOP_어연진_PRODUCT
order by p_code;

select e.no, e.name, d.depart_name, g.grade_name, e.age
from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g
where e.depart = d.depart_code
and e.grade = g.grade_code
and no like '%101%'
order by e.no;

select e.grade, g.grade_name 
from emp_어연진 e, emp_어연진_grade g
where e.grade = g.grade_code
and g.grade_code like '%G01%';

insert into jsl_어연진_member
(id, name, password, job, 
tell_1, tell_2, tell_3, 
mobile_1, mobile_2, mobile_3, 
email_1, email_2, reg_date)
values
('bbb',	'정유미', '5678', '1',
'042', '242', '4412',
'010', '7788', '6549',
'sshj0803', 'naver.com', to_date('2024-06-14 16:37:20','yyyy-MM-dd hh24:mi:ss')
);


update emp_어연진_grade
set grade_name='D01'
where grade_code like '%G01%';

delete emp_어연진_grade
where grade_code like '%G03%';

--공지사항--
create table jsl_어연진_notice(
    no varchar2(4) not null primary key, --게시글 번호
    title varchar2(100) not null, --제목
    content varchar2(2000) not null, --내용
    attach varchar2(50), --첨부
    hit number(5) default 0, --조회수
    reg_id varchar2(20) not null, --등록자
    reg_date date not null --등록일
);

--회원--
create table jsl_어연진_member(
    id varchar2(20) not null primary key,
    name varchar2(30) not null,
    password varchar2(20) not null,
    job varchar2(1),
    tell_1 varchar2(3), 
    tell_2 varchar2(4),
    tell_3 varchar2(4),
    mobile_1 varchar2(3) not null,
    mobile_2 varchar2(4) not null,
    mobile_3 varchar2(4) not null,
    email_1 varchar2(20) not null,
    emali_2 varchar2(10) not null,
    reg_date date not null,  --가입일
    last_login_date date, --최근로그인일자
    exit_date date --탈퇴일자
);

--NEWS--
create table jsl_어연진_news(
    no varchar2(4) not null primary key, --게시글 번호
    title varchar2(100) not null, --제목
    content varchar2(2000) not null, --내용
    hit number(5) default 0, --조회수
    reg_id varchar2(20) not null, --등록자
    reg_date date not null, --등록일
    ipt varchar2(1) --중요도
);

--Q&N--
create table jsl_어연진_qna(
    no varchar2(4) not null primary key, --게시글 번호
    title varchar2(100) not null, --제목
    content varchar2(2000) not null, --내용
    hit number(5) default 0, --조회수
    reg_id varchar2(20) not null, --등록자
    reg_date date not null, --등록일
    answer_state varchar2(8), --답변상태
    answer_content varchar2(2000) --답변 내용   
);

--Faq--
create table jsl_어연진_faq(
    no varchar2(4) not null primary key, --게시글 번호
    title varchar2(100) not null, --제목
    hit number(5) default 0, --조회수
    reg_id varchar2(20) not null, --등록자
    reg_date date not null, --등록일
    answer_content varchar2(2000) not null, --답변 내용  
    ipt varchar2(1) not null --중요도
);

--자료실--
create table jsl_어연진_pds(
    no varchar2(4) not null primary key, --게시글 번호
    title varchar2(100) not null, --제목
    content varchar2(2000) not null, --내용
    attach varchar2(50), --첨부
    hit number(5) default 0, --조회수
    reg_id varchar2(20) not null, --등록자
    reg_date date not null, --등록일
    update_id varchar2(20), --업데이트 사용자
    update_date date -- 업데이트일
);

select count(*) as count
from jsl_어연진_notice
where title like '%%';

update jsl_어연진_notice 
set hit = hit + 1
where no = '1';

insert into jsl_어연진_news
(no, title, content, reg_id, reg_date, ipt)
values
('1',	'aaaa', 'sshj080', 'admin',
to_date('2024-06-14 16:37:20','yyyy-MM-dd hh24:mi:ss'),
'i'
);

select * from(
    select rownum as rnum, tbl.*
    from(
        select n.no, n.title, m.name as reg_name, 
        to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit
        from jsl_어연진_member m, jsl_어연진_news n
        where m.id = n.reg_id
        and title like '%%'
        order by decode(ipt, 'i', '1'), to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;
    
select count(*) as count
from JSL_어연진_NEWS
where title like '%%';
    
select n.title, m.name as reg_name, n.content, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from jsl_어연진_member m, jsl_어연진_notice n
where m.id = n.reg_id 
and no = '1';

select n.no, n.title, m.name as reg_name, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from JSL_어연진_MEMBER m, JSL_어연진_NEWS n
where m.id = n.reg_id
and n.title like '%공%'
order by decode(ipt, 'i', 1), to_number(no) desc;

select n.title, m.name as reg_name, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from JSL_어연진_MEMBER m, JSL_어연진_NEWS n
where m.id = n.reg_id
and n.no like '%1%';

select no, title
from jsl_어연진_notice
where no =
    (select max(no) as no
    from jsl_어연진_notice
    where no < 8);

select b.no, c.title
from
    (select max(to_number(a.num)) as no
    from
        (select  row_number() over(order by no) as num, no
        from "JSL_어연진_NEWS"
        where ipt = 'n') a
    where num < 3) b, jsl_어연진_news c
where b.no = c.no;

select  row_number() over(order by no) as num, no
        from jSL_어연진_NEWS
        where ipt = 'i';
        
select no, title
from "JSL_어연진_NEWS"
where no = '1';

--이전글--    
select a.no, b.title
from
    (select max(to_number(no)) as no
    from jsl_어연진_news
    where no < 8 and ipt='i') a, jsl_어연진_news b
where a. no = b.no;    

--다음글--
select a.no, b.title
from
    (select min(to_number(no)) as no
    from jsl_어연진_news
    where no > 3 and ipt='i') a, jsl_어연진_news b
where a. no = b.no;

select no
from JSL_어연진_NEWS
where ipt = 'i';

select max(no)
from jsl_어연진_notice
where no > 8;

update  JSL_어연진_NEWS
set title = '1111', content = '2222', ipt = 'i'
where no = '1';

delete from JSL_어연진_News
where no = '1';

select * from
    (select rownum as rnum, tbl.* from
        (select q.no, q.title, q.answer_state, m.name, 
        to_char(q.reg_date, 'yyyy-MM-dd') as reg_date, q.hit
        from jsl_어연진_member m, jsl_어연진_qna q
        where m.id = q.reg_id
        and q.title like '%%'
        order by to_number(no) desc) tbl)
where rnum >= 1 and rnum <=8;

update JSL_어연진_QNA
set answer_content = null, answer_state = 'waiting'
where no = '1';

select * from(
    select rownum as rnum, tbl.*
    from(
        select p.no, p.title, m.name as reg_name, p.attach,
        to_char(p.reg_date, 'yyyy-MM-dd') as reg_date, p.hit
        from JSL_어연진_pds p, JSL_어연진_MEMBER m 
        where p.reg_id = m.id
        and p.title like '%%'
        order by to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;

select p.title, p.content, m1.name as reg_name, p.attach,
				to_char(p.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, p.hit,
				to_char(p.update_date, 'yyyy-MM-dd hh24:mi:ss') as update_date, m2.name as update_name\r\n" +
				from JSL_어연진_PDS p, jsl_어연진_member m1, jsl_어연진_member m2\r\n" + 
            where p.reg_id = m1.id\r\n" + 
				and p.update_id = m2.id\r\n" +
				and p.no = '1'

select * from(
    select rownum as rnum, tbl.*
    from(
        select n.no, n.title, m.name as reg_name n.attach, 
        to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit
        from jsl_어연진_member m, jsl_어연진_notice n
        where m.id = n.reg_id
        and title like '%%'
        order by to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;

select n.no, n.title, to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from(
    select count(*) as count from jsl_어연진_notice
    )a, jsl_어연진_notice n
where n.no > (a.count - 4)
order by to_number(no) desc;


rollback;
commit;