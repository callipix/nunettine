package kr.or.ddit.todaymeeting.controller;

import kr.or.ddit.chatting.service.MessageService;
import kr.or.ddit.todaymeeting.VChatRoom;
import kr.or.ddit.todaymeeting.service.TodayMeetingService;
import kr.or.ddit.vo.TdmtngChSpMshgVO;
import kr.or.ddit.vo.TdmtngPrtcpntVO;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@NoArgsConstructor
public class TodayMeetingControllerTest {

    @Autowired
    TodayMeetingService todayMeetingSerive;

    @Autowired
    MessageService messageService;

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void selectMyChat() {
    }

    @Test
    public void joinChat() {
    }

    @Test
    public void chatMemList() {
    }

    @Test
    public void join() {
    }

    @Test
    public void myList() {
        String userId = "testUser";
        List<VChatRoom> myList = this.todayMeetingSerive.myList(userId);

        for(VChatRoom vChatRoom : myList) {
            System.out.println(vChatRoom);
        }
    }

    @Test
    public void roomMsg() {
        int tdmtngNo = 174;
        String userId = "testUser";
        Map<String, Object> roomInfo = new HashMap<>();
        VChatRoom joinRoom = this.todayMeetingSerive.join(tdmtngNo , userId);

        List<TdmtngChSpMshgVO> msgList = this.messageService.roomMsgList(tdmtngNo);

        roomInfo.put("msgList", msgList);
        roomInfo.put("joinRoom", joinRoom);

        for(TdmtngChSpMshgVO vo : msgList) {
            System.out.println(vo);
        }

    }
}