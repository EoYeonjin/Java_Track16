package p_database;

import java.util.ArrayList;

public class MemberDao {
	MemberDto getMemberInfo(String id, String name) {
		MemberDto dto = new MemberDto(id, name);
		return dto;
	}
	ArrayList<MemberDto> getMemberList(){
		ArrayList<MemberDto> dtos = new ArrayList<>();
		MemberDto dto = new MemberDto("201", "�̹μ�");
		dtos.add(dto);
		return dtos;
	}
}