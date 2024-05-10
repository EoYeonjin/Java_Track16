package p_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDao_1 {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
String getName(String id) {
		
		String name = "정보없음";
		String query = "select name, area, age " + 
					"from member " + 
					"where id= '"+id+"'";
		
		//System.out.println(query);
		try {
			
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				name = rs.getString("name");
				/*String area = rs.getString("area");
				int age = rs.getInt("age");
				
				System.out.println("성명: "+name);
				System.out.println("지역: "+area);
				System.out.println("나이: "+age);
				
				System.out.println("결과 존재한다");*/
				
			}
			
			
		}catch(SQLException e) {
			
			System.out.println("getName() 오류");
			System.out.println(query);
			e.printStackTrace();
			
		}finally {
			
			DBConnection.closeDB(con, ps, rs);
			
		}
		
		return name;
	}

	public DBDto_1 getMemberInfo(String id) {
		
		DBDto_1 dto = null;
		String query = "select name, area, age\r\n" + 
						"from member \r\n" + 
						"where id= '"+id+"'";
		
		try {
			
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String area = rs.getString("area");
				int age = rs.getInt("age");
				dto = new DBDto_1(id, name, area, age);	
				
			}
			
		}catch(Exception e) {
			
			System.out.println("getMemeberInfo()메소드 오류");
			System.out.println(query);
			e.printStackTrace();
			
		}finally {
			
			DBConnection.closeDB(con, ps, rs);
			
		}
		
		
		return dto;
	}
	
}
