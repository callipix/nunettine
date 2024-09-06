package kr.or.ddit.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MssageDto {
	
	private int mssageNo;
	private Date mssageTrnsmisDt;
	private String mssageCn;
	private int mssageCnfirmAt;
	private int chttSpceNo;
	private String userId;
	
}