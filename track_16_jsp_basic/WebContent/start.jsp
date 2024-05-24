<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String name = "Hong Gildong";
%>
<%
	int age = 25;
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	JSP 시작~~<br>
<%
	out.print("name: "+name);
	out.print("<br>");
	out.print("age: "+age);
%>
</body>
</html>