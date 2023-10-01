package vn.com.linh.appb_provider2;

public class NhanVien {
    private int id;
    private String name;
    private String address;
    private PhongBan phongBan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public NhanVien() {
    }

    public NhanVien(int id, String name, String address, PhongBan phongBan) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phongBan = phongBan;
    }
}
