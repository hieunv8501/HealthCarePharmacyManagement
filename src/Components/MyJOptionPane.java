package Components;

import Views.TableLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MyJOptionPane extends JOptionPane {
    
    JComboBox<String> cb = new JComboBox<>(new String[] {"Ghi đè", "Bỏ qua", "Ghi đè tất cả", "Bỏ qua tất cả"});
    JPanel pl = new JPanel();
    private final Font font = new Font("Segoe UI Semibold", Font.BOLD, 14);
    public MyJOptionPane(TableLayout tbll, String defaultSelect) {
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        this.setIcon(logo);
        this.setPreferredSize(new Dimension(800,350));
        cb.setFont(font);
        cb.setForeground(Color.red);
        cb.setBorder(BorderFactory.createTitledBorder(null, "Hành động:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.BLACK));

        pl.setPreferredSize(new Dimension(850,400));
        cb.setSelectedItem(defaultSelect);
        tbll.resizeColumnWidth();
        
        pl.setLayout(new BorderLayout());
        pl.add(tbll, BorderLayout.CENTER);
        pl.add(cb, BorderLayout.SOUTH);
        
        this.showMessageDialog(null, pl, "Trùng mã", QUESTION_MESSAGE);
    }
    
    public String getAnswer() {
        return cb.getSelectedItem().toString();
    }
}
