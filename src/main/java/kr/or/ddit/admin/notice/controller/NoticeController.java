package kr.or.ddit.admin.notice.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ddit.admin.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.notice.service.NoticeService;
import kr.or.ddit.util.ArticlePage2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	private final String uploadFolder;

	@GetMapping(value="/notice")
	public String noticeList(Model model){
		List<NoticeDto> list = noticeService.getAllNoticeList();
		model.addAttribute("noticeList",list);
				
		return "admin/notice";	
	}
		
	@GetMapping(value="/detail")
	public String noticeDetail(@RequestParam int noticeNo, Model model){
	    log.info("detail->noticeDetail: {} ",noticeNo);
	    // 조회수 증가
	    this.noticeService.increaseViewCount(noticeNo);
	    // 공지사항 정보 조회
	    NoticeDto noticeDto = this.noticeService.detail(noticeNo);
	    model.addAttribute("noticeVO", noticeDto);
	    
	    NoticeDto noticeVOAtchDtoList = this.noticeService.sprviseAtchmnflVO(noticeNo);
	    model.addAttribute("sprviseAtchmnflVOList", noticeVOAtchDtoList);
	    
	    return "admin/detail";
	}

	@ResponseBody
	@PostMapping("/update")
	public int noticeUpdate(@RequestBody NoticeDto noticeDto) {

		//Updates: 1
		log.info("update :  {} ", noticeDto);
		int result = this.noticeService.update(noticeDto);
		//insert : 1
		log.info("update->result :  {} ",result);
		return result ;
	}
	@ResponseBody
	@PostMapping("/delete")
	public int noticeDelete(@RequestBody NoticeDto noticeDto) {
		log.info("delete: {} ", noticeDto);
		
		int result = this.noticeService.delete(noticeDto);
		log.info("delete->result: {} ",result);
		return result;
	}

	  @GetMapping(value="/create")
	  public String create(NoticeDto noticeDto, HttpSession session) {
		  log.info("create->noticeVO: {} ", noticeDto);
		  
		  return "admin/create"; 
	  }

	@ResponseBody
	@PostMapping(value="/createPost")
	public int createPost(NoticeDto noticeDto) {
		log.info("createPost->noticeVO: {} ", noticeDto);
		
		noticeDto.setMngrId("testAdmin");
		//System.out.println("notico:: " + noticeVO.getMngrId());
		File uploadPath = new File(uploadFolder,getFolder());
		
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
//		NoticeVO noticeVO = new NoticeVO()
		int result = this.noticeService.createPost(noticeDto);
		log.info("createPost->result: {} ", result);
		return result;
		//return "redirect:/admin/notice="+noticeVO.getNoticeNo();
		//return "redirect:/admin/notice?noticeNo="+noticeVO.getNoticeNo();
	}
	//연/월/일 폴더 생성
			public String getFolder() {
				//2024-01-30 형식(format) 지정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//날짜 객체 생성(java.util 패키지)
				Date date = new Date();
				//2024-01-30
				String str = sdf.format(date);
				//2024-01-30 -> 2024\\01\\30
				return str.replace("-", File.separator);
			}
			//이미지인지 판단. 
			public boolean checkImageType(File file) {
				String contentType;
				try {
					contentType = Files.probeContentType(file.toPath());
					log.info("contentType : {} ", contentType);
					//image/jpeg는 image로 시작함->true
					return contentType.startsWith("image");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//이 파일이 이미지가 아닐 경우
				return false;
			}

	@GetMapping(value="/list")
	public String list(Model model, Map<String,Object> map,
			@RequestParam(value="currentPage",required=false,defaultValue="1")int currentPage,
			@RequestParam(value="keyword", required=false, defaultValue="1")String keyword,
			@RequestParam(value="searchKey", required=false, defaultValue="")String searchKey){
		
		if(map!=null) {
			keyword = (String)map.get("keyword");
			searchKey = (String)map.get("searchKey");
			
			if(map.get("currentPage")==null) {
				map.put("currentPage",currentPage);
			}
		} else {
			map = new HashMap<String,Object>();
			map.put("keyword","");
			map.put("searchKey","");
			map.put("currentPage",1);
		}
		
		map.put("currentPage",currentPage);
		
		int total  = this.noticeService.getTotal(map);
		log.info("list->total: {} ",total);
		int size = 10;
		
		List<NoticeDto> noticeDtoList = this.noticeService.list(map);
		log.info("list->noticeVOList: {} ", noticeDtoList);
		
		model.addAttribute("data", new ArticlePage2<NoticeDto>(total
				,currentPage, size, noticeDtoList, keyword, "leftList",searchKey));
		
		return "admin/notice";
		
	}
		//요청URI : /admin/listAjax
		//요청파라미터 : {keyword:ㅁㅁ,currentPage:1}
		@ResponseBody
		@PostMapping(value="/listAjax")
		public ArticlePage2<NoticeDto> listAjax(@RequestBody(required=false) Map<String,Object> map) throws ParseException {
			
			log.info("listAjax->ArticlePage2->map :  {} ", map);
			
			String keyword = map.get("keyword").toString();
			String searchKey = map.get("searchKey").toString();
			int currentPage = Integer.parseInt(map.get("currentPage").toString());
			
			int total = this.noticeService.getTotal(map);
			log.info("listAjax->total {} ",total);
			
			int size = 10;
			
			List<NoticeDto> noticeDtoList = this.noticeService.list(map);
			log.info("list -> noticeVOList : {} ", noticeDtoList);
			
			ArticlePage2<NoticeDto> data = new ArticlePage2(total, currentPage, size, noticeDtoList, keyword, "leftList", searchKey);
			
			String url  = "/admin/notice";
			
			data.setUrl(url);
			
			return data;
		}
}