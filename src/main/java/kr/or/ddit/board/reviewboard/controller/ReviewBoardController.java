package kr.or.ddit.board.reviewboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dto.AftusBbscttAnswerDto;
import kr.or.ddit.dto.AftusBbscttDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.reviewboard.service.ReviewBoardService;
import kr.or.ddit.util.ArticlePage3;
import kr.or.ddit.dto.SrvcRequstDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reviewBoard")
@RequiredArgsConstructor
public class ReviewBoardController {

	private final ReviewBoardService reviewBoardService;

	private String userId(HttpServletRequest request) {
		   //세션값으로 아이디 가져오기
		      
		Object proSession = request.getSession().getAttribute("proSession");
		Object memSession = request.getSession().getAttribute("memSession");
		      
		   
		if(proSession !=null && proSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)proSession).get("userId");
			System.out.println("proSession : "+userId);
		      
			return userId != null ? userId.toString() : null;
		}
		if(memSession !=null && memSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)memSession).get("userId");
			System.out.println("memSession : " +userId);
		      
			return userId != null ? userId.toString() : null;

		}

		return null;
	}

	@GetMapping("/main")
	public String main(HttpServletRequest request
			,Model model) {
		
		String userId = userId(request);
		
		log.info("main -> userId : " + userId);
		

		model.addAttribute("userId", userId);
		
		return "reviewBoard/index";
	}
	
	@ResponseBody
	@PostMapping("/listAjax")
	public ArticlePage3<AftusBbscttDto> listAjax (@RequestBody(required=false) Map<String, Object> map) {
		
		int size = 10;
		int total = this.reviewBoardService.getTotal(map);
		
		log.info("listAjax->total : " + total);
		
		map.put("total", total);
		
		log.info("listAjax -> map : " + map);
		
		List<AftusBbscttDto> aftusBbscttDtoList = this.reviewBoardService.list(map);

		log.info("listAjax -> aftusBbscttVoList" + aftusBbscttDtoList);
		
		String currentPage = map.get("currentPage").toString();	
		
		log.info(currentPage);
		
		String keyword = "";
		
		//map에 userId가 있으면 userId를 keyword로 바꿔서 파라미터로 넘겨주고
		//userId가 없으면 그대로 keyword를 넘겨주기 위한 처리
		if(map.containsKey("userId")) {
			keyword = map.get("userId").toString();
			log.info("listMyReview->userId : " + keyword);
		} else {
			keyword = map.get("keyword").toString();
			log.info("listMyReview->userId : " + keyword);
		}
		
		String selectColumn = "";
		
		if(map.containsKey("selectColumn")) {
			selectColumn = map.get("selectColumn").toString();
			log.info("listAjax->selectColumn : " + selectColumn);
		}
		
		ArticlePage3<AftusBbscttDto> data = new ArticlePage3<AftusBbscttDto>(total,
				Integer.parseInt(currentPage), size, aftusBbscttDtoList, keyword, selectColumn);

		log.info("listAjax->data : " + data);
		
		return data;
	}
	
	@ResponseBody
	@PostMapping("/listModal")
	public List<SrvcRequstDto> listModal (String userId) {
		
		log.info("userId : " + userId);
		
		List<SrvcRequstDto> srvcRequstDtoList = this.reviewBoardService.listModal(userId);
		
		log.info("listModal -> SrvcRequstVOList" + srvcRequstDtoList);
		
		return srvcRequstDtoList;
	}
	

	@GetMapping("/create") 
	public String create(int srvcRequstNo,
						Model model) { 

		log.info("srvcRequstNo" + srvcRequstNo);
		model.addAttribute("srvcRequstNo", srvcRequstNo); 
		
		return "reviewBoard/create"; 
		}

	@PostMapping("/createAjax")
	public String createAjax(HttpServletRequest request, AftusBbscttDto aftusBbscttDto) {
		
		String userId = userId(request);
		
		aftusBbscttDto.setUserId(userId);
		
		log.info("createAjax -> aftusBbscttVO : " + aftusBbscttDto);
		
		int result = this.reviewBoardService.createAjax(aftusBbscttDto);
		
		log.info("createAjax -> result : " + result);
		log.info("createAjax -> aftusBbscttVO : " + aftusBbscttDto);
		
//		return aftusBbscttVO.getAftusBbscttNo();
	    return "redirect:/reviewBoard/detail?aftusBbscttNo=" + aftusBbscttDto.getAftusBbscttNo();

	}
	
	@GetMapping("/detail")
	public String detail(HttpServletRequest request, int aftusBbscttNo, Model model) {
		
		log.info("detail -> aftusBbscttNo : " + aftusBbscttNo);
		
		String sessionId = userId(request);
		
		//조회수 증가
		int result = this.reviewBoardService.updateCnt(aftusBbscttNo);
		
		log.info("cnt result : " + result);
		
		//상세페이지
		AftusBbscttDto aftusBbscttDto = this.reviewBoardService.detail(aftusBbscttNo);
		
		log.info("detail -> aftusBbscttVO : " + aftusBbscttDto);
		
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("aftusBbscttVO", aftusBbscttDto);
		
		int sprviseAtchmnflNo = aftusBbscttDto.getSprviseAtchmnflNo();
		
		
		log.info("fileList -> sprviseAtchmnflNo : " + sprviseAtchmnflNo);
		
		List<SprviseAtchmnflDto> fileList = this.reviewBoardService.fileList(sprviseAtchmnflNo);

		log.info("fileList : " + fileList);
		
		model.addAttribute("fileList", fileList);
		
		return "reviewBoard/detail";
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public int delete(int aftusBbscttNo) {
		
		log.info("aftusBbscttNo" + aftusBbscttNo);
		
		int result = this.reviewBoardService.delete(aftusBbscttNo);
		
		log.info("result" + result);
		
		return result;
		
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, AftusBbscttDto aftusBbscttDto) {
		
		String userId = userId(request);
		
		aftusBbscttDto.setUserId(userId);
		
		log.info("update -> aftusBbscttVO : " + aftusBbscttDto);
		
		int result = this.reviewBoardService.update(aftusBbscttDto);
		
		log.info("update -> result : " + result);
		
		return "redirect:/reviewBoard/detail?aftusBbscttNo=" + aftusBbscttDto.getAftusBbscttNo();
		
		
		
	}
	
	
	@ResponseBody
	@PostMapping("/fileDel")
	public int fileDel(@RequestBody SprviseAtchmnflDto sprviseAtchmnflDto) {
		
		log.info("delFileInfo -> sprviseAtchmnflVO : " + sprviseAtchmnflDto);
		
		int result = this.reviewBoardService.fileDel(sprviseAtchmnflDto);
		
		log.info("fileDel -> result : " + result);
		
		return result;
	}
	
	//아작스 댓글 리스트 조회
	@ResponseBody
	@PostMapping("/listAns")
	public List<AftusBbscttAnswerDto> listAns(int aftusBbscttNo){
	
		log.info("listAns -> aftusBbscttNo : " + aftusBbscttNo);
		List<AftusBbscttAnswerDto> listAnsVO = this.reviewBoardService.aftusBbscttAnswerList(aftusBbscttNo);
		log.info("listAns -> listAnsVO : " + listAnsVO);
		
		return listAnsVO;
	}
	
	//아작스 댓글 입력
	@ResponseBody
	@PostMapping("/insertAns")
	public int insertAns(@RequestBody AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		
		log.info("aftusBbscttAnswerVO : " + aftusBbscttAnswerDto);
	
		int result = this.reviewBoardService.aftusBbscttAnswerInsert(aftusBbscttAnswerDto);
		return result;
	}
	
	//아작스 댓글 삭제
	@ResponseBody
	@PostMapping("/deleteAns")
	public int deleteAns(int aftusBbscttAnswerNo) {
		
		log.info("deleteAns -> aftusBbscttAnswerNo : " + aftusBbscttAnswerNo);

		int result = this.reviewBoardService.aftusBbscttAnswerDelete(aftusBbscttAnswerNo);
		
		return result;
	}
	
	//아작스 댓글 수정
	@ResponseBody
	@PostMapping("/updateAns")
	public int updateAns(@RequestBody AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		log.info("updateAns -> aftusBbscttAnswerVO : " + aftusBbscttAnswerDto);

		int result = this.reviewBoardService.aftusBbscttAnswerUpdate(aftusBbscttAnswerDto);
		return result;
	}
	
	//아작스 댓댓글 보기
	@ResponseBody
	@PostMapping("/ansAnsView")
	public List<AftusBbscttAnswerDto> ansAnsView(int ptAftusBbscttAnswerNo) {
		log.info("ansAnsView -> ptAftusBbscttAnswerNo : " + ptAftusBbscttAnswerNo);
		List<AftusBbscttAnswerDto> ansAnsViewVO = null;
		ansAnsViewVO = this.reviewBoardService.ansAnsList(ptAftusBbscttAnswerNo);
		log.info("ansAnsView -> ansAnsViewVO : " + ansAnsViewVO);
		return ansAnsViewVO;
	}
	
	//아작스 댓댓글 달기
	@ResponseBody
	@PostMapping("/ansAnsInt")
	public int ansAnsInt(@RequestBody AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		log.info("ansAnsInt -> aftusBbscttAnswerVO : " + aftusBbscttAnswerDto);
		
		int result = this.reviewBoardService.ansAnsInt(aftusBbscttAnswerDto);
		log.info("ansAnsInt -> result : " + result);
		
		return result;
	}
	
	//아작스 댓댓글 갯수 구하기
	@ResponseBody
	@PostMapping("/ansAnsCnt")
	public int ansAnsCnt(int ptAftusBbscttAnswerNo) {
		log.info("ansAnsCnt -> ptAftusBbscttAnswerNo : " + ptAftusBbscttAnswerNo);

		int result = this.reviewBoardService.ansAnsCnt(ptAftusBbscttAnswerNo);
		log.info("ansAnsCnt -> result : " + result);
		return result;
	}
	
	
	
	
}
