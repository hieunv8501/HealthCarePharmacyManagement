/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import Controllers.HoadonController;
import Controllers.LonhapController;

import Models.ChitietHoadon;
import Models.Thuoc;
import Models.LoNhap;
import Models.Donvitinh;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TinhBui
 */
public class ChitietHoadonView extends javax.swing.JFrame {

    /**
     * Creates new form CTHD
     */
    private int maHoadon;
    HoadonController hdctr = new HoadonController();
    private DefaultTableModel modelCTHD;
    private int indexOfList;

    public ChitietHoadonView() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dsCTHD.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) dsCTHD.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dsCTHD.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        dsCTHD.getTableHeader().setOpaque(false);
        dsCTHD.getTableHeader().setBackground(Color.YELLOW);
         if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlHoaDon")) {
            btnThem.setEnabled(false);
            
        }
        txtMHD.setEditable(false);
        txtDonvi.setEditable(false);
        txtDongia.setEditable(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    }

    public <T> void showData(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (T t : list) {
            if (t instanceof ChitietHoadon) {
                ChitietHoadon cthd = (ChitietHoadon) t;
                String soluong = cthd.getSoluong() + " : " + cthd.getTenDonvitinh();

                model.addRow(new Object[]{
                    cthd.getMaThuoc(), cthd.getTenThuoc(), cthd.getMaLo(), soluong, cthd.getDongia()
                });

            }
        }
    }

    public void reset() {
        modelCTHD = (DefaultTableModel) dsCTHD.getModel();
        List<ChitietHoadon> cthd;
        cthd = hdctr.layDanhsachCTHD(maHoadon);
        this.showData(cthd, modelCTHD);
    }

    public void setVariable(int mhd) {
        this.maHoadon = mhd;
        txtMHD.setText(String.valueOf(this.maHoadon));

        List<Thuoc> dst;
        dst = hdctr.layDanhSachThuoc();
        for (Thuoc t : dst) {
            if (t instanceof Thuoc) {
                Thuoc th = (Thuoc) t;
                comboThuoc.addItem(String.valueOf(th.getMaThuoc()) + " - " + th.getTenThuoc());
            }
        }

        this.reset();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMHD = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dsCTHD = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboThuoc = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboLonhap = new javax.swing.JComboBox<>();
        spinSoluong = new com.toedter.components.JSpinField();
        jLabel4 = new javax.swing.JLabel();
        txtDonvi = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDongia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Chi tiết hóa đơn");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuốc trong hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 204))); // NOI18N

        dsCTHD.setBackground(new java.awt.Color(204, 255, 255));
        dsCTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dsCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuốc", "Tên thuốc", "Lô nhập", "Số lượng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsCTHD.setRowHeight(30);
        dsCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsCTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dsCTHD);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Ðơn vị:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Đơn giá:");

        comboThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboThuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboThuocItemStateChanged(evt);
            }
        });
        comboThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboThuocActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Chọn thuốc:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Chọn lô nhập:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Số lượng:");

        comboLonhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboLonhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLonhapActionPerformed(evt);
            }
        });

        spinSoluong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Mã hóa đơn:");

        txtDonvi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-back-arrow-30.png"))); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboThuoc, 0, 225, Short.MAX_VALUE)
                                    .addComponent(txtMHD)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(spinSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtDongia))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtDonvi))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(comboLonhap, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 338, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnThem)
                .addGap(52, 52, 52)
                .addComponent(btnLammoi)
                .addGap(52, 52, 52)
                .addComponent(btnSua)
                .addGap(52, 52, 52)
                .addComponent(btnXoa)
                .addGap(52, 52, 52)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboLonhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtDonvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spinSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnBack)
                    .addComponent(btnLammoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa thuốc này khỏi hóa đơn không không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];
            String maLo1 = comboLonhap.getSelectedItem().toString().split(" - ")[0];
            String maLo2 = maLo1.toString().split(": ")[1];

            ChitietHoadon cthd = new ChitietHoadon(maHoadon, Integer.parseInt(maThuoc), Integer.parseInt(maLo2), 0, 0, false);
            hdctr.xoaCTHD(cthd);
            this.reset();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];
        String maLo1 = comboLonhap.getSelectedItem().toString().split(" - ")[0];
        String maLo2 = maLo1.toString().split(": ")[1];

        ChitietHoadon cthd = new ChitietHoadon(this.maHoadon, Integer.parseInt(maThuoc), Integer.parseInt(maLo2), Integer.valueOf(spinSoluong.getValue()), Float.parseFloat(txtDongia.getText()), false);

        hdctr.themCTHD(cthd);

        this.reset();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];
        String maLo1 = comboLonhap.getSelectedItem().toString().split(" - ")[0];
        String maLo2 = maLo1.toString().split(": ")[1];
        ChitietHoadon cthd = new ChitietHoadon(maHoadon, Integer.parseInt(maThuoc), Integer.parseInt(maLo2), Integer.valueOf(spinSoluong.getValue()), Float.parseFloat(txtDongia.getText()), false);
        hdctr.capnhatCTHD(cthd);
        this.reset();

        LonhapController lnctr = new LonhapController();
        LoNhap ln = new LoNhap();
        ln = lnctr.layLoNhap(Integer.parseInt(maLo2));
        comboLonhap.removeAllItems();
        comboLonhap.addItem("Mã lô: " + ln.getMaLo() + " - Số lượng còn lại: " + ln.getSoluongConlai());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void comboThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboThuocItemStateChanged
        // TODO add your handling code here:
        Donvitinh dvt = new Donvitinh();
        String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];

        dvt = hdctr.layDonvitinh(Integer.valueOf(maThuoc));
        txtDonvi.setText(dvt.getTenDonvitinh());

        Thuoc thuoc = new Thuoc();

        thuoc = hdctr.layGiathuoc(Integer.valueOf(maThuoc));
        txtDongia.setText(String.valueOf(thuoc.getGiaBan()));

        comboLonhap.removeAllItems();
        List<LoNhap> dsln;
        dsln = hdctr.layDanhSachLonhap(Integer.valueOf(spinSoluong.getValue()), Integer.valueOf(maThuoc));
        for (LoNhap t : dsln) {
            if (t instanceof LoNhap) {
                LoNhap ln = (LoNhap) t;
                comboLonhap.addItem("Mã lô: " + String.valueOf(ln.getMaLo()) + " - Số lượng còn lại: " + ln.getSoluongConlai());
            }
        }
    }//GEN-LAST:event_comboThuocItemStateChanged

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void dsCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsCTHDMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        indexOfList = source.rowAtPoint(evt.getPoint());
        String s1 = source.getModel().getValueAt(indexOfList, 0).toString();
        String s2 = source.getModel().getValueAt(indexOfList, 1).toString();
        String s3 = source.getModel().getValueAt(indexOfList, 2).toString();
        String s4 = source.getModel().getValueAt(indexOfList, 3).toString();
        String s5 = source.getModel().getValueAt(indexOfList, 4).toString();

        comboThuoc.setSelectedItem(s1 + " - " + s2);
        String soluong = s4.split(" : ")[0];
        spinSoluong.setValue(Integer.parseInt(soluong));
        LonhapController lnctr = new LonhapController();
        LoNhap ln = new LoNhap();
        ln = lnctr.layLoNhap(Integer.parseInt(s3));
        comboLonhap.removeAllItems();
        comboLonhap.addItem("Mã lô: " + ln.getMaLo() + " - Số lượng còn lại: " + ln.getSoluongConlai());
        txtDongia.setText(s5);

        Donvitinh dvt = new Donvitinh();
        String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];

        dvt = hdctr.layDonvitinh(Integer.valueOf(maThuoc));
        txtDonvi.setText(dvt.getTenDonvitinh());

        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        comboThuoc.setEnabled(false);
        comboLonhap.setEnabled(false);


    }//GEN-LAST:event_dsCTHDMouseClicked

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn tải lại trang hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            comboLonhap.setEnabled(true);
            comboThuoc.setEnabled(true);

            this.reset();
        }

    }//GEN-LAST:event_btnLammoiActionPerformed

    private void comboThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboThuocActionPerformed

    private void comboLonhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLonhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLonhapActionPerformed

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
//            java.util.logging.Logger.getLogger(ChitietHoadonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChitietHoadonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChitietHoadonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChitietHoadonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChitietHoadonView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> comboLonhap;
    private javax.swing.JComboBox<String> comboThuoc;
    private javax.swing.JTable dsCTHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField spinSoluong;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtDonvi;
    private javax.swing.JTextField txtMHD;
    // End of variables declaration//GEN-END:variables
}
