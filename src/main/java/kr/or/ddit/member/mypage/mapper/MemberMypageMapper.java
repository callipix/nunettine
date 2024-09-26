package kr.or.ddit.member.mypage.mapper;

import java.util.Map;

public interface MemberMypageMapper {

	int memberDelete(String userId);

	int memberDelete2(String userId);

	int memberDelete3(String userId);

	int memberDelete4(String userId);

	int photoDelete(String userId);

	int updPhoto(Map<String, Object> map);

	int updAdres(Map<String, Object> map);

	int updMberMbtlnum(Map<String, Object> map);

	int updPw(Map<String, Object> map);

	int updNcnm(Map<String, Object> map);

	int updEmail(Map<String, Object> map);

	int updNm(Map<String, Object> map);

}
