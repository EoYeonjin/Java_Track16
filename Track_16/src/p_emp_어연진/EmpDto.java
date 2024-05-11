package p_emp_어연진;

public class EmpDto {
	private String no, name, depart, grade, depart_code, depart_name, grade_code, grade_name;
	private int age;
	
	
	
	public EmpDto(String no) {
		this.no = no;
	}

	public EmpDto(String depart_code, String depart_name) {
		this.depart_code = depart_code;
		this.depart_name = depart_name;
	}

	public EmpDto(String no, String name, String depart_name, String grade_name, int age) {
		this.no = no;
		this.name = name;
		this.depart_name = depart_name;
		this.grade_name = grade_name;
		this.age = age;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getDepart() {
		return depart;
	}

	public String getGrade() {
		return grade;
	}

	public int getAge() {
		return age;
	}

	public String getDepart_code() {
		return depart_code;
	}



	public String getDepart_name() {
		return depart_name;
	}



	public String getGrade_code() {
		return grade_code;
	}



	public String getGrade_name() {
		return grade_name;
	}
	
	
	
	
	
	
}