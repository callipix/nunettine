package kr.or.ddit.admin.oneInqry.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.OneInqryDto;

public interface OneInqryMapper {

	public List<OneInqryDto> searchList(Map<String, Object> map);
	
	public List<OneInqryDto> oneInqryNoAnswerList(Map<String, Object> map);
	
	public List<OneInqryDto> oneInqrySuccessList(Map<String, Object> map);
	
	public int getTotal(Map<String, Object> map);
	
	public int getNoAnswerTotal(Map<String, Object> map);
	
	public int getSuccessTotal(Map<String, Object> map);

	public int oneInqryCreatePost(Map<String, Object> oneInqryInfoMap);

	public OneInqryDto oneInqryDetail(OneInqryDto oneInqryDto);

	public int oneInqryUpdatePost(OneInqryDto oneInqryDto);
	
	public String userType(String userId);

	public int updateAnswer(Map<String, Object> updateParamMap);

	public int resignPro(Map<String, Object> map);

	public int getTotalResignPro(Map<String, Object> map);

	public List<OneInqryDto> resignProList(Map<String, Object> map);

	public int proSecssion(String proId);
	

}
