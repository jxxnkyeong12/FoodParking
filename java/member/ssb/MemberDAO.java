package member.ssb;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.login", map);
	}

	public int getmodify(MemberVO vo) {
		return sql.update("member.member_update", vo);
	}

	public int delete_member(MemberVO vo) {
		return sql.delete("member.delete",vo);
	}

	///
	public boolean member_id_check(String email) {
		return (Integer)sql.selectOne("member.email_check", email)==1 ? true : false;
	}

	////
	public int member_update(MemberVO vo) {
		return sql.update("member.member_update", vo);
		
	}
	

	public int kakao_update(MemberVO vo) {
		return sql.update("member.kakao_update", vo);
	}

	public int kakao_join(MemberVO vo) {
		return sql.insert("member.kakao_join", vo);
		
	}

	public int naver_update(MemberVO vo) {
		return sql.update("member.naver_update", vo);
		
	}

	public int naver_join(MemberVO vo) {
		return sql.insert("member.naver_join", vo);
	}

	public int member_email(MemberVO vo) {
		return sql.selectOne("member.phone_email", vo);
	}

	public int member_reset_password(MemberVO vo) {
		return sql.update("member.reset_password", vo);
	}

	public String member_salt(String email) {
		return sql.selectOne("member.salt", email);
	}
	

	
}
