package com.example.team_project01.list;

import java.io.Serializable;

public class Store_infoDTO implements Serializable {
    private int store_code, id, store_category;
    private String  store_name, store_tel, open_close, store_addr, store_comment, store_image, store_logo;


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
}
