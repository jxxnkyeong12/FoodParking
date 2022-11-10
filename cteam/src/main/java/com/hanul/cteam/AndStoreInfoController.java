package com.hanul.cteam;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andmember.MemberVO;
import andreview.ReviewVO;
import andstoreinfo.BasketVO;
import andstoreinfo.StoreInfoDAO;
import andstoreinfo.StoreInfoVO;
import andstoreinfo.StoreMenuVO;
import common.Common;

@RestController
public class AndStoreInfoController {

	@Autowired StoreInfoDAO dao;
	@Autowired Common common;
	Gson gson = new Gson();

	// 가게 메뉴리스트 조회 - sb 10/04
	@RequestMapping(value = "/storeMenuList", produces = "text/html;charset=utf-8")
	public String storeMenuList(int store_code) {
		List<StoreMenuVO> list = dao.store_menu_list(store_code);
		return gson.toJson(list);
	}

	// 장바구니 추가 - sb 10/04
	@RequestMapping(value = "/andBasketInsert", produces = "text/html; charset=utf-8")
	public String basket_insert(String menu, int id) {
		StoreMenuVO vo = new Gson().fromJson(menu, StoreMenuVO.class);
		dao.baketInsert(vo, id);
		return gson.toJson(vo);
	}

	// 장바구니 리스트 출력 - sb 10 /04
	@RequestMapping(value = "/andBasketList", produces = "text/html; charset=utf-8")
	public String basket_list(int id, int store_code) {
		HashMap<String, Integer> map = new HashMap();
		map.put("id", id);
		map.put("store_code", store_code);
		List<BasketVO> list = dao.basket_list(map);
		return gson.toJson(list);
	}

	// 장바구니 중복체크
	@RequestMapping(value = "/andBasket", produces = "text/html; charset=utf-8")
	public String baskett(int id) {
		List<BasketVO> list = dao.basket(id);
		return gson.toJson(list);
	}

	// 장바구니 전체삭제 - sb 10 /04
	@RequestMapping(value = "/andBasketDelete", produces = "text/html; charset=utf-8")
	public String basket_delet(String menu) {
		BasketVO vo = new Gson().fromJson(menu, BasketVO.class);
		dao.baketDelete(vo);
		return gson.toJson(vo);
	}

	// 장바구니 리스트 삭제 - sb 10 /04
	@RequestMapping(value = "/andBasketDeleteAll", produces = "text/html; charset=utf-8")
	public int basket_delet_all(int id) {
		return dao.baketDeleteAll(id);
	}

	//가게 평점 낮은순으로 조회 - jk
	@RequestMapping(value = "/andStoreMin", produces = "text/html;charset=utf-8")
	public String storeMin(int store_code) {
		List<StoreInfoVO> list = dao.store_Min(store_code);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 가게 평점 높은순으로 조회 - jk
	@RequestMapping(value = "/andStoreMax", produces = "text/html;charset=utf-8")
	public String storeMax(int store_code) {
		List<StoreInfoVO> list = dao.store_Max(store_code);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 가게 평균 점수 조회 - jk
	@RequestMapping(value = "/andStoreScore", produces = "text/html;charset=utf-8")
	public String storeScore(int store_code) {
		List<StoreInfoVO> list = dao.store_score(store_code);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 가게 리뷰 조회-jk
	@RequestMapping(value = "/andStoreReview", produces = "text/html;charset=utf-8")
	public String storeReview(int store_code) {
		List<StoreInfoVO> list = dao.store_Review(store_code);
		Gson gson = new Gson();
		return gson.toJson(list);

	}

	// 가게리스트 조회 -sb
	@RequestMapping(value = "/andStoreList", produces = "text/html;charset=utf-8")
	public String storeList() {
		List<StoreInfoVO> list = dao.store_list();
		// System.out.println(list.get(0).getStore_filepath());
		Gson gson = new Gson();
		return gson.toJson(list);

	}

}
