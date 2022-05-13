package Components;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MoreButton extends JButton {

    public MoreButton() {
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_more_20px.png")));
        this.setPreferredSize(new Dimension(30, 30));
    }
}
