package kr.or.ddit.admin.faq.mapper;

import java.util.List;

import kr.or.ddit.admin.faq.vo.FaqVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper {
	
	public List<FaqVO> faqList();

	public int faqUpdate(FaqVO faqVO);

	public int delete(FaqVO faqVO);

	public int createRegister(FaqVO faqVO);

}
