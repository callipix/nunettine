package kr.or.ddit.proservice.serviceInquiry.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.vo.SrvcBtfInqryDto;
import kr.or.ddit.vo.UsersDto;

public interface SrvcBtfInqryMapper {

	// 이용자 유형 확인
	public UsersDto userChk(Object object);

	public List<V_SrvcBtfInqryDto> btfInqryList(Map<String, Object> map);

	public V_SrvcBtfInqryDto btfInqryDetail(V_SrvcBtfInqryDto vSrvcBtfInqryVO);

	public int updateAnswer(Map<String, Object> updateParamMap);

	public int btfInqryCreatePost(Map<String, Object> btfInqryInfoMap);

	public int getTotal(Map<String, Object> map);
	
	public int getNoAnswerTotal(Map<String, Object> map);
	
	public int getSuccessTotal(Map<String, Object> map);

	public List<V_SrvcBtfInqryDto> btfInqryNoAnswerList(Map<String, Object> map);

	public List<V_SrvcBtfInqryDto> btfInqrySuccessList(Map<String, Object> map);

	public int btfInqryUpdatePost(SrvcBtfInqryDto srvcBtfInqryDto);


}
