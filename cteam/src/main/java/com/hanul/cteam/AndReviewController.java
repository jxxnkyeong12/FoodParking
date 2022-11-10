package com.hanul.cteam;




import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;


import andreview.ReviewDAO;
import andreview.ReviewVO;
import common.Common;


@RestController
public class AndReviewController {
		@ Autowired ReviewDAO dao;;
		@Autowired Common common;
		Gson gson = new Gson();

		
		
		
		
		
		
		//리뷰 삭제 - 리뷰 아까워서 일단 보류 즁 -jk
		public String delete (int id) {
			 dao.delete(id);
			return "";
		}
		
		
		
/*
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
	

 		
 */
		//리뷰 이미지 변경 안하고 다른것만 수정 jk
		@RequestMapping(value = "/andReviewUpdateNofile", produces = "text/html;charset=utf-8")
		public String ReviewNofile(String vo, HttpServletRequest re) {
			ReviewVO review_update_nofile = new Gson().fromJson(vo, ReviewVO.class);
			System.out.println("리뷰파일 그대로 있음");
			
			ReviewVO review = dao.review_image_detail(review_update_nofile);
			String imgpath = review.getReview_image();
			dao.review_update(review_update_nofile);
			//이미지파일이 그대로인 경우
			if(imgpath.equals( review_update_nofile.getReview_image() ) ) {
				  review.getReview_image();
				System.out.println( review.getReview_image());
				
				//기존파일 없애고 기본이미지로 했을때
			}else if ( ! imgpath.equals(review_update_nofile.getReview_image()) ) {
					 common.removed_review_img(review, re);
			}
			
			return gson.toJson(review_update_nofile);
			
		}
		
		
		
		
		//내 리뷰 수정 -> update jk
		@RequestMapping(value = "/andReviewUpdate", produces = "text/html;charset=utf-8")
		public String review_update(String vo, HttpServletRequest request) {
			ReviewVO  update = gson.fromJson(vo, ReviewVO.class);
			
			

	        ReviewVO review = dao.review_image_detail(update);
	        String imgpath =review.getReview_image();
	        

			MultipartRequest mReq = (MultipartRequest) request;
			System.out.println(mReq);
			MultipartFile file = mReq.getFile("file");
			
			imgpath = common.fileUpload("review_image", file, request);
			System.out.println(imgpath);	

		
				//파일첨부하지 않은 경우
				if( file.isEmpty() ) {
					
						if( update.getReview_image() != null ) {
							File f = new File( imgpath ); 
							String path = "";
							path = f.getParentFile().toString();
							System.out.println(path);
					
							if( f.exists() ) f.delete();
						}else {
						//원래첨부파일을 그대로 쓰는 경우
						update.setReview_image(imgpath);
					}			
					
				}else {
				//파일첨부한 경우
					common.fileUpload("review_image", file, request);
					//원래첨부파일이 있던 경우 물리적 파일도 삭제
					if( review.getReview_image() != null ) {
						 common.removed_review_img(review, request);
						}
					}
	        
			update.setReview_image(imgpath);
			dao.review_update(update);

			return gson.toJson(update);
		}

	//내 리뷰목록에서 리뷰를 클릭했을때 ! 리뷰 detail - jk 2022/10/02
		@RequestMapping(value = "/andReviewDetail", produces = "text/html;charset=utf-8")
		public String review_detail(ReviewVO vo) {
			ReviewVO detail = new ReviewVO();
			detail = dao.review_detail(vo);
			return gson.toJson(detail);
			
		}
		
		//리뷰 작성 -jk (리뷰이미지 없이 정보만 넘어온 경우)
	@RequestMapping(value = "/andReviewInsert", produces = "text/html;charset=utf-8")
	public String review_insert(String vo, HttpServletRequest request) {
		ReviewVO  insert = gson.fromJson(vo, ReviewVO.class);
		String imgpath = "";
		
		MultipartRequest mReq = (MultipartRequest) request;
		MultipartFile file = mReq.getFile("file");
		
		if(file != null) { 
			imgpath = common.fileUpload("review_image", file, request);
			System.out.println(imgpath);	

		}
		insert.setReview_image(imgpath);
		
		
		int result = dao.insert(insert);
		System.out.println(result);
		return gson.toJson(result);
	}
	
}
