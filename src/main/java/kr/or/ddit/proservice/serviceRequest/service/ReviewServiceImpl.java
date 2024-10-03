package kr.or.ddit.proservice.serviceRequest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ReviewDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import kr.or.ddit.proservice.serviceRequest.mapper.ReviewMapper;
import kr.or.ddit.dto.CommonCdDetailDto;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;
	private final SrvcRequstService srvcRequstService;

	@Override
	public List<CommonCdDetailDto> reInfo() {
		return this.reviewMapper.reInfo();
	}

	@Override
	public int reCreate(ReviewDto reviewDto) {

		return this.reviewMapper.reCreate(reviewDto);
	}

	@Override
	public List<ReviewDto> reDetail(String userId) {
		return this.reviewMapper.reDetail(userId);
	}

	@Override
	public List<ReviewDto> proReDetail(String userId) {
		return this.reviewMapper.proReDetail(userId);
	}

	@Override
	public List<ReviewDto> reviewList(Map<String, Object> map) {
		UsersDto usersDto = this.srvcRequstService.userChk((String)map.get("userId"));
		map.put("emplyrTy", usersDto.getEmplyrTy());

		return this.reviewMapper.reviewList(map);
	}

	@Override
	public List<ReviewDto> proReviewList(Map<String, Object> map) {
		UsersDto usersDto = this.srvcRequstService.userChk((String)map.get("userId"));

		return this.reviewMapper.proReviewList(map);
	}

	@Override
	public int reviewTotal(Map<String, Object> map) {
		UsersDto usersDto = this.srvcRequstService.userChk((String)map.get("userId"));
		map.put("emplyrTy", usersDto.getEmplyrTy());

		return this.reviewMapper.reviewTotal(map);
	}

	@Override
	public int proReviewTotal(Map<String, Object> map) {
		UsersDto usersDto = this.srvcRequstService.userChk((String)map.get("userId"));

		return this.reviewMapper.proReviewTotal(map);
	}

	@Override
	public int reviewNoWrCnt(Map<String, Object> map) {
		return this.reviewMapper.reviewNoWrCnt(map);
	}

	@Override
	public Map<String, Object> showReview(int reNo) {
		ReviewDto reviewDto = this.reviewMapper.showReview(reNo);

		List<CommonCdDetailDto> commonCdDetailDtoList = this.reviewMapper.reInfo();
		log.info("shoReview 공통코드 변환 : {}", commonCdDetailDtoList);

		Map<String, Object> showRvMap = new HashMap<String, Object>();

		showRvMap.put("reviewVO", reviewDto);
		showRvMap.put("commonCdDetailVOList", commonCdDetailDtoList);

		return showRvMap;
	}

	@Override
	public int rqrvTotal(Map<String, Object> map) {
		return this.reviewMapper.rqrvTotal(map);
	}

	@Override
	public List<ReviewDto> reTyChrtList(Map<String, Object> paramMap) {
		retyList(paramMap);
		return this.reviewMapper.reTyChrtList(paramMap);

	}

	@Override
	public List<ReviewDto> proReTyChrtList(Map<String, Object> paramMap) {
		retyList(paramMap);
		return this.reviewMapper.proReTyChrtList(paramMap);

	}
	private void retyList(Map<String, Object> paramMap) {
		List<String> reTyList = new ArrayList<>();
		for (int i = 1; i < 15; i++) {
			if (i < 10) {
				reTyList.add("REV0" + i);
			} else {
				reTyList.add("REV" + i);
			}
		}
		paramMap.put("reTyList", reTyList);
	}

	@Override
	public List<ReviewDto> reScoreChrtList(Map<String, Object> paramMap) {
		int[] reScoreArray = {1, 2, 3, 4, 5};

		paramMap.put("reScoreArray", reScoreArray);

		return this.reviewMapper.reScoreChrtList(paramMap);
	}

	@Override
	public List<ReviewDto> proReScoreChrtList(Map<String, Object> paramMap) {
		int[] reScoreArray = {1, 2, 3, 4, 5};

		paramMap.put("reScoreArray", reScoreArray);

		return this.reviewMapper.proReScoreChrtList(paramMap);
	}

}
