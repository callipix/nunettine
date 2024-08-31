package kr.or.ddit.board.procollabo.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;


public interface ProCprtnBbscttService {
	
	// 댓글 조회
	public List<ProCprtnAnswerDto> list2(int proCprtnBbscttNo);

	// 댓글 조회
	public int write(ProCprtnAnswerDto proCprtnAnswerDto);

	// 댓글 수정
	public int modify(ProCprtnAnswerDto proCprtnAnswerDto);

	// 댓글 삭제
	public int delete(ProCprtnAnswerDto proCprtnAnswerDto);
	
//	public List<ProCprtnBbscttVO> proCprtnBbscttList();

	public int getTotal(Map<String, Object> map);

	public List<ProCprtnBbscttDto> list(Map<String, Object> map);

	public int increaseViewCount(int proCprtnBbscttNo);

	public ProCprtnBbscttDto detail(int proCprtnBbscttNo);

	public ProCprtnBbscttDto detail2(int proCprtnBbscttNo);

	

}
