<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*, java.io.*" %>
<%
	request.setCharacterEncoding("utf-8");
	PdsDao dao = new PdsDao();
	
	String no = request.getParameter("t_no");
	String attach = request.getParameter("t_delAttach");
	
	int result = 0;
	String msg = "자료실 게시글이 삭제되었습니다";
	
	if(!attach.equals("첨부파일 없음")) {
		File file = new File(CommonUtil.getPdsDir(), attach);
		boolean tf = file.delete();
		
		if(tf) result = dao.deletePds(no);
		else System.out.print("첨부파일 삭제 실패 db_pds_delete.jsp");
		
	}else result = dao.deletePds(no);
	
	if(result != 1) msg = "삭제 실패";
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="pds_list.jsp";
</script>
</head>
<body>

</body>
</html>