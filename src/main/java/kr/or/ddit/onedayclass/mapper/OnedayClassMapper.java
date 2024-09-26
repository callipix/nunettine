package kr.or.ddit.onedayclass.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.BcityDto;

public interface OnedayClassMapper {

	List<VOndyclProUsersDto> vOndyclProUsersDtoList();

	int countOndycl();

	String getCodeNm(String codeCd);

	List<VOndyclProUsersDto> searchClass(Map<String, Object> searchMap);

	List<SpcltyRealmDto> category();

	List<BcityDto> getBcity();

	List<BrtcDto> brtcSelect(String bcityCode);

	VOndyclProUsersDto detail(String ondyclNo);

	int getAttachNo();

	int addSprviseAtchmnfl(SprviseAtchmnflDto sprviseAtchmnflDto);

	int createOndycl(Map<String, Object> map);

	List<SprviseAtchmnflDto> fileList(String ondyclNo);

	int getInsertClNum();

	int deleteClass(String classNO);

	int thisAttachNo(int ondyclNo);

	int updateOndycl(Map<String, Object> map);

	int updateOndyclSchdul(Map<String, Object> map);

	int updateSprviseAtchmnfl(SprviseAtchmnflDto sprviseAtchmnflDto);

	int buyClass(Map<String, Object> map);

	int getResveNo();

	int plusndyclResvpa(Map<String, Object> map);

	List<VOndyclProUsersDto> memberOndyclList(Map<String, Object> map);

	int countMberMyClass(Map<String, Object> map);

	List<VOndyclProUsersDto> proMyClassList(Map<String, Object> map);

	int countProMyClass(Map<String, Object> map);

	int resveCheck(Map<String, Object> mberOndyclMap);

	int mberClassCancel(Map<String, Object> map);

	String mberReviewTitle(int ondyclNo);

	int createReview(OndyclReviewDto ondyclReviewDto);

	List<ReviewMberDto> reviewList(int ondyclNo);

	List<BundleOndyclDto> mberShoppingCart(Map<String, Object> map);

	int countShoppingCart(Map<String, Object> map);

	int putShoppingCart(ShopngBundleDto shopngBundleDto);

	String getUserNcnm(String userId);

	int classBundleCk(Map<String, Object> mberOndyclMap);

	VOndyclSchdulDto priceCk(int ondyclNo);

	int delBundle(Map<String, Object> map);

	int getTotalPrice(Map<String, Object> map);

	double getTotalStar(Map<String, Object> map);

	int getTotalUser(Map<String, Object> map);

	int getMonthTotalUser(Map<String, Object> map);

	int getMonthTotalPrice(Map<String, Object> map);

	List<UserNcnmMberPhotoDto> getBuyer(String ondyclNo);

	List<OndyclDto> getOndyclRank();

	//원데이클래스 첨부파일 삭제
	int deleteSprviseAtchmnfl(int ondyclNo);

	String getPeopleCheck(int ondyclNo);

}
