package kr.or.ddit.util.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.util.fileupload.mapper.FileuploadMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileuploadServiceImpl implements FileuploadService {

	private final String uploadFolder;
	private final FileuploadMapper fileuploadMapper;

	public int fileUpload(List<MultipartFile> uploadFiles, String addPath, String userId, int res) {

		List<SprviseAtchmnflDto> sprviseAtchmnflDtoList = new ArrayList<SprviseAtchmnflDto>();
		Map<String, Object> fileMap = new HashMap<String, Object>();

		if (uploadFiles != null) {
			String uploadFileOriginalName = "";
			String uploadFileName = "";
			String mime = "";
			int seq = 1;

			File savePath = new File(uploadFolder + "\\" + addPath);
			if (!savePath.exists()) {
				savePath.mkdirs();
			}

			// 파일 정보 꺼내오기
			for (MultipartFile file : uploadFiles) {
				if (!file.isEmpty()) { // 파일이 비어있지 않은 경우에만 처리
					SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();
					uploadFileOriginalName = file.getOriginalFilename();
					// 파일 유형
					mime = file.getContentType();
					// 복사된 파일 이름
					uploadFileName = UUID.randomUUID().toString() + uploadFileOriginalName;
					sprviseAtchmnflDto.setAtchmnflNo(seq++);
					sprviseAtchmnflDto.setAtchmnflCours(
						"/images/" + addPath.replaceAll("\\\\", "/") + "/" + uploadFileName);
					sprviseAtchmnflDto.setAtchmnflNm(uploadFileOriginalName);
					sprviseAtchmnflDto.setStoreAtchmnflNm(uploadFileName);
					sprviseAtchmnflDto.setAtchmnflTy(mime);
					sprviseAtchmnflDto.setUserId(userId);

					sprviseAtchmnflDtoList.add(sprviseAtchmnflDto);

					File saveFile = new File(savePath + "\\" + uploadFileName);

					// 파일 복사
					try {
						file.transferTo(saveFile);
					} catch (IllegalStateException | IOException e) {
						e.getMessage();
					}// 파일 복사 종료
				}
			}// 파일 정보 꺼내오기 for문 종료
			if (sprviseAtchmnflDtoList != null && !sprviseAtchmnflDtoList.isEmpty()) {
				fileMap.put("sprviseAtchmnflVOList", sprviseAtchmnflDtoList);
				res += this.fileuploadMapper.fileupload(fileMap);
			}

		} //전체 if문 종료

		return res;
	}// 전체 메서드 종료

	@Override
	public int newFileUpload(List<MultipartFile> uploadFiles, String addPath, String userId, int res,
		String sprviseAtchmnflNo) {

		if (uploadFiles != null) {
			String uploadFileOriginalName = "";
			String uploadFileName = "";
			String mime = "";
			int seq = 1;

			File savePath = new File(uploadFolder + "\\" + addPath);
			if (!savePath.exists()) {
				savePath.mkdirs();
			}

			// 파일 정보 꺼내오기
			for (MultipartFile file : uploadFiles) {
				if (!file.isEmpty()) {
					SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();
					uploadFileOriginalName = file.getOriginalFilename();
					// 파일 유형
					mime = file.getContentType();
					// 복사된 파일 이름
					uploadFileName = UUID.randomUUID().toString() + uploadFileOriginalName;
					sprviseAtchmnflDto.setAtchmnflCours(
						"/images/" + addPath.replaceAll("\\\\", "/") + "/" + uploadFileName);
					sprviseAtchmnflDto.setAtchmnflNm(uploadFileOriginalName);
					sprviseAtchmnflDto.setStoreAtchmnflNm(uploadFileName);
					sprviseAtchmnflDto.setAtchmnflTy(mime);
					sprviseAtchmnflDto.setUserId(userId);
					sprviseAtchmnflDto.setSprviseAtchmnflNo(Integer.valueOf(sprviseAtchmnflNo));

					res += this.fileuploadMapper.newFileupload(sprviseAtchmnflDto);

					File saveFile = new File(savePath + "\\" + uploadFileName);

					// 파일 복사
					try {
						file.transferTo(saveFile);
						res += 1;
					} catch (IllegalStateException | IOException e) {
						e.getMessage();
					}// 파일 복사 종료
				}
			}// 파일 정보 꺼내오기 for문 종료
		} //전체 if문 종료
		return res;
	}// 전체 메서드 종료

	@Override
	public List<SprviseAtchmnflDto> getsprviseAtchmnfl(int sprviseAtchmnflNo) {
		return this.fileuploadMapper.getsprviseAtchmnfl(sprviseAtchmnflNo);
	}

	@Override
	public int updateFileupload(Map<String, Object> updateFileuploadMap) {
		return this.fileuploadMapper.updateFileupload(updateFileuploadMap);
	}

}