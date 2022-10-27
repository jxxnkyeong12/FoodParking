package enter.jk;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import home.ReviewVO;

@Repository
public class EnterDAO {


	@Autowired
	@Qualifier("cteam")
	private SqlSession sql;

	// 사업자등록번호 중복체크 -jk
	public boolean b_num_check(String b_num) {
		return (Integer) sql.selectOne("enter.b_num_check", b_num) == 1 ? true : false;
	}

	
	
	// 입점신청 등록 - jk
	public int enter_insert(EnterVO vo) {
		//sql.insert("enter.insert_store_loge", vo);
		return sql.insert("enter.insert", vo);
	}

	// 매니저모드 입점신청한 가게 불러오기 - ssb
	public List<EnterVO> admin_store() {
		return sql.selectList("enter.admin_store_list");
	}

	public EnterVO notice_detail(int id) {
		return sql.selectOne("enter.detail", id);
	}

	public int admin_store_cancle(int id) {
		return sql.delete("enter.store_delete", id);
	}

	public int admin_make_store(EnterVO vo) {
		return sql.insert("enter.store_insert", vo);
	}

	public int update_member_manager(int id) {
		return sql.update("enter.member_manager", id);
	}

	public int update_enter_status(int id) {
		return sql.update("enter.enter_status", id);
	}


	//리뷰 삭제요청
	public List<ReviewVO> admin_review_list() {
		return sql.selectList("admin_review.admin_review_list");
	}


	//리뷰 삭제 확인
	public int admin_delete_review(int star_code) {
		sql.delete("admin_review.delete", star_code);
		sql.update("admin_review.status", star_code);
		return 0;
	}

}
