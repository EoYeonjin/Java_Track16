package command.member;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CMemberView {

	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		
		MemberDto dto = dao.getMemberView(id);
		request.setAttribute("t_dto", dto);
		
	}

}