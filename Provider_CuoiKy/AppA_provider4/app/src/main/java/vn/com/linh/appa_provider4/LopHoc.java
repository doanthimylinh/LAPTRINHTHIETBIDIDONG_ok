package vn.com.linh.appa_provider4;

public class LopHoc {
    private int id;
    private String tenLop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public LopHoc() {
    }

    public LopHoc(int id, String tenLop) {
        this.id = id;
        this.tenLop = tenLop;
    }
}
