package kr.or.ddit.board.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.LbrtyBbscttAnswerDto;

public interface LbrtyBbscttService {

	LbrtyBbscttDto lbrtyBbscttDetail(int lbrtyBbscttNo);

	int lbrtyBbscttDelete(LbrtyBbscttDto lbrtyBbscttDto);

	int lbrtyBbscttInsert(LbrtyBbscttDto lbrtyBbscttDto);

	List<LbrtyBbscttAnswerDto> lbrtyBbscttAnswerList(String lbrtyBbscttNo);

	int lbrtyBbscttAnswerInsert(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	int lbrtyBbscttAnswerDelete(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	int lbrtyBbscttAnswerUpdate(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	int lbrtyBbscttUpdate(LbrtyBbscttDto lbrtyBbscttDto);

	List<LbrtyBbscttAnswerDto2> ansAnsList(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	int ansAnsInt(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	int ansAnsCnt(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	List<SprviseAtchmnfl> sprviseAtchmnflDetail(int sprviseAtchmnflNo);

	int fileDel(SprviseAtchmnfl sprviseAtchmnfl);

	List<SprviseAtchmnfl> detailfileList(String sprviseAtchmnflNo);

	List<LbrtyBbscttDto2> lbrtyBbscttListPage(Map<String, Object> map);

	int getTotal(Map<String, Object> map);

	int declInsert(SntncDeclDto sntncDeclDto);

	List<CommonCdDetailDto> declComCdDeSelect();

	int cntUp(int lbrtyBbscttNo);

}
