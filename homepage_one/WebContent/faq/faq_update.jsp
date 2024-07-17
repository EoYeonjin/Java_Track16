<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>    
<%
	FaqDao dao = new FaqDao();

	String no = request.getParameter("t_no");
	
	FaqDto dto = dao.getFaqView(no);
%>
<%@ include file="../common_header.jsp" %>
<%if(!sessionLevel.equals("top")){ %>
	<script type="text/javascript">
		alert("관리자 화면입니다");
		location.href="faq_list.jsp";
	</script>
<%} %>
<script type="text/javascript">
	function goUpdate(){
		faq.method="post";
		faq.action="db_faq_update.jsp";
		faq.submit();
	}
</script>	
	<!-- sub contents -->
	<div class="sub_title">
		<h2>자주하는 질문(FAQ)</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
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
					<a href="">자주하는질문<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="faq_list.jsp">FAQ</a>
						<a href="../pds/pds_list.jsp">자료실</a>
						<%if(sessionLevel.equals("top")){ %>
							<li><a href="../adm/adm_list.jsp">관리자</a></li>
						<%} %>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">글쓰기</h2>
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	  <form name="faq">
	  	<input type="hidden" name="t_no" value="<%=no %>">
			<table class="bord_table">
				<caption class="sr-only">자주하는 질문 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>중요도</th>
						<td colspan="3">
							<input type="radio" class="normal" name="t_ipt" value="n" <%if(dto.getIpt().equals("n")) out.print("checked"); %>>일반&nbsp;&nbsp;
							<input type="radio" class="normal" name="t_ipt" value="i" <%if(dto.getIpt().equals("i")) out.print("checked"); %>>중요
						</td>
					</tr>
					<tr>
						<th>질문</th>
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
								String answer_content = dto.getAnswer_content();
								answer_content = answer_content.replace("\"", "&quot;");
							%>
							<textarea name="t_answer_content"><%=answer_content %></textarea>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><%=dto.getReg_name() %></td>
						<th>등록일자</th>
						<td><%=dto.getReg_date() %></td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn_wrap">
				<input type="button" value="수정저장" class="btn_ok" onClick="javascript:goUpdate()">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='faq_list.jsp';">
			</div>
	  </div>
	  
	</div>
	<!-- end contents -->
	<script>
		function check() {
			
			if(faq.title.value=="") {
				alert("제목을 입력");
				faq.title.focus();
				return false;
			}
			if(faq.contents.value=="") {
				alert("내용을 입력");
				faq.contents.focus();
				return false;
			}
			return true;
		}
	</script>
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