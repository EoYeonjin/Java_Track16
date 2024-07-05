<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>    
<%
	FaqDao dao = new FaqDao();
	String no = request.getParameter("t_no");
		
	int result = dao.updateHit(no);
	int	hit = dao.getHit(no);
	
	if(result == 1) out.print(hit);
%>