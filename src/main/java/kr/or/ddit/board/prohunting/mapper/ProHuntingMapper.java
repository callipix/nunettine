package kr.or.ddit.board.prohunting.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProJoBbscttDto;

public interface ProHuntingMapper {

	public List<ProJoBbscttDto> listAjax(Map<String, Object> paramMap);

	public int getTotal(Map<String, Object> paramMap);

	public List<ProJoBbscttDto> detail(int proJoBbscttNo);

	public int rdCntUpdt(int proJoBbscttNo);

	public int proAnswerRegister(Map<String, Object> map);

	public int delProAnswer(Map<String, Object> map);

	public List<ProJoBbscttDto> myBoardList(Map<String, Object> paramMap);

	public int myBoardListgetTotal(Map<String, Object> paramMap);

}
