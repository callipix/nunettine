package kr.or.ddit.todaymeeting;

import java.util.Date;
import java.util.List;

import kr.or.ddit.dto.UsersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VChatRoom {
	
	private int    tdmtngNo;
	private String tdmtngNm;
	private String tdmtngCreateDt;
	private Date   tdmtngCreateDt2;
	private String userId;
	private String tdmtngDt;
	private String tdmtngCn;
	
	private String inviteMSG;
	private String proflPhoto;
	
	List<UsersDto> userInfo;

}
