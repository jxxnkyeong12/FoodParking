package notice.jk;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {
	
	  @Qualifier("cteam") @Autowired private SqlSession sql;
	
	public NoticePageVO notice_list(NoticePageVO vo) {
		vo.setTotalList( sql.selectOne("notice.totalList", vo));
		vo.setList( sql.selectList("notice.list",vo) ) ;
		return vo;
	}

	
}
