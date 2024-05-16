package p_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import p_database.DBConnection;

public class DB_Dao{
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public int setRental() {
		int result =0;
		String queryInsert ="insert into member_어연진\r\n" + 
				"(id,name,area)\r\n" + 
				"values\r\n" + 
				"('102','홍길자','30')";
		String queryUpdate ="update member_어연진\r\n" + 
				"set name='홍수정'\r\n" + 
				"where id='999'";
		
		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(queryInsert);
			result = ps.executeUpdate();
			
			if(result == 1) {
				ps = con.prepareStatement(queryUpdate);
				result = ps.executeUpdate();
			}
			
			if(result == 1) con.commit();
			else con.rollback();
				
			con.setAutoCommit(true);
			
		}catch(Exception e) {
			System.out.println("setInfo() 오류:"+queryInsert);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		
		return result;
	}	
	
	
	//출고 Rantal insert
	int setRentalStart() {
		String queryInsert ="insert into ~~~";
		
		return 0;
	}
	//출고 차량관리  update
	int setRentalupdate() {
		String queryupdate ="update ~~~";
		
		return 0;
	}
}







