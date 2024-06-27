package dto;

public class QnaDto {
	private String no, title, content, reg_id, reg_name, reg_date, answer_state, answer_content, answer_msg;
	private int hit;
	
	//조회
	public QnaDto(String no, String title, String reg_name, String reg_date, String answer_state, String answer_msg, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.answer_state = answer_state;
		this.answer_msg = answer_msg;
		this.hit = hit;
	}

	//등록
	public QnaDto(String no, String title, String content, String reg_id, String reg_date, String anwer_state) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.answer_state = anwer_state;
	}
	
	//상세조회
	public QnaDto(String title, String content, String reg_name, String reg_date, String answer_content,
			int hit) {
		super();
		this.title = title;
		this.content = content;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.answer_content = answer_content;
		this.answer_msg = answer_msg;
		this.hit = hit;
	}

	public String getAnswer_msg() {
		return answer_msg;
	}

	public String getNo() {
		return no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getReg_id() {
		return reg_id;
	}
	
	public String getReg_name() {
		return reg_name;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	
	public String getAnswer_state() {
		return answer_state;
	}
	
	public String getAnswer_content() {
		return answer_content;
	}
	
	public int getHit() {
		return hit;
	}
	
	
}
