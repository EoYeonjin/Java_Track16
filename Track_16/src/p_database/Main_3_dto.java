package p_database;

public class Main_3_dto {
	private String syear, sclass, no, name;
	private int kor, eng, mat;
	
	public Main_3_dto(String syear, String sclass, String no, String name, int kor, int eng, int mat) {
		this.syear = syear;
		this.sclass = sclass;
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	public Main_3_dto(String syear, String sclass, String no) {
		this.syear = syear;
		this.sclass = sclass;
		this.no = no;
	}

	public String getSyear() {
		return syear;
	}

	public String getSclass() {
		return sclass;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMat() {
		return mat;
	}
	
	
	
	
	
	
	
}