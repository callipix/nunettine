package kr.or.ddit.proservice.serviceRequest.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import kr.or.ddit.dto.SrvcRequstDto;
import kr.or.ddit.dto.UsersDto;

public interface SrvcRequstService {

	public UsersDto userChk(String userId);

	public List<V_SrvcRequstDto> srvcRqList(Map<String, Object> map);

	public V_SrvcRequstDto srvcRqDetail(V_SrvcRequstDto vSrvcRequstVO, String userId);

	public int processFn(int srvcRequstNo, String userId);

	public int acceptRequst(Map<String, Object> acceptMap, String userId);

	public int rejectRequst(Map<String, Object> rejectMap, String userId);

	public int srvcRqCreatePost(SrvcRequstDto srvcRequstDto, List<MultipartFile> uploadFiles);

	public int getTotal(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqNoAnswerList(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqSuccessList(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqRejectList(Map<String, Object> map);

	public int getNoAnswerTotal(Map<String, Object> map);
	
	public int getSuccessTotal(Map<String, Object> map);
	
	public int getRejectTotal(Map<String, Object> map);

	public int srvcRqUpdatePost(Map<String, Object> srvcRqUpdateMap, List<MultipartFile> uploadFiles, String userId);


}
