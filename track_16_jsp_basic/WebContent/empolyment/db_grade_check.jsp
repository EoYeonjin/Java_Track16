<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();
	String grade_code = request.getParameter("t_code");
	String grade_name = request.getParameter("t_name");
	String msg = "";
	String tf = "";
	
	GradeDto dto = dao.checkGd(grade_code);
	
	if(dto == null){
		msg = "사용가능한 직급코드입니다.";
	}else{
		msg = "이미 사용중인 직급코드이므로 사용이 불가능합니다.";
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
		location.href="dpat_write.jsp?new_code=<%=grade_code %>&&new_name=<%=grade_name %>&&tf=<%=tf %>";
	</script>
</head>
<body>

</body>
</html>