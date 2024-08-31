package kr.or.ddit.board.procollabo.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.procollabo.dto.ProCprtnAnswerDto;
import kr.or.ddit.board.procollabo.dto.ProCprtnBbscttDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.procollabo.mapper.ProCprtnBbscttMapper;

@Service
@RequiredArgsConstructor
public class ProCprtnBbscttServiceImpl implements ProCprtnBbscttService {

	private final ProCprtnBbscttMapper proCprtnBbscttMapper;

	@Override
	public int getTotal(Map<String, Object> map) {
		return this.proCprtnBbscttMapper.getTotal(map);
	}

	@Override
	public List<ProCprtnBbscttDto> list(Map<String, Object> map) {
		return this.proCprtnBbscttMapper.list(map);
	}

	@Override
	public int increaseViewCount(int proCprtnBbscttNo) {
		return this.proCprtnBbscttMapper.increaseViewCount(proCprtnBbscttNo);
	}

	@Override
	public ProCprtnBbscttDto detail(int proCprtnBbscttNo) {
		return this.proCprtnBbscttMapper.detail(proCprtnBbscttNo);
	}

	@Override
	public ProCprtnBbscttDto detail2(int proCprtnBbscttNo) {
		return this.proCprtnBbscttMapper.detail2(proCprtnBbscttNo);
	}

	@Override
	public List<ProCprtnAnswerDto> list2(int proCprtnBbscttNo){
		return this.proCprtnBbscttMapper.list2(proCprtnBbscttNo);
	}

	@Override
	public int write(ProCprtnAnswerDto proCprtnAnswerDto) {
		return this.proCprtnBbscttMapper.write(proCprtnAnswerDto);
	}

	@Override
	public int modify(ProCprtnAnswerDto proCprtnAnswerDto) {
		return this.proCprtnBbscttMapper.modify(proCprtnAnswerDto);
	}

	@Override
	public int delete(ProCprtnAnswerDto proCprtnAnswerDto) {
		return this.proCprtnBbscttMapper.delete(proCprtnAnswerDto);
	}

}
