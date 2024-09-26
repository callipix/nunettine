package kr.or.ddit.pro.proSearch.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.ProDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import kr.or.ddit.pro.proSearch.mapper.SearchProMapper;
import kr.or.ddit.dto.SpcltyRealmDto;

@Service
@RequiredArgsConstructor
public class SearchProServiceImpl implements SearchProService {

	private final SearchProMapper searchProMapper;

	@Override
	public List<ProDto> proList() {
		return this.searchProMapper.proList();
	}

	@Override
	public List<ProDto> proListPage(Map<String, Object> map) {
		return this.searchProMapper.proListPage(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return this.searchProMapper.getTotal(map);
	}

	@Override
	public List<SpcltyRealmDto> spcltyB() {
		return this.searchProMapper.spcltyB();
	}

	@Override
	public List<AdresDto> aroundPro() {
		return this.searchProMapper.aroundPro();
	}

	@Override
	public List<ProDto> getMonthPro(Map<String, Object> map) {
		return this.searchProMapper.getMonthPro(map);
	}

	@Override
	public List<SpcltyRealmDto> spcltySec(String code) {
		return this.searchProMapper.spcltySec(code);
	}

	@Override
	public String spcltyNm(String code) {
		return this.searchProMapper.spcltyNm(code);
	}

}
