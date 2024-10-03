package kr.or.ddit.pro.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProMypageMapper {

	int photoDelete(String userId);

}
