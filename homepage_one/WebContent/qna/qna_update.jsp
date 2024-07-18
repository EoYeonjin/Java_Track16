<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	QnaDao dao = new QnaDao();
	String no = request.getParameter("t_no");
	
	QnaDto dto = dao.getQnaView(no);
%>    
<%@ include file="../common_header.jsp" %>
<script>
	function goUpdate(){
		qna.method="post";
		qna.action="db_qna_update.jsp";
		qna.submit();
	}
</script>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>질문답변</h2>
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
					<a href="">질문답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="qna_list.jsp">질문과답변</a>
						<a href="../faq/faq_list.jsp">FAQ</a>
						<a href="../pds/pds_list.jsp">자료실</a>
						<%if(sessionLevel.equals("top")){ %>
							<a href="../adm/adm_list.jsp">관리자</a>
						<%} %>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">질문과 답변 글쓰기</h2>
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	   <form name="qna">
	   <input type="hidden" name="t_no" value="<%=dto.getNo() %>">
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>제목</th>
						<td colspan="3">
							<%
								String title = dto.getTitle();
								title = title.replace("\"", "&quot;");
							%>
							<input type="text" name="t_title" value="<%=title %>">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<%
								String content = dto.getContent();
								content = content.replace("\"", "&quot;");
							%>
							<textarea name="t_content"><%=dto.getContent() %></textarea>
						</td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><%=dto.getReg_name() %></td>
						<th>등록 일자</th>
						<td><%=dto.getReg_date() %></td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn_wrap">
				<input type="button" value="수정저장" class="btn_ok" onClick="javascript:goUpdate()">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='qna_list.jsp';">
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
