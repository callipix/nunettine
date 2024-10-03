package kr.or.ddit.board.prostory.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditorImgUpload {

	private final String uploadDirectCk = "C:\\Users\\Home\\Desktop\\ChatTest\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\finalProject\\resources\\ckFolder";

	//ckeditor 이미지 업로드 , data 전송 -> ResponseBody 어노테이션 추가(객체화)
	@ResponseBody
	@Transactional
	@PostMapping("/upload/uploadCk")
	public Map<String, Object> uploads(MultipartHttpServletRequest request) throws IllegalStateException, IOException {

		MultipartFile uploadFile = request.getFile("upload");
		log.info("uploads -> uploadFile : {} ", uploadFile);

		String originalFileName = uploadFile.getOriginalFilename();
		log.info("uploads -> originalFileName : {} ", originalFileName);

		String ext = originalFileName.substring(originalFileName.indexOf("."));
		log.info("ext : {} ", ext);    // .jpg

		String newFileName = UUID.randomUUID() + ext;
		log.info("newFileName : {}", newFileName);

		String url = request.getRequestURL().toString();
		log.info("uploads -> url 업로드 전 : {} ", url);

		// http://localhost/
		// http://192.168.93.73/

		url = url.substring(0, url.indexOf("/", 7));
		log.info("uploads -> url 업로드 후: {}", url);

		String savePath = this.uploadDirectCk + "\\" + newFileName;
		log.info("uploads -> savePath(임시 경로) : {} ", savePath);

		String uploadPath = getFolder().replace("\\", "/") + "/" + newFileName;
		log.info("uploadPath -> uploadPath(실제 웹 경로) {}", uploadPath);

		File file = new File(uploadPath);

		uploadFile.transferTo(file);

		Map<String, Object> map = new HashMap<>();
		map.put("uploaded", true);
		map.put("url", url + "/images/" + uploadPath);
		log.info("uploads -> map : {} ", map);

		return map;

	}

	public String getFolder() {

		LocalDate now = LocalDate.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String fmtNow = now.format(dtf);

		return fmtNow.replace("-", File.separator);

	}

}
