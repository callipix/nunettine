package kr.or.ddit.pro.join.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.VMberUsersDto;

public interface ProJoinMapper {

	int emailCk(String email);

	int idCk(String userId);

	int ncnmCk(String userNcnm);

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

	//관리자
	UsersDto admLogin(Map<String, Object> userMap);

	UsersDto adminVO(String userId);

	List<VOndyclProUsersDto> proMyClassList(String proId);

	int countProMyClass(String proId);

	//동균 추가 시작
	Date getUserEndDt(String userId);
	//동균 추가 끝

}
