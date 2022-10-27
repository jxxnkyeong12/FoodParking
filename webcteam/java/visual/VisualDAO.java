package visual;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class VisualDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public List<HashMap<String, Object>> total_price(int id) {
		return sql.selectList("visual.total_price", id);
	}
	


	public List<HashMap<String, Object>> day_total_price(int id) {
		return sql.selectList("visual.day_total_price", id);
	}

}
