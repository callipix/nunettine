package kr.or.ddit.pro.proBkmk.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.or.ddit.pro.proBkmk.mapper.ProBkmkMapper;
import kr.or.ddit.dto.ProBkmkDto;


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
	public List<ProBkmkDto> getFavInfo(String memId) {
		return this.proBkmkMapper.getFavInfo(memId);
	}
}
