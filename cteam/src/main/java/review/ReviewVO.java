package review;

import java.util.Date;

public class ReviewVO {

	
  private Date writedate;
  private String content, store_name, store_imgae;
  private int star_rating, celan, taste, mood, kind;
  
  
  
public int getStar_rating() {
	return star_rating;
}
public void setStar_rating(int star_rating) {
	this.star_rating = star_rating;
}
public int getCelan() {
	return celan;
}
public void setCelan(int celan) {
	this.celan = celan;
}
public int getTaste() {
	return taste;
}
public void setTaste(int taste) {
	this.taste = taste;
}
public int getMood() {
	return mood;
}
public void setMood(int mood) {
	this.mood = mood;
}
public int getKind() {
	return kind;
}
public void setKind(int kind) {
	this.kind = kind;

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
