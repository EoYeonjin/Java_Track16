package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.DpatDto;
import dto.EmpDto;
import dto.GradeDto;

public class GradeDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//전체조회
	public ArrayList<GradeDto> getGdList(){
		ArrayList<GradeDto> dtos = new ArrayList<GradeDto>();
		String query = "select * from emp_어연진_grade";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String grade_code = rs.getString("grade_code");
				String grade_name = rs.getString("grade_name");
				
				dtos.add(new GradeDto(grade_code, grade_name));
			}
		} catch (SQLException e) {
			System.out.println("getGdList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//조회
	public ArrayList<GradeDto> getGdInfo(String select, String search){
		ArrayList<GradeDto> dtos = new ArrayList<>();
		String query = "";
		if(!select.equals("none")) {
			query = "select * from emp_어연진_grade\r\n" + 
					"where "+select+" like '%"+search+"%'\r\n" +
					"order by grade_code";
		}else {
			query = "select * from emp_어연진_grade order by grade_code";
		}
		
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String grade_code = rs.getString("grade_code");
				String grade_name = rs.getString("grade_name");
				
				dtos.add(new GradeDto(grade_code, grade_name));
			}
		} catch (SQLException e) {
			System.out.println("getGdList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//상세조회
	public GradeDto checkGd(String grade_code) {
		GradeDto dto = null;
		String query = "select * from emp_어연진_grade\r\n" + 
				"where grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String grade_name = rs.getString("grade_name");
				
				dto = new GradeDto(grade_code, grade_name);
			}
			
		} catch (SQLException e) {
			System.out.println("checkGd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//등록
	public int insertGd(GradeDto dto) {
		int result = 0;
		String query = "insert into emp_어연진_grade\r\n" + 
				"(grade_code, grade_name)\r\n" + 
				"values\r\n" + 
				"('"+dto.getGrade_code()+"', '"+dto.getGrade_name()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertGd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//사용여부 확인
	public GradeDto checkUsage(String grade_code) {
		GradeDto dto = null;
		String query = "select e.grade, g.grade_name \r\n" + 
				"from emp_어연진 e, emp_어연진_grade g\r\n" + 
				"where e.grade = g.grade_code\r\n" + 
				"and g.grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String grade_name = rs.getString("grade_name");
				
				dto = new GradeDto(grade_code, grade_name);
			}
			
		} catch (SQLException e) {
			System.out.println("checkUsage() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//수정
	public int updateGd(String grade_code, String grade_name) {
		int result = 0;
		String query = "update emp_어연진_grade\r\n" + 
				"set grade_name='"+grade_name+"'\r\n" + 
				"where grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateGd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}		
		
		return result;
	}
	
	//삭제
	public int deleteGd(String grade_code) {
		int result = 0;
		String query = "delete emp_어연진_grade\r\n" + 
				"where grade_code like '%"+grade_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteGd() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
