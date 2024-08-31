package kr.or.ddit.vo;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TdmtngPrtcpntDto {
	
	private String userId;
	private int tdmtngNo;
	private String mberProflPhoto;
	private String proProflPhoto;
	
	List<UsersDto> usersDtoList;

	
	
	public TdmtngPrtcpntDto(String userId , int tdmtngNo){
		this.tdmtngNo = tdmtngNo;
		this.userId = userId;
	}
}
