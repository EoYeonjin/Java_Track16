package dto;

import java.util.ArrayList;

public class EmpDto {
	String no, name, depart, grade, depart_name, grade_name;
	int age;
	
	public EmpDto(String depart, String depart_name) {
		super();
		this.depart = depart;
		this.depart_name = depart_name;
	}

	public EmpDto(String no, String name, String depart_name) {
		super();
		this.no = no;
		this.name = name;
		this.depart_name = depart_name;
	}
	
	public EmpDto(String no, String name, String depart, String grade, int age) {
		super();
		this.no = no;
		this.name = name;
		this.depart = depart;
		this.grade = grade;
		this.age = age;
	}

	public EmpDto(String no, String name, String depart, String grade, String depart_name, String grade_name, int age) {
		super();
		this.no = no;
		this.name = name;
		this.depart = depart;
		this.grade = grade;
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

	public String getDepart_name() {
		return depart_name;
	}


	public String getGrade_name() {
		return grade_name;
	}
	
	
}
