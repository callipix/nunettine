package kr.or.ddit.proservice.serviceInquiry.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.SrvcBtfInqryDto;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.dto.UsersDto;

public interface SrvcBtfInqryService {

	List<V_SrvcBtfInqryDto> btfInqryList(Map<String, Object> map);

	UsersDto userChk(String userId);

	V_SrvcBtfInqryDto btfInqryDetail(V_SrvcBtfInqryDto vSrvcBtfInqryVO, String userId);

	int updateAnswer(Map<String, Object> updateParamMap, String userId);

	int btfInqryCreatePost(SrvcBtfInqryDto srvcBtfInqryDto, List<MultipartFile> uploadFiles);

	int getTotal(Map<String, Object> map);

	int getNoAnswerTotal(Map<String, Object> map);

	int getSuccessTotal(Map<String, Object> map);

	List<V_SrvcBtfInqryDto> btfInqryNoAnswerList(Map<String, Object> map);

	List<V_SrvcBtfInqryDto> btfInqrySuccessList(Map<String, Object> map);

	int btfInqryUpdatePost(Map<String, Object> btfInqryUpdateMap, List<MultipartFile> uploadFiles, String userId);

}