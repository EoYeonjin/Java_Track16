<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.*, dto.*, java.util.*"%>
<%
	AdmDao dao = new AdmDao();
	ArrayList<AdmDto> dtos = dao.getAdmList();
%>
<%@ include file="../common_header.jsp" %>
<%if(!sessionLevel.equals("top")){ %>  
	<script type="text/javascript">
		alert("관리자 화면입니다.");
		location.href="../index.jsp";
	</script>
<%}%>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>회원 목록</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="../index.jsp"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">관리자<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="../faq/faq_list.jsp">FAQ</a>
						<a href="../pds/pds_list.jsp">자료실</a>
						<a href="adm_list.jsp">관리자</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span><%=dtos.size() %></span>건</p>
		</div>
		<div class="search_group">
			<form name="noti" method="post" action="notice_list.jsp">
				<select name="t_select" class="select">
					<option value="title" <% out.print("selected"); %>>제목</option>
					<option value="content" <% out.print("selected"); %>>내용</option>
				</select>
				<input type="text" name="t_search" value="" class="search_word">
				<button class="btn_search" type="submit"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<caption class="sr-only">공지사항 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="*">
				<col width="*">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>탈퇴여부</th>
				</tr>
			</thead>
			<tbody>
			<%for(AdmDto dto: dtos){ %>
				<tr>
					<td><%=dto.getNo() %></td>
					<td class="title"><a href="javascript:goView('')"><%=dto.getId() %></a></td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getEmail_1() %>@<%=dto.getEmail_2() %></td>
					<td><%=dto.getMobile_1() %>-<%=dto.getMobile_2() %>-<%=dto.getMobile_3() %></td>
					<%if(dto.getExit_date() == null){ %>
						<td></td>
					<%} else { %>
						<td><img src=""></td>
					<%} %>	
				</tr>
			<%} %>	
			</tbody>
		</table>
		<div class="paging">

		<!--  
			<a href=""><i class="fa  fa-angle-double-left"></i></a>
			<a href=""><i class="fa fa-angle-left"></i></a>
			<a href="" class='active'>1</a>
			<a href='notice_list.jsp'>2</a>
			<a href='notice_list.jsp'>3</a>
			<a href='notice_list.jsp'>4</a>
			<a href="">5</a>
			<a href=""><i class="fa fa-angle-right"></i></a>
			<a href=""><i class="fa  fa-angle-double-right"></i></a>
		-->	
			
		</div>
	  </div>
	</div>
	<!-- end contents -->
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

	<footer class="footer">
		<%@ include file="../common_footer.jsp" %>
	</footer>

 </body>
</html>