package Views;

import Controllers.DonvitinhController;
import Controllers.HoadonController;
import Controllers.NhacungcapController;
import Controllers.NhanvienController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import Controllers.PhieunhapController;

import Models.Thuoc;
import Models.ChitietPhieunhap;
import Models.Donvitinh;
import Models.Nhacungcap;
import Models.Nhanvien;
import Models.Phieunhap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import javax.mail.FetchProfile;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class ChitietPhieunhapView extends JFrame {

    private int maPhieunhap;
    PhieunhapController pnCtrl = new PhieunhapController();
    private DefaultTableModel modelCTPN;
    //private int indexOfList;
    ArrayList<ChitietPhieunhap> dsctpn = new ArrayList<>();
    ArrayList<Thuoc> dst = new ArrayList<>();
    ArrayList<Nhanvien> dsnv = new ArrayList<>();
    ArrayList<Nhacungcap> dsncc = new ArrayList<>();
    ArrayList<Donvitinh> dsdvt = new ArrayList<>();
    HoadonController hdCtrl = new HoadonController();

    public ChitietPhieunhapView(String _maPN) {
        initComponents();

        this.setVisible(true);
        this.setSize(1200, 600);
        this.maPhieunhap = Integer.valueOf(_maPN);
        this.setTitle("Quản lý chi tiết phiếu nhập");
        this.setLocationRelativeTo(null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblCTPN.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tblCTPN.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblCTPN.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tblCTPN.getTableHeader().setOpaque(false);
        tblCTPN.getTableHeader().setBackground(Color.YELLOW);
        

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        showData(pnCtrl.layDanhsachChitietPhieunhapTheoMaPhieuNhap(maPhieunhap));

//        tblCTPN.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent me) {
//                String malo = getSelectedRow(1);
//                if (malo != null) {
//                    //showInfo(malo);
//                }
//            }
//        });
    }

    public String getSelectedRow(int col) {
        int i = tblCTPN.getSelectedRow();
        if (i >= 0) {
            int realI = tblCTPN.convertRowIndexToModel(i);
            return tblCTPN.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void showData(ArrayList<ChitietPhieunhap> dsctpn) {
        modelCTPN = (DefaultTableModel) tblCTPN.getModel();
        modelCTPN.setRowCount(0);
        int stt = 0;
        for (ChitietPhieunhap ctpn : dsctpn) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nSX = dateFormat.format(ctpn.getNgaySanxuat().getTime());
            String nHH = dateFormat.format(ctpn.getNgayHethan().getTime());

            String soluong = ctpn.getTenDonvitinh() + " " + ctpn.getTileQuidoi() + " " + ctpn.getTenDonvibanle() + " x" + ctpn.getSoluong();
            modelCTPN.addRow(new Object[]{
                ++stt, ctpn.getMaLo(), ctpn.getMaThuoc(), ctpn.getTenThuoc(), ctpn.getTenloaiThuoc(), nSX, nHH, soluong, ctpn.getDongia(), ctpn.getThanhtien()
            });
        }
    }

//    public void setVariable(int _mpn) {
//        
//        
//    }

//    private void showInfo(String _malo) {
//
//        if (_malo != null) {
//            for (ChitietPhieunhap ctpn : dsctpn) {
//                if (String.valueOf(ctpn.getMaLo()).equals(_malo)) {
//                    dsncc = NhacungcapController.getDanhSachNhacungcap();
//                    dsdvt = DonvitinhController.getDanhSachDonvitinh();
//                    dsnv = NhanvienController.getDanhSachNhanvien();
//                    dst = hdCtrl.layDanhSachThuoc();
//                    for (Thuoc t : dst) {
////                        cbbThuoc.addItem(String.valueOf(t.getMaThuoc()) + " - " + t.getTenThuoc());
////                        cbbThuoc.setSelectedItem(t.getMaThuoc() == ctpn.getMaThuoc()? t : "");
//                    }
//                    
//                    for (Nhanvien nv : dsnv) {
//                        cbbNhanVien.addItem(String.valueOf(nv.getMaNhanvien() + " - " + nv.getTenNhanvien()));
//                       //cbbNhanVien.setSelectedItem(nv.getMaNhanvien() == ? nv : "");
//                    }
//
//                    for (Nhacungcap ncc : dsncc) {
//                        cbbNhaCungCap.addItem(String.valueOf(ncc.getMaNhacungcap() + " - " + ncc.getTenNhacungcap()));
//                    }
//
//                    for (Donvitinh dvt : dsdvt) {
//                        cbbDVT.addItem(String.valueOf(dvt.getMaDonvitinh() + " - " + dvt.getTenDonvitinh()));
//                    }
//                    // show info
//                    txtMaPN.setText(String.valueOf(ctpn.getMaPhieunhap()));
//                    txtMaLN.setText(String.valueOf(ctpn.getMaLo()));
//                    txtDongia.setText(String.valueOf(ctpn.getDongia()));
//                    System.out.println(ctpn.getSoluong());
//
//                    return;
//                }
//            }
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Chi tiết phiếu nhập");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách các lô nhập", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 204))); // NOI18N

        tblCTPN.setBackground(new java.awt.Color(204, 255, 255));
        tblCTPN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Lô nhập", "Mã thuốc", "Tên thuốc", "Tên loại thuốc", "Ngày sản xuất", "Ngày hết hạn", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPN.setRowHeight(30);
        tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPN);

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-back-arrow-30.png"))); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnHuy.setText("Làm mới");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(btnHuy)
                .addGap(117, 117, 117)
                .addComponent(btnBack)
                .addContainerGap(360, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnHuy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPNMouseClicked
        // TODO add your handling code here:
//        JTable source = (JTable) evt.getSource();
//        indexOfList = source.rowAtPoint(evt.getPoint());
//        String s1 = source.getModel().getValueAt(indexOfList, 0).toString();
//        String s2 = source.getModel().getValueAt(indexOfList, 1).toString();
//        String s3 = source.getModel().getValueAt(indexOfList, 2).toString();
//        String s4 = source.getModel().getValueAt(indexOfList, 3).toString();
//        String s5 = source.getModel().getValueAt(indexOfList, 4).toString();
//
//        cbbThuoc.setSelectedItem(s1 + " - " + s2);
//        String soluong = s4.split(" : ")[0];
//        spinSoluong.setValue(Integer.parseInt(soluong));
//        LonhapController lnctr = new LonhapController();
//        LoNhap ln = new LoNhap();
//        ln = lnctr.layLoNhap(Integer.parseInt(s3));
//        //cbremoveAllItems();
//        //comboLonhap.addItem("Mã lô: " + ln.getMaLo() + " - Số lượng còn lại: " + ln.getSoluongConlai());
//        txtDongia.setText(s5);
//
//        Donvitinh dvt = new Donvitinh();
//        String maThuoc = cbbThuoc.getSelectedItem().toString().split(" - ")[0];
//
//        dvt = pnCtrl.layDonvitinh(Integer.valueOf(maThuoc));
//        //txtDonvi.setText(dvt.getTenDonvitinh());
//
//        btnSua.setEnabled(true);
//        btnXoa.setEnabled(true);
//        cbbThuoc.setEnabled(false);
//        //comboLonhap.setEnabled(false);


    }//GEN-LAST:event_tblCTPNMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
//        int input = JOptionPane.showConfirmDialog(null,
//            "Bạn có chắc muốn tải lại trang hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (input == 0) {
//            comboNhanvien.removeAllItems();
//            //            comboKhachhang.removeAllItems();
//            //            comboMaKM.removeAllItems();
//            dateLap.setCalendar(null);
//            gio.setValue(0);
//            phut.setValue(0);
//            giay.setValue(0);
//            txtMHD.setText(null);
//
//            btnLuu.setEnabled(false);
//            btnSua.setEnabled(false);
//            btnThem.setEnabled(true);
//            btnChonKH.setEnabled(false);
//            txtMKH.setText("");
//            txtMKM.setText("");
//            btnChonMKM.setEnabled(false);
//
//            this.reset();
//
//        }
    }//GEN-LAST:event_btnHuyActionPerformed

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
    private javax.swing.JButton btnHuy;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTPN;
    // End of variables declaration//GEN-END:variables
}
