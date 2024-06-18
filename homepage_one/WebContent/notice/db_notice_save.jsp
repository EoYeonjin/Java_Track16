<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	NoticeDao dao = new NoticeDao();
	
	String no = dao.getNo();
	String title = request.getParameter("t_title");
	String content = request.getParameter("t_content");
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	NoticeDto dto = new NoticeDto(no, title, content, reg_id, reg_date);
	
	int result = dao.noticeSave(dto); 
	String msg = "";
	
	if(result != 1) msg = "";

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