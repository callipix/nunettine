package kr.or.ddit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OndyclReviewDto {
	
	private int ondyclReNo;		//리뷰 번호
	private String ondyclReCn;	//리뷰 내용
	private String ondyclReWrDt;	//작성일자
	private String mberNcnm;	//작성자 닉네임
	private String mberId;	//작성자 아이디
	private int ondyclNo;	//원데이클래스 번호
	private int ondyclReScore;	//리뷰 별점

}
