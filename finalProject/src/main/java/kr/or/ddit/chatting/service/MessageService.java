package kr.or.ddit.chatting.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chatting.vo.MessageVO;
import kr.or.ddit.vo.TdmtngChSpMshgVO;

public interface MessageService {

	int insert(TdmtngChSpMshgVO message);
	
	List<MessageVO> messageList(int roomNo);

	List<TdmtngChSpMshgVO> roomMsgList(int tdmtngNo);
	
	Map<Integer , List<MessageVO> > msgPaging(int roomNo);

	int getMsgCount(int roomNo);
}
