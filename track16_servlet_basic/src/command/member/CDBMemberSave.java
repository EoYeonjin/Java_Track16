package command.member;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CDBMemberSave {

	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age = request.getParameter("t_age");
		
		MemberDto dto = new MemberDto(id, name, area, Integer.parseInt(age));
		int result = dao.insertMember(dto);
		String msg = "회원등록이 되었습니다";
		
		if(result != 1) msg = "회원등록 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
	}

	
	
}
