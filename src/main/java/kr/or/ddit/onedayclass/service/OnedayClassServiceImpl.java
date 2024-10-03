package kr.or.ddit.onedayclass.service;

import static kr.or.ddit.util.ImageCheck.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.ddit.dto.*;
import kr.or.ddit.util.ImageCheck;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.onedayclass.mapper.OnedayClassMapper;
import kr.or.ddit.dto.BcityDto;
import lombok.extern.slf4j.Slf4j;

import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnedayClassServiceImpl implements OnedayClassService {

	private final OnedayClassMapper onedayClassMapper;
	private final String uploadFolder;

	@Override
	public List<VOndyclProUsersDto> vOndyclProUsersDtoList() {
		return this.onedayClassMapper.vOndyclProUsersDtoList();
	}

	@Override
	public int countOndycl() {
		return this.onedayClassMapper.countOndycl();
	}

	@Override
	public String getCodeNm(String codeCd) {
		return this.onedayClassMapper.getCodeNm(codeCd);
	}

	@Override
	public List<VOndyclProUsersDto> searchClass(Map<String, Object> searchMap) {
		return this.onedayClassMapper.searchClass(searchMap);
	}

	@Override
	public List<SpcltyRealmDto> category() {
		return this.onedayClassMapper.category();
	}

	@Override
	public List<BcityDto> getBcity() {
		return this.onedayClassMapper.getBcity();
	}

	@Override
	public List<BrtcDto> brtcSelect(String bcityCode) {
		return this.onedayClassMapper.brtcSelect(bcityCode);
	}

	@Override
	public VOndyclProUsersDto detail(String ondyclNo) {
		return this.onedayClassMapper.detail(ondyclNo);
	}

	@Override
	public int getAttachNo() {
		return this.onedayClassMapper.getAttachNo();
	}

	@Override
	public int addSprviseAtchmnfl(SprviseAtchmnflDto sprviseAtchmnflDto) {
		return this.onedayClassMapper.addSprviseAtchmnfl(sprviseAtchmnflDto);
	}

	@Override
	public int createOndycl(Map<String, Object> map) {
		return this.onedayClassMapper.createOndycl(map);
	}

	@Override
	public List<SprviseAtchmnflDto> fileList(String ondyclNo) {
		return this.onedayClassMapper.fileList(ondyclNo);
	}

	@Override
	public int getInsertClNum() {
		return this.onedayClassMapper.getInsertClNum();
	}

	@Override
	public int deleteClass(String classNO) {
		return this.onedayClassMapper.deleteClass(classNO);
	}

	@Transactional
	@Override
	public int updateOndycl(VOndyclSchdulDto vOndyclSchdulDto) {
		//		log.info("업뎃 : {}", vOndyclSchdulDto);
		Map<String, Object> map = new HashMap<String, Object>();
		int ondyclNo = vOndyclSchdulDto.getOndyclNo();
		SprviseAtchmnflDto sprviseAtchmnflDto = new SprviseAtchmnflDto();
		int sprviseAtchmnflNo = this.onedayClassMapper.getAttachNo();
		//		int sprviseAtchmnflNo = this.onedayClassMapper.thisAttachNo(ondyclNo);
		int result = 0;
		String proId = vOndyclSchdulDto.getProId();
		String ondyclSchdulDe = vOndyclSchdulDto.getOndyclSchdulDe();
		ondyclSchdulDe = ondyclSchdulDe.replaceAll("-", "");

		map.put("ondyclNo", ondyclNo);
		map.put("ondyclNm", vOndyclSchdulDto.getOndyclNm());
		map.put("ondyclCn", vOndyclSchdulDto.getOndyclCn());
		map.put("ondyclPc", vOndyclSchdulDto.getOndyclPc());
		map.put("ondyclPsncpa", vOndyclSchdulDto.getOndyclPsncpa());
		map.put("proId", proId);
		map.put("ondyclSchdulDe", ondyclSchdulDe);
		map.put("ondyclSchdulBeginTime", vOndyclSchdulDto.getOndyclSchdulBeginTime());
		map.put("ondyclSchdulEndTime", vOndyclSchdulDto.getOndyclSchdulEndTime());
		map.put("ondyclAdres", vOndyclSchdulDto.getOndyclAdres());
		map.put("ondyclDetailAdres", vOndyclSchdulDto.getOndyclDetailAdres());
		map.put("ondyclZip", vOndyclSchdulDto.getOndyclZip());
		map.put("sprviseAtchmnflNo", sprviseAtchmnflNo);

		MultipartFile uploadFile = vOndyclSchdulDto.getUploadProfile();

		//		log.info("uploadFile.getOriginalFilename().length() : {}", uploadFile.getOriginalFilename().length());

		//썸네일 수정
		if (uploadFile.getOriginalFilename().length() > 0) {
			//			log.info("썸네일 메소드 시작1");
			MultipartFile multipartFile = vOndyclSchdulDto.getUploadProfile();

			File uploadPath = new File(uploadFolder, getFolder());
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			String uploadFileName = multipartFile.getOriginalFilename();

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			File saveFile = new File(uploadPath, uploadFileName);
			//			 log.info("프로필사진 이름  : {}", saveFile);
			try {
				multipartFile.transferTo(saveFile);

				if (ImageCheck.checkImageType(saveFile)) {//이미지라면
					//설계
					FileOutputStream thumbnail = new FileOutputStream(
						new File(uploadPath, "s_" + uploadFileName)
					);
					//썸네일 생성
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),
						thumbnail, 50, 50);
					thumbnail.close();
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			String url = "/images/" + getFolder().replace("\\", "/") + "/" + uploadFileName;

			//			 log.info("썸네일 url : {}", url);
			map.put("ondyclThumbPhoto", url);
		} else {
			//			log.info("썸네일 메소드 시작2");
			map.put("ondyclThumbPhoto", null);
		}//썸네일 사진 등록 끝

		MultipartFile[] multipartFileArr = vOndyclSchdulDto.getUploadFile();
		//		log.info("multipartFileArr : {}", multipartFileArr);
		//첨부파일(이미지들) 수정
		if (multipartFileArr[0].getOriginalFilename().length() > 0) {
			//			log.info("첨부파일 메소드 시작");
			String originFileName = ""; //원본파일명
			String newFileName = "";
			String mimeType = ""; //파일 형식
			long size = 0; //파일 사이즈
			int seq = 1;

			//기존 이미지 삭제(ondyclNo : 11)
			int result2 = this.onedayClassMapper.deleteSprviseAtchmnfl(ondyclNo);
			//			log.info("result2 : {}", result2);

			for (MultipartFile uploadFiles : vOndyclSchdulDto.getUploadFile()) {
				originFileName = uploadFiles.getOriginalFilename();
				size = uploadFiles.getSize();
				mimeType = uploadFiles.getContentType();

				UUID uuid = UUID.randomUUID();
				newFileName = uuid.toString() + "_" + originFileName;
				//				log.info("첨부파일 정보 : {}", originFileName+"/"+size+"/"+mimeType+"/"+newFileName);
				File saveFiles = new File(uploadFolder + "\\" + getFolder(), newFileName);
				String url = "/images/" + getFolder().replace("\\", "/") + "/" + newFileName;
				try {
					uploadFiles.transferTo(saveFiles);
					sprviseAtchmnflDto.setSprviseAtchmnflNo(sprviseAtchmnflNo);
					sprviseAtchmnflDto.setAtchmnflCours(url);
					sprviseAtchmnflDto.setAtchmnflNm(originFileName);
					sprviseAtchmnflDto.setStoreAtchmnflNm(newFileName);
					sprviseAtchmnflDto.setAtchmnflTy(mimeType);
					sprviseAtchmnflDto.setAtchmnflNo(seq++);
					sprviseAtchmnflDto.setUserId(proId);
					//					log.info("sprviseAtchmnflDto : {}", sprviseAtchmnflDto);

					result += this.onedayClassMapper.addSprviseAtchmnfl(sprviseAtchmnflDto);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}//end for
		} else {
			int result2 = this.onedayClassMapper.deleteSprviseAtchmnfl(ondyclNo);
		}

		result += this.onedayClassMapper.updateOndycl(map);
		result += this.onedayClassMapper.updateOndyclSchdul(map);

		return result;
	}


	@Transactional
	@Override
	public int buyClass(Map<String, Object> map) {
		int resveNo = this.onedayClassMapper.getResveNo();

		map.put("resveNo", resveNo);
		int result = this.onedayClassMapper.buyClass(map); //구매 구매상세 결제 추가
		result += this.onedayClassMapper.plusndyclResvpa(map); //클래스 참여인원 +1

		return result;
	}

	@Override
	public List<VOndyclProUsersDto> memberOndyclList(Map<String, Object> map) {
		List<VOndyclProUsersDto> vOndyclProUsersDtoList = this.onedayClassMapper.memberOndyclList(map);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String todayStr = sdf.format(date);
		try {
			Date today = sdf.parse(todayStr);

			for (int i = 0; i < vOndyclProUsersDtoList.size(); i++) {
				Date ondyclSchdulDe = sdf.parse(vOndyclProUsersDtoList.get(i).getOndyclSchdulDe());

				boolean dayCheck = ondyclSchdulDe.before(today);
				vOndyclProUsersDtoList.get(i).setDayCheck(dayCheck);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return vOndyclProUsersDtoList;
	}

	@Override
	public int countMberMyClass(Map<String, Object> map) {
		return this.onedayClassMapper.countMberMyClass(map);
	}

	@Override
	public List<VOndyclProUsersDto> proMyClassList(Map<String, Object> map) {

		//		log.info("impl map " + map);
		List<VOndyclProUsersDto> vOndyclProUsersDtoList = this.onedayClassMapper.proMyClassList(map);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String todayStr = sdf.format(date);
		try {
			Date today = sdf.parse(todayStr);

			for (int i = 0; i < vOndyclProUsersDtoList.size(); i++) {
				Date ondyclSchdulDe = sdf.parse(vOndyclProUsersDtoList.get(i).getOndyclSchdulDe());

				boolean dayCheck = ondyclSchdulDe.before(today);
				vOndyclProUsersDtoList.get(i).setDayCheck(dayCheck);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return vOndyclProUsersDtoList;
	}

	@Override
	public int countProMyClass(Map<String, Object> map) {
		return this.onedayClassMapper.countProMyClass(map);
	}

	@Override
	public int resveCheck(Map<String, Object> mberOndyclMap) {
		return this.onedayClassMapper.resveCheck(mberOndyclMap);
	}

	@Override
	public int mberClassCancel(Map<String, Object> map) {
		int result = this.onedayClassMapper.mberClassCancel(map);
		return result;
	}

	@Override
	public String mberReviewTitle(int ondyclNo) {
		return this.onedayClassMapper.mberReviewTitle(ondyclNo);
	}

	@Override
	public int createReview(OndyclReviewDto ondyclReviewDto) {
		int result = this.onedayClassMapper.createReview(ondyclReviewDto);

		return result;
	}

	@Override
	public List<ReviewMberDto> reviewList(int ondyclNo) {
		List<ReviewMberDto> reviewMberDtoList = this.onedayClassMapper.reviewList(ondyclNo);

		for (ReviewMberDto reMbDto : reviewMberDtoList) {
			String wrDate = reMbDto.getOndyclReWrDt();

			wrDate.replace("-", ".");
			wrDate = "2" + wrDate.substring(1);

			reMbDto.setOndyclReWrDt(wrDate);
		}

		return reviewMberDtoList;
	}

	@Override
	@Transactional
	public List<BundleOndyclDto> mberShoppingCart(Map<String, Object> map) {
		List<BundleOndyclDto> shopngBundleDtoList = this.onedayClassMapper.mberShoppingCart(map);
		String userId;
		String userNcnm;

		for (BundleOndyclDto bundleOndyclDto : shopngBundleDtoList) {
			userId = bundleOndyclDto.getProId();

			userNcnm = this.onedayClassMapper.getUserNcnm(userId);
			bundleOndyclDto.setUserNcnm(userNcnm);
		}

		return shopngBundleDtoList;
	}

	@Override
	public int countShoppingCart(Map<String, Object> map) {
		return this.onedayClassMapper.countShoppingCart(map);
	}

	@Override
	public int putShoppingCart(ShopngBundleDto shopngBundleDto) {
		return this.onedayClassMapper.putShoppingCart(shopngBundleDto);
	}

	@Override
	public int classBundleCk(Map<String, Object> mberOndyclMap) {
		return this.onedayClassMapper.classBundleCk(mberOndyclMap);
	}

	@Override
	public VOndyclSchdulDto priceCk(int ondyclNo) {
		return this.onedayClassMapper.priceCk(ondyclNo);
	}

	@Override
	public int delBundle(Map<String, Object> map) {
		return this.onedayClassMapper.delBundle(map);
	}

	@Override
	public int buyBundle(Map<String, Object> map) {
		log.info("넘어온 맵 : {}", map);
		int result = 0;
		VOndyclSchdulDto vOndyclSchdulDto;

		List<Integer> arrClassNo = (List<Integer>)map.get("checkList");
		log.info("배열 : {}", arrClassNo + " / " + arrClassNo.size());

		Map<String, Object> buyMap = new HashMap<String, Object>();
		buyMap.put("mberId", map.get("mberId"));
		buyMap.put("setleNo", map.get("setleNo"));

		for (int i = 0; i < arrClassNo.size(); i++) {
			int resveNo = this.onedayClassMapper.getResveNo();
			Object item = arrClassNo.get(i);
			int ondyclNo;
			if (item instanceof String) {
				ondyclNo = Integer.parseInt((String)item);
			} else if (item instanceof Integer) {
				ondyclNo = (Integer)item;
			} else {
				// 예외 처리 또는 로그를 남김
				continue; // 또는 적절한 오류 처리
			}
			log.info("각 넘버 : {}", arrClassNo.get(i));
			vOndyclSchdulDto = this.onedayClassMapper.priceCk(ondyclNo);

			buyMap.put("ondyclNo", ondyclNo);
			buyMap.put("resveNo", resveNo);
			buyMap.put("resveTpprice", vOndyclSchdulDto.getOndyclPc());
			buyMap.put("ondyclSchdulNo", vOndyclSchdulDto.getOndyclSchdulNo());

			log.info("결제처리 buyMap : {}", buyMap);
			result += this.onedayClassMapper.buyClass(buyMap);
			result += this.onedayClassMapper.plusndyclResvpa(buyMap);

			//장바구니에서 삭제
			result += this.onedayClassMapper.delBundle(buyMap);
		}

		return result;
	}

	@Override
	public int getTotalPrice(Map<String, Object> map) {
		return this.onedayClassMapper.getTotalPrice(map);
	}

	@Override
	public double getTotalStar(Map<String, Object> map) {
		return this.onedayClassMapper.getTotalStar(map);
	}

	@Override
	public int getTotalUser(Map<String, Object> map) {
		return this.onedayClassMapper.getTotalUser(map);
	}

	@Override
	public int getMonthTotalUser(Map<String, Object> map) {
		return this.onedayClassMapper.getMonthTotalUser(map);
	}

	@Override
	public int getMonthTotalPrice(Map<String, Object> map) {
		return this.onedayClassMapper.getMonthTotalPrice(map);
	}

	@Override
	public List<UserNcnmMberPhotoDto> getBuyer(String ondyclNo) {
		return this.onedayClassMapper.getBuyer(ondyclNo);
	}

	@Override
	public List<OndyclDto> getOndyclRank() {
		List<OndyclDto> ondyclDtoList = this.onedayClassMapper.getOndyclRank();

		return ondyclDtoList;
	}

	@Override
	public String getPeopleCheck(int ondyclNo) {
		return this.onedayClassMapper.getPeopleCheck(ondyclNo);
	}
}