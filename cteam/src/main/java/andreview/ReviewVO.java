package andreview;

import java.util.List;



public class ReviewVO {

//	star_code, id, store_code, star_rating, clean, taste, mood, kind, writedate, review_content
   
  private int star_code, id, store_code, cnt;
  private float star_rating, clean, taste, mood, kind;
  private String  review_content, store_name, review_image;
  private String writedate;
  

  //private List<NoticeVO> list;
  private List<FileVO> fileInfo;
  
  
  
  

public List<FileVO> getFileInfo() {
	return fileInfo;
}
public void setFileInfo(List<FileVO> fileInfo) {
	this.fileInfo = fileInfo;
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
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
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
public String getReview_content() {
	return review_content;
}
public void setReview_content(String review_content) {
	this.review_content = review_content;
}
public String getStore_name() {
	return store_name;
}
public void setStore_name(String store_name) {
	this.store_name = store_name;
}
public String getReview_image() {
	return review_image;
}
public void setReview_image(String review_image) {
	this.review_image = review_image;
}
public String getWritedate() {
	return writedate;
}
public void setWritedate(String writedate) {
	this.writedate = writedate;
}
  
  
  
  
}
