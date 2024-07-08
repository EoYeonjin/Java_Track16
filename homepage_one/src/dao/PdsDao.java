package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import dto.PdsDto;

public class PdsDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//get number
	public String getNo() {
		String no = "";
		String query = "select nvl(max(to_number(no)), '0000') + 1 as no\r\n" + 
				"from JSL_어연진_PDS";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
			}
		} catch (SQLException e) {
			System.out.println("getNo() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}
	
	//insert
	public int insertPds(PdsDto dto) {
		int result = 0;
		String query = "insert into JSL_어연진_PDS\r\n" + 
				"(no, title, content, attach, reg_id, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getAttach()+"', '"+dto.getReg_id()+"', to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertPds() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
