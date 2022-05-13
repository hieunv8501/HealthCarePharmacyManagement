package pharmacymanagement;

import Views.LoginForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PharmacyManagement {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        new LoginForm().setVisible(true);
    }
}
