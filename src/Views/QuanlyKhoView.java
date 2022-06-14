package Views;

import Controllers.HoadonController;
import Models.LoNhap;
import Controllers.LonhapController;
import Models.Donvitinh;
import Models.Thuoc;
import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author TinhBui
 */
public class QuanlyKhoView extends javax.swing.JPanel {

    private DefaultTableModel modelLN;
    LonhapController lnctrl = new LonhapController();
    HoadonController hdctr = new HoadonController();

    private int maLo1;
    private int maLo2;

    public QuanlyKhoView() {
        initComponents();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dsThuocConHSD.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) dsThuocConHSD.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dsThuocConHSD.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        dsThuocConHSD.getTableHeader().setOpaque(false);
        dsThuocConHSD.getTableHeader().setBackground(Color.YELLOW);
        TableColumnModel tcm = dsThuocConHSD.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(5);
        tcm.getColumn(1).setPreferredWidth(5);
        tcm.getColumn(2).setPreferredWidth(5);

        dsThuocHetHSD.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) dsThuocHetHSD.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dsThuocHetHSD.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        dsThuocHetHSD.getTableHeader().setOpaque(false);
        dsThuocHetHSD.getTableHeader().setBackground(Color.YELLOW);
        TableColumnModel tcm1 = dsThuocHetHSD.getColumnModel();
        tcm1.getColumn(0).setPreferredWidth(5);
        tcm1.getColumn(1).setPreferredWidth(5);
        tcm1.getColumn(2).setPreferredWidth(5);

        btnXoa1.setEnabled(false);
        btnXoa2.setEnabled(false);

        this.reset();
        this.setVaribleForThuoc();
    }

    public void setVaribleForThuoc() {
        List<Thuoc> dst;
        dst = hdctr.layDanhSachThuoc();
        for (Thuoc t : dst) {
            if (t instanceof Thuoc) {
                Thuoc th = (Thuoc) t;
                comboThuoc1.addItem(String.valueOf(th.getMaThuoc()) + " - " + th.getTenThuoc());
                comboThuoc2.addItem(String.valueOf(th.getMaThuoc()) + " - " + th.getTenThuoc());
            }
        }
    }

    public <T> void showData1(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        int index = 0;
        for (T t : list) {
            if (t instanceof LoNhap) {
                index++;
                LoNhap ln = (LoNhap) t;
                int soLuongQuidoi = ln.getSoluongConlai() / ln.getTiLeQuidoi();
                int soLuongLe = ln.getSoluongConlai() - soLuongQuidoi * ln.getTiLeQuidoi();

                String[] date1 = ln.getNgaySanXuat().toString().split("-");
                String ngaySX = date1[2] + "/" + date1[1] + "/" + date1[0];
                String[] date2 = ln.getNgayHetHan().toString().split("-");
                String ngayHH = date2[2] + "/" + date2[1] + "/" + date2[0];
                model.addRow(new Object[]{
                    index, ln.getMaLo(), ln.getMaThuoc(), ln.getTenThuoc(), soLuongQuidoi + " " + ln.getTenDonvitinh() + " + " + soLuongLe + " " + ln.getTenDonviBanLe() + " (" + ln.getTenDonvitinh() + " " + ln.getTiLeQuidoi() + " " + ln.getTenDonviBanLe() + ")", ngaySX, ngayHH
                });

            }
        }
    }

    public <T> void showData2(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        int index = 0;

        for (T t : list) {
            if (t instanceof LoNhap) {
                index++;
                LoNhap ln = (LoNhap) t;
                int soLuongQuidoi = ln.getSoluongConlai() / ln.getTiLeQuidoi();
                int soLuongLe = ln.getSoluongConlai() - soLuongQuidoi * ln.getTiLeQuidoi();

                LocalDate now = LocalDate.now();
                Duration diff = Duration.between(now.atStartOfDay(), ln.getNgayHetHan().atStartOfDay());
                long diffDays = diff.toDays();
                model.addRow(new Object[]{
                    index, ln.getMaLo(), ln.getMaThuoc(), ln.getTenThuoc(), soLuongQuidoi + " " + ln.getTenDonvitinh() + " + " + soLuongLe + " " + ln.getTenDonviBanLe() + " (" + ln.getTenDonvitinh() + " " + ln.getTiLeQuidoi() + " " + ln.getTenDonviBanLe() + ")", -diffDays + " ngày"
                });

            }
        }
    }

    public void showThongKeThuoc(LoNhap ln, DefaultTableModel model) {  
        model.setRowCount(0);
        int soLuongQuidoi = ln.getSoluongConlai() / ln.getTiLeQuidoi();
        int soLuongLe = ln.getSoluongConlai() - soLuongQuidoi * ln.getTiLeQuidoi();

        model.addRow(new Object[]{
            "", "", ln.getMaThuoc(), ln.getTenThuoc(), soLuongQuidoi + " " + ln.getTenDonvitinh() + " + " + soLuongLe + " " + ln.getTenDonviBanLe() + " (" + ln.getTenDonvitinh() + " " + ln.getTiLeQuidoi() + " " + ln.getTenDonviBanLe() + ")", "", ""
        });

    }

    public void reset() {
        
        int soLuong = lnctrl.tinhSoLoaiThuoc();
        lbTongThuoc.setText("Tổng số loại thuốc trong kho: " + soLuong);
        
        modelLN = (DefaultTableModel) dsThuocConHSD.getModel();
        List<LoNhap> dsln;
        dsln = lnctrl.layDanhsachLNConHSD();
        this.showData1(dsln, modelLN);

        modelLN = (DefaultTableModel) dsThuocHetHSD.getModel();
        List<LoNhap> dsln1;
        dsln1 = lnctrl.layDanhsachLNHetHSD();
        this.showData2(dsln1, modelLN);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dsThuocConHSD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        dsThuocHetHSD = new javax.swing.JTable();
        btnXoa1 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        comboThuoc1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnXem1 = new javax.swing.JButton();
        btnXem2 = new javax.swing.JButton();
        comboThuoc2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbTongThuoc = new javax.swing.JLabel();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lô thuốc còn hạn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        dsThuocConHSD.setBackground(new java.awt.Color(204, 255, 255));
        dsThuocConHSD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dsThuocConHSD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã lô nhập", "Mã thuốc", "Tên thuốc", "Số lượng còn lại", "Ngày sản xuất", "Ngày hết hạn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsThuocConHSD.setRowHeight(30);
        dsThuocConHSD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsThuocConHSDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dsThuocConHSD);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lô thuốc hết hạn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 22), new java.awt.Color(204, 0, 0))); // NOI18N

        dsThuocHetHSD.setBackground(new java.awt.Color(204, 255, 255));
        dsThuocHetHSD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dsThuocHetHSD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã lô nhập", "Mã thuốc", "Tên thuốc", "Số lượng còn lại", "Đã hết hạn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsThuocHetHSD.setRowHeight(30);
        dsThuocHetHSD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsThuocHetHSDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dsThuocHetHSD);

        btnXoa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnXoa2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa2.setText("Xóa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_show_property_30px.png"))); // NOI18N
        jButton2.setText("Thuốc sắp hết hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnLammoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnLammoi.setText("Làm mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        comboThuoc1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboThuoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboThuoc1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Chọn thuốc cần thống kê: ");

        btnXem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_antibiotic_30px.png"))); // NOI18N
        btnXem1.setText("Xem");
        btnXem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXem1ActionPerformed(evt);
            }
        });

        btnXem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_antibiotic_30px.png"))); // NOI18N
        btnXem2.setText("Xem");
        btnXem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXem2ActionPerformed(evt);
            }
        });

        comboThuoc2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboThuoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboThuoc2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Chọn thuốc cần thống kê: ");

        lbTongThuoc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLammoi)
                            .addComponent(jLabel1))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboThuoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXem1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1177, Short.MAX_VALUE)
                                .addComponent(btnXoa1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTongThuoc))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(67, 67, 67)
                        .addComponent(comboThuoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXem2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1175, Short.MAX_VALUE)
                        .addComponent(btnXoa2)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongThuoc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLammoi)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboThuoc1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa1)
                        .addComponent(jLabel1)
                        .addComponent(btnXem1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboThuoc2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(btnXem2))
                    .addComponent(btnXoa2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn lô thuôc " + maLo1 + " này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            lnctrl.xoaLonhap(maLo1);
            this.reset();
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void dsThuocConHSDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsThuocConHSDMouseClicked
        // TODO add your handling code here:
        btnXoa1.setEnabled(true);
        int indexOfList;
        JTable source = (JTable) evt.getSource();
        indexOfList = source.rowAtPoint(evt.getPoint());
        String s = source.getModel().getValueAt(indexOfList, 0).toString();

        maLo1 = Integer.parseInt(s);

    }//GEN-LAST:event_dsThuocConHSDMouseClicked

    private void dsThuocHetHSDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsThuocHetHSDMouseClicked
        // TODO add your handling code here:
        btnXoa2.setEnabled(true);
        int indexOfList;
        JTable source = (JTable) evt.getSource();
        indexOfList = source.rowAtPoint(evt.getPoint());
        String s = source.getModel().getValueAt(indexOfList, 0).toString();

        maLo2 = Integer.parseInt(s);
    }//GEN-LAST:event_dsThuocHetHSDMouseClicked

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn lô thuôc " + maLo2 + " đã hết hạn này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            lnctrl.xoaLonhap(maLo2);
            this.reset();
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn tải lại trang hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            this.reset();

        }
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new ThuocSapHetHang().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboThuoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboThuoc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboThuoc1ActionPerformed

    private void btnXem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXem1ActionPerformed
        // TODO add your handling code here:
        String maThuoc = comboThuoc1.getSelectedItem().toString().split(" - ")[0];
        LoNhap ln = new LoNhap();
        ln = lnctrl.layLonhapTheoMaThuoc(Integer.parseInt(maThuoc));

        modelLN = (DefaultTableModel) dsThuocConHSD.getModel();
        this.showThongKeThuoc(ln, modelLN);
    }//GEN-LAST:event_btnXem1ActionPerformed

    private void btnXem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXem2ActionPerformed
        // TODO add your handling code here:
        String maThuoc = comboThuoc2.getSelectedItem().toString().split(" - ")[0];
        LoNhap ln = new LoNhap();
        ln = lnctrl.layLonhapTheoMaThuocHetHSD(Integer.parseInt(maThuoc));

        modelLN = (DefaultTableModel) dsThuocHetHSD.getModel();
        this.showThongKeThuoc(ln, modelLN);
    }//GEN-LAST:event_btnXem2ActionPerformed

    private void comboThuoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboThuoc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboThuoc2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnXem1;
    private javax.swing.JButton btnXem2;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JComboBox<String> comboThuoc1;
    private javax.swing.JComboBox<String> comboThuoc2;
    private javax.swing.JTable dsThuocConHSD;
    private javax.swing.JTable dsThuocHetHSD;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTongThuoc;
    // End of variables declaration//GEN-END:variables
}
