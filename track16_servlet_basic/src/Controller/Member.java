package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.MemberDto;
import command.member.CDBMemberDelete;
import command.member.CDBMemberSave;
import command.member.CDBMemberUpdate;
import command.member.CMemberList;
import command.member.CMemberUpdateForm;
import command.member.CMemberView;
import command.member.CMemberWrite;

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
		
		if(gubun == null) gubun = "list";
		
		String viewPage = "";
		
		//목록
		if(gubun.equals("list")) {
			CMemberList mem = new CMemberList();
			mem.execute(request);
			
			
		/*	MemberDao dao = new MemberDao();
			
			String select = request.getParameter("t_select");
			String search = request.getParameter("t_search");
			
			if(select == null) {
				select = "id";
				search = "";
			}
			
			ArrayList<MemberDto> dtos = dao.getListAll(select, search);
			
			request.setAttribute("t_dtos", dtos);
			request.setAttribute("t_select", select);
			request.setAttribute("t_search", search);
		*/	
			viewPage = "member/member_list.jsp";
			
		//등록	
		}else if(gubun.equals("writeForm")) {
			CMemberWrite mem = new CMemberWrite();
			mem.execute(request);
	
			viewPage = "member/member_write.jsp";
			
		//저장	
		}else if(gubun.equals("save")) {
			CDBMemberSave mem = new CDBMemberSave();
			mem.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//회원정보	
		}else if(gubun.equals("view")) {
			CMemberView mem = new CMemberView();
			mem.execute(request);
			
			viewPage = "member/member_view.jsp";
			
		//수정목록	
		}else if(gubun.equals("updateForm")) {
			CMemberUpdateForm mem = new CMemberUpdateForm();
			mem.execute(request);
			
			viewPage = "member/member_update.jsp";
			
		//수정	
		}else if(gubun.equals("update")) {
			CDBMemberUpdate mem = new CDBMemberUpdate();
			mem.execute(request);
			
			viewPage = "common_alert.jsp";
			
		//삭제	
		}else if(gubun.equals("delete")) {
			CDBMemberDelete mem = new CDBMemberDelete();
			mem.execute(request);
			
			viewPage = "common_alert.jsp";
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
