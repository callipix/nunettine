package kr.or.ddit.admin.notice.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.admin.notice.dto.NoticeDto;
import kr.or.ddit.vo.SprviseAtchmnflDto;

public interface NoticeMapper {

	public List<NoticeDto> getAllNoticeList();

	public NoticeDto detail(int noticeNo);

	public int update(NoticeDto noticeDto);

	public int delete(NoticeDto noticeDto);

	public int createPost(NoticeDto noticeDto);

	public int getTotal(Map<String, Object> map);

	public List<NoticeDto> list(Map<String, Object> map);

	public int insertSprvise(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int increaseViewCount(int noticeNo);

	public NoticeDto sprviseAtchmnflVO(int noticeNo);

	

}
