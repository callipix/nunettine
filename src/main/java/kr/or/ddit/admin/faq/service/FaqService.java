package kr.or.ddit.admin.faq.service;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;

public interface FaqService {

	List<FaqDto> faqList();

	int faqUpdate(FaqDto faqDto);

	int delete(FaqDto faqDto);

	int createRegister(FaqDto faqDto);

}
