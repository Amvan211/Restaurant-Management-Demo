package vn.restaurant.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import vn.restaurant.model.ClsBan;
import vn.restaurant.model.ClsChiTietHoaDon;
import vn.restaurant.model.ClsHoaDon;

public class CtlThanhToanHoaDon {
    private final List<ClsBan> ban = new ArrayList<>();
    private final Map<String, ClsHoaDon> hoaDon = new HashMap<>();

    public CtlThanhToanHoaDon() {
        String[] loai = {"2 chỗ", "4 chỗ", "4 chỗ", "6 chỗ", "6 chỗ", "8 chỗ"};

        for (int i = 1; i <= 6; i++) {
            String id = String.format("%02d", i);
            String trangThai = (i == 2 || i == 4 || i == 5) ? "Đang sử dụng" : "Trống";
            ban.add(new ClsBan(id, "Bàn " + id, loai[i - 1], trangThai));
        }

        hoaDon.put(
                "05",
                new ClsHoaDon(
                        "HD001",
                        "05",
                        "Nguyễn Văn A",
                        LocalDate.of(2026, 11, 19),
                        List.of(
                                new ClsChiTietHoaDon("Cơm rang", 1, 100_000),
                                new ClsChiTietHoaDon("Rau xào", 2, 25_000))));
    }

    public List<ClsBan> danhSachBan() {
        return Collections.unmodifiableList(ban);
    }

    public List<ClsBan> timBan(String keyword) {
        String tuKhoa = keyword.strip().toLowerCase(Locale.ROOT);
        if (tuKhoa.isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập tên bàn cần tìm.");
        }

        return ban.stream()
                .filter(item -> item.ma.toLowerCase(Locale.ROOT).contains(tuKhoa)
                        || item.ten.toLowerCase(Locale.ROOT).contains(tuKhoa))
                .collect(Collectors.toList());
    }

    public ClsHoaDon layHoaDon(String maBan) {
        ClsHoaDon hoaDonCanTim = hoaDon.get(maBan);
        if (hoaDonCanTim == null) {
            throw new IllegalStateException("Bàn này chưa có hóa đơn cần thanh toán.");
        }
        if (hoaDonCanTim.daThanhToan) {
            throw new IllegalStateException("Hóa đơn đã được thanh toán.");
        }
        return hoaDonCanTim;
    }

    public synchronized void xacNhan(String maBan) {
        ClsHoaDon hoaDonCanThanhToan = layHoaDon(maBan);
        hoaDonCanThanhToan.daThanhToan = true;

        ban.stream()
                .filter(item -> item.ma.equals(maBan))
                .findFirst()
                .ifPresent(item -> item.trangThai = "Trống");
    }
}
