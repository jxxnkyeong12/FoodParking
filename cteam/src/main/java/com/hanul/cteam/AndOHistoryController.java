package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andorder.OrderHistoryDAO;
import andorder.OrderHistoryVO;

@RestController
public class AndOHistoryController {
	@Autowired OrderHistoryDAO dao;
	Gson gson = new Gson();
	
	
	//리스트 뽑기 jk - 2022/10/05
	
	@RequestMapping(value = "/andOrderHistory", produces = "text/html;charset=utf-8")
	public String OHlist(int id) {
		System.out.println(id);
		List<OrderHistoryVO> list = dao.list(id) ;
		
		return gson.toJson(list);
		
	}
	
}
