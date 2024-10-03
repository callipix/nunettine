package kr.or.ddit.admin.usersSearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.admin.usersSearch.dto.UsersDto;
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

import kr.or.ddit.admin.usersSearch.service.UsersSearchService;
//import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.util.ArticlePage2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/usersSearch")
@Controller
@RequiredArgsConstructor
public class UsersSearchController {

	private final UsersSearchService usersSearchService;

	@GetMapping("/list")
	public String list(Model model, Map<String, Object> map,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
		@RequestParam(value = "keyword", required = false, defaultValue = "1") String keyword,
		@RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey) {

		if (map != null) {
			keyword = (String)map.get("keyword");
			searchKey = (String)map.get("searchKey");

			if (map.get("currentPage") == null) {
				map.put("currentPage", currentPage);
			}
		} else {
			map = new HashMap<>();
			map.put("keyword", "");
			map.put("searchKey", "");
			map.put("currentPage", 1);
		}
		map.put("currentPage", currentPage);

		int total = this.usersSearchService.getTotal(map);
		log.info("list -> total: {}", total);//44
		int size = 10;

		List<UsersDto> usersDtoList = this.usersSearchService.list(map);
		log.info("list -> usersVOList : {}", usersDtoList);
		log.info("list -> map : {}", map);

		model.addAttribute("data", new ArticlePage2<UsersDto>(total
			, currentPage, size, usersDtoList, keyword, "rightList", searchKey));

		return "usersSearch/list";
	}

	@ResponseBody
	@PostMapping("/listAjax")
	public ArticlePage2<UsersDto> listAjax(@RequestBody(required = false) Map<String, Object> map) throws
		ParseException {

		log.info("listAjax -> map : {}", map);

		String keyword = map.get("keyword").toString();
		String searchKey = map.get("searchKey").toString();
		int currentPage = Integer.parseInt(map.get("currentPage").toString());

		int total = this.usersSearchService.getTotal(map);
		log.info("listAjax -> total {}", total);

		int size = 10;

		List<UsersDto> usersDtoList = this.usersSearchService.list(map);

		ArticlePage2<UsersDto> data = new ArticlePage2<UsersDto>(total
			, currentPage, size, usersDtoList, keyword, "rightList", searchKey);

		String url = "/usersSearch/list";

		data.setUrl(url);

		return data;
	}

	@GetMapping("/detail")
	public String usersDetail(@RequestParam String userId, Model model) {
		log.info("detail->usersDetail : {}", userId);

		UsersDto usersDto = this.usersSearchService.detail(userId);
		log.info("detail -> usersVO : {}", usersDto);

		model.addAttribute("usersVO", usersDto);

		return "usersSearch/detail";
	}

}
