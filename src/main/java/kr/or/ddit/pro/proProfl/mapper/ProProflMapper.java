package kr.or.ddit.pro.proProfl.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.BcityDto;


public interface ProProflMapper {

	
	public List<BcityDto> list(Map<String, Object> map);
	
	public List<VCityDto> getBrtcList(String bcityNm);

	public int createPost(ProProflDto proProflDto);

	public String bcCode(String bcityNm);

	public String btCode(@Param("bcityNm")String bcityNm,@Param("brtcNm")String brtcNm);

	public ProProflDto detail(String proId);

	public VProUsersDto getProInfo(String proId);

	public ProProflDto getProId(String sessionId);

	public List<VPrtfolioDto> prtTumb(String proId);

	public List<SprviseAtchmnflDto> portfolioPicture(String sprviseAtchmnflNo);

	public int modify(ProProflDto proProflDto);

	public String getBcityNm(String bcityCode);

	public String getBrtcNm(String brtcCode);

	public String getBunryu(String spcltyRealmCode);

	public int getSrvcCount(String proId);

	public int getRevCount(String proId);
	
	public int getBkmkCount(String proId);
	
	public List<ReviewDto> getReview(Map<String, Object> map);
	public int getRevCnt2(Map<String, Object> map);
	
	
	//동균 신고로 인해 추가
	public List<CommonCdDetailDto> declComCdDeSelect();

	public int declInsert(UserDeclDto userDeclDto);

	public int declUpdate(UserDeclDto userDeclDto);
	////동균 신고로 인해 추가 끝









}
