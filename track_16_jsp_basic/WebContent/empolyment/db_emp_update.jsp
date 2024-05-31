<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>  
<%
	request.setCharacterEncoding("UTF-8");
	EmpDao dao = new EmpDao();
	
	String no = request.getParameter("t_no");
	String name = request.getParameter("t_name");
	String depart = request.getParameter("t_depart");
	String grade = request.getParameter("t_grade");
	int age = Integer.parseInt(request.getParameter("t_age"));
	
	
	int result = dao.updateEmp(no, name, depart, grade, age);

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
		location.href="emp_list.jsp";
	</script>
</head>
<body>

</body>
</html>