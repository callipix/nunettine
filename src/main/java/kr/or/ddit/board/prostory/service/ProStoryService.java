package kr.or.ddit.board.prostory.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.prostory.dto.ProStoryBbscttDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.board.prostory.dto.GoodPointDto;

public interface ProStoryService {
	
	// 게시글 리스트
	public List<ProStoryBbscttDto> storyList();
	
	public List<ProStoryBbscttDto> getPage(Map<String, Object> searchParam);
	
	public int getTotal();

	// 게시글쓰기
	public int insert(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi);
	
	// 게시글 선택
	public ProStoryBbscttDto getStory(int storyNo);
	
	// 게시글 수정
	public int updateStory(ProStoryBbscttDto proStoryBbscttDto, MultipartHttpServletRequest multi);
	
	// 게시글 삭제
	public int deleteStory(String userId , int storyNo);
	
	// 게시글 조회수
	public int getStoryCount(int storyNo);

	// 게시글 좋아요
	public ProStoryBbscttDto updateGood(GoodPointDto goodPointDto);

	// 게시글 좋아요 취소
	public ProStoryBbscttDto goodRemove(GoodPointDto goodPointDto);

	// 좋아요 했는지 체크
	public int getGoodCheck(GoodPointDto goodPointDto);
	
	public List<ProStoryBbscttDto> getPageTest(Map<String, Object> map);
	
	// 메인페이지 추천수 정렬
	public List<ProStoryBbscttDto> getWeekRecommend();

}
