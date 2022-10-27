package home;

import java.sql.Date;

public class ReviewVO {
//star_code, id, review_image, store_code, star_rating, clean, taste, mood, kind, writedate, review_content
	private float star_rating, clean,  taste,mood, kind ;
	private int star_code, id, store_code;
	private String nickname, writedate, review_image, review_content, 
	                         profile_image, store_name;
	
	private Date com_writedate;
	
	
	
	
	
	public Date getCom_writedate() {
		return com_writedate;
	}
	public void setCom_writedate(Date com_writedate) {
		this.com_writedate = com_writedate;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public float getStar_rating() {
		return star_rating;
	}
	public void setStar_rating(float star_rating) {
		this.star_rating = star_rating;
	}
	public float getClean() {
		return clean;
	}
	public void setClean(float clean) {
		this.clean = clean;
	}
	public float getTaste() {
		return taste;
	}
	public void setTaste(float taste) {
		this.taste = taste;
	}
	public float getMood() {
		return mood;
	}
	public void setMood(float mood) {
		this.mood = mood;
	}
	public float getKind() {
		return kind;
	}
	public void setKind(float kind) {
		this.kind = kind;
	}
	public int getStar_code() {
		return star_code;
	}
	public void setStar_code(int star_code) {
		this.star_code = star_code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStore_code() {
		return store_code;
	}
	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	
	
	
}
