package kr.or.ddit.admin.oneInqry.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.OneInqryDto;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.UsersDto;

public interface OneInqryService {

	UsersDto userChk(String userId);

	List<OneInqryDto> searchList(Map<String, Object> map);

	int getTotal(Map<String, Object> map);

	List<OneInqryDto> oneInqryNoAnswerList(Map<String, Object> map);

	int getNoAnswerTotal(Map<String, Object> map);

	List<OneInqryDto> oneInqrySuccessList(Map<String, Object> map);

	int getSuccessTotal(Map<String, Object> map);

	int oneInqryCreatePost(OneInqryDto oneInqryDto, List<MultipartFile> uploadFiles);

	OneInqryDto oneInqryDetail(OneInqryDto oneInqryDto, String userId);

	int oneInqryUpdatePost(Map<String, Object> oneInqryUpdateMap, List<MultipartFile> uploadFiles,
		String userId);

	int updateAnswer(Map<String, Object> updateParamMap, String userId);

	int resignPro(Map<String, Object> map);

	List<OneInqryDto> resignProList(Map<String, Object> map);

	int getTotalResignPro(Map<String, Object> map);

	int proSecssion(String proId);

}
