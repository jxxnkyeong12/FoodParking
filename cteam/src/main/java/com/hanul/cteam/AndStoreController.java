package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andstore.AndMapVO;
import andstore.AndSearchDAO;
import andstore.AndSearchVO;
import storeinfo.StoreInfoVO;

@RestController
public class AndStoreController {
	@Autowired AndSearchDAO dao;
	Gson gson = new Gson();
	
	//검색어가 포함된 리스트 출력
	@RequestMapping(value = "andSearchList", produces = "test/html; charset=utf-8")
	public String search_list() {
		List<AndSearchVO> list = dao.search_list();
		return gson.toJson(list);
	}
	
	
	//가게 위도, 경도 뽑기
	@RequestMapping(value = "andStoreMap", produces = "test/html; charset=utf-8")
	public String store_map(int store_code) {
		AndMapVO list = dao.store_map(store_code);
		
		return gson.toJson(list);
	}
}
