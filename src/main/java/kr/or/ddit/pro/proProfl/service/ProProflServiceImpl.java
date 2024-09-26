package kr.or.ddit.pro.proProfl.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pro.proProfl.mapper.ProProflMapper;
import kr.or.ddit.dto.ProProflDto;

@Service
@RequiredArgsConstructor
public class ProProflServiceImpl implements ProProflService {

	private final ProProflMapper proProflMapper;

	@Override
	public List<BcityDto> list(Map<String, Object> map) {
		return proProflMapper.list(map);
	}

	@Override
	public List<VCityDto> getBrtcList(String bcityNm) {
		return proProflMapper.getBrtcList(bcityNm);
	}

	@Override
	public int createPost(ProProflDto proProflDto) {
		return proProflMapper.createPost(proProflDto);
	}

	@Override
	public ProProflDto detail(String proId) {
		return proProflMapper.detail(proId);
	}

	@Override
	public String bcCode(String bcityNm) {
		return proProflMapper.bcCode(bcityNm);
	}

	@Override
	public String btCode(String bcityNm, String brtcNm) {
		return this.proProflMapper.btCode(bcityNm, brtcNm);
	}

	@Override
	public VProUsersDto getProInfo(String proId) {
		return this.proProflMapper.getProInfo(proId);
	}

	@Override
	public ProProflDto getProId(String sessionId) {
		return this.proProflMapper.getProId(sessionId);
	}

	@Override
	public List<VPrtfolioDto> prtTumb(String proId) {
		return this.proProflMapper.prtTumb(proId);
	}

	@Override
	public List<SprviseAtchmnflDto> portfolioPicture(String sprviseAtchmnflNo) {
		return this.proProflMapper.portfolioPicture(sprviseAtchmnflNo);
	}

	@Override
	public int modify(ProProflDto proProflDto) {
		return this.proProflMapper.modify(proProflDto);
	}

	@Override
	public String getBcityNm(String bcityCode) {
		return this.proProflMapper.getBcityNm(bcityCode);
	}

	@Override
	public String getBrtcNm(String brtcCode) {
		return this.proProflMapper.getBrtcNm(brtcCode);
	}

	@Override
	public String getBunryu(String spcltyRealmCode) {
		return this.proProflMapper.getBunryu(spcltyRealmCode);
	}

	@Override
	public int getSrvcCount(String proId) {
		return this.proProflMapper.getSrvcCount(proId);
	}

	@Override
	public int getRevCount(String proId) {
		return this.proProflMapper.getRevCount(proId);
	}

	@Override
	public int getRevCnt2(Map<String, Object> map) {
		return this.proProflMapper.getRevCnt2(map);
	}

	@Override
	public int getBkmkCount(String proId) {
		return this.proProflMapper.getBkmkCount(proId);
	}

	@Override
	public List<ReviewDto> getReview(Map<String, Object> map) {
		return this.proProflMapper.getReview(map);
	}

	//동균 신고 추가
	@Override
	public List<CommonCdDetailDto> declComCdDeSelect() {
		return this.proProflMapper.declComCdDeSelect();
	}

	@Override
	@Transactional
	public int declInsert(UserDeclDto userDeclDto) {

		int result = 0;
		result += this.proProflMapper.declInsert(userDeclDto);
		result += this.proProflMapper.declUpdate(userDeclDto);

		return result;

	}

}
