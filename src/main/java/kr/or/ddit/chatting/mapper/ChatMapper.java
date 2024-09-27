package kr.or.ddit.chatting.mapper;

import java.util.List;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.dto.UsersDto;

public interface ChatMapper {

	List<ChatRoomDto> roomList();

	List<AllChatRoomViewDto> myRoomList(String userId);

	int joinCheck(ChatRelayDto chatRelayDto);

	int joinRoom(ChatRelayDto chatRelayDto);

	List<ChatRelayDto> myRoomId(int roomNo);

	List<UsersDto> userInfo(String userId);

	UsersDto joinUserInfo(String userId);

	List<AllChatRoomViewDto> roomByRoomNo(int roomNo);

	AllChatRoomViewDto roomByRoomNo2(int roomNo);

	AllChatRoomViewDto myRoomListTest(String userId, int roomNo);

	int createRoom(ChatRoomDto chatRoomDto);
}
