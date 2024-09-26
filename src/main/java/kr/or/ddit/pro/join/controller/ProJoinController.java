package kr.or.ddit.pro.join.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.pro.join.service.ProJoinService;
import kr.or.ddit.dto.VProUsersDto;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProJoinController {

	private final String uploadFolder;
	private final ProJoinService proJoinService;
	private final JavaMailSenderImpl mailSender;

	@GetMapping("/proLogin")
	public String proLogin() {
		return "pro/proLogin";
	}

	//프로 회원가입 화면으로 이동
	@GetMapping("/proJoin")
	public String proJoin(Model model) {

		//전문분야 코드 출력
		List<SpcltyRealmDto> codeList = this.proJoinService.selectCode();
		//		log.info("전문분야 코드 : " + codeList);
		//		log.info("전문분야 코드 : " + codeList.get(0));
		model.addAttribute("codeList", codeList);

		return "pro/proJoin";
	}

	//하위 전문분애 출력
	@ResponseBody
	@GetMapping("/codeSelect")
	public List<SpcltyRealmDto> codeSelect(String code) {
		List<SpcltyRealmDto> codeList = this.proJoinService.codeSelect(code);
		//		log.info("codeList : " + codeList);
		return codeList;
	}

	//이메일 중복확인
	@ResponseBody
	@GetMapping("/emailCk")
	public int emailCk(String email) {
		int result = this.proJoinService.emailCk(email);

		return result;
	}

	//아이디 중복확인
	@ResponseBody
	@GetMapping("/idCk")
	public int idCk(String userId) {
		int result = this.proJoinService.idCk(userId);

		return result;
	}

	//닉네임 중복 확인
	@ResponseBody
	@GetMapping("/ncnmCk")
	public int ncnmCk(String userNcnm) {
		int result = this.proJoinService.ncnmCk(userNcnm);

		return result;
	}

	//휴대폰 본인인증
	@ResponseBody
	@PostMapping("/check/sendSMS")
	public String sendSMS(String proMbtlnum) {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		log.info("인증번호 : {}", numStr);
		this.proJoinService.certifiedPhoneNumber(proMbtlnum, numStr);
		return numStr;
	}

	//프로 회원가입
	@PostMapping("/proInsert")
	public String proInsert(UsersDto usersDto, ProDto proDto, AdresDto adresDto) {
		//		log.info("userVO : " + usersVO);
		//		log.info("proVO : " + proVO);
		//		log.info("adresVO : " + adresVO);
		Map<String, Object> map = new HashMap<>();

		//프로필사진 업로드 처리
		if (proDto.getProProflPhoto() != null && !proDto.getProProflPhoto().isEmpty()) {
			MultipartFile multipartFile = proDto.getUploadFile();

			File uploadPath = new File(uploadFolder, getFolder());
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			File saveFile = new File(uploadPath, uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
			}
			String url = "/images/" + getFolder().replace("\\", "/") + "/" + uploadFileName;
			map.put("proProflPhoto", url);
			map.put("profile", url);
		} else {
			map.put("proProflPhoto", null);
		}
		map.put("userId", usersDto.getUserId());
		map.put("userNcnm", usersDto.getUserNcnm());
		map.put("proMbtlnum", proDto.getProMbtlnum());
		map.put("sexdstnTy", proDto.getSexdstnTy());
		map.put("email", proDto.getEmail());
		map.put("spcltyRealmCode", proDto.getSpcltyRealmCode());
		map.put("userNm", usersDto.getUserNm());
		map.put("userPassword", usersDto.getUserPassword());
		map.put("adres", adresDto.getAdres());
		map.put("detailAdres", adresDto.getDetailAdres());
		map.put("zip", adresDto.getZip());

		int result = this.proJoinService.proInsert(map);

		return "welcome";
	}

	@GetMapping("/admLogout")
	public String admLogout(HttpSession session) {
		session.removeAttribute("admSession");
		return "redirect:/main";
	}

	////////////////////////////////////////////////////////////////
	@SuppressWarnings("unlikely-arg-type")
	//프로 로그인
	@ResponseBody
	@PostMapping("/proLogin")
	public Map<String, Object> proLogin(String userId, String userPassword, HttpServletRequest request) {

		Map<String, Object> userMap = new HashMap<>();
		userMap.put("userId", userId);
		userMap.put("userPassword", userPassword);
		log.info("로그인 전 map : {}", userMap);

		UsersDto adminVO = this.proJoinService.adminVO(userId);
		UsersDto usersDto = this.proJoinService.proLogin(userMap);
		//동균
		if (adminVO == null) {
			return userMap;
		}
		if (usersDto == null) {
			return userMap;
		}
		//동균끝
		
		/*
		adminVO : UsersVO(userId=testAdmin, userNm=테스트관리자, userPassword=asdasd, emplyrTy=ET03
		, secsnAt=1, userNcnm=테스트관리자, cnt=0)
		 */
		log.info("adminVO : {}", adminVO);
		log.info("usersVO : {}", usersDto);
		//ET03 또는 ET02

		if ("ET03".equals(usersDto.getEmplyrTy())) {//관리자

			log.info("asdp : {}", adminVO);

			userMap.put("cnt", 1);
			userMap.put("type", "ET03");
			userMap.put("userNcnm", adminVO.getUserNcnm()); //닉네임

			HttpSession session = request.getSession();

			if (!userMap.isEmpty()) {
				session.setAttribute("admSession", adminVO);
			} else {
				session.setAttribute("admSession", null);
			}
			log.info("세션체크 : {}", userMap);
			userMap.get("");

			return userMap;

		} else if ("ET02".equals(usersDto.getEmplyrTy())) {
			if (usersDto == null) {
				return userMap;
			}
			//동균 신고 처리 부분 추가
			//log.info("해당 프로 아이디 : " + userId);
			Date userEndDt = this.proJoinService.getUserEndDt(userId);
			log.info("디비로 가져온 userEndDt : {}", userEndDt);

			// 현재 날짜와 시간 가져오기

			// 디비로 가져온 제재 종료일이 현재 시간과 같다면
			//				if(userEndDt==now) {
			//			        // Calendar 객체를 사용하여 어제의 날짜를 구하기
			//			        Calendar cal = Calendar.getInstance();
			//			        cal.setTime(userEndDt);
			//			        cal.add(Calendar.DATE, -1); // 현재 날짜에서 1일 빼기
			//			        Date yesterdayDate = cal.getTime();
			//
			//			        log.info("어제: " + yesterdayDate);
			//
			//					userEndDt = yesterdayDate;
			//				}

			//LocalDate nowDt = userEndDt.toLocalDate();
			//LocalDate now = LocalDate.now();

			Date now = new Date();
			log.info("제재 종료일과 비교할 현재일 : {}", now);

			if (userEndDt.after(now)) {
				userMap.put("userEndDt", userEndDt);
				usersDto.setCnt(2);
				log.info("현재 날짜와 제재 종료일일 다른 경우 로근이 cnt : {}", usersDto.getCnt());
				userMap.put("cnt", usersDto.getCnt());
				return userMap;
			}
			//동균 신고 처리 부분 추가 끝

			//		log.info("로그인 usersVO : " + usersVO);
			VProUsersDto vProUsersDto = this.proJoinService.getProfile(userMap);
			log.info("로그인 vProUsersVO : {}", vProUsersDto);

			try {
				AdresDto adresDto = this.proJoinService.getAdres(userMap);
				userMap.put("zip", adresDto.getZip()); //우편번호
				userMap.put("adres", adresDto.getAdres()); //주소
				userMap.put("detailAdres", adresDto.getDetailAdres()); //상세주소
				//			log.info("로그인 후 adresVO : " + adresVO);
			} catch (NullPointerException e) {
				userMap.put("zip", "-"); //우편번호
				userMap.put("adres", "-"); //주소
				userMap.put("detailAdres", "-"); //상세주소
			}
			userMap.put("cnt", usersDto.getCnt());
			userMap.put("type", usersDto.getEmplyrTy()); //유저타입(프로/회원)
			userMap.put("userNcnm", usersDto.getUserNcnm()); //닉네임

			if (vProUsersDto != null && usersDto.getCnt() == 1) {
				String profile = vProUsersDto.getProProflPhoto();
				if (profile != null) {
					profile = vProUsersDto.getProProflPhoto();
				}
				userMap.put("email", vProUsersDto.getEmail()); //이메일
				userMap.put("userNm", vProUsersDto.getUserNm()); //이름
				userMap.put("proMbtlnum", vProUsersDto.getProMbtlnum()); //전화번호
				userMap.put("sexdstnTy", vProUsersDto.getSexdstnTy()); //성별
				userMap.put("profile", profile); //프로필사진
				userMap.put("changePwCk", usersDto.getChangePwCk()); //임시비번여부

				//전문가 코드 나열
				String spcltyRealmCode = vProUsersDto.getSpcltyRealmCode(); //전문 분야 코드
				String thirdsrCode = this.proJoinService.proSRCode(spcltyRealmCode); //depth 3단계 이름
				String secondSRCode = ""; //depth 2단계 이름
				String firstSRCode = ""; //depth 1단계 이름
				String spcltyRealmNm = ""; //전문 분야 풀네임
				if (spcltyRealmCode.length() == 6) { //전문 분야 코드 6자리일때
					secondSRCode = this.proJoinService.proSRCode(spcltyRealmCode.substring(0, 4));
					firstSRCode = this.proJoinService.proSRCode(spcltyRealmCode.substring(0, 3));
					spcltyRealmNm = firstSRCode + " >> " + secondSRCode + " >> " + thirdsrCode;
				}
				if (spcltyRealmCode.length() == 4) {
					firstSRCode = this.proJoinService.proSRCode(spcltyRealmCode.substring(0, 3));
					spcltyRealmNm = firstSRCode + " >> " + thirdsrCode;
				}
				userMap.put("firstSRCode", firstSRCode); //depth 1단계 이름
				userMap.put("secondSRCode", secondSRCode); //depth 2단계 이름
				userMap.put("thirdsrCode", thirdsrCode); //depth 3단계 이름
				userMap.put("spcltyRealmNm", spcltyRealmNm); //전문분야 풀네임
				//			log.info("세션 들어갈 map : "+userMap);
				HttpSession session = request.getSession();

				if (!userMap.isEmpty()) {
					session.setAttribute("proSession", userMap);
				} else {
					session.setAttribute("proSession", null);
				}
			} else if (vProUsersDto == null && usersDto.getCnt() == 1) {
				userMap.put("cnt", 1);
			} else if (vProUsersDto == null && usersDto.getCnt() == 0) {
				userMap.put("cnt", 0);
			}
			log.info("세션체크 for proLogin : {}", userMap);
			userMap.get("");

		} else {
			log.info("1" + usersDto.getCnt());
			if (usersDto.getCnt() == 1) {
				log.info("2{}", usersDto.getCnt());
				userMap.put("type", "ET01");
			} else {
				log.info("3{}", usersDto.getCnt());
				userMap.put("cnt", 0);
			}
		}

		return userMap;
	}

	//프로 로그아웃
	@GetMapping("/proLogout")
	public String proLogout(HttpSession session) {
		session.removeAttribute("proSession");
		return "redirect:/main";
	}

	public String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}

	//아이디 찾기
	@ResponseBody
	@PostMapping("/idSearch")
	public UsersDto idSearch(VProUsersDto vProUsersDto) {
		UsersDto usersDto = this.proJoinService.idSearch(vProUsersDto);
		if (usersDto == null) {
			VMberUsersDto mberVO = this.proJoinService.idSearch2(vProUsersDto);
			UsersDto userVO = new UsersDto();
			if (mberVO != null) {
				userVO.setEmplyrTy(mberVO.getEmplyrTy());
				userVO.setUserId(mberVO.getUserId());
				return userVO;
			} else {
				userVO.setEmplyrTy("warn");
				return userVO;
			}
		}
		return usersDto;
	}

	//비밀번호찾기
	@ResponseBody
	@PostMapping("/pwSearch")
	public Map<String, Object> pwSearch(VProUsersDto vProUsersDto) {
		String userPassword;
		Map<String, Object> map = new HashMap<String, Object>();
		UsersDto usersDto = this.proJoinService.pwSearch(vProUsersDto);
		if (usersDto == null) {
			String emplyrTy = this.proJoinService.pwSearch2(vProUsersDto);
			if (emplyrTy == null) {//프로조회
				map.put("emplyrTy", "warn");
				return map;
			} else {
				map.put("emplyrTy", emplyrTy);
				return map;
			}
		} else {
			String emplyrTy = usersDto.getEmplyrTy();
			map.put("emplyrTy", emplyrTy);
			userPassword = usersDto.getUserPassword();
		}
		map.put("proId", vProUsersDto.getProId());

		if (userPassword == null || userPassword.isEmpty()) { //검색 결과가 없을때
			userPassword = null;
			map.put("userPassword", userPassword);
		} else { //비밀번호가 조회되면 이메일로 전송
			// 임시 비밀번호를 발급받기 위한 랜덤번호(0~9,A~Z 까지 추가하고 싶은 문자는 아래의 형식처럼 추가가능)
			char[] charSet = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			// 임시비빌번호가 저장될 변수
			String str = "";
			// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
			int idx = 0;
			for (int i = 0; i < 10; i++) {
				idx = (int)(charSet.length * Math.random());
				str += charSet[idx];
			}
			log.info("메일 보낼 임시비번 " + str);
			String setForm = "ddit230901@gmail.com"; //보낼 이메일 주소
			String toMail = vProUsersDto.getEmail(); //받을 이메일 주소
			String title = "누네띠네 프로님의 임시비밀번호 발송 이메일 입니다."; //이메일 제목
			String content =
				"누네띠네를 이용해 주셔서 감사합니다." + "<br><br>" + "변경된 임시비밀번호는 <div style='backgroud-color:yellow;'><b>"
					+ str + "</b> 입니다.</div><br><br>해당 임시비밀번호로 로그인 후 꼭 비밀번호를 변경해주세요.<br><br>"
					+ "<a href='localhost/main'>누네띠네로 돌아가기</a>";
			mailSend(setForm, toMail, title, content); //메일 전송 메소드 호출
			map.put("imsiPw", str);
			// 임시비밀번호로 변경
			int result = this.proJoinService.updatePw(map);
		}
		return map;
	}

	//이메일 전송 메소드
	public void mailSend(String setForm, String toMail, String title, String content) {
		//true, 매개값 주면 multipart 형싱의 메세지 전달 가능, 인코딩도 가능
		MimeMessage message = mailSender.createMimeMessage();
		log.info("메일전송 성공");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setForm);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true); //true면 html형식으로 전송, 아니면 text로 그냥 감
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}