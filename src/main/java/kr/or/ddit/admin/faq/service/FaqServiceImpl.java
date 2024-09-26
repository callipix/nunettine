package kr.or.ddit.admin.faq.service;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.faq.mapper.FaqMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

	private final FaqMapper faqMapper;

	@Override
	public List<FaqDto> faqList() {
		return this.faqMapper.faqList();
	}

	@Override
	public int faqUpdate(FaqDto faqDto) {
		return this.faqMapper.faqUpdate(faqDto);
	}

	@Override
	public int delete(FaqDto faqDto) {
		return this.faqMapper.delete(faqDto);
	}

	@Override
	public int createRegister(FaqDto faqDto) {
		return this.faqMapper.createRegister(faqDto);
	}

}
