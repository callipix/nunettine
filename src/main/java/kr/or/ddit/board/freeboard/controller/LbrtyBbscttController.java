package kr.or.ddit.board.freeboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.freeboard.service.LbrtyBbscttService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.dto.SprviseAtchmnfl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/lbrtybbsctt")
@Controller
@RequiredArgsConstructor
public class LbrtyBbscttController {

	private final String uploadFolder;
	private final LbrtyBbscttService lbrtyBbscttService;

	private String userIdCk(HttpServletRequest request) {
		//세션값으로 아이디 가져오기

		Object proSession = request.getSession().getAttribute("proSession");
		Object memSession = request.getSession().getAttribute("memSession");

		if (proSession != null && proSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)proSession).get("userId");
			log.info("proSession : {}", userId);

			return userId != null ? userId.toString() : null;
		}
		if (memSession != null && memSession instanceof HashMap) {
			Object userId = ((HashMap<String, Object>)memSession).get("userId");
			log.info("memSession : {}", userId);

			return userId != null ? userId.toString() : null;

		}

		return null;
	}

	@GetMapping("/detail")
	public String detail(int lbrtyBbscttNo, Model model, HttpServletRequest request) {
		String userId = userIdCk(request);
		model.addAttribute("userId", userId);
		log.info("detail -> userId : {}", userId);

		log.info("detail->lbrtyBbscttNo : {}", lbrtyBbscttNo);
		LbrtyBbscttDto detail = this.lbrtyBbscttService.lbrtyBbscttDetail(lbrtyBbscttNo);
		// List<LbrtyBbscttVO2> list = this.lbrtyBbscttService.lbrtyBbscttList();
		// log.info("list : {}", list);
		log.info("detail : {}", detail);
		log.info("detail -> sprviseAtchmnflNo : {}", detail.getSprviseAtchmnflNo());

		int sprviseAtchmnflNo = detail.getSprviseAtchmnflNo();
		log.info("detail -> sprviseAtchmnflNo : {}", sprviseAtchmnflNo);
		List<SprviseAtchmnfl> sprList = this.lbrtyBbscttService.sprviseAtchmnflDetail(sprviseAtchmnflNo);

		List<CommonCdDetailDto> comCdList = this.lbrtyBbscttService.declComCdDeSelect();
		log.info("detail -> comCdList : {}", comCdList);
		model.addAttribute("comCdList", comCdList);

		log.info("sprList : {}", sprList);
		model.addAttribute("detail", detail);
		model.addAttribute("sprList", sprList);
		log.info("detail -> model : {}", model);

		//select 문 두개를 불러올때도 서비스 딴에서 불어와야되나?

		return "lbrtybbsctt/detail";
	}

	@GetMapping("/delete")
	public String delete(LbrtyBbscttDto lbrtyBbscttDto, Model model) {
		log.info("lbNO : {}", lbrtyBbscttDto.getLbrtyBbscttNo());
		log.info("sqNO : {}", lbrtyBbscttDto.getSprviseAtchmnflNo());
		//log.info("detail->lbrtyBbscttNo : {}", lbrtyBbscttNo);

		int result = this.lbrtyBbscttService.lbrtyBbscttDelete(lbrtyBbscttDto);

		//--------------------------
		log.info("result : {}", result);

		/*
		 * List<LbrtyBbscttVO2> list = this.lbrtyBbscttService.lbrtyBbscttList();
		 * log.info("list : {}", list);
		 *
		 * model.addAttribute("lbrtyBbscttList",list);
		 */

		return "redirect:read";
	}

	//insert 홈페이지 이동
	@GetMapping("/insert")
	public String insert(HttpServletRequest request, Model model) {

		String userId = userIdCk(request);
		model.addAttribute("userId", userId);
		log.info("model -> userId : {}", userId);
		log.info("userId : {}", userId);

		return "lbrtybbsctt/registerTest";
	}

	//자유 게시글 등록
	@PostMapping("/create")
	public String create(LbrtyBbscttDto lbrtyBbscttDto) {

		log.info("create lbrtyBbscttVO : {}", lbrtyBbscttDto);

		File uploadPath = new File(uploadFolder, getFolder());

		// 연원일 폴더 생성 실행
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		int result = this.lbrtyBbscttService.lbrtyBbscttInsert(lbrtyBbscttDto); //스프링
		log.info("lbrtyBbscttInsert->result : {}", result);
		//파일 객체 
		//MultipartFile[] multipartFile = lbrtyBbscttVO.getUploadFile();

		return "redirect:read2";
	}

	public String getFolder() {
		// 2024-01-30 형식(format) 지정
		// 간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		// 2024-01-30
		String str = sdf.format(date);
		// 2024-01-30 -> 2024\\01\\30
		return str.replace("-", File.separator);
	}

	//----------------------비동기 방식 ------------------------------------

	//게시글 삭제
	@ResponseBody
	@PostMapping("/bbsDelete")
	public int bbsDelete(@RequestBody LbrtyBbscttDto lbrtyBbscttDto) {
		log.info("bbsDelete -> LbrtyBbscttVO : {}", lbrtyBbscttDto);
		int result = 0;
		result = this.lbrtyBbscttService.lbrtyBbscttDelete(lbrtyBbscttDto);
		log.info("bbsDelete -> result : {}", result);
		return result;
	}

	//게시글 수정
	@ResponseBody
	@PostMapping("/bbsUpdate")
	public int bbsUpdate(LbrtyBbscttDto lbrtyBbscttDto) {
		log.info("bbsUpdate -> LbrtyBbscttVO : {}", lbrtyBbscttDto);
		int result = 0;
		result = this.lbrtyBbscttService.lbrtyBbscttUpdate(lbrtyBbscttDto);
		log.info("bbsUpdate -> result : {}", result);
		return result;
	}

	//아작스 댓글 리스트 조회
	@ResponseBody
	@RequestMapping(value = "/listAns", method = RequestMethod.POST)
	public List<LbrtyBbscttAnswerDto> listAns(@RequestBody Map<String, Object> map) {
		String lbrtyBbscttNo = (String)map.get("lbrtyBbscttNo");
		log.info("lbrtyBbscttNo : {}", lbrtyBbscttNo);
		List<LbrtyBbscttAnswerDto> listAnsVO = this.lbrtyBbscttService.lbrtyBbscttAnswerList(lbrtyBbscttNo);
		log.info("listAnsVO : {}", listAnsVO);

		return listAnsVO;
	}

	//아작스 댓글 입력
	@ResponseBody
	@PostMapping("/insertAns")
	public int insertAns(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {

		log.info("lbrtyBbscttAnswerCn : {}", lbrtyBbscttAnswerDto);
		int result = 0;
		result = this.lbrtyBbscttService.lbrtyBbscttAnswerInsert(lbrtyBbscttAnswerDto);
		return result;
	}

	//아작스 댓글 삭제
	@ResponseBody
	@PostMapping("/deleteAns")
	public int deleteAns(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {

		log.info("deleteAns -> lbrtyBbscttAnswerVO : {}", lbrtyBbscttAnswerDto);
		int result = 0;
		result = this.lbrtyBbscttService.lbrtyBbscttAnswerDelete(lbrtyBbscttAnswerDto);
		return result;
	}

	//아작스 댓글 수정
	@ResponseBody
	@PostMapping("/updataAns")
	public int updataAns(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {
		log.info("updataAns -> lbrtyBbscttAnswerVO : {}", lbrtyBbscttAnswerDto);
		int result = 0;
		result = this.lbrtyBbscttService.lbrtyBbscttAnswerUpdate(lbrtyBbscttAnswerDto);
		return result;
	}

	//아작스 댓댓글 보기
	@ResponseBody
	@PostMapping("/ansAnsView")
	public List<LbrtyBbscttAnswerDto2> ansAnsView(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {
		log.info("ansAnsView -> lbrtyBbscttAnswerVO : {}", lbrtyBbscttAnswerDto);
		List<LbrtyBbscttAnswerDto2> ansAnsViewVO = null;
		ansAnsViewVO = this.lbrtyBbscttService.ansAnsList(lbrtyBbscttAnswerDto);
		log.info("ansAnsView -> ansAnsViewVO : {}", ansAnsViewVO);
		return ansAnsViewVO;
	}

	//아작스 댓댓글 달기
	@ResponseBody
	@PostMapping("/ansAnsInt")
	public int ansAnsInt(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {
		log.info("ansAnsInt -> lbrtyBbscttAnswerVO : {}", lbrtyBbscttAnswerDto);
		int result = 0;
		result = this.lbrtyBbscttService.ansAnsInt(lbrtyBbscttAnswerDto);
		log.info("ansAnsInt -> result : {}", result);
		return result;
	}

	//아작스 댓댓글 갯수 구하기
	@ResponseBody
	@PostMapping("/ansAnsCnt")
	public int ansAnsCnt(@RequestBody LbrtyBbscttAnswerDto lbrtyBbscttAnswerDto) {
		log.info("ansAnsCnt -> lbrtyBbscttAnswerVO : {}", lbrtyBbscttAnswerDto);
		int result = 0;
		result = this.lbrtyBbscttService.ansAnsCnt(lbrtyBbscttAnswerDto);
		log.info("ansAnsCnt -> result : {}", result);
		return result;
	}

	//아작스 파일 부분삭제
	@ResponseBody
	@PostMapping("/fileDel")
	public Map<String, Object> fileDel(@RequestBody SprviseAtchmnfl sprviseAtchmnfl) {
		log.info("fileDel -> sprviseAtchmnfl : {}", sprviseAtchmnfl);
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();

		int atchmnflNo = sprviseAtchmnfl.getAtchmnflNo();
		log.info("atchmnflNo : {}", atchmnflNo);

		map.put("atchmnflNo", atchmnflNo);
		result = this.lbrtyBbscttService.fileDel(sprviseAtchmnfl);
		map.put("result", result);
		log.info("fileDel -> result : {}", result);
		return map;
	}

	// 첨부파일 다운로드
	// 첨부 파일 열어 보기 + 다운로드
	@GetMapping("/pdfView")
	public void pdfView(HttpServletResponse response,
		@RequestParam(value = "sprviseAtchmnflNo", required = false) String sprviseAtchmnflNo) throws IOException {
		log.info("pdfView -> sprviseAtchmnflNo : {}", sprviseAtchmnflNo);
		//	       log.info("pdfView -> atchFileSn: " + atchFileSn);

		List<SprviseAtchmnfl> sprviseAtchmnflList = this.lbrtyBbscttService.detailfileList(sprviseAtchmnflNo);
		log.info("pdfView -> sprviseAtchmnflList : {}", sprviseAtchmnflList);

		if (sprviseAtchmnflList.isEmpty()) {
			throw new FileNotFoundException("첨부 파일이 없습니다.");
		}

		for (SprviseAtchmnfl sprviseAtchmnfl : sprviseAtchmnflList) {
			log.info("pdfView -> getAtchFileNm1 : {}", sprviseAtchmnfl.getAtchmnflNm()); // 파일명을 찍어보는것
			log.info("pdfView -> getAtchFileNm2 : {}", sprviseAtchmnfl.getStoreAtchmnflNm()); // 파일명과 경로 같이 찍어보는것
			String pdfFilePath = uploadFolder + "\\task\\" + sprviseAtchmnfl.getAtchmnflNm(); // PDF 파일의 경로와 파일명을 지정합니다.

			File pdfFile = new File(pdfFilePath);
			log.info("pdfView -> pdfFile : {}", pdfFile);

			// 파일이 존재하는지 확인
			if (!pdfFile.exists()) {
				throw new IOException("PDF 파일을 찾을 수 없습니다.");
			}

			// HTTP 응답 헤더 설정
			response.setContentType("application/pdf");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());

			// 파일 읽기 및 전송
			try (FileInputStream fis = new FileInputStream(pdfFile); OutputStream os = response.getOutputStream()) {

				byte[] buffer = new byte[1024];
				int bytesRead;

				while ((bytesRead = fis.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}

				os.flush();
			} catch (IOException e) {
				throw new IOException("파일을 읽거나 전송하는 중에 오류가 발생했습니다.", e);
			}
		}
	}

	//페이징 처리 전체 목록 출력
	@GetMapping("/read2")
	public String list(Model model, Map<String, Object> map, HttpServletRequest request,
		@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		String userId = userIdCk(request);
		model.addAttribute("userId : ", userId);

		int total = this.lbrtyBbscttService.getTotal(map);
		log.info("ajaxLbList from list -> total : {}", total);
		map.put("total", total);

		map.put("currentPage", currentPage);
		List<LbrtyBbscttDto2> lbrtyBbscttDto2List = this.lbrtyBbscttService.lbrtyBbscttListPage(map);
		log.info("lbrtyBbscttVO2list : {}", lbrtyBbscttDto2List);

		model.addAttribute("lbrtyBbscttVO2list", lbrtyBbscttDto2List);

		return "lbrtybbsctt/read2";

	}

	//검색 목록 출력
	@ResponseBody
	@PostMapping("/ajaxLbList")
	public ArticlePage<LbrtyBbscttDto2> ajaxLbList(@RequestBody(required = false) Map<String, Object> map,
		HttpServletRequest request, Model model) {

		int size = 10;
		int total = this.lbrtyBbscttService.getTotal(map);
		log.info("ajaxLbList -> total : {}", total);
		map.put("total", total);

		log.info("ajaxLbList -> map : {}", map);

		List<LbrtyBbscttDto2> ajaxLbList = this.lbrtyBbscttService.lbrtyBbscttListPage(map);

		log.info("ajaxLbList -> ajaxLbList : {}", ajaxLbList);

		String userId = userIdCk(request);
		model.addAttribute("userId", userId);
		log.info("ajaxLbList -> userId : {}", userId);

		// map : {"keyword : "","currentPage":1}

		String currentPage = map.get("currentPage").toString();
		String keyword = map.get("keyword").toString();
		log.info("ajaxLbList -> currentPage : {}", currentPage);
		log.info("ajaxLbList -> keyword : {}", keyword);

		ArticlePage<LbrtyBbscttDto2> data = new ArticlePage<LbrtyBbscttDto2>(total,
			Integer.parseInt(currentPage), size, ajaxLbList, keyword);

		String url = "/lbrtybbsctt/read2";
		data.setUrl(url);

		return data;

	}

	//신고처리 
	@ResponseBody
	@PostMapping("/declInsert")
	public int declInsert(@RequestBody SntncDeclDto sntncDeclDto) {
		int result = 0;

		log.info("declInsert ->  sntncDeclVO : {}", sntncDeclDto);

		result = this.lbrtyBbscttService.declInsert(sntncDeclDto);

		return result;

	}

	//조회수
	@ResponseBody
	@GetMapping("/cntUp")
	public int cntUp(int lbrtyBbscttNo) {
		int result = 0;

		log.info("cntUp ->  lbrtyBbscttNo : {}", lbrtyBbscttNo);
		result = this.lbrtyBbscttService.cntUp(lbrtyBbscttNo);

		return result;

	}

}
