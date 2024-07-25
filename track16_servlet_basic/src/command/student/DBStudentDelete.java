package command.student;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.StudentDao;

public class DBStudentDelete implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		StudentDao dao = new StudentDao();
		
		String syear = request.getParameter("t_syear");
		String sclass = request.getParameter("t_sclass");
		String no = request.getParameter("t_no");
		
		int result = dao.deleteStd(syear, sclass, no);
		
		String msg = "학생 정보가 삭제되었습니다";
		
		if(result != 1) msg = "삭제 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Student");

	}

}
