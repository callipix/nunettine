package kr.or.ddit.admin.oneInqry.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.OneInqryDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.oneInqry.service.OneInqryService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.OneInqryAnswerDto;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/oneInqry")
@RequiredArgsConstructor
public class oneInqryController {

	private final OneInqryService oneInqryService;
	private final FileuploadService fileuploadService;

	// 세션에서 가져올 아이디
	String userId = "";
	OneInqryDto oneInqryDto = new OneInqryDto();
	OneInqryAnswerDto oneInqryAnwerVO = new OneInqryAnswerDto();

	public String userIdChk(HttpServletRequest request) {
		userId = "";
		HttpSession session = request.getSession();

		if (((HashMap)session.getAttribute("proSession")) != null) {
			userId = ((HashMap)session.getAttribute("proSession")).get("userId").toString();
		} else if (((HashMap)session.getAttribute("memSession")) != null) {
			userId = ((HashMap)session.getAttribute("memSession")).get("userId").toString();
		} else if (session.getAttribute("admSession") != null) {
			UsersDto usersDto = (UsersDto)session.getAttribute("admSession");
			userId = usersDto.getUserId();
		}

		log.info(" [관리자] userId : {}", userId);
		return userId;
	}

	@GetMapping("/oneInqryCreate")
	public String oneInqryCreate() {
		return "oneInqry/oneInqryCreate";
	}

	@PostMapping("/oneInqryCreatePost")
	public String btfInqryCreatePost(@RequestParam Map<String, Object> oneInqryInfoMap,
		@RequestParam("uploadFiles") List<MultipartFile> uploadFiles,
		HttpServletRequest request) {

		userId = userIdChk(request);

		log.info("관리자문의 컨트롤/ 관리자 문의 파람맵{}", oneInqryInfoMap);

		OneInqryDto oneInqryDto = new OneInqryDto();
		oneInqryDto.setOneInqrySj((String)oneInqryInfoMap.get("oneInqrySj"));
		oneInqryDto.setOneInqryCn((String)oneInqryInfoMap.get("oneInqryCn"));
		oneInqryDto.setUserId(userId);

		int res = 0;

		res = this.oneInqryService.oneInqryCreatePost(oneInqryDto, uploadFiles);

		return "redirect:/oneInqry/oneInqryList";
	}

	// 목록
	@GetMapping("/oneInqryList")
	public String oneInqrylist() {
		return "oneInqry/oneInqryList";
	}

	// 목록
	@GetMapping("/oneInqryNoAnswerList")
	public String oneInqryNoAnswerList() {
		return "oneInqry/oneInqryNoAnswerList";
	}

	// 목록
	@GetMapping("/oneInqrySuccessList")
	public String oneInqrySuccessList() {
		return "oneInqry/oneInqrySuccessList";
	}

	// 목록
	@GetMapping("/myOneInqryList")
	public String myOneInqrylist() {
		return "oneInqry/myOneInqryList";
	}

	// 목록
	@GetMapping("/myOneInqryNoAnswerList")
	public String myOneInqryNoAnswerList() {
		return "oneInqry/myOneInqryNoAnswerList";
	}

	// 목록
	@GetMapping("/myOneInqrySuccessList")
	public String myOneInqrySuccessList() {
		return "oneInqry/myOneInqrySuccessList";
	}

	//전체 및 검색 목록 출력
	@PostMapping("/searchList")
	@ResponseBody
	public Map<String, Object> searchList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.searchList(map);
		log.info("1:1문의 전체 목록 : {}", oneInqryDtoList);

		int total = this.oneInqryService.getTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/oneInqryList";
		data.setUrl(url);

		map.put("data", data);

		return map;
	}

	// 답변 안된 목록 출력
	@PostMapping("/oneInqryNoAnswerList")
	@ResponseBody
	public ArticlePage<OneInqryDto> oneInqryNoAnswerList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.oneInqryNoAnswerList(map);

		int total = this.oneInqryService.getNoAnswerTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/oneIqnryNoAnswerList";
		data.setUrl(url);

		map.put("data", data);
		return data;
	}

	// 답변 완료 목록 출력
	@PostMapping("/oneInqrySuccessList")
	@ResponseBody
	public ArticlePage<OneInqryDto> oneInqrySuccessList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {
		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.oneInqrySuccessList(map);

		int total = this.oneInqryService.getSuccessTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/oneIqnrySuccessList";
		data.setUrl(url);

		return data;
	}

	//전체 및 검색 목록 출력
	@PostMapping("/mySearchList")
	@ResponseBody
	public Map<String, Object> mySearchList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.searchList(map);
		log.info("1:1문의 전체 목록 for mySearchList: {}", oneInqryDtoList);

		int total = this.oneInqryService.getTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/myOneInqryList";
		data.setUrl(url);

		map.put("data", data);

		return map;
	}

	// 답변 안된 목록 출력
	@PostMapping("/myOneInqryNoAnswerList")
	@ResponseBody
	public ArticlePage<OneInqryDto> myOneInqryNoAnswerList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.oneInqryNoAnswerList(map);

		int total = this.oneInqryService.getNoAnswerTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/myOneIqnryNoAnswerList";
		data.setUrl(url);

		map.put("data", data);
		return data;
	}

	// 답변 완료 목록 출력
	@PostMapping("/myOneInqrySuccessList")
	@ResponseBody
	public ArticlePage<OneInqryDto> myOneInqrySuccessList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		userId = userIdChk(request);
		map.put("userId", userId);

		List<OneInqryDto> oneInqryDtoList =
			this.oneInqryService.oneInqrySuccessList(map);

		int total = this.oneInqryService.getSuccessTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data
			= new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage), size, oneInqryDtoList, keyword);

		String url = "/oneInqry/myOneIqnrySuccessList";
		data.setUrl(url);

		return data;
	}

	@GetMapping("/oneInqryDetail")
	public String oneInqryDetail(@RequestParam("oneInqryNo") int oneInqryNo, String userId, Model model) {
		OneInqryDto oneInqryDto = new OneInqryDto();
		oneInqryDto.setOneInqryNo(oneInqryNo);
		oneInqryDto = this.oneInqryService.oneInqryDetail(oneInqryDto, userId);

		log.info("1:1 detail -> userId  : " + userId);

		int sprviseAtchmnflNo = oneInqryDto.getSprviseAtchmnflNo();
		List<SprviseAtchmnflDto> sprviseAtchmnflDtoList = this.fileuploadService.getsprviseAtchmnfl(sprviseAtchmnflNo);
		log.info("[oneinqryController] detail -> sprviseAtchmnflVOList : {}", sprviseAtchmnflDtoList);

		log.info("[oneinqryController] detail -> oneInqryVO.getSprviseAtchmnflVOList : {}",
			oneInqryDto.getSprviseAtchmnflDtoList());

		oneInqryDto = this.oneInqryService.oneInqryDetail(oneInqryDto, userId);
		oneInqryDto.setSprviseAtchmnflDtoList(sprviseAtchmnflDtoList);

		model.addAttribute("oneInqryVO", oneInqryDto);
		if ("testAdmin".equals(userId)) {
			model.addAttribute("loginId", "admin");
		}

		return "oneInqry/oneInqryDetail";

	}

	@PostMapping("/oneInqryUpdatePost")
	public String oneInqryUpdatePost(@RequestParam Map<String, Object> oneInqryUpdateMap,
		@RequestParam("uploadFiles") List<MultipartFile> uploadFiles,
		HttpServletRequest request) {
		// 결과값
		// 사전문의 업데이트
		// 사전문의 업데이트 + 기존 사진 삭제
		// 사전문의 업데이트 + 기존 사진 삭제 + 새로운 사진 업데이트
		userId = userIdChk(request);
		return "redirect:/oneInqry/oneInqryDetail?oneInqryNo=" + oneInqryUpdateMap.get("oneInqryNo");
	}

	@PostMapping("/updateAnswer")
	@ResponseBody
	public int updateAnswer(@RequestBody Map<String, Object> param) {
		int res = 0;

		int oneInqryNo = Integer.parseInt(String.valueOf(param.get("oneInqryNo")));
		String oneInqryAnswerCn = (String)param.get("oneInqryAnswerCn");
		userId = (String)param.get("userId");

		Map<String, Object> updateParamMap = new HashMap<String, Object>();
		updateParamMap.put("oneInqryNo", oneInqryNo);
		updateParamMap.put("oneInqryAnswerCn", oneInqryAnswerCn);
		updateParamMap.put("userId", userId);

		res = this.oneInqryService.updateAnswer(updateParamMap, userId);

		return res;
	}

	@PostMapping("/resignPro")
	public String resignPro(@RequestParam("resignReason") String resignReason,
		@RequestParam(value = "resignReasonInput", required = false) String resignReasonInput,
		@RequestParam("userId") String userId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if ("기타".equals(resignReason)) {
			map.put("oneIqnryCn", resignReasonInput);
		} else {
			map.put("oneIqnryCn", resignReason);

		}
		log.info("프로 탈퇴 사유 : {}", resignReason);
		log.info("프로 탈퇴 사유(기타) : {}", resignReasonInput);
		log.info("프로 탈퇴 아이디: {}", userId);
		log.info("프로 탈퇴 map : {}", map);

		return "redirect:/pro/proMypage";
	}

}