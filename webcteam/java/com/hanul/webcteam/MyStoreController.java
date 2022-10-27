package com.hanul.webcteam;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.CommonService;
import member.ssb.MemberVO;
import mystore.hs.LogoFileInfoVO;
import mystore.hs.MyStoreDAO;
import mystore.hs.MyStoreInfoVO;
import mystore.hs.StoreFileInfoVO;

import mystore.hs.MyStoreDAO;


@Controller
public class MyStoreController {
	@Autowired private MyStoreDAO dao;
	@Autowired private CommonService common;

	//가게 상세화면 요청
	@RequestMapping("/mystore.st")
	public String mystore(Model model, HttpSession session) {
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		int id = loginInfo.getId();
		
		MyStoreInfoVO vo = dao.store_detail(id);
		model.addAttribute("vo", vo);
	
		session.setAttribute("category", "mystore");
		return "store/info";
	}
	
	//수정화면 요청
	@RequestMapping("/modify.st")
	public String modify(Model model, HttpSession session) {
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		int id = loginInfo.getId();
				
		MyStoreInfoVO vo = dao.store_detail(id);
		vo.setCategoryInfo(dao.category_list());
		
		model.addAttribute("vo", vo);
		model.addAttribute("category", dao.category_list());
	
		return "store/update";
	}
	
	//수정 저장 처리
	@RequestMapping("/update.st")
	public String store_update(MyStoreInfoVO vo, Model model, String filename, HttpServletRequest req) {
		MyStoreInfoVO store = dao.store_detail(vo.getId());
		LogoFileInfoVO logoInfo = new LogoFileInfoVO();
		StoreFileInfoVO imageInfo = new StoreFileInfoVO();
		
		String orig_logo = store.getStore_logo();
		String orig_store = store.getStore_image();
		
		MultipartRequest mRequest = (MultipartRequest) req;
		MultipartFile logo_img = mRequest.getFile("logo_img");
		MultipartFile store_img = mRequest.getFile("store_img");
		
		if(!logo_img.isEmpty()) {//파일을 첨부했을 때
			if(store.getStore_logo() != null) { //사진 바꾸기
				orig_logo = common.fileUpload("store_logo", logo_img, req);
				
				vo.setStore_logo(orig_logo);
				
				//로고테이블 정보 수정
				logoInfo.setStore_code(vo.getStore_code());
				logoInfo.setStore_logo_name(logo_img.getOriginalFilename());
				logoInfo.setStore_logo_path(orig_logo);
				
				File f = new File(orig_logo); 
				String path = "";
				path = f.getParentFile().toString();
				if(f.exists()) f.delete();
				
				dao.logo_update(logoInfo);
			}else { //사진 그대로 쓰는 경우
				vo.setStore_logo(orig_logo);
			}
		}else {
			vo.setStore_logo(orig_logo);
		}
		
		if(!store_img.isEmpty()) {
			if(store.getStore_image() != null) {
				orig_store = common.fileUpload("store_image", store_img, req);
				
				vo.setStore_image(orig_store);
				
				imageInfo.setStore_code(vo.getStore_code());
				imageInfo.setStore_image_name(store_img.getOriginalFilename());
				imageInfo.setStore_image_path(orig_store);
				
				File f = new File(orig_store); 
				String path = "";
				path = f.getParentFile().toString();
				if(f.exists()) f.delete();
				
				dao.image_update(imageInfo);
			}else {
				vo.setStore_image(orig_store);
			}
		}else {
			vo.setStore_image(orig_store);
		}
		
		dao.store_update(vo);
		
		return "redirect:mystore.st?id=" + vo.getId();
	}

	
}

