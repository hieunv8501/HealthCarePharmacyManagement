package Components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ExportExcelButton extends JButton {

    public ExportExcelButton() {
        this.setText("Xuất Excel");
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ms_excel_30px.png")));
    }
}