package com.hanul.cteam;


import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import andmember.AndMemberDAO;
import andmember.MemberVO;
import common.Common;

@RestController
public class AndMemberController {

	@Autowired AndMemberDAO dao;
	@Autowired Common common;
	Gson gson = new Gson();
	
	
	//로그인정보 변경
	@RequestMapping(value = "/andResume", produces = "text/html;charset=utf-8")
	public String resume(String email) {
		System.out.println(email);
		MemberVO vo = new MemberVO();
		vo.setEmail(email);
	     MemberVO resume =  dao.resume(vo);
		System.out.println(resume.getEmail());
		return gson.toJson(resume);
	}
	
	
	//회원탈퇴  jk - 2022/09/29
	@RequestMapping(value = "/andDelete", produces = "text/html;charset=utf-8")
	public String delete(String email) {
		System.out.println(email);
	    int delete = dao.delete(email);
		return "";
	}

	
	
	//내 정보 수정 사진변경 안하고 다른것만 수정할때 - jk 
	@RequestMapping(value = "/andModifyNofile", produces = "text/html;charset=utf-8")
	public String modifyNOfile(String vo, HttpServletRequest request) {
		MemberVO modifynofile = new Gson().fromJson(vo, MemberVO.class);
		System.out.println("파일수정안해서 그대로인 상태");
		
		MemberVO profile = dao.profile_image_detail(modifynofile.getEmail());
		String imgpath = profile.getProfile_image();
//	    int result	= dao.update(modifynofile);
	     dao.update(modifynofile);
		
	
		
		//이미지파일이 그대로인 경우
		if(imgpath.equals( modifynofile.getProfile_image() ) ) {
			  profile.getProfile_image();
			System.out.println(profile.getProfile_image());
			
			//기존파일 없애고 기본이미지로 했을때 이리 타야 하는데..
		}else if ( ! imgpath.equals(modifynofile.getProfile_image() ) ) {
				 common.removed_image(profile, request);
		}
		
		return gson.toJson(modifynofile);
		
	}
	
	
	//내 정보 수정 - jk 2022/09/28
	@RequestMapping(value = "/andModify", produces = "text/html;charset=utf-8")
	public String modify (String vo,  HttpServletRequest request) {
		MemberVO modify = new Gson().fromJson(vo, MemberVO.class);
		System.out.println("오긴하니");
		
        MemberVO profile = dao.profile_image_detail(modify.getEmail());
        String imgpath =profile.getProfile_image();
        
        
      
		MultipartRequest mReq = (MultipartRequest) request;
		System.out.println(mReq);
		MultipartFile file = mReq.getFile("file");
		
		imgpath = common.fileUpload("profile_image", file, request);
		System.out.println(imgpath);	

	
			//파일첨부하지 않은 경우
			if( file.isEmpty() ) {
				
					if( modify.getProfile_image() != null ) {
						File f = new File( imgpath ); 
						String path = "";
						path = f.getParentFile().toString();
						System.out.println(path);
				
						if( f.exists() ) f.delete();
					}else {
					//원래첨부파일을 그대로 쓰는 경우
					profile.setProfile_image(imgpath);
				}			
				
			}else {
			//파일첨부한 경우
				common.fileUpload("profile_image", file, request);
				//profile.setProfile_image(common.fileUpload("profile_image", file, request));
			
				//원래첨부파일이 있던 경우 물리적 파일도 삭제
				if( profile.getProfile_image() != null ) {
				      
					 common.removed_image(profile, request);
					}
				}
        
		modify.setProfile_image(imgpath);
		dao.update(modify);

		return gson.toJson(modify);
	}
	
	
	
	//회원프로필 사진 저장 - jk 2022/09/28
		@RequestMapping(value = "/andInsert", produces = "text/html;charset=utf-8")
		public String insert(String vo, HttpServletRequest request) {
			MemberVO myinfo = gson.fromJson(vo, MemberVO.class);	
			String imgpath = "";
			
			MultipartRequest mReq = (MultipartRequest) request;
			//System.out.println(mReq);
			MultipartFile file = mReq.getFile("file");
			
			if(file != null) { 
				imgpath = common.fileUpload("profile_image", file, request);
				System.out.println(imgpath);	

			}
			
			myinfo.setProfile_image(imgpath);
			 dao.join(myinfo);
			return gson.toJson(myinfo);
		}
	
		

		
	//로그인 (salt찾아서 로그인 하는 기능) - jk 2022/09/22
	@RequestMapping(value = "/andLogin", produces = "text/html;charset=utf-8")
	public String login(String email, String pw) {
		MemberVO vo = new MemberVO();
		System.out.println(email);
		vo.setEmail(email);
		vo.setPw(pw);
		
		vo = dao.login(vo);
		
		return gson.toJson(vo);
	}

	

	//회원가입 - hs + sb 
	@RequestMapping(value = "/andJoin", produces = "text/html;charset=utf-8")
	public String join(String vo) {
		MemberVO joinInfo = new Gson().fromJson(vo, MemberVO.class);
	
		// 소셜 로그인 회원가입
		if (joinInfo.getSocial().equals("Y")) {
			dao.social_join(joinInfo);
		} else {
			dao.join(joinInfo);
		}

		return gson.toJson(vo);
	
	}

	

	//이메일 중복체크 - hs
	@RequestMapping(value = "/andEmailChk", produces = "text/html;charset=utf-8")
	public String email_check(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		boolean result = dao.email_check(email);
		
		System.out.println(result);
		
		if(result == true) {
			return "있음";
		}else {
			return "없음";
		}
		
	}
	
	
	//비밀번호 찾기 이메일보내기
	
	@RequestMapping(value = "/andemailSend", produces = "text/html;charset=utf-8")
	public String emailsend(String email) {
	
		//임시비번생성
		String pw = UUID.randomUUID().toString();
	   pw = pw.substring( pw.lastIndexOf("-")+1);
		
		System.out.println(pw);
		
		
		
		HtmlEmail mail	= new HtmlEmail();
		mail.setHostName("smtp.mail.nate.com");//메일서버 지정
		mail.setCharset("utf-8");
		mail.setDebug(true); //메일전송과정 로그로 확인
		
		
		//로그인아이디, 비번 입력하기 (관리자 아이디,, 비번입력)
		mail.setAuthentication("sins1209","비번입력");
        mail.setSSLOnConnect(true);	//로그인버튼 누르기
		
        try {
			mail.setFrom("sins1209@nate.com", "관리자입니다");
			mail.addTo(email); //받는사람 이메일 지정
			
			
			mail.setSubject("비번재설정");//제목
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>임시비밀번호 발급</h3>");
			msg.append("<p>아이디 : ").append(email).append(  "</p>");
			msg.append("<p>발급된 임시 비밀번호로 로그인 후 비밀번호를 변경해주세요</p>");
			msg.append("<p><strong> ").append(pw).append(  "</strong></p>");
			msg.append("<hr>");
			msg.append("</body>");
			msg.append("</html>");
			
			mail.setHtmlMsg(msg.toString()); //내용
			
			mail.send();//메일 보내기버튼 누르기 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        return "";
	}

	
	
	
}
