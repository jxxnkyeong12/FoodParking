package com.hanul.webcteam;



import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Iterator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import common.CommonService;
import enter.jk.EnterDAO;
import enter.jk.EnterPageVO;
import enter.jk.EnterVO;

import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import enter.jk.EnterDAO;
import enter.jk.EnterVO;
import home.ReviewVO;







@Controller
public class ManagerController {
	@Autowired private EnterDAO dao;
	@Autowired private CommonService common;
	

	
	//리뷰 삭제 수락 jk
    @RequestMapping("/admin_review_delete")
	public String admin_review_delete(int star_code) {
		 dao.admin_delete_review(star_code);
		 return "redirect: admin_review";
	}
	
	
	//리뷰삭제 요청 jk
   @RequestMapping("/admin_review")
	public String admin_review_delete(ReviewVO vo, Model model) {
		//사장님 리뷰 요청 화면 
    	List<ReviewVO> list =  dao.admin_review_list();
    	model.addAttribute("list", list);
		return "manager/review_delete";
	}
	
	
	

	//입점신청 수락으로 회원 아이디로 된 매장 하나 만들기
	@RequestMapping("/admin_make_store")
	public String admin_make_store(EnterVO vo) {
		//회원의 매니저를 'Y'로 바꾼다
		if(dao.update_member_manager(vo.getId()) >= 1) {
			if(dao.update_enter_status(vo.getId()) >= 1) {
				dao.admin_make_store(vo);
				String email = dao.admin_get_email(vo.getId());
				vo.setEmail(email);
				common.sendEmail(vo.getEmail(), vo.getStore_name(), vo.getCancle(), 2);

			}
		}
		return "redirect:admin_store";
	}
	

	//입점신청 실패
	@RequestMapping("/admin_store_cancle")
	public String admin_store(EnterVO vo) {
		dao.admin_store_cancle(vo);
		String email = dao.admin_get_email(vo.getId());
		vo.setEmail(email);
		common.sendEmail(vo.getEmail(), vo.getStore_name(), vo.getCancle(), 1);
		return "redirect:admin_store";

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
	
	

	@Autowired private EnterPageVO page;
	
			//입점신청 화면 요청
		@RequestMapping("/admin_store")
		public String admin_store(HttpSession session, Model model
									, String search, String keyword
									, @RequestParam(defaultValue = "list") String viewType
									, @RequestParam(defaultValue = "10")int pageList
									, @RequestParam(defaultValue = "1") int curPage) {
			
			page.setCurPage(curPage);
			page.setPageList(pageList);
			
		
			model.addAttribute("page",  dao.admin_store(page));
			return "manager/apply";
		}

	
}
