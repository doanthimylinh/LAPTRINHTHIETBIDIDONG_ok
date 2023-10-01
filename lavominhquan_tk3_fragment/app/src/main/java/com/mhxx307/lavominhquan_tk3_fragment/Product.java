package com.mhxx307.lavominhquan_tk3_fragment;

import java.io.Serializable;

public class Product implements Serializable {
    private int img;
    private String tenSanPham;
    private double giaSanPham;
    private String thongTinSanPham;

    public Product(int img, String tenSanPham, double giaSanPham) {
        this.img = img;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    public Product(int img, String tenSanPham, double giaSanPham, String thongTinSanPham) {
        this.img = img;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.thongTinSanPham = thongTinSanPham;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getThongTinSanPham() {
        return thongTinSanPham;
    }

    public void setThongTinSanPham(String thongTinSanPham) {
        this.thongTinSanPham = thongTinSanPham;
    }
}
