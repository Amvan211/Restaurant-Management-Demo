package vn.restaurant.model;

public record ClsChiTietHoaDon(String tenMon, int soLuong, long donGia) {
    public long thanhTien() {
        return soLuong * donGia;
    }
}
