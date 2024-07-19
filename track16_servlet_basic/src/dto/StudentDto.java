package dto;

public class StudentDto {
	String syear, sclass, no, name;
	int kor, eng, mat, sum;
	double ave;
	
	public StudentDto(String syear, String sclass, String no, String name) {
		super();
		this.syear = syear;
		this.sclass = sclass;
		this.no = no;
		this.name = name;
	}

	public StudentDto(String syear, String sclass, String no, String name, int kor, int eng, int mat) {
		super();
		this.syear = syear;
		this.sclass = sclass;
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	public StudentDto(String syear, String sclass, String no, String name, int kor, int eng, int mat, int sum,
			double ave) {
		super();
		this.syear = syear;
		this.sclass = sclass;
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = sum;
		this.ave = ave;
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
	
	public int getSum() {
		return sum;
	}

	public double getAve() {
		return ave;
	}
}
