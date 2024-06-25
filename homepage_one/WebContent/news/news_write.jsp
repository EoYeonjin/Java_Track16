<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.*" %>
<%@ include file="../common_header.jsp" %>
<%if(!sessionLevel.equals("top")){ %>
	<script type="text/javascript">
		alert("관리자 화면입니다");
		location.href="../index.jsp";
	</script>
<%} %>
<script type="text/javascript">
	function goSave(){
		if(checkValueLength(news.t_title, 5, 40,  "제목을 입력해주세요", '제목은 5자 이상 40자 이내로 입력해주세요\n현재자릿수: ')) return;
		if(checkValueLength(news.t_content, 1, 800, "내용을 입력해주세요", '내용은 1자 이상 800자 이내로 입력해주세요\n현재자릿수: ')) return;
		
		news.method="post";
		news.action="db_news_save.jsp";
		news.submit();
	}
</script>
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
						<a href="../notice/notice.jsp">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">NEWS<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="news_list.jsp">NEWS</a>
						<a href="gratings.html">DW인터뷰</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">NEWS 글쓰기</h2>
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	  <form name="news">
			<table class="bord_table">
				<caption class="sr-only">NEWS 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>중요도</th>
						<td colspan="3">
							<input type="radio" class="normal" name="t_ipt" value="n" checked>일반&nbsp;&nbsp;
							<input type="radio" class="normal" name="t_ipt" value="i">중요
						</td>
					</tr>
					<tr>
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
				<input type="button" value="저장" class="btn_ok" onClick="goSave()">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='news_list.jsp';">
			</div>
	  </div>
	  
	</div>
	<!-- end contents -->
	<script>
		function check() {
			if(notice.writer.value=="") {
				alert("글쓴이 입력");
				notice.writer.focus();
				return false;
			}
			if(notice.title.value=="") {
				alert("제목을 입력");
				notice.title.focus();
				return false;
			}
			if(notice.contents.value=="") {
				alert("내용을 입력");
				notice.contents.focus();
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
		<div class="container clearfix">
			<address class="address">
				<p class="title">본사</p>
				(우)12345 대전광역시 중구 계룡로 825 (용두동, 희영빌딩) 5층,6층/고객센터: 042-242-4412 	사업자등록번호: 305-86-06709
			</address>
			<p class="copyright">Copyright &copy JSL 인재개발원주식회사. All rights reserved.</p>
		</div>
</footer>

 </body>
</html>