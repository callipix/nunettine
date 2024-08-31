package kr.or.ddit.chatting.mapper;

import java.util.List;

import kr.or.ddit.chatting.dto.MessageDto;
import kr.or.ddit.dto.TdmtngChSpMshgDto;
import kr.or.ddit.dto.TdmtngDto;

public interface MessageMapper {

	public int insert(TdmtngChSpMshgDto message);
	
	public List<MessageDto> messageList(int roomNo);

	public List<MessageDto> msgPaging(int roomNo);
	
	String firstMsgDate(int msgNo);

	public List<TdmtngChSpMshgDto> roomMsgList(int tdmtngNo);
	
	public int getMsgCount(int roomNo);
	
	public TdmtngDto firstMsg(TdmtngDto inviteMSG);
}
