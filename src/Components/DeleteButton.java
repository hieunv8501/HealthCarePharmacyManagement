package Components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DeleteButton extends JButton {
    public DeleteButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_delete_forever_30px_1.png")));
    }
}
