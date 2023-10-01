package com.example.fragment_tk3_kieu2;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int hinh;
    private String sanpham;
    private String gia;
    private  String thongtin;

    public SanPham(int hinh, String sanpham, String gia) {
        this.hinh = hinh;
        this.sanpham = sanpham;
        this.gia = gia;
    }

    public SanPham(int hinh, String sanpham, String gia, String thongtin) {
        this.hinh = hinh;
        this.sanpham = sanpham;
        this.gia = gia;
        this.thongtin = thongtin;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getSanpham() {
        return sanpham;
    }

    public void setSanpham(String sanpham) {
        this.sanpham = sanpham;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}
