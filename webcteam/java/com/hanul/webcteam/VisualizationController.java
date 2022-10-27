package com.hanul.webcteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import visual.BasketVO;
import visual.VisualDAO;



@Controller
public class VisualizationController {
	
	@Autowired 	private VisualDAO dao;
	Gson gson = new Gson();
	
	
	
	//일별 매출현황 - sb
	@ResponseBody @RequestMapping("visual/day_total_price")
	public List<HashMap<String, Object>> day_total_price(int id) {
		List<HashMap<String, Object>> map = dao.day_total_price(id);
		return map;
	}
	
	
	//월별 매출현황 - sb
	@ResponseBody @RequestMapping("visual/total_price")
	public List<HashMap<String, Object>> total_price(int id) {
		List<HashMap<String, Object>> map = dao.total_price(id);
		return map;
	}
	
	
	//시각화화면 요청 -sb
	@RequestMapping("/mytotal_price.st")
	public String visual(HttpSession session) {
		session.setAttribute("category", "vi");
		return "visual/list";
	}
}
