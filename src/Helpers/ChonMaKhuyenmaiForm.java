package Helpers;

import Views.KhuyenmaiView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonMaKhuyenmaiForm extends JFrame {

    KhuyenmaiView tblMKM = new KhuyenmaiView();
    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txMaKM;

    public ChonMaKhuyenmaiForm(JTextField _txMaKM) {
        this.setLayout(new BorderLayout());
        this.setTitle("Chọn nhân viên");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.txMaKM = _txMaKM;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ok_30px.png")));

        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);

        this.add(tblMKM, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOK.addActionListener((ActionEvent ae) -> {
            String maKM = tblMKM.getSelectedRow(0);

            if (maKM != null) {
                this.txMaKM.setText(maKM);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn mã khuyến mãi nào!");
            }
        });

        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
