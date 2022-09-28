package com.hanul.cteam;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andmember.AndMemberDAO;

import andmember.MemberVO;
import common.Common;

@RestController
public class AndMemberController {

  @Autowired AndMemberDAO dao;
  @Autowired Common common;
	Gson gson = new Gson();
	
	
	
	//로그인 (salt찾아서..로그인 되게)
	@RequestMapping("/andLogin")
	public String login(String email, String pw, String social) {
	   MemberVO vo = new MemberVO();
	   if(email == null || pw == null) {
			//return gson.toJson(null);
		}
	   
	   String salt = dao.member_salt(email);
	   pw = common.getEncrypt(salt, pw);
	   
	   vo.setEmail(email);
	   vo.setPw(pw);
	   vo = dao.login(vo, social);
		
		return gson.toJson(vo);
	}
	
	

	//회원가입
	@RequestMapping(value = "/andJoin", produces = "text/html;charset=utf-8")
	public String join(String vo) {
		MemberVO joinInfo = new Gson().fromJson(vo, MemberVO.class);
		
		//소셜 로그인 회원가입
		if(joinInfo.getSocial().equals("Y")) {
			dao.social_join(joinInfo);
		}else {
			dao.join(joinInfo);			
		}

		return gson.toJson(vo);
	
	}
	
	
	
	
	
}
