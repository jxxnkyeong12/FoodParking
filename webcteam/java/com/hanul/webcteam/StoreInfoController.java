package com.hanul.webcteam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StoreInfoController {
	
	//내 가게 리스트 화면 요청
	@RequestMapping("/myStore")
	public String myStore(HttpServletRequest request) {
		//화면 요청
		return "store/mystore";
	}

	
}
