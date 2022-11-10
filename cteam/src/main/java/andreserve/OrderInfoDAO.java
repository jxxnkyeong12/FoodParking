package andreserve;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import andorder.OrderHistoryVO;
import andorder.OrderInfoVO;
import common.Common;

@Repository
public class OrderInfoDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	@Autowired private Common common;
	
	//주문상태 테이블에 예약 추가
	public int insert_order_info(Order_infoVO vo) {
		return sql.insert("orderinfo.order_info_insert", vo);
	}

	public List<OrderInfoVO> select_order_info(int id) {
		return sql.selectList("orderinfo.order_info_select", id);
	}

	public int delete_order_info(int id) {
		return sql.delete("orderinfo.order_info_delete", id);
	}

	public int insert_order_history(OrderHistoryVO vo) {
		return sql.insert("orderinfo.order_history_insert", vo);
	}

	
	
}
