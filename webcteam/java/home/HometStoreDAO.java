package home;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HometStoreDAO {

	@Autowired @Qualifier("cteam") private SqlSession sql;

	//홈에서 가게 리스트 뿌리기 jk 
	public List<StoreInfoVO> home_store_enter() {
		return sql.selectList("home.store_list");
	}

	//홈에서 가게 하나 클릭했을때 그 가게 정보만 출력되게 -jk
	public StoreInfoVO home_store_detail(int store_code) {
		StoreInfoVO vo = new StoreInfoVO();  
		 vo = sql.selectOne("home.store_detail", store_code);
		 System.out.println(vo.getReview_cnt());
		return vo;
		
	}
	
	//홈에서 가게 정보 - > 메뉴 이미지 띄우려고 
	public List<StoreInfoVO> home_store_menu(int store_code) {
		return sql.selectList("home.store_menu", store_code);
	}
	
}
