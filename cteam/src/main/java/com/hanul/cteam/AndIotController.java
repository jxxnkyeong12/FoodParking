package com.hanul.cteam;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import iot.IotDAO;
import iot.IotVO;

@RestController
public class AndIotController {
	@Autowired IotDAO dao;
	Gson gson = new Gson();

	
	@RequestMapping(value = "/iot_table_total", produces = "text/html; charset=utf-8")
	public String iot_table_total(String table) {
			List<IotVO> list = dao.getTable_history(table);
		return gson.toJson(list);
	}

	
	
	@RequestMapping(value = "/iot_table", produces = "text/html; charset=utf-8")
	public String iot_table(String table) {
		List<IotVO> list = dao.getTable(table);
		return gson.toJson(list);
	}
	

}