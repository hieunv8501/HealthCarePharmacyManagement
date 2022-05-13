package Components;

import Views.TableLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyJOptionPane extends JOptionPane {
    
    JComboBox<String> cb = new JComboBox<>(new String[] {"Ghi đè", "Bỏ qua", "Ghi đè tất cả", "Bỏ qua tất cả"});
    JPanel pl = new JPanel();
    
    public MyJOptionPane(TableLayout tbll, String defaultSelect) {
        cb.setBorder(BorderFactory.createTitledBorder("Hành động:"));
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
