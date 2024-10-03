package kr.or.ddit.pro.prtFolio.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.dto.PrtfolioDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pro.prtFolio.service.PrtfolioService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/prtFolio")
@RequiredArgsConstructor
public class PrtfolioController {

	private final String uploadFolder;
	private final PrtfolioService prtfolioService;

	@GetMapping("/create")
	public String create() {
		return "prtFolio/create";
	}

	@PostMapping("/createPost")
	public String createPost(PrtfolioDto prtfolioDto) {
		log.info("createPost->prtfolioVO : {}", prtfolioDto);

		File uploadPath = new File(uploadFolder, getFolder());

		//연월일
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		int result = this.prtfolioService.createPost(prtfolioDto);
		log.info("createPost->result : {}", result);

		return "redirect:/proProfl/detail?proId=" + prtfolioDto.getProId();
	}

	//연/월/일 폴더 생성
	public String getFolder() {
		//2024-01-30 형식(format) 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//2024-01-30
		String str = sdf.format(date);
		//2024-01-30 -> 2024\\01\\30
		return str.replace("-", File.separator);
	}

	@ResponseBody
	@PostMapping("/deletePrt")
	public int deletePrt(@RequestParam("sprviseAtchmnflNo") int sprviseAtchmnflNo) {
		int result = this.prtfolioService.deletePrt(sprviseAtchmnflNo);
		log.info("result : {}", result);
		return result;
	}

}
