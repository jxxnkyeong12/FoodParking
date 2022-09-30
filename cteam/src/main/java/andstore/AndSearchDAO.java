package andstore;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import storeinfo.StoreInfoVO;

@Repository
public class AndSearchDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;

	public List<AndSearchVO> search_list() {
		return sql.selectList("andSearch.search_list");
	}

	public AndMapVO store_map(int store_code) {
		return sql.selectOne("andSearch.store_map", store_code);
	}


}