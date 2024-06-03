<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>     
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();

	String grade_code = request.getParameter("t_code");
	String grade_name = request.getParameter("t_name");
	
	
	int result = dao.updateGd(grade_code, grade_name);

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
		location.href="grade_list.jsp";
	</script>
</head>
<body>

</body>
</html>