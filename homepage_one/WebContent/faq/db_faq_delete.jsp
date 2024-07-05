<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("utf-8");
	FaqDao dao = new FaqDao();
	
	String no = request.getParameter("t_no");
	
	int result = dao.deleteFaq(no);
	String msg = "FAQ 게시글이 삭제되었습니다";
	
	if(result != 1) msg = "삭제 실패";
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="faq_list.jsp";
</script>
</head>
<body>

</body>
</html>