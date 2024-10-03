package kr.or.ddit.proservice.serviceInquiry.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.SrvcBtfInqryDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceInquiry.mapper.SrvcBtfInqryMapper;
import kr.or.ddit.proservice.serviceInquiry.dto.V_SrvcBtfInqryDto;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SrvcBtfInqryServiceImpl implements SrvcBtfInqryService {

	private final SrvcBtfInqryMapper srvcBtfInqryMapper;
	private final FileuploadService fileuploadService;

	// 아이디 유형 확인 
	@Override
	public UsersDto userChk(String userId) {

		UsersDto usersDto = this.srvcBtfInqryMapper.userChk(userId);

		if (usersDto.getEmplyrTy().equals("ET01")) { // 회원일 경우, 프로 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET02");

		} else if (usersDto.getEmplyrTy().equals("ET02")) { // 프로일 경우, 회원 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET01");
		}
		log.info("userChk -> usersVO : {}",  usersDto.toString());

		return usersDto;
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);

		return this.srvcBtfInqryMapper.getTotal(map);
	}

	@Override
	public int getNoAnswerTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);

		return this.srvcBtfInqryMapper.getNoAnswerTotal(map);
	}

	@Override
	public int getSuccessTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);

		return this.srvcBtfInqryMapper.getSuccessTotal(map);
	}

	// 보낸 사전 문의 및 받은 사전문의 목록 조회 
	@Override
	public List<V_SrvcBtfInqryDto> btfInqryList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		log.info("(serviceImpl)btfInqryList -> usersVO from btfInqryList : {}",  usersDto);

		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);

		return this.srvcBtfInqryMapper.btfInqryList(map);
	}

	// 미답변 목록 조회
	@Override
	public List<V_SrvcBtfInqryDto> btfInqryNoAnswerList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		log.info("(serviceImpl)btfInqryList -> usersVO : {}", usersDto);
		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);

		return this.srvcBtfInqryMapper.btfInqryNoAnswerList(map);
	}

	// 답변 완료 목록 조회
	@Override
	public List<V_SrvcBtfInqryDto> btfInqrySuccessList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		log.info("(serviceImpl)btfInqryList -> usersVO from btfInqrySuccessList : {}",  usersDto);
		V_SrvcBtfInqryDto vSrvcBtfInqryDto = V_SrvcBtfInqryDto.builder().userId(usersDto.getUserId()).emplyrTy(usersDto.getEmplyrTy()).build();

		map.put("vSrvcBtfInqryVO", vSrvcBtfInqryDto);
		return this.srvcBtfInqryMapper.btfInqrySuccessList(map);
	}

	@Override
	public V_SrvcBtfInqryDto btfInqryDetail(V_SrvcBtfInqryDto vSrvcBtfInqryVO, String userId) {

		UsersDto usersDto = userChk(userId);
		vSrvcBtfInqryVO.setUserId(usersDto.getUserId());
		vSrvcBtfInqryVO.setEmplyrTy(usersDto.getEmplyrTy());

		return this.srvcBtfInqryMapper.btfInqryDetail(vSrvcBtfInqryVO);
	}

	@Override
	public int updateAnswer(Map<String, Object> updateParamMap, String userId) {

		updateParamMap.put("proId", userId);
		return this.srvcBtfInqryMapper.updateAnswer(updateParamMap);
	}

	@Override
	public int btfInqryCreatePost(SrvcBtfInqryDto srvcBtfInqryDto, List<MultipartFile> uploadFiles) {

		int res = 0;
		log.info("btfInqryCreatePost -> srvcRequstVO : {}",  srvcBtfInqryDto);
		log.info("btfInqryCreatePost -> 제목 : {}",  srvcBtfInqryDto.getBtfInqrySj());
		log.info("btfInqryCreatePost -> 내용 : {}",  srvcBtfInqryDto.getBtfInqryCn());
		log.info("btfInqryCreatePost -> 아이디 : {}",  srvcBtfInqryDto.getMberId());
		log.info("btfInqryCreatePost -> 프로아이디 : {}",  srvcBtfInqryDto.getProId());

		Map<String, Object> btfInqryInfoMap = new HashMap<>();

		btfInqryInfoMap.put("btfInqrySj", srvcBtfInqryDto.getBtfInqrySj());
		btfInqryInfoMap.put("btfInqryCn", srvcBtfInqryDto.getBtfInqryCn());
		btfInqryInfoMap.put("mberId", srvcBtfInqryDto.getMberId());
		btfInqryInfoMap.put("proId", srvcBtfInqryDto.getProId());

		// 요청서 기본 정보
		res = this.srvcBtfInqryMapper.btfInqryCreatePost(btfInqryInfoMap);

		String addPath = "pro_service\\btfInqryImage";
		res += fileuploadService.fileUpload(uploadFiles, addPath, srvcBtfInqryDto.getMberId(), res);

		return res;
	}

	@Override
	public int btfInqryUpdatePost(Map<String, Object> btfInqryUpdateMap, List<MultipartFile> uploadFiles,
		String userId) {
		int res = 0;
		// 문의 번호
		String btfInqryNo = (String)btfInqryUpdateMap.get("btfInqryNo");
		// 문의 변경 제목 
		String btfInqrySj = (String)btfInqryUpdateMap.get("hiddenBtfInqrySj");
		// 문의 변경 내용
		String btfInqryCn = (String)btfInqryUpdateMap.get("newBtfInqryCn");
		// 문의 첨부파일 번호
		String sprviseAtchmnflNo = (String)btfInqryUpdateMap.get("sprviseAtchmnflNo");

		log.info("[btfInqryUpdatePost/serviceImpl]번호 : {}", btfInqryUpdateMap.get("btfInqryNo"));
		log.info("[btfInqryUpdatePost/serviceImpl]제목 : {}", btfInqryUpdateMap.get("hiddenBtfInqrySj"));
		log.info("[btfInqryUpdatePost/serviceImpl]내용 : {}", btfInqryUpdateMap.get("newBtfInqryCn"));
		log.info("[btfInqryUpdatePost/serviceImpl]통합첨부파일 번호 : {}", btfInqryUpdateMap.get("sprviseAtchmnflNo"));
		log.info("[btfInqryUpdatePost/serviceImpl]기존 사진 배열 : {}", btfInqryUpdateMap.get("atchmnflNo[]"));

		// 사전문의 업데이트
		SrvcBtfInqryDto srvcBtfInqryDto = new SrvcBtfInqryDto();
		if (btfInqrySj != null || btfInqryCn != null) {
			srvcBtfInqryDto.setBtfInqryNo(Integer.valueOf(btfInqryNo));
			srvcBtfInqryDto.setBtfInqrySj(btfInqrySj);
			srvcBtfInqryDto.setBtfInqryCn(btfInqryCn);

			res = this.srvcBtfInqryMapper.btfInqryUpdatePost(srvcBtfInqryDto);
		}

		// 기존 이미지 삭제
		Map<String, Object> updateFileuploadMap = new HashMap<String, Object>();

		String atchmnflNoArrayParam = (String)btfInqryUpdateMap.get("atchmnflNo[]");
		String[] atchmnflNoArray = atchmnflNoArrayParam.split(",");
		log.info("[btfInqryUpdatePost/serviceImpl] String[] atchmnflNoArray : {}",  Arrays.toString(atchmnflNoArray));

		updateFileuploadMap.put("sprviseAtchmnflNo", sprviseAtchmnflNo);
		updateFileuploadMap.put("atchmnflNoArray", atchmnflNoArray);
		log.info("[btfInqryUpdatePost/serviceImpl] 기존 이미지 삭제 map : {}",  updateFileuploadMap);
		res += this.fileuploadService.updateFileupload(updateFileuploadMap);

		// 새로운 파일 업로드
		if (!uploadFiles.isEmpty()) {
			String addPath = "pro_service\\btfInqryImage";
			res += fileuploadService.newFileUpload(uploadFiles, addPath, userId, res, sprviseAtchmnflNo);
		}
		return res;
	}
}
