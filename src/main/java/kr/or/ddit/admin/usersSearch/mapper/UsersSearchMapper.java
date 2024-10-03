package kr.or.ddit.admin.usersSearch.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.admin.usersSearch.dto.UsersDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersSearchMapper {

	int getTotal(Map<String, Object> map);

	List<UsersDto> list(Map<String, Object> map);

	UsersDto detail(String userId);

	String getUserProfile(String userId);

	int userDanger(String userId);

}
