package Helpers;

import Components.CancelButton;
import Components.InsertButton;
import Controllers.TaikhoanController;
import Models.Taikhoan;
import Components.MoreButton;
import Components.UpdateButton;
import Components.UploadButton;
import Controllers.NhanvienController;
import Controllers.QuyenController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.mindrot.jbcrypt.BCrypt;

public class TaikhoanViewHelper extends JFrame {

    String type;
    TaikhoanController taikhoanCtrl = new TaikhoanController();
    Taikhoan tkSua;
    private final Font font = new Font("Segoe UI Semibold", Font.BOLD, 14);

    JTextField txtTentaikhoan = new JTextField(15);
    JPasswordField txtPwd = new JPasswordField(15);
    JTextField txtMaNV = new JTextField(12);
    JTextField txtMaQuyen = new JTextField(12);
    JLabel lblAnhDaiDien = new JLabel();

    JComboBox<String> cbChonTrangThai;

    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonQuyen = new MoreButton();

    InsertButton btnThem = new InsertButton();
    UpdateButton btnSua = new UpdateButton();
    CancelButton btnHuy = new CancelButton();
    UploadButton btnTailen = new UploadButton();

    public TaikhoanViewHelper(String _type, String _username) {
        this.setLayout(new BorderLayout());
        this.setSize(550, 550);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Images/logo_login.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.type = _type;

        // input set border
        txtTentaikhoan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Tên tài khoản", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.black));
        txtPwd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Mật khẩu", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.black));
        //txtMaNV.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        //txtMaQuyen.setBorder(BorderFactory.createTitledBorder("Quyền"));
        cbChonTrangThai = new JComboBox<>(new String[]{"Ẩn", "Hiện"});
        btnChonNhanVien.setText("Chọn");
        btnChonNhanVien.setToolTipText("Chọn nhân viên");
        btnChonQuyen.setText("Chọn");
        btnChonQuyen.setToolTipText("Chọn quyền");
        btnTailen.setText("Tải ảnh lên");
        btnTailen.setToolTipText("Tải ảnh đại diện lên");

        //set size + height
        txtTentaikhoan.setPreferredSize(new Dimension(40, 50));
        txtPwd.setPreferredSize(new Dimension(40, 50));
        txtMaNV.setPreferredSize(new Dimension(40, 35));
        txtMaQuyen.setPreferredSize(new Dimension(40, 35));
        cbChonTrangThai.setPreferredSize(new Dimension(65, 30));
        lblAnhDaiDien.setPreferredSize(new Dimension(200, 200));

        //set Font
        txtTentaikhoan.setFont(font);
        txtPwd.setFont(font);
        txtMaNV.setFont(font);
        txtMaQuyen.setFont(font);
        cbChonTrangThai.setFont(font);

        //set color 
        txtTentaikhoan.setForeground(Color.red);
        txtPwd.setForeground(Color.red);
        txtMaNV.setForeground(Color.red);
        txtMaQuyen.setForeground(Color.red);
        cbChonTrangThai.setForeground(Color.red);

        //add button to Layout
        JPanel plChonNhanVien = new JPanel();
        plChonNhanVien.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.black));
        plChonNhanVien.add(txtMaNV);
        plChonNhanVien.add(btnChonNhanVien);

        JPanel plChonQuyen = new JPanel();
        plChonQuyen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Mã quyền", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.black));
        plChonQuyen.add(txtMaQuyen);
        plChonQuyen.add(btnChonQuyen);

        JPanel plChonTT = new JPanel();
        plChonTT.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Trạng thái", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.black));
        plChonTT.add(cbChonTrangThai);

        JPanel plChonAnhDaiDien = new JPanel();
        plChonAnhDaiDien.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //btnTailen.add
        plChonAnhDaiDien.add(btnTailen);
        plChonAnhDaiDien.add(lblAnhDaiDien);

        JPanel plInput = new JPanel();
        plInput.add(txtTentaikhoan);
        plInput.add(txtPwd);
        plInput.add(plChonNhanVien);
        plInput.add(plChonQuyen);
        plInput.add(plChonTT);
        plInput.add(plChonAnhDaiDien);

        // panel buttons
        JPanel plButton = new JPanel();

        // Trường hợp: Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm tài khoản");
            this.setIconImage(new ImageIcon(this.getClass().getResource("/Images/icons8_add_30px.png")).getImage());
            cbChonTrangThai.setSelectedItem("Hiện");
            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa tài khoản");
            this.setIconImage(new ImageIcon(this.getClass().getResource("/Images//Images/icons8_support_30px.png")).getImage());
            for (Taikhoan tk : TaikhoanController.getDanhSachTaiKhoan()) {
                if (tk.getTaikhoan().equals(_username)) {
                    this.tkSua = tk;
                }
            }
            if (this.tkSua == null) {
                JOptionPane.showMessageDialog(this, "Lỗi, không tìm thấy tài khoản");
                this.dispose();
            }

            cbChonTrangThai.setSelectedItem(this.tkSua.isDaXoa() ? "Ẩn" : "Hiện");
            txtTentaikhoan.setText(this.tkSua.getTaikhoan());
            txtPwd.setText(this.tkSua.getMatkhau());
            txtMaNV.setText(this.tkSua.getNv().getMaNhanvien() + " - " + this.tkSua.getNv().getTenNhanvien());
            txtMaQuyen.setText(this.tkSua.getQ().getMaQuyen() + " - " + this.tkSua.getQ().getTenQuyen());
            txtTentaikhoan.setEditable(false);
            txtPwd.setEditable(false);
            txtMaNV.setEditable(false);
            btnSua.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        // mouse listener
        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txtMaNV);
        });
        btnChonQuyen.addActionListener((ae) -> {
            ChonQuyenForm cq = new ChonQuyenForm(txtMaQuyen, null);
        });

        this.setVisible(true);
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            Taikhoan tkNew = new Taikhoan();
            String username = txtTentaikhoan.getText();
            String pass = String.valueOf(txtPwd.getPassword());
            int manv = Integer.parseInt(txtMaNV.getText());
            String maquyen = txtMaQuyen.getText();

            tkNew.setTaikhoan(username);
            tkNew.setMatkhau(saveHashPwd(pass));
            tkNew.setNv(NhanvienController.getNhanVien(manv));
            tkNew.setQ(QuyenController.getQuyen(maquyen));

            if (taikhoanCtrl.themTaiKhoan(tkNew)) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản " + username + " thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            Taikhoan tkUpdate = new Taikhoan();
            String username = txtTentaikhoan.getText();
            String pass = String.valueOf(txtPwd.getPassword());
            int manv = Integer.parseInt(txtMaNV.getText().split(" - ")[0]);
            String maquyen = txtMaQuyen.getText().split(" - ")[0];
            boolean trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? false : true);

            tkUpdate.setTaikhoan(username);
            tkUpdate.setMatkhau(pass);
            tkUpdate.setNv(NhanvienController.getNhanVien(manv));
            tkUpdate.setQ(QuyenController.getQuyen(maquyen));
            tkUpdate.setDaXoa(trangthai);

            if (taikhoanCtrl.capnhatTaiKhoan(tkUpdate)) {
                JOptionPane.showMessageDialog(this, "Cập nhật tài khoản " + username + " thành công!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin tài khoản " + username + " thất bại, vui lòng thử lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private String saveHashPwd(String pwdToHash) {
        String hashedPwd = null;
        if (pwdToHash != null && !pwdToHash.isEmpty()) {
            int round = 10;
            hashedPwd = BCrypt.hashpw(pwdToHash, BCrypt.gensalt(round));
        }
        return hashedPwd;
    }

    private Boolean checkEmpty() {
        String username = txtTentaikhoan.getText();
        String pass = String.valueOf(txtPwd.getPassword());
        String manv = txtMaNV.getText();
        String maquyen = txtMaQuyen.getText();

        if (username.trim().equals("")) {
            return showErrorTx(txtTentaikhoan, "Tên tài khoản không được để trống");

        } else if (pass.equals("")) {
            return showErrorPx(txtPwd, "Mật khẩu không được để trống");

        } else if (manv.trim().equals("")) {
            return showErrorTx(txtMaNV, "Nhân viên không được để trống");

        } else if (maquyen.trim().equals("")) {
            return showErrorTx(txtMaQuyen, "Quyền không được để trống");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    private Boolean showErrorPx(JPasswordField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
}
