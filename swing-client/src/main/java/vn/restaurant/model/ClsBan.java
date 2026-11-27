package vn.restaurant.model;

public class ClsBan {
    public final String ma;
    public final String ten;
    public final String loai;
    public String trangThai;

    public ClsBan(String ma, String ten, String loai, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.trangThai = trangThai;
    }
}
