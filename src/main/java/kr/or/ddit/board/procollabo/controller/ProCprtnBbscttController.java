package kr.or.ddit.board.procollabo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.procollabo.service.ProCprtnBbscttService;
import kr.or.ddit.util.ArticlePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/proCprtnBbsctt")
@RequiredArgsConstructor
public class ProCprtnBbscttController {

	private final String uploadFolder;
	private final ProCprtnBbscttService proCprtnBbscttService;
	
	private String userId(HttpServletRequest request) {
		//세션값으로 아이디 가져오기
		
		Object proSession = request.getSession().getAttribute("proSession");
		Object memSession = request.getSession().getAttribute("memSession");
		Object admSession = request.getSession().getAttribute("admSession");
		
		if(proSession !=null && proSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)proSession).get("userId");
			log.info("proSession : {}",userId);
			
			return userId != null ? userId.toString() : null;
		}
		if(memSession !=null && memSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)memSession).get("userId");
			log.info("memSession : {}",userId);
			
			return userId != null ? userId.toString() : null;

		}
		if(admSession !=null && admSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)admSession).get("userId");
			log.info("admSession : {}", userId);
			
			return userId != null ? userId.toString() : null;

		}
		return "not";

	}
	
	@GetMapping(value="/list")
	public String list(Model model, Map<String,Object>map,
			@RequestParam(value="currentPage",required=false,defaultValue="1")int currentPage,
			@RequestParam(value="keyword", required=false, defaultValue="1")String keyword){
		
		if(map!=null) {
			keyword = (String)map.get("keyword");
			
			if(map.get("currentPage")==null) {
				map.put("currentPage",currentPage);
			}
		}else {
			map = new HashMap<String,Object>();
			map.put("keyword","");
			map.put("currentPage",1);
		}
		
		map.put("currentPage",currentPage);
		
		int total  = this.proCprtnBbscttService.getTotal(map);
		log.info("list->total:"+total);
		int size = 10;
		
		List<ProCprtnBbscttDto> proCprtnBbscttDtoList = this.proCprtnBbscttService.list(map);
		log.info("list->proCprtnBbscttVOList:" + proCprtnBbscttDtoList);
		
		model.addAttribute("data", new ArticlePage<ProCprtnBbscttDto>(total
				,currentPage, size, proCprtnBbscttDtoList, keyword));
		
		return "/proCprtnBbsctt/list";
		
	}

	@ResponseBody
	@PostMapping(value="/listAjax")
	public ArticlePage<ProCprtnBbscttDto> listAjax(@RequestBody(required=false) Map<String,Object> map) throws ParseException {
		
		log.info("listAjax->map : " + map);
		
		String keyword = map.get("keyword").toString();
		int currentPage = Integer.parseInt(map.get("currentPage").toString());
		
		int total = this.proCprtnBbscttService.getTotal(map);
		log.info("listAjax->total:"+total);
		
		int size = 10;
		
		List<ProCprtnBbscttDto> proCprtnBbscttDtoList = this.proCprtnBbscttService.list(map);
		log.info("list->proCprtnBbscttVOList:" + proCprtnBbscttDtoList);
		
		ArticlePage<ProCprtnBbscttDto> data = new ArticlePage<ProCprtnBbscttDto>(total, currentPage, size, proCprtnBbscttDtoList, keyword);
		
		String url  = "/proCprtnBbsctt/list";
		
		data.setUrl(url);
		
		return data;
	}
	
	//협업게시판 상세
	@GetMapping("/detail")
	public String detail(@RequestParam("proCprtnBbscttNo") int proCprtnBbscttNo, Model model) {
		log.info("detail->proCprtnBbscttNo:"+proCprtnBbscttNo);
		
		this.proCprtnBbscttService.increaseViewCount(proCprtnBbscttNo);
		
		//프로 협업 게시판 상세
		ProCprtnBbscttDto proCprtnBbscttDto = this.proCprtnBbscttService.detail(proCprtnBbscttNo);
		/*
		ProCprtnBbscttVO(rnum=0, proCprtnBbscttNo=21, proCprtnBbscttSj=테스트제목3, proCprtnBbscttCn=테스트내용
		, proCprtnBbscttWrDt=Thu Mar 21 15:12:35 KST 2024, proCprtnBbscttRdcnt=335
		, sprviseAtchmnflNo=621, proId=testPro, spAtVOList=null, proList=null, proProflList=null)
		 */
		log.info("detail->proCprtnBbscttVO : " + proCprtnBbscttDto);
		model.addAttribute("proCprtnBbscttVO", proCprtnBbscttDto);
		
		//프로 아이디에 해당되는 다른 상세들
		ProCprtnBbscttDto proCprtnBbscttDto2 = this.proCprtnBbscttService.detail2(proCprtnBbscttNo);
		/*
		ProCprtnBbscttVO(rnum=0, proCprtnBbscttNo=21, proCprtnBbscttSj=테스트제목3, proCprtnBbscttCn=테스트내용
		, proCprtnBbscttWrDt=Thu Mar 21 15:12:35 KST 2024, proCprtnBbscttRdcnt=337
		, sprviseAtchmnflNo=621, proId=testPro, spAtVOList=null, 
		proList=[
			ProVO(proId=testPro, proMbtlnum=01066666666, sexdstnTy=SD02, email=test@test.net
			, proProflPhoto=null, spcltyRealmCode=SR2502, uploadFile=null)], 
		proProflList=[
			ProProflVO(proId=testPro, proProflOnLiIntrcn=aaa, proProflContactPosblTime=12:00~13:00, proProflReqForm=a, proProflHist=a, bcityCode=24, brtcCode=24010)])
		 */
		log.info("detail->proCprtnBbscttVO2 : " + proCprtnBbscttDto2);
		model.addAttribute("proCprtnBbscttVO2", proCprtnBbscttDto2);
		
		List<ProCprtnAnswerDto> proCprtnAnswerDto = null;
		proCprtnAnswerDto = this.proCprtnBbscttService.list2(proCprtnBbscttNo);
		model.addAttribute("proCprtnAnswerVO", proCprtnAnswerDto);
		log.info("detail->proCprtnAnswerVO : " + proCprtnAnswerDto);
		
		return "proCprtnBbsctt/detail";
	}
	
}
