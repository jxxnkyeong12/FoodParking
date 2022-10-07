package com.hanul.cteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import review.ReviewDAO;
import review.ReviewVO;


@RestController
public class AndReviewController {
		@ Autowired ReviewDAO dao;;
		Gson gson = new Gson();
		
	@RequestMapping(value = "/andReview", produces = "text/html;charset=utf-8")
	public String review_insert(String vo) {
		ReviewVO  insert = gson.fromJson(vo, ReviewVO.class);
		
		return "";
	}
	
}
