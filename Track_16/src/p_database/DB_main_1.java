package p_database;

import java.util.Scanner;

public class DB_main_1 {

	public static void main(String[] args) {
		DBDao_1 dao = new DBDao_1();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ID? ");
		String id = sc.next();
		
		//String name = dao.getName(id);
		//System.out.println("name: "+name);
		
		DBDto_1 dto = dao.getMemberInfo(id);
		
		if(dto != null) {
			System.out.println("ID: "+dto.getId());
			System.out.println("¼º¸í: "+dto.getName());
			System.out.println("Áö¿ª: "+dto.getArea());
			System.out.println("³ªÀÌ: "+dto.getAge());
		}else {
			System.out.println("Á¤º¸¾øÀ½");
		}
	}

}
