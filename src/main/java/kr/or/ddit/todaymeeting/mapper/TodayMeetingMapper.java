package kr.or.ddit.todaymeeting.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.TdmtngDto;
import kr.or.ddit.vo.TdmtngPrtcpntDto;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.vo.UsersDto;

public interface TodayMeetingMapper {
	
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
	
	public int getTotal(Map<String, Object> map);

	public int joinChat(TdmtngPrtcpntDto tdmtngPrtcpntDto);

	public int chatMemCount(int tdmtngNo);

	public List<TdmtngPrtcpntDto> chatMemList(int tdmtngNo);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* 새로 추가
	* @param userId
	* @return
	*/
	public List<VChatRoom> myList(String userId);
	
	public VChatRoom join( @Param("tdmtngNo")int tdmtngNo ,
						   @Param("userId") String userId );

	public int getTotalMsg(int tdmtngNo);

	public List<UsersDto> getUserInfo(String userId);

	public int updateFirstMSG(TdmtngDto tdmtngDto);
	
	

}
