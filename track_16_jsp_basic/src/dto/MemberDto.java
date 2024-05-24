package dto;

public class MemberDto {
	private String id, name, area, area_name, area_code;
	private int age;
	
	public MemberDto() {}

	public MemberDto(String area_code) {
		this.area_code = area_code;
	}

	public MemberDto(String id, String name, String area, int age) {
		this.id = id;
		this.name = name;
		this.area = area;
		this.age = age;
	}

	public MemberDto(String area_name, String area_code) {
		this.area_name = area_name;
		this.area_code = area_code;
	}

	public MemberDto(String id, String name, String area_name, String area, int age) {
		this.id = id;
		this.name = name;
		this.area_name = area_name;
		this.area = area;
		this.age = age;
	}

	public String getArea_name() {
		return area_name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getArea() {
		return area;
	}

	public int getAge() {
		return age;
	}

	public String getArea_code() {
		return area_code;
	}
	
	
	
}
