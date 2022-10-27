package mystore.hs;

import java.util.List;

public class MyStoreInfoVO {
	private int store_code, id, store_category, review_cnt, store_post;
	private float star_rating, clean, taste, mood, kind;
	private String store_name, store_tel, open_close, store_addr, store_comment, store_image, store_logo, nickname,
			content, review_content, writedate, store_logo_path, b_num, store_logo_name, addr_more, menu_name;

	private List<LogoFileInfoVO> logoInfo; //로고 정보 담을 리스트
	private List<MenuInfoVO> menuInfo; //메뉴 정보 담을 리스트
	private List<StoreCategoryVO> categoryInfo; //카테고리 리스트
	private List<StoreFileInfoVO> imageInfo; //가게 사진 담을 리스트
	
	
	
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public List<StoreFileInfoVO> getImageInfo() {
		return imageInfo;
	}

	public void setImageInfo(List<StoreFileInfoVO> imageInfo) {
		this.imageInfo = imageInfo;
	}

	public List<StoreCategoryVO> getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(List<StoreCategoryVO> categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public List<MenuInfoVO> getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(List<MenuInfoVO> menuInfo) {
		this.menuInfo = menuInfo;
	}

	public String getAddr_more() {
		return addr_more;
	}

	public void setAddr_more(String addr_more) {
		this.addr_more = addr_more;
	}

	public int getStore_post() {
		return store_post;
	}

	public void setStore_post(int store_post) {
		this.store_post = store_post;
	}

	public List<LogoFileInfoVO> getLogoInfo() {
		return logoInfo;
	}

	public void setLogoInfo(List<LogoFileInfoVO> logoInfo) {
		this.logoInfo = logoInfo;
	}

	public String getStore_logo_name() {
		return store_logo_name;
	}

	public void setStore_logo_name(String store_logo_name) {
		this.store_logo_name = store_logo_name;
	}

	public String getB_num() {
		return b_num;
	}

	public void setB_num(String b_num) {
		this.b_num = b_num;
	}

	public int getReview_cnt() {
		return review_cnt;
	}

	public void setReview_cnt(int review_cnt) {
		this.review_cnt = review_cnt;
	}

	public String getStore_logo_path() {
		return store_logo_path;
	}

	public void setStore_logo_path(String store_logo_path) {
		this.store_logo_path = store_logo_path;
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

	public int getStore_code() {
		return store_code;
	}

	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStore_category() {
		return store_category;
	}

	public void setStore_category(int store_category) {
		this.store_category = store_category;
	}

	public float getStar_rating() {
		return star_rating;
	}

	public void setStar_rating(float star_rating) {
		this.star_rating = star_rating;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_tel() {
		return store_tel;
	}

	public void setStore_tel(String store_tel) {
		this.store_tel = store_tel;
	}

	public String getOpen_close() {
		return open_close;
	}

	public void setOpen_close(String open_close) {
		this.open_close = open_close;
	}

	public String getStore_addr() {
		return store_addr;
	}

	public void setStore_addr(String store_addr) {
		this.store_addr = store_addr;
	}

	public String getStore_comment() {
		return store_comment;
	}

	public void setStore_comment(String store_comment) {
		this.store_comment = store_comment;
	}

	public String getStore_image() {
		return store_image;
	}

	public void setStore_image(String store_image) {
		this.store_image = store_image;
	}

	public String getStore_logo() {
		return store_logo;
	}

	public void setStore_logo(String store_logo) {
		this.store_logo = store_logo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
