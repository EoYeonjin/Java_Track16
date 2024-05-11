package p_database;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2 {

	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			Main_1_dao dao = new Main_1_dao();
			
			System.out.println("°Ë»ö ¼º¸í: ");
			String search = sc.next();
			
			ArrayList<Main_1_dto> dtos = 
									dao.memberAll(search);
			
			/*for(int k=0; k<dtos.size(); k++) {
				System.out.println(dtos.get(k).getId() + "\t" +
									dtos.get(k).getName() + "\t" +
									dtos.get(k).getArea() + "\t" +
									dtos.get(k).getAge() + "\n");
				
			}
			System.out.println("=========================================");
			ArrayList<Main_1_dto> dtoMember = dao.memberAlllist();
			
			for(int k=0; k<dtoMember.size(); k++) {
				System.out.println(dtoMember.get(k).getId() + "\t" +
									dtoMember.get(k).getName() + "\t" +
									dtoMember.get(k).getArea() + "\t" +
									dtoMember.get(k).getAge() + "\n");
				
			}*/
			System.out.println("=========================================");
			if(dtos.size() == 0) System.out.println("°Ë»ö ³»¿ë ¾øÀ½"); 
			for(Main_1_dto dto :dtos) {
				
				System.out.println(dto.getId() + "\t" +
							dto.getName() + "\t" +
							dto.getArea() + "\t" +
							dto.getAge() + "\n");
				
				
				
			}
		
	}

}
