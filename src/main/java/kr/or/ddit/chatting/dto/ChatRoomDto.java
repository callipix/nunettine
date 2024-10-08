package kr.or.ddit.chatting.dto;

import java.util.List;

import kr.or.ddit.dto.UsersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor                        // 기본생성자
public class ChatRoomDto {

	private int roomNo;                    // 채팅방번호(pk)
	private String roomName;            // 채팅방이름
	private String createDate;            // 채팅방 생성일
	private String userId;                // 채팅방 참가자
	//	private String joinUser;			// 채팅방 참가자

	public ChatRoomDto(String roomName, String userId) {
		this.roomName = roomName;
		this.userId = userId;
	}

	private List<UsersDto> userVO;

}
