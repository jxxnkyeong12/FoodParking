package andmember;

import java.util.HashMap;

public interface AndMemberService {

	//CRDU ()
		int member_join(MemberVO vo); //회원가입시 회원정보 저장
		MemberVO member_login(MemberVO vo, String social); //로그인 처리
		boolean member_id_check(String userid); //회원가입시 아이디 중복확인 (아이디 존재여부)
		String member_salt(String userid); //회원의 salt 조회 회원의 아이디를 통해서 솔트정보
		int member_update (MemberVO vo); //마이페이지에서 회원정보변경 저장
		int member_reset_password(MemberVO vo);//비밀번호 변경
		int member_userid_email(MemberVO vo); //비밀번호 변경을 위한 아이디와 이메일 확인
		int member_delete(String userid); //회원 탈퇴시 회원정보 삭제
	
}
