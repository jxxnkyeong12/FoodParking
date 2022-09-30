package andstore;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AndSearchDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;

	public List<AndSearchVO> menu_list() {
		return sql.selectList("andSearch.list");
	}
}