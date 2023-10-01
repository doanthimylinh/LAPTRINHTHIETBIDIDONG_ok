package vn.com.linh.appa_provider4;

public class SinhVien {
    private int id;
    private String tenHS;
    private LopHoc lopHoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHS() {
        return tenHS;
    }

    public void setTenHS(String tenHS) {
        this.tenHS = tenHS;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public SinhVien(int id, String tenHS, LopHoc lopHoc) {
        this.id = id;
        this.tenHS = tenHS;
        this.lopHoc = lopHoc;
    }

    public SinhVien() {
    }
}
