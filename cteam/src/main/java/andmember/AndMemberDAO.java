package andmember;




import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.RestController;

import common.Common;

  
@RestController
public class AndMemberDAO {
 
	@Autowired @Qualifier("cteam") private SqlSession sql;
	@Autowired private Common common;
	
	
	//로그인 
	public MemberVO login(MemberVO vo, String social) {
		//여기서 분기
				if(social.equals("Y")) {
					return sql.selectOne("andmember.social_login", vo);
				}else {
					return sql.selectOne("andmember.login", vo);
					
				}
				
				
	}//login
	
	//로그인시 salt 찾아주기
	public String member_salt (String email) {
		return sql.selectOne("andmember.salt", email);
	}
	
	
	//회원가입
	public int join(MemberVO vo) {
			String salt = common.generateSalt();
			String pw = common.getEncrypt(salt, vo.getPw()); //데이터 객체에 담겨왔을 pw!
			vo.setSalt(salt);
			vo.setPw(pw);
		  
			return sql.insert("andmember.and_join", vo);
	}
	
	
	//소셜 회원가입
	public int social_join(MemberVO vo) {
		return sql.insert("andmember.social_join", vo);
	}
	
	
	//소셜 아이디 중복체크
	public int social_email_check(MemberVO vo) {
		// 결과값이 1이상이면 true , 아니면 false
		return (Integer)sql.selectOne("andmember.social_id_check", vo);
	}

}
