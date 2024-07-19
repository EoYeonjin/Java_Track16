<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("t_name");
	int age = (int)request.getAttribute("t_age");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	성명 : <%=name %><br>
	나이 : <%=age %>
</body>
</html>