package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import dto.NoticeDto;

public class NoticeDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//게시글 no생성
	public String getNo() {
		String no = "";
		String query = "select nvl(max(no), '0') + 1 as no \r\n" + 
				"from jsl_어연진_notice";
		
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
	
	//등록
	public int noticeSave(NoticeDto dto) {
		int result = 0;
		String query= "insert into jsl_어연진_notice\r\n" + 
				"(no, title, content, reg_id, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"','"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getReg_id()+"',\r\n" + 
				"to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				")";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("noticeSave() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
