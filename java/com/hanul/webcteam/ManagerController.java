package com.hanul.webcteam;



import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonService;
import enter.jk.EnterDAO;
import enter.jk.EnterVO;







@Controller
public class ManagerController {
	@Autowired private EnterDAO dao;
	@Autowired private CommonService common;
	
	//입점신청 수락으로 회원 아이디로 된 매장 하나 만들기
	@RequestMapping("/admin_make_store")
	public String admin_make_store(EnterVO vo) {
		//회원의 매니저를 'Y'로 바꾼다
		if(dao.update_member_manager(vo.getId()) >= 1) {
			if(dao.update_enter_status(vo.getId()) >= 1) {
				dao.admin_make_store(vo);
			}
		}
		return "/admin_store";
	}
	
	
	//입점신청 화면 요청
	@RequestMapping("/admin_store_cancle")
	public String admin_store(int  id) {
		dao.admin_store_cancle(id);
		
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping("/download")
	public String download(int id, String b_enter_copy,  HttpServletResponse response
			, HttpServletRequest request) throws Exception {
		String path = b_enter_copy; // 경로에 접근할 때 역슬래시('\') 사용
    	
    	File file = new File(path);
    	System.out.println(path);
		//해당 첨부파일을 서버에서 찾아 클라이언트에 다운로드한다
		//해당 공지글 정보를 조회하여 저장된 파일정보를 파악
		
		 common.fileDownload(file.getName(), path, response, request);
		return null;
    }
	
	
	
		//입점신청 화면 요청
		@RequestMapping("/admin_store")
		public String admin_store(Model model) {
			List<EnterVO> list =  dao.admin_store();
			model.addAttribute("list", list);
			return "manager/apply";
		}

	
}
