package com.hanul.webcteam;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import notice.jk.NoticeDAO;
import notice.jk.NoticePageVO;

@Controller
public class NoticeController {

		@Autowired private NoticeDAO dao;
		 @Autowired private NoticePageVO page;
		
		@RequestMapping("/detail.no")
		public String detail() {
			return "";
		}
		
		
		//화면 요청 -jk
		@RequestMapping("/notice.no")
		public String list(HttpSession  session, Model model
									, String search, String keyword
									, @RequestParam(defaultValue = "1") int curPage) {
			session.setAttribute("category", "no");
			//응답화면 요청만
			page.setCurPage(curPage);
			page.setSearch(search); 
			page.setKeyword(keyword);
			
			page = dao.notice_list(page);
			model.addAttribute("page", page);
		 return "notice/list";	
		}
		
	
}
