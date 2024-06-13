<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>    
<%
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
		
	int count = dao.checkId(id);
	
	if(count != 0) out.print("사용 불가");
	else out.print("사용 가능");
%>
