<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, common.*,java.io.*" %>
<% 
	request.setCharacterEncoding("utf-8");
	PdsDao dao = new PdsDao();
	
	String no = request.getParameter("t_no");
	String attach = request.getParameter("t_attach");
	
	int result = 0;
	
	File file = new File(CommonUtil.getPdsDir(), attach);
	boolean tf = file.delete();
	if(tf) {
		result = dao.pdsAttachDelete(no);
	}else System.out.print("Ajax 첨부파일 삭제 오류 db_attach_delete.jsp");
	
	String msg = "첨부파일이 삭제 되었습니다.";
	if(result != 1) msg = "첨부파일 삭제 실패 db_attach_delete.jsp";
	
	out.print(msg);
%>
