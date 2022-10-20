package com.hanul.webcteam;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mystore.hs.MyStoreDAO;

@Controller
public class MyStoreController {
	@Autowired private MyStoreDAO dao;

	//가게 목록 화면 요청
	@RequestMapping("/list.st")
	public String store_list(HttpSession session, Model model) {
		session.setAttribute("category", "mystore");
		//model.addAttribute(dao.mystore_list(id));
		return "store/list";
	}
}
