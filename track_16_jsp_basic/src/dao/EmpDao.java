package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.EmpDto;

public class EmpDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//조회
	public ArrayList<EmpDto> getEmpList(String select, String search){
		ArrayList<EmpDto> dtos = new ArrayList<EmpDto>();
		String query = "select e.no, e.name, d.depart_name\r\n" + 
				"from emp_어연진 e, emp_어연진_depart d\r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and "+select+" like '%"+search+"%'\r\n" + 
				"order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String name = rs.getString("name");
				String depart_name = rs.getString("depart_name");
				
				dtos.add(new EmpDto(no, name, depart_name));
			}
		} catch (SQLException e) {
			System.out.println("getEmpList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//상세조회
	public EmpDto checkEmp(String no) {
		EmpDto dto = null;
		String query = "select e.no, e.name, e.depart, d.depart_name, e.grade, g.grade_name, e.age\r\n" + 
				"from emp_어연진 e, emp_어연진_depart d, emp_어연진_grade g\r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and e.grade = g.grade_code\r\n" + 
				"and no like '%"+no+"%'\r\n" + 
				"order by e.no";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String depart = rs.getString("depart");
				String depart_name = rs.getString("depart_name");
				String grade = rs.getString("grade");
				String grade_name = rs.getString("grade_name");
				int age = rs.getInt("age");
				
				dto = new EmpDto(no, name, depart, grade, depart_name, grade_name, age);
				
			}
		} catch (SQLException e) {
			System.out.println("checkEmp() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//등록
	public int insertEmp(EmpDto dto) {
		int result = 0;
		String query = "insert into emp_어연진\r\n" + 
				"(no, name, depart, grade, age)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getName()+"', '"+dto.getDepart()+"', '"+dto.getGrade()+"', "+dto.getAge()+")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertEmp() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//수정
	public int updateEmp(String no, String name, String depart, String grade, int age) {
		int result = 0;
		String query = "update emp_어연진\r\n" + 
				"set name='"+name+"', depart ='"+depart+"', grade ='"+grade+"', age="+age+"\r\n" + 
				"where no like '%"+no+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateEmp() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
		public int deleteEmp(String no) {
			int result = 0;
			String query = "delete emp_어연진\r\n" + 
					"where no like '%"+no+"%'";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				result = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("deleteEmp() method error\n"+query);
				e.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
}
