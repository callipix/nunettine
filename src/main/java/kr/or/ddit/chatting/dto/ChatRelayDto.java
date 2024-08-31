package kr.or.ddit.chatting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatRelayDto {

	private int roomNo;			// 채팅방 번호
	private String userId;		// 유저아이디
	
	public ChatRelayDto(int roomNo , String userId) {
		this.roomNo = roomNo;
		this.userId = userId;
	}
}
