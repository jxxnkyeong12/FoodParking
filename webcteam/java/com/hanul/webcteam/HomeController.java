package com.hanul.webcteam;

import java.util.List;

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

import home.HometStoreDAO;
import home.StoreInfoVO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HometStoreDAO dao;
	
	// 푸드파킹 둘러보기 화면 요청
	@RequestMapping("/history.ho")
	public String history() {
		
		return "home/history";
	}
	
	
	// 푸드파킹 인사말 화면 요청
	@RequestMapping("/about.ho")
	public String about() {
		
		return "home/about";
	}
	
	

	// 메인화면에서 가게 클릭시 가게 상세하게 나오게 하려고
	@RequestMapping("/detail.ho")
	public String detail(int store_code, Model model) {
		
		StoreInfoVO vo = dao.home_store_detail(store_code);
		List<StoreInfoVO> list = dao.home_store_menu(store_code);
		
			model.addAttribute("vo",vo);
			model.addAttribute("list", list);
		
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
