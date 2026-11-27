package vn.restaurant.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public final class Theme {
    public static final Color BLUE = new Color(20, 94, 182);
    public static final Color NAVY = new Color(20, 49, 90);
    public static final Color LIGHT = new Color(233, 243, 255);
    public static final Color ORANGE = new Color(244, 128, 31);
    public static final Font FONT = new Font("SansSerif", Font.PLAIN, 15);

    private Theme() {
    }

    public static JButton button(String text, boolean primary) {
        JButton button = new JButton(text);
        button.setFont(FONT.deriveFont(Font.BOLD));
        button.setForeground(primary ? Color.WHITE : NAVY);
        button.setBackground(primary ? BLUE : Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
                new LineBorder(primary ? BLUE : new Color(180, 199, 220), 1, true),
                new EmptyBorder(10, 17, 10, 17)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JLabel title(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FONT.deriveFont(Font.BOLD, 25f));
        label.setForeground(NAVY);
        return label;
    }

    public static JPanel panel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    public static void table(JTable table) {
        table.setFont(FONT);
        table.setRowHeight(41);
        table.setSelectionBackground(new Color(179, 216, 250));
        table.setSelectionForeground(NAVY);
        table.setGridColor(new Color(220, 231, 242));
        table.getTableHeader().setBackground(LIGHT);
        table.getTableHeader().setForeground(NAVY);
        table.getTableHeader().setFont(FONT.deriveFont(Font.BOLD));
        table.getTableHeader().setPreferredSize(new Dimension(0, 39));
    }

    public static String money(long amount) {
        return String.format(java.util.Locale.GERMANY, "%,d", amount) + "đ";
    }
}
