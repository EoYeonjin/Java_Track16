<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	EmpDao dao = new EmpDao();
	String no = request.getParameter("t_no");
	String name = request.getParameter("t_name");
	String depart = request.getParameter("t_depart");
	String grade = request.getParameter("t_grade");
	String age = request.getParameter("t_age");
	String msg = "";
	String tf = "";
	
	EmpDto dto = dao.checkEmp(no);
	
	if(dto == null){
		msg = "사용가능한 사원번호입니다.";
		tf = "y";
	}else{
		msg = "이미 사용중인 사원번호이므로 사용이 불가능합니다.";
		tf = "/disabled";
	}
%>         
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		alert("<%=msg %>");
		location.href="emp_write.jsp?n_no=<%=no %>&&n_name=<%=name %>&&n_depart=<%=depart %>&&n_grade=<%=grade %>&&n_age=<%=age %>&&tf=<%=tf %>";
	</script>
</head>
<body>

</body>
</html>