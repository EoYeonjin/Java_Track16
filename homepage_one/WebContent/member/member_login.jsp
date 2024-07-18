<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>

	<!-- sub contents -->
	<div class="sub_title">
		<h2>접속자 로그인</h2>
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
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
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
	<script>
	//로그인
	function fn_login(){
  		if(checkValue(mem.t_id, "아이디를 입력해주세요")) return;
  		if(checkValue(mem.t_password, '비밀번호를 입력해주세요')) return;
		
  		mem.method="post";
		mem.action="db_member_login.jsp";
		mem.submit();
	}
  	
	//엔터시 비밀번호 포커스
  	function checkEnter(){
  		var keyValue = event.keyCode;
  		if(keyValue == 13){
  			mem.t_password.focus();
  			
  		}
  	}
  	
	//엔터시 로그인
  	function goSubmit(){
  		var keyValue = event.keyCode;
  		if(keyValue == 13){
  			fn_login();
  		}
  	}
  	</script>
	<div class="container">
			<div class="member_boxL">
                <h2>개인회원</h2>
                <div class="login_form">
                    <form name="mem" id="frmLogin">
                    <div class="fl_clear"><label for="mbrId">아이디</label><input name="t_id" autofocus onkeypress="checkEnter()" id="mbrId" type="text"></div>
                    <div class="fl_clear"><label for="scrtNo">비밀번호</label><input name="t_password" onkeypress="goSubmit()" id="scrtNo" type="password"></div>
                    <a class="btn_login btn_Blue" href="javascript:fn_login();">로그인</a>
                    </form>
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