package kr.or.ddit.board.prostory.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.prostory.dto.GoodPointDto;

public interface ProStoryMapper {

	// 게시글 리스트
	public List<ProStoryBbscttDto> storyList();

	// 게시글 선택
	public ProStoryBbscttDto getStory(int storyNo);

	// 게시글쓰기
	public int insert(ProStoryBbscttDto proStoryBbscttDto);
	
	// 게시글 수정
	public int updateStory(ProStoryBbscttDto proStoryBbscttDto);
	
	// 게시글 삭제
	public int deleteStory(@Param("proId") String userId , @Param("proStoryBbscttNo") int storyNo);
	
	// 게시글 조회수
	public int getStoryCount(@Param("proStoryBbscttNo") int storyNo);
	
	// 게시글 좋아요 수
	public ProStoryBbscttDto goodCount(ProStoryBbscttDto psbcttVO);

	// 게시글 좋아요
	public int goodSave(GoodPointDto goodPointDto);
	// 좋아요 -> 게시판에 반영
	public int goodUp(ProStoryBbscttDto psbcttVO);
	
	// 게시글 좋아요 취소
	public int goodRemove(GoodPointDto goodPointDto);
	// 좋아요 취소 -> 게시판에 반영
	public int goodDown(ProStoryBbscttDto psbcttVO);

	// 게시글 전체수
	public int getTotal();
	
	// 페이징 -> 더보기
	public List<ProStoryBbscttDto> getPage(Map<String, Object> searchParam);

	// 좋아요 했는지 체크
	public int getGoodCheck(GoodPointDto goodPointDto);

	// 페이징 일반 페이징으로
	public List<ProStoryBbscttDto> getPageTest(Map<String, Object> map);

	// 일주일내 추천수 상위 4개 게시물
	public List<ProStoryBbscttDto> getWeekRecommend();
	
}