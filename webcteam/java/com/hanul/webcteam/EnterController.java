package com.hanul.webcteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartRequest;

import common.CommonService;
import enter.jk.EnterDAO;
import enter.jk.EnterVO;

@Controller
public class EnterController {
	@Autowired 	private EnterDAO dao;
	
	@Autowired private CommonService common;
	
	
	
	
	
	
	//문자인증- jk
	@RequestMapping(value = "/phoneCheck", method = RequestMethod.GET)
	@ResponseBody
	public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
		common.certifiedPhoneNumber(userPhoneNumber,randomNumber);
		return Integer.toString(randomNumber);
	}
	
	
	//입점신청 insert해주기- jk
	@RequestMapping("/insert.en")

	public String insert(EnterVO vo, HttpServletRequest request) {
		
		MultipartRequest mReq = (MultipartRequest) request;
		MultipartFile file = mReq.getFile("file");
		MultipartFile file2 = mReq.getFile("file2");
		
		
		if(!file.isEmpty() && !file2.isEmpty()) {
			vo.setB_enter_copy(common.fileUploadhw("entering", file, request));
			vo.setB_enter_copy2(common.fileUploadhw("entering", file2, request));
		}
		dao.enter_insert(vo);
		return "redirect:/";
	}
	
	
	
	//사업자등록번호 체크 -jk
	@ResponseBody @RequestMapping("/b_check")
	public boolean b_num_check(String b_num) {
		return dao.b_num_check(b_num);
	}
	
	
	
	//입점신청서 연결화면 -jk
	@RequestMapping("/enter.en")
	public String list(HttpSession session) {
		session.setAttribute("category", "en");
		//응답화면연결
		return "enter/list";
	}
	
}
