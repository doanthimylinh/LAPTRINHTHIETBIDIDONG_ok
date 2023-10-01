package com.mhxx307.democontentprovider_appa_onetomany.entity;

public class NhanVien {
    private int id;
    private String tenNhanVien;
    private PhongBan phongBan;

    public NhanVien() {
    }

    public NhanVien(int id, String tenNhanVien, PhongBan phongBan) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.phongBan = phongBan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }
}
