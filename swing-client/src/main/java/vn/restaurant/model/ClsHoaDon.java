package vn.restaurant.model;

import java.time.LocalDate;
import java.util.List;

public class ClsHoaDon {
    public final String ma;
    public final String maBan;
    public final String nhanVien;
    public final LocalDate ngayLap;
    public final List<ClsChiTietHoaDon> chiTiet;
    public boolean daThanhToan;

    public ClsHoaDon(
            String ma,
            String maBan,
            String nhanVien,
            LocalDate ngayLap,
            List<ClsChiTietHoaDon> chiTiet) {
        this.ma = ma;
        this.maBan = maBan;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
        this.chiTiet = chiTiet;
    }

    public long tongTien() {
        return chiTiet.stream()
                .mapToLong(ClsChiTietHoaDon::thanhTien)
                .sum();
    }
}
