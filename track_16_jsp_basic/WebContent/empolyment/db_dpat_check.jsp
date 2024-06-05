<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();
	String depart_code = request.getParameter("t_code");
	String depart_name = request.getParameter("t_name");
	String msg = "";
	String tf = "";
	
	DpatDto dto = dao.checkDpt(depart_code);
	
	if(dto == null){
		msg = "사용가능한 부서코드입니다.";
	}else{
		msg = "이미 사용중인 부서코드이므로 사용이 불가능합니다.";
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
		location.href="grade_write.jsp?new_code=<%=depart_code %>&&new_name=<%=depart_name %>&&tf=<%=tf %>";
	</script>
</head>
<body>

</body>
</html>