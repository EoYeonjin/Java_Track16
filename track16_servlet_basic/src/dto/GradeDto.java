package dto;

public class GradeDto {
	String grade_code, grade_name;

	public GradeDto(String grade_code, String grade_name) {
		super();
		this.grade_code = grade_code;
		this.grade_name = grade_name;
	}

	public String getGrade_code() {
		return grade_code;
	}

	public String getGrade_name() {
		return grade_name;
	}
	
	
}
