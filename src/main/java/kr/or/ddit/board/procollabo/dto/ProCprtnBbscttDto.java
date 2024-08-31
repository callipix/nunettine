package kr.or.ddit.board.procollabo.dto;

import java.util.Date;
import java.util.List;

import kr.or.ddit.dto.ProDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.dto.ProProflDto;
import lombok.Data;

@Data
public class ProCprtnBbscttDto {

	private int rnum;
	private int proCprtnBbscttNo;
	private String proCprtnBbscttSj;
	private String proCprtnBbscttCn;
	@DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss")
	private Date proCprtnBbscttWrDt;
	private int proCprtnBbscttRdcnt;
	private int sprviseAtchmnflNo;
	private String proId;
	
	private List<SprviseAtchmnflDto> spAtVOList;
	
	private List<ProDto> proList;
	private List<ProProflDto> proProflList;
	
	private String userNcnm;
	private String proProflPhoto;
}
