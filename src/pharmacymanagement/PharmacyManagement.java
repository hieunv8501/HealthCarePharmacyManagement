package pharmacymanagement;

import Views.DangnhapView;
import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PharmacyManagement {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DangnhapView lg = new DangnhapView();
                lg.setVisible(true);             
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(lg);
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            }
        });
    }
}
