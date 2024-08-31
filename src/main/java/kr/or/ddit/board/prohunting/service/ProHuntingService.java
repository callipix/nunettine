package kr.or.ddit.board.prohunting.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ProJoBbscttDto;

public interface ProHuntingService {

	public List<ProJoBbscttDto> listAjax(Map<String, Object> paramMap);

	public int getTotal(Map<String, Object> paramMap);

	public Map<String,Object> detail(int proJoBbscttNo);

	public int rdCntUpdt(int proJoBbscttNo);

	public int proAnswerRegister(Map<String, Object> map);

	public int delProAnswer(Map<String, Object> map);

	public List<ProJoBbscttDto> myBoardList(Map<String, Object> paramMap);

	public int myBoardListgetTotal(Map<String, Object> paramMap);

}
