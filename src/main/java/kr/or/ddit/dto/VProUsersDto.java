package kr.or.ddit.dto;

import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VProUsersDto {
	private String proId;
	private String proMbtlnum;
	private String sexdstnTy;
	private String email;
	private String userNm;
	private String spcltyRealmCode;
	private String userPassword;
	private String emplyrTy;
	private int secsnAt;
	private String userNcnm;
	private String userId;
	private String proProflPhoto;
	private String changePwCk;

	private MultipartFile uploadFile;
}
