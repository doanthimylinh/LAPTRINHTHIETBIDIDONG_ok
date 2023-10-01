package com.example.ui_demo;

import android.graphics.drawable.Drawable;

public class NhanVien {
    private String hinh;
    private int maSo;
    private String hoTen;
    private String gioiTinh;
    private String donVi;

    public NhanVien(String hinh, int maSo, String hoTen, String gioiTinh, String donVi) {
        this.hinh = hinh;
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
    }

    public NhanVien(int maSo, String hoTen, String gioiTinh, String donVi) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
    }

    public int getMaSo() {
        return maSo;
    }

    public void setMaSo(int maSo) {
        this.maSo = maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }


    @Override
    public String toString() {
        return "NhanVien{"
                +hinh +
                "Mã số: " + maSo +'\'' +
                ", Họ tên: " + hoTen + '\'' +
                ", Giới tính: " + gioiTinh + '\'' +
                ", Đơn vị: " + donVi;
    }
}