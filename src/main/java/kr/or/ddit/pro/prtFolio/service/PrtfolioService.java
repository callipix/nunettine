package kr.or.ddit.pro.prtFolio.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.PrtfolioDto;

@Service
public interface PrtfolioService {
	
	//포트폴리오 파일업로드
	public int createPost(PrtfolioDto prtfolioDto);
	//포트폴리오 삭제
	public int deletePrt(int sprviseAtchmnflNo);

}
