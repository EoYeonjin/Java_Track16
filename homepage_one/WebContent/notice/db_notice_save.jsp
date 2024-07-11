<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*, java.io.File" %>
<%
	NoticeDao dao = new NoticeDao();
	
	//attachment
	int maxSize = 1024 * 1024 * 10;
	String noti_dir = CommonUtil.getNoticeDir();
	
	MultipartRequest mpr = new MultipartRequest(request, noti_dir, maxSize, "utf-8", new DefaultFileRenamePolicy());

	String no = dao.getNo();
	String title = mpr.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = mpr.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String attach = mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	String reg_id = (String)session.getAttribute("sessionId");
	String reg_date = CommonUtil.getTodayTime();
	
	NoticeDto dto = new NoticeDto(no, title, content, attach, reg_id, reg_date);
	
	int result = dao.noticeSave(dto);
	
	if(result != 1 && !attach.equals("")){
		File file = new File(noti_dir, attach);
		boolean fileDel = file.delete();
		if(!fileDel) System.out.print("공지사항 등록 오류시 첨부파일 삭제. db_notice_save.jsp");
	}
	
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