<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>      
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();

	String depart_code = request.getParameter("t_code");
	
	EmpDto dto = dao.checkUsage(depart_code);
	String msg = "";
	
	if(dto != null) msg = "";
	else if(dto == null){
		int result = dao.deleteDpt(depart_code);
		
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
		alert('해당 부서코드의 사원이 존재하므로 삭제가 불가합니다.');
		location.href="dpat_view.jsp";

	<%	}else { %>
		alert("<%=msg %>");
		location.href="dpat_list.jsp";
	<%	} %>
</script>
</head>
<body>

</body>
</html>