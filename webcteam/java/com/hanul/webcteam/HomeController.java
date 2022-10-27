package com.hanul.webcteam;

import java.util.List;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import home.HometStoreDAO;
import home.ReviewVO;
import home.StoreInfoVO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HometStoreDAO dao;


	//home폴더 -detail.jsp 리뷰 신고하기 중복 확인 jk
	@ResponseBody @RequestMapping("/admin_detail.ho")
	public boolean admin_detail(String star_code) {
		System.out.println(star_code);
		System.out.println("리뷰중복확인거침");
		return dao.admin_detail(star_code);
	}
	
	
	
	
	//home폴더-detail.jsp 상세페이지에서 신고하기 버튼이 눌러졌을때 - jk
	@ResponseBody @RequestMapping("/admin_insert.ho")
	public int admin_insert(String star_code) {
	    	System.out.println(star_code + "인설트부분");
		     int code = Integer.parseInt(star_code);
			
			 return  dao.admin_insert(star_code);
	}
	
	
	
	// 메인화면에서 가게 클릭시 가게 상세하게 나오게 하려고
	@RequestMapping("/detail.ho")
	public String detail(int store_code, Model model) {
		
		StoreInfoVO vo = dao.home_store_detail(store_code);
		List<StoreInfoVO> list = dao.home_store_menu(store_code);
		List<ReviewVO> review = dao.home_store_review(store_code);

			model.addAttribute("vo",vo);
			model.addAttribute("list", list);
		
		    model.addAttribute("review", review);
		return "home/detail";
	}

	// 메인화면으로 입점가게 리스트 뿌리는 메소드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		List<StoreInfoVO> vo = dao.home_store_enter();
		model.addAttribute("vo", vo);
		return "home";
	}

}
