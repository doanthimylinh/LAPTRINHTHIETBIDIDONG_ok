package vn.com.linh.appa_provider2;

public class PhongBan {
    private int id;
    private String name;

    public PhongBan(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PhongBan() {
    }

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
}
