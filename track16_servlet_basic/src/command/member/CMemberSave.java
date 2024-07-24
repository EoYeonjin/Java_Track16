package command.member;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class CMemberSave {
	public void execute(HttpServletRequest request){
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
	}
}
