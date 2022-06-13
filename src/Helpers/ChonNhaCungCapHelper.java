package Helpers;

import Views.NhacungcapView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonNhaCungCapHelper extends JFrame {

    NhacungcapView nhacungcapView = new NhacungcapView();

    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txtNCC;

    public ChonNhaCungCapHelper(JTextField _txtNCC) {
        this.setTitle("Chọn nhà cung cấp");
        this.setLayout(new BorderLayout());
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.txtNCC = _txtNCC;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ok_30px.png")));

        JPanel plButtons = new JPanel();
        plButtons.add(btnOK);
        plButtons.add(btnCancel);

        this.add(nhacungcapView, BorderLayout.CENTER);
        this.add(plButtons, BorderLayout.SOUTH);
        this.setVisible(true);

        // actionlistener
        btnOK.addActionListener((ActionEvent ae) -> {
            String mancc = String.valueOf(nhacungcapView.getSelectedRow(0));
            if (mancc != null) {
                this.txtNCC.setText(mancc);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn nhà cung cấp nào!");
            }
        });

        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
