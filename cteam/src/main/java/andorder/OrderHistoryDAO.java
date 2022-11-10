package andorder;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class OrderHistoryDAO {

	@Autowired @Qualifier("cteam") private SqlSession sql;

	//주문내역 리스트 jk - 2022/10/04
	public List<OrderHistoryVO> list(int id) {
		return sql.selectList("andorderhistory.list", id);
	}
	
	
	
}
