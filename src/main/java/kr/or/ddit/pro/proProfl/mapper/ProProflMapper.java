package kr.or.ddit.pro.proProfl.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.BcityDto;

public interface ProProflMapper {

	List<BcityDto> list(Map<String, Object> map);

	List<VCityDto> getBrtcList(String bcityNm);

	int createPost(ProProflDto proProflDto);

	String bcCode(String bcityNm);

	String btCode(@Param("bcityNm") String bcityNm, @Param("brtcNm") String brtcNm);

	ProProflDto detail(String proId);

	VProUsersDto getProInfo(String proId);

	ProProflDto getProId(String sessionId);

	List<VPrtfolioDto> prtTumb(String proId);

	List<SprviseAtchmnflDto> portfolioPicture(String sprviseAtchmnflNo);

	int modify(ProProflDto proProflDto);

	String getBcityNm(String bcityCode);

	String getBrtcNm(String brtcCode);

	String getBunryu(String spcltyRealmCode);

	int getSrvcCount(String proId);

	int getRevCount(String proId);

	int getBkmkCount(String proId);

	List<ReviewDto> getReview(Map<String, Object> map);

	int getRevCnt2(Map<String, Object> map);

	//동균 신고로 인해 추가
	List<CommonCdDetailDto> declComCdDeSelect();

	int declInsert(UserDeclDto userDeclDto);

	int declUpdate(UserDeclDto userDeclDto);
	////동균 신고로 인해 추가 끝

}
