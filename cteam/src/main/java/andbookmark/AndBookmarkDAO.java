package andbookmark;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AndBookmarkDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public int bookmark_insert(AndBookmarkVO vo) {
		return sql.insert("andBookmark.bookmark", vo);
	}

	public void bookmark_delete(AndBookmarkVO vo) {
		sql.delete("andBookmark.delete", vo);
	}

	public List<AndBookmarkVO> bookmark_list(String id) {
		return sql.selectList("andBookmark.list", id);
	}

	
}