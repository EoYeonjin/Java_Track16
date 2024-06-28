<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*, dto.*" %>
<%
	QnaDao dao = new QnaDao();
	String no = request.getParameter("t_no");
	String answer_state = request.getParameter("t_answer_state");
	
	int hitCount = dao.updateHit(no);
	if(hitCount != 1) System.out.print("질문답변 조회수 증가 오류");
	String answer_content = "";
	String answer_msg = "Answer Save";
	
	QnaDto dto = dao.getQnaView(no);
	if(answer_state.equals("complet")){
		answer_content = dao.getAnswerContent(no);
		answer_msg = "Answer Modify";
	}
	
	QnaDto preDto = dao.getPreQna(no);
	QnaDto nextDto = dao.getNextQna(no);
%>    
<%@ include file="../common_header.jsp" %>	
<script type="text/javascript">
	function goUpdateForm(no){
		<%if(sessionName.equals(dto.getReg_name())){  %>
			<%if(answer_state.equals("waiting")){ %>
				qna.t_no.value = no;
				
				qna.method="post";
				qna.action="qna_update.jsp";
				qna.submit();
			<%}else { %>
				alert("답변이 작성되어 있어 수정이 불가합니다");
			<%} %>
		<%}else { %>
			alert("작성한 본인만 수정 가능합니다");
		<%} %>
	}
	
	function goDelete(no){
		if(confirm("삭제하시겠습니까?")){
			<%if(sessionName.equals(dto.getReg_name()) || sessionLevel.equals("top")){  %>
				<%if(answer_state.equals("waiting") || sessionLevel.equals("top")){ %>
					qna.t_no.value = no;
					
					qna.method="post";
					qna.action="db_qna_delete.jsp";
					qna.submit();
				<%}else { %>
					alert("답변이 작성되어 있어 수정이 불가합니다");
				<%} %>	
			<%}else { %>
				alert("작성한 본인만 삭제 가능합니다");
			<%} %>
		}
	}
	
	function goAnswerSave(){
		if(checkValueLength(answerForm.t_answer_content, 1, 800, "내용을 입력해주세요", '내용은 1자 이상 800자 이내로 입력해주세요\n현재자릿수: ')) return;
		
		answerForm.method="post";
		answerForm.action="db_answer_save.jsp";
		answerForm.submit();
	}
	
	function goView(no, answer_state){
		viewForm.t_no.value = no;
		viewForm.t_answer_state.value = answer_state;
		
		viewForm.method="post";
		viewForm.action="qna_view.jsp";
		viewForm.submit();
	}
	
	function goAnswerDelete(){
		answerForm.method="post";
		answerForm.action="db_answer_delete.jsp";
		answerForm.submit();
	}
</script>
<form name="qna">
	<input type="hidden" name="t_no">
</form>
<form name="viewForm">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_answer_state">
</form>
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
		<div class="board_view">
			<h2><%=dto.getTitle() %></h2>
			<p class="info"><span class="user"><%=dto.getReg_name() %></span> | <%=dto.getReg_date() %> | <i class="fa fa-eye"></i> <%=dto.getHit() %></p>
			<div class="board_body">
				<textarea readonly><%=dto.getContent() %></textarea>
				<%if(!answer_content.equals("")){ %>
					<p style="font-weight:bold">답변</p>
					<textarea readonly><%=answer_content %></textarea>
				<%} else if(answer_content.equals("") && sessionLevel.equals("top")) {%>
					<p style="font-weight:bold">답변</p>
				<%} %>

<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
	$(".answerButt").toggle(function(){
		$(".answerArea").slideDown(500);	
	}, function(){
		$(".answerArea").slideUp(500);
	})
});
//]]>
</script>
<style>
	.answerArea{display:none} 
	.btn_3wrap span {
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}
	.answerArea .textArea_H120{
		padding:5px;
		width:700px;
		height:120px;
	}	
	.answerArea .saveButt{
		float:right;
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}	
</style>
				
			<!-- 답변 -->
			<form name="answerForm">
				<div class="answerArea">
					<input type="hidden" name="t_no" value="<%=no %>">
					<input type="hidden" name="t_answer_state" value="<%=answer_state %>">
					<textarea name="t_answer_content" class="textArea_H120"><%=answer_content %></textarea>
					<a href="javascript:goAnswerSave()" class="saveButt"><%=answer_msg %></a>
				</div>
			</form>					
			</div>
			
			<div class="prev_next">
			<%if(preDto == null){ %>
				<a class="btn_prev">
				<span class="prev_wrap">
					<span>이전글은 없습니다</span>
				</span>
				</a>
			<%}else { %>
				<a href="javascript:goView('<%=preDto.getNo() %>', '<%=preDto.getAnswer_state() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong>이전글</strong>
					<span>
						<%
							String preTitle = preDto.getTitle();
							if(preTitle.length() > 15) preTitle = preTitle.substring(0, 15) + "...";
							out.print(preTitle);
						%>
					</span>
				</span>
				</a>
			<%} %>
				<div class="btn_3wrap">
				
					<a href="qna_list.jsp">목록</a> 
					<%if(!sessionLevel.equals("top") && !sessionName.equals("")){ %>
						<a href="javascript:goUpdateForm('<%=no %>')">수정</a> 
					<%} %>
					<%if(!sessionName.equals("")){ %>
						<a href="javascript:goDelete('<%=no %>')">삭제</a>
					<%} %>
					<%if(sessionLevel.equals("top")){ %> 
						<span class="answerButt" style="cursor:pointer">Answer</span>
						<%if(answer_state.equals("complet")){ %>
							<a href="javascript:goAnswerDelete()">Answer Delete</a>
						<%} %>
					<%} %>
				</div>
				
				<%if(nextDto == null){ %>				
					<a href="" class="btn_next">
					<span class="next_wrap">
						<span>다음글은 없습니다</span>
					</span>
					</a>
				<%}else { %>
					<a href="javascript:goView('<%=nextDto.getNo() %>', '<%=nextDto.getAnswer_state() %>')" class="btn_next">
					<span class="next_wrap">
						<strong>다음글</strong>
						<span>
							<%
								String nextTitle = nextDto.getTitle();
								if(nextTitle.length() > 15) nextTitle = nextTitle.substring(0, 15) + "...";
								out.print(nextTitle);
							%>
						</span>
					</span>
					<i class="fa fa-angle-right"></i></a>
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