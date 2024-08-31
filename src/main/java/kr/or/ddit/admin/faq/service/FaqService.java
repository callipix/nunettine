package kr.or.ddit.admin.faq.service;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;

public interface FaqService {

	public List<FaqDto> faqList();

	public int faqUpdate(FaqDto faqDto);

	public int delete(FaqDto faqDto);

	public int createRegister(FaqDto faqDto);


}
