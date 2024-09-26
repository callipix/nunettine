package kr.or.ddit.board.reviewboard.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.AftusBbscttAnswerDto;
import kr.or.ddit.dto.AftusBbscttDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import kr.or.ddit.dto.SrvcRequstDto;

public interface ReviewBoardMapper {

	List<AftusBbscttDto> list(Map<String, Object> map);

	List<SrvcRequstDto> listModal(String userId);

	//로그인한 회원 리뷰 조회
	List<AftusBbscttDto> listMyReview(String userId);

	int create(AftusBbscttDto aftusBbscttDto);

	AftusBbscttDto detail(int aftusBbscttNo);

	int insertAtach(SprviseAtchmnflDto sprviseAtchmnflDto);

	int delete(int aftusBbscttNo);

	int createAjax(AftusBbscttDto aftusBbscttDto);

	int insertFile(SprviseAtchmnflDto sprviseAtchmnflDto);

	int update(AftusBbscttDto aftusBbscttDto);

	int updateCnt(int aftusBbscttNo);

	int updateFile(SprviseAtchmnflDto sprviseAtchmnflDto);

	List<SprviseAtchmnflDto> fileList(int sprviseAtchmnflNo);

	int fileDel(SprviseAtchmnflDto sprviseAtchmnflDto);

	int getTotal(Map<String, Object> map);

	List<AftusBbscttAnswerDto> aftusBbscttAnswerList(int aftusBbscttNo);

	int aftusBbscttAnswerInsert(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	int aftusBbscttAnswerDelete(int aftusBbscttAnswerNo);

	int aftusBbscttAnswerUpdate(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	List<AftusBbscttAnswerDto> ansAnsList(int ptAftusBbscttAnswerNo);

	int ansAnsInt(AftusBbscttAnswerDto aftusBbscttAnswerDto);

	int ansAnsCnt(int ptAftusBbscttAnswerNo);

}
