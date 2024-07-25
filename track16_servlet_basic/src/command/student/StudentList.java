package command.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.StudentDao;
import dto.StudentDto;

public class StudentList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		StudentDao dao = new StudentDao();
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		String syear = request.getParameter("t_syear");
		String sclass = request.getParameter("t_sclass");
		
		if(select == null) {
			select = "syear";
			search = "";
		}
		
		if(syear == null || syear.equals("all")) {
			syear = "";
		}
		
		if(sclass == null || sclass.equals("all")) {
			sclass = "";
		}
		
		ArrayList<StudentDto> dtos = dao.getStudentList(select, search, syear, sclass);
		
		if(syear.equals("")){
			syear = "all";
		}
		
		if(sclass.equals("")){
			sclass = "all";
		}
		
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		request.setAttribute("t_syear", syear);
		request.setAttribute("t_sclass", sclass);
	}

}
