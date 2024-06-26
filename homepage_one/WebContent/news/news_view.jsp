<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>    
<%
	NewsDao dao = new NewsDao();

	String no = request.getParameter("t_no");
	String ipt = request.getParameter("t_ipt");
	
	int hitCount = dao.updateHit(no);
	if(hitCount != 1) System.out.print("NEWS 조회수 증가 오류");
	NewsDto dto = dao.getNewsView(no);
	
	NewsDto preDto = dao.getPreNews(no, ipt);
	NewsDto nexDto = dao.getNextNews(no, ipt);
%>
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">
	function goUpdateForm(){
		news.method="post";
		news.action="news_update.jsp";
		news.submit();
	}
	
	function goDelete(){
		if(confirm("삭제하시겠습니까?")){
			news.method="post";
			news.action="db_news_delete.jsp";
			news.submit();
		}
	}
	
	function goView(no, ipt){
		news.t_no.value = no;
		news.t_ipt.value = ipt;
		
		news.method="post";
		news.action="news_view.jsp";
		news.submit();
	}
</script>
<form name="news">
	<input type="hidden" name="t_no" value="<%=no %>">
	<input type="hidden" name="t_ipt" value="<%=ipt %>">
</form>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>NEWS</h2>
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
					<a href="">NEWS<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="news_list.jsp">NEWS</a>
						<a href="gratings.html">질문답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="board_view">
			<h2><%=dto.getTitle() %></h2>
			<p class="info"><span class="user"><%=dto.getReg_name() %></span> | <%=dto.getReg_date() %> | <i class="fa fa-eye"></i> <%=dto.getHit() %></p>
			<div class="board_body">
				<textarea readonly><%=dto.getContent() %></textarea>
			</div>
			<div class="prev_next">
			<%if(preDto != null){ %>
				<a href="javascript:goView('<%=preDto.getNo() %>', '<%=preDto.getIpt() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong>이전글</strong>
					<span>
						<%
							String preTitle = preDto.getTitle();
							if(preTitle.length() > 15) preTitle = preTitle.substring(0, 15) +"...";
							out.print(preTitle);
						%>
					</span>
				</span>
				</a>
				<%} else{ %>
					<a></i>
					<span class="prev_wrap">
						<span>이전글은 존재하지 않습니다</span>
					</span>
					</a>
				<%} %>
					<div class="btn_3wrap">
						<a href="news_list.jsp">목록</a> 
						<%if(sessionLevel.equals("top")){ %>
						<a href="javascript:goUpdateForm()">수정</a> 
						<a href="javascript:goDelete()">삭제</a>
						<%} %>
					</div>
				<%if(nexDto != null){ %>
				<a href="javascript:goView('<%=nexDto.getNo() %>', '<%=nexDto.getIpt() %>')" class="btn_next">
				<span class="next_wrap">
					<strong>다음글</strong>
					<span>
						<%
							String nextTitle = nexDto.getTitle(); 
							if(nextTitle.length() > 15) nextTitle = nextTitle.substring(0, 15) + "...";
							out.print(nextTitle);
						%>
					</span>
				</span>
				<i class="fa fa-angle-right"></i></a>
				<%} else{ %>
					<a class="btn_next">
					<span class="next_wrap">
						<span>다음글은 존재하지 않습니다.</span>
					</span>
					</a>
				<%} %>
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