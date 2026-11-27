package vn.restaurant.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import vn.restaurant.service.CtlThanhToanHoaDon;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Fall back to Swing's default look and feel.
            }

            new FrmThanhToanHoaDon(new CtlThanhToanHoaDon()).setVisible(true);
        });
    }
}
