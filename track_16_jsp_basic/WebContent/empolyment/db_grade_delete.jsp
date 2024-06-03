<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>      
<%
	request.setCharacterEncoding("UTF-8");
	GradeDao dao = new GradeDao();

	String grade_code = request.getParameter("t_code");
	
	GradeDto dto = dao.checkUsage(grade_code);
	String msg = "";
	
	if(dto != null) msg = "";
	else if(dto == null){
		int result = dao.deleteGd(grade_code);
		
		msg = "삭제 성공";
		if(result != 1) msg = "삭제 실패";
	}
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	<%	if(msg.equals("")){ %>
		alert('해당 직급코드의 사원이 존재하므로 삭제가 불가합니다.');
		location.href="grade_list.jsp";

	<%	}else { %>
		alert("<%=msg %>");
		location.href="grade_list.jsp";
	<%	} %>
</script>
</head>
<body>

</body>
</html>