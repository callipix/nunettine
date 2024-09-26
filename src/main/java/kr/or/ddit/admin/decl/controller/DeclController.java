package kr.or.ddit.admin.decl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import kr.or.ddit.admin.decl.service.DeclService;
import kr.or.ddit.dto.SntncDeclDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/decl")
@Controller
@RequiredArgsConstructor
public class DeclController {

	private final DeclService declService;
	
	//페이징 처리
	@GetMapping("/userdecl")
	public String userdecl(Model model, Map<String,Object> map, HttpServletRequest request,
			@RequestParam(value="currentPage",required=false,defaultValue="1") int currentPage) {
		
		map.put("currentPage", currentPage);
		List<SntncDeclDto> lbrbbsList = this.declService.decllbrSelect(map);
        log.info("lbrbbs -> lbrbbsList in userdecl : {}", lbrbbsList);
		model.addAttribute("lbrbbsList",lbrbbsList);
		
		return "decl/userdecl";
	}
	
	@GetMapping("/lbrbbs")
	public String lbrbbs(Model model, Map<String,Object> map, HttpServletRequest request,
			@RequestParam(value="currentPage",required=false,defaultValue="1") int currentPage) {
		
		map.put("currentPage", currentPage);
		List<SntncDeclDto> lbrbbsList = this.declService.decllbrSelect(map);
		log.info("lbrbbs -> lbrbbsList in lbrbbs : {} ", lbrbbsList);
		model.addAttribute("lbrbbsList",lbrbbsList);
		
		return "decl/lbrbbs";
	}
	
	@ResponseBody
	@PostMapping("/ajaxList")
	public List<SntncDeclDto> ajaxList(Map<String,Object> map) {
		
		List<SntncDeclDto> lbrbbsList = this.declService.decllbrSelect(map);
		log.info("lbrbbs -> lbrbbsList : {} " , lbrbbsList);
		
		return lbrbbsList;
	}
	
	@ResponseBody
	@PostMapping("/selectList")
	public LbrtyBbscttDto2 selectList(@RequestBody SntncDeclDto sntncDeclDto) {
		log.info("selectList -> sntncDeclVO : {}" , sntncDeclDto);
		
		LbrtyBbscttDto2 selectVo = this.declService.lbrtyBbscttVo(sntncDeclDto);
		log.info("selectList -> selectList : {}" , selectVo);
		
		return selectVo;
	}
	
	@ResponseBody
	@PostMapping("/declResnList")
	public List<SntncDeclDto> declResnList(@RequestBody SntncDeclDto sntncDeclDto) {
		log.info("declResnList -> sntncDeclVO : {}" , sntncDeclDto);
		
		List<SntncDeclDto> declResnList = this.declService.declResnList(sntncDeclDto);
		log.info("declResnList -> declResnList : {}" , declResnList);
		
		return declResnList;
	}
	
	@ResponseBody
	@GetMapping("/declSet")
	public int declSet(int lbrtyBbscttNo) {
		log.info("declSet -> lbrtyBbscttNo : {}" , lbrtyBbscttNo);
		
		int result = 0;
		result = this.declService.declSet(lbrtyBbscttNo);
		log.info("declSet -> result : {}" , result);
		
		return result;
	}

	@ResponseBody
	@GetMapping("/userList")
	public List<UsersDto> userList() {
		
		List<UsersDto> userList = this.declService.userList();
		log.info("userIist -> userList : {}" , userList);
		
		return userList;
	}
	
	@ResponseBody
	@PostMapping("/getDeclCount")
	public int getDeclCount(String userId2) {
		log.info("userId2 : {}" , userId2);
		int count = 0;
		count = this.declService.getDeclCount(userId2);
		if(count==0) {
			return 0;
		}
		log.info("getDeclCount -> count : {}" , count);
		return count;
	}
	
	@ResponseBody
	@PostMapping("/userDeclList")
	public List<UserDeclDto> userDeclList(String userId) {
		log.info("userId : {}" , userId);
		List<UserDeclDto> userDeclList = this.declService.userDeclList(userId);
		log.info("userDeclList -> userDeclList : {}" , userDeclList);

		return userDeclList;
	}
	
	@ResponseBody
	@PostMapping("/userDeclSet")
	public int userDeclSet(@RequestBody Map<String, Object> map) {
		log.info("userDeclSet -> map : {}" , map);
		int result = 0;
		result = this.declService.userDeclSet(map);
		log.info("userDeclSet -> result : {}" , result);
		
		return result;
	}
	@ResponseBody
	@PostMapping("/declHistoryList")
	public List<PunshDto> declHistoryList(String userId) {
		log.info("declHistoryList -> userId : {}" , userId);
		List<PunshDto> hisoryList = null;
		hisoryList = this.declService.declHistoryList(userId);
		log.info("declHistoryList -> hisoryList : {}" , hisoryList);
		
		return hisoryList;
	}
}
