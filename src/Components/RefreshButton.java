package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RefreshButton extends JButton {

    public RefreshButton() {
        this.setText("Làm mới");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_data_backup_30px.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
