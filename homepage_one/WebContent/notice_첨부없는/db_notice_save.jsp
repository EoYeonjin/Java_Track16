<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	NoticeDao dao = new NoticeDao();
	
	String no = dao.getNo();
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = request.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	NoticeDto dto = new NoticeDto(no, title, content, reg_id, reg_date, "");
	
	int result = dao.noticeSave(dto); 
	String msg = "공지사항 등록 되었습니다";
	
	if(result != 1) msg = "공지사항 등록 실패";

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