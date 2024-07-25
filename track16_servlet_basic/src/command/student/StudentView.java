package command.student;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.StudentDao;
import dto.StudentDto;

public class StudentView implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		StudentDao dao = new StudentDao();
		
		String syear = request.getParameter("t_syear");
		String sclass = request.getParameter("t_sclass");
		String no = request.getParameter("t_no");
		
		StudentDto dto = dao.getStudentView(syear, sclass, no);
		
		request.setAttribute("t_dto", dto);
	}

}
