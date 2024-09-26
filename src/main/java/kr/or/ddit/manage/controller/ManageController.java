package kr.or.ddit.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.admin.faq.dto.FaqDto;
import kr.or.ddit.admin.notice.dto.NoticeDto;
import kr.or.ddit.admin.usersSearch.dto.UsersDto;
import kr.or.ddit.dto.*;
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

import kr.or.ddit.admin.decl.service.DeclService;
import kr.or.ddit.admin.faq.service.FaqService;
import kr.or.ddit.admin.notice.service.NoticeService;
import kr.or.ddit.admin.oneInqry.service.OneInqryService;
import kr.or.ddit.admin.usersSearch.service.UsersSearchService;
import kr.or.ddit.manage.service.ManageService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.OneInqryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/manage")
@Controller
@RequiredArgsConstructor
public class ManageController {

	private final ManageService manageService;
	private final DeclService declService;
	private final OneInqryService oneInqryService;
	private final NoticeService noticeService;
	private final FaqService faqService;
	private final UsersSearchService usersSearchService;
	private final FileuploadService fileuploadService;

	public OneInqryDto oneInqryDto = new OneInqryDto();

	public String userIdChk(HttpServletRequest request) {
		String userId = "";
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

	//유저설치 디테일
	@GetMapping("/userDetail")
	public String usersDetail(@RequestParam String userId, Model model) {
		log.info("detail->usersDetail: {}", userId);

		UsersDto usersDto = this.usersSearchService.detail(userId);
		log.info("detail->usersVO: {}", usersDto);

		String profile = this.usersSearchService.getUserProfile(userId);

		log.info("profle {}", profile);
		model.addAttribute("usersVO", usersDto);
		model.addAttribute("profile", profile);

		return "manage/userDetail";
	}

	//공지사항 리스트
	@GetMapping("/notice")
	public String noticeList(Model model) {
		List<NoticeDto> list = noticeService.getAllNoticeList();
		model.addAttribute("noticeList", list);

		DongChartDto dongVO = this.manageService.test();
		log.info("dongVO from noticeList : {}" , dongVO);

		model.addAttribute("dongVO", dongVO);

		return "manage/notice";
	}

	//공지사항 등록
	@GetMapping(value = "/create")
	public String create(NoticeDto noticeDto, HttpSession session) {
		log.info("create->noticeVO:" + noticeDto);

		return "manage/create2";
	}

	//공지사항 상세
	@GetMapping(value = "/detail")
	public String noticeDetail(@RequestParam int noticeNo, Model model) {
		log.info("detail->noticeDetail:" + noticeNo);
		// 조회수 증가
		this.noticeService.increaseViewCount(noticeNo);
		// 공지사항 정보 조회
		NoticeDto noticeDto = this.noticeService.detail(noticeNo);
		model.addAttribute("noticeVO", noticeDto);

		NoticeDto noticeVOAtchDtoList = this.noticeService.sprviseAtchmnflDto(noticeNo);
		model.addAttribute("sprviseAtchmnflVOList", noticeVOAtchDtoList);

		return "manage/detail";
	}

	//FAQ 리스트
	@GetMapping("/faq")
	public String faqList(Model model) {
		List<FaqDto> faqList = faqService.faqList();
		model.addAttribute("faqList", faqList);
		return "manage/faq";
	}

	//FAQ 등록
	@GetMapping(value = "/create", params = "register")
	public String createRegister(FaqDto faqDto) {
		log.info("createRegister->faqVO:" + faqDto);
		return "manage/create";
	}

	//1대1문의 리스트
	@GetMapping("/oneInqryList")
	public String oneInqrylist() {
		return "manage/oneInqryList";
	}

	//1대1 문의 미답변
	@GetMapping("/oneInqryNoAnswerList")
	public String oneInqryNoAnswerList() {
		return "manage/oneInqryNoAnswerList";
	}

	@PostMapping("/oneInqryNoAnswerList")
	@ResponseBody
	public ArticlePage<OneInqryDto> oneInqryNoAnswerList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {
		String userId = "";
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

		String url = "/manage/oneIqnryNoAnswerList";
		data.setUrl(url);

		map.put("data", data);
		return data;
	}

	// 1대1 문의 답변성공
	@GetMapping("/oneInqrySuccessList")
	public String oneInqrySuccessList() {
		return "manage/oneInqrySuccessList";
	}

	// 답변 완료 목록 출력
	@PostMapping("/oneInqrySuccessList")
	@ResponseBody
	public ArticlePage<OneInqryDto> oneInqrySuccessList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {
		String userId = "";
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

		String url = "/manage/oneIqnrySuccessList";
		data.setUrl(url);

		return data;
	}

	@GetMapping("/oneInqryDetail")
	public String oneInqryDetail(@RequestParam("oneInqryNo") int oneInqryNo, Model model) {
		String userId = "";
		oneInqryDto.setOneInqryNo(oneInqryNo);
		oneInqryDto = this.oneInqryService.oneInqryDetail(oneInqryDto, userId);

		log.info("1:1 detail -> userId  : {}" , userId);

		int sprviseAtchmnflNo = oneInqryDto.getSprviseAtchmnflNo();
		List<SprviseAtchmnflDto> sprviseAtchmnflDtoList = this.fileuploadService.getsprviseAtchmnfl(sprviseAtchmnflNo);
		log.info("[oneinqryController] detail -> sprviseAtchmnflVOList : {}" , sprviseAtchmnflDtoList);

		log.info("[oneinqryController] detail -> oneInqryVO.getSprviseAtchmnflVOList : {}", oneInqryDto.getSprviseAtchmnflDtoList());

		oneInqryDto = this.oneInqryService.oneInqryDetail(oneInqryDto, userId);
		oneInqryDto.setSprviseAtchmnflDtoList(sprviseAtchmnflDtoList);

		model.addAttribute("oneInqryVO", oneInqryDto);
		if ("testAdmin".equals(userId)) {
			model.addAttribute("loginId", "admin");
		}

		return "manage/oneInqryDetail";

	}

	@PostMapping("/oneInqryUpdatePost")
	public String oneInqryUpdatePost(@RequestParam Map<String, Object> oneInqryUpdateMap,
		@RequestParam("uploadFiles") List<MultipartFile> uploadFiles,
		HttpServletRequest request) {
		// 결과값
		// 사전문의 업데이트
		// 사전문의 업데이트 + 기존 사진 삭제
		// 사전문의 업데이트 + 기존 사진 삭제 + 새로운 사진 업데이트
		int res = 0;
		String userId = "";
		userId = userIdChk(request);

		res = this.oneInqryService.oneInqryUpdatePost(oneInqryUpdateMap, uploadFiles, userId);

		return "redirect:/manage/oneInqryDetail?oneInqryNo=" + oneInqryUpdateMap.get("oneInqryNo");
	}

	@GetMapping("/decl")
	public String lbrbbs(Model model, Map<String, Object> map, HttpServletRequest request,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		map.put("currentPage", currentPage);
		List<SntncDeclDto> lbrbbsList = this.declService.decllbrSelect(map);
		log.info("lbrbbs -> lbrbbsList from lbrbbs : {} " , lbrbbsList);
		model.addAttribute("lbrbbsList", lbrbbsList);

		return "manage/decl";
	}

	@GetMapping("/userdecl")
	public String userdecl(Model model, Map<String, Object> map, HttpServletRequest request,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		map.put("currentPage", currentPage);
		List<SntncDeclDto> lbrbbsList = this.declService.decllbrSelect(map);
		log.info("lbrbbs -> lbrbbsList : {}" , lbrbbsList);
		model.addAttribute("lbrbbsList", lbrbbsList);

		return "manage/userdecl";
	}

	@GetMapping("/resignPro")
	public String btfInqryList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		map.put("currentPage", currentPage);

		// 전체 리스트 출력
		List<OneInqryDto> oneInqryDtoList = this.oneInqryService.resignProList(map);

		model.addAttribute("oneInqryVOList", oneInqryDtoList);

		return "manage/resignProList";
	}

	@PostMapping("/userDanger")
	@ResponseBody
	public int userDanger(String userId) {
		log.info("update -> result : {}" , userId);
		int result = this.usersSearchService.userDanger(userId);
		return result;
	}

	// 검색 목록 출력
	@PostMapping("/resignProList")
	@ResponseBody
	public ArticlePage<OneInqryDto> resignProList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		List<OneInqryDto> oneInqryDtoList = this.oneInqryService.resignProList(map);

		int total = this.oneInqryService.getTotalResignPro(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<OneInqryDto> data = new ArticlePage<OneInqryDto>(total, Integer.parseInt(currentPage),
			size, oneInqryDtoList, keyword);

		String url = "/manage/resignProList";
		data.setUrl(url);

		return data;
	}

	// 유저 탈퇴 수락
	@ResponseBody
	@PostMapping("/proSecssion")
	public int proSecssion(String proId) {

		return this.oneInqryService.proSecssion(proId);
	}

	//메인 페이지 가기
	@GetMapping("/main")
	public String main(Model model) {
		DongChartDto dongVO = this.manageService.test();
		log.info("dongVO : {}" , dongVO);

		DongChartDto2 dongVO2 = this.manageService.test2();
		log.info("dongVO2 : {}" , dongVO2);

		DongChartDto3 dongVO3 = this.manageService.test3();
		log.info("dongVO3 : {}" , dongVO3);

		model.addAttribute("dongVO", dongVO);
		model.addAttribute("dongVO2", dongVO2);
		model.addAttribute("dongVO3", dongVO3);

		return "manage/main";
	}

	//메인 페이지 가기
	@GetMapping("/main2")
	public String main2(Model model) {
		return "manage/main2";
	}

}