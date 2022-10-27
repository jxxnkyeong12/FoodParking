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
		return sql.selectOne("home.store_detail", store_code);
		
	}
	
	//홈에서 가게 상세 -> 가게 리뷰 나오게 jk 
	public List<ReviewVO> home_store_review(int store_code) {
		System.out.println("오긴하니");
		return sql.selectList("home.store_review_list", store_code);
	}
	
	//홈에서 가게 정보 - > 메뉴 이미지 띄우려고 
	public List<StoreInfoVO> home_store_menu(int store_code) {
		return sql.selectList("home.store_menu", store_code);
	}

	//내 가게 리뷰 신고(관리자 게시판에 insert) 요청 ->jk 
	public int admin_insert(String star_code) {
		System.out.println(star_code + "insert");
		return (Integer) sql.insert("home.admin_insert", star_code);
	}

	// 리뷰 신고 클릭시 중복 처리 jk
	public boolean admin_detail(String star_code) {
		System.out.println(star_code + "중복체크 뭐로오냐");
		return(Integer) sql.selectOne("home.admin_detail", star_code) == 1 ? true : false;
	}

	
}
