package kr.or.ddit.pro.proBkmk.mapper;

import java.util.List;

import kr.or.ddit.dto.ProBkmkDto;
import org.apache.ibatis.annotations.Param;

public interface ProBkmkMapper {
	
	public int proBkmkCreate(@Param("proId") String proId, @Param("mberId") String mberId);

	public String proBkmkCheck(@Param("proId") String proId, @Param("mberId") String mberId);

	public int proBkmkDelete(@Param("proId") String proId, @Param("mberId") String mberId);

	public List<ProBkmkDto> getFavInfo(String memId);
}
