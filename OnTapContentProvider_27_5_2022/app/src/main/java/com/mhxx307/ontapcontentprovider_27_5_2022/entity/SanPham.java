package com.mhxx307.ontapcontentprovider_27_5_2022.entity;

public class SanPham {
    private int id;
    private String tenSanPham;
    private double giaTien;
    private String chatLuongSanPham;
    private LoaiSanPham loaiSanPham;

    public SanPham() {
    }

    public SanPham(int id, String tenSanPham, double giaTien, String chatLuongSanPham, LoaiSanPham loaiSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.chatLuongSanPham = chatLuongSanPham;
        this.loaiSanPham = loaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getChatLuongSanPham() {
        return chatLuongSanPham;
    }

    public void setChatLuongSanPham(String chatLuongSanPham) {
        this.chatLuongSanPham = chatLuongSanPham;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
}
