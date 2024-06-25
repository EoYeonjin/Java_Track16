<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	NewsDao dao = new NewsDao();
	
	String no = dao.getNo();
	String ipt = request.getParameter("t_ipt");
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = request.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	NewsDto dto = new NewsDto(no, title, content, reg_id, reg_date, ipt);
	
	int result = dao.newsSave(dto);
	String msg = "News가 등록되었습니다";
	
	if(result != 1) msg = "News 등록 실패";
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