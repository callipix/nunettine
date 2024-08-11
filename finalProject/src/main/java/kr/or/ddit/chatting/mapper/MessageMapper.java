package kr.or.ddit.chatting.mapper;

import java.util.List;

import kr.or.ddit.chatting.vo.MessageVO;
import kr.or.ddit.vo.TdmtngChSpMshgVO;
import kr.or.ddit.vo.TdmtngVO;

public interface MessageMapper {

	public int insert(TdmtngChSpMshgVO message);
	
	public List<MessageVO> messageList(int roomNo);	

	public List<MessageVO> msgPaging(int roomNo);	
	
	String firstMsgDate(int msgNo);

	public List<TdmtngChSpMshgVO> roomMsgList(int tdmtngNo);
	
	public int getMsgCount(int roomNo);
	
	public TdmtngVO firstMsg(TdmtngVO inviteMSG);
}
