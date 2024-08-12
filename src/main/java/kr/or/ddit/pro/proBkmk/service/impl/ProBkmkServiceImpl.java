package kr.or.ddit.pro.proBkmk.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pro.proBkmk.mapper.ProBkmkMapper;
import kr.or.ddit.pro.proBkmk.service.ProBkmkService;
import kr.or.ddit.vo.ProBkmkVO;


@Service
@RequiredArgsConstructor
public class ProBkmkServiceImpl implements ProBkmkService {
	
	private final ProBkmkMapper proBkmkMapper;
	
	@Override
	public int proBkmkCreate(String proId, String mberId) {
		return this.proBkmkMapper.proBkmkCreate(proId,mberId);
	}
	@Override
	public String proBkmkCheck(String proId, String mberId) {
		return this.proBkmkMapper.proBkmkCheck(proId,mberId);
	}
	@Override
	public int proBkmkDelete(String proId, String mberId) {
		return this.proBkmkMapper.proBkmkDelete(proId,mberId);
	}
	@Override
	public List<ProBkmkVO> getFavInfo(String memId) {
		return this.proBkmkMapper.getFavInfo(memId);
	}
}
