package kr.or.ddit.chatting.service;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import kr.or.ddit.chatting.mapper.MessageMapper;
import kr.or.ddit.chatting.dto.MessageDto;
import kr.or.ddit.dto.TdmtngChSpMshgDto;
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageMapper messageMapper;
	
	@Override
	public int insert(TdmtngChSpMshgDto message) {
		
		int result = this.messageMapper.insert(message);
		return result;
	}
	@Override
	public List<MessageDto> messageList(int roomNo) {
		return this.messageMapper.messageList(roomNo);
	}

	@Override
	public Map<Integer, List<MessageDto>> msgPaging(int roomNo) {
		return null;
	}

	@Override
	public List<TdmtngChSpMshgDto> roomMsgList(int tdmtngNo) {
		
		List<TdmtngChSpMshgDto> roomMsgList = this.messageMapper.roomMsgList(tdmtngNo);
		log.info("TdmtngChSpMshgVO 채팅방번호 : {}", tdmtngNo);
		log.info("TdmtngChSpMshgVO list : {}", roomMsgList);
		return roomMsgList;
	}
	@Override
	public int getMsgCount(int roomNo) {
		return this.messageMapper.getMsgCount(roomNo);
	}

}
