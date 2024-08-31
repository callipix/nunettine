package kr.or.ddit.board.freeboard.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.*;
import kr.or.ddit.vo.LbrtyBbscttAnswerDto;

public interface LbrtyBbscttMapper {

	public List<LbrtyBbscttDto2> lbrtyBbscttList();

	public LbrtyBbscttDto lbrtyBbscttDetail(int lbrtyBbscttNo);

	public int lbrtyBbscttDelete(LbrtyBbscttDto lbrtyBbscttDto);
	
	public int sprviseAtchmnflDelete(LbrtyBbscttDto lbrtyBbscttDto);

	public int lbrtyBbscttInsert(LbrtyBbscttDto lbrtyBbscttDto);

	public int SprviseAtchmnflInsert(SprviseAtchmnfl sprviseAtchmnfl);

	public List<LbrtyBbscttAnswerDto> lbrtyBbscttAnswerList(String lbrtyBbscttNo);

	public int lbrtyBbscttAnswerInsert(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public int lbrtyBbscttAnswerDelete(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public int lbrtyBbscttAnswerUpdate(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public int lbrtyBbscttUpdate(LbrtyBbscttDto lbrtyBbscttDto);

	public List<LbrtyBbscttAnswerDto2> ansAnsList(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public int ansAnsInt(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public int ansAnsCnt(LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto);

	public List<SprviseAtchmnfl> sprviseAtchmnflDetail(int sprviseAtchmnflNo);

	public int fileDel(SprviseAtchmnfl sprviseAtchmnfl);

	public int sprviseAtchmnflNoNextval();

	public int uptSprviseAtchmnflInsert(SprviseAtchmnfl sprviseAtchmnfl);

	public void uptsprviseAtchmnflNo(LbrtyBbscttDto lbrtyBbscttDto);

	public List<SprviseAtchmnfl> detailfileList(String sprviseAtchmnflNo);

	public List<LbrtyBbscttDto2> lbrtyBbscttListPage(Map<String, Object> map);

	public int getTotal(Map<String, Object> map);

	public int declInsert(SntncDeclDto sntncDeclDto);

	public List<CommonCdDetailDto> declComCdDeSelect();

	public int cntUp(int lbrtyBbscttNo);


}
