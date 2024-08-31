package kr.or.ddit.chatting.mapper;

import java.util.List;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.vo.UsersDto;

public interface ChatMapper {

	public List<ChatRoomDto> roomList();
	
//	public List<ChatRoomVO> myRoomList(String userId);
	
	public List<AllChatRoomViewDto> myRoomList(String userId);

	public int joinCheck(ChatRelayDto chatRelayDto);

	public int joinRoom(ChatRelayDto chatRelayDto);

	public List<ChatRelayDto> myRoomId(int roomNo);

	public List<UsersDto> userInfo(String userId);

	public UsersDto joinUserInfo(String userId);

	public List<AllChatRoomViewDto> roomByRoomNo(int roomNo);

	public AllChatRoomViewDto roomByRoomNo2(int roomNo);
	
	public AllChatRoomViewDto myRoomListTest(String userId, int roomNo);

	public int createRoom(ChatRoomDto chatRoomDto);
}
