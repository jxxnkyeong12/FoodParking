package reserve;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import common.Common;

@Repository
public class OrderInfoDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	@Autowired private Common common;
	
	//주문상태 테이블에 예약 추가
	public int insert_order_info(Order_infoVO vo) {
		return sql.insert("orderinfo.order_info_insert", vo);
	}

	//주문정보 상세 조회
	public List<Order_infoVO> order_detail(Order_infoVO vo) {
		return sql.selectList("orderinfo.order_detail", vo);
	}
	
}
