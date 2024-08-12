package kr.or.ddit.util.echo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.or.ddit.alarm.vo.NtchDetailVO;
import kr.or.ddit.chatting.service.MessageService;
import kr.or.ddit.chatting.vo.MessageVO;
import kr.or.ddit.todaymeeting.service.TodayMeetingService;
import kr.or.ddit.vo.TdmtngChSpMshgVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	enum MessageType {
	
		ENTER , INVITE , MESSAGE;
	}
	
	private final MessageService messageService;
	private final SimpMessagingTemplate template;
	
	@ResponseBody
	@PostMapping("/chat/messageList")
	public ModelMap messageListTest(@RequestParam("roomNo") int roomNo) throws JsonProcessingException {

		ModelMap modelMap = new ModelMap();

		List<MessageVO> messageList = this.messageService.messageList(roomNo);

		modelMap.addAttribute("messageList", messageList);

	    return modelMap;
	}
	
	@MessageMapping("/chat/enterK")
	public void enter(TdmtngChSpMshgVO message) {
		// 수신 영역
		message.setMessageType(MessageType.ENTER.toString());
		
		String str = "<div id='msgArea'>"+ message.getUserNcnm()+"님이 접속하였습니다 </div>";
		
		message.setTdmtngChSpMsgCn(str);
		
		template.convertAndSend("/sub/chat/room/" + message.getTdmtngNo(), message);
			
	}
	@Transactional
	@MessageMapping("/chat/message") // 메시지 전송영역
	public void message(TdmtngChSpMshgVO message) {
		
		message.setMessageType(MessageType.MESSAGE.toString());
		log.info("메시지 전송 체크 {} Before " + message);
		int result = this.messageService.insert(message);
		
		if(result > 0) {
			template.convertAndSend("/sub/chat/room/" + message.getTdmtngNo() , message);
			log.info("메시지 전송 성공{} Insert After result : " + result + message);
		}
		else
			log.info("메시지 전송 실패 " + result);
	}
	
///////////// 알람 기능 삭제되었음!!
	@MessageMapping("/alarm/get")
	public void alram(NtchDetailVO alarmInfo) {
		// 수신영역
		
		System.out.println("알람 수신 체크");
		
		template.convertAndSend("/sub/alarm/"+ alarmInfo.getUserId() , alarmInfo);

	}
	
	@MessageMapping("/alarm/send")
	public void alramSend(NtchDetailVO alarmInfo) {
		// 전송영역
		
		System.out.println("알람 전송 체크");
		
		template.convertAndSend("/sub/alarm/"+ alarmInfo.getUserId() , alarmInfo);

		System.out.println("알람 내용 : " + alarmInfo);
	}
	
}