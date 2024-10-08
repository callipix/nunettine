package kr.or.ddit.dto;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VPrtfolioDto {
	
	private int prtfolioNo;
	private String prtfolioSj;
	private Date prtfolioWrDt;
	private int sprviseAtchmnflNo;
	private String proId;
	private int atchmnflNo;
	private String atchmnflCours;
	private String atchmnflNm;
	private String storeAtchmnflNm;
	private String atchmnflTy;
	private Date registDt;
	private Date updtDt;
}
