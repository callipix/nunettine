package kr.or.ddit.admin.decl.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.SntncDeclDto;


public interface DeclService {

	List<SntncDeclDto> decllbrSelect(Map<String, Object> map);

	LbrtyBbscttDto2 lbrtyBbscttVo(SntncDeclDto sntncDeclDto);

	List<SntncDeclDto> declResnList(SntncDeclDto sntncDeclDto);

	int declSet(int lbrtyBbscttNo);
	
	//유저 목록 조회
	List<UsersDto> userList();
	//유저 신고 횟수 조회
	int getDeclCount(String userId2);
	
	//해당 유저 상세 신고사항
	List<UserDeclDto> userDeclList(String userId);

	int userDeclSet(Map<String, Object> map);
	
	//해당 유저 제재 현황
	List<PunshDto> declHistoryList(String userId);
	

}
