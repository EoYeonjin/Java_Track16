package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.NoticeDto;

public class NoticeDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//게시글 no생성
	public String getNo() {
		String no = "";
		String query = "select nvl(max(to_number(no)), '0') + 1 as no \r\n" + 
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
	
	//목록
	public ArrayList<NoticeDto> getNoticeList(String select, String search){
		ArrayList<NoticeDto> dtos = new ArrayList<>();
		String query = "select n.no, n.title, m.name as reg_name, \r\n" + 
				"to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit\r\n" + 
				"from jsl_어연진_member m, jsl_어연진_notice n\r\n" + 
				"where m.id = n.reg_id\r\n" + 
				"and n."+select+" like '%"+search+"%'\r\n" + 
				"order by to_number(no) desc";
		
		if(select.equals("")) {
			query = "select n.no, n.title, m.name as reg_name, \r\n" + 
					"to_char(n.reg_date, 'yyyy-MM-dd') as reg_date, n.hit\r\n" + 
					"from jsl_어연진_member m, jsl_어연진_notice n\r\n" + 
					"where m.id = n.reg_id\r\n" + 
					"order by to_number(no) desc";
		}
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				dtos.add(new NoticeDto(no, title, reg_name, reg_date, hit));
			}
		} catch (SQLException e) {
			System.out.println("getNoticeList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
}
