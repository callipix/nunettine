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
public class LbrtyBbscttAnswerDto {
	private int lbrtyBbscttAnswerNo;
	private int lbrtyBbscttNo;
	private String lbrtyBbscttAnswerCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm:ss")
	private Date lbrtyBbscttAnswerWrDt;
	private int ptLbrtyBbscttAnswerNo;
	private int ptLbrtyBbscttNo;
	private String userId;
}
