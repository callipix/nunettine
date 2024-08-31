package kr.or.ddit.chatting.dto;

import java.util.List;

import kr.or.ddit.dto.UsersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class AllChatRoomViewDto {

	private int roomNo;
	private String roomName;
	private String createDate;
	
	private String userId;
	
	private List<UsersDto> userVO;

}
