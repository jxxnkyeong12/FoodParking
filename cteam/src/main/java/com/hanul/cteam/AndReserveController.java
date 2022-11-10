package com.hanul.cteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import andorder.OrderHistoryVO;
import andorder.OrderInfoVO;
import andreserve.OrderInfoDAO;
import andreserve.Order_infoVO;

@RestController
public class AndReserveController {

	
	@Autowired OrderInfoDAO dao;
	Gson gson = new Gson();

	
	//주문상태 테이블에 예약 추가
	@RequestMapping(value = "/andInsert_order_history", produces = "text/html; charset=utf-8")
	public String andInsert_order_history(String vo) {
		OrderHistoryVO order_history = new Gson().fromJson(vo, OrderHistoryVO.class);
		dao.insert_order_history(order_history);
		return gson.toJson(vo);
	}
	
	
	
	//주문상태 테이블에 예약 추가
	@RequestMapping(value = "/andDelete_order_info", produces = "text/html; charset=utf-8")
	public int andDelete_order_info(int id) {
		return dao.delete_order_info(id);
		
	}
	
	
	//주문상태 테이블에 예약 추가
	@RequestMapping(value = "/reserve_store", produces = "text/html; charset=utf-8")
	public String reserve_store(String vo) {
		Order_infoVO order_info = new Gson().fromJson(vo, Order_infoVO.class);
		dao.insert_order_info(order_info);
		return gson.toJson(vo);
	}
	
	//주문상태 테이블 있는지 조회
	@RequestMapping(value = "/andOrder_info_list", produces = "text/html; charset=utf-8")
	public String insert_order_info(int id) {
		List<OrderInfoVO> list = dao.select_order_info(id);
		return gson.toJson(list);
	}
	
	
	
}
