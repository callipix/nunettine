package kr.or.ddit.chatting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDto {

	private int msgNo;                // 메세지 번호(pk)
	private String msgSendDate;        // 메세지 보낸 일자
	private String msgCont;            // 메세지 내용

	private String msgCheck;        // 메세지 체크여부(읽음여부) T

	private int roomNo;                // 채팅방번호
	private String userId;            // 보낸아이디

}
