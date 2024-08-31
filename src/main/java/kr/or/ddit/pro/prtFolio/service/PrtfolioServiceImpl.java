package kr.or.ddit.pro.prtFolio.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import kr.or.ddit.vo.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.pro.prtFolio.mapper.PrtfolioMapper;
import kr.or.ddit.vo.PrtfolioDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrtfolioServiceImpl implements PrtfolioService {

	private final String uploadFolder;
	private final PrtfolioMapper prtfolioMapper;
	
	@Override
	public int createPost(PrtfolioDto prtfolioDto) {

		int result = this.prtfolioMapper.createPost(prtfolioDto);
		
		//원본파일명
		String uploadFileName = "";
		//MIME타입
		String mime = "";
		//seq컬럼 카운터
		int seq = 1;
		
		MultipartFile[] uploadFile = prtfolioDto.getUploadFile();
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------");
			log.info("원본 파일 명 : " + multipartFile.getOriginalFilename());
			log.info("MIMME타입 : " + multipartFile.getContentType());
			log.info("-------------------");
			
			uploadFileName = multipartFile.getOriginalFilename();
			mime = multipartFile.getContentType();
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			String userId = prtfolioDto.getProId();
			int sprviseAtchmnflNo = prtfolioDto.getSprviseAtchmnflNo();
			log.info("sprviseAtchmnflNo : " + sprviseAtchmnflNo);
			
			File saveFile = new File(uploadFolder + "\\" + getFolder(),uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();
				sprviseAtchmnflDto.setSprviseAtchmnflNo(sprviseAtchmnflNo);
				sprviseAtchmnflDto.setAtchmnflNo(seq++);
				sprviseAtchmnflDto.setAtchmnflCours("/images/" + getFolder().replaceAll("\\\\", "/") + "/"
						+ uploadFileName);
				sprviseAtchmnflDto.setAtchmnflNm(multipartFile.getOriginalFilename());
				sprviseAtchmnflDto.setStoreAtchmnflNm(uploadFileName);
				sprviseAtchmnflDto.setAtchmnflTy(mime);
				sprviseAtchmnflDto.setUserId(userId);
				
				log.info("sprviseAtchmnflVO : " + sprviseAtchmnflDto);
				
				result += this.prtfolioMapper.insertSprvise(sprviseAtchmnflDto);
				
				log.info("createPost2->prtfolioVO : " + prtfolioDto);
				log.info("sprviseAtchmnflVO2 : " + sprviseAtchmnflDto);
			}catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
			
		}
		return result;
		
	}

	//연/월/일 폴더 생성
	public String getFolder() {
		//2024-01-30 형식(format) 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//2024-01-30
		String str = sdf.format(date);
		//2024-01-30 -> 2024\\01\\30
		return str.replace("-", File.separator);
	}
	
	//이미지인지 판단. 
	public boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info("contentType : " + contentType);
			//image/jpeg는 image로 시작함->true
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//이 파일이 이미지가 아닐 경우
		return false;
	}

	@Override
	public int deletePrt(int sprviseAtchmnflNo) {
		return this.prtfolioMapper.deletePrt(sprviseAtchmnflNo);
	}
	
}
