package kr.or.ddit.board.reviewboard.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.AftusBbscttAnswerDto;
import kr.or.ddit.dto.AftusBbscttDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import kr.or.ddit.dto.SrvcRequstDto;

public interface ReviewBoardMapper {

	public List<AftusBbscttDto> list(Map<String, Object> map);

	public List<SrvcRequstDto> listModal(String userId);
	
	//로그인한 회원 리뷰 조회
	public List<AftusBbscttDto> listMyReview(String userId);
	
	public int create(AftusBbscttDto aftusBbscttDto);

	public AftusBbscttDto detail(int aftusBbscttNo);
	
	public int insertAtach(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int delete(int aftusBbscttNo);

	public int createAjax(AftusBbscttDto aftusBbscttDto);

	public int insertFile(SprviseAtchmnflDto sprviseAtchmnflDto);
	
	public int update(AftusBbscttDto aftusBbscttDto);

	public int updateCnt(int aftusBbscttNo);

	public int updateFile(SprviseAtchmnflDto sprviseAtchmnflDto);

	public List<SprviseAtchmnflDto> fileList(int sprviseAtchmnflNo);

	public int fileDel(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int getTotal(Map<String, Object> map);

	public List<AftusBbscttAnswerDto> aftusBbscttAnswerList(int aftusBbscttNo);

	public int aftusBbscttAnswerInsert(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	public int aftusBbscttAnswerDelete(int aftusBbscttAnswerNo);

	public int aftusBbscttAnswerUpdate(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	public List<AftusBbscttAnswerDto> ansAnsList(int ptAftusBbscttAnswerNo);

	public int ansAnsInt(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	public int ansAnsCnt(int ptAftusBbscttAnswerNo);

	

	

}
