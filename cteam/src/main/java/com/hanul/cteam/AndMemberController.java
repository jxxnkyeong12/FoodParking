package com.hanul.cteam;


import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	

	
	//회원탈퇴  jk - 2022/09/29
	@RequestMapping(value = "/andDelete", produces = "text/html;charset=utf-8")
	public String delete(String email) {
		System.out.println(email);
	    int delete = dao.delete(email);
		return "";
	}

	
	//내 정보 수정 - jk 2022/09/28
	@RequestMapping(value = "/andModify", produces = "text/html;charset=utf-8")
	public String modify (String vo,  HttpServletRequest request) {
		MemberVO modify = new Gson().fromJson(vo, MemberVO.class);
		
		
        MemberVO profile = dao.profile_image_detail(modify.getEmail());

        String imgpath =profile.getProfile_image();
        
		MultipartRequest mReq = (MultipartRequest) request;
		System.out.println(mReq);
		MultipartFile file = mReq.getFile("file");

		
		if(file != null) {
			imgpath = common.fileUpload("profile_image", file, request);
			System.out.println(imgpath);	

		}

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

		dao.modify(modify);
		return gson.toJson(modify);
	}
	
	
	
	//회원프로필 사진 저장 - jk 2022/09/28
		@RequestMapping(value = "/andInsert", produces = "text/html;charset=utf-8")
		public String insert(String vo, HttpServletRequest request) {
			MemberVO myinfo = gson.fromJson(vo, MemberVO.class);	
			String imgpath = "";


			
			MultipartRequest mReq = (MultipartRequest) request;
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
}
