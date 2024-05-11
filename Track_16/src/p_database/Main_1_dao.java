package p_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main_1_dao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public String getAreaCode(String id) {
		String area = ""; 
		String query = "select area\r\n" + 
				"from member\r\n" + 
				"where id= '"+id+"'";
		
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				//id = rs.getString("ID");
				//String name = rs.getString("name");
				area = rs.getString("area");
				//String age = rs.getString("age");
				
				//dto = new Main_1_dto(id, name, area, age);
			}
			
		} catch (SQLException e) {
			System.out.println("getAreaCode() 메소드 오류");
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		
		return area;
	}

	public int getAge(String id) {
		int age = 0;
		String query = "select age\r\n" + 
				"from member\r\n" + 
				"where id= '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				age = rs.getInt("age");
			}
	
			
		} catch (SQLException e) {
			System.out.println("getAge() 메소드 오류");
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return age;
	}

	public Main_1_dto getInfo(String id) {
		Main_1_dto dto = null;
		String query = "select id, name, area, age\r\n" + 
				"from member\r\n" + 
				"where id= '"+id+"'";
		
		
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					id = rs.getString("id");
					String name = rs.getString("name");
					String area = rs.getString("area");
					String age = rs.getString("age");
					
					dto = new Main_1_dto(id, name, area, age);
				}
				
			} catch (SQLException e) {
				System.out.println("getInfo() 메소드 오류");
				System.out.println(query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
		
			
		return dto;
	}

	public ArrayList<Main_1_dto> memberAll(String search) {
		ArrayList<Main_1_dto> dtos = new ArrayList<Main_1_dto>();
		
		String query = "select id, name, area, age\r\n" + 
				"from member\r\n" + 
				"where name like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				String age = rs.getString("age");
				
				Main_1_dto dto = new Main_1_dto(id, name, area, age);
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("memberAll() 메소드 오류");
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dtos;
	}

	public ArrayList<Main_1_dto> memberAlllist() {
		ArrayList<Main_1_dto> dtos = new ArrayList<Main_1_dto>();
		String query = "select id, name, area, age\r\n" + 
				"from member";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				String age = rs.getString("age");
				
				Main_1_dto dto = new Main_1_dto(id, name, area, age);
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("memberAlllist() method error");
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
}
