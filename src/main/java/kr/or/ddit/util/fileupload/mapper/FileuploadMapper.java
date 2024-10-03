package kr.or.ddit.util.fileupload.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.SprviseAtchmnflDto;

@Mapper
public interface FileuploadMapper {

	int fileupload(Map<String, Object> fileMap);

	List<SprviseAtchmnflDto> getsprviseAtchmnfl(int sprviseAtchmnflNo);

	int updateFileupload(Map<String, Object> updateFileuploadMap);

	int newFileupload(SprviseAtchmnflDto sprviseAtchmnflDto);
}
