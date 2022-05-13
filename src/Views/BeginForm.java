package Views;

import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeginForm extends JPanel {

    JLabel lbInfo;

    public BeginForm(String text) {
        setLayout(new GridBagLayout());

        lbInfo = new JLabel(text);
        lbInfo.setFont(new Font("Arial", Font.BOLD, 40));
        lbInfo.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_thumb_up_99px.png")));

        add(lbInfo);
    }

    public void setLabelText(String text) {
        lbInfo.setText(text);
    }
}
