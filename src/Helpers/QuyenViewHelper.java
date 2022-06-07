package Helpers;

import Components.CancelButton;
import Components.InsertButton;
import Components.UpdateButton;
import Models.Quyen;
import Controllers.QuyenController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class QuyenViewHelper extends JFrame {
    String type;
    QuyenController quyenCtrl = new QuyenController();
    Quyen quyen;

    JTextField txtMaQuyen = new JTextField(10);
    JTextField txtTenQuyen = new JTextField(20);
    ChiTietQuyenForm chitietForm = new ChiTietQuyenForm();

    InsertButton btnThem = new InsertButton();
    UpdateButton btnSua = new UpdateButton();
    CancelButton btnHuy = new CancelButton();

    public QuyenViewHelper(String _type, String _maq) {
        this.setLayout(new BorderLayout());
        this.setSize(675, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.type = _type;

        // inputs
        txtMaQuyen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Mã quyền", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI Semibold", Font.BOLD, 14), Color.black));
        txtMaQuyen.setFont(new Font("SansSerif", Font.BOLD, 13));
        txtMaQuyen.setForeground(Color.RED);
        txtTenQuyen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Tên quyền", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI Semibold", Font.BOLD, 14), Color.black));
        txtTenQuyen.setFont(new Font("SansSerif", Font.BOLD, 13));
        txtTenQuyen.setForeground(Color.RED);
        JPanel plInput = new JPanel();
        plInput.add(txtMaQuyen);
        plInput.add(txtTenQuyen);
        plInput.add(chitietForm);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm quyền");
            txtMaQuyen.setText(quyenCtrl.getNextID());

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa quyền");
            for (Quyen q : quyenCtrl.getDanhSachQuyen()) {
                if (q.getMaQuyen().equals(_maq)) {
                    this.quyen = q;
                }
            }
            if (this.quyen == null) {
                JOptionPane.showMessageDialog(this, "Lỗi, không tìm thấy quyền");
                this.dispose();
            }

            txtMaQuyen.setText(this.quyen.getMaQuyen());
            txtTenQuyen.setText(this.quyen.getTenQuyen());
            chitietForm.setQuyen(this.quyen.getChitietQuyen());

            txtMaQuyen.setEditable(false);

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
        
        this.setVisible(true);
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String maquyen = txtMaQuyen.getText();
            String tenquyen = txtTenQuyen.getText();
            String chitietquyen = chitietForm.getQuyen();

            if (quyenCtrl.add(maquyen, tenquyen, chitietquyen)) {
                JOptionPane.showMessageDialog(this, "Thêm " + maquyen + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String maquyen = txtMaQuyen.getText();
            String tenquyen = txtTenQuyen.getText();
            String chitietquyen = chitietForm.getQuyen();

            if (quyenCtrl.update(maquyen, tenquyen, chitietquyen)) {
                JOptionPane.showMessageDialog(this, "Sửa " + maquyen + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String maquyen = txtMaQuyen.getText();
        String tenquyen = txtTenQuyen.getText();
        String chitietquyen = chitietForm.getQuyen();

        if (maquyen.trim().equals("")) {
            return showErrorTx(txtMaQuyen, "Mã quyền không được để trống");

        } else if (tenquyen.trim().equals("")) {
            return showErrorTx(txtMaQuyen, "Tên quyền không được để trống");

        } else if (chitietquyen.trim().equals("")) {
            return showErrorTx(txtMaQuyen, "Bạn chưa chọn quyền nào cả !!");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
}

class ChiTietQuyenForm extends JPanel {

    final String[] type = {"Chỉ xem", "Xem và Quản lý"};
    ArrayList<PanelChonQuyen> dsPanel = new ArrayList<>();

    public ChiTietQuyenForm() {
        setPreferredSize(new Dimension(650, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Chi tiết quyền", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI Semibold", Font.BOLD, 14), Color.black));

        dsPanel.add(new PanelChonQuyen("Bán thuốc", new String[]{"Bán thuốc"}, new String[]{"qlBanThuoc"}));
        dsPanel.add(new PanelChonQuyen("Nhập thuốc", new String[]{"Nhập thuốc"}, new String[]{"qlNhapThuoc"}));
        dsPanel.add(new PanelChonQuyen("Thuốc", type, new String[]{"xemThuoc", "qlThuoc"}));
        dsPanel.add(new PanelChonQuyen("Loại thuốc", type, new String[]{"xemLoaiThuoc", "qlLoaiThuoc"}));
        dsPanel.add(new PanelChonQuyen("Hóa đơn", type, new String[]{"xemHoaDon", "qlHoaDon"}));
        dsPanel.add(new PanelChonQuyen("Khuyến mãi", type, new String[]{"xemKhuyenMai", "qlKhuyenMai"}));
        dsPanel.add(new PanelChonQuyen("Nhân viên", type, new String[]{"xemNhanVien", "qlNhanVien"}));
        dsPanel.add(new PanelChonQuyen("Khách hàng", type, new String[]{"xemKhachHang", "qlKhachHang"}));
        dsPanel.add(new PanelChonQuyen("Nhập thuốc", type, new String[]{"xemPhieuNhap", "qlPhieuNhap"}));
        dsPanel.add(new PanelChonQuyen("Nhà cung cấp", type, new String[]{"xemNCC", "qlNCC"}));
        dsPanel.add(new PanelChonQuyen("Tài khoản", type, new String[]{"xemTaiKhoan", "qlTaiKhoan"}));
        dsPanel.add(new PanelChonQuyen("Quyền", type, new String[]{"xemQuyen", "qlQuyen"}));        
        dsPanel.add(new PanelChonQuyen("Thống kê", type, new String[]{"xemQuyen", "qlQuyen"}));

        
        for (PanelChonQuyen p : dsPanel) {
            this.add(p);
        }
    }

    public String getQuyen() {
        String result = "";
        for (PanelChonQuyen p : dsPanel) {
            result += p.getValue();
        }
        return result.trim();
    }

    public void setQuyen(String quyen) {
        for (PanelChonQuyen p : dsPanel) {
            p.setValue(quyen);
        }
    }
}

class PanelChonQuyen extends JPanel {

    String name;
    String[] type, value;

    JCheckBox chb;
    JComboBox<String> cb;
    private DefaultListCellRenderer listRenderer;
    
    public PanelChonQuyen(String name, String[] type, String[] value) {
        this.setPreferredSize(new Dimension(300, 60));
        this.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        this.name = name;
        this.type = type;
        this.value = value;

        this.chb = new JCheckBox(this.name);
        this.chb.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        this.add(this.chb);

        this.cb = new JComboBox<>(this.type);
        
        this.cb.setPreferredSize(new Dimension(130, 20));
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        this.cb.setRenderer(listRenderer);
        this.cb.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        this.cb.setEnabled(false);
        this.add(this.cb);

        chb.addActionListener((ae) -> {
            if (chb.isSelected()) {
                this.cb.setEnabled(true);
            } else {
                this.cb.setEnabled(false);
            }
        });
    }

    public String getValue() {
        String result = "";

        if (chb.isSelected()) {
            result += " " + value[cb.getSelectedIndex()];
        }

        return result;
    }

    public void setValue(String s) {
        if (s.equals("")) {
            chb.setSelected(false);
            return;
        }

        for (int i = 0; i < value.length; i++) {
            if (s.contains(value[i])) {
                cb.setSelectedIndex(i);
                cb.setEnabled(true);
                chb.setSelected(true);
            }
        }
    }
}

