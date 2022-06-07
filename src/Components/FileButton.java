package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FileButton extends JButton {

    public FileButton() {
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_opened_folder_20px.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
