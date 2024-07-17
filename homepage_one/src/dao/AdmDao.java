package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.AdmDto;

public class AdmDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	//조회
	public ArrayList<AdmDto> getAdmList(){
		ArrayList<AdmDto> dtos = new ArrayList<AdmDto>();
		String query = "select rownum, tbl.*\r\n" + 
				"from(\r\n" + 
				"select id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, \r\n" + 
				"to_char(exit_date, 'yyyy-MM-dd') as exit_date\r\n" + 
				"from  jsl_어연진_member\r\n" + 
				")tbl, jsl_어연진_member m\r\n" + 
				"where tbl.id = m.id\r\n" + 
				"order by m.reg_date desc";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("rownum");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String email_1 = rs.getString("email_1");
				String email_2 = rs.getString("email_2");
				String exit_date = rs.getString("exit_date");
				
				dtos.add(new AdmDto(no, id, name, mobile_1, mobile_2, mobile_3, email_1, email_2, exit_date));
			}
		} catch (SQLException e) {
			System.out.println("getAdmList() method error\n"+query);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
}
