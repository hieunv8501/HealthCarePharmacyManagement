package Views;

import Models.Phieunhap;
import Controllers.PhieunhapController;
import Helpers.PriceFormatter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieunhapView extends JPanel {

    PhieunhapController phieunhapCtrl = new PhieunhapController();
    ArrayList<Phieunhap> dsPhieuNhap = phieunhapCtrl.layDanhsachPhieuNhap();
    DefaultTableModel tbModel;

    public PhieunhapView() {
        initComponents();
        tbModel = (DefaultTableModel) tblPhieuNhap.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuNhap.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tblPhieuNhap.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblPhieuNhap.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tblPhieuNhap.getTableHeader().setOpaque(false);
        tblPhieuNhap.getTableHeader().setBackground(Color.YELLOW);
        tblPhieuNhap.setUpdateSelectionOnSort(true);
        tblPhieuNhap.setFillsViewportHeight(true);
        tblPhieuNhap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        txtTimKiem.setBorder(BorderFactory.createTitledBorder(" ")); //t0ạo border rỗng
        dateLap.setEnabled(false);
        gio.setEnabled(false);
        phut.setEnabled(false);
        giay.setEnabled(false);
        // buttons
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlPhieuNhap")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnTaiXuongExcel.setEnabled(false);
            btnTaiLenExcel.setEnabled(false);
        }
        tblPhieuNhap.addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String mapn = getSelectedRow(1);
                if (mapn != null) {
                    showInfo(mapn);
                }
            }
        });
        btnXemCT.addActionListener((ae) -> {
            btnXemChitietPhieunhapMouseClicked();
        });
        refresh();

    }

    public String getSelectedRow(int col) {
        int i = tblPhieuNhap.getSelectedRow();
        if (i >= 0) {
            int realI = tblPhieuNhap.convertRowIndexToModel(i);
            return tblPhieuNhap.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void refresh() {
        //PhieunhapController.layDanhsachPhieuNhap();
        loadDataToTable();
    }

    private void loadDataToTable() {
        tbModel.setRowCount(0);
        int stt = 0;
        for (Phieunhap pn : dsPhieuNhap) {
            stt++;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formatted = format1.format(pn.getNgayNhap().getTime());
            tbModel.addRow(new String[]{
                String.valueOf(stt),
                String.valueOf(pn.getMaPhieunhap()),
                String.valueOf(pn.getNcc().getMaNhacungcap()),
                String.valueOf(pn.getNv().getMaNhanvien()),
                String.valueOf(formatted),
                String.valueOf(pn.getTongTien()),
                pn.isDaXoa() ? "Đã tạm khóa" : "Bình thường",});
        }
    }

    private void btnXemChitietPhieunhapMouseClicked() {
        int i = tblPhieuNhap.getSelectedRow();
        if (i >= 0) {
            ChitietPhieunhapView ctpnView = new ChitietPhieunhapView(txtMaPN.getText());
            ctpnView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để xem!");
        }
    }

    public void showInfo(String mapn) {
        if (mapn != null) {
            // show hình
            for (Phieunhap pn : dsPhieuNhap) {
                if (String.valueOf(pn.getMaPhieunhap()).equals(mapn)) {
                    // show info
                    txtMaPN.setText(String.valueOf(pn.getMaPhieunhap()));
                    txtNCC.setText(pn.getNcc().getMaNhacungcap()+ " - " + pn.getNcc().getTenNhacungcap());
                    txtNV.setText(pn.getNv().getMaNhanvien() + " - " + pn.getNv().getTenNhanvien());
                    dateLap.setCalendar(pn.getNgayNhap());
                    //gio.setValue(pn.getNgayNhap().getTime().getHours());
                    //phut.setValue(pn.getNgayNhap().getTime().getHours());
                    //giay.setValue(pn.getNgayNhap().getTime().getHours());
                    txtTongTien.setText(String.valueOf(pn.getTongTien()));
                    return;
                }
            }
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

        txtNV = new javax.swing.JTextField();
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
        tblPhieuNhap = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        gio = new javax.swing.JSpinner();
        phut = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        giay = new javax.swing.JSpinner();
        dateLap = new com.toedter.calendar.JDateChooser();
        btnXemCT = new javax.swing.JButton();
        txtMaPN = new javax.swing.JTextField();
        txtNCC = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1200, 745));

        txtNV.setEditable(false);
        txtNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

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
        cbbChonTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã Quyền", "Tên Quyền", "Chi Tiết Quyền", "Quyền Đã Ẩn" }));
        cbbChonTimKiem.setAlignmentX(0.0F);
        cbbChonTimKiem.setAlignmentY(0.0F);

        btnTaiLenExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiLenExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_ms_excel_30px.png"))); // NOI18N
        btnTaiLenExcel.setText("Tải lên Excel");

        btnTaiXuongExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaiXuongExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_downloads_30px.png"))); // NOI18N
        btnTaiXuongExcel.setText("Tải xuống Excel");

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu nhập hệ thống", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 51, 255));

        tblPhieuNhap.setAutoCreateRowSorter(true);
        tblPhieuNhap.setBackground(new java.awt.Color(240, 240, 240));
        tblPhieuNhap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 0), null, null));
        tblPhieuNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập", "Ngày nhập", "Tổng tiền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPhieuNhap.setGridColor(new java.awt.Color(0, 0, 0));
        tblPhieuNhap.setRowHeight(40);
        tblPhieuNhap.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tblPhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPhieuNhap.setShowGrid(true);
        jScrollPane2.setViewportView(tblPhieuNhap);
        tblPhieuNhap.getAccessibleContext().setAccessibleParent(tblPhieuNhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setText("Giờ:");

        gio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        phut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setText("Phút:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setText("Giây:");

        giay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        dateLap.setDateFormatString("dd-MM-yyyy");
        dateLap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateLap.setMinimumSize(new java.awt.Dimension(56, 56));

        btnXemCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXemCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_agreement_30px.png"))); // NOI18N
        btnXemCT.setText("Xem chi tiết");
        btnXemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTActionPerformed(evt);
            }
        });

        txtMaPN.setEditable(false);
        txtMaPN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtNCC.setEditable(false);
        txtNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(btnThem)
                                .addGap(39, 39, 39)
                                .addComponent(btnSua)
                                .addGap(39, 39, 39)
                                .addComponent(btnXoa)
                                .addGap(39, 39, 39)
                                .addComponent(btnLamMoi)
                                .addGap(39, 39, 39)
                                .addComponent(btnTaiLenExcel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(368, 368, 368)
                                .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnTimKiem)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTaiXuongExcel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNV)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(giay, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(txtNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLap, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(giay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(phut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLenExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiXuongExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemCT))
                .addGap(47, 47, 47)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
      
    }//GEN-LAST:event_btnXemCTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaiLenExcel;
    private javax.swing.JButton btnTaiXuongExcel;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbChonTimKiem;
    private com.toedter.calendar.JDateChooser dateLap;
    private javax.swing.JSpinner giay;
    private javax.swing.JSpinner gio;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner phut;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
