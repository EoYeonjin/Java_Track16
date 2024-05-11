package p_database;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_3_student {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main_3_dao dao = new Main_3_dao();
		
	/*	System.out.println("학년\t반\t번호\t이름\t국어\t영어\t수학");
		System.out.println("====================================================");
		for(Main_3_dto dto: dtos) {
			
			System.out.println("\n"+dto.getSyear() + "학년" + "\t" +
							dto.getSclass() + "반" + "\t" +
							dto.getNo() + "번" +"\t" +
							dto.getName() + "\t" +
							dto.getKor() + "점" +"\t" +
							dto.getEng() + "점" +"\t" +
							dto.getMat() + "점" +"\n");
			System.out.println("----------------------------------------------------");
		}*/
		
		String gubun = "";
		do {
			System.out.println("학년검색: Y, 반검색: C, 성명검색: N, 전체출력: A, 등록: I, 수정: U, 삭제: D, 종료: 0? ");
			gubun = sc.next();
			
			//학년검색
			if(gubun.equalsIgnoreCase("y") || gubun.equals("ㅛ")) {
				System.out.println("학년? ");
				String syear = sc.next();
				ArrayList<Main_3_dto> dtosYear = dao.getSyearList(syear);
				
				dao.infoPrint(dtosYear);
				
			//반검색	
			}else if(gubun.equalsIgnoreCase("c") || gubun.equals("ㅊ")) {
				System.out.println("반? ");
				String sclass = sc.next();
				ArrayList<Main_3_dto> dtosSclass = dao.getSclassList(sclass);
				
				dao.infoPrint(dtosSclass);
				
			//성명검색	
			}else if(gubun.equalsIgnoreCase("n") || gubun.equals("ㅜ")) {
				System.out.println("성명? ");
				String name = sc.next(); 
				
				ArrayList<Main_3_dto> dtosName = dao.getNameList(name);
				
				dao.infoPrint(dtosName);
			
			//전체출력	
			}else if(gubun.equalsIgnoreCase("a") || gubun.equals("ㅁ")) {
				ArrayList<Main_3_dto> dtos = dao.studentInfo();
				
				dao.infoPrint(dtos);
			
			//입력	
			}else if(gubun.equalsIgnoreCase("i") || gubun.equals("ㅑ")){
				System.out.println("학년? ");
				String syear = sc.next();
				System.out.println("반? ");
				String sclass = sc.next();
				System.out.println("번호? ");
				String no = sc.next();
				System.out.println("성명? ");
				String name = sc.next();
				System.out.println("국어점수? ");
				int kor = sc.nextInt();
				System.out.println("영어점수? ");
				int eng = sc.nextInt();
				System.out.println("수학점수? ");
				int mat = sc.nextInt();
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
				int result = dao.saveStudent(dto);
				
				if(result == 1) {
					System.out.println("등록 성공");
				}else {
					System.out.println("등록 실패");
				}
			
			//수정
			}else if(gubun.equalsIgnoreCase("u") || gubun.equals("ㅕ")){
				System.out.println("학년? ");
				String syear = sc.next();
				System.out.println("반? ");
				String sclass = sc.next();
				System.out.println("번호? ");
				String no = sc.next();
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no);
				
				Main_3_dto resultDto = dao.getStudentView(dto);
				if(resultDto == null) {
					System.out.println("입력 정보 없음");
				}else {
					String name = resultDto.getName();
					int kor = resultDto.getKor();
					int eng = resultDto.getEng();
					int mat = resultDto.getMat();
					
					System.out.println("=========================출력========================");
					System.out.println("학년\t반\t번호\t이름\t국어\t영어\t수학");
					System.out.println("====================================================");
					System.out.println(syear+"학년\t"+sclass+"반\t"+no+"번호\t"+name+"\t"+kor+"점\t"+eng+"점\t"+mat+"점");
					System.out.println("----------------------------------------------------");
					
					System.out.println("수정: y, 나가기: n ? ");
					String workGubun = sc.next();
					
					if(workGubun.equalsIgnoreCase("y") || workGubun.equals("ㅛ")) {
						System.out.println("성명? ");
						name = sc.next();
						System.out.println("국어점수? ");
						kor = sc.nextInt();
						System.out.println("영어점수? ");
						eng = sc.nextInt();
						System.out.println("수학점수? ");
						mat = sc.nextInt();
						
						Main_3_dto updateDto = new Main_3_dto(syear, sclass, no, name, kor, eng, mat);
						
						int result = dao.updateStudent(updateDto);
						
						if(result == 0) {
							System.out.println("수정실패");
						}else {
							System.out.println("수정완료");
						}
					}
				}
				
			//삭제	
			}else if(gubun.equalsIgnoreCase("d") || gubun.equals("ㅇ")){
				System.out.println("학년? ");
				String syear = sc.next();
				System.out.println("반? ");
				String sclass = sc.next();
				System.out.println("번호? ");
				String no = sc.next();
				
				Main_3_dto dto = new Main_3_dto(syear, sclass, no);
				Main_3_dto infoDto = dao.getStudentView(dto);
				
				if(infoDto == null) {
					System.out.println("정보 없음");
				}else {
					System.out.println("=========================출력========================");
					System.out.println("학년\t반\t번호\t이름\t국어\t영어\t수학");
					System.out.println("====================================================");
					System.out.println("\n"+infoDto.getSyear() + "학년" + "\t" +
							infoDto.getSclass() + "반" + "\t" +
							infoDto.getNo() + "번" +"\t" +
							infoDto.getName() + "\t" +
							infoDto.getKor() + "점" +"\t" +
							infoDto.getEng() + "점" +"\t" +
							infoDto.getMat() + "점" +"\n");
					System.out.println("----------------------------------------------------");
					
					System.out.println("삭제: y, 나가기: n ? ");
					String workGubun = sc.next();
					
					if(workGubun.equalsIgnoreCase("y") || workGubun.equals("ㅛ")) {
						int result = dao.deleteStudent(dto);
						if(result == 1) {
							System.out.println("삭제 성공");
						}else {
							System.out.println("삭제 실패");
						}
					}
				}
				
			}else {
				System.out.println("입력 오류");
			}
			
			System.out.println("");
		}while(!gubun.equals("0"));
		
		System.out.println("종료~");

	}

}
