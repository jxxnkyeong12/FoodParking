package com.hanul.webcteam;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import join.hs.JoinDAO;
import join.hs.JoinVO;

@Controller
public class JoinController {
	@Autowired private JoinDAO dao;
	
	//회원가입 화면 요청 - hs (10/12)
	@RequestMapping("/join.mb")
	public String join_mb(HttpSession session) {
		session.setAttribute("category", "join");
		return "member/join";
	}
	
	//회원가입 처리 요청
	@ResponseBody @RequestMapping(value="/join", produces="text/html; charset=utf-8")
	public String join(JoinVO vo) {
		//if(!file.isEmpty()) {
			//String profile = common.fileUpload("profile", file, req);
			//vo.setFile_path(profile);
		//}
		dao.member_join(vo);
		
		return "";
	}
	
	
	//이메일 중복확인 요청
	@ResponseBody @RequestMapping("/email_check")
	public boolean email_check(String email) {
		return dao.member_email_check(email);
	}
}