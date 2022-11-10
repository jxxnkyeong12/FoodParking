package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andreview.ReviewVO;
import andstore.AndMapVO;
import andstore.AndSearchDAO;
import andstore.AndSearchVO;
import andstoreinfo.StoreInfoVO;


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
	
	
	//가게 리뷰 많은 순으로 출력
	@RequestMapping(value = "andReviewAsc", produces = "test/html; charset=utf-8")
	public String review_asc() {
		List<StoreInfoVO> list = dao.review_asc();
		return gson.toJson(list);
	}
	
	
	//특정 가게 정보 뽑기 - 10.10 hs
	@RequestMapping(value = "andStoreDetail", produces = "test/html; charset=utf-8")
	public String store_detail(int store_code) {
		StoreInfoVO vo = dao.store_detail(store_code);
		
		//List<StoreInfoVO> list = dao.store_detail(store_code);
		return gson.toJson(vo);
	}
	
	
	//카테고리별로 가게 리스트 출력 - 10.11 hs
	@RequestMapping(value = "andCategory", produces = "test/html; charset=utf-8")
	public String search_category(int store_category) {
		List<StoreInfoVO> list = dao.search_category(store_category);
		return gson.toJson(list);
	}
}
