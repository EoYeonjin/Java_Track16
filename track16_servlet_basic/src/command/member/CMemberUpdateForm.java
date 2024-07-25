package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CMemberUpdateForm {

	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		
		MemberDto dto = dao.getMemberView(id);
		ArrayList<MemberDto> dtos = dao.getAreaCode();
		
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_dtos", dtos);
		
	}

}
