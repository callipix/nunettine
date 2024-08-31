package kr.or.ddit.admin.faq.mapper;

import java.util.List;

import kr.or.ddit.admin.faq.dto.FaqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper {
	
	public List<FaqDto> faqList();

	public int faqUpdate(FaqDto faqDto);

	public int delete(FaqDto faqDto);

	public int createRegister(FaqDto faqDto);

}
