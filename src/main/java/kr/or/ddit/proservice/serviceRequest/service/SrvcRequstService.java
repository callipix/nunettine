package kr.or.ddit.proservice.serviceRequest.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import kr.or.ddit.dto.SrvcRequstDto;
import kr.or.ddit.dto.UsersDto;

public interface SrvcRequstService {

	UsersDto userChk(String userId);

	List<V_SrvcRequstDto> srvcRqList(Map<String, Object> map);

	V_SrvcRequstDto srvcRqDetail(V_SrvcRequstDto vSrvcRequstVO, String userId);

	int processFn(int srvcRequstNo, String userId);

	int acceptRequst(Map<String, Object> acceptMap, String userId);

	int rejectRequst(Map<String, Object> rejectMap, String userId);

	int srvcRqCreatePost(SrvcRequstDto srvcRequstDto, List<MultipartFile> uploadFiles);

	int getTotal(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqNoAnswerList(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqSuccessList(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqRejectList(Map<String, Object> map);

	int getNoAnswerTotal(Map<String, Object> map);

	int getSuccessTotal(Map<String, Object> map);

	int getRejectTotal(Map<String, Object> map);

	int srvcRqUpdatePost(Map<String, Object> srvcRqUpdateMap, List<MultipartFile> uploadFiles, String userId);

}
