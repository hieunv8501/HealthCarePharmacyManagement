package Components;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ExportExcelButton extends JButton {

    public ExportExcelButton() {
        this.setText("Xuất Excel");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ms_excel_30px.png")));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
