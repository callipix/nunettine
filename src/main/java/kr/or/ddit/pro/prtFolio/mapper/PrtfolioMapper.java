package kr.or.ddit.pro.prtFolio.mapper;

import kr.or.ddit.dto.PrtfolioDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;

public interface PrtfolioMapper {
	int createPost(PrtfolioDto prtfolioDto);

	int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	int deletePrt(int sprviseAtchmnflNo);
}
