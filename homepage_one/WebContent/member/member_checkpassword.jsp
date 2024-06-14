<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>    
<%
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	String password = request.getParameter("t_password");
	
	int count = dao.checkPassword(id, password);
	
	if(count == 1) out.print("yes");
	else out.print("no");
%>    