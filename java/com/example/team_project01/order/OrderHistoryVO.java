package com.example.team_project01.order;

public class OrderHistoryVO {

    private String menu_image, store_name, menu_name, total_info, order_date;
    private int  price , total_price, id, store_code, order_state, category_code, order_num, menu_cnt;


    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
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
    public int getOrder_state() {
        return order_state;
    }
    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }
    public int getCategory_code() {
        return category_code;
    }
    public void setCategory_code(int category_code) {
        this.category_code = category_code;
    }
    public int getOrder_num() {
        return order_num;
    }
    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }
    public int getMenu_cnt() {
        return menu_cnt;
    }
    public void setMenu_cnt(int menu_cnt) {
        this.menu_cnt = menu_cnt;
    }
    public String getTotal_info() {
        return total_info;
    }
    public void setTotal_info(String total_info) {
        this.total_info = total_info;
    }
    public String getMenu_image() {
        return menu_image;
    }
    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }
    public String getStore_name() {
        return store_name;
    }
    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    public String getMenu_name() {
        return menu_name;
    }
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getTotal_price() {
        return total_price;
    }
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }


}
