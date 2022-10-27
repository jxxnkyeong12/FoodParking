package com.hanul.webcteam;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.CommonService;
import home.StoreInfoVO;
import member.ssb.MemberVO;
import mystore.hs.LogoFileInfoVO;
import mystore.hs.MenuInfoVO;
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
	public String store_update(MyStoreInfoVO vo, Model model, HttpServletRequest req) {
		MyStoreInfoVO store = dao.store_detail(vo.getId());
		LogoFileInfoVO logoInfo = new LogoFileInfoVO();
		StoreFileInfoVO imageInfo = new StoreFileInfoVO();
		
		String orig_logo = store.getStore_logo();
		String orig_store = store.getStore_image();
		
		MultipartRequest mRequest = (MultipartRequest) req;
		MultipartFile logo_img = mRequest.getFile("logo_img");
		MultipartFile store_img = mRequest.getFile("store_img");
		
		if(orig_logo.contains("default")) { //파일이 처음부터 없을 경우
			if(!logo_img.isEmpty()) { //파일을 첨부했을 때
				String new_logo = common.fileUpload("store_logo", logo_img, req);
				
				vo.setStore_logo(new_logo);
				logoInfo.setStore_code(vo.getStore_code());
				logoInfo.setStore_logo_name(logo_img.getOriginalFilename());
				logoInfo.setStore_logo_path(new_logo);
				
				dao.logo_insert(logoInfo);
				
			}else { //파일을 첨부하지 않은 경우
				vo.setStore_logo("http://121.147.215.12:3304/cteam/upload/store_logo/store_logo_default.png");
				dao.logo_default(vo.getStore_code()); //기본 이미지로 넣기
				
			}
		}else {
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
		}
		
		if(orig_store.contains("default")) {  //가게 이미지 첨부 X
			if(!store_img.isEmpty()) { //파일을 첨부했을 때
				String new_image = common.fileUpload("store_image", store_img, req);
				
				vo.setStore_image(new_image);
				imageInfo.setStore_code(vo.getStore_code());
				imageInfo.setStore_image_name(store_img.getOriginalFilename());
				imageInfo.setStore_image_path(new_image);
				dao.image_insert(imageInfo);
				
			}else { //파일을 첨부하지 않은 경우
				vo.setStore_image("http://121.147.215.12:3304/cteam/upload/store_logo/store_logo_default.png");
				dao.image_default(vo.getStore_code()); //기본 이미지로 넣기
			}
			
		}else { //가게 이미지 첨부됐을 때
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
		}
		
		dao.store_update(vo);
		return "redirect:mystore.st?id=" + vo.getId();
	}

	//가게 메뉴 추가
	@ResponseBody @RequestMapping("/menu_add.st")
	public int menu_add(MenuInfoVO vo, HttpServletRequest req, MultipartFile menu_upload_image) {
//		MultipartRequest mRequest = (MultipartRequest) req;
//		MultipartFile menu_image = mRequest.getFile("menu_image");
		
		if(!menu_upload_image.isEmpty() ) {
			vo.setMenu_image(common.fileUpload("menu_image", menu_upload_image, req));
		}
		return dao.menu_add(vo);
	}
	
	//가게 메뉴 삭제
	@ResponseBody @RequestMapping("/menu_delete.st")
	public int menu_delete(MenuInfoVO vo) {
		return dao.menu_delete(vo);
	}
	
	//폐업시 비밀번호 값 비교 후 가게 삭제
	@ResponseBody @RequestMapping("/close_store.st")
	public int close_store(String pw, int store_code) {
		return dao.close_store(store_code, pw);
	}
}