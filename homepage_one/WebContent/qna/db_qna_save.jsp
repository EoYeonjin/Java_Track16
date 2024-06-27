<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	QnaDao dao = new QnaDao();
	
	String no = dao.getNo();
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = request.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	String answer_state = "waiting";
	
	QnaDto dto = new QnaDto(no, title, content, reg_id, reg_date, answer_state);	
	
	int result = dao.insertQna(dto);
	String msg = "질문답변 게시글이 등록되었습니다";
	
	if(result != 1) msg = "등록 실패";
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