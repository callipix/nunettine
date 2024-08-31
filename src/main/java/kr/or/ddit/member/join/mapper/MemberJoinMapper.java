package kr.or.ddit.member.join.mapper;

import java.util.Map;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.UsersDto;
import kr.or.ddit.dto.VMberUsersDto;
import kr.or.ddit.dto.VProUsersDto;

public interface MemberJoinMapper {

	public int emailCk(String email);

	public int idCk(String userId);

	public int ncnmCk(String userNcnm);

	public UsersDto memberLogin(Map<String, Object> userMap);

	public int memberInsert(Map<String, Object> map);

	public VMberUsersDto getProfile(Map<String, Object> userMap);

	public UsersDto pwSearch(VMberUsersDto vMberUsersDto);

	public UsersDto idSearch(VMberUsersDto vMberUsersDto);

	public int updatePw(Map<String, Object> map);

	public VProUsersDto idSearch2(VMberUsersDto vMberUsersDto);

	public String pwSearch2(VMberUsersDto vMberUsersDto);

	public AdresDto getAdres(Map<String, Object> map);

}
