package Views;

import Components.ExcelOperation;
import Models.Taikhoan;
import Controllers.TaikhoanController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TaikhoanView extends JPanel {
    
    TaikhoanController taikhoanCtrl = new TaikhoanController();
    DefaultTableModel tbModel;
    final int TENDANGNHAP_I = 1, MATKHAU_I = 2, MANHANVIEN_I = 3, MAQUYEN_I = 4;
    
    public TaikhoanView() {
        initComponents();
        tbModel = (DefaultTableModel)tblTaiKhoan.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblTaiKhoan.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tblTaiKhoan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblTaiKhoan.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tblTaiKhoan.getTableHeader().setOpaque(false);
        tblTaiKhoan.getTableHeader().setBackground(Color.YELLOW);       
        tblTaiKhoan.setUpdateSelectionOnSort(true);
        tblTaiKhoan.setFillsViewportHeight(true);
        tblTaiKhoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        txtTimKiem.setBorder(BorderFactory.createTitledBorder(" ")); //tạo border rỗng
        
        // buttons
        if (!LoginView.quyenLogin.getChitietQuyen().contains("qlTaiKhoan")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnTaiLenExcel.setEnabled(false);
        }
        
        refresh();
        
        tblTaiKhoan.addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String manhanvien = getSelectedRow(1);
                if (manhanvien != null) {
                    showInfo(manhanvien);
                }
            }
        });
        
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        
        btnLamMoi.addActionListener((ActionEvent ae) -> {
            refresh();
        });
//        btnTaiXuongExcel.addActionListener((ActionEvent ae) -> {
//            //new ExcelOperation();
//        });
//        btnTaiLenExcel.addActionListener((ActionEvent ae) -> {
//            new DocExcel().docFileExcelTaiKhoan();
//        });
        
    }
    
    private void showInfo(String _tentk) {
        if (_tentk != null) {
            // show hình
            for (Taikhoan tk : taikhoanCtrl.getDanhSachTaiKhoan()) {
                if (tk.getTaikhoan().equals(_tentk)) {
                    // show info
                    txtTenTaiKhoan.setText(tk.getTaikhoan());
                    txtPwd.setText(tk.getMatkhau());
                    txtMaNhanVien.setText(tk.getNv().getMaNhanvien() + " - " + tk.getNv().getTenNhanvien());
                    txtMaQuyen.setText(tk.getQ().getMaQuyen() + " - " + tk.getQ().getTenQuyen());                                      
                    txtTrangThai.setText(tk.isDaXoa()? "Đã tạm ẩn" : "Bình thường");
                    return;
                }
            }
        }
    }
    
    private void refresh() {
        taikhoanCtrl.readDB();
        txtTenTaiKhoan.setText("");
        txtMaNhanVien.setText("");
        txtMaQuyen.setText("");
        txtPwd.setText("");
        txtTimKiem.setText("");
        txtTrangThai.setText("");
        lblProfile.setIcon(null);
        loadDataToTable();
    }
    
    private void loadDataToTable(){
        tbModel.setRowCount(0);
        var taikhoanList = taikhoanCtrl.getDanhSachTaiKhoan();
        int stt = 0;
        for (Taikhoan tk : taikhoanList) {
            stt++;
            tbModel.addRow(new String[]{
                String.valueOf(stt),
                tk.getTaikhoan(),
                String.valueOf(tk.getMatkhau()),
                String.valueOf(tk.getNv().getMaNhanvien()),
                tk.getQ().getMaQuyen(),
                tk.isDaXoa()?"Đã tạm khóa":"Bình thường",
            });     
        }
    }
    private String getSelectedRow(int col) {
        int i = tblTaiKhoan.getSelectedRow();
        if (i >= 0) {
            int realI = tblTaiKhoan.convertRowIndexToModel(i);
            return tblTaiKhoan.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }
    private void btnThemMouseClicked(){
        
    }
    private void btnXoaMouseClicked(){
        System.out.println("1");
    }
    private void btnSuaMouseClicked(){
        System.out.println("2");
    }
    private void xuatFileExcelTaiKhoan(){
        System.out.println("3");
    }
    private void docFileExcelTaiKhoan(){
        System.out.println("4");
    }
//    private void txSearchOnChange() {
//        setDataToTable(quyenCtrl.search(txtTimKiem.getText(), cbbChonTimKiem.getSelectedItem().toString()), this.tblQuyen);
//    }
    
//    public String getSelectedRow(int col) {
//        int i = tblLayout.getTable().getSelectedRow();
//        if (i >= 0) {
//            int realI = tblLayout.getTable().convertRowIndexToModel(i);
//            return tblLayout.getModel().getValueAt(realI, col).toString();
//        }
//        return null;
//    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTenTaiKhoan = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cbbChonTimKiem = new javax.swing.JComboBox<>();
        btnTaiLenExcel = new javax.swing.JButton();
        btnTaiXuongExcel = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        txtMaQuyen = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        txtPwd = new javax.swing.JPasswordField();
        lblProfile = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1200, 745));

        txtTenTaiKhoan.setEditable(false);
        txtTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenTaiKhoan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");

        cbbChonTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbChonTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tên tài khoản", "Mã nhân viên", "Mã quyền", "Tài khoản đã bị ẩn" }));
        cbbChonTimKiem.setAlignmentX(0.0F);
        cbbChonTimKiem.setAlignmentY(0.0F);

        btnTaiLenExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiLenExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_ms_excel_30px.png"))); // NOI18N
        btnTaiLenExcel.setText("Nhập Excel");

        btnTaiXuongExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiXuongExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_downloads_30px.png"))); // NOI18N
        btnTaiXuongExcel.setText("Xuất Excel");

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách tài khoản hệ thống", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblTaiKhoan.setAutoCreateRowSorter(true);
        tblTaiKhoan.setBackground(new java.awt.Color(240, 240, 240));
        tblTaiKhoan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblTaiKhoan.setGridColor(new java.awt.Color(0, 0, 0));
        tblTaiKhoan.setRowHeight(40);
        tblTaiKhoan.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tblTaiKhoan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTaiKhoan.setShowGrid(true);
        jScrollPane2.setViewportView(tblTaiKhoan);
        tblTaiKhoan.getAccessibleContext().setAccessibleParent(tblTaiKhoan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        txtMaQuyen.setEditable(false);
        txtMaQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtPwd.setEditable(false);
        txtPwd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mật khẩu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        lblProfile.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh hồ sơ"));

        txtTrangThai.setEditable(false);
        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangThai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trạng thái", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtPwd))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(txtMaQuyen))
                        .addGap(35, 35, 35)
                        .addComponent(txtTrangThai))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnTimKiem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(95, 95, 95))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 169, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(39, 39, 39)
                .addComponent(btnSua)
                .addGap(39, 39, 39)
                .addComponent(btnXoa)
                .addGap(39, 39, 39)
                .addComponent(btnLamMoi)
                .addGap(39, 39, 39)
                .addComponent(btnTaiLenExcel)
                .addGap(40, 40, 40)
                .addComponent(btnTaiXuongExcel)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaiXuongExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLenExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaiLenExcel;
    private javax.swing.JButton btnTaiXuongExcel;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbChonTimKiem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaQuyen;
    private javax.swing.JPasswordField txtPwd;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
