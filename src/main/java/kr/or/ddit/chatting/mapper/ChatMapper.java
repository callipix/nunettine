package kr.or.ddit.chatting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.dto.UsersDto;
@Mapper
public interface ChatMapper {

	List<ChatRoomDto> roomList();

	List<AllChatRoomViewDto> myRoomList(String userId);

	int joinCheck(ChatRelayDto chatRelayDto);

	List<ChatRelayDto> myRoomId(int roomNo);

	List<UsersDto> userInfo(String userId);

	UsersDto joinUserInfo(String userId);

	AllChatRoomViewDto roomByRoomNo2(int roomNo);
}
