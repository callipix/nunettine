package kr.or.ddit.manage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.or.ddit.manage.mapper.ManageMapper;
import kr.or.ddit.vo.DongChartDto;
import kr.or.ddit.vo.DongChartDto2;
import kr.or.ddit.vo.DongChartDto3;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService{
	private final ManageMapper manageMapper;
	
	@Override
	public DongChartDto test() {
		return this.manageMapper.test();
	}

	@Override
	public DongChartDto2 test2() {
		return this.manageMapper.test2();
	}

	@Override
	public DongChartDto3 test3() {
		return this.manageMapper.test3();
	}

	
}
