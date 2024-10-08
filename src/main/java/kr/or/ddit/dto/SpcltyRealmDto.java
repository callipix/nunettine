package kr.or.ddit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpcltyRealmDto {
	private String spcltyRealmCode; //전문분야 코드
	private String spcltyRealmNm; //전문분야 명
	private String ptprtSpcltyRealmCode; //상위 전문분야 코드
	
	private int lev; //계층형쿼리 레벨
}
