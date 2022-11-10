package iot;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class IotDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public List<IotVO> getTable_history(String table) {
		return sql.selectList("iot.getTable_history", table);
	}

	public List<IotVO> getTable(String table) {
		return sql.selectList("iot.getTable", table);
	}

}
