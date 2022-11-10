package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andreview.ReviewDAO;
import andreview.ReviewVO;
import common.Common;

@RestController
public class AndReviewListController {


	@Autowired ReviewDAO dao;
	@Autowired Common common;
	Gson gson = new Gson();
	
	
	//내가 쓴 리뷰 리스트 -jk
	@RequestMapping(value = "/andReviewList", produces = "text/html; charset=utf-8")
	public String review_list(int id) {
		System.out.println(id);
		List<ReviewVO> list = dao.review_list(id);
		return gson.toJson(list);
	}
	

	//리뷰 리스트에서 바로 삭제 -jk
	@RequestMapping(value = "/andReviewDelete", produces = "text/html; charset=utf-8")
	public String review_delete(int id) {
		System.out.println(id);
		 int delete = dao.delete(id);
		return gson.toJson(delete);
	}
	
	
	
}
