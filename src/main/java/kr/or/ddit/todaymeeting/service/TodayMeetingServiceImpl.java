package kr.or.ddit.todaymeeting.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.vo.TdmtngDto;
import kr.or.ddit.vo.TdmtngPrtcpntDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.todaymeeting.mapper.TodayMeetingMapper;
import kr.or.ddit.vo.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodayMeetingServiceImpl implements TodayMeetingService {

	private final String uploadFolder;
	private final TodayMeetingMapper todayMeetingMapper;

	//모임 캘린더 조회
	@Override
	public List<TdmtngDto> findAll(String userId) {
		return this.todayMeetingMapper.findAll(userId);
	}
	
	//모임 캘린더 리스트 조회
	@Override
	public List<TdmtngDto> list(Map<String, Object> map) {
		// error : 파라미터로 map을 안 넣어줘서 keyword를 못 보내서 모든 리스트가 출력됐음
		return this.todayMeetingMapper.list(map);
	}
	
	//모임 상세 조회
	@Override
	public TdmtngDto detail(int tdmtngNo) {
		return this.todayMeetingMapper.detail(tdmtngNo);
	}
	
	//모임 생성
	@Override
	public int create(TdmtngDto tdmtngDto) {
		log.info("create : {}", tdmtngDto);

		if (tdmtngDto.getUploadFile() != null && !tdmtngDto.getUploadFile().isEmpty()) {
		    log.info("이미지파일 처리 {}", tdmtngDto.getUploadFile().getOriginalFilename());
		    imageUpload(tdmtngDto);
		}

		return this.todayMeetingMapper.create(tdmtngDto);
	}
	//모임 수정
	@Override
	public int update(TdmtngDto tdmtngDto) {
		log.info("update : {} ", tdmtngDto);
		if (!tdmtngDto.getUploadFile().getOriginalFilename().isEmpty()) {
			log.info("이미지파일 처리2 {}", tdmtngDto.getUploadFile().getOriginalFilename());
	        imageUpload(tdmtngDto);
	    }
		return this.todayMeetingMapper.update(tdmtngDto);
	}
	
	//모임 삭제
	@Override
	public int delete(int tdmtngNo) {
		return this.todayMeetingMapper.delete(tdmtngNo);
	}
	
	
	@Override
	public TdmtngPrtcpntDto selectMyChat(TdmtngPrtcpntDto tdmtngPrtcpntDto) {
		return this.todayMeetingMapper.selectMyChat(tdmtngPrtcpntDto);
	}
	
	@Override
	public int getTotal(Map<String,Object> map) {
		return this.todayMeetingMapper.getTotal(map);
	}
	


	@Override
	public int joinChat(TdmtngPrtcpntDto tdmtngPrtcpntDto) {
		
		TdmtngDto tdmtngDto = new TdmtngDto();
		
		log.info("입장 전{}", tdmtngDto);
		
		tdmtngDto.setUserId(tdmtngPrtcpntDto.getUserId());
		tdmtngDto.setTdmtngNo(tdmtngPrtcpntDto.getTdmtngNo());
		int result = this.todayMeetingMapper.updateFirstMSG(tdmtngDto);
		
		log.info("입장 후 result : {}" , result);
		log.info("입장 후 tdmtngVO : {}" , tdmtngDto);
		
		return this.todayMeetingMapper.joinChat(tdmtngPrtcpntDto);
	}
	
	@Override
	public int chatMemCount(int tdmtngNo) {
		return this.todayMeetingMapper.chatMemCount(tdmtngNo);
	}

	@Override
	public List<TdmtngPrtcpntDto> chatMemList(int tdmtngNo) {
		return this.todayMeetingMapper.chatMemList(tdmtngNo);
	}
	
	public TdmtngDto imageUpload(TdmtngDto tdmtngDto) {

		// TdmtngVO의 MultipartFile uploadFile 가져오기
		MultipartFile multipartFile = tdmtngDto.getUploadFile();
		
		log.info("이미지 파일명 : {} ", multipartFile.getOriginalFilename());
		log.info("이미지 크기 : {}", multipartFile.getSize());
		log.info("MIME 타입 : {}", multipartFile.getContentType());
		
		// 저장될 폴더 설정
		File uploadPath = new File(uploadFolder, getFolder());

		if (uploadPath.exists() == false) {// 폴더가 존재하지 않으면
			uploadPath.mkdirs();
		}

		String uploadFileName = multipartFile.getOriginalFilename();

		// java.util.UUID => 랜덤값 생성
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;

		File saveFile = new File(uploadPath, uploadFileName);
		// 파일 복사 실행
		try {
			multipartFile.transferTo(saveFile);

			tdmtngDto.setTdmtngThumbPhoto("/images/" + getFolder().replace("\\", "/") + "/" + uploadFileName);

			log.info("create -> tdmtngVO : {}", tdmtngDto);

		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
		}
		
		return tdmtngDto;
	}

	private String getFolder() {
		// 2024-01-30 형식(format) 지정
		// 간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		// 2024-01-30
		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}
	@Override
	public List<VChatRoom> myList(String userId) {
		log.info("userId 유저 아이디 : {}", userId);

		List<VChatRoom> myList = this.todayMeetingMapper.myList(userId);
		List<UsersDto> userInfo = this.todayMeetingMapper.getUserInfo(userId);
		for(VChatRoom chat : myList) {
			chat.setUserInfo(userInfo);
		}
		log.info("myList myList myList : {}", myList);
		return myList;
	}
	
	@Override
	public VChatRoom join(int tdmtngNo , String userId) {
		
		VChatRoom list = this.todayMeetingMapper.join(tdmtngNo , userId);
		
		List<TdmtngPrtcpntDto> chatMemList = this.todayMeetingMapper.chatMemList(tdmtngNo);
		
		for(TdmtngPrtcpntDto test : chatMemList) {
			
			if(list.getUserId().equals(test.getUserId())) {
				
				list.setUserInfo(test.getUsersDtoList());
				
			}
			log.info("테스트 : {}", test);
		}
		log.info("방번호로 리스트 체크 : {}", list);
		return list;
	}

	@Override
	public int getTotalMsg(int tdmtngNo) {
		return this.todayMeetingMapper.getTotalMsg(tdmtngNo);
	}

}