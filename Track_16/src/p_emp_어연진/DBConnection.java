package p_emp_어연진;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
			Connection con = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch(ClassNotFoundException e){
				System.out.println("There is not oracle driver");
				e.printStackTrace();
			}
			
			String db_url = "jbdc:oracle:thin:@jsl-704:1521:xe";
			String db_user = "track16";
			String db_password = "1234";
			
			try {
				con = DriverManager.getConnection(db_url, db_user, db_password);
			} catch(Exception e) {
				System.out.println("Account setting error");
			}
			
			return con;
	}
	
	static void closeDB(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
