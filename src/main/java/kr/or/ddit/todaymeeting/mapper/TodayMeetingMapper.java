package kr.or.ddit.todaymeeting.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.TdmtngDto;
import kr.or.ddit.dto.TdmtngPrtcpntDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.dto.UsersDto;
@Mapper
public interface TodayMeetingMapper {

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

	/**
	 * 새로 추가
	 * @param userId
	 * @return
	 */
	List<VChatRoom> myList(String userId);

	VChatRoom join(@Param("tdmtngNo") int tdmtngNo,
		@Param("userId") String userId);

	List<UsersDto> getUserInfo(String userId);

	int updateFirstMSG(TdmtngDto tdmtngDto);

}
