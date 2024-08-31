package kr.or.ddit.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProBkmkDto {

	private int proBkmkNo;
	private String proId;
	private String mberId;
	
	//중첩된 자바빈-프로즐겨찾기
	private List<ProDto> BkmkVOList;
	
	//중첩된자바빈-프로즐겨찾기
	private List<UsersDto> userBkVOList;
	
}

