<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
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
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">질문과 답변 글쓰기</h2>
	  <form name="notice" method="post" action="qa_reply.html">
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>글쓴이</th>
						<td>홍길동</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>입학절차가 궁금합니다</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>입학하기를 희망하기에 입학절차와 필요한 서류가 무엇인지 자세히 알려주세요</td>
					</tr>
					<tr>
						<th>첨부</th>
						<td><img src="" alt=""></td>
					</tr>
					<tr>
						<th>답변</th>
						<td><textarea name="reply"></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="submit" value="저장" class="btn_ok">&nbsp;&nbsp;<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;<input type="button" value="목록" class="btn_list" onClick="location.href='qa.html';">
			</div>
		</form>
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
