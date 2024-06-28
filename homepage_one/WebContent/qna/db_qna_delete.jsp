<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%
	QnaDao dao = new QnaDao();
	String no = request.getParameter("t_no");
	
	int result = dao.deleteQna(no);
	String msg = "질문답변 게시글이 삭제되었습니다";
	
	if(result != 1) msg = "삭제 실패"; 
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="qna_list.jsp";
</script>
</head>
<body>

</body>
</html>