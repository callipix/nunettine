package kr.or.ddit.proservice.serviceRequest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.SrvcRequstDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.proservice.serviceInquiry.mapper.SrvcBtfInqryMapper;
import kr.or.ddit.proservice.serviceRequest.mapper.SrvcRequstMapper;
import kr.or.ddit.proservice.serviceRequest.dto.V_SrvcRequstDto;
import kr.or.ddit.util.fileupload.service.FileuploadService;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SrvcRequstSeviceImpl implements SrvcRequstService {

	private final SrvcBtfInqryMapper srvcBtfInqryMapper;
	private final SrvcRequstMapper srvcRequstMapper;
	private final FileuploadService fileuploadService;

	V_SrvcRequstDto vSrvcRequstVO = new V_SrvcRequstDto();

	@Override
	public UsersDto userChk(String userId) {

		UsersDto usersDto = this.srvcBtfInqryMapper.userChk(userId);

		if (usersDto.getEmplyrTy().equals("ET01")) { // 회원일 경우, 프로 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET02");

		} else if (usersDto.getEmplyrTy().equals("ET02")) { // 프로일 경우, 회원 닉네임을 얻기 위함
			usersDto.setEmplyrTy("ET01");
		}
		log.info("userChk -> usersVO : {}", usersDto.toString());

		return usersDto;
	}

	@Override
	public List<V_SrvcRequstDto> srvcRqList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.srvcRqList(map);
	}

	@Override
	public List<V_SrvcRequstDto> srvcRqNoAnswerList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.srvcRqNoAnswerList(map);
	}

	@Override
	public List<V_SrvcRequstDto> srvcRqSuccessList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.srvcRqSuccessList(map);
	}

	@Override
	public List<V_SrvcRequstDto> srvcRqRejectList(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.srvcRqRejectList(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.getTotal(map);
	}

	@Override
	public int getNoAnswerTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.getNoAnswerTotal(map);
	}

	@Override
	public int getSuccessTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.getSuccessTotal(map);
	}

	@Override
	public int getRejectTotal(Map<String, Object> map) {
		UsersDto usersDto = userChk((String)map.get("userId"));
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		map.put("vSrvcRequstVO", vSrvcRequstVO);

		return this.srvcRequstMapper.getRejectTotal(map);
	}

	@Override
	public V_SrvcRequstDto srvcRqDetail(V_SrvcRequstDto vSrvcRequstVO, String userId) {
		UsersDto usersDto = userChk(userId);
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		return this.srvcRequstMapper.srvcRqDetail(vSrvcRequstVO);
	}

	@Override
	public int processFn(int srvcRequstNo, String userId) {
		UsersDto usersDto = userChk(userId);
		vSrvcRequstVO.setUserId(usersDto.getUserId());
		vSrvcRequstVO.setEmplyrTy(usersDto.getEmplyrTy());

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String emplyrTy = usersDto.getEmplyrTy();
		String processUser = "";
		if ("ET01".equals(emplyrTy)) { // 프로
			processUser = "SRVC_REQUST_PROCESS_PRO";
		} else if ("ET02".equals(emplyrTy)) { //회원
			processUser = "SRVC_REQUST_PROCESS_MBER";
		}
		paramMap.put("emplyrTy", emplyrTy);
		paramMap.put("processUser", processUser);
		paramMap.put("userId", userId);
		paramMap.put("srvcRequstNo", srvcRequstNo);

		return this.srvcRequstMapper.processFn(paramMap);
	}

	@Override
	public int acceptRequst(Map<String, Object> acceptMap, String userId) {
		acceptMap.put("proId", userId);
		int res = 0;

		res = this.srvcRequstMapper.acceptRequst(acceptMap);

		return res;
	}

	@Override
	public int rejectRequst(Map<String, Object> rejectMap, String userId) {
		rejectMap.put("proId", userId);
		int res = 0;

		res = this.srvcRequstMapper.rejectRequst(rejectMap);

		return res;
	}

	@Override
	public int srvcRqCreatePost(SrvcRequstDto srvcRequstDto, List<MultipartFile> uploadFiles) {

		int res = 0;
		log.info("srvcRqCreatePost -> srvcRequstVO : {}", srvcRequstDto);
		log.info("srvcRqCreatePost -> 제목 : {}", srvcRequstDto.getSrvcRequstSj());
		log.info("srvcRqCreatePost -> 내용 : {}", srvcRequstDto.getSrvcRequstCn());

		Map<String, Object> srvcRqInfoMap = new HashMap<String, Object>();

		srvcRqInfoMap.put("srvcRequstSj", srvcRequstDto.getSrvcRequstSj());
		srvcRqInfoMap.put("srvcRequstCn", srvcRequstDto.getSrvcRequstCn());
		srvcRqInfoMap.put("mberId", srvcRequstDto.getMberId());
		srvcRqInfoMap.put("proId", srvcRequstDto.getProId());

		// 요청서 기본 정보
		res = this.srvcRequstMapper.srvcRqCreatePost(srvcRqInfoMap);

		String addPath = "pro_service\\srvcRequstImage";
		log.info("uploadFiles : {}", uploadFiles.toString());

		if (uploadFiles != null && !uploadFiles.isEmpty()) {
			res += fileuploadService.fileUpload(uploadFiles, addPath,
				srvcRequstDto.getMberId(), res);
		}
		return res;
	}

	@Override
	public int srvcRqUpdatePost(Map<String, Object> srvcRqUpdateMap, List<MultipartFile> uploadFiles, String userId) {
		int res = 0;
		// 문의 번호
		String srvcRequstNo = (String)srvcRqUpdateMap.get("srvcRequstNo");
		// 문의 변경 제목 
		String srvcRequstSj = (String)srvcRqUpdateMap.get("hiddenSrvcRequstSj");
		// 문의 변경 내용
		String srvcRequstCn = (String)srvcRqUpdateMap.get("newSrvcRequstCn");
		// 문의 첨부파일 번호
		String sprviseAtchmnflNo = (String)srvcRqUpdateMap.get("sprviseAtchmnflNo");

		// 사전문의 업데이트
		SrvcRequstDto srvcRequstDto = new SrvcRequstDto();
		if (srvcRequstSj != null || srvcRequstCn != null) {
			srvcRequstDto.setSrvcRequstNo(Integer.valueOf(srvcRequstNo));
			srvcRequstDto.setSrvcRequstSj(srvcRequstSj);
			srvcRequstDto.setSrvcRequstCn(srvcRequstCn);

			res = this.srvcRequstMapper.srvcRqUpdatePost(srvcRequstDto);
		}

		Map<String, Object> updateFileuploadMap = new HashMap<String, Object>();
		String atchmnflNoArrayParam = (String)srvcRqUpdateMap.get("atchmnflNo[]");
		String[] atchmnflNoArray = atchmnflNoArrayParam.split(",");

		updateFileuploadMap.put("sprviseAtchmnflNo", sprviseAtchmnflNo);
		updateFileuploadMap.put("atchmnflNoArray", atchmnflNoArray);

		res += this.fileuploadService.updateFileupload(updateFileuploadMap);

		// 새로운 파일 업로드
		if (!uploadFiles.isEmpty()) {
			String addPath = "pro_service\\btfInqryImage";
			res += fileuploadService.newFileUpload(uploadFiles, addPath, userId, res, sprviseAtchmnflNo);
		}
		return res;
	}
}
