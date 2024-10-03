package kr.or.ddit.pro.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import kr.or.ddit.util.ImageCheck;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.pro.mypage.service.ProMypageService;
import kr.or.ddit.dto.AdresDto;
import kr.or.ddit.dto.VProUsersDto;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProMypageController {

	private final String uploadFolder;
	private final ProMypageService proMypageService;

	@GetMapping("/proMypage")
	public String proMypage() {
		return "pro/proMypage";
	}

	@GetMapping("/proUpdateCk")
	public String proUpdateCk() {
		return "pro/proUpdateCk";
	}

	@GetMapping("/proPostList")
	public String proPostList() {
		return "pro/proPostList";
	}

	@GetMapping("/proMyClassList")
	public String proMyClassList() {
		return "onedayClass/proMyClassList";
	}

	//회원정보 수정
	@PostMapping("/updating")
	public String updating(VProUsersDto vProUsersDto, AdresDto adresDto, HttpSession session) {
		Map<String, Object> map = (Map<String, Object>)session.getAttribute("proSession");

		String proMbtlnum = vProUsersDto.getProMbtlnum();
		if (proMbtlnum != null && !proMbtlnum.isEmpty()) {
			map.put("proMbtlnum", proMbtlnum);
		}
		String userPassword = vProUsersDto.getUserPassword();
		if (userPassword != null && !userPassword.isEmpty()) {
			map.put("userPassword", userPassword);
		}
		String userNcnm = vProUsersDto.getUserNcnm();
		if (userNcnm != null && !userNcnm.isEmpty()) {
			map.put("userNcnm", userNcnm);
		}
		String email = vProUsersDto.getEmail();
		if (email != null && !email.isEmpty()) {
			map.put("email", email);
		}
		String userNm = vProUsersDto.getUserNm();
		if (userNm != null && !userNm.isEmpty()) {
			map.put("userNm", userNm);
		}
		String zip = adresDto.getZip();
		String adres = adresDto.getAdres();
		String detailAdres = adresDto.getDetailAdres();
		if (detailAdres != null && !detailAdres.isEmpty()) {
			map.put("zip", zip);
			map.put("adres", adres);
			map.put("detailAdres", detailAdres);
		}

		MultipartFile multipartFile = vProUsersDto.getUploadFile();
		if (vProUsersDto.getProProflPhoto() != null && !vProUsersDto.getProProflPhoto().isEmpty()) {
			File uploadPath = new File(uploadFolder, ImageCheck.getFolder());
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			String uploadFileName = multipartFile.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			uploadFileName = uuid + "_" + uploadFileName;
			File saveFile = new File(uploadPath, uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				log.info(e.getMessage());
			}
			String proProflPhoto = "/images/" + ImageCheck.getFolder().replace("\\", "/") + "/" + uploadFileName;

			map.put("proProflPhoto", proProflPhoto);
			map.put("profile", proProflPhoto);
		} else {
			map.put("proProflPhoto", null);
			map.put("profile", null);
		}

		return "pro/proMypage";
	}

	//프로필 사진 삭제
	@ResponseBody
	@PostMapping("/photoDelete")
	public int photoDelete(String userId) {
		int result = this.proMypageService.photoDelete(userId);

		return result;
	}
}
