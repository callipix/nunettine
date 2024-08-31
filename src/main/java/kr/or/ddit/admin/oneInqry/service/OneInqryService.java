package kr.or.ddit.admin.oneInqry.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.OneInqryDto;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.UsersDto;

public interface OneInqryService {
	
	public UsersDto userChk(String userId);

	public List<OneInqryDto> searchList(Map<String, Object> map);

	public int getTotal(Map<String, Object> map);

	public List<OneInqryDto> oneInqryNoAnswerList(Map<String, Object> map);

	public int getNoAnswerTotal(Map<String, Object> map);

	public List<OneInqryDto> oneInqrySuccessList(Map<String, Object> map);

	public int getSuccessTotal(Map<String, Object> map);

	public int oneInqryCreatePost(OneInqryDto oneInqryDto, List<MultipartFile> uploadFiles);

	public OneInqryDto oneInqryDetail(OneInqryDto oneInqryDto, String userId);

	public int oneInqryUpdatePost(Map<String, Object> oneInqryUpdateMap, List<MultipartFile> uploadFiles,
			String userId);

	public int updateAnswer(Map<String, Object> updateParamMap, String userId);

	public int resignPro(Map<String, Object> map);

	public List<OneInqryDto> resignProList(Map<String, Object> map);

	public int getTotalResignPro(Map<String, Object> map);

	public int proSecssion(String proId);

}
