package mystore.hs;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MyStoreDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;

//	public List<MyStoreInfoVO> mystore_list(int id) {
//		return sql.selectList("mystore.list", id);
//	}

}
