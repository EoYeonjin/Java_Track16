<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*" %>
<%
	request.setCharacterEncoding("utf-8");
	FaqDao dao = new FaqDao();
	
	String no = dao.getNo();
	String title = request.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String answer_content = request.getParameter("t_answer_content");
	answer_content = answer_content.replace("'", "&#39;");
	String ipt = request.getParameter("t_ipt");
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	FaqDto dto = new FaqDto(no, title, answer_content, reg_id, reg_date, ipt);
	
	int result = dao.insertFaq(dto);
	String msg = "FAQ 게시글이 등록되었습니다";
	
	if(result != 1) msg = "등록 실패";
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