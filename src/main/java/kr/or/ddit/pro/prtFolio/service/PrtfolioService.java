package kr.or.ddit.pro.prtFolio.service;

import kr.or.ddit.dto.PrtfolioDto;

public interface PrtfolioService {

	//포트폴리오 파일업로드
	int createPost(PrtfolioDto prtfolioDto);

	//포트폴리오 삭제
	int deletePrt(int sprviseAtchmnflNo);

}
