<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서대여 프로그램</title>
	<link href="main.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1>(과정평가형정보처리산업기사)도서대여 프로그램</h1>
	</header>	
	
	<nav>
		<ul>
			<li><a href="sub_1.jsp">회원조회</a></li>
			<li><a href="sub_2.jsp">도서대여등록</a></li>
			<li><a href="sub_3.jsp">대여이력조회</a></li>
			<li><a href="sub_4.jsp">도서대여건수</a></li>
			<li><a href="index.jsp">홈으로</a></li>
		</ul>
	</nav>
	
	<section>
		<h2>도서 대여 이력 조회</h2>
		<br>
		<table border="1">
			<colgroup>
				<col width="15%">
				<col width="15%">
				<col width="30%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<tr>
				<th>대여번호</th>
				<th>회원명</th>
				<th>도서명</th>
				<th>대여일자</th>
				<th>반납일자</th>
			</tr>
			<tr>
				<td>A001</td>
				<td>김스타</td>
				<td>1990년8월20일</td>
				<td>여성</td>
				<td>댄스</td>
			</tr>			
		</table>
		
		
	</section>
	
	<footer>
		<p>
			HRDKOREA Copyright © All rights Reserved. Human Resources Developement Service of Korea.
		</p>
	</footer>
</body>
</html>