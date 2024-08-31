package kr.or.ddit.onedayclass.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.*;
import kr.or.ddit.dto.BcityDto;

public interface OnedayClassMapper {

	public List<VOndyclProUsersDto> vOndyclProUsersDtoList();

	public int countOndycl();

	public String getCodeNm(String codeCd);

	public List<VOndyclProUsersDto> searchClass(Map<String, Object> searchMap);

	public List<SpcltyRealmDto> category();

	public List<BcityDto> getBcity();

	public List<BrtcDto> brtcSelect(String bcityCode);

	public VOndyclProUsersDto detail(String ondyclNo);

	public int getAttachNo();

	public int addSprviseAtchmnfl(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int createOndycl(Map<String, Object> map);

	public List<SprviseAtchmnflDto> fileList(String ondyclNo);

	public int getInsertClNum();

	public int deleteClass(String classNO);

	public int thisAttachNo(int ondyclNo);

	public int updateOndycl(Map<String, Object> map);

	public int updateOndyclSchdul(Map<String, Object> map);

	public int updateSprviseAtchmnfl(SprviseAtchmnflDto sprviseAtchmnflDto);

	public int buyClass(Map<String, Object> map);

	public int getResveNo();

	public int plusndyclResvpa(Map<String, Object> map);

	public List<VOndyclProUsersDto> memberOndyclList(Map<String, Object> map);

	public int countMberMyClass(Map<String, Object> map);
	
	public List<VOndyclProUsersDto> proMyClassList(Map<String, Object> map);

	public int countProMyClass(Map<String, Object> map);

	public int resveCheck(Map<String, Object> mberOndyclMap);

	public int mberClassCancel(Map<String, Object> map);

	public String mberReviewTitle(int ondyclNo);

	public int createReview(OndyclReviewDto ondyclReviewDto);

	public List<ReviewMberDto> reviewList(int ondyclNo);

	public List<BundleOndyclDto> mberShoppingCart(Map<String, Object> map);

	public int countShoppingCart(Map<String, Object> map);

	public int putShoppingCart(ShopngBundleDto shopngBundleDto);

	public String getUserNcnm(String userId);

	public int classBundleCk(Map<String, Object> mberOndyclMap);

	public VOndyclSchdulDto priceCk(int ondyclNo);

	public int delBundle(Map<String, Object> map);

	public int getTotalPrice(Map<String, Object> map);

	public double getTotalStar(Map<String, Object> map);

	public int getTotalUser(Map<String, Object> map);

	public int getMonthTotalUser(Map<String, Object> map);

	public int getMonthTotalPrice(Map<String, Object> map);

	public List<UserNcnmMberPhotoDto> getBuyer(String ondyclNo);

	public List<OndyclDto> getOndyclRank();

	//원데이클래스 첨부파일 삭제
	public int deleteSprviseAtchmnfl(int ondyclNo);

	public String getPeopleCheck(int ondyclNo);


}
