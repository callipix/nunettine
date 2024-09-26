package kr.or.ddit.admin.notice.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.admin.notice.dto.NoticeDto;
import kr.or.ddit.dto.SprviseAtchmnflDto;

public interface NoticeMapper {

	List<NoticeDto> getAllNoticeList();

	NoticeDto detail(int noticeNo);

	int update(NoticeDto noticeDto);

	int delete(NoticeDto noticeDto);

	int createPost(NoticeDto noticeDto);

	int getTotal(Map<String, Object> map);

	List<NoticeDto> list(Map<String, Object> map);

	int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	int increaseViewCount(int noticeNo);

	NoticeDto sprviseAtchmnflDto(int noticeNo);

}
