package com.mhxx307.democontentprovider_appa_onetomany.entity;

public class PhongBan {
    private int id;
    private String tenPhongBan;

    public PhongBan() {
    }

    public PhongBan(int id, String tenPhongBan) {
        this.id = id;
        this.tenPhongBan = tenPhongBan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
