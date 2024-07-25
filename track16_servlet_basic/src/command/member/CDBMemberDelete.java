package command.member;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;

public class CDBMemberDelete {

	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		
		int result = dao.deleteMember(id);
		String msg = "회원이 삭제되었습니다";
		
		if(result != 1) msg = "삭제 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
	}

}
