package kr.or.ddit.pro.proBkmk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.dto.ProBkmkDto;

@Service
public interface ProBkmkService {
	
	// 즐겨찾기
	int proBkmkCreate(String proId, String mberId);
	// 즐겨찾기 확인
	String proBkmkCheck(String proId, String mberId);
	// 즐겨찾기 삭제
	int proBkmkDelete(String proId, String mberId);
	// 즐겨찾기 목록
	List<ProBkmkDto> getFavInfo(String memId);

}
