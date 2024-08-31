package kr.or.ddit.pro.proProfl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pro.proProfl.service.ProProflService;
import kr.or.ddit.util.ArticlePage5;
import kr.or.ddit.dto.BcityDto;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/proProfl")
@Slf4j
@Controller
@RequiredArgsConstructor
public class ProProflController {
   
   private final ProProflService proProflService;
   
   @GetMapping("/create")
   public String create(Model model, @ModelAttribute("proProflVO") ProProflDto proProflDto, HttpSession session) {
      
      Map<String,Object> map = new HashMap<String, Object>();
      List<BcityDto> bcityDtoList = this.proProflService.list(map);
      
      String proId = ((HashMap) (session.getAttribute("proSession"))).get("userId").toString();
      log.info("create->proId : " + proId);
	  VProUsersDto vProUsersDto = this.proProflService.getProInfo(proId);
	  log.info("create->vProUsersVO : " + vProUsersDto);
	   
	  String spcltyRealmCode = vProUsersDto.getSpcltyRealmCode();
	  String proBun = this.proProflService.getBunryu(spcltyRealmCode);
	  log.info("create->proBun : " + proBun);
      
      model.addAttribute("bcityVOList", bcityDtoList);
      model.addAttribute("vProUsersVO", vProUsersDto);
	  model.addAttribute("proBun",proBun);
      
      return "proProfl/create";
   }
   
   @GetMapping("/brtcList")
   @ResponseBody
   public List<VCityDto> getBrtcList(@RequestParam("bcityNm") String bcityNm){
	   List<VCityDto> brtcList = this.proProflService.getBrtcList(bcityNm);
	   return brtcList;
   }
   
   @GetMapping("/getProId")
   @ResponseBody
   public ProProflDto getProId(@RequestParam("sessionId") String sessionId) {
		/* log.info("sessionId : " + sessionId); */
	   ProProflDto proProflDto = proProflService.getProId(sessionId);
	   return proProflDto;
   }
   
   @PostMapping("/createPost")
   public String createPost(@RequestParam("bcityNm") String bcityNm,
						    @RequestParam("brtcNm") String brtcNm,
						    Model model,
						    ProProflDto proProflDto) {
	   String bcityCode = this.proProflService.bcCode(bcityNm);
	   String brtcCode = this.proProflService.btCode(bcityNm,brtcNm);
	   
	// proProflVO 객체에 bcCode와 brtcCode 값을 설정
	   proProflDto.setBcityCode(bcityCode);
	   proProflDto.setBrtcCode(brtcCode);
	   /*
	   ProProflVO(proId=protest100, proProflOnLiIntrcn=안녕하세요~, proProflContactPosblTime=9:00~13:00
	   , proProflReqForm=메, proProflHist=메, bcityCode=25, brtcCode=25020)
	    */
	   log.info("createPost->proProflVO : " + proProflDto);
	  
       int result = this.proProflService.createPost(proProflDto);
       log.info("createPost->result : " + result);
      
       return "redirect:/proProfl/detail?proId="+ proProflDto.getProId();
}
   
   
   @GetMapping("/detail")
   public String detail(@RequestParam("proId") String proId, Model model,Map<String,Object> map) {
	   log.info("detail->proId : " + proId);
	   int srvcCount = this.proProflService.getSrvcCount(proId);
	   int revCount = this.proProflService.getRevCount(proId);
	   int bkmkCount = this.proProflService.getBkmkCount(proId);
	   
	   ProProflDto proProflDto = this.proProflService.detail(proId);
	   log.info("detail->proProflVO : " + proProflDto);
	   
	   //ATCHMNFL_NO : 1
	   List<VPrtfolioDto> prtTumbVOList = this.proProflService.prtTumb(proId);
	   log.info("detail->prtTumbVOList : " + prtTumbVOList);
	   
	   
	   VProUsersDto vProUsersDto = this.proProflService.getProInfo(proId);
	   log.info("detail->vProUsersVO : " + vProUsersDto);
	   
	   //동균 신고로 인해 추가
	   List<CommonCdDetailDto> comCdList = this.proProflService.declComCdDeSelect();
	   log.info("detail-> comCdList: " + comCdList);
	   model.addAttribute("comCdList", comCdList);
	   //동균 신고로 인해 추가 끝
	   
	   String spcltyRealmCode = vProUsersDto.getSpcltyRealmCode();
	   String proBun = this.proProflService.getBunryu(spcltyRealmCode);
	   log.info("detail->proBun : " + proBun);
	   
	   model.addAttribute("proProflVO", proProflDto);
	   model.addAttribute("VPrtfolioVO", prtTumbVOList);
	   model.addAttribute("vProUsersVO", vProUsersDto);
	   model.addAttribute("proBun",proBun);
	   model.addAttribute("srvcCount",srvcCount);
	   model.addAttribute("revCount",revCount);
	   model.addAttribute("bkmkCount",bkmkCount);
	   
	   return "proProfl/detail";
	   
   }
   
	//리뷰 목록 페이징
	@ResponseBody
	@PostMapping("/reviewPage")
	public ArticlePage5<ReviewDto> reviewPage(Model model, @RequestBody(required=false) Map<String,Object> map, HttpServletRequest request){
		int size = 5;
		map.put("size",size);
		
		log.info("reviewList -> map : " + map);
		
		List<ReviewDto> reviewList = this.proProflService.getReview(map);
		
		log.info("reviewPage -> reviewList : " + reviewList);
		
		int total = this.proProflService.getRevCnt2(map);
		log.info("reviewList -> reviewTotal : " + total);
		
		String currentPage = map.get("currentPage").toString();
		String keyword = "";
		String proId = map.get("proId").toString();
		log.info("reviewPage -> currentPage : " + currentPage);
		log.info("reviewPage -> keyword : " + keyword);
		log.info("reviewPage -> proId : " + proId);
		
		ArticlePage5<ReviewDto> data = new ArticlePage5<ReviewDto>(total, Integer.parseInt(currentPage),size,reviewList,keyword,proId);
		
		String url = "/proProfl/detail";
		data.setUrl(url);
		
		return data;
	}

   
//   //proProfl/portfolioPicture
   @ResponseBody
   @PostMapping("/portfolioPicture")
   public List<SprviseAtchmnflDto>  portfolioPicture(@RequestParam("sprviseAtchmnflNo")String sprviseAtchmnflNo, Model model){
	   log.info("sprviseAtchmnflNo : " + sprviseAtchmnflNo);
	   
	   List<SprviseAtchmnflDto> sprviseAtchmnflDtoList = this.proProflService.portfolioPicture(sprviseAtchmnflNo);
	   log.info("sprviseAtchmnflVOList : " + sprviseAtchmnflDtoList);
	   
	   return sprviseAtchmnflDtoList;
   }
   
   @ResponseBody
   @PostMapping("/getNm")
   public Map<String, String> getNm(@RequestParam("bcityCode") String bcityCode,
		    				  @RequestParam("brtcCode") String brtcCode){
	   Map<String, String> map = new HashMap<>();
	   String bcityNm = this.proProflService.getBcityNm(bcityCode);
	   String brtcNm = this.proProflService.getBrtcNm(brtcCode);
	   log.info("bcityNm : " + bcityNm + ", brtcNm : "+ brtcNm);
	   map.put("bcityNm", bcityNm);
	   map.put("brtcNm", brtcNm);
	   
	   return map;
   }
   
   @GetMapping("/modify")
   public String modify(@RequestParam("proId") String proId,Model model) {
	   ProProflDto proProflDto = this.proProflService.detail(proId);
	   Map<String,Object> map = new HashMap<String, Object>();
	   List<BcityDto> bcityDtoList = this.proProflService.list(map);
	      
	   model.addAttribute("bcityVOList", bcityDtoList);
	   model.addAttribute("proProflVO", proProflDto);
	   log.info("modify->proProflVO : " + proProflDto);
	   
	   return "proProfl/modify";
   }
   
   @GetMapping("/modifyProfl")
   public String modifyProfl(@RequestParam("bcityNm") String bcityNm,
							 @RequestParam("brtcNm") String brtcNm,
							 @RequestParam("proId") String proId, Model model, ProProflDto proProflDto) {
	   log.info("modifyProfl->bcityNm : " + bcityNm + ",brtcNm : " + brtcNm);
	   String bcityCode = this.proProflService.bcCode(bcityNm);
	   String brtcCode = this.proProflService.btCode(bcityNm, brtcNm);
	   proProflDto.setBcityCode(bcityCode);
	   proProflDto.setBrtcCode(brtcCode);


	   log.info("modifyProfl->proProflVO : " + proProflDto);
	   
	   int result = this.proProflService.modify(proProflDto);
		model.addAttribute("proProflVO", proProflDto);
		log.info("modifyProfl2->proProflVO : " + proProflDto);
		
		return "redirect:/proProfl/detail?proId="+ proProflDto.getProId();
   }
   
   //동균 신고로 인해 추가 
   @ResponseBody
   @PostMapping("/declInsert")
   public int declInsert(@RequestBody UserDeclDto userDeclDto) {
	   int result = 0;
	   
	   log.info("declInsert -> userDeclVO : " + userDeclDto);
	   result = this.proProflService.declInsert(userDeclDto);
	   log.info("declInsert -> result : " + result);
	   
	   
	   return result;
   }
   //동균 신고로 인해 추가 끝
   
}