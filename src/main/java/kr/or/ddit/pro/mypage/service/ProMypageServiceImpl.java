package kr.or.ddit.pro.mypage.service;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import kr.or.ddit.pro.mypage.mapper.ProMypageMapper;

@Service
@RequiredArgsConstructor
public class ProMypageServiceImpl implements ProMypageService {

	private final ProMypageMapper proMypageMapper;

	@Override
	public int photoDelete(String userId) {
		return this.proMypageMapper.photoDelete(userId);
	}
}
