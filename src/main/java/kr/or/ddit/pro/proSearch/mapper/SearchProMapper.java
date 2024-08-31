package kr.or.ddit.pro.proSearch.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AdresDto;
import kr.or.ddit.vo.ProDto;
import kr.or.ddit.vo.SpcltyRealmDto;

public interface SearchProMapper {


	public List<ProDto> proList();

	public List<ProDto> proListPage(Map<String, Object> map);

	public int getTotal(Map<String, Object> map);

	public List<SpcltyRealmDto> spcltyB();

	public List<AdresDto> aroundPro();
	
	public List<ProDto> getMonthPro(Map<String, Object> map);

	public List<SpcltyRealmDto> spcltySec(String code);

	public String spcltyNm(String code);

}
