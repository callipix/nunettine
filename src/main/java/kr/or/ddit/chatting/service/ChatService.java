package kr.or.ddit.chatting.service;

import java.util.List;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.dto.UsersDto;

public interface ChatService {

	public List<ChatRoomDto> roomList();

	//새로만든
//	public List<ChatRoomVO> myRoomList(String userId);

	public List<AllChatRoomViewDto> myRoomList(String userId);

	public AllChatRoomViewDto myRoomListTest(String userId, int roomNo);
	
	public int joinCheck(ChatRelayDto chatRelayDto);

	public int joinRoom(ChatRelayDto chatRelayDto);

	public List<ChatRelayDto> myRoomId(int roomNo);

	public List<UsersDto> userInfo(String userId);

	public UsersDto joinUserInfo(String userId);

	public List<AllChatRoomViewDto> roomByRoomNo(int roomNo);
	
	public AllChatRoomViewDto roomByRoomNo2(int roomNo);

	public int createRoom(ChatRoomDto chatRoomDto);


}
