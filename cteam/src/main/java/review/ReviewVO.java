package review;

import java.util.Date;

public class ReviewVO {
	
	//star_code, id, store_code, writedate, content
  private int star_code, id, store_code;
  private Date writedate;
  private String content;
  
  
  
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
public Date getWritedate() {
	return writedate;
}
public void setWritedate(Date writedate) {
	this.writedate = writedate;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
  
  
  
}
