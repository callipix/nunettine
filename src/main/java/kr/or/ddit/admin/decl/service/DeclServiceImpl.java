package kr.or.ddit.admin.decl.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.admin.decl.mapper.DeclMapper;
import kr.or.ddit.dto.SntncDeclDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeclServiceImpl implements DeclService {

	private final DeclMapper declMapper;

	@Override
	public List<SntncDeclDto> decllbrSelect(Map<String, Object> map) {
		return this.declMapper.decllbrSelect(map);
	}

	@Override
	public LbrtyBbscttDto2 lbrtyBbscttVo(SntncDeclDto sntncDeclDto) {
		return this.declMapper.lbrtyBbscttVo(sntncDeclDto);
	}

	@Override
	public List<SntncDeclDto> declResnList(SntncDeclDto sntncDeclDto) {
		return this.declMapper.declResnList(sntncDeclDto);
	}

	@Override
	@Transactional
	public int declSet(int lbrtyBbscttNo) {
		int result = 0;
		// 이곳에서 두가지 실행
		// 1번 해당 게시글 삭제 여부가 1로 업데이트가 되면서
		result = this.declMapper.declSet1(lbrtyBbscttNo);
		log.info("1번째 결과 값 : {}", result);
		// 2번 신고에 대한 처리를 완료로 뜨게 해야된다.(DECL_PROCESS_AT = 1)
		result += this.declMapper.declSet2(lbrtyBbscttNo);
		log.info("2번째 결과 값 : {}", result);
		result += this.declMapper.declSet3(lbrtyBbscttNo);
		log.info("3번째 결과 값 : {}", result);

		return result;
	}

	@Override
	public List<UsersDto> userList() {
		return this.declMapper.userList();
	}

	@Override
	public int getDeclCount(String userId2) {
		return this.declMapper.getDeclCount(userId2);
	}

	@Override
	public List<UserDeclDto> userDeclList(String userId) {
		return this.declMapper.userDeclList(userId);
	}

	@Transactional
	@Override
	public int userDeclSet(Map<String, Object> map) {
		int result = 0;
		result += this.declMapper.userDeclSet(map);
		result += this.declMapper.declProcessAtSet(map);

		return result;

	}

	@Override
	public List<PunshDto> declHistoryList(String userId) {
		return this.declMapper.declHistoryList(userId);
	}

}
