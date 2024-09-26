package kr.or.ddit.admin.usersSearch.service;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.usersSearch.mapper.UsersSearchMapper;
import kr.or.ddit.admin.usersSearch.dto.UsersDto;

@Service
@RequiredArgsConstructor
public class UsersSearchServiceImpl implements UsersSearchService {

	private final UsersSearchMapper usersSearchMapper;

	@Override
	public int getTotal(Map<String, Object> map) {
		return this.usersSearchMapper.getTotal(map);
	}

	@Override
	public List<UsersDto> list(Map<String, Object> map) {
		return this.usersSearchMapper.list(map);
	}

	@Override
	public UsersDto detail(String userId) {
		return this.usersSearchMapper.detail(userId);
	}

	@Override
	public String getUserProfile(String userId) {
		return this.usersSearchMapper.getUserProfile(userId);
	}

	@Override
	public int userDanger(String userId) {
		return this.usersSearchMapper.userDanger(userId);
	}

}
