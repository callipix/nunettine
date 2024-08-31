package kr.or.ddit.proservice.serviceRequest.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CommonCdDetailDto;
import kr.or.ddit.dto.ReviewDto;

public interface ReviewMapper {

	public List<CommonCdDetailDto> reInfo();

	public int reCreate(ReviewDto reviewDto);

	public List<ReviewDto> reDetail(String userId);

	public List<ReviewDto> reviewList(Map<String, Object> map);

	public int reviewTotal(Map<String, Object> map);

	public int reviewNoWrCnt(Map<String, Object> map);

	public ReviewDto showReview(int reNo);

	public int rqrvTotal(Map<String, Object> map);

	public List<ReviewDto> reTyChrtList(Map<String, Object> paramMap);

	public List<ReviewDto> reScoreChrtList(Map<String, Object> paramMap);

	public List<ReviewDto> proReviewList(Map<String, Object> map);

	public int proReviewTotal(Map<String, Object> map);

	public List<ReviewDto> proReDetail(String userId);

	public List<ReviewDto> proReTyChrtList(Map<String, Object> paramMap);

	public List<ReviewDto> proReScoreChrtList(Map<String, Object> paramMap);

}
