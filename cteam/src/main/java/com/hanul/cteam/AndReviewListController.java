package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import common.Common;
import review.ReviewDAO;
import review.ReviewVO;

@RestController
public class AndReviewListController {


	@Autowired ReviewDAO dao;
	@Autowired Common common;
	Gson gson = new Gson();
	
	//찜 목록
	@RequestMapping(value = "/andReviewList", produces = "text/html; charset=utf-8")
	public String review_list(String email) {
		System.out.println(email);
		List<ReviewVO> list = dao.review_list(email);
		return gson.toJson(list);
	}
	

	//리뷰 삭제
	@RequestMapping(value = "/andReviewDelete", produces = "text/html; charset=utf-8")
	public String review_delete(String email) {
		System.out.println(email);
		 int delete = dao.delete(email);
		return "";
	}
	
	
	
}
