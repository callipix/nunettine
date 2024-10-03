package kr.or.ddit.pro.prtFolio.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.PrtfolioDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
@Mapper
public interface PrtfolioMapper {
	int createPost(PrtfolioDto prtfolioDto);

	int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	int deletePrt(int sprviseAtchmnflNo);
}
