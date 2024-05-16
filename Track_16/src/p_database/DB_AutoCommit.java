package p_database;


public class DB_AutoCommit {
	public static void main(String[] args) {
		DB_Dao dao = new DB_Dao();
		int result = dao.setRental();
		
		System.out.println("상태: "+result);
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		
		Scanner sc = new Scanner(System.in);
		DB_1_dao dao = new DB_1_dao();
		
		System.out.print(" ID? ");
		String id = sc.next();

		int age = dao.getIdAge(id);
*/		
		

/*		
		String name = dao.getIdName(id);
		if(name.equals("")){
			System.out.println("ID없음~");
		} else {
			System.out.println("성명:"+name);
		}
*/		
	}

}





