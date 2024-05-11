package p_database;

import java.sql.Connection;

public class MemberMain {

	public static void main(String[] args) {
		Connection con = DBConnection.getConnection();
		System.out.println("con 1: "+con);
		
		/*DBConnection.closeDB(con);
		System.out.println("con2 : "+con);*/
	}

}