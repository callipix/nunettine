package kr.or.ddit.admin.usersSearch.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.admin.usersSearch.dto.UsersDto;

public interface UsersSearchService {

	public int getTotal(Map<String, Object> map);

	public List<UsersDto> list(Map<String, Object> map);

	public UsersDto detail(String userId);

	public String getUserProfile(String userId);

	public int userDanger(String userId);

}
