package kr.or.ddit.pro.prtFolio.mapper;

import kr.or.ddit.vo.PrtfolioDto;
import kr.or.ddit.vo.SprviseAtchmnflDto;

public interface PrtfolioMapper {
	public int createPost(PrtfolioDto prtfolioDto);

	public int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int deletePrt(int sprviseAtchmnflNo);
}
