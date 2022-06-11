package Helpers;

import Views.KhachHangView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonKhachhangForm extends JFrame {

    KhachHangView tblKH = new KhachHangView();
    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txMaKH;

    public ChonKhachhangForm(JTextField _txMaKH) {
        this.setLayout(new BorderLayout());
        this.setTitle("Chọn nhân viên");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.txMaKH = _txMaKH;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ok_30px.png")));

        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);

        this.add(tblKH, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOK.addActionListener((ActionEvent ae) -> {
            String maNV = tblKH.getSelectedRow(0);
            String tenNV = tblKH.getSelectedRow(1);

            if (maNV != null) {
                this.txMaKH.setText(maNV + " - " + tenNV);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng nào!");
            }
        });

        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
