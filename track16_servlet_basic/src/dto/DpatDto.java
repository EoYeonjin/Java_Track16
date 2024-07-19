package dto;

public class DpatDto {
	String depart_code, depart_name;

	public DpatDto(String depart_code, String depart_name) {
		super();
		this.depart_code = depart_code;
		this.depart_name = depart_name;
	}

	public String getDepart_code() {
		return depart_code;
	}

	public String getDepart_name() {
		return depart_name;
	}
	
	

}
