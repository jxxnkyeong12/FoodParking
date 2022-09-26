package andmember;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import common.Common;

@Repository
public class AndMemberDAO {

	@Autowired @Qualifier("cteam") private SqlSession sql;
	@Autowired private Common common;

	// 로그인
	public MemberVO login(MemberVO vo) {
		return sql.selectOne("andmember.login", vo);
	}// login

	
	// 로그인시 salt 찾아주기
	public String member_salt(String email) {
		return sql.selectOne("andmember.salt", email);
	}

	
	// 회원가입 - hs
	public int join(MemberVO vo) {
		String salt = common.generateSalt();
		String pw = common.getEncrypt(salt, vo.getPw()); // 데이터 객체에 담겨왔을 pw!
		vo.setSalt(salt);
		vo.setPw(pw);

		return sql.insert("andmember.and_join", vo);
	}

	public int social_join(MemberVO vo) {
		return sql.insert("andmember.social_join", vo);
	}

	
	//이메일 체크 - hs
	public boolean email_check(String email) {
		//return (Integer) sql.selectOne("andmember.email_check", email) == 1 ? true : false;
		int result = (Integer) sql.selectOne("andmember.email_check", email);
		return result == 1 ? true : false;
	}

}
