package kr.or.ddit.admin.faq.controller;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.faq.service.FaqService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

	private final FaqService faqService;

	@GetMapping("/list")
	public String faqList(Model model) {
		List<FaqDto> faqList = faqService.faqList();
		model.addAttribute("faqList", faqList);
		log.info("faqList {} ", faqList.toString());
		return "faq/list";
	}

	@ResponseBody
	@PostMapping("/update")
	public int faqUpdate(@RequestBody FaqDto faqDto) {
		faqDto.setMngrId("testAdmin");
		log.info("update 여기 {}", faqDto);
		int result = this.faqService.faqUpdate(faqDto);
		log.info("update  ->  result: {}", result);
		return result;
	}

	@ResponseBody
	@PostMapping("/delete")
	public int faqDelete(@RequestBody FaqDto faqDto) {
		faqDto.setMngrId("testAdmin");
		log.info("delete: {}", faqDto);
		int result = this.faqService.delete(faqDto);
		log.info("delete -> result: {}", result);
		return result;
	}

	@GetMapping(value = "/create", params = "register")
	public String createRegister(FaqDto faqDto) {

		log.info("createRegister -> faqDto:{}", faqDto);

		return "faq/create";
	}

	@PostMapping(value = "/create", params = "register")
	public String createRegisterPost(FaqDto faqDto) {

		faqDto.setMngrId("testAdmin");
		log.info("createRegisterPost -> faqDto: {}", faqDto);

		int result = this.faqService.createRegister(faqDto);
		log.info("createRegister -> result: {}", result);

		return "redirect:/faq/list";

	}
}
