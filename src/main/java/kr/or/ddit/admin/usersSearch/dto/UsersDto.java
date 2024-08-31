package kr.or.ddit.admin.usersSearch.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsersDto {
	private int rnum;
	private String userId;
	private String userNcnm;
	private String userNm;
	private String userPassword;
	private String emplyrTy;
	private int secsnAt;
}
