package com.hanul.cteam;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import reserve.OrderInfoDAO;
import reserve.Order_infoVO;

@RestController
public class ReserveController {
	@Autowired OrderInfoDAO dao;
	Gson gson = new Gson();

	//주문상태 테이블에 예약 추가
	@RequestMapping(value = "/reserve_store", produces = "text/html; charset=utf-8")
	public String insert_order_info(String vo) {
		Order_infoVO order_info = new Gson().fromJson(vo, Order_infoVO.class);
		dao.insert_order_info(order_info);
		return gson.toJson(vo);
	}
	
	
	//주문정보 상세 조회
	@RequestMapping(value = "/order_detail", produces = "text/html; charset=utf-8")
	public String order_detail(String vo) {
		Order_infoVO order_info = new Gson().fromJson(vo, Order_infoVO.class);
		List<Order_infoVO> list =  dao.order_detail(order_info);
		return gson.toJson(vo);
	}
	

	
}