package kr.or.ddit.dto;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SprviseAtchmnfl {
	private int sprviseAtchmnflNo;
	private int atchmnflNo;
	private String atchmnflCours;
	private String atchmnflNm;
	private String storeAtchmnflNm;
	private String atchmnflTy;
	private Date registDt;
	private String userId;
	private Date updtDt;
}
