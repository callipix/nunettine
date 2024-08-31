package kr.or.ddit.proservice.serviceInquiry.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.SrvcBtfInqryDto;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.dto.UsersDto;

public interface SrvcBtfInqryService {

	public List<V_SrvcBtfInqryDto> btfInqryList(Map<String, Object> map);

	public UsersDto userChk(String userId);

	public V_SrvcBtfInqryDto btfInqryDetail(V_SrvcBtfInqryDto vSrvcBtfInqryVO, String userId);

	public int updateAnswer(Map<String, Object> updateParamMap, String userId);

	public int btfInqryCreatePost(SrvcBtfInqryDto srvcBtfInqryDto, List<MultipartFile> uploadFiles);

	public int getTotal(Map<String, Object> map);
	
	public int getNoAnswerTotal(Map<String, Object> map);
	
	public int getSuccessTotal(Map<String, Object> map);

	public List<V_SrvcBtfInqryDto> btfInqryNoAnswerList(Map<String, Object> map);

	List<V_SrvcBtfInqryDto> btfInqrySuccessList(Map<String, Object> map);

	public int btfInqryUpdatePost(Map<String, Object> btfInqryUpdateMap, List<MultipartFile> uploadFiles, String userId);

	
}