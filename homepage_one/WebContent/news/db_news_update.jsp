<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*"%>
<%
	request.setCharacterEncoding("utf-8");
	NewsDao dao = new NewsDao();

	String no = request.getParameter("t_no");
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");	
	String content = request.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String ipt = request.getParameter("t_ipt");
	
	NewsDto dto = new NewsDto(no, title, content, ipt);
	
	int result = dao.newsUpdate(dto);
	String msg = "NEWS 게시글이 수정되었습니다";
	
	if(result != 1) msg = "수정 실패";
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="news_list.jsp";
</script>
</head>
<body>

</body>
</html>