package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andbookmark.AndBookmarkDAO;
import andbookmark.AndBookmarkVO;

@RestController
public class AndBookmarkController {
	@Autowired AndBookmarkDAO dao;
	Gson gson = new Gson();

	//찜 추가
	@RequestMapping(value = "/andBMInsert", produces = "text/html; charset=utf-8")
	public String bookmark_insert(String vo) {
		AndBookmarkVO bookmarkInfo = new Gson().fromJson(vo, AndBookmarkVO.class);
		dao.bookmark_insert(bookmarkInfo);
		return gson.toJson(vo);
	}

	//찜 해제
	@RequestMapping(value = "/andBMDelete", produces = "text/html; charset=utf-8")
	public String bookmark_delete(String vo) {
		AndBookmarkVO bookmarkInfo = new Gson().fromJson(vo, AndBookmarkVO.class);
		dao.bookmark_delete(bookmarkInfo);
		return gson.toJson(vo);
	}
	
	//찜 목록
	@RequestMapping(value = "/andBMList", produces = "text/html; charset=utf-8")
	public String bookmark_list(String id) {
		List<AndBookmarkVO> list = dao.bookmark_list(id);
		return gson.toJson(list);
	}
	
}