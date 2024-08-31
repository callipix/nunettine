package kr.or.ddit.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OneInqryAnswerDto {

	private int oneInqryAnswerNo;
	private String oneInqryAnswerCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date oneInqryAnswerWrDt;
	private int oneInqryNo;
	private String userId;
	private String mngrId;
	
}

