package mystore.hs;

public class StoreFileInfoVO {
	private int store_code, store_image_num;
	private String store_image_name, store_image_path;

	public int getStore_image_num() {
		return store_image_num;
	}

	public void setStore_image_num(int store_image_num) {
		this.store_image_num = store_image_num;
	}

	public int getStore_code() {
		return store_code;
	}

	public void setStore_code(int store_code) {
		this.store_code = store_code;
	}

	public String getStore_image_name() {
		return store_image_name;
	}

	public void setStore_image_name(String store_image_name) {
		this.store_image_name = store_image_name;
	}

	public String getStore_image_path() {
		return store_image_path;
	}

	public void setStore_image_path(String store_image_path) {
		this.store_image_path = store_image_path;
	}

}