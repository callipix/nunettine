package kr.or.ddit.board.prostory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GoodPointDto {

	private int goodNo;						// good번호
	private int proStoryBbscttNo;			// 
	private String userId;					// 
	
	public GoodPointDto(int proStoryBbscttNo , String userId) {
		this.proStoryBbscttNo = proStoryBbscttNo;
		this.userId = userId;
	}
	
}
