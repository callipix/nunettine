package kr.or.ddit.chatting.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.or.ddit.chatting.mapper.ChatMapper;
import kr.or.ddit.chatting.dto.AllChatRoomViewDto;
import kr.or.ddit.chatting.dto.ChatRelayDto;
import kr.or.ddit.chatting.dto.ChatRoomDto;
import kr.or.ddit.vo.UsersDto;
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

	private final ChatMapper chatMapper;

	@Override
	public List<ChatRoomDto> roomList() {
		return this.chatMapper.roomList();
	}

	@Override
	public List<AllChatRoomViewDto> myRoomList(String userId) {
		return this.chatMapper.myRoomList(userId);
	}

	@Override
	public int joinCheck(ChatRelayDto chatRelayDto) {
		return this.chatMapper.joinCheck(chatRelayDto);
	}

	@Override
	public int joinRoom(ChatRelayDto chatRelayDto) {
		return this.chatMapper.joinRoom(chatRelayDto);
	}

	@Override
	public List<ChatRelayDto> myRoomId(int roomNo) {
		return this.chatMapper.myRoomId(roomNo);
	}

	@Override
	public List<UsersDto> userInfo(String userId) {
		return this.chatMapper.userInfo(userId);
	}

	@Override
	public UsersDto joinUserInfo(String userId) {
		return this.chatMapper.joinUserInfo(userId);
	}

	@Override
	public List<AllChatRoomViewDto> roomByRoomNo(int roomNo) {
		return this.chatMapper.roomByRoomNo(roomNo);
	}

	@Override
	public AllChatRoomViewDto roomByRoomNo2(int roomNo) {
		return this.chatMapper.roomByRoomNo2(roomNo);
	}

	@Override
	public AllChatRoomViewDto myRoomListTest(String userId, int roomNo) {
		return this.chatMapper.myRoomListTest(userId, roomNo);
	}
	@Override
	public int createRoom(ChatRoomDto chatRoomDto) {
		return this.chatMapper.createRoom(chatRoomDto);
	}

}
