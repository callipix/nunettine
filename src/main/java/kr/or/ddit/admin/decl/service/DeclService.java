package kr.or.ddit.admin.decl.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.*;
import kr.or.ddit.vo.SntncDeclDto;


public interface DeclService {

	public List<SntncDeclDto> decllbrSelect(Map<String, Object> map);

	public LbrtyBbscttDto2 lbrtyBbscttVo(SntncDeclDto sntncDeclDto);

	public List<SntncDeclDto> declResnList(SntncDeclDto sntncDeclDto);

	public int declSet(int lbrtyBbscttNo);
	
	//유저 목록 조회
	public List<UsersDto> userList();
	//유저 신고 횟수 조회
	public int getDeclCount(String userId2);
	
	//해당 유저 상세 신고사항
	public List<UserDeclDto> userDeclList(String userId);

	public int userDeclSet(Map<String, Object> map);
	
	//해당 유저 제재 현황
	public List<PunshDto> declHistoryList(String userId);
	

}
