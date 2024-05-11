package p_emp_어연진;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//전체조회
	public ArrayList<EmpDto> empInfo() {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n"
				+ "order by e.no";
				
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				EmpDto dto = new EmpDto(no, name, depart_name, grade_name, age);
				dtos.add(dto);
				
			}
		} catch (SQLException e) {
			System.out.println("empInfo() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//출력
	public void infoPrint(ArrayList<EmpDto> dtos) {
		if(dtos.size() == 0) {
			System.out.println("해당 사항 없음");
		}else {
			System.out.println("================출력================");
			System.out.println("사번\t이름\t부서\t직급\t나이");
			System.out.println("===================================");
			for(EmpDto dto: dtos) {
				System.out.println(dto.getNo() + "\t" +
									dto.getName() + "\t" +
									dto.getDepart_name() + "\t" +
									dto.getGrade_name() + "\t" +
									dto.getAge() + "\t");
				System.out.println("-----------------------------------");
			}
		}
		
	}

	//사번검색
	public ArrayList<EmpDto> getNoList(String no) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and no like '%"+no+"%'\r\n"
						+ "order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				EmpDto dto = new EmpDto(no, name, depart_name, grade_name, age);
				dtos.add(dto);
				
			}
		} catch (SQLException e) {
			System.out.println("getNoList() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dtos;
	}

	//이름검색
	public ArrayList<EmpDto> getNameList(String name) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and name like '%"+name+"%'\r\n"
						+ "order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				name = rs.getString("name");
				String depart = rs.getString("depart_name");
				String grade = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				EmpDto dto = new EmpDto(no, name, depart, grade, age);
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("getNameList() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	
	//부서 코드 
	public ArrayList<EmpDto> getDepartCode() {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select * from emp_어연진_depart\r\n"
				+ "order by depart_code";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String depart_name = rs.getString("depart_name");
				String depart_code = rs.getString("depart_code");
				
				EmpDto dto = new EmpDto(depart_code, depart_name);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dtos;
	}
	
	//코드출력
	public void codePrint(ArrayList<EmpDto> dtos) {
		System.out.println("=====출력=====");
		for(EmpDto dto: dtos) {
			System.out.println(dto.getDepart_name() + "\t" + "[" + dto.getDepart_code()+"]");
		}
		System.out.println("=============");
	}
	
	//부서검색
	public ArrayList<EmpDto> getDepartList(String depart) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and depart_code like '%"+depart+"%'\r\n"
						+ "order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				EmpDto dto = new EmpDto(no, name, depart_name, grade_name, age);
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("getDepartList() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//직급코드
	public ArrayList<EmpDto> getGradeCode() {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select * from emp_어연진_grade\r\n"
				+ "order by grade_code";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String grade_name = rs.getString("grade_name");
				String grade_code = rs.getString("grade_code");
				
				EmpDto dto = new EmpDto(grade_code, grade_name);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getGradeCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//직급검색
	public ArrayList<EmpDto> getGradeList(String grade) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and grade_code like '%"+grade+"%'\r\n"
						+ "order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				EmpDto dto = new EmpDto(no, name, depart_name, grade_name, age);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getGradeList() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	//특정조건조회
	public EmpDto getEmpView(EmpDto dto) {
		EmpDto resultDto = null;
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g \r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and no like '%"+dto.getNo()+"%'\r\n"
						+ "order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				resultDto = new EmpDto(no, name, depart_name, grade_name, age);
			}
			
			
		} catch (SQLException e) {
			System.out.println("getGradeList() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return resultDto;
	}

	//등록
	public int insertEmp(EmpDto dto) {
		int result = 0;
		String query = "insert into emp_어연진\r\n" + 
				"(no, name, depart, grade, age)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getName()+"', '"+dto.getDepart_name()+"', '"+dto.getGrade_name()+"', "+dto.getAge()+")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertEmp() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//특정부서코드조회
	public EmpDto sameDepartCode(String depart) {
		EmpDto resultDto = null;
		String query = "select * from emp_어연진_depart\r\n" + 
				"where depart_code like '%"+depart+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String depart_name = rs.getString("depart_name");
				String depart_code = rs.getString("depart_code");
				
				resultDto = new EmpDto(depart_code, depart_name);
			}
		} catch (SQLException e) {
			System.out.println("sameDepartCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		
		return resultDto;
	}

	//특정직급코드조회
	public EmpDto sameGradeCode(String grade) {
		EmpDto resultDto = null;
		String query = "select * from emp_어연진_grade\r\n" + 
				"where grade_code like '%"+grade+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String grade_name = rs.getString("grade_name");
				String grade_code = rs.getString("grade_code");
				
				resultDto = new EmpDto(grade_code, grade_name);
			}
		} catch (SQLException e) {
			System.out.println("sameGradeCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return resultDto;
	}

	//수정
	public int updateEmp(EmpDto dto) {
		int result = 0;
		String query = "update emp_어연진 \r\n" + 
				"set name='"+dto.getName()+"', depart='"+dto.getDepart_name()+"', grade='"+dto.getGrade_name()+"', age="+dto.getAge()+"\r\n" + 
				"where no='"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateEmp() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return result;
	}

	//삭제
	public int deleteEmp(String no) {
		int result = 0;
		String query = "delete from emp_어연진\r\n" + 
				"where no='"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteEmp() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//부서관리 등록
	public int insertDepart(EmpDto dto) {
		int result = 0;
		String query = "insert into emp_어연진_depart\r\n" + 
				"(depart_code, depart_name)\r\n" + 
				"values\r\n" + 
				"('"+dto.getDepart_code()+"', '"+dto.getDepart_name()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertDepart() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//직급관리 등록
	public int insertGrade(EmpDto dto) {
		int result = 0;
		String query = "insert into emp_어연진_grade\r\n" + 
				"(grade_code, grade_name)\r\n" + 
				"values\r\n" + 
				"('"+dto.getDepart_code()+"', '"+dto.getDepart_name()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertGrade() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//부서관리 수정
	public int updateDepart(EmpDto dto) {
		int result = 0;
		String query = "update emp_어연진_depart\r\n" + 
				"set depart_name = '"+dto.getDepart_name()+"'\r\n" + 
				"where depart_code = '"+dto.getDepart_code()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateDepart() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	//특정 조건 부서코드
	public ArrayList<EmpDto> getDepartCode(String depart_code) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select * from emp_어연진_depart\r\n" + 
				"where depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String depart_name = rs.getString("depart_name");
				depart_code = rs.getString("depart_code");
				
				EmpDto dto = new EmpDto(depart_code, depart_name);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getDepartCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//하나의 코드 출력
	public void singleCodePrint(ArrayList<EmpDto> dtos) {
		System.out.println("============");
		for(EmpDto dto: dtos) {
			System.out.println(dto.getDepart_name() + "\t" + "[" + dto.getDepart_code()+"]");
		}
		System.out.println("=============");
		
	}

	//특정 조건 직급코드
	public ArrayList<EmpDto> getGradeCode(String grade_code) {
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select * from emp_어연진_grade\r\n" + 
				"where grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String grade_name = rs.getString("grade_name");
				grade_code = rs.getString("grade_code");
				
				EmpDto dto = new EmpDto(grade_code, grade_name);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("getGradeCode() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}

	//직급관리 수정
	public int updateGrade(EmpDto dto) {
		int result = 0;
		String query = "update emp_어연진_grade\r\n" + 
				"set grade_name = '"+dto.getDepart_name()+"'\r\n" + 
				"where grade_code = '"+dto.getDepart_code()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateGrade() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	//부서삭제
	public int deleteDepart(String depart_code) {
		int result  = 0;
		String query = "delete from emp_어연진_depart\r\n" + 
				"where depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteDepart() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//직급삭제
	public int deleteGrade(String grade_code) {
		int result  = 0;
		String query = "delete from emp_어연진_grade\r\n" + 
				"where grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteGrade() method error"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	
	
	

	
	
	
	
}