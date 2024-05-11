package p_database;

import java.util.Scanner;

public class Main_1 {

	public static void main(String[] args) {
		Main_1_dao dao = new Main_1_dao();
		
		Scanner sc = new Scanner(System.in);
		System.out.println(" ID? "); //A001
		String id = sc.next();
		
		String area = dao.getAreaCode(id); //10
		System.out.println("area: "+area+"\n");
		int age = dao.getAge(id);  //23
		System.out.println("age: "+age+"\n");
		Main_1_dto dto = dao.getInfo(id);
		System.out.println("ID: "+dto.getId());
		System.out.println("ÀÌ¸§: "+dto.getName());
		System.out.println("Áö¿ª: "+dto.getArea());
		System.out.println("³ªÀÌ: "+dto.getAge()+"\n");
		
	}

}
