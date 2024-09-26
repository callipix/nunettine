package kr.or.ddit.board.procollabo.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;

public interface ProCprtnBbscttMapper {

	//	List<ProCprtnBbscttVO> proCprtnBbscttList();

	int getTotal(Map<String, Object> map);

	List<ProCprtnBbscttDto> list(Map<String, Object> map);

	int increaseViewCount(int proCprtnBbscttNo);

	ProCprtnBbscttDto detail(int proCprtnBbscttNo);

	ProCprtnBbscttDto detail2(int proCprtnBbscttNo);

	List<ProCprtnAnswerDto> list2(int proCprtnBbscttNo);

	int write(ProCprtnAnswerDto proCprtnAnswerDto);

	int modify(ProCprtnAnswerDto proCprtnAnswerDto);

	int delete(ProCprtnAnswerDto proCprtnAnswerDto);

}
