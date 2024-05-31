<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>  
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();

	String depart_code = request.getParameter("t_code");
	String depart_name = request.getParameter("t_name");
	
	
	int result = dao.updateDpt(depart_code, depart_name);

	String msg = "수정 성공";
	if(result != 1) msg = "수정 실패";
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		alert("<%=msg %>");
		location.href="dpat_list.jsp";
	</script>
</head>
<body>

</body>
</html>