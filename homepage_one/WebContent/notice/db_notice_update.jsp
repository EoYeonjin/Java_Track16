<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	NoticeDao dao = new NoticeDao();
	
	String no = request.getParameter("t_no");
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = request.getParameter("t_content");
	content = content.replace("'", "&#39;");
	
	NoticeDto dto = new NoticeDto(no, title, content, "", "");
	
	int result = dao.noticeUpdate(dto); 
	String msg = "공지사항 수정 되었습니다";
	
	if(result != 1) msg = "공지사항 수정 실패";

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