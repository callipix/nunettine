package kr.or.ddit.pro.prtFolio.mapper;

import kr.or.ddit.dto.PrtfolioDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;

public interface PrtfolioMapper {
	public int createPost(PrtfolioDto prtfolioDto);

	public int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int deletePrt(int sprviseAtchmnflNo);
}
