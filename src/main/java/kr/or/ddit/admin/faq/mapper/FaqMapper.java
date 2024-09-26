package kr.or.ddit.admin.faq.mapper;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper {

	List<FaqDto> faqList();

	int faqUpdate(FaqDto faqDto);

	int delete(FaqDto faqDto);

	int createRegister(FaqDto faqDto);

}
