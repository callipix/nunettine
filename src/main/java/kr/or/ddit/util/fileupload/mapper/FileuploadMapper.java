package kr.or.ddit.util.fileupload.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.SprviseAtchmnflDto;


public interface FileuploadMapper {

	public int fileupload(Map<String, Object> fileMap);

	public List<SprviseAtchmnflDto> getsprviseAtchmnfl(int sprviseAtchmnflNo);

	public int updateFileupload(Map<String, Object> updateFileuploadMap);

	public int newFileupload(SprviseAtchmnflDto sprviseAtchmnflDto);
}
