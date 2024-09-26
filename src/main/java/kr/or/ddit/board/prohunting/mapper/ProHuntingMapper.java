package kr.or.ddit.board.prohunting.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProJoBbscttDto;

public interface ProHuntingMapper {

	List<ProJoBbscttDto> listAjax(Map<String, Object> paramMap);

	int getTotal(Map<String, Object> paramMap);

	List<ProJoBbscttDto> detail(int proJoBbscttNo);

	int rdCntUpdt(int proJoBbscttNo);

	int proAnswerRegister(Map<String, Object> map);

	int delProAnswer(Map<String, Object> map);

	List<ProJoBbscttDto> myBoardList(Map<String, Object> paramMap);

	int myBoardListgetTotal(Map<String, Object> paramMap);

}
