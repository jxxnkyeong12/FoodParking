package com.example.team_project01.common;

import java.io.Serializable;

public class BasketVO implements Serializable {
    private int category_code, store_code, id, menu_cnt, menu_code, menu_price;



    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public int getCategory_code() {
        return category_code;
    }

    public void setCategory_code(int category_code) {
        this.category_code = category_code;
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

    public int getMenu_cnt() {
        return menu_cnt;
    }

    public void setMenu_cnt(int menu_cnt) {
        this.menu_cnt = menu_cnt;
    }

    public int getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(int menu_code) {
        this.menu_code = menu_code;
    }
}
