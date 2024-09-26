package kr.or.ddit.pro.proBkmk.mapper;

import java.util.List;

import kr.or.ddit.dto.ProBkmkDto;
import org.apache.ibatis.annotations.Param;

public interface ProBkmkMapper {
	
	int proBkmkCreate(@Param("proId") String proId, @Param("mberId") String mberId);

	String proBkmkCheck(@Param("proId") String proId, @Param("mberId") String mberId);

	int proBkmkDelete(@Param("proId") String proId, @Param("mberId") String mberId);

	List<ProBkmkDto> getFavInfo(String memId);
}
