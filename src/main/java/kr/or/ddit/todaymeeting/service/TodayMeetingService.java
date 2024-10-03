package kr.or.ddit.todaymeeting.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.dto.TdmtngDto;
import kr.or.ddit.dto.TdmtngPrtcpntDto;

public interface TodayMeetingService {

	//모임 캘린더 조회
	List<TdmtngDto> findAll(String userId);

	//모임 캘린더 리스트 조회
	List<TdmtngDto> list(Map<String, Object> map);

	//모임 상세 조회
	TdmtngDto detail(int tdmtngNo);

	//모임 생성
	int create(TdmtngDto tdmtngDto);

	//모임 수정
	int update(TdmtngDto tdmtngDto);

	//모임 삭제
	int delete(int tdmtngNo);

	TdmtngPrtcpntDto selectMyChat(TdmtngPrtcpntDto tdmtngPrtcpntDto);

	int getTotal(Map<String, Object> map);

	int joinChat(TdmtngPrtcpntDto tdmtngPrtcpntDto);

	int chatMemCount(int tdmtngNo);

	List<TdmtngPrtcpntDto> chatMemList(int tdmtngNo);

	List<VChatRoom> myList(String userId);

	VChatRoom join(int tdmtngNo, String userId);

}
