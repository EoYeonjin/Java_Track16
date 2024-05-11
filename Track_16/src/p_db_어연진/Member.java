package p_db_어연진;

import java.util.ArrayList;
import java.util.Scanner;

import p_database.Main_3_dao;
import p_database.Main_3_dto;

public class Member {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemberDao dao = new MemberDao();
		String gubun = "";
		
		do {
			System.out.println("조회: S, 등록: I, 수정: U, 삭제: D, 종료: Q ?");
			gubun = sc.next();
			
			//검색
			if(gubun.equalsIgnoreCase("s") || gubun.equals("ㄴ")) {
				System.out.println("전체 출력: A, 이름 검색: N, 지역 검색: Z ?");
				String gubunSearch = sc.next();

				//전체출력
				if(gubunSearch.equalsIgnoreCase("a") || gubunSearch.equals("ㅁ")) {
					ArrayList<MemberDto> dtos = dao.memberInfo();
					dao.infoPrint(dtos);
						
				//이름검색	
				}else if(gubunSearch.equalsIgnoreCase("n") || gubunSearch.equals("ㅜ")) {
					System.out.println("검색할 이름을 입력하세요.");
					String name = sc.next();
						
					ArrayList<MemberDto> dtosName = dao.getNameList(name);
					dao.infoPrint(dtosName);
					
				//지역검색
				}else if(gubunSearch.equalsIgnoreCase("z") || gubunSearch.equals("ㅋ")) {
					ArrayList<MemberDto> dtosAreaCode = dao.getAreaCode();
					dao.areaCodePrint(dtosAreaCode);
						
					System.out.println("지역 코드를 입력하세요.");
					String area = sc.next();
					
					MemberDto codeDto = dao.sameMemberCode(area);
					if(codeDto == null) {
						System.out.println("지역 코드를 잘못 입력하셨습니다.");
					}else {
						ArrayList<MemberDto> dtosArea = dao.getAreaList(area);
						dao.infoPrint(dtosArea);
					}
				}else System.out.println("잘못된 코드를 입력하셨습니다. 다시 입력해주세요.");

			//등록
			}else if(gubun.equalsIgnoreCase("i") || gubun.equals("ㅑ")) {
				System.out.println("ID를 입력해주세요.");
				String id = sc.next();
				
				MemberDto dto1 = new MemberDto(id);
				MemberDto resultDto = dao.getMemberView(dto1);
				
				if(resultDto == null) {
					System.out.println("이름을 입력해주세요.");
					String name = sc.next();
					ArrayList<MemberDto> dtosAreaCode = dao.getAreaCode();
					dao.areaCodePrint(dtosAreaCode);
					System.out.println("지역 코드를 입력해주세요.");
					String area = sc.next();
					
					MemberDto codeDto = dao.sameMemberCode(area);
					if(codeDto == null) {
						System.out.println("지역 코드를 잘못 입력하셨습니다.");
					}else {
						System.out.println("나이를 입력해주세요.");
						int age = sc.nextInt();
						
						MemberDto dto = new MemberDto(id, name, area, age);
						
						int result = dao.saveMember(dto);
						
						if(result == 1) {
							System.out.println("등록 성공");
						}else {
							System.out.println("등록 실패");
						}
					}
				}else System.out.println("이미 존재하는 ID입니다.");
			
			//수정
			}else if(gubun.equalsIgnoreCase("u") || gubun.equals("ㅕ")) {
				System.out.println("수정할 ID를 입력해주세요.");
				String id = sc.next();
				
				MemberDto dto = new MemberDto(id);
				MemberDto resultDto = dao.getMemberView(dto);
				
				if(resultDto == null) {
					System.out.println("해당 ID는 없습니다.");
				}else {
				
					System.out.println("=============출력=============");
					System.out.println("ID\t이름\t지역\t나이");
					System.out.println("=============================");
					System.out.println(resultDto.getId() +"\t"+
							resultDto.getName() +"\t"+ 
							resultDto.getArea_name()+ "[" +resultDto.getArea()+"]" + "\t"+
							resultDto.getAge());
					System.out.println("----------------------------");
					
					System.out.println("수정하시겠습니까? Yes: Y, No: N");
					String gubunup = sc.next();
					
					if(gubunup.equalsIgnoreCase("y") || gubunup.equals("ㅛ")) {
						System.out.println("수정할 이름을 입력해주세요.");
						String name = sc.next();
						ArrayList<MemberDto> dtosAreaCode = dao.getAreaCode();
						dao.areaCodePrint(dtosAreaCode);
						System.out.println("수정할 지역 코드를 입력해주세요.");
						String area = sc.next();
						
						MemberDto codeDto = dao.sameMemberCode(area);
						if(codeDto == null) {
							System.out.println("지역 코드를 잘못 입력하셨습니다.");
						}else {
							System.out.println("나이를 입력해주세요.");
							int age = sc.nextInt();
							
							MemberDto updateDto = new MemberDto(id, name, area, age);
							int result = dao.updateMember(updateDto);
							
							if(result == 1) {
								System.out.println("수정 성공");
							}else {
								System.out.println("수정 실패");
							}
						}
					}
				}
				
			//삭제	
			}else if(gubun.equalsIgnoreCase("d") || gubun.equals("ㅇ")) {
				System.out.println("삭제할 ID를 입력해주세요.");
				String id = sc.next();
				
				MemberDto dto = new MemberDto(id);
				MemberDto resultDto = dao.getMemberView(dto);
				
				if(resultDto == null) {
					System.out.println("해당 ID는 없습니다.");
				}else {
				
					System.out.println("=============출력"
							+ "=============");
					System.out.println("ID\t이름\t지역\t나이");
					System.out.println("=============================");
					System.out.println(resultDto.getId() +"\t"+
							resultDto.getName() +"\t"+ 
							resultDto.getArea_name()+ "[" +resultDto.getArea()+"]" + "\t"+
							resultDto.getAge());
					System.out.println("----------------------------");
					
					System.out.println("삭제하시겠습니까? Yes: Y, No: N");
					String gubundel = sc.next();
					
					if(gubundel.equalsIgnoreCase("y") || gubundel.equals("ㅛ")) {
						int result = dao.deleteMember(id);
						
						if(result == 1) {
							System.out.println("삭제 성공");
						}else {
							System.out.println("삭제 실패");
						}
					}
				}
				
			}else if(gubun.equalsIgnoreCase("q") || gubun.equals("ㅂ")){
				break;
			}else System.out.println("잘못된 코드를 입력하셨습니다. 다시 입력해주세요.");
			
		}while(!gubun.equals("Q"));
		
		System.out.println("종료");
		

	}

}