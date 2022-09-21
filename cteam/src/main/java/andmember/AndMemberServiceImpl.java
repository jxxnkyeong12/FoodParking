package andmember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AndMemberServiceImpl implements AndMemberService {

	@Autowired private AndMemberDAO dao;
	
	
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public MemberVO member_login(MemberVO vo) {
//		return dao.member_login(vo);
//	}

	@Override
	public boolean member_id_check(String userid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String member_salt(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_reset_password(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_userid_email(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO member_login(MemberVO vo, String social) {
		return dao.member_login(vo, social);
	}

}
