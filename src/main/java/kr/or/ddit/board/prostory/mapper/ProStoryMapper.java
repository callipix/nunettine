package kr.or.ddit.board.prostory.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.prostory.dto.GoodPointDto;
@Mapper
public interface ProStoryMapper {

    // 게시글 리스트
    List<ProStoryBbscttDto> storyList();

    // 게시글 선택
    ProStoryBbscttDto getStory(int storyNo);

    // 게시글 쓰기
    int insert(ProStoryBbscttDto proStoryBbscttDto);

    // 게시글 수정
    int updateStory(ProStoryBbscttDto proStoryBbscttDto);

    // 게시글 삭제
    int deleteStory(@Param("proId") String userId, @Param("proStoryBbscttNo") int storyNo);

    // 게시글 조회수
    int getStoryCount(@Param("proStoryBbscttNo") int storyNo);

    // 게시글 좋아요 수
    ProStoryBbscttDto goodCount(ProStoryBbscttDto psbcttVO);

    // 게시글 좋아요
    int goodSave(GoodPointDto goodPointDto);

    // 좋아요 -> 게시판에 반영
    int goodUp(ProStoryBbscttDto psbcttVO);

    // 게시글 좋아요 취소
    int goodRemove(GoodPointDto goodPointDto);

    // 좋아요 취소 -> 게시판에 반영
    int goodDown(ProStoryBbscttDto psbcttVO);

    // 게시글 전체수
    int getTotal();

    // 페이징 -> 더보기
    List<ProStoryBbscttDto> getPage(Map<String, Object> searchParam);

    // 좋아요 했는지 체크
    int getGoodCheck(GoodPointDto goodPointDto);

    // 페이징 일반 페이징
    List<ProStoryBbscttDto> getPageTest(Map<String, Object> map);

    // 일주일내 추천수 상위 4개 게시물
    List<ProStoryBbscttDto> getWeekRecommend();

}