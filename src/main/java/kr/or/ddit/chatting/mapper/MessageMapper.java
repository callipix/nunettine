package kr.or.ddit.chatting.mapper;

import java.util.List;

import kr.or.ddit.chatting.dto.MessageDto;
import kr.or.ddit.dto.TdmtngChSpMshgDto;
import kr.or.ddit.dto.TdmtngDto;

public interface MessageMapper {

	int insert(TdmtngChSpMshgDto message);
	
	List<MessageDto> messageList(int roomNo);

	String firstMsgDate(int msgNo);

	List<TdmtngChSpMshgDto> roomMsgList(int tdmtngNo);
	
	int getMsgCount(int roomNo);
	
	TdmtngDto firstMsg(TdmtngDto inviteMSG);
}
