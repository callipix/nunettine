package kr.or.ddit.pro.join.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import kr.or.ddit.pro.join.mapper.ProJoinMapper;
import kr.or.ddit.dto.AdresDto;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProJoinServiceImpl implements ProJoinService {
	
	private final ProJoinMapper proJoinMapper;
	
	@Override
	public int emailCk(String email) {
		return this.proJoinMapper.emailCk(email);
	}

	@Override
	public int idCk(String userId) {
		return this.proJoinMapper.idCk(userId);
	}

	@Override
	public int ncnmCk(String userNcnm) {
		return this.proJoinMapper.ncnmCk(userNcnm);
	}
	
	@Override
	public void certifiedPhoneNumber(String proMbtlnum, String numStr) {
		 String api_key = "NCSLIUVALN15NV86";
        String api_secret = "YCBUEUDBEZA5DMIDC6OAFBY08MRGMZZE";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", proMbtlnum);    // 수신전화번호
        params.put("from", "01083354487");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "문자메세지 테스트 : 인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version
        try {
            JSONObject obj = coolsms.send(params);
            log.info("obj : {}", obj.toString());
        } catch (CoolsmsException e) {
            log.info("{}",e.getMessage());
            log.info("{}",e.getCode());
        }
		
	}

	@Override
	public UsersDto proLogin(Map<String, Object> userMap) {
		return this.proJoinMapper.proLogin(userMap);
	}
	
	@Override
	public int proInsert(Map<String, Object> map) {
		return this.proJoinMapper.proInsert(map);
	}

	@Override
	public VProUsersDto getProfile(Map<String, Object> userMap) {
		return this.proJoinMapper.getProfile(userMap);
	}

	@Override
	public UsersDto idSearch(VProUsersDto vProUsersDto) {
		return this.proJoinMapper.idSearch(vProUsersDto);
	}

	@Override
	public UsersDto pwSearch(VProUsersDto vProUsersDto) {
		return this.proJoinMapper.pwSearch(vProUsersDto);
	}

	@Override
	public int updatePw(Map<String, Object> map) {
		return this.proJoinMapper.updatePw(map);
	}

	@Override
	public VMberUsersDto idSearch2(VProUsersDto vProUsersDto) {
		return this.proJoinMapper.idSearch2(vProUsersDto);
	}

	@Override
	public String pwSearch2(VProUsersDto vProUsersDto) {
		return this.proJoinMapper.pwSearch2(vProUsersDto);
	}


	@Override
	public AdresDto getAdres(Map<String, Object> userMap) {
		return this.proJoinMapper.getAdres(userMap);
	}

	@Override
	public String proSRCode(String spcltyRealmCode) {
		return this.proJoinMapper.proSRCode(spcltyRealmCode);
	}

	@Override
	public List<SpcltyRealmDto> selectCode() {
		return this.proJoinMapper.selectCode();
	}

	@Override
	public List<SpcltyRealmDto> codeSelect(String code) {
		return this.proJoinMapper.codeSelect(code);
	}


	//관리자
	@Override
	public UsersDto admLogin(Map<String, Object> userMap) {
		// TODO Auto-generated method stub
		return this.proJoinMapper.admLogin(userMap);
	}

	@Override
	public UsersDto adminVO(String userId) {
		// TODO Auto-generated method stub
		return this.proJoinMapper.adminVO(userId);
	}

	@Override
	public List<VOndyclProUsersDto> proMyClassList(String proId) {
		List<VOndyclProUsersDto> vOndyclProUsersDtoList = this.proJoinMapper.proMyClassList(proId);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String todayStr = sdf.format(date);
		try {
		    Date today = sdf.parse(todayStr);

		    for(int i = 0; i < vOndyclProUsersDtoList.size(); i++) {
		        Date ondyclSchdulDe = sdf.parse(vOndyclProUsersDtoList.get(i).getOndyclSchdulDe());

		        boolean dayCheck = ondyclSchdulDe.before(today);
		        vOndyclProUsersDtoList.get(i).setDayCheck(dayCheck);
		        log.info("시간 비교 : " + todayStr + "/" + ondyclSchdulDe + " 불린 : " + dayCheck);
		    }
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return vOndyclProUsersDtoList;
	}

	@Override
	public int countProMyClass(String proId) {
		return this.proJoinMapper.countProMyClass(proId);
	}
	
	//동균 신고처리 추가
	@Override
	public Date getUserEndDt(String userId) {
		return this.proJoinMapper.getUserEndDt(userId);
	}
	//동균 추가 끝

}
