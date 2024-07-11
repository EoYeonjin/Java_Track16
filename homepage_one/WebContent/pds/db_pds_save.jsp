<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*, java.io.File" %> 
<%
	PdsDao dao = new PdsDao();
	
	//attachment
	int maxSize = 1024 * 1024 * 10;
	String pds_dir=CommonUtil.getPdsDir();
	
	MultipartRequest mpr = new MultipartRequest(request, pds_dir, maxSize,"utf-8", new DefaultFileRenamePolicy());
	
	String no = dao.getNo(); 
	String title = mpr.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = mpr.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String attach = mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	PdsDto dto = new PdsDto(no, title, content, attach, reg_id, reg_date);
	
	int result = dao.insertPds(dto); 
	
	if(result != 1 && !attach.equals("")){
		File file = new File(pds_dir, attach);
		boolean fileDel = file.delete();
		if(!fileDel) System.out.print("자료실 등록 오류 시 첨부파일 삭제. db_pds_save.jsp");
	}
	
	String msg = "자료실 게시글 등록 되었습니다";
	
	if(result != 1) msg = "등록 실패";
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