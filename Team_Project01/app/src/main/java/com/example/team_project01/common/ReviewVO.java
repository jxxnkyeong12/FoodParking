package com.example.team_project01.common;

import java.util.Date;

public class ReviewVO {
	
	//star_code, id, store_code, writedate, content

  private Date writedate;
  private String content, store_name, store_imgae;
  
  
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
public String getStore_name() {
	return store_name;
}
public void setStore_name(String store_name) {
	this.store_name = store_name;
}
public String getStore_imgae() {
	return store_imgae;
}
public void setStore_imgae(String store_imgae) {
	this.store_imgae = store_imgae;
}
  
  
 
  
  
}
