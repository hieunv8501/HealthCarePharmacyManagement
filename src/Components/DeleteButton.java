package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DeleteButton extends JButton {
    public DeleteButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_delete_forever_30px_1.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
