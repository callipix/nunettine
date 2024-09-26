package kr.or.ddit.chatting.service;

import java.util.List;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.dto.UsersDto;

public interface ChatService {

	List<ChatRoomDto> roomList();

	//새로만든
//	List<ChatRoomVO> myRoomList(String userId);

	List<AllChatRoomViewDto> myRoomList(String userId);

	AllChatRoomViewDto myRoomListTest(String userId, int roomNo);
	
	int joinCheck(ChatRelayDto chatRelayDto);

	int joinRoom(ChatRelayDto chatRelayDto);

	List<ChatRelayDto> myRoomId(int roomNo);

	List<UsersDto> userInfo(String userId);

	UsersDto joinUserInfo(String userId);

	List<AllChatRoomViewDto> roomByRoomNo(int roomNo);
	
	AllChatRoomViewDto roomByRoomNo2(int roomNo);

	int createRoom(ChatRoomDto chatRoomDto);


}
