package com.hanul.cteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@RequestMapping(value = "/andLogin", produces = "text/html;charset=utf-8")
	public String login(String email, String pw) {
		MemberVO vo = new MemberVO();
		String salt = dao.member_salt(email);
		pw = common.getEncrypt(salt, pw);

		vo.setEmail(email);
		vo.setPw(pw);
		vo = dao.login(vo);

		return gson.toJson(vo);
	}

	
	//회원가입
	@RequestMapping(value = "/andJoin", produces = "text/html;charset=utf-8")
	public String join(String vo) {
		MemberVO joinInfo = new Gson().fromJson(vo, MemberVO.class);

		// 소셜 로그인 회원가입
		if (joinInfo.getSocial().equals("Y")) {
			dao.social_join(joinInfo);
		} else {
			dao.join(joinInfo);
		}

		return gson.toJson(vo);
	}

	
	//이메일 중복체크
	@RequestMapping(value = "/andEmailChk", produces = "text/html;charset=utf-8")
	public String email_check(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		boolean result = dao.email_check(email);
		
		System.out.println(result);
		
		if(result == true) {
			return "있음";
		}else {
			return "없음";
		}
		
	}
}
