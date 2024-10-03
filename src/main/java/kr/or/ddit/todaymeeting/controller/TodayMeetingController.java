package kr.or.ddit.todaymeeting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dto.TdmtngDto;
import kr.or.ddit.dto.TdmtngPrtcpntDto;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.chatting.service.MessageService;
import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.todaymeeting.service.TodayMeetingService;
import kr.or.ddit.util.ArticlePage3;
import kr.or.ddit.dto.TdmtngChSpMshgDto;
import kr.or.ddit.dto.UsersDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
@RequestMapping("/todayMeeting")
public class TodayMeetingController {

	private final TodayMeetingService todayMeetingService;
	private final MessageService messageService;

	//세션값으로 아이디 가져오기
	private String userId(HttpServletRequest request) {

		Object proSession = request.getSession().getAttribute("proSession");
		Object memSession = request.getSession().getAttribute("memSession");

		if (proSession != null && proSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)proSession).get("userId");
			log.info("proSession : {}", userId);

			return userId != null ? userId.toString() : null;
		}
		if (memSession != null && memSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)memSession).get("userId");
			log.info("memSession : {}", userId);

			return userId != null ? userId.toString() : null;

		}

		return "not";
	}

	@GetMapping("/calendar")
	public String cal() {
		return "todayMeeting/cal/calendar";
	}

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {

		String userId = userId(request);

		log.info("main : {}", userId);

		model.addAttribute("userId", userId);

		return "todayMeeting/index";
	}

	@GetMapping("/create")
	public String create() {
		return "todayMeeting/create";
	}

	@ResponseBody
	@PostMapping("/listAjax")
	public ArticlePage3<TdmtngDto> listAjax(@RequestBody(required = false) Map<String, Object> map) {

		log.info("map : {}", map);

		int size = 10;
		int total = this.todayMeetingService.getTotal(map);

		log.info("listAjax -> total : {}", total);

		map.put("total", total);

		log.info("map : {}", map);

		List<TdmtngDto> tdmtngDtoList = this.todayMeetingService.list(map);

		log.info("listAjax -> tdmtngVOList : {}", tdmtngDtoList);

		String currentPage = map.get("currentPage").toString();

		log.info(currentPage);

		String keyword = map.get("keyword").toString();
		log.info("listAjax -> keyword : {}", keyword);

		String selectColumn = map.get("selectColumn").toString();
		log.info("listAjax -> selectColumn : {}", selectColumn);

		ArticlePage3<TdmtngDto> data = new ArticlePage3<>(total,
			Integer.parseInt(currentPage), size, tdmtngDtoList, keyword, selectColumn);

		log.info("listAjax -> data : {}", data);

		return data;
	}

	// 내 이벤트 조회
	@GetMapping("/calendarList")
	@ResponseBody
	public List<Map<String, Object>> showAllEventInUpdate(String userId) {

		log.info(userId);

		JSONObject jsonObj;
		JSONArray jsonArr = new JSONArray();

		HashMap<String, Object> hash = new HashMap<>();
		List<TdmtngDto> list = todayMeetingService.findAll(userId);

		log.info("calList : {}", list);

		for (TdmtngDto tdmtngcal : list) {
			hash.put("id", tdmtngcal.getTdmtngNo());
			hash.put("title", tdmtngcal.getTdmtngNm());
			hash.put("start", tdmtngcal.getTdmtngDt());

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		log.info("jsonArrCheck : {}", jsonArr);
		return jsonArr;
	}

	@GetMapping("/detail")
	public String detail(HttpServletRequest request, int tdmtngNo
		, Model model) {
		log.info("detail -> tdmtngNo : {}", tdmtngNo);

		String sessionId = userId(request);

		TdmtngDto tdmtngDto = todayMeetingService.detail(tdmtngNo);

		log.info("detail -> tdmtngVO : {}", tdmtngDto);

		int count = this.todayMeetingService.chatMemCount(tdmtngNo);

		log.info("chatMemCount -> count : {}", count);

		model.addAttribute("sessionId", sessionId);
		model.addAttribute("tdmtngVO", tdmtngDto);
		model.addAttribute("chatMemCount", count);

		return "todayMeeting/detail";
	}

	@ResponseBody
	@PostMapping("/create")
	public int create(HttpServletRequest request, TdmtngDto tdmtngDto) {

		String userId = userId(request);

		tdmtngDto.setUserId(userId);

		log.info("create -> tdmtngVO : {}", tdmtngDto);

		int result = this.todayMeetingService.create(tdmtngDto);

		log.info("tdmtngVO -> result : {}", result);
		log.info("tdmtngVO -> result : {}", tdmtngDto.getTdmtngNo());

		return tdmtngDto.getTdmtngNo();
	}

	@PostMapping("/update")
	public String update(TdmtngDto tdmtngDto) {
		log.info("update -> tdmtngVO : {}", tdmtngDto);

		int result = this.todayMeetingService.update(tdmtngDto);

		log.info("tdmtngVO -> result from update : {}", result);

		//redirect : 새로운 URL요청
		return "redirect:/todayMeeting/detail?tdmtngNo=" + tdmtngDto.getTdmtngNo();
	}

	@GetMapping("/delete")
	public String delete(int tdmtngNo) {
		log.info("delete -> tdmtngno : {}", tdmtngNo);

		int result = this.todayMeetingService.delete(tdmtngNo);

		log.info("delete -> result : {}", result);

		return "todayMeeting/index";
	}

	//내 채팅 불러오기
	@ResponseBody
	@PostMapping("/selectMyChat")
	public TdmtngPrtcpntDto selectMyChat(@RequestBody TdmtngPrtcpntDto tdmtngPrtcpntDto) {

		log.info("selectMyChat -> tdmtngPrtcpntVO1 : {}", tdmtngPrtcpntDto);

		tdmtngPrtcpntDto = this.todayMeetingService.selectMyChat(tdmtngPrtcpntDto);

		log.info("selectMyChat -> tdmtngPrtcpntVO2 : {}", tdmtngPrtcpntDto);

		return tdmtngPrtcpntDto;
	}

	//채팅 참여(INSERT)
	@ResponseBody
	@PostMapping("/joinChat")
	public int joinChat(HttpServletRequest request, int tdmtngNo, TdmtngPrtcpntDto tdmtngPrtcpntDto) {
		//Unknown return value type: java.lang.Integer 에러 : @ResponseBody를 안 해줘서..

		log.info("joinChat -> tdmtngNo : {}", tdmtngNo);

		String userId = userId(request);

		tdmtngPrtcpntDto.setUserId(userId);
		tdmtngPrtcpntDto.setTdmtngNo(tdmtngNo);

		log.info("joinChat -> tdmtngPrtcpntVO : {}", tdmtngPrtcpntDto);

		int result = this.todayMeetingService.joinChat(tdmtngPrtcpntDto);

		log.info("joinChat -> result : {}", result);

		return result;
	}

	//채팅방 멤버 리스트
	@ResponseBody
	@PostMapping("/chatMemList")
	public List<TdmtngPrtcpntDto> chatMemList(int tdmtngNo) {

		log.info("chatMemList -> tdmtngNo : {}", tdmtngNo);

		List<TdmtngPrtcpntDto> chatMemList = this.todayMeetingService.chatMemList(tdmtngNo);

		log.info("chatMemList -> chatMemList : {}", chatMemList);

		return chatMemList;
	}

	@ResponseBody
	@PostMapping("/join")
	public VChatRoom join(@RequestParam("tdmtngNo") int tdmtngNo, HttpServletRequest request, Model model) {

		log.info("detail -> tdmtngNo from join : {}", tdmtngNo);

		VChatRoom joinRoom = this.todayMeetingService.join(tdmtngNo, userId(request));

		List<TdmtngPrtcpntDto> chatMemList = this.todayMeetingService.chatMemList(tdmtngNo);

		for (TdmtngPrtcpntDto test : chatMemList) {

			if (joinRoom.getUserId().equals(test.getUserId())) {

				if (test.getMberProflPhoto() != null) {

					joinRoom.setProflPhoto(test.getMberProflPhoto());

				} else {

					joinRoom.setProflPhoto(test.getProProflPhoto());
				}

			}

		}

		for (UsersDto userImg : joinRoom.getUserInfo()) {

			for (TdmtngPrtcpntDto bb : chatMemList) {

				if (userImg.getUserId().equals(bb.getUserId())) {

					if (bb.getMberProflPhoto() != null) {

						userImg.setProflPhoto(bb.getMberProflPhoto());
						break;
					}
					if (bb.getProProflPhoto() != null) {

						userImg.setProflPhoto(bb.getProProflPhoto());
						break;
					}
				}

			}
		}

		model.addAttribute("joinRoom", joinRoom);
		log.info("detail After -> joinRoom : {}", joinRoom);
		return joinRoom;
	}

	@ResponseBody
	@PostMapping("/myList")
	public ResponseEntity<List<VChatRoom>> myList(HttpServletRequest request) {

		List<VChatRoom> myList = this.todayMeetingService.myList(userId(request));

		log.info("내방 리스트 : {}", myList);

		return ResponseEntity.ok().body(myList);
	}

	@ResponseBody
	@PostMapping("/msgList")
	public Map<String, Object> roomMsg(@RequestParam("tdmtngNo") int tdmtngNo, HttpServletRequest request) {

		Map<String, Object> roomInfo = new HashMap<>();

		VChatRoom joinRoom = this.todayMeetingService.join(tdmtngNo, userId(request));

		List<TdmtngChSpMshgDto> msgList = this.messageService.roomMsgList(tdmtngNo);

		List<TdmtngPrtcpntDto> chatMemList = this.todayMeetingService.chatMemList(tdmtngNo);

		for (TdmtngChSpMshgDto aa : msgList) {

			for (TdmtngPrtcpntDto bb : chatMemList) {

				if (aa.getUserId().equals(bb.getUserId())) {

					if (bb.getMberProflPhoto() != null) {
						aa.setProflPhoto(bb.getMberProflPhoto());
						break;
					}
					if (bb.getProProflPhoto() != null) {
						aa.setProflPhoto(bb.getProProflPhoto());
						break;
					}
				}
			}

		}
		roomInfo.put("msgList", msgList);
		roomInfo.put("joinRoom", joinRoom);

		log.info("해당 방 : {}", joinRoom);
		log.info("메세지 리스트: {}", msgList);
		return roomInfo;
	}

}
