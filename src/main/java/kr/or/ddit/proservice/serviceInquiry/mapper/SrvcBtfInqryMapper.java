package kr.or.ddit.proservice.serviceInquiry.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.dto.SrvcBtfInqryDto;
import kr.or.ddit.dto.UsersDto;
@Mapper
public interface SrvcBtfInqryMapper {

	// 이용자 유형 확인
	UsersDto userChk(Object object);

	List<V_SrvcBtfInqryDto> btfInqryList(Map<String, Object> map);

	V_SrvcBtfInqryDto btfInqryDetail(V_SrvcBtfInqryDto vSrvcBtfInqryVO);

	int updateAnswer(Map<String, Object> updateParamMap);

	int btfInqryCreatePost(Map<String, Object> btfInqryInfoMap);

	int getTotal(Map<String, Object> map);

	int getNoAnswerTotal(Map<String, Object> map);

	int getSuccessTotal(Map<String, Object> map);

	List<V_SrvcBtfInqryDto> btfInqryNoAnswerList(Map<String, Object> map);

	List<V_SrvcBtfInqryDto> btfInqrySuccessList(Map<String, Object> map);

	int btfInqryUpdatePost(SrvcBtfInqryDto srvcBtfInqryDto);

}
