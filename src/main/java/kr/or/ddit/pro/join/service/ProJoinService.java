package kr.or.ddit.pro.join.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.VOndyclProUsersDto;

public interface ProJoinService {

	int emailCk(String email);

	int idCk(String userId);

	int ncnmCk(String userNcnm);

	void certifiedPhoneNumber(String mberMbtlnum, String numStr);

	UsersDto proLogin(Map<String, Object> userMap);

	int proInsert(Map<String, Object> map);

	VProUsersDto getProfile(Map<String, Object> userMap);

	UsersDto idSearch(VProUsersDto vProUsersDto);

	UsersDto pwSearch(VProUsersDto vProUsersDto);

	int updatePw(Map<String, Object> map);

	VMberUsersDto idSearch2(VProUsersDto vProUsersDto);

	String pwSearch2(VProUsersDto vProUsersDto);

	AdresDto getAdres(Map<String, Object> userMap);

	String proSRCode(String spcltyRealmCode);

	List<SpcltyRealmDto> selectCode();

	List<SpcltyRealmDto> codeSelect(String code);

	//관리자 users 불러오기
	UsersDto adminVO(String userId);

	List<VOndyclProUsersDto> proMyClassList(String proId);

	//동균 신고 처리 추가 부분
	//프로 제재종료일 불러오기
	Date getUserEndDt(String userId);
	//동균 신고 처리 추가 끝

}
