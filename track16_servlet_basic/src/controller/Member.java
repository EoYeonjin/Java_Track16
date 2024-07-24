package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.CMemberList;
import command.member.CMemberSave;
import command.member.CMemberView;
import command.member.CMemberWrite;
import dao.MemberDao;
import dto.AreaDto;
import dto.MemberDto;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun ="list";
		String viewPage ="";

		//목록
		if(gubun.equals("list")) {
			CMemberList mem = new CMemberList();
			mem.execute(request);
			viewPage ="member/member_list.jsp";
/*			
			MemberDao dao = new MemberDao();
			String select = request.getParameter("t_select");
			String search = request.getParameter("t_search");
			if(select == null) {
				select ="id";
				search ="";
			}
			
			ArrayList<MemberDto> dtos =	dao.memberList(select, search);
			
			request.setAttribute("t_dtos", dtos);
			request.setAttribute("t_select", select);
			request.setAttribute("t_search", search);			
*/			

		
		// 등록폼
		} else if(gubun.equals("writeForm")) {
//			MemberDao dao = new MemberDao();
//			ArrayList<AreaDto> dtos = dao.getAreaList();
//			request.setAttribute("t_dtos", dtos);
			CMemberWrite mem = new CMemberWrite();
			mem.execute(request);
			viewPage ="member/member_write.jsp";
			
		// 회원 저장	
		} else if(gubun.equals("save")){
			CMemberSave mem = new CMemberSave();
			mem.execute(request);
			viewPage ="common_alert.jsp";
/*			
			MemberDao dao = new MemberDao();
			String id   = request.getParameter("t_id");
			String name = request.getParameter("t_name");
			String area = request.getParameter("t_area");
			String age  = request.getParameter("t_age");
			MemberDto dto = new MemberDto(id, name, 
										area, Integer.parseInt(age));
			int result = dao.insertMember(dto);
			
			if(result == 1) request.setAttribute("t_msg", "성공!!!");
			else request.setAttribute("t_msg", "실패~~~");
			request.setAttribute("t_url", "Member");
*/			
			

		// 상세조회	
		} else if(gubun.equals("view")){
			CMemberView mem = new CMemberView();
			mem.execute(request);
			viewPage ="member/member_view.jsp";
			
//			MemberDao dao = new MemberDao();
//			String id = request.getParameter("t_id");
//			
//			MemberDto dto = dao.getMemberView(id);
//			request.setAttribute("t_dto", dto);			

		// 수정폼	
		} else if(gubun.equals("updateForm")){
			MemberDao dao = new MemberDao();
			String id = request.getParameter("t_id");
			
			MemberDto dto = dao.getMemberView(id);
			ArrayList<AreaDto> areaDtos = dao.areaInfo();
			
			request.setAttribute("t_dto", dto);
			request.setAttribute("t_areaDtos",areaDtos);			
			
			viewPage ="member/member_update.jsp";

		// 수정저장	
		} else if(gubun.equals("update")){
			MemberDao dao = new MemberDao();
			
			String id   = request.getParameter("t_id");
			String name = request.getParameter("t_name");
			String area = request.getParameter("t_area");
			String age  = request.getParameter("t_age");
			
			MemberDto dto = new MemberDto(id, name, 
										  area, Integer.parseInt(age));		
			int result = dao.updateAll(dto);
			String msg ="수정 성공!";
			if(result != 1) msg ="수정 실패~";
			
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", "Member");			
			
			viewPage ="common_alert.jsp";
			
		// 삭제	
		} else if(gubun.equals("delete")){
			MemberDao dao = new MemberDao();
			String id = request.getParameter("t_id");
			
			int result = dao.deleteInfo(id);
			String msg ="삭제 성공!";
			if(result != 1) msg ="삭제 실패~";
			
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", "Member");	
			
			viewPage ="common_alert.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);	
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
