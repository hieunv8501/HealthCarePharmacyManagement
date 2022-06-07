package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CancelButton extends JButton {
    public CancelButton() {
        this.setText("Hủy");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
