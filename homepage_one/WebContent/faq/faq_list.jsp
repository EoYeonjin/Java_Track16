<%@page import="java.text.DecimalFormat"%>
<%@page import="com.sun.glass.ui.Pixels.Format"%>
<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*, java.util.*" %>
<%
	FaqDao dao = new FaqDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null){
		select = "title";
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

	ArrayList<FaqDto> dtos = dao.getFaqList(select, search, start, end); 
%>    
<%@ include file="../common_header.jsp" %>
<script type="text/javascript">
	
	function goPage(pageNumber){
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="faq_list.jsp";
		pageForm.submit();
	}
	
	function goUpdateForm(no){
		faq.t_no.value = no;
		
		faq.method="post";
		faq.action="faq_update.jsp";
		faq.submit();
	}
	
	function goDelete(no){
		if(confirm("삭제하시겠습니까?")){
			faq.t_no.value = no;
			
			faq.method="post";
			faq.action="db_faq_delete.jsp";
			faq.submit();
		}
	}
	
  	function setHit(no){
  		$.ajax({
  			type:"post",
  			url :"faq_hit.jsp",
  			data:"t_no="+no,
  			dataType:"text",
  			error:function(){
  				alert("통신 실패");
  			},
  			success:function(data){
  				var result = $.trim(data);	//공백 사라지게 하는 메소드
  				const element = document.querySelector('#hit'+no);
  				element.textContent = result;
  			}
  		});
  	}
  	
	
</script>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
	<%
		search = search.replace("\"", "&quot;");
	%>
	<input type="hidden" name="t_select" value="<%=select %>">
	<input type="hidden" name="t_search" value="<%=search %>">
</form>
<form name="faq">
	<input type="hidden" name="t_no">
</form>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>자주하는 질문(FAQ)</h2>
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
					<a href="">자주하는질문<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="../notice/notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="faq_list.jsp">FAQ</a>
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
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span><%=totalCount %></span>건</p>
		</div>
		<div class="search_group">
			<form name="myform" action="">
				<select name="t_select" class="select">
					<option value="title" <%if(select.equals("title")) out.print("selected"); %>>제목</option>
					<option value="answer_content" <%if(select.equals("answer_content")) out.print("selected"); %>>내용</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
				<button class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		
		<div class="faq-group">
			<%for(FaqDto dto: dtos){ %>
				<div class="accordion" id="<%=dto.getNo()%>">
					<table class="table">
						<colgroup>
							<col width="10%">
							<col width="*">
							<col width="15%">
							<col width="10%">
							<col width="10%">
						</colgroup>
						<tr>
							<td>
								<%
									if(dto.getIpt().equals("n")){
										int no = Integer.parseInt(dto.getNo());
										DecimalFormat df = new DecimalFormat("000");
										String str = df.format(no);
										out.print("F"+str);
									}
									else out.print("[중요]");
								%>
							</td>
							<td><%=dto.getTitle() %></td>
							<td><%=dto.getReg_name() %></td>
							<td><%=dto.getReg_date() %></td>
							<td style="padding-left:20px;"><span id="hit<%=dto.getNo() %>"><%=dto.getHit() %></span></td>
						</tr>	
					</table>
				</div>
				<div class="panel">
					<textarea readonly><%=dto.getAnswer_content() %></textarea>
					<%if(sessionLevel.equals("top")){ %>
						<div class="btn_2wrap" style="text-align:center;">
							<a href="javascript:goUpdateForm('<%=dto.getNo()%>')">수정</a>
							<a style="background:#ff3b1f; color:#fff" href="javascript:goDelete('<%=dto.getNo()%>')">삭제</a> 
						</div>
					<%} %>
				</div>
				<%} %>
		</div>


		<script>
			$(function() {
/*			
				$( '.accordion' ).click( function() {
				//$(".accordion").on("click",function() {	
					//$(".panel").slideUp();
					//$(this).next().slideToggle();
					//$(this).next().slideDown();
					$(".panel").not($(this).next().slideToggle()).slideUp();
					//$(this).next().slideDown();
					

				} );
		*/			
			
				$(".accordion").on("click",function() {
					
					if(!$(this).hasClass("active")){
						setHit($(this).attr('id'));
					
					}
					
					$(".panel").not($(this).next().slideToggle()).slideUp();
					$(".accordion").not($(this)).removeClass("active");
					$(this).toggleClass("active");
					
				});
		
			});
			
			
		</script>


		<div class="paging">
		<%
			String pageDis = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
			out.print(pageDis);
		%>
		<!--
			<a href=""><i class="fa  fa-angle-double-left"></i></a>
			<a href=""><i class="fa fa-angle-left"></i></a>
			<a href="" class="active">1</a>
			<a href="">2</a>
			<a href="">3</a>
			<a href="">4</a>
			<a href="">5</a>
			<a href=""><i class="fa fa-angle-right"></i></a>
			<a href=""><i class="fa  fa-angle-double-right"></i></a
		>-->
			<%if(sessionLevel.equals("top")){ %>
				<a href="faq_write.jsp" class="btn_write">글쓰기</a>
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