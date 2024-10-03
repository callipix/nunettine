package kr.or.ddit.manage.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.DongChartDto;
import kr.or.ddit.dto.DongChartDto2;
import kr.or.ddit.dto.DongChartDto3;
@Mapper
public interface ManageMapper {

	DongChartDto test();

	DongChartDto2 test2();

	DongChartDto3 test3();

}
