package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InsertButton extends JButton {

    public InsertButton() {
        this.setText("Thêm");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_add_30px.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
    }
}
