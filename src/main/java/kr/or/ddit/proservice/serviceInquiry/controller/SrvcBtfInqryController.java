package kr.or.ddit.proservice.serviceInquiry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.SrvcBtfInqryDto;
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

import kr.or.ddit.proservice.serviceInquiry.service.SrvcBtfInqryService;
import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/srvcBtfInqry")
@RequiredArgsConstructor
public class SrvcBtfInqryController {

	private final SrvcBtfInqryService srvcBtfInqryService;
	private final FileuploadService fileuploadService;

	public String userIdChk(HttpServletRequest request) {
		String userId = "";
		HttpSession session = request.getSession();

		if (((HashMap)session.getAttribute("proSession")) != null) {
			userId = ((HashMap)session.getAttribute("proSession")).get("userId").toString();
		} else if (((HashMap)session.getAttribute("memSession")) != null) {
			userId = ((HashMap)session.getAttribute("memSession")).get("userId").toString();
		}
		return userId;
	}

	// 전체 목록 출력
	@GetMapping("/btfInqryList")
	public String btfInqryList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 전체 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList from btfInqryList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/btfInqryList";
	}

	// 미답변 목록 출력
	@GetMapping("/btfInqryNoAnswerList")
	public String btfInqryNoAnswerList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 미답변 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryNoAnswerList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList from btfInqryNoAnswerList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/btfInqryNoAnswerList";
	}

	// 미답변 목록 출력
	@GetMapping("/btfInqrySuccessList")
	public String btfInqrySuccessList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 미답변 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqrySuccessList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList from btfInqrySuccessList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/btfInqrySuccessList";
	}

	// 전체 목록 출력
	@GetMapping("/myBtfInqryList")
	public String myBtfInqryList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 전체 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList from myBtfInqryList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/myBtfInqryList";
	}

	// 미답변 목록 출력
	@GetMapping("/myBtfInqryNoAnswerList")
	public String myBtfInqryNoAnswerList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 미답변 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryNoAnswerList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList from myBtfInqryNoAnswerList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/myBtfInqryNoAnswerList";
	}

	// 미답변 목록 출력
	@GetMapping("/myBtfInqrySuccessList")
	public String myBtfInqrySuccessList(Model model, HttpServletRequest request, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdChk(request);

		map.put("userId", userId);
		map.put("currentPage", currentPage);

		// 미답변 리스트 출력
		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqrySuccessList(map);

		model.addAttribute("vSrvcBtfInqryVOList", vSrvcBtfInqryVOList);
		log.info("btfInqryList -> vsrvcBtfInqryVOList : {}", vSrvcBtfInqryVOList);

		return "srvcBtfInqry/myBtfInqrySuccessList";
	}

	// 검색 목록 출력
	@PostMapping("/searchList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> searchList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryList(map);

		int total = this.srvcBtfInqryService.getTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/btfIqnryList";
		data.setUrl(url);

		return data;
	}

	// 답변 안된 목록 출력
	@PostMapping("/btfInqryNoAnswerList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> btfInqryNoAnswerList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryNoAnswerList(map);

		int total = this.srvcBtfInqryService.getNoAnswerTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/btfIqnryNoAnswerList";
		data.setUrl(url);

		return data;
	}

	// 답변 완료 목록 출력
	@PostMapping("/btfInqrySuccessList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> btfInqrySuccessList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqrySuccessList(map);

		int total = this.srvcBtfInqryService.getSuccessTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/btfIqnrySuccessList";
		data.setUrl(url);

		return data;
	}

	// 검색 목록 출력
	@PostMapping("/mySearchList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> mySearchList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryList(map);

		int total = this.srvcBtfInqryService.getTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/myBtfIqnryList";
		data.setUrl(url);

		return data;
	}

	// 답변 안된 목록 출력
	@PostMapping("/myBtfInqryNoAnswerList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> myBtfInqryNoAnswerList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqryNoAnswerList(map);

		int total = this.srvcBtfInqryService.getNoAnswerTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/myBtfIqnryNoAnswerList";
		data.setUrl(url);

		return data;
	}

	// 답변 완료 목록 출력
	@PostMapping("/myBtfInqrySuccessList")
	@ResponseBody
	public ArticlePage<V_SrvcBtfInqryDto> myBtfInqrySuccessList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request) {

		String userId = userIdChk(request);
		map.put("userId", userId);

		List<V_SrvcBtfInqryDto> vSrvcBtfInqryVOList = this.srvcBtfInqryService.btfInqrySuccessList(map);

		int total = this.srvcBtfInqryService.getSuccessTotal(map);

		int size = 10;

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();

		ArticlePage<V_SrvcBtfInqryDto> data = new ArticlePage<V_SrvcBtfInqryDto>(total, Integer.parseInt(currentPage),
			size, vSrvcBtfInqryVOList, keyword);

		String url = "/srvcBtfInqry/myBtfIqnrySuccessList";
		data.setUrl(url);

		return data;
	}

	@GetMapping("/btfInqryDetail")
	public String btfInqryDetail(@RequestParam("btfInqryNo") int btfInqryNo, Model model,HttpServletRequest request) {

		String userId = userIdChk(request);
		V_SrvcBtfInqryDto vSrvcBtfInqryVO = V_SrvcBtfInqryDto.builder().btfInqryNo(btfInqryNo).build();
		vSrvcBtfInqryVO = this.srvcBtfInqryService.btfInqryDetail(vSrvcBtfInqryVO, userId);

		int sprviseAtchmnflNo = vSrvcBtfInqryVO.getSprviseAtchmnflNo();
		this.fileuploadService.getsprviseAtchmnfl(sprviseAtchmnflNo);

		log.info("btfInqryDetail -> vSrvcBtfInqryVO : {}", vSrvcBtfInqryVO);

		vSrvcBtfInqryVO = this.srvcBtfInqryService.btfInqryDetail(vSrvcBtfInqryVO, userId);
		log.info("btfInqryDetail -> vSrvcBtfInqryVO.getSprviseAtchmnflVOList() : {}",
			vSrvcBtfInqryVO.getSprviseAtchmnflDtoList());
		log.info("btfInqryDetail -> vSrvcBtfInqryVO.getEmplyrTy : {}", vSrvcBtfInqryVO.getEmplyrTy());

		model.addAttribute("vSrvcBtfInqryVO", vSrvcBtfInqryVO);

		return "srvcBtfInqry/btfInqryDetail";
	}

	@PostMapping("/updateAnswer")
	@ResponseBody
	public int updateAnswer(@RequestBody Map<String, Object> param,HttpServletRequest request) {
		int res;

		String userId = userIdChk(request);
		log.info("번호 : {}", param.get("btfInqryNo"));

		int btfInqryNo = Integer.parseInt(String.valueOf(param.get("btfInqryNo")));
		String btfInqryAnswerCn = (String)param.get("btfInqryAnswerCn");

		Map<String, Object> updateParamMap = new HashMap<>();
		updateParamMap.put("btfInqryNo", btfInqryNo);
		updateParamMap.put("btfInqryAnswerCn", btfInqryAnswerCn);

		res = this.srvcBtfInqryService.updateAnswer(updateParamMap, userId);

		return res;
	}

	@GetMapping("/btfInqryCreate")
	public String btfInqryCreate(@RequestParam("proId") String proId) {
		return "srvcBtfInqry/btfInqryCreate";
	}

	@PostMapping("/btfInqryCreatePost")
	public String btfInqryCreatePost(@RequestParam Map<String, Object> btfInqryInfoMap,
		@RequestParam("uploadFiles") List<MultipartFile> uploadFiles, HttpServletRequest request) {

		String userId = userIdChk(request);

		log.info("[btfInqryController] createPost -> proId : {}", btfInqryInfoMap.get("proId"));
		SrvcBtfInqryDto srvcBtfInqryDto = new SrvcBtfInqryDto();
		srvcBtfInqryDto.setBtfInqrySj((String)btfInqryInfoMap.get("btfInqrySj"));
		srvcBtfInqryDto.setBtfInqryCn((String)btfInqryInfoMap.get("btfInqryCn"));
		srvcBtfInqryDto.setProId((String)btfInqryInfoMap.get("proId"));

		log.info("[btfInqryController] btfInqryCreatePost->userId : {}", userId);
		srvcBtfInqryDto.setMberId(userId);

		this.srvcBtfInqryService.btfInqryCreatePost(srvcBtfInqryDto, uploadFiles);

		return "redirect:/srvcBtfInqry/btfInqryList?mberCheck=mber";
	}

	@PostMapping("/btfInqryUpdatePost")
	public String btfInqryUpdatePost(@RequestParam Map<String, Object> btfInqryUpdateMap,
		@RequestParam("uploadFiles") List<MultipartFile> uploadFiles, HttpServletRequest request) {
		// 결과값
		// 사전문의 업데이트
		// 사전문의 업데이트 + 기존 사진 삭제
		// 사전문의 업데이트 + 기존 사진 삭제 + 새로운 사진 업데이트

		String userId = userIdChk(request);
		log.info("btfInqryUpdatePost -> btfInqryUpdateMap : {}", btfInqryUpdateMap.toString());
		log.info("btfInqryUpdatePost -> uploadFiles : {}", uploadFiles.toString());

		this.srvcBtfInqryService.btfInqryUpdatePost(btfInqryUpdateMap, uploadFiles, userId);

		return "redirect:/srvcBtfInqry/btfInqryDetail?btfInqryNo=" + btfInqryUpdateMap.get("btfInqryNo");
	}
}
