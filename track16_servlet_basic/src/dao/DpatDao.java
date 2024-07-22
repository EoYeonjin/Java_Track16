package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.DpatDto;
import dto.EmpDto;

public class DpatDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//전체조회
	public ArrayList<DpatDto> getDptList(){
		ArrayList<DpatDto> dtos = new ArrayList<>();
		String query = "select * from emp_어연진_depart order by depart_code";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String depart_code = rs.getString("depart_code");
				String depart_name = rs.getString("depart_name");
				
				dtos.add(new DpatDto(depart_code, depart_name));
			}
		} catch (SQLException e) {
			System.out.println("getDptList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//조회
	public ArrayList<DpatDto> getDptInfo(String select, String search){
		ArrayList<DpatDto> dtos = new ArrayList<>();
		String query = "";
		if(!select.equals("none")) {
			query = "select * from emp_어연진_depart\r\n" + 
					"where "+select+" like '%"+search+"%'\r\n" +
					"order by depart_code";
		}else {
			query = "select * from emp_어연진_depart order by depart_code";
		}
		
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String depart_code = rs.getString("depart_code");
				String depart_name = rs.getString("depart_name");
				
				dtos.add(new DpatDto(depart_code, depart_name));
			}
		} catch (SQLException e) {
			System.out.println("getDptList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
	
	//상세조회
	public DpatDto checkDpt(String depart_code) {
		DpatDto dto = null;
		String query = "select * from emp_어연진_depart\r\n" + 
				"where depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String depart_name = rs.getString("depart_name");
				
				dto = new DpatDto(depart_code, depart_name);
			}
			
		} catch (SQLException e) {
			System.out.println("checkDpt() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//등록
	public int insertDpt(DpatDto dto) {
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
			System.out.println("insertDpt() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//수정
	public int updateDpt(String depart_code, String depart_name) {
		int result = 0;
		String query = "update emp_어연진_depart\r\n" + 
				"set depart_name='"+depart_name+"'\r\n" + 
				"where depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertDpt() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//사용여부 확인
	public DpatDto checkUsage(String depart_code) {
		DpatDto dto = null;
		String query = "select e.depart, d.depart_name \r\n" + 
				"from emp_어연진 e, emp_어연진_depart d\r\n" + 
				"where e.depart = d.depart_code\r\n" + 
				"and d.depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String depart_name = rs.getString("depart_name");
				
				dto = new DpatDto(depart_code, depart_name);
			}
			
		} catch (SQLException e) {
			System.out.println("checkUsage() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//삭제
	public int deleteDpt(String depart_code) {
		int result = 0;
		String query = "delete emp_어연진_depart\r\n" + 
				"where depart_code like '%"+depart_code+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertDpt() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
