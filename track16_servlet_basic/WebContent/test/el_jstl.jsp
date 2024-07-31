<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
======== el 표현식 =======<br>
name : ${t_name} <br>
age : ${t_age} <br>
arr size : ${t_arr.size()} <br>
arr 0번째 : ${t_arr[0]} <br>

======= jstl 표현식 ======<br>
<c:if test="${t_name == '홍길동' }"> 1. 홍길동 <br> </c:if>
<c:if test="${t_name eq '홍길동' }"> 2. 홍길동 <br> </c:if>
<c:if test="${t_name != '이상민' }"> 3. 홍길동 <br> </c:if>
<c:if test="${t_name ne '이상민' }"> 4. 홍길동 <br> </c:if>

<c:if test="${t_age == 25 }"> 5. same age <br> </c:if>
<c:if test="${t_age >= 20 }"> 6. older or same <br> </c:if>

<c:if test="${t_name eq '홍길동' or t_age >= 30 }"> 7. or <br> </c:if>
<c:if test="${t_name eq '홍길동' || t_age >= 30 }"> 8. or <br> </c:if>
<c:if test="${t_name eq '홍길동' and t_age >= 20 }"> 9. and <br> </c:if>
<c:if test="${t_name eq '홍길동' && t_age >= 20 }"> 10. and <br> </c:if>

<c:choose>
	<c:when test="${t_age >= 40 }">older than 40 <br></c:when>
	<c:when test="${t_age >= 30 }">older than 30 <br></c:when>
	<c:when test="${t_age >= 20 }">older than 20 <br></c:when>
	<c:otherwise>others <br></c:otherwise>
</c:choose>	
======= jstl 표현식 반복문 for ======<br>
<c:forEach items="${t_arr}" var="arr">
	${arr} <br>
</c:forEach>
======= dtos ======<br>
<c:forEach items="${t_dtos}" var="dtos">
	${dtos.getID()} ${dtos.getName()} ${dtos.getArea()} ${dtos.get()} <br>
</c:forEach>
</body>
</html>