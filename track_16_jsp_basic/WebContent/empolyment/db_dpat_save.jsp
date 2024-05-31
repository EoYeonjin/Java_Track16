<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	DpatDao dao = new DpatDao();
	
	String depart_code = request.getParameter("t_code");
	String depart_name = request.getParameter("t_name");
	
	DpatDto dto = dao.checkDpt(depart_code);
	String msg = "";
	
	if(dto != null) msg = "";
	else{
		int result = dao.insertDpt(new DpatDto(depart_code, depart_name));
		
		if(result == 1) msg = "등록 성공";
		else msg = "등록 실패";
	}
%>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%	if(msg.equals("")){ %>
		alert('해당 부서코드는 이미 존재하므로 등록이 불가합니다.\n다시 입력해주세요');
		location.href="dpat_write.jsp";
		
<%	}else { %>
		alert("<%=msg %>");
		location.href="dpat_list.jsp";
<%	} %>
</script>
</head>
<body>

</body>
</html>