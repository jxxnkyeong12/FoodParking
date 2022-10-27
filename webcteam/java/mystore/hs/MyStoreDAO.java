package mystore.hs;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

}
