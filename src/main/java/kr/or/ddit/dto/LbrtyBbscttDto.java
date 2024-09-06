package kr.or.ddit.dto;

import java.util.Date;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LbrtyBbscttDto {
	private int    rnum;
	private int lbrtyBbscttNo;
	private String lbrtyBbscttSj;
	private String lbrtyBbscttCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm")
	private Date lbrtyBbscttWrDt;
	private int lbrtyBbscttRdcnt;
	private int sprviseAtchmnflNo;
	private String userId;
	private String delType;
	
	private MultipartFile[] files;
	
	
}