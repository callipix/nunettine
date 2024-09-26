package kr.or.ddit.board.prostory.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.board.prostory.mapper.ProStoryMapper;
import kr.or.ddit.board.prostory.dto.GoodPointDto;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProStoryServiceImpl implements ProStoryService {

	private final String uploadFolder;
	private final ProStoryMapper proStoryMapper;

	@Override
	@Transactional
	public int insert(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi) {

		int result = 0;
		MultipartFile multipartFile = proStoryBbscttDto.getUploadFile();
		log.info("proStoryBbscttVO : {}", proStoryBbscttDto);
		log.info("MIME 타입   : {}", multipartFile.getContentType());

		File uploadPath = new File(uploadFolder, getFolder());

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		String uploadFileName = multipartFile.getOriginalFilename();
		UUID uuid = UUID.randomUUID();

		uploadFileName = uuid.toString() + "_" + uploadFileName;

		File saveFile = new File(uploadPath, uploadFileName);
		log.info("uploadPath : {}", uploadPath);
		log.info("uploadFileName : {}", uploadFileName);

		try {
			multipartFile.transferTo(saveFile); // 썸네일 처리 -> 이미지만 가능하기때문에 이미지인지 사전체크
			if (checkImageType(saveFile)) { 	// 이미지가 맞다면
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 300, 300);
				thumbnail.close();
			};
			proStoryBbscttDto.setProStoryBbscttThumbPhoto("/" + getFolder().replace("\\", "/") + "/" + "s_" + uploadFileName);
			result = this.proStoryMapper.insert(proStoryBbscttDto);
			log.info("proStoryBbscttVO -> result : {}", result);
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	@Transactional
	public List<ProStoryBbscttDto> storyList() {

		if (this.proStoryMapper.storyList() == null) {
			return null;
		}
		return this.proStoryMapper.storyList();
	}

	@Override
	@Transactional
	public ProStoryBbscttDto getStory(int storyNo) {

		ProStoryBbscttDto proStoryBbscttDto = this.proStoryMapper.getStory(storyNo);

		log.info("프로 데이터 확인 : {}", proStoryBbscttDto);

		return proStoryBbscttDto;
	}

	@Override
	@Transactional
	public List<ProStoryBbscttDto> getPage(Map<String, Object> searchParam) {

		int getTotal = this.proStoryMapper.getTotal();

		log.info("데이터 잘 오나확인  ServiceImpl : {}", searchParam);

		searchParam.put("totalPages", getTotal);

		List<ProStoryBbscttDto> getStory = this.proStoryMapper.getPage(searchParam);

		log.info("최종값??!: {} ", searchParam);

		return getStory;
	}

	/* 수정해야함.. 임시로 하드코딩 */
	@Override
	@Transactional
	public int updateStory(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi) {

		int result = 0;
		MultipartFile multipartFile = proStoryBbscttDto.getUploadFile();

		log.info("proStoryBbscttVO for updateStory : {}", proStoryBbscttDto);
		// 스프링 파일 객체

		// 연월일 폴더 생성 설계 ... \\upload \\ 2024 \\ 01 \\ 30
		File uploadPath = new File(uploadFolder, getFolder());
		// 연월일 폴더 생성 실행
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String uploadFileName = multipartFile.getOriginalFilename();

			log.info("fileName ::: {}", uploadFileName);
			// 파일명 중복 방지 -> 같은 날 같은 이미지 업로드 시 파일명 중복 방지 시작----------------
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			File saveFile = new File(uploadPath, uploadFileName);
			log.info("uploadPath for updateStory: {}", uploadPath);
			log.info("uploadFileName for updateStory : {}", uploadFileName);

			try {
				multipartFile.transferTo(saveFile);
				if (checkImageType(saveFile)) { // 이미지가 맞다면
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 450, 450);
					thumbnail.close();
				};
				proStoryBbscttDto.setProStoryBbscttThumbPhoto(
						"/" + getFolder().replace("\\", "/") + "/" + "s_" + uploadFileName);
				result = this.proStoryMapper.updateStory(proStoryBbscttDto);
				log.info("proStoryBbscttVO -> 수정 결과 result : {}",result);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}
	@Override
	@Transactional
	public int deleteStory(String userId, int storyNo) {
		return this.proStoryMapper.deleteStory(userId, storyNo);
	}

	@Override
	@Transactional
	public int getStoryCount(int storyNo) {
		return this.proStoryMapper.getStoryCount(storyNo);
	}

	@Override
	@Transactional
	public ProStoryBbscttDto updateGood(GoodPointDto goodPointDto) {

		ProStoryBbscttDto psbcttVO = new ProStoryBbscttDto();

		psbcttVO.setProStoryBbscttNo(goodPointDto.getProStoryBbscttNo());

		this.proStoryMapper.goodUp(psbcttVO);
		int result = this.proStoryMapper.goodSave(goodPointDto);

		if (result > 0) {
			log.info("♥ 추가 성공!!");
			psbcttVO = this.proStoryMapper.goodCount(psbcttVO);
		} else {
			log.info("♡ 추가 실패");
			return null;
		}
		log.info("하트 추가 후 해당글 추천수 : {}",psbcttVO.getProStoryBbscttRecommend());
		return psbcttVO;
	}

	@Override
	@Transactional
	public ProStoryBbscttDto goodRemove(GoodPointDto goodPointDto) {

		ProStoryBbscttDto psbcttVO = new ProStoryBbscttDto();

		psbcttVO.setProStoryBbscttNo(goodPointDto.getProStoryBbscttNo());

		this.proStoryMapper.goodDown(psbcttVO);

		int result = this.proStoryMapper.goodRemove(goodPointDto);

		log.info("하트삭제 성공 : {}", result);

		if (result > 0) {
			psbcttVO = this.proStoryMapper.goodCount(psbcttVO);
		}
		log.info("하트 삭제 후 해당글 추천수 : {}", psbcttVO.getProStoryBbscttRecommend());
		return psbcttVO;
	}

	public boolean checkImageType(File file) {
		// MIME(Multipurpose Internet Mail Extensions) : 문서, 파일 또는 바이트 집합의 성격과 형식. 표준화
		// MIME 타입 알아냄. .jpeg / .jpg의 MIME타입 : image/jpeg
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info("contentType : {}", contentType);
			// image/jpeg는 image로 시작함->true
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 이 파일이 이미지가 아닐 경우
		return false;
	}

	public String getFolder() {
		String fmtNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		log.info("fmtNow ::: {}", fmtNow);
		return fmtNow.replace("-", File.separator);
	}

	@Override
	@Transactional
	public int getTotal() {
		return this.proStoryMapper.getTotal();
	}

	@Override
	public int getGoodCheck(GoodPointDto goodPointDto) {
		return this.proStoryMapper.getGoodCheck(goodPointDto);
	}
	@Override
	public List<ProStoryBbscttDto> getPageTest(Map<String , Object> map){
		log.info("getPageTest : {}", map);
		return this.proStoryMapper.getPageTest(map);
	}
	@Override
	public List<ProStoryBbscttDto> getWeekRecommend(){

		return this.proStoryMapper.getWeekRecommend();
	}
}