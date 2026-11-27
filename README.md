# RestaurantPaymentSystem — Module Nhân viên xác nhận thanh toán

## Công nghệ
- Java 17+, Java Swing (desktop)
- Jakarta Servlet 6 (Jakarta EE / J2EE-style web module), Maven, WAR
- Dữ liệu mẫu trong bộ nhớ, **không phải** hệ thống thanh toán sản xuất.

## Chạy giao diện Swing (không cần server)
Mở terminal tại thư mục gốc:

```bash
mvn -pl swing-client -am package
java -jar swing-client/target/swing-client-1.0.0.jar
```

Hoặc mở dự án Maven trong VS Code / IntelliJ, chạy `vn.restaurant.ui.Main`.

## Chạy servlet J2EE/Jakarta EE tùy chọn
```bash
mvn -pl web-server -am package
```
Deploy `web-server/target/restaurant-payment.war` lên **Tomcat 10.1+** (Servlet 6). Truy cập `http://localhost:8080/restaurant-payment/api/tables`.

**Lưu ý kiến trúc:** Swing demo hoạt động độc lập với dữ liệu trong bộ nhớ; servlet là endpoint minh họa riêng, chưa đồng bộ dữ liệu với Swing. Để kết nối thực sự cần bổ sung HTTP client và API đầy đủ cùng cơ sở dữ liệu.

## Luồng giao diện
1. `FrmThanhToanHoaDon`: danh sách bàn 01–06, tìm kiếm theo tên/mã, chọn bàn 05 và bấm Xem hóa đơn.
2. `FrmChiTietHoaDon`: HD001, bàn 05, cơm rang 1 × 100.000đ, rau xào 2 × 25.000đ, tổng 150.000đ.
3. `FrmXacNhanThanhToan`: kiểm tra lại và xác nhận. Trạng thái hóa đơn thành đã thanh toán, bàn thành trống. Mở hộp thoại máy in để in.

## Ngoại lệ
- Bỏ trống ô tìm kiếm: yêu cầu nhập tên bàn.
- Không tìm thấy bàn: thông báo.
- Bàn không có hóa đơn: thông báo.
- Hóa đơn đã thanh toán: không cho xác nhận lại.
- Hủy tại giao diện chi tiết hoặc xác nhận: không thay đổi trạng thái.

## Cấu trúc
- `swing-client/.../ui`: các form Java Swing.
- `swing-client/.../service`: `CtlThanhToanHoaDon` xử lý nghiệp vụ mẫu.
- `swing-client/.../model`: `ClsBan`, `ClsHoaDon`, `ClsChiTietHoaDon`.
- `swing-client/.../util`: style dùng chung.
- `web-server`: Servlet mẫu đóng gói WAR.

## Giới hạn
Dữ liệu mẫu sẽ khôi phục sau khi tắt/mở ứng dụng. Chưa có đăng nhập, MySQL, giao dịch thanh toán thực, xác thực, lưu bền vững, đồng bộ Swing–Servlet hoặc chức năng in lại. Khi hộp thoại in bị hủy, giao dịch demo đã được xác nhận; đây là điểm cần cải tiến cho bản triển khai thực tế.
