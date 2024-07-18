<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.*" %>    
<%@ include file="../common_header.jsp" %>
<%if(sessionName == ""){ %>
	<script type="text/javascript">
		alert("로그인 후 이용 가능합니다");
		location.href="qna_list.jsp";
	</script>
<%} %>
<script type="text/javascript">
	function goSave(){
		if(checkValueLength(saveForm.t_title, 5, 40, "제목을 입력해주세요", '제목은 5자 이상 40자 이내로 입력해주세요\n현재자릿수: ')) return;
		if(checkValueLength(saveForm.t_content, 1, 800, "내용을 입력해주세요", '내용은 1자 이상 800자 이내로 입력해주세요\n현재자릿수: ')) return;
		
		saveForm.method="post";
		saveForm.action="db_qna_save.jsp";
		saveForm.submit()
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
	  <form name="saveForm">
			<table class="bord_table">
				<caption class="sr-only">질문과 답변 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>제목</th>
						<td colspan="3"><input type="text" name="t_title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea name="t_content"></textarea></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><%=sessionName %></td>
						<th>등록 일자</th>
						<td><%=CommonUtil.getToday() %></td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn_wrap">
				<input type="button" value="저장" class="btn_ok" onClick="javascript:goSave()">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
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