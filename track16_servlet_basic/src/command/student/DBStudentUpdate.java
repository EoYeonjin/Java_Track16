package command.student;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.StudentDao;

public class DBStudentUpdate implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		StudentDao dao = new StudentDao();
		
		String syear = request.getParameter("t_syear");
		String sclass = request.getParameter("t_sclass");
		String no = request.getParameter("t_no");
		String name = request.getParameter("t_name");
		String kor = request.getParameter("t_kor");
		String mat = request.getParameter("t_mat");
		String eng = request.getParameter("t_eng");
		
		int result = dao.updateStd(syear, sclass, no, name, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
		String msg = "학생 정보가 수정되었습니다";
		
		if(result != 1) msg = "수정 실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Student");
	}

}
