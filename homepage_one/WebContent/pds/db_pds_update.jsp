<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="sun.nio.ch.DefaultAsynchronousChannelProvider"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, common.*, java.io.File" %>
<%
	PdsDao dao = new PdsDao();
	
	//attachment
	int maxSize = 1024 * 1024 * 10;
	
	MultipartRequest mpr = new MultipartRequest(request, CommonUtil.getPdsDir(), maxSize, "utf-8", new DefaultFileRenamePolicy());
	
	String no = mpr.getParameter("t_no");
	String title = mpr.getParameter("t_title");
	title = title.replace("'", "&#39;");
	String content = mpr.getParameter("t_content");
	content = content.replace("'", "&#39;");
	String attach = mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	
	String saveAttachName = "";
	
	String delete_attach = mpr.getParameter("t_delete_attach");
	String ori_attach = mpr.getParameter("t_ori_attach");
	
	//기존 파일 삭제
	boolean delFile = false;
	if(delete_attach!= null) {
		File file = new File(CommonUtil.getPdsDir(), delete_attach);
		delFile = file.delete();
		if(!delFile) System.out.print("자료실 수정 첨부파일 삭제 오류 - 기존 파일 삭제 db_pds_update.jsp");
	}else saveAttachName = ori_attach;
	
	//새로 첨부 했으면
	if(!attach.equals("")){
		if(ori_attach != null && !delFile){
			File file = new File(CommonUtil.getPdsDir(), ori_attach);
			boolean tf = file.delete();
			if(!tf) System.out.print("자료실 수정 첨부파일 삭제 오류 - 새로 첨부시 파일 삭제 db_pds_update.jsp");
		}
		saveAttachName = attach;
	}
	
	String update_id = (String)session.getAttribute("sessionId");
	String update_date = CommonUtil.getTodayTime();
	
	PdsDto dto = new PdsDto(no, title, content, attach, "", "", update_id, update_date);
	int result = dao.updatePds(dto); 
	String msg = "자료실 게시글이 수정되었습니다";
	
	if(result != 1) msg = "수정 실패";

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