package Helpers;

import Views.QuyenView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChonQuyenForm extends JFrame {
    QuyenView tblQuyen = new QuyenView();
    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txMaQuyen;
    JTextField txChiTietQuyen;

    public ChonQuyenForm(JTextField _txMaQuyen, JTextField _txChiTietQuyen) {
        this.setLayout(new BorderLayout());
        this.setTitle("Chọn quyền");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.txMaQuyen = _txMaQuyen;
        this.txChiTietQuyen = _txChiTietQuyen;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_ok_30px.png")));

        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);
        
        this.add(tblQuyen, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOK.addActionListener((ActionEvent ae) -> {          
            String maQuyen = tblQuyen.getSelectedRow(1);
            String chitietquyen = tblQuyen.getSelectedRow(3);
            if (maQuyen != null) {
                if (this.txMaQuyen != null) {
                    this.txMaQuyen.setText(maQuyen);
                }
                if (this.txChiTietQuyen != null) {
                    this.txChiTietQuyen.setText(chitietquyen);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn quyền nào!");
            }

        });

        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
