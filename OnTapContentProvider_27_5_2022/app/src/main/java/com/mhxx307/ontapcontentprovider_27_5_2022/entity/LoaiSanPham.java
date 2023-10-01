package com.mhxx307.ontapcontentprovider_27_5_2022.entity;

public class LoaiSanPham {
    private int id;
    private String tenLoaiSanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, String tenLoaiSanPham) {
        this.id = id;
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }
}
