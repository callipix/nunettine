package kr.or.ddit.board.procollabo.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;

public interface ProCprtnBbscttMapper {

//	public List<ProCprtnBbscttVO> proCprtnBbscttList();

	public int getTotal(Map<String, Object> map);

	public List<ProCprtnBbscttDto> list(Map<String, Object> map);

	public int increaseViewCount(int proCprtnBbscttNo);

	public ProCprtnBbscttDto detail(int proCprtnBbscttNo);

	public ProCprtnBbscttDto detail2(int proCprtnBbscttNo);

	public List<ProCprtnAnswerDto> list2(int proCprtnBbscttNo);

	public int write(ProCprtnAnswerDto proCprtnAnswerDto);

	public int modify(ProCprtnAnswerDto proCprtnAnswerDto);

	public int delete(ProCprtnAnswerDto proCprtnAnswerDto);

	

	
}
