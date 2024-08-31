package kr.or.ddit.todaymeeting.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.vo.TdmtngDto;
import kr.or.ddit.vo.TdmtngPrtcpntDto;


public interface TodayMeetingService {
	
	//모임 캘린더 조회
	public List<TdmtngDto> findAll(String userId);
	
	//모임 캘린더 리스트 조회
	public List<TdmtngDto> list(Map<String, Object> map);
	
	//모임 상세 조회
	public TdmtngDto detail(int tdmtngNo);

	//모임 생성
	public int create(TdmtngDto tdmtngDto);
	
	//모임 수정
	public int update(TdmtngDto tdmtngDto);
	
	//모임 삭제
	public int delete(int tdmtngNo);

	public TdmtngPrtcpntDto selectMyChat(TdmtngPrtcpntDto tdmtngPrtcpntDto);
	
	public int getTotal(Map<String,Object> map);

	public int joinChat(TdmtngPrtcpntDto tdmtngPrtcpntDto);

	public int chatMemCount(int tdmtngNo);

	public List<TdmtngPrtcpntDto> chatMemList(int tdmtngNo);

	public List<VChatRoom> myList(String userId);
	
	public VChatRoom join(int tdmtngNo , String userId);

	public int getTotalMsg(int tdmtngNo);

}
