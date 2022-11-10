package andstoreinfo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import common.Common;

@RestController
public class StoreInfoDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public List<StoreInfoVO> store_list() {
		return sql.selectList("store.list");
	}


	public List<StoreMenuVO> store_menu_list(int store_code) {
		return sql.selectList("store.menu_list", store_code);
	}



	public int baketInsert(StoreMenuVO vo, int id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		map.put("id", id);
		return sql.insert("store.basket_insert", map);
	}



	public List<BasketVO> basket_list(HashMap<String, Integer> map) {
		return sql.selectList("store.basket_list", map);
	}



	public List<BasketVO> basket(int id) {
		return sql.selectList("store.basket", id);
	}



	public int baketDelete(BasketVO vo) {
		return sql.delete("store.basket_delete", vo);
	}



	public int baketDeleteAll(int id) {
		return sql.delete("store.basket_delete_all", id);
	}
	
	

	  //가게 리뷰 총 평점 jk - 2022/10/01
		public List<StoreInfoVO> store_score(int store_code) {
			return sql.selectList("store.store_score", store_code);
		}

		
	//가게에 있는 리뷰 최신순으로 조회 jk 2022/10/01
	public List<StoreInfoVO> store_Review(int store_code) {
		return sql.selectList("store.store_review", store_code);
	}



	//가게 평점 높은 순 조회 jk - 2022/10/02
	public List<StoreInfoVO> store_Max(int store_code) {
		return sql.selectList("store.store_max", store_code);
	}
	
	//가게 리뷰 평점 낮은 순 조회 jk - 2022/10/02
	public List<StoreInfoVO> store_Min(int store_code) {
		return sql.selectList("store.store_min", store_code);
	}
	
}
