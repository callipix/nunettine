package kr.or.ddit.admin.decl.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.*;
import kr.or.ddit.vo.SntncDeclDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeclMapper {

	public List<SntncDeclDto> decllbrSelect(Map<String, Object> map);

	public LbrtyBbscttDto2 lbrtyBbscttVo(SntncDeclDto sntncDeclDto);

	public List<SntncDeclDto> declResnList(SntncDeclDto sntncDeclDto);

	public int declSet1(int lbrtyBbscttNo);

	public int declSet2(int lbrtyBbscttNo);

	public List<UsersDto> userList();

	public int getDeclCount(String userId2);

	public List<UserDeclDto> userDeclList(String userId);

	public int userDeclSet(Map<String, Object> map);

	public int declProcessAtSet(Map<String, Object> map);

	public List<PunshDto> declHistoryList(String userId);

	public int declSet3(int lbrtyBbscttNo);

}
