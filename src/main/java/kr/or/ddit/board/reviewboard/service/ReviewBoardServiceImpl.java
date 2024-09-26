package kr.or.ddit.board.reviewboard.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.dto.AftusBbscttAnswerDto;
import kr.or.ddit.dto.AftusBbscttDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.reviewboard.mapper.ReviewBoardMapper;
import kr.or.ddit.dto.SrvcRequstDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewBoardServiceImpl implements ReviewBoardService {

	private final String uploadFolder;
	private final ReviewBoardMapper reviewBoardMapper;

	@Override
	public List<AftusBbscttDto> list(Map<String, Object> map) {
		return this.reviewBoardMapper.list(map);
	}

	@Override
	public List<SrvcRequstDto> listModal(String userId) {
		return this.reviewBoardMapper.listModal(userId);
	}

	//로그인한 회원 리뷰 조회
	@Override
	public List<AftusBbscttDto> listMyReview(String userId) {
		return this.reviewBoardMapper.listMyReview(userId);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return this.reviewBoardMapper.getTotal(map);
	}

	@Override
	public int create(AftusBbscttDto aftusBbscttDto) {

		return this.reviewBoardMapper.create(aftusBbscttDto);
	}

	@Override
	public AftusBbscttDto detail(int aftusBbscttNo) {
		return this.reviewBoardMapper.detail(aftusBbscttNo);
	}

	@Override
	public int delete(int aftusBbscttNo) {
		return this.reviewBoardMapper.delete(aftusBbscttNo);
	}

	@Override
	public int update(AftusBbscttDto aftusBbscttDto) {

		int result = 0;

		int sprviseAtchmnflNo = aftusBbscttDto.getSprviseAtchmnflNo();

		log.info("updateSprviseAtchmnflNo {}", sprviseAtchmnflNo);

		MultipartFile[] uploadFile = aftusBbscttDto.getUploadFile();
		if (uploadFile == null || uploadFile.length == 0 || uploadFile[0].isEmpty()) {
			result = this.reviewBoardMapper.update(aftusBbscttDto);
		} else {

			//원본 파일명
			String originalFilename = "";
			//저장될파일명
			String uploadFileName = "";
			//파일 크기
			long size = 0;
			//MIME타입
			String mime = "";
			//sql 성공한 행의 수

			result = this.reviewBoardMapper.update(aftusBbscttDto);

			String userId = aftusBbscttDto.getUserId();

			uploadFile = aftusBbscttDto.getUploadFile();

			SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();
			log.info("AtchmnflNo : " + sprviseAtchmnflDto.getAtchmnflNo());

			for (MultipartFile multipartFile : uploadFile) {
				log.info("----------------------------------------------------------------------");
				log.info("원본 파일명   : {}", multipartFile.getOriginalFilename());
				log.info("파일 크기     : {}", multipartFile.getSize());
				log.info("MIME 타입     : {}", multipartFile.getContentType());

				originalFilename = multipartFile.getOriginalFilename();
				size = multipartFile.getSize();
				mime = multipartFile.getContentType();

				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + originalFilename;

				//File 객체 설계 (어디로 복사할 것인지? 경로)
				//			File saveFile = new File(uploadDirect + "\\" + getFolder(), uploadFileName);

				File uploadPath = new File(uploadFolder, getFolder());
				File saveFile = new File(uploadPath, uploadFileName);
				try {

					multipartFile.transferTo(saveFile);

					//ATTACH테이블에 insert
					//				sprviseAtchmnflVO.setAtchmnflNo(atchmnflNo++);

					sprviseAtchmnflDto.setAtchmnflCours("/images/" + getFolder().replace("\\", "/") + "/"
						+ uploadFileName);
					sprviseAtchmnflDto.setAtchmnflNm(originalFilename);
					sprviseAtchmnflDto.setStoreAtchmnflNm(uploadFileName);
					sprviseAtchmnflDto.setAtchmnflTy(mime);
					sprviseAtchmnflDto.setSprviseAtchmnflNo(sprviseAtchmnflNo);
					sprviseAtchmnflDto.setUserId(userId);

					if (aftusBbscttDto.getSprviseAtchmnflNo() == 0) {
						//기존에 첨부파일이 없다면 insert
						log.info("인서트?");
						result += this.reviewBoardMapper.insertFile(sprviseAtchmnflDto);
					} else {
						//기존 첨부파일이 있다면 update
						log.info("업데이트?");
						result += this.reviewBoardMapper.updateFile(sprviseAtchmnflDto);
					}
				} catch (IllegalStateException | IOException e) {
					log.error(e.getMessage());
				}
			}
			/*
			 * log.info("insert : " + aftusBbscttVO); if
			 * (aftusBbscttVO.getUploadFile().length != 0) { log.info("이미지파일 처리하러~" +
			 * aftusBbscttVO.getUploadFile().length); fileUpload(aftusBbscttVO); }
			 */
		}
		return result;
	}

	@Override
	public int updateCnt(int aftusBbscttNo) {
		return this.reviewBoardMapper.updateCnt(aftusBbscttNo);
	}

	@Override
	public List<SprviseAtchmnflDto> fileList(int sprviseAtchmnflNo) {

		return this.reviewBoardMapper.fileList(sprviseAtchmnflNo);
	}

	@Override
	public int fileDel(SprviseAtchmnflDto sprviseAtchmnflDto) {
		return this.reviewBoardMapper.fileDel(sprviseAtchmnflDto);
	}

	@Override
	public int createAjax(AftusBbscttDto aftusBbscttDto) {
		int result = 0;
		//파일을 올리든 안 올리든 sprviseAtchmnflNo=0으로 vo에 담겨서
		//db에 파일이 없어도 nextval 되어 데이터가 추가되는 이슈
		//uploadFile이 없을 경우 처리를 해줌
		MultipartFile[] uploadFile = aftusBbscttDto.getUploadFile();
		log.info("uploadFile : {}", uploadFile);
		if (uploadFile == null || uploadFile.length == 0 || uploadFile[0].isEmpty()) {
			aftusBbscttDto.setSprviseAtchmnflNo(1);
			log.info("setSprviseAtchmnflNo : {}", aftusBbscttDto.getSprviseAtchmnflNo());
			result = this.reviewBoardMapper.createAjax(aftusBbscttDto);
		} else {
			//원본 파일명
			String originalFilename = "";
			//저장될파일명
			String uploadFileName = "";
			//파일 크기
			long size = 0;
			//MIME타입
			String mime = "";
			//sql 성공한 행의 수

			//		int result = this.reviewBoardMapper.createAjax(aftusBbscttVO);

			int atchmnflNo = 1;

			String userId = aftusBbscttDto.getUserId();

			result = this.reviewBoardMapper.createAjax(aftusBbscttDto);

			uploadFile = aftusBbscttDto.getUploadFile();

			for (MultipartFile multipartFile : uploadFile) {
				log.info("--------------------------------------------------------");
				log.info("원본 파일명 : {}", multipartFile.getOriginalFilename());
				log.info("파일 크기    : {}", multipartFile.getSize());
				log.info("MIME 타입  : {}", multipartFile.getContentType());

				originalFilename = multipartFile.getOriginalFilename();
				size = multipartFile.getSize();
				mime = multipartFile.getContentType();

				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + originalFilename;

				File uploadPath = new File(uploadFolder, getFolder());

				if (uploadPath.exists() == false) {// 폴더가 존재하지 않으면
					uploadPath.mkdirs();
				}

				//File 객체 설계 (경로)
				File saveFile = new File(uploadFolder + "\\" + getFolder(), uploadFileName);

				try {

					multipartFile.transferTo(saveFile);

					//ATTACH테이블에 insert
					SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();

					sprviseAtchmnflDto.setAtchmnflNo(atchmnflNo++);
					sprviseAtchmnflDto.setAtchmnflCours("/images/" + getFolder().replace("\\", "/") + "/"
						+ uploadFileName);
					sprviseAtchmnflDto.setAtchmnflNm(originalFilename);
					sprviseAtchmnflDto.setStoreAtchmnflNm(uploadFileName);
					sprviseAtchmnflDto.setAtchmnflTy(mime);

					sprviseAtchmnflDto.setUserId(userId);

					result += this.reviewBoardMapper.insertFile(sprviseAtchmnflDto);

				} catch (IllegalStateException | IOException e) {
					log.error(e.getMessage());
				}
			}

			/*
			 * log.info("insert : " + aftusBbscttVO); if
			 * (aftusBbscttVO.getUploadFile().length != 0) { log.info("이미지파일 처리하러~" +
			 * aftusBbscttVO.getUploadFile().length); fileUpload(aftusBbscttVO); }
			 */
		}

		return result;
	}

	//연/월/일 폴더 생성
	public String getFolder() {
		//2024-01-30 형식(format) 지정
		//간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//2024-01-30
		String str = sdf.format(date);
		//2024-01-30 -> 2024\\01\\30
		return str.replace("-", File.separator);
	}

	@Override
	public List<AftusBbscttAnswerDto> aftusBbscttAnswerList(int aftusBbscttNo) {
		return this.reviewBoardMapper.aftusBbscttAnswerList(aftusBbscttNo);
	}

	@Override
	public int aftusBbscttAnswerInsert(AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		return this.reviewBoardMapper.aftusBbscttAnswerInsert(aftusBbscttAnswerDto);
	}

	@Override
	public int aftusBbscttAnswerDelete(int aftusBbscttAnswerNo) {
		return this.reviewBoardMapper.aftusBbscttAnswerDelete(aftusBbscttAnswerNo);
	}

	@Override
	public int aftusBbscttAnswerUpdate(AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		return this.reviewBoardMapper.aftusBbscttAnswerUpdate(aftusBbscttAnswerDto);
	}

	@Override
	public List<AftusBbscttAnswerDto> ansAnsList(int ptAftusBbscttAnswerNo) {
		return this.reviewBoardMapper.ansAnsList(ptAftusBbscttAnswerNo);
	}

	@Override
	public int ansAnsInt(AftusBbscttAnswerDto aftusBbscttAnswerDto) {
		return this.reviewBoardMapper.ansAnsInt(aftusBbscttAnswerDto);
	}

	@Override
	public int ansAnsCnt(int ptAftusBbscttAnswerNo) {
		return this.reviewBoardMapper.ansAnsCnt(ptAftusBbscttAnswerNo);
	}

}
