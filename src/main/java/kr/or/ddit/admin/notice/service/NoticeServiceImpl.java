package kr.or.ddit.admin.notice.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.admin.notice.dto.NoticeDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.notice.mapper.NoticeMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final String uploadFolder;
	private final NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> getAllNoticeList() {
		return this.noticeMapper.getAllNoticeList();
	}

	@Override
	public NoticeDto detail(int noticeNo) {
		return this.noticeMapper.detail(noticeNo);
	}

	@Override
	public int update(NoticeDto noticeDto) {
		return this.noticeMapper.update(noticeDto);
	}

	@Override
	public int delete(NoticeDto noticeDto) {
		return this.noticeMapper.delete(noticeDto);
	}

	@Override
	public int createPost(NoticeDto noticeDto) {
		log.info("noticeVO : {}", noticeDto);

		int result = this.noticeMapper.createPost(noticeDto);

		//원본파일명
		String uploadFileName = "";
		//Mime타입
		String mime = "";
		//seq 컬럼 카운터
		int seq = 1;

		MultipartFile[] uploadFile = noticeDto.getUploadFile();

		for (MultipartFile multipartFile : uploadFile) {
			log.info("-------------------------------------------------------");
			log.info("원본파일명 : {}", multipartFile.getOriginalFilename());
			log.info("MIME타입 :{} ", multipartFile.getContentType());
			log.info("-------------------------------------------------------");

			uploadFileName = multipartFile.getOriginalFilename();
			mime = multipartFile.getContentType();

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			String userId = noticeDto.getMngrId();
			int sprviseAtchmnflNo = noticeDto.getSprviseAtchmnflNo();
			log.info("sprviseAtchmnflNo : {}", sprviseAtchmnflNo);

			File saveFile = new File(uploadFolder + "\\" + getFolder(), uploadFileName);

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

				log.info("sprviseAtchmnflVO : {}", sprviseAtchmnflDto);

				result += this.noticeMapper.insertSprvise(sprviseAtchmnflDto);

			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}

		}

		return result;

	}

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
	/*
	 * public boolean checkImageType(File file) { String contentType; try {
	 * contentType = Files.probeContentType(file.toPath());
	 * log.info("contentType : " + contentType); //image/jpeg는 image로 시작함->true
	 * return contentType.startsWith("image"); } catch (IOException e) {
	 * e.printStackTrace(); } //이 파일이 이미지가 아닐 경우 return false; }
	 */

	@Override
	public int getTotal(Map<String, Object> map) {
		return this.noticeMapper.getTotal(map);
	}

	@Override
	public List<NoticeDto> list(Map<String, Object> map) {
		log.info("map 테스트 : {}", map);
		return this.noticeMapper.list(map);
	}

	@Override
	public int increaseViewCount(int noticeNo) {
		return this.noticeMapper.increaseViewCount(noticeNo);
	}

	@Override
	public NoticeDto sprviseAtchmnflDto(int noticeNo) {
		return this.noticeMapper.sprviseAtchmnflDto(noticeNo);
	}

}
