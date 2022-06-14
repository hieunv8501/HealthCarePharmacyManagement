package Views;

import Controllers.HuyenController;
import Controllers.LoaiNhanvienController;
import Controllers.NhanvienController;
import Controllers.TinhController;
import Controllers.XaController;
import Models.Huyen;
import Models.LoaiNhanvien;
import Models.Nhanvien;
import Models.Tinh;
import Models.Xa;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhanVienView extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    int flagClick = 1;
    ArrayList<Tinh> dsTinh = new ArrayList<>();
    ArrayList<Huyen> dsHuyen;
    ArrayList<Xa> dsXa;
    XaController xaCtl = new XaController();
    NhanvienController nvCtrl = new NhanvienController();
    List<Nhanvien> dsnv;

    public NhanVienView() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbNV.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tbNV.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tbNV.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tbNV.getTableHeader().setOpaque(false);
        tbNV.getTableHeader().setBackground(Color.YELLOW);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        reset();
        addTinh();
        addDSLoaiNhanvien();
    }

    public final void addTinh() {
        TinhController tinhCtl = new TinhController();
        dsTinh = tinhCtl.getDanhsachTinh();
        String temp;
        for (Tinh tinh : dsTinh) {
            temp = tinh.getTenTinh();
            cbTinh.addItem(temp);
        }
    }

    public final void addDSLoaiNhanvien() {
        LoaiNhanvienController loaiNVCtrl = new LoaiNhanvienController();
        ArrayList<String> dsLoaiNV = loaiNVCtrl.getDSTenLoaiNhanVien();
        for (String loaiNV : dsLoaiNV) {
            cbLoaiNV.addItem(loaiNV);
        }
    }

    public <T> void showData(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (T t : list) {
            if (t instanceof Nhanvien nhanvien) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngaysinh = nhanvien.getNgaySinh().format(formatter);
                model.addRow(new Object[]{
                    nhanvien.getMaNhanvien() , nhanvien.getTenNhanvien(), nhanvien.getGioiTinh(), ngaysinh, nhanvien.getSoDienThoai(), nhanvien.getDiaChi(), nhanvien.getLoaiNhanvien().getTenLoaiNhanvien(), nhanvien.getBangCap()});
            }
        }
    }

    public final void reset() {
        tableModel = (DefaultTableModel) tbNV.getModel();
        dsnv = NhanvienController.getDSNV();
        this.showData(dsnv, tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbXa = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbHuyen = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbTinh = new javax.swing.JComboBox<>();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtBangCap = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cbGioiTinh = new javax.swing.JComboBox<>();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        btnLuu = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbLoaiNV = new javax.swing.JComboBox<>();
        btnXemLuong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNV = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Nhân viên"));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Họ tên:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Giới tính:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Bằng Cấp:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("Xã");

        cbXa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbXa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Xã--" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Huyện");

        cbHuyen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbHuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Huyện--" }));
        cbHuyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbHuyenItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Tỉnh");

        cbTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Tỉnh--" }));
        cbTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTinhItemStateChanged(evt);
            }
        });

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtBangCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        cbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-30.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Vị trí:");

        cbLoaiNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbLoaiNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Vị trí--" }));

        btnXemLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXemLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_show_property_30px.png"))); // NOI18N
        btnXemLuong.setText("Xem Bảng Lương");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(cbLoaiNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(169, 169, 169)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBangCap)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbXa, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(133, 133, 133))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(btnThem)
                .addGap(106, 106, 106)
                .addComponent(btnLuu)
                .addGap(106, 106, 106)
                .addComponent(btnSua)
                .addGap(116, 116, 116)
                .addComponent(btnXoa)
                .addGap(99, 99, 99)
                .addComponent(btnXemLuong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addComponent(jLabel2))
                            .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtBangCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(cbTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbXa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLuu)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnXemLuong))
                .addGap(36, 36, 36))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        tbNV.setBackground(new java.awt.Color(204, 255, 255));
        tbNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Địa chỉ", "Vị trí", "Bằng cấp"
            }
        ));
        tbNV.setRowHeight(30);
        tbNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTinhItemStateChanged
        // TODO add your handling code here:
        cbHuyen.removeAllItems();
        cbHuyen.addItem("--Chọn Huyện--");
        HuyenController huyenCtl = new HuyenController();
        if (dsHuyen != null) {
            dsHuyen.clear();
        }
        dsHuyen = huyenCtl.getDanhsachHuyenTheoTinh(cbTinh.getSelectedItem().toString());
        for (Huyen huyen : dsHuyen) {
            cbHuyen.addItem(huyen.getTenHuyen());
        }

    }//GEN-LAST:event_cbTinhItemStateChanged

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        if (flagClick == 1) {
            btnThem.setText("Hủy");
            btnLuu.setEnabled(true);
            flagClick = 0;
        }
        if (flagClick == 0) {
            txtHoTen.setText("");
            flagClick = 1;
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void cbHuyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbHuyenItemStateChanged
        // TODO add your handling code here:
        if (cbHuyen.getItemCount() != 0) {

            if (dsXa != null) {
                dsXa.clear();
            }
            dsXa = xaCtl.getDanhsachXaTheoHuyen(cbHuyen.getSelectedItem().toString());
            cbXa.removeAllItems();
            cbXa.addItem("--Chọn Xã--");
            for (Xa xa : dsXa) {
                cbXa.addItem(xa.getTenXa());
            }
        }
    }//GEN-LAST:event_cbHuyenItemStateChanged

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        // TODO add your handling code here:
        if (txtHoTen.getText().equals("") || dateNgaySinh.getDate() == null || txtSDT.getText().equals("") || txtBangCap.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống các trường thông tin!");
        } else if (cbLoaiNV.getSelectedItem().toString().equals("--Chọn Vị trí--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Vị trí của nhân viên!");
        } else if (cbTinh.getSelectedItem().toString().equals("--Chọn Tỉnh--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Tỉnh!");
        } else if (cbHuyen.getSelectedItem().toString().equals("--Chọn Huyện--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Huyện!");
        } else if (cbXa.getSelectedItem().toString().equals("--Chọn Xã--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Xã!");
        } else {
            int maNhanvien = dsnv.get(dsnv.size() - 1).getMaNhanvien() + 1;
            String tenNhanvien = txtHoTen.getText();
            LocalDate ngaySinh = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String sDT = txtSDT.getText();
            String gioiTinh = cbGioiTinh.getSelectedItem().toString();
            String bangCap = txtBangCap.getText();
            int maLoaiNV = LoaiNhanvienController.getMaLoaiNhanvien(cbLoaiNV.getSelectedItem().toString());
            Nhanvien nhanvien = new Nhanvien(maNhanvien, tenNhanvien, ngaySinh, sDT, gioiTinh, bangCap, LoaiNhanvienController.getLoaiNhanvien(maLoaiNV), xaCtl.getXa(cbXa.getSelectedItem().toString()), 0, false);
            if (NhanvienController.themNhanvien(nhanvien)) {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
            }
            reset();
        }
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tbNV.getModel();
        if (tbNV.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn vui lòng chọn 1 nhân viên trong danh sách!!!");
        } else {
            if (txtHoTen.getText().equals("") || dateNgaySinh.getDate() == null || txtSDT.getText().equals("") || txtBangCap.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống các trường thông tin!");
            } else if (cbLoaiNV.getSelectedItem().toString().equals("--Chọn Vị trí--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Vị trí của nhân viên!");
            } else if (cbTinh.getSelectedItem().toString().equals("--Chọn Tỉnh--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Tỉnh!");
            } else if (cbHuyen.getSelectedItem().toString().equals("--Chọn Huyện--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Huyện!");
            } else if (cbXa.getSelectedItem().toString().equals("--Chọn Xã--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Xã!");
            } else {
                int maNhanvien = Integer.parseInt(tableModel.getValueAt(tbNV.getSelectedRow(), 0).toString());
                String tenNhanvien = txtHoTen.getText();
                LocalDate ngaySinh = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String sDT = txtSDT.getText();
                String gioiTinh = cbGioiTinh.getSelectedItem().toString();
                String bangCap = txtBangCap.getText();
                int maLoaiNV = LoaiNhanvienController.getMaLoaiNhanvien(cbLoaiNV.getSelectedItem().toString());
                Nhanvien nhanvien = new Nhanvien(maNhanvien, tenNhanvien, ngaySinh, sDT, gioiTinh, bangCap, LoaiNhanvienController.getLoaiNhanvien(maLoaiNV), xaCtl.getXa(cbXa.getSelectedItem().toString()), 0, false);
                if (NhanvienController.suaNhanvien(nhanvien)) {
                    JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");
                }
                reset();
            }
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void tbNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNVMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tbNV.getModel();
        String tenNV = tableModel.getValueAt(tbNV.getSelectedRow(), 1).toString();
        String gioiTinh = tableModel.getValueAt(tbNV.getSelectedRow(), 2).toString();
        String ngaySinh = tableModel.getValueAt(tbNV.getSelectedRow(), 3).toString();
        String SDT = tableModel.getValueAt(tbNV.getSelectedRow(), 4).toString();
        String diaChi = tableModel.getValueAt(tbNV.getSelectedRow(), 5).toString();
        String viTri = tableModel.getValueAt(tbNV.getSelectedRow(), 6).toString();
        String bangCap = tableModel.getValueAt(tbNV.getSelectedRow(), 7).toString();

        txtHoTen.setText(tenNV);
        cbGioiTinh.setSelectedItem(gioiTinh);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }

        dateNgaySinh.setDate(date);
        txtSDT.setText(SDT);
        String[] part = diaChi.split(" - ");
        String xa = part[0];
        String huyen = part[1];
        String tinh = part[2];
        cbTinh.setSelectedItem(tinh);
        cbHuyen.setSelectedItem(huyen);
        cbXa.setSelectedItem(xa);
        cbLoaiNV.setSelectedItem(viTri);
        txtBangCap.setText(bangCap);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tbNVMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tbNV.getModel();
        if (tbNV.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn vui lòng chọn 1 Nhân viên trong danh sách!!!");
        } else {
            int maNV = Integer.parseInt(tableModel.getValueAt(tbNV.getSelectedRow(), 0).toString());
            if(NhanvienController.xoaNhanvien(maNV)) {
                JOptionPane.showMessageDialog(null, "Xóa thông tin nhân viên thành công!");
                reset();
            }
        }
    }//GEN-LAST:event_btnXoaMouseClicked
    public String getSelectedRow(int col) {
        int i = tbNV.getSelectedRow();
        if (i >= 0) {
            int realI = tbNV.convertRowIndexToModel(i);
            return tbNV.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXemLuong;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbHuyen;
    private javax.swing.JComboBox<String> cbLoaiNV;
    private javax.swing.JComboBox<String> cbTinh;
    private javax.swing.JComboBox<String> cbXa;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNV;
    private javax.swing.JTextField txtBangCap;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
