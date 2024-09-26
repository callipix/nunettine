package kr.or.ddit.proservice.serviceRequest.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CommonCdDetailDto;
import kr.or.ddit.dto.ReviewDto;

public interface ReviewMapper {

	List<CommonCdDetailDto> reInfo();

	int reCreate(ReviewDto reviewDto);

	List<ReviewDto> reDetail(String userId);

	List<ReviewDto> reviewList(Map<String, Object> map);

	int reviewTotal(Map<String, Object> map);

	int reviewNoWrCnt(Map<String, Object> map);

	ReviewDto showReview(int reNo);

	int rqrvTotal(Map<String, Object> map);

	List<ReviewDto> reTyChrtList(Map<String, Object> paramMap);

	List<ReviewDto> reScoreChrtList(Map<String, Object> paramMap);

	List<ReviewDto> proReviewList(Map<String, Object> map);

	int proReviewTotal(Map<String, Object> map);

	List<ReviewDto> proReDetail(String userId);

	List<ReviewDto> proReTyChrtList(Map<String, Object> paramMap);

	List<ReviewDto> proReScoreChrtList(Map<String, Object> paramMap);

}
