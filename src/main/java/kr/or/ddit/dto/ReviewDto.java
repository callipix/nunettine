package kr.or.ddit.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDto {
	private int reNo;
	private String reTy;
	private String reCn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date reWrDt;
	private int reScore;
	private int srvcRequstNo;
	
	private List<V_SrvcRequstDto> vSrvcRequstVOList;
	private List<SrvcRequstDto> srvcReVOList;
	private List<MberDto> mberReviewVOList;
	private List<UsersDto> userReviewVOList;
	private List<CommonCdDetailDto> comReviewVOList;
	
}
