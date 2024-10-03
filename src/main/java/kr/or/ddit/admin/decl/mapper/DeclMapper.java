package kr.or.ddit.admin.decl.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.SntncDeclDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeclMapper {

	List<SntncDeclDto> decllbrSelect(Map<String, Object> map);

	LbrtyBbscttDto2 lbrtyBbscttVo(SntncDeclDto sntncDeclDto);

	List<SntncDeclDto> declResnList(SntncDeclDto sntncDeclDto);

	int declSet1(int lbrtyBbscttNo);

	int declSet2(int lbrtyBbscttNo);

	List<UsersDto> userList();

	int getDeclCount(String userId2);

	List<UserDeclDto> userDeclList(String userId);

	int userDeclSet(Map<String, Object> map);

	int declProcessAtSet(Map<String, Object> map);

	List<PunshDto> declHistoryList(String userId);

	int declSet3(int lbrtyBbscttNo);

}
