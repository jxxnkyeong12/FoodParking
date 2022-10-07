package com.example.team_project01.order;

import java.io.Serializable;

public class Order_infoVO implements Serializable {
    private int order_num, category_code, store_code, id, menu_cnt, price, order_state;
    private String order_date, phone, order_time, order_peple;


    public String getOrder_peple() {
        return order_peple;
    }

    public void setOrder_peple(String order_peple) {
        this.order_peple = order_peple;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
