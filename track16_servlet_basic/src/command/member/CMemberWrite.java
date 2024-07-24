package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.AreaDto;

public class CMemberWrite {
	public void execute(HttpServletRequest request){
		MemberDao dao = new MemberDao();
		ArrayList<AreaDto> dtos = dao.getAreaList();
		request.setAttribute("t_dtos", dtos);		
	}
}










