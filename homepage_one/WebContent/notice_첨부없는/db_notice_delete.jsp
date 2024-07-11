<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	NoticeDao dao = new NoticeDao();
	
	String no = request.getParameter("t_no");
	
	int result = dao.noticeDelete(no);
	String msg = "게시글 삭제 되었습니다";
	
	if(result != 1) msg = "게시글 삭제 실패";

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="notice_list.jsp";
</script>
</head>
<body>

</body>
</html>