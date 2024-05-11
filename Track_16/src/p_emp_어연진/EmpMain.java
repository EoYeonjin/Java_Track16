package p_emp_어연진;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmpDao dao = new EmpDao();
		String gubun = "";
		
		do {
			System.out.println("검색: S, 등록: I, 수정: U, 삭제: D, 부서관리: P, 직급관리: G, 종료: Q ?");
			gubun = sc.next();
			
			//검색
			if(gubun.equalsIgnoreCase("s") || gubun.equals("ㄴ")) {
				System.out.println("전체: A, 사번검색: E, 성명검색: N, 부서검색: D. 직급검색: G, 뒤로 가기: B");
				String search = sc.next();
				
				//전체
				if(search.equalsIgnoreCase("a") || search.equals("ㅁ")) {
					ArrayList<EmpDto> allDtos = dao.empInfo();
					dao.infoPrint(allDtos);
					
				//사번검색	
				}else if(search.equalsIgnoreCase("e") || search.equals("ㄷ")) {
					System.out.println("검색할 사번을 입력하세요.");
					String no = sc.next();
					
					ArrayList<EmpDto> NoDtos = dao.getNoList(no);
					dao.infoPrint(NoDtos);
					
				//성명검색	
				}else if(search.equalsIgnoreCase("n") || search.equals("ㅜ")) {
					System.out.println("검색할 성명을 입력하세요.");
					String name = sc.next();
					
					ArrayList<EmpDto> NameDtos = dao.getNameList(name);
					dao.infoPrint(NameDtos);
					
				//부서검색
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					ArrayList<EmpDto> departDtos = dao.getDepartCode();
					dao.codePrint(departDtos);
					
					System.out.println("검색할 부서를 입력하세요.");
					String depart = sc.next();
					
					EmpDto departCodeDto = dao.sameDepartCode(depart);
					if(departCodeDto == null) {
						System.out.println("부서코드를 잘못 입력하셨습니다.");
					}else {
						ArrayList<EmpDto> DepartDtos = dao.getDepartList(depart);
						dao.infoPrint(DepartDtos);
					}
					
				//직급검색
				}else if(search.equalsIgnoreCase("g") || search.equals("ㅎ")) {
					ArrayList<EmpDto> gradeDtos = dao.getGradeCode();
					dao.codePrint(gradeDtos);
					
					System.out.println("검색할 직급을 입력하세요.");
					String grade = sc.next();
					
					EmpDto gradeCodeDto = dao.sameGradeCode(grade);
					
					if(gradeCodeDto == null) {
						System.out.println("직급코드를 잘못 입력하셨습니다.");
					}else {
						ArrayList<EmpDto> GradeDtos = dao.getGradeList(grade);
						dao.infoPrint(GradeDtos);
					}
				}else if(search.equalsIgnoreCase("b") || search.equals("ㅠ")) {
					
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			
			//등록	
			}else if(gubun.equalsIgnoreCase("i") || gubun.equals("ㅑ")) {
				System.out.println("등록할 사번을 입력하세요.");
				String no = sc.next();
				
				EmpDto dto = new EmpDto(no);
				EmpDto resultDto = dao.getEmpView(dto);
				
				if(resultDto != null) {
					System.out.println("이미 존재하는 사번입니다.");
				}else {
					System.out.println("등록할 이름을 입력하세요.");
					String name = sc.next();
					
					ArrayList<EmpDto> departDtos = dao.getDepartCode();
					dao.codePrint(departDtos);
					System.out.println("등록할 부서코드를 입력하세요.");
					String depart = sc.next();
					
					EmpDto departCodeDto = dao.sameDepartCode(depart);
					
					if(departCodeDto == null) {
						System.out.println("부서코드를 잘못 입력하셨습니다.");
					}else {
						ArrayList<EmpDto> gradeDtos = dao.getGradeCode();
						dao.codePrint(gradeDtos);
						System.out.println("등록할 직급코드를 입력하세요.");
						String grade = sc.next();
						
						EmpDto gradeCodeDto = dao.sameGradeCode(grade);
						
						if(gradeCodeDto == null) {
							System.out.println("직급코드를 잘못 입력하셨습니다.");
						}else {
							System.out.println("등록할 나이를 입력하세요.");
							int age = sc.nextInt();
							
							EmpDto updateDto = new EmpDto(no, name, depart, grade, age);
							int result = dao.insertEmp(updateDto);
							
							if(result == 1) {
								System.out.println("등록 성공");
							}else {
								System.out.println("등록 실패");
							}
						}
					}
				}
				
			//수정	
			}else if(gubun.equalsIgnoreCase("u") || gubun.equals("ㅕ")) {
				System.out.println("수정할 사번을 입력하세요.");
				String no = sc.next();
				
				EmpDto dto = new EmpDto(no);
				EmpDto resultDto = dao.getEmpView(dto);
				
				if(resultDto == null) {
					System.out.println("존재하지 않는 사번입니다.");
				}else {
					ArrayList<EmpDto> NoDtos = dao.getNoList(no);
					dao.infoPrint(NoDtos);
					System.out.println("수정하시겠습니까? Yes: y, No: n");
					String gubun1 = sc.next();
					
					if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
						System.out.println("수정할 이름을 입력하세요.");
						String name = sc.next();
						
						ArrayList<EmpDto> departDtos = dao.getDepartCode();
						dao.codePrint(departDtos);
						System.out.println("수정할 부서코드를 입력하세요.");
						String depart = sc.next();
						
						EmpDto departCodeDto = dao.sameDepartCode(depart);
						
						if(departCodeDto == null) {
							System.out.println("부서코드를 잘못 입력하셨습니다.");
						}else {
							ArrayList<EmpDto> gradeDtos = dao.getGradeCode();
							dao.codePrint(gradeDtos);
							System.out.println("수정할 직급코드를 입력하세요.");
							String grade = sc.next();
							
							EmpDto gradeCodeDto = dao.sameGradeCode(grade);
							
							if(gradeCodeDto == null) {
								System.out.println("직급코드를 잘못 입력하셨습니다.");
							}else {
								System.out.println("수정할 나이를 입력하세요.");
								int age = sc.nextInt();
								
								EmpDto updateDto = new EmpDto(no, name, depart, grade, age);
								int result = dao.updateEmp(updateDto);
								
								if(result == 1) {
									System.out.println("수정 성공");
								}else {
									System.out.println("수정 실패");
								}
							}
						}
					}
				}
				
			//삭제
			}else if(gubun.equalsIgnoreCase("d") || gubun.equals("ㅇ")) {
				System.out.println("삭제할 사번을 입력하세요.");
				String no = sc.next();
				
				EmpDto dto = new EmpDto(no);
				EmpDto resultDto = dao.getEmpView(dto);
				
				if(resultDto == null) {
					System.out.println("존재하지 않는 사번입니다.");
				}else {
					ArrayList<EmpDto> NoDtos = dao.getNoList(no);
					dao.infoPrint(NoDtos);
					System.out.println("삭제하시겠습니까? Yes: y, No: n");
					String gubun1 = sc.next();
					
					if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
						int result = dao.deleteEmp(no);
								
						if(result == 1) {
									System.out.println("삭제 성공");
						}else {
									System.out.println("삭제 실패");
						}
					}
				}
				
			//부서관리	
			}else if(gubun.equalsIgnoreCase("p") || gubun.equals("ㅔ")) {
				ArrayList<EmpDto> departDtos = dao.getDepartCode();
				dao.codePrint(departDtos);
				
				System.out.println("부서 추가: I, 부서 수정: U, 부서 삭제: D, 뒤로 가기: B");
				String search = sc.next();
				
				//추가
				if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					System.out.println("추가할 부서 코드를 입력해주세요.");
					String depart_code = sc.next(); 
					EmpDto departCodeDto = dao.sameDepartCode(depart_code);
					
					if(departCodeDto != null) {
						System.out.println("이미 존재하는 부서 코드입니다.");
					}else {
						System.out.println("추가할 부서 이름을 입력해주세요.");
						String depart_name = sc.next();
						
						EmpDto dto = new EmpDto(depart_code, depart_name);
						int result = dao.insertDepart(dto);
						
						if(result == 1) {
							System.out.println("부서 추가 성공");
						}else {
							System.out.println("부서 추가 실패");
						}
					}
				
				//수정
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 부서 코드를 입력해주세요.");
					String depart_code = sc.next(); 
					
					EmpDto departCodeDto = dao.sameDepartCode(depart_code);
					
					if(departCodeDto == null) {
						System.out.println("존재하지 않는 부서 코드입니다.");
					}else {
						ArrayList<EmpDto> dtos = dao.getDepartCode(depart_code);
						dao.singleCodePrint(dtos);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String gubun1 = sc.next();
						
						if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
							System.out.println("수정할 부서 이름을 입력해주세요.");
							String depart_name = sc.next();
							
							EmpDto dto = new EmpDto(depart_code, depart_name);
							int result = dao.updateDepart(dto);
							
							if(result == 1) {
								System.out.println("부서 수정 성공");
							}else {
								System.out.println("부서 수정 실패");
							}
						}
					}
				
				//부서삭제
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 부서 코드를 입력해주세요.");
					String depart_code = sc.next(); 
					
					EmpDto departCodeDto = dao.sameDepartCode(depart_code);
					
					if(departCodeDto == null) {
						System.out.println("존재하지 않는 부서 코드입니다.");
					}else {
						ArrayList<EmpDto> arr = dao.getDepartList(depart_code);
						if(arr.size() != 0) {
							dao.infoPrint(arr);
							System.out.println("해당 부서의 사원이 존재하여 삭제가 불가능합니다.");
						}else {
							ArrayList<EmpDto> dtos = dao.getDepartCode(depart_code);
							dao.singleCodePrint(dtos);
							System.out.println("삭제하시겠습니까? Yes: y, No: n");
							String gubun1 = sc.next();
							
							if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
								int result = dao.deleteDepart(depart_code);
								
								if(result == 1) {
									System.out.println("부서 삭제 성공");
								}else {
									System.out.println("부서 삭제 실패");
								}
							}
						}
					}
				}else if(search.equalsIgnoreCase("b") || search.equals("ㅠ")) {
					
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
				
			//직급관리	
			}else if(gubun.equalsIgnoreCase("g") || gubun.equals("ㅎ")) {
				ArrayList<EmpDto> gradeDtos = dao.getGradeCode();
				dao.codePrint(gradeDtos);
				
				System.out.println("직급 추가: I, 직급 수정: U, 직급 삭제: D, 뒤로 가기: B");
				String search = sc.next();
				
				if(search.equalsIgnoreCase("i") || search.equals("ㅑ")) {
					System.out.println("추가할 직급 코드를 입력해주세요.");
					String grade_code = sc.next(); 
					EmpDto gradeCodeDto = dao.sameGradeCode(grade_code);
					
					if(gradeCodeDto != null) {
						System.out.println("이미 존재하는 직급 코드입니다.");
					}else {
						System.out.println("추가할 직급 이름을 입력해주세요.");
						String grade_name = sc.next();
						
						EmpDto dto = new EmpDto(grade_code, grade_name);
						int result = dao.insertGrade(dto);
						
						if(result == 1) {
							System.out.println("직급 추가 성공");
						}else {
							System.out.println("직급 추가 실패");
						}
					}
					
				//직급수정
				}else if(search.equalsIgnoreCase("u") || search.equals("ㅕ")) {
					System.out.println("수정할 직급 코드를 입력해주세요.");
					String grade_code = sc.next(); 
					
					EmpDto gradeCodeDto = dao.sameGradeCode(grade_code);
					
					if(gradeCodeDto == null) {
						System.out.println("존재하지 않는 직급 코드입니다.");
					}else {
						ArrayList<EmpDto> dtos = dao.getGradeCode(grade_code);
						dao.singleCodePrint(dtos);
						System.out.println("수정하시겠습니까? Yes: y, No: n");
						String gubun1 = sc.next();
						
						if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
							System.out.println("수정할 직급 이름을 입력해주세요.");
							String grade_name = sc.next();
							
							EmpDto dto = new EmpDto(grade_code, grade_name);
							int result = dao.updateGrade(dto);
							
							if(result == 1) {
								System.out.println("직급 수정 성공");
							}else {
								System.out.println("직급 수정 실패");
							}
						}
					}
						
				//직급삭제		
				}else if(search.equalsIgnoreCase("d") || search.equals("ㅇ")) {
					System.out.println("삭제할 직급 코드를 입력해주세요.");
					String grade_code = sc.next(); 
					
					EmpDto gradeCodeDto = dao.sameGradeCode(grade_code);
					
					if(gradeCodeDto == null) {
						System.out.println("존재하지 않는 직급 코드입니다.");
					}else {
						ArrayList<EmpDto> arr = dao.getGradeList(grade_code);
						if(arr.size() != 0) {
							dao.infoPrint(arr);
							System.out.println("해당 직급의 사원이 존재하여 삭제가 불가능합니다.");
						}else {
							ArrayList<EmpDto> dtos = dao.getGradeCode(grade_code);
							dao.singleCodePrint(dtos);
							System.out.println("삭제하시겠습니까? Yes: y, No: n");
							String gubun1 = sc.next();
							
							if(gubun1.equalsIgnoreCase("y") || gubun1.equals("ㅛ")) {
								int result = dao.deleteGrade(grade_code);
								
								if(result == 1) {
									System.out.println("직급 삭제 성공");
								}else {
									System.out.println("직급 삭제 실패");
								}
							}
						}
					}
				}else if(search.equalsIgnoreCase("b") || search.equals("ㅠ")) {
					
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
				
			//종료
			}else if(gubun.equalsIgnoreCase("q") || gubun.equals("ㅂ")){
				break;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}while(!gubun.equals("q"));
		
		System.out.println("종료");
	}

}