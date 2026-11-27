package vn.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vn.restaurant.model.ClsChiTietHoaDon;
import vn.restaurant.model.ClsHoaDon;
import vn.restaurant.service.CtlThanhToanHoaDon;
import vn.restaurant.util.Theme;

public class FrmChiTietHoaDon extends JDialog {
    public FrmChiTietHoaDon(
            JFrame owner,
            CtlThanhToanHoaDon ctl,
            ClsHoaDon hoaDon,
            Runnable onPaid) {
        super(owner, "Chi tiết hóa đơn - " + hoaDon.ma, true);
        setSize(860, 640);
        setLocationRelativeTo(owner);
        setMinimumSize(new Dimension(720, 540));

        JPanel root = Theme.panel(new BorderLayout(0, 20));
        root.setBorder(BorderFactory.createEmptyBorder(22, 28, 22, 28));
        setContentPane(root);
        root.add(Theme.title("Chi tiết hóa đơn (FrmChiTietHoaDon)"), BorderLayout.NORTH);

        JPanel center = Theme.panel(new BorderLayout(0, 20));
        root.add(center, BorderLayout.CENTER);

        JPanel info = Theme.panel(new GridLayout(3, 2, 15, 12));
        info.setBackground(Theme.LIGHT);
        info.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        String[] invoiceInfo = {
            "Mã hóa đơn: " + hoaDon.ma,
            "Ngày lập: " + hoaDon.ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            "Bàn: Bàn " + hoaDon.maBan,
            "Nhân viên: " + hoaDon.nhanVien,
            "Trạng thái: Chưa thanh toán",
            ""
        };
        for (String text : invoiceInfo) {
            JLabel label = new JLabel(text);
            label.setFont(Theme.FONT);
            info.add(label);
        }
        center.add(info, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(
                new String[] {"STT", "Tên món", "Số lượng", "Đơn giá", "Thành tiền"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int order = 1;
        for (ClsChiTietHoaDon item : hoaDon.chiTiet) {
            tableModel.addRow(new Object[] {
                order++, item.tenMon(), item.soLuong(),
                Theme.money(item.donGia()), Theme.money(item.thanhTien())
            });
        }

        JTable table = new JTable(tableModel);
        Theme.table(table);
        center.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footer = Theme.panel(new BorderLayout(0, 20));
        JLabel total = new JLabel(
                "Tổng tiền:  " + Theme.money(hoaDon.tongTien()), SwingConstants.RIGHT);
        total.setFont(Theme.FONT.deriveFont(Font.BOLD, 24f));
        total.setForeground(new Color(184, 44, 28));
        total.setOpaque(true);
        total.setBackground(Theme.LIGHT);
        total.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 20));
        footer.add(total, BorderLayout.NORTH);

        JPanel actions = Theme.panel(new FlowLayout(FlowLayout.RIGHT, 14, 8));
        JButton cancel = Theme.button("Hủy", false);
        JButton confirm = Theme.button("Xác nhận", true);
        actions.add(cancel);
        actions.add(confirm);
        footer.add(actions, BorderLayout.SOUTH);
        root.add(footer, BorderLayout.SOUTH);

        cancel.addActionListener(event -> dispose());
        confirm.addActionListener(event -> {
            FrmXacNhanThanhToan dialog = new FrmXacNhanThanhToan(this, ctl, hoaDon);
            dialog.setVisible(true);
            if (dialog.isPaid()) {
                onPaid.run();
                dispose();
            }
        });
    }
}
