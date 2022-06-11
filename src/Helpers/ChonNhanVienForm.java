package Helpers;

import Views.NhanvienView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonNhanVienForm extends JFrame {
    NhanvienView tblNV = new NhanvienView();
    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txMaNV;

    public ChonNhanVienForm(JTextField _txMaNV) {
        this.setLayout(new BorderLayout());
        this.setTitle("Chọn nhân viên");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.txMaNV = _txMaNV;
        
        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ok_30px.png")));
        
        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);
        
        this.add(tblNV, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOK.addActionListener((ActionEvent ae) -> {
            String maNV = tblNV.getSelectedRow(1);
            if (maNV != null) {
                this.txMaNV.setText(maNV);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào!");
            }
        });
        
        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
