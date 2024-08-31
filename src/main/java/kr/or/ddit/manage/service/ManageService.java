package kr.or.ddit.manage.service;

import kr.or.ddit.dto.DongChartDto;
import kr.or.ddit.dto.DongChartDto2;
import kr.or.ddit.dto.DongChartDto3;

public interface ManageService {
	
	//전문분야 통계
	public DongChartDto test();
	
	//서비스 요청 통계
	public DongChartDto2 test2();

	// 서비스요청 프로 수락 거절 현황
	public DongChartDto3 test3();

}
