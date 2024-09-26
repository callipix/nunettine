package kr.or.ddit.member.join.service;

import java.util.Map;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.UsersDto;
import kr.or.ddit.dto.VMberUsersDto;
import kr.or.ddit.dto.VProUsersDto;

public interface MemberJoinService {

	int emailCk(String email);

	int idCk(String userId);

	int ncnmCk(String userNcnm);

	void certifiedPhoneNumber(String mberMbtlnum, String numStr);

	UsersDto memberLogin(Map<String, Object> userMap);

	int memberInsert(Map<String, Object> map);

	VMberUsersDto getProfile(Map<String, Object> userMap);

	UsersDto pwSearch(VMberUsersDto vMberUsersDto);

	UsersDto idSearch(VMberUsersDto vMberUsersDto);

	int updatePw(Map<String, Object> map);

	VProUsersDto idSearch2(VMberUsersDto vMberUsersDto);

	String pwSearch2(VMberUsersDto vMberUsersDto);

	AdresDto getAdres(Map<String, Object> map);

	
}
