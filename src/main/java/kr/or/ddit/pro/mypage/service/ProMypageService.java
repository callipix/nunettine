package kr.or.ddit.pro.mypage.service;

import java.util.Map;

public interface ProMypageService {

	int updProMbtlnum(Map<String, Object> map);

	int updPW(Map<String, Object> map);

	int updNcnm(Map<String, Object> map);

	int updPhoto(Map<String, Object> map);

	int photoDelete(String userId);

	int updEmail(Map<String, Object> map);

	int updNm(Map<String, Object> map);

	int updAdres(Map<String, Object> map);
}
