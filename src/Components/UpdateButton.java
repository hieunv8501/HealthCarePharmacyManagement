package Components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UpdateButton extends JButton {
    public UpdateButton() {
        this.setText("Sửa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_support_30px.png")));
    }
}
