package storeinfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import common.Common;

@RestController
public class StoreInfoDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	@Autowired private Common common;
	
	
	
	public List<StoreInfoVO> store_list() {
		return sql.selectList("store.list");
	}



	public List<StoreMenuVO> store_menu_list(int store_code) {
		return sql.selectList("store.menu_list", store_code);
	}


}
