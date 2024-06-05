<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();
	
	String grade_code = request.getParameter("t_code");
	String grade_name = request.getParameter("t_name");
	
	String msg = "";

	int result = dao.insertGd(new GradeDto(grade_code, grade_name));
		
	if(result == 1) msg = "등록 성공";
	else msg = "등록 실패";
	
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		alert("<%=msg %>");
		location.href="grade_list.jsp";
</script>
</head>
<body>

</body>
</html>