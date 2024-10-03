package kr.or.ddit.chatting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.chatting.dto.MessageDto;
import kr.or.ddit.dto.TdmtngChSpMshgDto;
import kr.or.ddit.dto.TdmtngDto;
@Mapper
public interface MessageMapper {

	int insert(TdmtngChSpMshgDto message);
	
	List<MessageDto> messageList(int roomNo);

	List<TdmtngChSpMshgDto> roomMsgList(int tdmtngNo);
	
}
