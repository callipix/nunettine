package kr.or.ddit.board.reviewboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.AftusBbscttAnswerDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import kr.or.ddit.dto.AftusBbscttDto;
import kr.or.ddit.dto.SrvcRequstDto;

public interface ReviewBoardService {

	//후기 조회
	List<AftusBbscttDto> list(Map<String, Object> map);

	//후기 작성 가능한 완료 서비스 조회
	List<SrvcRequstDto> listModal(String userId);

	//로그인 한 회원 리뷰 조회
	List<AftusBbscttDto> listMyReview(String userId);

	//후기 작성
	int create(AftusBbscttDto aftusBbscttDto);

	AftusBbscttDto detail(int aftusBbscttNo);

	int delete(int aftusBbscttNo);

	int createAjax(AftusBbscttDto aftusBbscttDto);

	int update(AftusBbscttDto aftusBbscttDto);

	int updateCnt(int aftusBbscttNo);

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
