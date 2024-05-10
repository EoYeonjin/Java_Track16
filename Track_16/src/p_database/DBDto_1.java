package p_database;

public class DBDto_1 {
	private String id, name, area;
	private int age;
	
	public DBDto_1(String id, String name, String area, int age) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.age = age;
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
}
