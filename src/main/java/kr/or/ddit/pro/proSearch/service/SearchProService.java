package kr.or.ddit.pro.proSearch.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProDto;
import org.springframework.stereotype.Service;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.SpcltyRealmDto;

@Service
public interface SearchProService {
	
	//프로전체 리스트 
	public List<ProDto> proList();
	
	//프로 리스트 검색, 페이징
	public List<ProDto> proListPage(Map<String, Object> map);

	//페이징 토탈
	public int getTotal(Map<String, Object> map);
	
	//서비스 대분류
	public List<SpcltyRealmDto> spcltyB();
	
	//서비스 하위분류
	public List<SpcltyRealmDto> spcltySec(String code);
	
	//내주변 프로
	public List<AdresDto> aroundPro();
	
	//이달의 프로
	public List<ProDto> getMonthPro(Map<String, Object> map);
	
	//서비스 이름
	public String spcltyNm(String code);



}
