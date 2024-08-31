package kr.or.ddit.pro.proSearch.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.vo.AdresDto;
import kr.or.ddit.vo.ProDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pro.proProfl.service.ProProflService;
import kr.or.ddit.pro.proSearch.service.SearchProService;
import kr.or.ddit.util.ArticlePage3;
import kr.or.ddit.vo.BcityDto;
import kr.or.ddit.vo.SpcltyRealmDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/proSearch")
@RequiredArgsConstructor
public class SearchProController {
	
	private final SearchProService searchProService;
	private final ProProflService proProflService;
	
	@GetMapping("/aroundPro")
	public String aroundPro(Model model) {
	    List<AdresDto> aroundProList = this.searchProService.aroundPro();
//	    log.info("aroundPro-> aroundProList : " + aroundProList);
	    model.addAttribute("aroundProList", aroundProList);
	    return "proSearch/aroundPro";
	}
	
	@GetMapping("/aroundInfo")
	@ResponseBody
	public List<AdresDto> aroundPro2() {
		List<AdresDto> aroundInfo = this.searchProService.aroundPro();
//		log.info("aroundPro-> aroundProList2 : " + aroundInfo);
		return aroundInfo;
	}
	
	@GetMapping(value = "/spcltyCode", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String spcltyCode(@RequestParam("spcltyRealmCode") String spcltyRealmCode) {
		  String proBun = this.proProflService.getBunryu(spcltyRealmCode);
//		  log.info("spcltyCode->proBun : " + proBun);
		  return proBun;
	}
	
	//전체 프로 출력
	// /proSearch/proList?currentPage=2&keyword=%ED%83%9C%EA%B6%8C%EB%8F%84&selectColumn=SPCLTY_REALM_NM
	@GetMapping("/proList")
	public String proList(Model model, Map<String,Object> map) {
	      List<BcityDto> bcityDtoList = this.proProflService.list(map);
	      List<SpcltyRealmDto> spcltyBList = this.searchProService.spcltyB();
	      log.info("proList->spcltyBList: " + spcltyBList);
	      
	      model.addAttribute("bcityVOList", bcityDtoList);
	      model.addAttribute("spcltyBList",spcltyBList);
		
		return "proSearch/proList";
	}
	
	//서비스 하위 분야
	@ResponseBody
	@GetMapping("/spcltySec")
	public List<SpcltyRealmDto> spcltySec(String code) {
		log.info("code : " + code);
		List<SpcltyRealmDto> spcltySecList = this.searchProService.spcltySec(code);
		log.info("spcltySec : " + spcltySecList);
		return spcltySecList;
	}

	
	//전체 프로리스트 페이징처리
	@GetMapping("/proAllPage")
	public String proAllPage(Model model, Map<String, Object> map, HttpServletRequest request, 
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		
		map.put("currentPage", currentPage);
		
		List<ProDto> proAllList = this.searchProService.proListPage(map);
		
		model.addAttribute("proAllList", proAllList);
		log.info("proSeLiPage->proAllPage : " +proAllList );
		
		return "proSearch/proAllPage";
	}
	//서비스 이름
	@ResponseBody
	@GetMapping(value = "/spcltyNm", produces = "text/plain; charset=utf-8")
	public String spcltyNm(@RequestParam("code") String code,Model model) {
		String keyword = this.searchProService.spcltyNm(code);
		log.info("spcltyNm -> keyword : " + keyword);
		
		return keyword;
	}
	
	
	//검색 목록 페이징
	@ResponseBody
	@PostMapping("/searchPage")
	public ArticlePage3<ProDto> searchPage(Model model, @RequestBody(required=false) Map<String,Object> map, HttpServletRequest request){
		int size = 5;
		map.put("size",size);
		
		log.info("searchPage -> map : " + map);
		
		List<ProDto> proSearchList = this.searchProService.proListPage(map);
		
		log.info("serachPage -> proSearchList : " + proSearchList);
		
		int total = this.searchProService.getTotal(map);
		log.info("serachPage -> total : " + total);
		
		
		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();
		String selectColumn = map.get("selectColumn").toString();
		log.info("serachPage -> currentPage : " + currentPage);
		log.info("serachPage -> keyword : " + keyword);
		log.info("serachPage -> selectColumn : " + selectColumn);
		
		ArticlePage3<ProDto> data = new ArticlePage3<ProDto>(total, Integer.parseInt(currentPage),size,proSearchList,keyword,selectColumn);
		
		String url = "/proSearch/proList";
		data.setUrl(url);
		
		return data;
	}
	
	

}
