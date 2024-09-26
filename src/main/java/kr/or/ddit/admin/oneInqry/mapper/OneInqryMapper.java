package kr.or.ddit.admin.oneInqry.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.OneInqryDto;

public interface OneInqryMapper {

	List<OneInqryDto> searchList(Map<String, Object> map);
	
	List<OneInqryDto> oneInqryNoAnswerList(Map<String, Object> map);
	
	List<OneInqryDto> oneInqrySuccessList(Map<String, Object> map);
	
	int getTotal(Map<String, Object> map);
	
	int getNoAnswerTotal(Map<String, Object> map);
	
	int getSuccessTotal(Map<String, Object> map);

	int oneInqryCreatePost(Map<String, Object> oneInqryInfoMap);

	OneInqryDto oneInqryDetail(OneInqryDto oneInqryDto);

	int oneInqryUpdatePost(OneInqryDto oneInqryDto);
	
	String userType(String userId);

	int updateAnswer(Map<String, Object> updateParamMap);

	int resignPro(Map<String, Object> map);

	int getTotalResignPro(Map<String, Object> map);

	List<OneInqryDto> resignProList(Map<String, Object> map);

	int proSecssion(String proId);
	

}
