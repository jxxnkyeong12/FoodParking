package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andstore.AndSearchDAO;
import andstore.AndSearchVO;

@RestController
public class AndStoreController {
	@Autowired AndSearchDAO dao;
	Gson gson = new Gson();
	
	//검색어가 포함된 리스트 출력
	@RequestMapping(value = "andSearchList", produces = "test/html; charset=utf-8")
	public String list() {
		List<AndSearchVO> list = dao.menu_list();
		return gson.toJson(list);
	}
}
