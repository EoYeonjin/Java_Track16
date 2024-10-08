<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<%@ page import="dto.*, java.util.*" %> 
<%
/**	request.setCharacterEncoding("utf-8");

	ArrayList<MemberDto> dtos = (ArrayList<MemberDto>)request.getAttribute("t_dtos");

	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
	
	if(select == null) {
		select = "id";
		search = "";
	}**/
%>   
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : Track16 홍길동
	******************************************** 
 -->	
	<title>Track16 홍길동</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
</head>
<body>
	<script type="text/javascript">
		function goSearch(){
			mem.method="post";
			mem.action="MemberList";
			mem.submit();
		}
		
		function goView(id){
			viewForm.t_id.value = id;
			viewForm.method="post";
			viewForm.action="MemberView";
			viewForm.submit();
		}
	</script>
	<form name="viewForm">
		<input type="hidden" name="t_id">
	</form>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>Track16 홍길동 회원관리</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span>${t_dtos.size() }</span>건</p>
			</div>
			<form name="mem">
				<div class="search_group">
					<select class="select" name="t_select">
						<option value="id"  <c:if test="${t_select eq 'id'}">selected</c:if>>ID</option>
						<option value="name" <c:if test="${t_select eq 'name'}">selected</c:if>>성명</option>
					</select>
					<input type="text" class="search_word" name="t_search" value="${t_search }">
					<button class="btn_search" onClick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
				</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>ID</th>
					<th>성명</th>
					<th>지역</th>
					<th>나이</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="dto" items="${t_dtos}">
				<tr>
					<td><a href="MemberView?t_id=${dto.getId() }">${dto.getId() }</a></td>
					<td><a href="javascript:goView('${dto.getId() }')">${dto.getName() }</a></td>
					<td>${dto.getArea_name() }</td>
					<td>${dto.getAge() }</td>
				</tr>	
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<a href="MemberWrite" class="write">회원등록</a>
		</div>
	</div>
 </body>
</html>