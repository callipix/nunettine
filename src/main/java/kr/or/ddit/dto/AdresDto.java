package kr.or.ddit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdresDto {
	private String userId;
	private int adresNo;
	private String adres; //주소
	private String detailAdres; //상세주소
	private String zip; //우편번호
}
