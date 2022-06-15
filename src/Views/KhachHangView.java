package Views;

//import Controllers.KhachhangController;
import Components.ExcelExportFunction;
import Components.ExcelImportFunction;
import Controllers.HuyenController;
import Controllers.KhachhangController;
import Controllers.TinhController;
import Controllers.XaController;
import Models.Huyen;
import Models.Khachhang;
import Models.Tinh;
import Models.Xa;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KhachHangView extends javax.swing.JPanel {

    //KhachhangController Khachhangcrtl = new KhachhangController();
    private DefaultTableModel tableModel;
    int flagClick = 1;
    ArrayList<Tinh> dsTinh = new ArrayList<>();
    ArrayList<Huyen> dsHuyen;
    ArrayList<Xa> dsXa;
    XaController xaCtl = new XaController();
    KhachhangController khCtl = new KhachhangController();
    List<Khachhang> dskh;

    public KhachHangView() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKH.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tableKH.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableKH.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tableKH.getTableHeader().setOpaque(false);
        tableKH.getTableHeader().setBackground(Color.YELLOW);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        addTinh();
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlKhachHang")) {
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnTaiLenExcel.setEnabled(false);
            btnTaiXuongExcel.setEnabled(false);
        }
        btnTaiXuongExcel.addActionListener((ActionEvent ae) -> {
            new ExcelExportFunction().xuatFileExcelKhachhang();
        });
        btnTaiLenExcel.addActionListener((ActionEvent ae) -> {
            new ExcelImportFunction().docFileExcelKhachhang();
        });
        reset();

    }

    public String getSelectedRow(int col) {
        int i = tableKH.getSelectedRow();
        if (i >= 0) {
            int realI = tableKH.convertRowIndexToModel(i);
            return tableKH.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    //Hàm add các tỉnh vào combobox tỉnh
    public final void addTinh() {
        TinhController tinhCtl = new TinhController();
        dsTinh = tinhCtl.getDanhsachTinh();
        String temp;
        for (Tinh tinh : dsTinh) {
            temp = tinh.getTenTinh();
            cbTinh.addItem(temp);
        }
    }

    public <T> void showData(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (T t : list) {
            if (t instanceof Khachhang kh) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngaysinh = kh.getNgaySinh().format(formatter);
                model.addRow(new Object[]{
                    kh.getMaKhachhang(), kh.getTenKhachhang(), kh.getGioitinh(), ngaysinh, kh.getSoDienthoai(), kh.getDiaChi()});
            }
        }
    }

    public final void reset() {
        tableModel = (DefaultTableModel) tableKH.getModel();
        dskh = KhachhangController.layDSKhachHang();
        this.showData(dskh, tableModel);
        ShowSearchTextBox();
    }

    public void ShowSearchTextBox() {

        Set<String> hash_Set = new HashSet<String>();
        dskh.forEach(khachhang1 -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngaysinh = khachhang1.getNgaySinh().format(formatter);
            hash_Set.add(String.valueOf(khachhang1.getMaKhachhang()));
            hash_Set.add(khachhang1.getTenKhachhang());
            hash_Set.add(khachhang1.getGioitinh());
            hash_Set.add(khachhang1.getDiaChi());
            hash_Set.add(ngaysinh);
            hash_Set.add(String.valueOf(khachhang1.getNgaySinh().getDayOfMonth()));
            hash_Set.add(String.valueOf(khachhang1.getNgaySinh().getMonth()));
            hash_Set.add(String.valueOf(khachhang1.getNgaySinh().getYear()));
            hash_Set.add(String.valueOf(khachhang1.getXa()));
        }
        );
        txtSearchBox.clearItemSuggestion();
        Iterator value = hash_Set.iterator();
        while (value.hasNext()) {
            txtSearchBox.addItemSuggestion(String.valueOf(value.next()));
        }
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
        cbXa = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbTinh = new javax.swing.JComboBox<>();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        cbGioiTinh = new javax.swing.JComboBox<>();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        cbHuyen = new javax.swing.JComboBox<>();
        btnTaiLenExcel = new javax.swing.JButton();
        btnTaiXuongExcel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtSearchBox = new com.raven.chart.TextFieldSuggestion();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Tên khách hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Giới tính:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel4.setText("Số Điện thoại:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel6.setText("Xã:");

        cbXa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbXa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Xã--" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel7.setText("Huyện:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel8.setText("Tỉnh:");

        cbTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Tỉnh--" }));
        cbTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTinhItemStateChanged(evt);
            }
        });

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-30.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        cbGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        txtNgaySinh.setDateFormatString("dd/MM/yyyy");
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbHuyen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbHuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Huyện--" }));
        cbHuyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbHuyenItemStateChanged(evt);
            }
        });
        cbHuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHuyenActionPerformed(evt);
            }
        });

        btnTaiLenExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiLenExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_ms_excel_30px.png"))); // NOI18N
        btnTaiLenExcel.setText("Nhập Excel");

        btnTaiXuongExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiXuongExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_downloads_30px.png"))); // NOI18N
        btnTaiXuongExcel.setText("Xuất Excel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH)
                            .addComponent(cbGioiTinh, 0, 280, Short.MAX_VALUE))
                        .addGap(268, 268, 268)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbHuyen, 0, 138, Short.MAX_VALUE)
                            .addComponent(cbXa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(64, 64, 64)
                .addComponent(btnLuu)
                .addGap(73, 73, 73)
                .addComponent(btnSua)
                .addGap(63, 63, 63)
                .addComponent(btnXoa)
                .addGap(65, 65, 65)
                .addComponent(btnTaiLenExcel)
                .addGap(59, 59, 59)
                .addComponent(btnTaiXuongExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(cbTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbXa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(59, 59, 59)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTaiLenExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTaiXuongExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa)
                        .addComponent(btnLuu)))
                .addGap(65, 65, 65))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        tableKH.setBackground(new java.awt.Color(204, 255, 255));
        tableKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ Tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ"
            }
        ));
        tableKH.setRowHeight(30);
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Tìm khách hàng:");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel9)
                .addGap(33, 33, 33)
                .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(jLabel9)
                    .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        if (flagClick == 1) {
            btnThem.setText("Hủy");
            btnLuu.setEnabled(true);
            flagClick = 0;
        } else if (flagClick == 0) {
            btnThem.setText("Thêm");

            txtTenKH.setText("");
            btnLuu.setEnabled(false);
            txtSDT.setText("");
            txtNgaySinh.setDate(null);
            cbTinh.setSelectedItem("--Chọn Tỉnh--");
            cbHuyen.setSelectedItem("--Chọn Huyện--");
            cbXa.setSelectedItem("--Chọn Xã--");
            flagClick = 1;
        }
    }//GEN-LAST:event_btnThemMouseClicked

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
        if (txtTenKH.getText().equals("") || txtNgaySinh.getDate() == null || txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống các trường thông tin!");
        } else if (cbTinh.getSelectedItem().toString().equals("--Chọn Tỉnh--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Tỉnh!");
        } else if (cbHuyen.getSelectedItem().toString().equals("--Chọn Huyện--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Huyện!");
        } else if (cbXa.getSelectedItem().toString().equals("--Chọn Xã--")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Xã!");
        } else {
            Khachhang khachhang = new Khachhang();
            khachhang.setTenKhachhang(txtTenKH.getText());
            khachhang.setGioitinh(cbGioiTinh.getSelectedItem().toString());

            LocalDate ngaySinh = txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            khachhang.setNgaySinh(ngaySinh);

            khachhang.setSoDienthoai(txtSDT.getText());
            khachhang.setXa(xaCtl.getXa(cbXa.getSelectedItem().toString()));
            khachhang.setMaKhachhang(dskh.get(dskh.size() - 1).getMaKhachhang() + 1);
            khachhang.setKhachQuen(false);
            khachhang.setDaXoa(false);
            khCtl.themKhachHang(khachhang);
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
            reset();
        }

    }//GEN-LAST:event_btnLuuMouseClicked

    private void tableKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tableKH.getModel();

        String tenKH = tableModel.getValueAt(tableKH.getSelectedRow(), 1).toString();
        String gioiTinh = tableModel.getValueAt(tableKH.getSelectedRow(), 2).toString();
        String ngaySinh = tableModel.getValueAt(tableKH.getSelectedRow(), 3).toString();
        String SDT = tableModel.getValueAt(tableKH.getSelectedRow(), 4).toString();
        String diaChi = tableModel.getValueAt(tableKH.getSelectedRow(), 5).toString();

        txtTenKH.setText(tenKH);
        cbGioiTinh.setSelectedItem(gioiTinh);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
        } catch (ParseException ex) {
            Logger.getLogger(KhachHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNgaySinh.setDate(date);
        txtSDT.setText(SDT);
        String[] part = diaChi.split(" - ");
        String xa = part[0];
        String huyen = part[1];
        String tinh = part[2];
        cbTinh.setSelectedItem(tinh);
        cbHuyen.setSelectedItem(huyen);
        cbXa.setSelectedItem(xa);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tableKHMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tableKH.getModel();
        if (tableKH.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn vui lòng chọn 1 Khách hàng trong danh sách!!!");
        } else {
            if (txtTenKH.getText().equals("") || txtNgaySinh.getDate() == null || txtSDT.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống các trường thông tin!");
            } else if (cbTinh.getSelectedItem().toString().equals("--Chọn Tỉnh--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Tỉnh!");
            } else if (cbHuyen.getSelectedItem().toString().equals("--Chọn Huyện--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Huyện!");
            } else if (cbXa.getSelectedItem().toString().equals("--Chọn Xã--")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn Xã!");
            } else {
                Khachhang khachhang = new Khachhang();
                khachhang.setTenKhachhang(txtTenKH.getText());
                khachhang.setGioitinh(cbGioiTinh.getSelectedItem().toString());

                LocalDate ngaySinh = txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                khachhang.setNgaySinh(ngaySinh);

                khachhang.setSoDienthoai(txtSDT.getText());
                khachhang.setXa(xaCtl.getXa(cbXa.getSelectedItem().toString()));
                int maKH = Integer.parseInt(tableModel.getValueAt(tableKH.getSelectedRow(), 0).toString());
                khachhang.setMaKhachhang(maKH);
                khachhang.setKhachQuen(false);
                khachhang.setDaXoa(false);
                khCtl.suaKhachhang(khachhang);
                JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công!");
                reset();
            }
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        tableModel = (DefaultTableModel) tableKH.getModel();
        if (tableKH.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn vui lòng chọn 1 Khách hàng trong danh sách!!!");
        } else {
            String maKHStr = tableModel.getValueAt(tableKH.getSelectedRow(), 0).toString();
            int maKH = Integer.parseInt(maKHStr);
            khCtl.xoaKhachhang(maKH);
            JOptionPane.showMessageDialog(null, "Xóa thông tin khách hàng thành công!");
            reset();
        }
    }//GEN-LAST:event_btnXoaMouseClicked

    private void cbHuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHuyenActionPerformed

    private void txtSearchBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBoxKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            btnTimKiem.doClick();
        }
    }//GEN-LAST:event_txtSearchBoxKeyPressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchBox.getText();
        if (searchText.equals("") || searchText.equals(null)) {
            this.reset();
        } else {
            DefaultTableModel tblModel = (DefaultTableModel) tableKH.getModel();
            tblModel.getDataVector().removeAllElements();
            tblModel.fireTableDataChanged();
            if (dskh == null) {
                this.reset();
            }
            //dsThuoc = ThuocController.timkiemThuoc(searchText.toLowerCase());
            int stt = 0;
            if (dskh != null) {
                for (Khachhang khachhang : dskh) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String ngaysinh = khachhang.getNgaySinh().format(formatter);

                    //                String daxoa = km.isDaXoa() == true ? "Ðã xóa" : "";
                    if (String.valueOf(khachhang.getMaKhachhang()).contains(searchText) || khachhang.getTenKhachhang().contains(searchText) || String.valueOf(khachhang.getSoDienthoai()).contains(searchText) || ngaysinh.contains(searchText) || khachhang.getDiaChi().contains(searchText) || String.valueOf(khachhang.getNgaySinh().getDayOfMonth()).contains(searchText) || String.valueOf(khachhang.getNgaySinh().getMonth()).contains(searchText) || String.valueOf(khachhang.getNgaySinh().getYear()).contains(searchText) || String.valueOf(khachhang.getXa()).contains(searchText) || khachhang.getGioitinh().contains(searchText)) {
                        tblModel.addRow(new Object[]{
                            khachhang.getMaKhachhang(), khachhang.getTenKhachhang(), khachhang.getGioitinh(), ngaysinh, khachhang.getSoDienthoai(), khachhang.getDiaChi()
                        });
                    }

                }

            } else {
                JOptionPane.showMessageDialog(this, "Danh sách phiếu nhập rỗng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaiLenExcel;
    private javax.swing.JButton btnTaiXuongExcel;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbHuyen;
    private javax.swing.JComboBox<String> cbTinh;
    private javax.swing.JComboBox<String> cbXa;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTable tableKH;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private com.raven.chart.TextFieldSuggestion txtSearchBox;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
