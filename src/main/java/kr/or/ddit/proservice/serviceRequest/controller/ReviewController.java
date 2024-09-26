package kr.or.ddit.proservice.serviceRequest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dto.ReviewDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.ddit.proservice.serviceRequest.service.ReviewService;
import kr.or.ddit.proservice.serviceRequest.service.SrvcRequstService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.dto.CommonCdDetailDto;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/srvcRqReview")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	private final SrvcRequstService srvcRequstService;
	private final SrvcRequstController srvcRequstController;

	@GetMapping("/reInfo")
	@ResponseBody
	List<CommonCdDetailDto> reInfo() {
		List<CommonCdDetailDto> commonCdDetailDtoList = this.reviewService.reInfo();
		log.info("[reviewController] 리뷰 공통코드 : {}", commonCdDetailDtoList);

		return commonCdDetailDtoList;
	}

	@PostMapping("/reCreatePost")
	@ResponseBody
	public int reCreate(@RequestBody ReviewDto reviewDto) {
		log.info("reviewVO : {}", reviewDto);

		int res = 0;
		res = this.reviewService.reCreate(reviewDto);

		return res;
	}

	@GetMapping("/reDetail")
	@ResponseBody
	public List<ReviewDto> reDetail(HttpServletRequest request) {
		String userId = this.srvcRequstController.userIdChk(request);

		List<ReviewDto> reviewDtoList = this.reviewService.reDetail(userId);

		return reviewDtoList;

	}

	@GetMapping("/reMgmt")
	public String reMgmt(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 리스트 토탈 구하기
		// 필요값 : vSrvcRequstVO.userId, vSrvcRequstVO.emplyrTy='ET02'
		String userId = this.srvcRequstController.userIdChk(request);
		UsersDto usersDto = this.srvcRequstService.userChk(userId);
		map.put("userId", userId);
		map.put("vSrvcRequstVO.emplyrTy", usersDto.getEmplyrTy());
		int rqrvTotal = this.reviewService.rqrvTotal(map);

		// 리뷰 건수 토탈 구하기 
		// 필요값 : userId, emplyrTy
		map.put("userId", usersDto.getUserId());
		log.info("reMgMt -> totalMap(rvTotal 파라미터) : {}", map);
		int rvTotal = this.reviewService.reviewTotal(map);

		// 별점
		List<ReviewDto> reviewDtoList = this.reviewService.reDetail(userId);
		int scoreSum = 0;
		for (ReviewDto reviewDto : reviewDtoList) {
			scoreSum += reviewDto.getReScore();
		}

		float scoreAvgF = (float)scoreSum / reviewDtoList.size();
		String rvScoreAvg = String.format("%.1f", scoreAvgF);
		log.info("값 : {}", rvScoreAvg);

		List<CommonCdDetailDto> cdDetailList = this.reviewService.reInfo();

		model.addAttribute("rqrvTotal", rqrvTotal);
		model.addAttribute("rvTotal", rvTotal);
		model.addAttribute("reviewVOList", new Gson().toJson(reviewDtoList));
		model.addAttribute("rvScoreAvg", rvScoreAvg);
		model.addAttribute("cdDetailList", new Gson().toJson(cdDetailList));

		return "srvcRqReview/reviewMgmt";
	}

	@GetMapping("/proReMgmt")
	public String proReMgmt(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String userId = this.srvcRequstController.userIdChk(request);
		UsersDto usersDto = this.srvcRequstService.userChk(userId);

		// 리뷰 건수 토탈 구하기 
		// 필요값 : userId, emplyrTy
		map.put("userId", usersDto.getUserId());

		int rvTotal = this.reviewService.proReviewTotal(map);

		// 별점
		List<ReviewDto> reviewDtoList = this.reviewService.proReDetail(userId);
		int scoreSum = 0;
		for (ReviewDto reviewDto : reviewDtoList) {
			scoreSum += reviewDto.getReScore();
		}
		float scoreAvgF = (float)scoreSum / reviewDtoList.size();
		String rvScoreAvg = String.format("%.1f", scoreAvgF);

		List<CommonCdDetailDto> cdDetailList = this.reviewService.reInfo();
		model.addAttribute("rvTotal", rvTotal);
		model.addAttribute("reviewVOList", new Gson().toJson(reviewDtoList));
		model.addAttribute("rvScoreAvg", rvScoreAvg);
		model.addAttribute("cdDetailList", new Gson().toJson(cdDetailList));

		return "srvcRqReview/proReviewMgmt";
	}

	@PostMapping("/reviewList")
	@ResponseBody
	public Map<String, Object> reviewList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {
		String userId = this.srvcRequstController.userIdChk(request);
		map.put("userId", userId);

		int size = (Integer.parseInt(map.get("size").toString()));
		map.put("size", size);

		// 리뷰 목록 가져오기
		List<ReviewDto> reviewDtoList = this.reviewService.reviewList(map);
		int total = this.reviewService.reviewTotal(map);

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		// 목록 paging처리
		ArticlePage<ReviewDto> data
			= new ArticlePage<ReviewDto>(total, Integer.parseInt(currentPage), size, reviewDtoList, keyword);
		String url = "/srvcRqReview/reviewMgmt";
		data.setUrl(url);

		// 작성 안된 review 수
		int res = this.reviewService.reviewNoWrCnt(map);

		Map<String, Object> reviewListMap = new HashMap<String, Object>();
		reviewListMap.put("data", data);
		reviewListMap.put("reviewNoWrCnt", res);

		log.info("reviewList-> userId from reviewList : {}", userId);
		log.info("reviewList-> reviewVOList : {}", reviewDtoList);
		log.info("reviewList-> 페이징 처리 : {}", data);
		return reviewListMap;
	}

	@PostMapping("/proReviewList")
	@ResponseBody
	public Map<String, Object> proReviewList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {
		String userId = this.srvcRequstController.userIdChk(request);
		map.put("userId", userId);
		log.info("reviewList-> userId : {}", userId);

		int size = (Integer.parseInt(map.get("size").toString()));
		map.put("size", size);

		// 리뷰 목록 가져오기
		List<ReviewDto> reviewDtoList = this.reviewService.proReviewList(map);
		log.info("proReviewList controller -> reviewVOList : {}", reviewDtoList);
		int total = this.reviewService.proReviewTotal(map);

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		// 목록 paging처리
		ArticlePage<ReviewDto> data
			= new ArticlePage<ReviewDto>(total, Integer.parseInt(currentPage), size, reviewDtoList, keyword);
		String url = "/srvcRqReview/proReviewMgmt";
		data.setUrl(url);

		// 작성 안된 review 수
		int res = this.reviewService.reviewNoWrCnt(map);

		Map<String, Object> reviewListMap = new HashMap<String, Object>();
		reviewListMap.put("data", data);
		reviewListMap.put("reviewNoWrCnt", res);

		log.info("페이징 처리 : {}", data);
		return reviewListMap;
	}

	@PostMapping("/showReview")
	@ResponseBody
	public Map<String, Object> showReview(@RequestParam int reNo) {
		Map<String, Object> showReviewMap = this.reviewService.showReview(reNo);

		return showReviewMap;
	}

	@GetMapping("/reTyChrtList")
	@ResponseBody
	public Map<String, Object> reTyChrtList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String userId = this.srvcRequstController.userIdChk(request);
		paramMap.put("userId", userId);

		List<ReviewDto> reTyChrtList = new ArrayList<ReviewDto>();
		reTyChrtList = this.reviewService.reTyChrtList(paramMap);
		log.info("[reTyChartLsit] : ", reTyChrtList);

		List<CommonCdDetailDto> reviewCodeNmList = new ArrayList<CommonCdDetailDto>();
		reviewCodeNmList = this.reviewService.reInfo();

		List<ReviewDto> reScoreChrtList = new ArrayList<ReviewDto>();
		reScoreChrtList = this.reviewService.reScoreChrtList(paramMap);

		paramMap.put("reTyChrtList", reTyChrtList);
		paramMap.put("reScoreChrtList", reScoreChrtList);
		paramMap.put("reviewCodeNmList", reviewCodeNmList);

		return paramMap;
	}

	@GetMapping("/proReTyChrtList")
	@ResponseBody
	public Map<String, Object> proReTyChrtList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String userId = this.srvcRequstController.userIdChk(request);
		paramMap.put("userId", userId);

		List<ReviewDto> reTyChrtList = new ArrayList<ReviewDto>();
		reTyChrtList = this.reviewService.proReTyChrtList(paramMap);
		log.info("[reScoreChartLsit] : ", reTyChrtList);

		List<CommonCdDetailDto> reviewCodeNmList = new ArrayList<CommonCdDetailDto>();
		reviewCodeNmList = this.reviewService.reInfo();

		List<ReviewDto> reScoreChrtList = new ArrayList<ReviewDto>();
		reScoreChrtList = this.reviewService.proReScoreChrtList(paramMap);

		paramMap.put("reTyChrtList", reTyChrtList);
		paramMap.put("reScoreChrtList", reScoreChrtList);
		paramMap.put("reviewCodeNmList", reviewCodeNmList);

		return paramMap;
	}

	@GetMapping("/reScoreChrtList")
	@ResponseBody
	public List<ReviewDto> reScoreChrtList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String userId = this.srvcRequstController.userIdChk(request);
		paramMap.put("userId", userId);

		List<ReviewDto> reScoreChrtList = new ArrayList<ReviewDto>();
		reScoreChrtList = this.reviewService.reScoreChrtList(paramMap);

		return reScoreChrtList;

	}

}