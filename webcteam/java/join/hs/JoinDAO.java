package join.hs;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public int member_join(JoinVO vo) {
		return sql.insert("join.join", vo);
	}

	public boolean member_email_check(String email) {
		return (Integer) sql.selectOne("join.email_check", email) == 1 ? true : false;
	}
}