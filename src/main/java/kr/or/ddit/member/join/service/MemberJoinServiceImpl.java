package kr.or.ddit.member.join.service;

import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.dto.VMberUsersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.join.mapper.MemberJoinMapper;
import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.UsersDto;
import kr.or.ddit.dto.VProUsersDto;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberJoinServiceImpl implements MemberJoinService {

	private final MemberJoinMapper memberJoinMapper;

	@Override
	public int emailCk(String email) {
		return this.memberJoinMapper.emailCk(email);
	}

	@Override
	public int idCk(String userId) {
		return this.memberJoinMapper.idCk(userId);
	}

	@Override
	public int ncnmCk(String userNcnm) {
		return this.memberJoinMapper.ncnmCk(userNcnm);
	}

	@Override
	public void certifiedPhoneNumber(String mberMbtlnum, String numStr) {
		String api_key = "NCSLIUVALN15NV86";
        String api_secret = "YCBUEUDBEZA5DMIDC6OAFBY08MRGMZZE";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", mberMbtlnum);    // 수신전화번호
        params.put("from", "01083354487");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "문자메세지 테스트 : 인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version
        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            log.info(obj.toString());
        } catch (CoolsmsException e) {
            log.info(e.getMessage());
            log.info(String.valueOf(e.getCode()));
        }
		
	}

	@Override
	public UsersDto memberLogin(Map<String, Object> userMap) {
		return this.memberJoinMapper.memberLogin(userMap);
	}

	@Override
	public int memberInsert(Map<String, Object> map) {
		return this.memberJoinMapper.memberInsert(map);
	}

	@Override
	public VMberUsersDto getProfile(Map<String, Object> userMap) {
		return this.memberJoinMapper.getProfile(userMap);
	}

	@Override
	public UsersDto pwSearch(VMberUsersDto vMberUsersDto) {
		return this.memberJoinMapper.pwSearch(vMberUsersDto);
	}

	@Override
	public UsersDto idSearch(VMberUsersDto vMberUsersDto) {
		return this.memberJoinMapper.idSearch(vMberUsersDto);
	}

	@Override
	public int updatePw(Map<String, Object> map) {
		return this.memberJoinMapper.updatePw(map);
	}

	@Override
	public VProUsersDto idSearch2(VMberUsersDto vMberUsersDto) {
		return this.memberJoinMapper.idSearch2(vMberUsersDto);
	}

	@Override
	public String pwSearch2(VMberUsersDto vMberUsersDto) {
		return this.memberJoinMapper.pwSearch2(vMberUsersDto);
	}

	@Override
	public AdresDto getAdres(Map<String, Object> map) {
		return this.memberJoinMapper.getAdres(map);
	}

}
