<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	request.setCharacterEncoding("utf-8");
	QnaDao dao = new QnaDao();
	
	String no = request.getParameter("t_no");
	String answer_content = request.getParameter("t_answer_content");
	answer_content = answer_content.replace("'", "&#39;");
	String answer_state = request.getParameter("t_answer_state");
	
	QnaDto dto = new QnaDto(no, answer_content);
	
	int result = dao.updateAnswer(dto);
	String msg = "질문답변 게시글의 답변이 등록되었습니다.";
	
	if(answer_state.equals("complet")) msg = "질문답변 게시글의 답변이 수정되었습니다";
	if(result != 1) msg = "답변 등록 실패";
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