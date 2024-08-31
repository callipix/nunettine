package kr.or.ddit.board.prostory.controller;

import kr.or.ddit.board.prostory.service.ProStoryService;
import kr.or.ddit.board.prostory.dto.ArticlePage5;
import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@ComponentScan(basePackages={"kr.or.ddit.*"})
@NoArgsConstructor
public class ProStoryControllerTest {

    @Autowired
    ProStoryController proStoryController;

    @Autowired
    ProStoryService proStoryService;

    @Test
    public void list() {

        int total = this.proStoryService.getTotal();
        Map<String , Object> searchParam = new HashMap<String, Object>();

        String currentPage = "1";
        String type = "제목";
        String keyword = "반려견";

        searchParam.put("currentPage", currentPage);
        searchParam.put("type"          , type);
        searchParam.put("keyword"       , keyword);

        List<ProStoryBbscttDto> pagingList = this.proStoryService.getPage(searchParam);
        ArticlePage5<ProStoryBbscttDto> data = new ArticlePage5<ProStoryBbscttDto>(total, Integer.parseInt(currentPage), 8 , pagingList, keyword , type);
        System.out.println("pagingList = " + pagingList);
        System.out.println();
        System.out.println("data = " + data);
    }

    @Test
    public void getContent() {
    }

    @Test
    public void getWeekRecommend() {
    }

    @Test
    public void create() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void idCheck() {
    }

    @Test
    public void update() {
    }

    @Test
    public void updateStory() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getProStory() {
    }

    @Test
    public void getGoodCheck() {
    }

    @Test
    public void goodUp() {
    }

    @Test
    public void goodCancle() {
    }
}