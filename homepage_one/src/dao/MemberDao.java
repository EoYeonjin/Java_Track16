package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;

public class MemberDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//ID중복 검사
	public int checkId(String id){
		int count = 0;
		String query = "select count(*) as count\r\n" + 
				"from jsl_어연진_member\r\n" + 
				"where id='"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			System.out.println("checkId() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
}
