package kr.or.ddit.chatting.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chatting.dto.MessageDto;
import kr.or.ddit.vo.TdmtngChSpMshgDto;

public interface MessageService {

	int insert(TdmtngChSpMshgDto message);
	
	List<MessageDto> messageList(int roomNo);

	List<TdmtngChSpMshgDto> roomMsgList(int tdmtngNo);
	
	Map<Integer , List<MessageDto> > msgPaging(int roomNo);

	int getMsgCount(int roomNo);
}
