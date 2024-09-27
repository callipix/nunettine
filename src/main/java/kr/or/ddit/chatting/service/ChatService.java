package kr.or.ddit.chatting.service;

import java.util.List;

import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.dto.UsersDto;

public interface ChatService {

	List<ChatRoomDto> roomList();

	List<AllChatRoomViewDto> myRoomList(String userId);

	int joinCheck(ChatRelayDto chatRelayDto);

	List<ChatRelayDto> myRoomId(int roomNo);

	List<UsersDto> userInfo(String userId);

	UsersDto joinUserInfo(String userId);

	AllChatRoomViewDto roomByRoomNo2(int roomNo);

}
