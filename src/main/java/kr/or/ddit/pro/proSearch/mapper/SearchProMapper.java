package kr.or.ddit.pro.proSearch.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.ProDto;
import kr.or.ddit.dto.SpcltyRealmDto;

public interface SearchProMapper {

	List<ProDto> proList();

	List<ProDto> proListPage(Map<String, Object> map);

	int getTotal(Map<String, Object> map);

	List<SpcltyRealmDto> spcltyB();

	List<AdresDto> aroundPro();

	List<ProDto> getMonthPro(Map<String, Object> map);

	List<SpcltyRealmDto> spcltySec(String code);

	String spcltyNm(String code);

}
