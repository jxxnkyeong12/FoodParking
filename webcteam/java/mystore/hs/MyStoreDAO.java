package mystore.hs;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import home.StoreInfoVO;

@Repository
public class MyStoreDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;

	public MyStoreInfoVO store_detail(int id) {
		MyStoreInfoVO vo = sql.selectOne("mystore.detail", id);
		int store_code = vo.getStore_code();
		vo.setLogoInfo(sql.selectList("mystore.logo_info", store_code));
		vo.setMenuInfo(sql.selectList("mystore.menu_info", store_code));
		vo.setImageInfo(sql.selectList("mystore.image_info", store_code));
		return vo;
	}

	public int store_update(MyStoreInfoVO vo) {
		return sql.update("mystore.update", vo);
	}

	public List<StoreCategoryVO> category_list() {
		return sql.selectList("mystore.category_list");
	}

	public int logo_update(LogoFileInfoVO logo) {
		return sql.update("mystore.logo_update", logo);
	}

	public int image_update(StoreFileInfoVO image) {
		return sql.update("mystore.image_update", image);
	}

	public int menu_delete(MenuInfoVO vo) {
		return sql.delete("mystore.menu_delete", vo);
	}

	public int menu_add(MenuInfoVO vo) {
		return sql.insert("mystore.menu_add", vo);
	}

	public int close_store(int store_code, String pw) {
		int pw_check = sql.selectOne("mystore.pw_check",pw);
		if(pw_check == 1) {
			sql.delete("mystore.close_store", store_code);
		}
		return 0;
	}

	public void logo_insert(LogoFileInfoVO logoInfo) {
		sql.insert("mystore.logo_insert", logoInfo);
	}

	public void logo_default(int store_code) {
		sql.insert("mystore.logo_default", store_code);
	}

	public void image_insert(StoreFileInfoVO imageInfo) {
		sql.insert("mystore.image_insert", imageInfo);
	}
	
	public void image_default(int store_code) {
		sql.insert("mystore.image_default", store_code);
	}
}
