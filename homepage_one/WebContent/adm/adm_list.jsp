<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.*, dto.*, java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	AdmDao dao = new AdmDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null){
		select = "id";
		search = "";
	}
	
	search = search.replace("'", "&#39;");
	
	/* paging 설정 start*/
	int totalCount = dao.getTotalCount(select,search);
	int list_setup_count = 4;  //한페이지당 출력 행수 
	int pageNumber_count = 3;  //한페이지당 출력 페이지 갯수
	
	String nowPage = request.getParameter("t_nowPage");
	int current_page = 0; // 현재페이지 번호
	int total_page = 0;    // 전체 페이지 수
	
	if(nowPage == null || nowPage.equals("")) current_page = 1; 
	else current_page = Integer.parseInt(nowPage);
	
	total_page = totalCount / list_setup_count;  // 몫 : 2
	int rest = 	totalCount % list_setup_count;   // 나머지:1
	if(rest !=0) total_page = total_page + 1;     // 3
	
	int start = (current_page -1) * list_setup_count + 1;
	int end   = current_page * list_setup_count;
	/* paging 설정 end*/
		
	ArrayList<AdmDto> dtos = dao.getAdmList(select, search, start, end);
%>
<%@ include file="../common_header.jsp" %>
<%if(!sessionLevel.equals("top")){ %>  
	<script type="text/javascript">
		alert("관리자 화면입니다.");
		location.href="../index.jsp";
	</script>
<%}%>
<script type="text/javascript">
	function goView(id){
		viewForm.t_id.value = id
		viewForm.method="post";
		viewForm.action="adm_view.jsp";
		viewForm.submit();
	}
	
	function goPage(pageNumber){
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="adm_list.jsp";
		pageForm.submit();
	}
</script>
<form name="viewForm">
	<input type="hidden" name="t_id">
</form>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
	<%
		search = search.replace("\"", "&quot;");
	%>
	<input type="hidden" name="t_select" value="<%=select %>">
	<input type="hidden" name="t_search" value="<%=search %>">
</form>
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
			<p>총회원수<span><%=totalCount %></span>명</p>
		</div>
		<div class="search_group">
			<form name="noti" method="post" action="adm_list.jsp">
				<select name="t_select" class="select">
					<option value="id" <%if(select.equals("id")) out.print("selected"); %>>ID</option>
					<option value="name" <%if(select.equals("name")) out.print("selected"); %>>이름</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
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
				<col width="*">
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
					<td style="text-align:center"><a href="javascript:goView('<%=dto.getId() %>')"><%=dto.getId() %></a></td>
					<td><a href="javascript:goView('<%=dto.getId() %>')"><%=dto.getName() %></a></td>
					<td><%=dto.getEmail_1() %>@<%=dto.getEmail_2() %></td>
					<td><%=dto.getMobile_1() %> - <%=dto.getMobile_2() %> - <%=dto.getMobile_3() %></td>
					<%if(dto.getExit_date() == null){ %>
						<td></td>
					<%} else { %>
						<td><img src="../images/delete-user.png"  class="img_exit"></td>
					<%} %>	
				</tr>
			<%} %>	
			</tbody>
		</table>
		<div class="paging">
		<%
			String pageDis = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
			out.print(pageDis);
		%>
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