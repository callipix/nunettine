package kr.or.ddit.board.procollabo.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;


 public interface ProCprtnBbscttService {
	
	// 댓글 조회
	 List<ProCprtnAnswerDto> list2(int proCprtnBbscttNo);

	// 댓글 조회
	 int write(ProCprtnAnswerDto proCprtnAnswerDto);

	// 댓글 수정
	 int modify(ProCprtnAnswerDto proCprtnAnswerDto);

	// 댓글 삭제
	 int delete(ProCprtnAnswerDto proCprtnAnswerDto);
	
//	 List<ProCprtnBbscttVO> proCprtnBbscttList();

	 int getTotal(Map<String, Object> map);

	 List<ProCprtnBbscttDto> list(Map<String, Object> map);

	 int increaseViewCount(int proCprtnBbscttNo);

	 ProCprtnBbscttDto detail(int proCprtnBbscttNo);

	 ProCprtnBbscttDto detail2(int proCprtnBbscttNo);

	

}
