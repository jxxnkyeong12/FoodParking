package mystore.hs;

public class MenuInfoVO {
	private int store_code, menu_code, price, menu_id;
	private String menu_image, menu_name;

	public int getStore_code() {
		return store_code;
	}

	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}

	public int getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(int menu_code) {
		this.menu_code = menu_code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_image() {
		return menu_image;
	}

	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

}