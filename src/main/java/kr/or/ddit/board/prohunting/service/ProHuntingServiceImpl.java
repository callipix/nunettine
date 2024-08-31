package kr.or.ddit.board.prohunting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.ProJoBbscttDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.prohunting.mapper.ProHuntingMapper;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.SprviseAtchmnflDto;


@Service
@RequiredArgsConstructor
public class ProHuntingServiceImpl implements ProHuntingService {

	private final ProHuntingMapper proHuntingMapper;
	private final FileuploadService FileuploadService;
	
	@Override
	public List<ProJoBbscttDto> listAjax(Map<String, Object> paramMap) {
		return this.proHuntingMapper.listAjax(paramMap);
	}

	@Override
	public int getTotal(Map<String, Object> paramMap) {
		return this.proHuntingMapper.getTotal(paramMap);
	}
	@Override
	public List<ProJoBbscttDto> myBoardList(Map<String, Object> paramMap) {
		return this.proHuntingMapper.myBoardList(paramMap);
	}
	
	@Override
	public int myBoardListgetTotal(Map<String, Object> paramMap) {
		return this.proHuntingMapper.myBoardListgetTotal(paramMap);
	}

	@Override
	public Map<String,Object> detail(int proJoBbscttNo) {
		// 게시글과 댓글의 세부 내용
		List<ProJoBbscttDto> proJoBbscttDtoList = new ArrayList<ProJoBbscttDto>();
		proJoBbscttDtoList = this.proHuntingMapper.detail(proJoBbscttNo);
		
		int sprviseAtchmnflNo = 0;
		// 첨부파일 필요
		for(ProJoBbscttDto proJoBbscttDto : proJoBbscttDtoList) {
			sprviseAtchmnflNo = proJoBbscttDto.getSprviseAtchmnflNo();
		}
		List<SprviseAtchmnflDto> spviseAtchmnflVOList = this.FileuploadService.getsprviseAtchmnfl(sprviseAtchmnflNo);
		
		Map<String, Object> detailMap = new HashMap<String, Object>();
		detailMap.put("proJoBbscttVOList", proJoBbscttDtoList);
		detailMap.put("spviseAtchmnflVOList", spviseAtchmnflVOList);
		
		return detailMap;
		
	}

	@Override
	public int rdCntUpdt(int proJoBbscttNo) {
		return this.proHuntingMapper.rdCntUpdt(proJoBbscttNo);
	}

	@Override
	public int proAnswerRegister(Map<String, Object> map) {
		return this.proHuntingMapper.proAnswerRegister(map);
	}

	@Override
	public int delProAnswer(Map<String, Object> map) {
		return this.proHuntingMapper.delProAnswer(map);
	}

}
