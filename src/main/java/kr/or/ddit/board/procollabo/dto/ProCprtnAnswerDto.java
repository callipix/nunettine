package kr.or.ddit.board.procollabo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProCprtnAnswerDto {

	private int proCprtnAnswerNo;
	private int proCprtnBbscttNo;
	private String proCprtnAnswerCn;
	private Date proCprtnAnswerWrDt;
	private String proId;
	
}
