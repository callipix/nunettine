package kr.or.ddit.proservice.serviceInquiry.dto;

import java.util.Date;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class V_SrvcBtfInqryDto {
	
	private int num; // rownum 번
	private int btfInqryNo;
	private String btfInqrySj;
	private String btfInqryCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date btfInqryWrDt;
	private String mberId;
	private String proId;
	private int sprviseAtchmnflNo;
	private String btfInqryAnswerCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date btfInqryAnswerWrDt;
	private String userId;
	private String userNcnm;
	private String emplyrTy;

	private List<SprviseAtchmnflDto> sprviseAtchmnflDtoList;

}
