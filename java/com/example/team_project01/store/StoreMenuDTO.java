package com.example.team_project01.store;

import java.io.Serializable;

public class StoreMenuDTO implements Serializable {
    private int menu_id, store_code, menu_code, price;
<<<<<<< HEAD:Team_Project01/app/src/main/java/com/example/team_project01/store/StoreMenuDTO.java
    private String menu_image, menu_name;


=======
    private String menu_image, menu_name, store_image;

    public String getStore_image() {
        return store_image;
    }

    public void setStore_image(String store_image) {
        this.store_image = store_image;
    }
>>>>>>> 9cb002ba616cae74e12e5a50d2a549397d4d6f88:java/com/example/team_project01/store/StoreMenuDTO.java

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

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