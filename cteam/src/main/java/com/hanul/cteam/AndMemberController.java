package com.hanul.cteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.google.gson.Gson;

import andmember.AndMemberDAO;

import andmember.MemberVO;

@Controller
public class AndMemberController {

  @Autowired AndMemberDAO dao;
	Gson gson = new Gson();
	
	
	
	@ResponseBody @RequestMapping("/login")
	public String login(String email, String pw, String social) {
	   MemberVO vo = new MemberVO();
	   if(email == null || pw == null) {
			return gson.toJson(null);
		}
	   
	   vo.setEmail(email);
	   vo.setPw(pw);
	    vo = dao.login(vo, social);
		
		
		return gson.toJson(vo);
	}
	
}
