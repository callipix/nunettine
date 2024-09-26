package kr.or.ddit.util.fileupload.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.SprviseAtchmnflDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileuploadService {

	int fileUpload(List<MultipartFile> uploadFiles, String addPath, String userId, int res);
	
	List<SprviseAtchmnflDto> getsprviseAtchmnfl(int sprviseAtchmnflNo);

	int updateFileupload(Map<String, Object> updateFileuploadMap);

	int newFileUpload(List<MultipartFile> uploadFiles, String addPath, String userId, int res, String sprviseAtchmnflNo);
}
