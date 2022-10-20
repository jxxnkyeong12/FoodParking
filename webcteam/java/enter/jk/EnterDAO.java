package enter.jk;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EnterDAO {
  
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
			//사업자등록번호 중복체크 -jk
			public boolean b_num_check(String b_num) {
				return (Integer) sql.selectOne("enter.b_num_check",b_num) ==1? true: false;
			}

			//입점신청 등록 - jk
			public int enter_insert(EnterVO vo) {
				return sql.insert("enter.insert", vo);
			}
	
}
