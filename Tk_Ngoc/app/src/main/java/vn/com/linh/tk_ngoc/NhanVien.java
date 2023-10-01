package vn.com.linh.tk_ngoc;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private  int id;
    private String hoten;
    private  int sdt;
    private String diachi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public NhanVien() {
    }

    public NhanVien(int id, String hoten, int sdt, String diachi) {
        this.id = id;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public NhanVien(String hoten, int sdt, String diachi) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "id=" + id +
                ", hoten='" + hoten + '\'' +
                ", sdt=" + sdt +
                ", diachi='" + diachi + '\'' +
                '}';
    }
}
