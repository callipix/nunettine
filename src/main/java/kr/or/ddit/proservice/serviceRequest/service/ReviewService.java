package kr.or.ddit.proservice.serviceRequest.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CommonCdDetailDto;
import kr.or.ddit.dto.ReviewDto;

public interface ReviewService {

	List<CommonCdDetailDto> reInfo();

	int reCreate(ReviewDto reviewDto);

	List<ReviewDto> reDetail(String userId);

	List<ReviewDto> reviewList(Map<String, Object> map);

	int reviewTotal(Map<String, Object> map);

	int reviewNoWrCnt(Map<String, Object> map);

	Map<String, Object> showReview(int reNo);

	int rqrvTotal(Map<String, Object> map);

	List<ReviewDto> reTyChrtList(Map<String, Object> paramMap);

	List<ReviewDto> reScoreChrtList(Map<String, Object> paramMap);

	List<ReviewDto> proReviewList(Map<String, Object> map);
	
	int proReviewTotal(Map<String, Object> map);

	List<ReviewDto> proReDetail(String userId);

	List<ReviewDto> proReTyChrtList(Map<String, Object> paramMap);

	List<ReviewDto> proReScoreChrtList(Map<String, Object> paramMap);

}
