package andmember;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

  
@RestController
public class AndMemberDAO {
 
	@Autowired @Qualifier("cteam") private SqlSession sql;
	
	public MemberVO login(MemberVO vo, String social) {
		//여기서 분기
				if(social.equals("Y")) {
					return sql.selectOne("andmember.social_login", vo);
				}else {
					return sql.selectOne("andmember.login", vo);
					
				}
	}//login
	
	
	
	
}
