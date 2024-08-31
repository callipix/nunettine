package kr.or.ddit.proservice.serviceRequest.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import kr.or.ddit.dto.SrvcRequstDto;

public interface SrvcRequstMapper {

	public List<V_SrvcRequstDto> srvcRqList(Map<String, Object> map);

	public V_SrvcRequstDto srvcRqDetail(V_SrvcRequstDto vSrvcRequstVO);

	public int processFn(Map<String, Object> paramMap);

	public int acceptRequst(Map<String, Object> acceptMap);

	public int rejectRequst(Map<String, Object> rejectMap);

	public int srvcRqCreatePost(Map<String, Object> srvcRqInfoMap);

	public int insertSprviseAtchmnfl(Map<String, Object> srvcRqInfoMap);

	public int getTotal(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqNoAnswerList(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqSuccessList(Map<String, Object> map);

	public List<V_SrvcRequstDto> srvcRqRejectList(Map<String, Object> map);

	public int getNoAnswerTotal(Map<String, Object> map);

	public int getSuccessTotal(Map<String, Object> map);
	
	public int getRejectTotal(Map<String, Object> map);

	public int srvcRqUpdatePost(SrvcRequstDto srvcRequstDto);

}
