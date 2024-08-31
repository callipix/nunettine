package kr.or.ddit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
import kr.or.ddit.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.ddit.board.prostory.service.ProStoryService;
import kr.or.ddit.manage.service.ManageService;
import kr.or.ddit.onedayclass.service.OnedayClassService;
import kr.or.ddit.pro.proProfl.service.ProProflService;
import kr.or.ddit.pro.proSearch.service.SearchProService;
import kr.or.ddit.vo.BcityDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ManageService manageService;
	private final ProStoryService proStoryService;
	private final SearchProService searchProService;
	private final ProProflService proProflService;
	private final OnedayClassService onedayClassService;
	
	@GetMapping("/main")
	public String main(Model model) throws JsonProcessingException {
		
		DongChartDto2 dongVO2 = this.manageService.test2();
		log.info("dongVO2 : " + dongVO2);
		
		DongChartDto3 dongVO3 = this.manageService.test3();
		log.info("dongVO3 : " + dongVO3);
		
		model.addAttribute("dongVO2",dongVO2);
		model.addAttribute("dongVO3",dongVO3);
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		List<BcityDto> bcityDtoList = this.proProflService.list(map);
		List<SpcltyRealmDto> spcltyBList = this.searchProService.spcltyB();
		List<ProDto> monthProList = this.searchProService.getMonthPro(map);
		List<OndyclDto> ondyclDtoList = this.onedayClassService.getOndyclRank();
		ProDto proDto = monthProList.get(0); // 리스트의 첫 번째 요소를 가져옴
		String proId = proDto.getProId();
	
		int srvcCount = this.proProflService.getSrvcCount(proId);
		int revCount = this.proProflService.getRevCount(proId);
		int bkmkCount = this.proProflService.getBkmkCount(proId);
		
		/* 프로이야기 추천수 기준 가져오기 새로 추가 */
		List<ProStoryBbscttDto> getRecommendList = this.proStoryService.getWeekRecommend();
		/* 프로이야기 추천수 기준 가져오기 새로 추가*/

		log.info("main->bcityVOList" + bcityDtoList);
		log.info("main->spcltyBList" + spcltyBList);
		log.info("main->monthProList" + monthProList);
		log.info("main->getRecommendList" + getRecommendList);
		
		model.addAttribute("ondyclVOList", ondyclDtoList);
		model.addAttribute("bcityVOList", bcityDtoList);
		model.addAttribute("spcltyBList",spcltyBList);
		model.addAttribute("monthProList",monthProList);
		model.addAttribute("srvcCount",srvcCount);
		model.addAttribute("revCount",revCount);
		model.addAttribute("bkmkCount",bkmkCount);
		model.addAttribute("getRecommendList",getRecommendList);
		
		return "main";
	}
}
