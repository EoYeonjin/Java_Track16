<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="sun.nio.ch.DefaultAsynchronousChannelProvider"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*, java.io.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	NoticeDao dao = new NoticeDao();
	
	//attachment
	int maxSize = 1024 * 1024 * 10;
	
	MultipartRequest mpr = new MultipartRequest(request, CommonUtil.getNoticeDir(), maxSize, "utf-8", new DefaultFileRenamePolicy());
	
	String no = mpr.getParameter("t_no");
	String title = mpr.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = mpr.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String attach = mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	
	String saveAttachName = "";
	
	String ori_attach = mpr.getParameter("t_ori_attach");
	if(ori_attach == null) ori_attach = "";
	
	boolean delFile = false;
	
	//새로 첨부 했으면
	if(!attach.equals("")){
		if(ori_attach != null && !delFile){
			File file = new File(CommonUtil.getNoticeDir(), ori_attach);
			boolean tf = file.delete();
			if(!tf) System.out.print("자료실 수정 첨부파일 삭제 오류 - 새로 첨부시 파일 삭제 db_pds_update.jsp");
		}
		saveAttachName = attach;
	}else saveAttachName = ori_attach;
	
	NoticeDto dto = new NoticeDto(no, title, content, saveAttachName, "", "");
	
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