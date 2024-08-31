package kr.or.ddit.pro.proProfl.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import org.springframework.stereotype.Service;

import kr.or.ddit.dto.BcityDto;

@Service
public interface ProProflService {
	//광역시 리스트 받아오기
	public List<BcityDto> list(Map<String, Object> map);
	//선택한 광역시의 시군구 받아오기
	public List<VCityDto> getBrtcList(String bcityNm);
	//광역시 코드 가져오기
	public String bcCode(String bcityNm);
	//시군구 코드 가져오기
	public String btCode(String bcityNm,String brtcNm);
	//프로프로필 넣기
	public int createPost(ProProflDto proProflDto);
	//프로프로필 보기
	public ProProflDto detail(String proId);
	//프로 정보 가져오기
	public VProUsersDto getProInfo(String proId);
	//프로필을 가지고있는 프로아이디 
	public ProProflDto getProId(String sessionId);
	//포트폴리오 썸네일 데려오기
	public List<VPrtfolioDto> prtTumb(String proId);
	//포트폴리오 데려오기
	public List<SprviseAtchmnflDto> portfolioPicture(String sprviseAtchmnflNo);
	//프로 프로필 수정하기
	public int modify(ProProflDto proProflDto);
	//프로필 지역 코드->이름
	public String getBcityNm(String bcityCode);
	public String getBrtcNm(String brtcCode);
	//프로 분류
	public String getBunryu(String spcltyRealmCode);
	
	//고용,리뷰,즐찾 수 
	public int getSrvcCount(String proId);
	public int getRevCount(String proId);
	public int getBkmkCount(String proId);
	
	//리뷰
	public List<ReviewDto> getReview(Map<String, Object> map);
	//리뷰토탈수
	public int getRevCnt2(Map<String, Object> map);

	
	//동균 신고
	//동균 신고 유형
	public List<CommonCdDetailDto> declComCdDeSelect();
	
	//동균 신고 하기
	public int declInsert(UserDeclDto userDeclDto);
	//동균 신고 끝



}
