package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UpdateButton extends JButton {
    public UpdateButton() {
        this.setText("Sửa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_support_30px.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
