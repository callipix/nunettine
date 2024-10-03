package kr.or.ddit.admin.oneInqry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.OneInqryDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.oneInqry.mapper.OneInqryMapper;
import kr.or.ddit.proservice.serviceInquiry.mapper.SrvcBtfInqryMapper;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OneInqryServiceImpl implements OneInqryService {

	private final FileuploadService fileuploadService;
	private final SrvcBtfInqryMapper srvcBtfInqryMapper;
	private final OneInqryMapper oneInqryMapper;

	// 아이디 유형 확인 
	@Override
	public UsersDto userChk(String userId) {

		UsersDto usersDto = this.srvcBtfInqryMapper.userChk(userId);

		if (usersDto.getEmplyrTy().equals("ET01")) { // 회원일 경우, 프로 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET02");

		} else if (usersDto.getEmplyrTy().equals("ET02")) { // 프로일 경우, 회원 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET01");
		}

		log.info("userChk -> usersVO : {}", usersDto);

		return usersDto;
	}

	@Override
	public List<OneInqryDto> searchList(Map<String, Object> map) {

		UsersDto usersDto = userChk((String)map.get("userId"));
		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.searchList(map);
	}

	// 미답변 목록 조회
	@Override
	public List<OneInqryDto> oneInqryNoAnswerList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		log.info("(serviceImpl)btfInqryList -> usersVO : {}", usersDto);

		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.oneInqryNoAnswerList(map);
	}

	// 답변 완료 목록 조회
	@Override
	public List<OneInqryDto> oneInqrySuccessList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		log.info("(serviceImpl)btfInqryList from oneInqrySuccessList -> usersVO : {}", usersDto);

		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneBtfInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.oneInqrySuccessList(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.getTotal(map);
	}

	@Override
	public int getNoAnswerTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.getNoAnswerTotal(map);
	}

	@Override
	public int getSuccessTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		OneInqryDto oneInqryDto = OneInqryDto.builder().userId(usersDto.getUserId()).build();

		map.put("oneInqryVO", oneInqryDto);
		map.put("userId", usersDto.getUserId());
		map.put("mngrId", "testAdmin");

		return this.oneInqryMapper.getSuccessTotal(map);
	}

	@Override
	public int oneInqryCreatePost(OneInqryDto oneInqryDto, List<MultipartFile> uploadFiles) {
		int res = 0;

		Map<String, Object> oneInqryInfoMap = new HashMap<String, Object>();

		oneInqryInfoMap.put("oneInqrySj", oneInqryDto.getOneInqrySj());
		oneInqryInfoMap.put("oneInqryCn", oneInqryDto.getOneInqryCn());
		oneInqryInfoMap.put("userId", oneInqryDto.getUserId());

		// 요청서 기본 정보
		res = this.oneInqryMapper.oneInqryCreatePost(oneInqryInfoMap);

		String addPath = "oneInqry\\oneInqryImage";
		res += fileuploadService.fileUpload(uploadFiles, addPath, oneInqryDto.getUserId(), res);

		return res;
	}

	@Override
	public OneInqryDto oneInqryDetail(OneInqryDto oneInqryDto, String userId) {

		UsersDto usersDto = userChk(userId);

		oneInqryDto.setUserId(usersDto.getUserId());

		return this.oneInqryMapper.oneInqryDetail(oneInqryDto);
	}

	@Override
	public int oneInqryUpdatePost(Map<String, Object> oneInqryUpdateMap, List<MultipartFile> uploadFiles,
		String userId) {
		int res = 0;
		// 문의 번호
		String oneInqryNo = (String)oneInqryUpdateMap.get("oneInqryNo");
		// 문의 변경 제목 
		String oneInqrySj = (String)oneInqryUpdateMap.get("hiddenOneInqrySj");
		// 문의 변경 내용
		String oneInqryCn = (String)oneInqryUpdateMap.get("newOneInqryCn");
		// 문의 첨부파일 번호
		String sprviseAtchmnflNo = (String)oneInqryUpdateMap.get("sprviseAtchmnflNo");

		// 사전문의 업데이트
		OneInqryDto oneInqryDto = new OneInqryDto();
		if (oneInqrySj != null || oneInqryCn != null) {
			oneInqryDto.setOneInqryNo(Integer.valueOf(oneInqryNo));
			oneInqryDto.setOneInqrySj(oneInqrySj);
			oneInqryDto.setOneInqryCn(oneInqryCn);

			res = this.oneInqryMapper.oneInqryUpdatePost(oneInqryDto);
		}

		// 기존 이미지 삭제
		Map<String, Object> updateFileuploadMap = new HashMap<>();

		String atchmnflNoArrayParam = (String)oneInqryUpdateMap.get("atchmnflNo[]");
		String[] atchmnflNoArray = atchmnflNoArrayParam.split(",");

		updateFileuploadMap.put("sprviseAtchmnflNo", sprviseAtchmnflNo);
		updateFileuploadMap.put("atchmnflNoArray", atchmnflNoArray);
		log.info("[btfInqryUpdatePost/serviceimpl] 기존 이미지 삭제 map : {}", updateFileuploadMap);
		res += this.fileuploadService.updateFileupload(updateFileuploadMap);

		// 새로운 파일 업로드
		if (!uploadFiles.isEmpty()) {
			String addPath = "oneInqry\\oneInqryImage";
			res += fileuploadService.newFileUpload(uploadFiles, addPath, userId, res, sprviseAtchmnflNo);
		}
		return res;
	}

	@Override
	public int updateAnswer(Map<String, Object> updateParamMap, String userId) {
		return this.oneInqryMapper.updateAnswer(updateParamMap);
	}

	@Override
	public int resignPro(Map<String, Object> map) {
		return this.oneInqryMapper.resignPro(map);
	}

	@Override
	public List<OneInqryDto> resignProList(Map<String, Object> map) {
		return this.oneInqryMapper.resignProList(map);
	}

	@Override
	public int getTotalResignPro(Map<String, Object> map) {
		return this.oneInqryMapper.getTotalResignPro(map);
	}

	@Override
	public int proSecssion(String proId) {
		return this.oneInqryMapper.proSecssion(proId);
	}

}
