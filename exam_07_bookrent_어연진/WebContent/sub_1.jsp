<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
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
		<h2>회원 조회</h2>
		<br>
		<table border="1">
			<colgroup>
				<col width="15%">
				<col width="15%">
				<col width="30%">
				<col width="10%">
				<col width="10%">
				<col width="20%">
			</colgroup>
			<tr>
				<th>회원번호</th>
				<th>회원명</th>
				<th>생년월일</th>
				<th>성별</th>
				<th>전화번호</th>
				<th>회원가입일</th>
			</tr>
			<c:forEach var="dto" items="${t_dtos }">
				<tr>
					<td>${dto.getP_no() }</td>
					<td>${dto.getP_name() }</td>
					<td>${dto.getP_birth() }</td>
					<td>${dto.getP_gender() }</td>
					<td>${dto.getP_tel1() } - ${dto.getP_tel2() } - ${dto.getP_tel3() }</td>
					<td>${dto.getP_reg_date() }</td>
				</tr>
			</c:forEach>			
		</table>
		
	</section>
	
	<footer>
		<p>
			HRDKOREA Copyright © All rights Reserved. Human Resources Developement Service of Korea.
		</p>
	</footer>
</body>
</html>