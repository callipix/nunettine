package kr.or.ddit.pro.join.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.VMberUsersDto;

public interface ProJoinMapper {
	
	public int emailCk(String email);

	public int idCk(String userId);

	public int ncnmCk(String userNcnm);

	public UsersDto proLogin(Map<String, Object> userMap);
	
	public int proInsert(Map<String, Object> map);

	public VProUsersDto getProfile(Map<String, Object> userMap);

	public UsersDto idSearch(VProUsersDto vProUsersDto);

	public UsersDto pwSearch(VProUsersDto vProUsersDto);

	public int updatePw(Map<String, Object> map);

	public VMberUsersDto idSearch2(VProUsersDto vProUsersDto);

	public String pwSearch2(VProUsersDto vProUsersDto);

	public AdresDto getAdres(Map<String, Object> userMap);

	public String proSRCode(String spcltyRealmCode);

	public List<SpcltyRealmDto> selectCode();

	public List<SpcltyRealmDto> codeSelect(String code);


	//관리자
	public UsersDto admLogin(Map<String, Object> userMap);

	public UsersDto adminVO(String userId);


	public List<VOndyclProUsersDto> proMyClassList(String proId);

	public int countProMyClass(String proId);

	//동균 추가 시작
	public Date getUserEndDt(String userId);
	//동균 추가 끝

}
