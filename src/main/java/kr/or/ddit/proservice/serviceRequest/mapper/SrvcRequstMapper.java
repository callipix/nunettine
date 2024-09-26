package kr.or.ddit.proservice.serviceRequest.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import kr.or.ddit.dto.SrvcRequstDto;

public interface SrvcRequstMapper {

	List<V_SrvcRequstDto> srvcRqList(Map<String, Object> map);

	V_SrvcRequstDto srvcRqDetail(V_SrvcRequstDto vSrvcRequstVO);

	int processFn(Map<String, Object> paramMap);

	int acceptRequst(Map<String, Object> acceptMap);

	int rejectRequst(Map<String, Object> rejectMap);

	int srvcRqCreatePost(Map<String, Object> srvcRqInfoMap);

	int insertSprviseAtchmnfl(Map<String, Object> srvcRqInfoMap);

	int getTotal(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqNoAnswerList(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqSuccessList(Map<String, Object> map);

	List<V_SrvcRequstDto> srvcRqRejectList(Map<String, Object> map);

	int getNoAnswerTotal(Map<String, Object> map);

	int getSuccessTotal(Map<String, Object> map);
	
	int getRejectTotal(Map<String, Object> map);

	int srvcRqUpdatePost(SrvcRequstDto srvcRequstDto);

}
