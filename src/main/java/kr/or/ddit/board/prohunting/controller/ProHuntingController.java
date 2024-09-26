package kr.or.ddit.board.prohunting.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dto.ProJoBbscttDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.prohunting.service.ProHuntingService;
import kr.or.ddit.util.ArticlePage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/proHunting")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProHuntingController {

	private final ProHuntingService proHuntingService;

	@GetMapping("/list")
	public String list() {
		return "proHunting/list";
	}

	@PostMapping("/listAjax")
	@ResponseBody
	public ArticlePage<ProJoBbscttDto> listAjax(HttpServletRequest request,
		@RequestBody(required = false) Map<String, Object> paramMap) {

		int currentPage = Integer.parseInt((paramMap.get("currentPage").toString()));
		String keyword = paramMap.get("keyword").toString();
		int size = 10;
		int total = this.proHuntingService.getTotal(paramMap);
		int totalPages = 0;
		int startPage = 1;
		int endPage = 0;
		if (total > 0) {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			startPage = currentPage / 5 * 5 + 1;
			if (currentPage % 5 == 0) {
				startPage -= 5;
			}

			endPage = startPage + (5 - 1);

			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}
		paramMap.put("endPage", endPage);
		paramMap.put("totalPages", totalPages);

		List<ProJoBbscttDto> proHuntingList = this.proHuntingService.listAjax(paramMap);
		log.info("구인 게시판 proHuntingList : {}", proHuntingList);

		ArticlePage<ProJoBbscttDto> data = new ArticlePage<ProJoBbscttDto>(total, currentPage, size, proHuntingList,
			keyword);

		String url = "/proHunting/list";
		data.setUrl(url);

		return data;
	}

	@PostMapping("/myBoardList")
	@ResponseBody
	public ArticlePage<ProJoBbscttDto> myBoardList(HttpServletRequest request,
		@RequestBody(required = false) Map<String, Object> paramMap) {

		int size = 5;
		int total = this.proHuntingService.myBoardListgetTotal(paramMap);
		int totalPages = 0;
		if (total > 0) {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
		}

		paramMap.put("totalPages", totalPages);
		String currentPage = paramMap.get("currentPage").toString();
		String keyword = paramMap.get("keyword").toString();

		List<ProJoBbscttDto> proHuntingList = this.proHuntingService.myBoardList(paramMap);

		ArticlePage<ProJoBbscttDto> data = new ArticlePage<ProJoBbscttDto>(total, Integer.parseInt(currentPage), size,
			proHuntingList, keyword);

		String url = "/proHunting/myBoardList?currentPage=" + currentPage + "&keyword=" + keyword;
		data.setUrl(url);

		return data;
	}

	@GetMapping("/detail")
	public String detail(@RequestParam int proJoBbscttNo, Model model) {

		Map<String, Object> detailMap = this.proHuntingService.detail(proJoBbscttNo);

		model.addAttribute("proJoBbscttVOList", detailMap.get("proJoBbscttVOList"));
		model.addAttribute("spviseAtchmnflVOList", detailMap.get("spviseAtchmnflVOList"));

		return "proHunting/detail";
	}

	@PostMapping("/rdCntUpdt")
	@ResponseBody
	public int rdCntUpdt(@RequestBody Map<String, Object> map) {
		int res = 0;

		int proJoBbscttNo = Integer.parseInt(map.get("proJoBbscttNo").toString());

		res = this.proHuntingService.rdCntUpdt(proJoBbscttNo);

		return res;
	}

	@PostMapping("/proAnswerRegister")
	@ResponseBody
	public int proAnswerRegister(@RequestBody Map<String, Object> map) {

		int res = 0;

		res = this.proHuntingService.proAnswerRegister(map);

		return res;
	}

	@PostMapping("/delProAnswer")
	@ResponseBody
	public int delProAnswer(@RequestBody Map<String, Object> map) {
		int res = 0;

		res = this.proHuntingService.delProAnswer(map);

		return res;
	}
}
