package com.hanul.webcteam;


import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.ssb.MemberDAO;
import member.ssb.MemberVO;




@Controller
public class MemberController {
	@Autowired private MemberDAO dao;
	@Autowired private CommonService common;

	//카카오 로그인처리 요청
	@RequestMapping("/kakaologin")
	public String kakaologin(HttpServletRequest request) {
		//인가 코드 받기
		//https://kauth.kakao.com/oauth/authorize?response_type=code
		//&client_id=${REST_API_KEY}
		//&redirect_uri=${REDIRECT_URI}
		StringBuffer url 
		= new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(KAKAO_ID);
		url.append("&redirect_uri=").append( appName(request) ).append("/kakao_callback");
		return "redirect:" + url.toString();
	}
	
	@RequestMapping("/kakao_callback")
	public String kakao_callback(String code, String error, HttpSession session) {
		if( error!=null ) return "redirect:/";
		//토큰 받기
//		curl -v -X POST "https://kauth.kakao.com/oauth/token" \
//		 -d "grant_type=authorization_code" \
//		 -d "client_id=${REST_API_KEY}" \
//		 -d "code=${AUTHORIZE_CODE}"
		StringBuffer url
		= new StringBuffer(
				"https://kauth.kakao.com/oauth/token?grant_type=authorization_code");
		url.append("&client_id=").append(KAKAO_ID);
		url.append("&code=").append(code);
		JSONObject json = new JSONObject( common.requestAPI(url) );
		String token_type = json.getString("token_type");
		String access_token = json.getString("access_token");
		
		//사용자 정보 가져오기 - json
//		GET/POST /v2/user/me HTTP/1.1
//		Host: kapi.kakao.com
//		Authorization: Bearer ${ACCESS_TOKEN}
		url = new StringBuffer("https://kapi.kakao.com/v2/user/me");
		json = new JSONObject( common.requestAPI(url, token_type+ " "+access_token) );
		
		if( ! json.isEmpty() ) {
			
			//카카오 사용자정보를 회원정보로 담는다
			MemberVO vo = new MemberVO();
			vo.setSocial("K");
			vo.setEmail( json.get("id").toString() ); 
			
			json = json.getJSONObject("kakao_account");
			vo.setName( json.has("name") ? json.getString("name") : "");
			vo.setEmail( json.getString("email") );
			
			json = json.getJSONObject("profile");
			if( json.has("nickname") ) {
				vo.setName( json.getString("nickname") );
			}
			
			//카카오 아이디가 있는지 확인하여 
			//카카오로그인이 처음이면  insert(false), 아니면 update(true)
			if( dao.member_id_check(vo.getEmail()) ) {
				 dao.kakao_update(vo);
			}else {
				dao.kakao_join(vo);
			}
			//카카오로그인정보를 세션에 담는다
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/";
	}
	
	private String KAKAO_ID = "4142c6974d99dc64d6fd1dcea79dcb62";
	
	
	//네이버 로그인처리 요청
	@RequestMapping("/naverlogin")
	public String naverlogin(HttpSession session, HttpServletRequest r) {
		//로그인연동 url 생성하기
		//https://nid.naver.com/oauth2.0/authorize?response_type=code
		//&client_id=CLIENT_ID
		//&state=STATE_STRING
		//&redirect_uri=CALLBACK_URL
		String state = UUID.randomUUID().toString();
		session.setAttribute("state", state);
		
		StringBuffer url 
		= new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(NAVER_ID);
		url.append("&state=").append(state);
		url.append("&redirect_uri=").append( appName(r) ).append("/naver_callback");
		//동의항목 재동의요청
		url.append("&auth_type=reprompt");
		return "redirect:" + url.toString();
	}
	
	@RequestMapping("/naver_callback")
	public String naver_callback(String state, HttpSession session
								, String code, String error) {
//		Callback 응답 정보
//		API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
//		API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}&error_description={에러메시지}
		if( error!=null || !state.equals((String)session.getAttribute("state")) ) return "redirect:/"; 
		
		//접근 토큰 발급 요청
		//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		//&client_id=jyvqXeaVOVmV
		//&client_secret=527300A0_COq1_XV33cf
		//&code=EIc5bFrl4RibFls1
		//&state=9kgsGTfH4j7IyAkg  
		StringBuffer url 
		= new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(NAVER_ID);
		url.append("&client_secret=").append(NAVER_SECRET);
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		String result = common.requestAPI(url); //API요청
		
		JSONObject json = new JSONObject( result ); //API요청 응답결과가 json
		String access_token = json.getString("access_token");
		String token_type = json.getString("token_type");
		
		//접근 토큰을 이용하여 프로필 API 호출하기 - 응답결과 JSON
		//https://openapi.naver.com/v1/nid/me
		//Authorization: {토큰 타입] {접근 토큰]
		
		url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
		json = new JSONObject( common.requestAPI(url, token_type + " " + access_token) );

		//프로필 API 호출결과코드가 정상("00")인 경우 프로필정보에 접근
		if( json.getString("resultcode").equals("00") ) {
			
			//필요한 프로필정보는 response 에 있다 - json
			json = json.getJSONObject("response");
			
			MemberVO vo = new MemberVO();
			vo.setSocial("N");
			vo.setEmail( json.getString("email") );
			vo.setName(  json.has("nickname") ? json.getString("nickname")
											: json.getString("name") );
			vo.setProfile_image(json.has("profile_image")?json.getString("profile_image") : "");
			
			//DB의 회원정보에 네이버 아이디 정보가 있다면 회원정보를 update(true), 
			//							   없다면 회원정보를 insert(false)
			if( dao.member_id_check(vo.getEmail()) ) {
				 dao.naver_update(vo);
			}else {
				dao.naver_join(vo);
			}
			//로그인정보를 세션에 담는다
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/";
	}
	
	
	private String appName(HttpServletRequest r) {
		// RequestURL: http://localhost:8080/iot/naver_callback
		// ServletPath: /naver_callback
		// replace 후: http://localhost:8080/iot
		return r.getRequestURL().toString().replace(r.getServletPath(), "");
	}
	
	private String NAVER_ID = "xBT432tWWgd7WLBldMzn";
	private String NAVER_SECRET = "F34LhTm2RA";
	
	
	
	
	//회원탈퇴 처리
		@RequestMapping("/delete.mb")
		public String delete(MemberVO vo, HttpSession session) {
			dao.delete_member(vo);
			session.setAttribute("loginInfo", null);
			return "redirect:/";
		}
	
	
	
	
	//회원정보 수정 요청
	@RequestMapping("/update")
	public String modify(MemberVO vo, HttpSession session) {
		dao.getmodify(vo);
		return "redirect:/";
	}
	
	
	//회원정보 페이지 요청
	@RequestMapping("/myInfo")
	public String myInfo(HttpSession session) {
		//응답화면연결: 회원정보
		return "member/myInfo";
	}
	
	//로그인 화면 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}
	
	
	
	//지능형iot회원 로그인처리 요청
		@ResponseBody @RequestMapping("/memberlogin")
		public boolean login(String email, String pw, HttpSession session) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("email", email);
			map.put("pw", pw);
			MemberVO vo = dao.member_login(map);
			session.setAttribute("loginInfo", vo);
			
			return vo == null ? false : true;
		}
	
	
	//로그인 화면 요청
		@RequestMapping("/login.mb")
		public String login(HttpSession session) {
			session.setAttribute("category", "login");
			//응답화면연결:로그인화면
			return "member/login";
		}

	
}
