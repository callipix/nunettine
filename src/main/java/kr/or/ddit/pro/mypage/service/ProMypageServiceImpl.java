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
	public int updProMbtlnum(Map<String, Object> map) {
		return this.proMypageMapper.updProMbtlnum(map);
	}

	@Override
	public int updPW(Map<String, Object> map) {
		return this.proMypageMapper.updPW(map);
	}

	@Override
	public int updNcnm(Map<String, Object> map) {
		return this.proMypageMapper.updNcnm(map);
	}

	@Override
	public int updPhoto(Map<String, Object> map) {
		return this.proMypageMapper.updPhoto(map);
	}

	@Override
	public int photoDelete(String userId) {
		return this.proMypageMapper.photoDelete(userId);
	}

	@Override
	public int updEmail(Map<String, Object> map) {
		return this.proMypageMapper.updEmail(map);
	}

	@Override
	public int updNm(Map<String, Object> map) {
		return this.proMypageMapper.updNm(map);
	}

	@Override
	public int updAdres(Map<String, Object> map) {
		return this.proMypageMapper.updAdres(map);
	}
}
