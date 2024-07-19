delete from student
where syear='4'
and sclass='14'
and no='1';

rollback;

select m.id, m.name, m.area, a.area_name, m.age 
from member_��� m, area a 
where m.area = a.area_code;

insert into member_���
(id, name, area, age)
values
('401', '�迬��', '40', 39);

rollback;

insert into member_���
(id, name, area, age)
values
('401', '�迬��', '40', 35);

select m.id, m.name, m.area, a.area_name, m.age
from member_��� m, area a
where m.area = a.area_code
and m.id like '%401%';

update member_��� 
set name='��ǿ�', area='50', age=10
where id='501'; 

commit;

delete from member_���
where id='501';

select area_code
from area
where area_code='10';

select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age 
from emp_��� e, emp_���_depart d, emp_���_grade g 
where e.depart = d.depart_code
and e.grade = g.grade_code
order by e.no;

select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age 
from emp_��� e, emp_���_depart d, emp_���_grade g 
where e.depart = d.depart_code
and e.grade = g.grade_code
and grade_code like '%G01%';

select * from emp_���_depart
where depart_code like '%D01%';

select * from emp_���
where no like '%101%';

insert into emp_���
(no, name, depart, grade, age)
values
('102', '�迬��', 'D02', 'G02', 39);

rollback;

update emp_��� 
set name='��ǿ�', depart='D04', grade='G03', age=10
where no='103'; 

commit;

delete from emp_���
where no='101';

update emp_���_depart
set depart_name = '����'
where depart_code = 'D05';

delete from emp_���_depart
where depart_code like '%D01%';

create table shop_���_product(
    p_code VARCHAR2(4) not null primary key, 
    p_name VARCHAR2(20) not null,
    p_size VARCHAR2(4) not null,
    count number(3) not null,
    price number(8) not null
);

create table shop_���_sale(
    ordernum varchar2(4) not null primary key,
    p_code varchar2(4) not null,
    member_id varchar2(10) not null,
    buycount number(3) not null,
    payment varchar2(1) not null,
    buy_date date not null
);

insert into shop_���_product
(p_code, p_name, p_size, count, price)
values
('P345', '��Ƽ', 'xl', 10, 15000);

commit;

select nvl (max(p_code), 'P001') as p_code
from shop_���_product;

select nvl(max(no), '0000') + 1 as no
from JSL_���_NEWS;

delete from shop_���_product;

select * from SHOP_���_PRODUCT
order by p_code;

select e.no, e.name, d.depart_name, g.grade_name, e.age
from emp_��� e, emp_���_depart d, emp_���_grade g
where e.depart = d.depart_code
and e.grade = g.grade_code
and no like '%101%'
order by e.no;

select e.grade, g.grade_name 
from emp_��� e, emp_���_grade g
where e.grade = g.grade_code
and g.grade_code like '%G01%';

insert into jsl_���_member
(id, name, password, job, 
tell_1, tell_2, tell_3, 
mobile_1, mobile_2, mobile_3, 
email_1, email_2, reg_date)
values
('bbb',	'������', '5678', '1',
'042', '242', '4412',
'010', '7788', '6549',
'sshj0803', 'naver.com', to_date('2024-06-14 16:37:20','yyyy-MM-dd hh24:mi:ss')
);


update emp_���_grade
set grade_name='D01'
where grade_code like '%G01%';

delete emp_���_grade
where grade_code like '%G03%';

--��������--
create table jsl_���_notice(
    no varchar2(4) not null primary key, --�Խñ� ��ȣ
    title varchar2(100) not null, --����
    content varchar2(2000) not null, --����
    attach varchar2(50), --÷��
    hit number(5) default 0, --��ȸ��
    reg_id varchar2(20) not null, --�����
    reg_date date not null --�����
);

--ȸ��--
create table jsl_���_member(
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
    reg_date date not null,  --������
    last_login_date date, --�ֱٷα�������
    exit_date date --Ż������
);

--NEWS--
create table jsl_���_news(
    no varchar2(4) not null primary key, --�Խñ� ��ȣ
    title varchar2(100) not null, --����
    content varchar2(2000) not null, --����
    hit number(5) default 0, --��ȸ��
    reg_id varchar2(20) not null, --�����
    reg_date date not null, --�����
    ipt varchar2(1) --�߿䵵
);

--Q&N--
create table jsl_���_qna(
    no varchar2(4) not null primary key, --�Խñ� ��ȣ
    title varchar2(100) not null, --����
    content varchar2(2000) not null, --����
    hit number(5) default 0, --��ȸ��
    reg_id varchar2(20) not null, --�����
    reg_date date not null, --�����
    answer_state varchar2(8), --�亯����
    answer_content varchar2(2000) --�亯 ����   
);

--Faq--
create table jsl_���_faq(
    no varchar2(4) not null primary key, --�Խñ� ��ȣ
    title varchar2(100) not null, --����
    hit number(5) default 0, --��ȸ��
    reg_id varchar2(20) not null, --�����
    reg_date date not null, --�����
    answer_content varchar2(2000) not null, --�亯 ����  
    ipt varchar2(1) not null --�߿䵵
);

--�ڷ��--
create table jsl_���_pds(
    no varchar2(4) not null primary key, --�Խñ� ��ȣ
    title varchar2(100) not null, --����
    content varchar2(2000) not null, --����
    attach varchar2(50), --÷��
    hit number(5) default 0, --��ȸ��
    reg_id varchar2(20) not null, --�����
    reg_date date not null, --�����
    update_id varchar2(20), --������Ʈ �����
    update_date date -- ������Ʈ��
);

select count(*) as count
from jsl_���_notice
where title like '%%';

update jsl_���_notice 
set hit = hit + 1
where no = '1';

insert into jsl_���_news
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
        from jsl_���_member m, jsl_���_news n
        where m.id = n.reg_id
        and title like '%%'
        order by decode(ipt, 'i', '1'), to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;
    
select count(*) as count
from JSL_���_NEWS
where title like '%%';
    
select n.title, m.name as reg_name, n.content, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from jsl_���_member m, jsl_���_notice n
where m.id = n.reg_id 
and no = '1';

select n.no, n.title, m.name as reg_name, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from JSL_���_MEMBER m, JSL_���_NEWS n
where m.id = n.reg_id
and n.title like '%��%'
order by decode(ipt, 'i', 1), to_number(no) desc;

select n.title, m.name as reg_name, n.hit,
to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from JSL_���_MEMBER m, JSL_���_NEWS n
where m.id = n.reg_id
and n.no like '%1%';

select no, title
from jsl_���_notice
where no =
    (select max(no) as no
    from jsl_���_notice
    where no < 8);

select b.no, c.title
from
    (select max(to_number(a.num)) as no
    from
        (select  row_number() over(order by no) as num, no
        from "JSL_���_NEWS"
        where ipt = 'n') a
    where num < 3) b, jsl_���_news c
where b.no = c.no;

select  row_number() over(order by no) as num, no
        from jSL_���_NEWS
        where ipt = 'i';
        
select no, title
from "JSL_���_NEWS"
where no = '1';

--������--    
select a.no, b.title
from
    (select max(to_number(no)) as no
    from jsl_���_news
    where no < 8 and ipt='i') a, jsl_���_news b
where a. no = b.no;    

--������--
select a.no, b.title
from
    (select min(to_number(no)) as no
    from jsl_���_news
    where no > 3 and ipt='i') a, jsl_���_news b
where a. no = b.no;

select no
from JSL_���_NEWS
where ipt = 'i';

select max(no)
from jsl_���_notice
where no > 8;

update  JSL_���_NEWS
set title = '1111', content = '2222', ipt = 'i'
where no = '1';

delete from JSL_���_News
where no = '1';

select * from
    (select rownum as rnum, tbl.* from
        (select q.no, q.title, q.answer_state, m.name, 
        to_char(q.reg_date, 'yyyy-MM-dd') as reg_date, q.hit
        from jsl_���_member m, jsl_���_qna q
        where m.id = q.reg_id
        and q.title like '%%'
        order by to_number(no) desc) tbl)
where rnum >= 1 and rnum <=8;

update JSL_���_QNA
set answer_content = null, answer_state = 'waiting'
where no = '1';

select * from(
    select rownum as rnum, tbl.*
    from(
        select p.no, p.title, m.name as reg_name, p.attach,
        to_char(p.reg_date, 'yyyy-MM-dd') as reg_date, p.hit
        from JSL_���_pds p, JSL_���_MEMBER m 
        where p.reg_id = m.id
        and p.title like '%%'
        order by to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;

select p.title, p.content, m1.name as reg_name, p.attach,
				to_char(p.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, p.hit,
				to_char(p.update_date, 'yyyy-MM-dd hh24:mi:ss') as update_date, m2.name as update_name\r\n" +
				from JSL_���_PDS p, jsl_���_member m1, jsl_���_member m2\r\n" + 
            where p.reg_id = m1.id\r\n" + 
				and p.update_id = m2.id\r\n" +
				and p.no = '1'

select * from(
    select rownum as rnum, tbl.*
    from(
        select n.no, n.title, m.name as reg_name n.attach, 
        to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit
        from jsl_���_member m, jsl_���_notice n
        where m.id = n.reg_id
        and title like '%%'
        order by to_number(no) desc
) tbl)
where rnum>= 1 and rnum<=4;

select n.no, n.title, to_char(n.reg_date, 'yyyy-MM-dd') as reg_date
from(
    select count(*) as count from jsl_���_notice
    )a, jsl_���_notice n
where n.no > (a.count - 4)
order by to_number(no) desc;


rollback;
commit;