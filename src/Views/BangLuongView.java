/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.LoaiNhanvienController;
import DBConnection.DBConnection;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author admin
 */
public class BangLuongView extends javax.swing.JFrame {

    private DefaultTableModel tableModel;

    /**
     * Creates new form BangLuongView
     */
    public BangLuongView() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbLuong.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tbLuong.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tbLuong.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tbLuong.getTableHeader().setOpaque(false);
        tbLuong.getTableHeader().setBackground(Color.YELLOW);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbLuong.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tbLuong.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        addDSLoaiNhanvien();
        LocalDate currentdate = LocalDate.now();
        int temp = currentdate.getMonthValue();
        String currentMonth = String.valueOf(currentdate.getMonthValue());
        cbMonth.setSelectedItem(currentMonth);
        getDataNVBanThuoc(temp);
    }

    public void getDataNVBanThuoc(int thang) {
        tableModel = (DefaultTableModel) tbLuong.getModel();
        tableModel.setRowCount(0);
        DBConnection con = new DBConnection();
        try {
            PreparedStatement pstmt = con.getConn().prepareStatement("{call dbo.layThongTinBangLuong(?)}");
            pstmt.setInt(1, thang);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                String tenNV = rs.getString("TenNhanVien");
                long luong = rs.getLong("Luong");
                long thuong = rs.getLong("Thuong");
                long tongLuong = rs.getLong("TongLuong");
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                if (tongLuong != 0) {
                    tableModel.addRow(new Object[]{maNV, tenNV, formatter.format(luong) + " VND", formatter.format(thuong) + " VND", formatter.format(tongLuong) + " VND"});
                }

            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
    }

    public void getDataNVBaoVe() {
        tableModel = (DefaultTableModel) tbLuong.getModel();
        tableModel.setRowCount(0);
        DBConnection con = new DBConnection();
        try {
            PreparedStatement pstmt = con.getConn().prepareStatement("{call dbo.layThongTinBangLuongBaoVe()}");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                String tenNV = rs.getString("TenNhanVien");
                long luong = rs.getLong("Luong");
                long thuong = rs.getLong("Thuong");
                long tongLuong = rs.getLong("TongLuong");
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                if (tongLuong != 0) {
                    tableModel.addRow(new Object[]{maNV, tenNV, formatter.format(luong) + " VND", formatter.format(thuong) + " VND", formatter.format(tongLuong) + " VND"});
                }

            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
    }

    public void getDataNVQuanLyKho() {
        tableModel = (DefaultTableModel) tbLuong.getModel();
        tableModel.setRowCount(0);
        DBConnection con = new DBConnection();
        try {
            PreparedStatement pstmt = con.getConn().prepareStatement("{call dbo.layThongTinBangLuongQuanLyKho()}");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                String tenNV = rs.getString("TenNhanVien");
                long luong = rs.getLong("Luong");
                long thuong = rs.getLong("Thuong");
                long tongLuong = rs.getLong("TongLuong");
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                if (tongLuong != 0) {
                    tableModel.addRow(new Object[]{maNV, tenNV, formatter.format(luong) + " VND", formatter.format(thuong) + " VND", formatter.format(tongLuong) + " VND"});
                }

            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
    }

    public final void addDSLoaiNhanvien() {
        LoaiNhanvienController loaiNVCtrl = new LoaiNhanvienController();
        ArrayList<String> dsLoaiNV = loaiNVCtrl.getDSTenLoaiNhanVien();
        for (String loaiNV : dsLoaiNV) {
            cbLoaiNV.addItem(loaiNV);
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

        lblMonth = new javax.swing.JLabel();
        cbMonth = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLuong = new javax.swing.JTable();
        btnQuayLai = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbLoaiNV = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bảng lương nhân viên");

        lblMonth.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMonth.setText("Tháng:");

        cbMonth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMonthItemStateChanged(evt);
            }
        });

        tbLuong.setBackground(new java.awt.Color(204, 255, 255));
        tbLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân viên", "Tên Nhân Viên", "Lương", "Thưởng", "Tổng Lương"
            }
        ));
        tbLuong.setRowHeight(30);
        jScrollPane1.setViewportView(tbLuong);

        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Bảng Lương Nhân viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Loại nhân viên:");

        cbLoaiNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbLoaiNV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLoaiNVItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(313, 313, 313))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(327, 327, 327)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonth)
                    .addComponent(jLabel3)
                    .addComponent(cbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiMouseClicked

    private void cbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMonthItemStateChanged
        // TODO add your handling code here:
        int month = Integer.parseInt(cbMonth.getSelectedItem().toString());
        getDataNVBanThuoc(month);
    }//GEN-LAST:event_cbMonthItemStateChanged

    private void cbLoaiNVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLoaiNVItemStateChanged
        // TODO add your handling code here:
        if (cbLoaiNV.getSelectedItem().toString().equals("Nhân viên bán thuốc")) {
            lblMonth.setVisible(true);
            cbMonth.setVisible(true);
            int month = Integer.parseInt(cbMonth.getSelectedItem().toString());
            getDataNVBanThuoc(month);
        } else {
            lblMonth.setVisible(false);
            cbMonth.setVisible(false);
            if (cbLoaiNV.getSelectedItem().toString().equals("Bảo vệ")) {
                getDataNVBaoVe();
            } else if (cbLoaiNV.getSelectedItem().toString().equals("Nhân viên quản lý kho")) {
                getDataNVQuanLyKho();
            }
        }

    }//GEN-LAST:event_cbLoaiNVItemStateChanged

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BangLuongView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BangLuongView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BangLuongView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BangLuongView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BangLuongView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JComboBox<String> cbLoaiNV;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JTable tbLuong;
    // End of variables declaration//GEN-END:variables
}
