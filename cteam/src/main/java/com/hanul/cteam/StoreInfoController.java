package com.hanul.cteam;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


import common.Common;
import storeinfo.StoreInfoDAO;
import storeinfo.StoreInfoVO;
import storeinfo.StoreMenuVO;

@RestController
public class StoreInfoController {

  @Autowired StoreInfoDAO dao;
  @Autowired Common common;
	Gson gson = new Gson();
	
	
	
	//가게리스트 조회
	@RequestMapping(value = "/andStoreList", produces = "text/html;charset=utf-8")
	public String storeList() {
		List<StoreInfoVO> list = dao.store_list();
		Gson gson = new Gson();
		return gson.toJson(list);
		
	}
	
	//가게 메뉴리스트 조회
	@RequestMapping(value = "/storeMenuList", produces = "text/html;charset=utf-8")
	public String storeMenuList(int store_code) {
		List<StoreMenuVO> list = dao.store_menu_list(store_code);
		Gson gson = new Gson();
		return gson.toJson(list);
		
	}
	
}
