package home;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HometStoreDAO {

	@Autowired @Qualifier("cteam") private SqlSession sql;

	public List<StoreInfoVO> home_store_enter() {
		return sql.selectList("home.store_list");
	}
	
}
