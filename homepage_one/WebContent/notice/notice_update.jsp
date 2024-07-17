<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	NoticeDao dao = new NoticeDao();
	String no = request.getParameter("t_no");
	NoticeDto dto = dao.getNoticeView(no);
%>    
<%@ include file="../common_header.jsp" %>
<%@ page import="common.*" %>
<% if(!sessionLevel.equals("top")) {%>    
		<script type="text/javascript">
			alert("관리자 화면입니다")
			location.href="../index.jsp";
		</script>
<% } %>
<script type="text/javascript">
	function goUpdate(){
		if(checkValueLength(noti.t_title, 5, 40, "제목을 입력해주세요", '제목은 5자 이상 40자 이내로 입력해주세요\n현재자릿수: ')) return;
		if(checkValueLength(noti.t_content, 1, 800, "내용을 입력해주세요", '내용은 1자 이상 800자 이내로 입력해주세요\n현재자릿수: ')) return;
		
		noti.method="post";
		noti.action="db_notice_update.jsp";
		noti.submit();
	}
	
	function checkDel(){
		if(confirm("첨부파일을 삭제 하시겠습니까?")){
	  		$.ajax({
	  			type:"post",
	  			url :"db_attach_delete.jsp",
	  			data:"t_no="+noti.t_no.value+"&t_attach="+noti.t_delete_attach.value,
	  			dataType:"text",
	  			error:function(){
	  				alert("통신 실패");
	  			},
	  			success:function(data){
	  				var result = $.trim(data);	//공백 사라지게 하는 메소드
	  				if(result == "첨부파일이 삭제 되었습니다.") {
	  					document.getElementById("divAttach").style.display="none";
	  					noti.t_ori_attach.value = "";
	  				}
	  			}
	  		});
		}else document.getElementById("board_img").checked = false;
	}
</script>
<!-- sub contents -->
	<div class="sub_title">
		<h2>공지사항</h2>
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
						<a href="notice_list.jsp">공지사항</a>
						<a href="../news/news_list.jsp">NEWS</a>
						<a href="../qna/qna_list.jsp">질문과답변</a>
						<a href="../faq/faq_list.jsp">FAQ</a>
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
	  <h2 class="sr-only">공지사항 글쓰기</h2>
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	  	<form name="noti" enctype="multipart/form-data">
	  		<input type="hidden" name="t_no" value="<%=no %>">
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
							<textarea name="t_content"><%=content%></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부</th>
						<td colspan="3">
							<input type="file" name="t_attach">
							<%if(!dto.getAttach().equals("첨부파일 없음")){ %>
								<div id="divAttach">
									<img src="../images/file.png" class="board_img">
									<%=dto.getAttach() %>&nbsp;&nbsp;삭제<input type="checkbox" onClick="checkDel()" name="t_delete_attach" class="normal" value="<%=dto.getAttach() %>" id="board_img">
									<input type="hidden" name="t_ori_attach" value="<%=dto.getAttach() %>">
								</div>
							<%}%>
						</td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><%=sessionName %></td>
						<th>등록 일자</th>
						<td><%=dto.getReg_date() %></td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="btn_wrap">
				<input type="button" value="수정저장" class="btn_ok" onclick="goUpdate()">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='notice_list.jsp';">
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