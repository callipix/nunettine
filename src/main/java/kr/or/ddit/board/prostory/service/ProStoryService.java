package kr.or.ddit.board.prostory.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.board.prostory.dto.GoodPointDto;

public interface ProStoryService {

    // 게시글 리스트
    List<ProStoryBbscttDto> storyList();

    List<ProStoryBbscttDto> getPage(Map<String, Object> searchParam);

    int getTotal();

    // 게시글 쓰기
    int insert(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi);

    // 게시글 선택
    ProStoryBbscttDto getStory(int storyNo);

    // 게시글 수정
    int updateStory(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi);

    // 게시글 삭제
    int deleteStory(String userId, int storyNo);

    // 게시글 조회수
    int getStoryCount(int storyNo);

    // 게시글 좋아요
    ProStoryBbscttDto updateGood(GoodPointDto goodPointDto);

    // 게시글 좋아요 취소
    ProStoryBbscttDto goodRemove(GoodPointDto goodPointDto);

    // 좋아요 했는지 체크
    int getGoodCheck(GoodPointDto goodPointDto);

    List<ProStoryBbscttDto> getPageTest(Map<String, Object> map);

    // 메인 페이지 추천수 정렬
    List<ProStoryBbscttDto> getWeekRecommend();

}
