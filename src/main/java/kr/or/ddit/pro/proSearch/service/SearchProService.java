package kr.or.ddit.pro.proSearch.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProDto;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.SpcltyRealmDto;

public interface SearchProService {

	//프로전체 리스트 
	List<ProDto> proList();

	//프로 리스트 검색, 페이징
	List<ProDto> proListPage(Map<String, Object> map);

	//페이징 토탈
	int getTotal(Map<String, Object> map);

	//서비스 대분류
	List<SpcltyRealmDto> spcltyB();

	//서비스 하위분류
	List<SpcltyRealmDto> spcltySec(String code);

	//내주변 프로
	List<AdresDto> aroundPro();

	//이달의 프로
	List<ProDto> getMonthPro(Map<String, Object> map);

	//서비스 이름
	String spcltyNm(String code);

}
