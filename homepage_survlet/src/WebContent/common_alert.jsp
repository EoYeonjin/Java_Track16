<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<%
	//String msg = (String)request.getAttribute("t_msg");
	//String url = (String)request.getAttribute("t_url");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<form name="alertForm">
	<input type="hidden" name="t_gubun">
</form>
<c:if test="${t_gubun eq 'myinfo' }">
	<script type="text/javascript">
		alert("${t_msg}");
		alertForm.t_gubun.value="${t_gubun}";
		alertForm.method="post";
		alertForm.action="${t_url }";
		alertForm.submit();
	</script>
</c:if>
<c:if test="${empty t_gubun }">
	<script type="text/javascript">
		alert("${t_msg}");
		location.href="${t_url}";
	</script>
</c:if>
</body>
</html>