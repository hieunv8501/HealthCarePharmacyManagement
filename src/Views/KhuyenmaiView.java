/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controllers.KhuyenmaiController;
import Models.Khuyenmai;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TinhBui
 */
public class KhuyenmaiView extends javax.swing.JPanel {

    /**
     * Creates new form KhuyenmaiView
     */
    Calendar cld = Calendar.getInstance();
    KhuyenmaiController kmctr = new KhuyenmaiController();
    private DefaultTableModel modelMKM;
    private int indexOfList;

    public KhuyenmaiView() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dsMaKM.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) dsMaKM.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dsMaKM.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        dsMaKM.getTableHeader().setOpaque(false);
        dsMaKM.getTableHeader().setBackground(Color.YELLOW);
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlKhuyenMai")) {
            btnThem.setEnabled(false);
        }
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMKM.setEditable(false);

        this.reset();
    }

    public String getSelectedRow(int col) {
        int i = dsMaKM.getSelectedRow();
        if (i >= 0) {
            int realI = dsMaKM.convertRowIndexToModel(i);
            return dsMaKM.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public <T> void showData(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (T t : list) {
            if (t instanceof Khuyenmai) {
                Khuyenmai km = (Khuyenmai) t;
                String[] date1 = km.getNgayBatdau().toString().split("-");
                String ngayBD = date1[2] + "/" + date1[1] + "/" + date1[0];
                String[] date2 = km.getNgayKetthuc().toString().split("-");
                String ngayKT = date2[2] + "/" + date2[1] + "/" + date2[0];
//                String daxoa = km.isDaXoa() == true ? "Ðã xóa" : "";
                model.addRow(new Object[]{
                    km.getMaKhuyenmai(), km.getTenKhuyenmai(), km.getDieukienKhuyenmai(), km.getPhantramKhuyenmai(), ngayBD, ngayKT
                });

            }
        }
    }

    public void reset() {
        modelMKM = (DefaultTableModel) dsMaKM.getModel();
        List<Khuyenmai> dskm;
        dskm = kmctr.layDanhsachMKM();
        this.showData(dskm, modelMKM);
    }

    public String randomMKM() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public String CheckMKM(String mkm) {
        List<Khuyenmai> dskm;
        dskm = kmctr.layDanhsachMKMAll();
        for (Khuyenmai t : dskm) {
            if (t instanceof Khuyenmai) {
                Khuyenmai km = (Khuyenmai) t;
                if (mkm.equalsIgnoreCase(km.getMaKhuyenmai())) {
                    return CheckMKM(randomMKM());
                }

            }
        }
        return mkm;
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
        dsMaKM = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMKM = new javax.swing.JTextField();
        txtTKM = new javax.swing.JTextField();
        txtDKKM = new javax.swing.JTextField();
        txtPTKM = new javax.swing.JTextField();
        dateBD = new com.toedter.calendar.JDateChooser();
        dateKT = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        searchBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách mã khuyến mãi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        dsMaKM.setBackground(new java.awt.Color(204, 255, 255));
        dsMaKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dsMaKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện", "Phần trăm khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
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
        dsMaKM.setRowHeight(30);
        dsMaKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsMaKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dsMaKM);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Mã khuyến mãi:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Tên khuyến mãi:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel3.setText("Điều kiện khuyến mãi:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel4.setText("Phần trăm khuyến mãi:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Ngày bắt đầu: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel6.setText("Ngày kết thúc:");

        txtMKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKMActionPerformed(evt);
            }
        });

        txtTKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDKKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtPTKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateBD.setDateFormatString("dd-MM-yyyy");
        dateBD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateKT.setDateFormatString("dd-MM-yyyy");
        dateKT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-30.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        searchBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchBox.setAutoscrolls(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Tìm mã giảm giá:");

        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDKKM, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(115, 115, 115)
                                .addComponent(txtMKM, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPTKM)
                            .addComponent(dateBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateKT, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(btnThem)
                                .addGap(80, 80, 80)
                                .addComponent(btnHuy)
                                .addGap(80, 80, 80)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btnXoa))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabel7)
                                .addGap(42, 42, 42)
                                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnSearch)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnSearch))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtMKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDKKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dateBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(dateKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLuu)
                    .addComponent(btnSua)
                    .addComponent(btnHuy)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKMActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        String MKM = CheckMKM(randomMKM());
        txtMKM.setText(MKM);
        btnLuu.setEnabled(true);
        dateBD.setCalendar(cld);
        cld.add(Calendar.DATE, 1);
        dateKT.setCalendar(cld);
        btnThem.setEnabled(false);
        txtMKM.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
//        int input = JOptionPane.showConfirmDialog(null,
//                "Bạn có chắc muốn hủy hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (input == 0) {
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn tải lại trang hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            txtMKM.setText("");
            txtTKM.setText("");
            txtDKKM.setText("");
            txtPTKM.setText("");
            dateBD.setCalendar(null);
            dateKT.setCalendar(null);

            btnLuu.setEnabled(false);
            btnSua.setEnabled(false);
            btnThem.setEnabled(true);
            this.reset();
        }

//        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:

        String maKhuyenmai = txtMKM.getText();
        String tenKhuyenmai = txtTKM.getText();
        float dieukienKM = Float.parseFloat(txtDKKM.getText());
        float phantramKM = Float.parseFloat(txtPTKM.getText());
        LocalDate ngayBD = LocalDateTime.ofInstant(dateBD.getCalendar().toInstant(), dateBD.getCalendar().getTimeZone().toZoneId()).toLocalDate();
        LocalDate ngayKT = LocalDateTime.ofInstant(dateKT.getCalendar().toInstant(), dateKT.getCalendar().getTimeZone().toZoneId()).toLocalDate();
        boolean check = true;

        if (dsMaKM.getRowCount() > 0) {
            for (int i = 0; i < dsMaKM.getRowCount(); i++) {
                if (maKhuyenmai.equalsIgnoreCase(dsMaKM.getModel().getValueAt(i, 0).toString())) {
                    JOptionPane.showMessageDialog(null, "Mã khuyễn mãi " + maKhuyenmai + " này đã tồn tại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    check = false;
                    break;
                }
            }
        }
        if (check == true) {
            Khuyenmai KM = new Khuyenmai(maKhuyenmai, tenKhuyenmai, dieukienKM, phantramKM, ngayBD, ngayKT, false);

            try {
                kmctr.themMaKhuyenmai(KM);
                JOptionPane.showMessageDialog(null, "Thêm mã khuyễn mãi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi không thể thêm mã khuyến mãi", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }

            String MKM = CheckMKM(randomMKM());
            txtMKM.setText(MKM);
            this.reset();
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        String maKhuyenmai = txtMKM.getText();
        String tenKhuyenmai = txtTKM.getText();
        float dieukienKM = Float.parseFloat(txtDKKM.getText());
        float phantramKM = Float.parseFloat(txtPTKM.getText());
        LocalDate ngayBD = LocalDateTime.ofInstant(dateBD.getCalendar().toInstant(), dateBD.getCalendar().getTimeZone().toZoneId()).toLocalDate();
        LocalDate ngayKT = LocalDateTime.ofInstant(dateKT.getCalendar().toInstant(), dateKT.getCalendar().getTimeZone().toZoneId()).toLocalDate();
        Khuyenmai KM = new Khuyenmai(maKhuyenmai, tenKhuyenmai, dieukienKM, phantramKM, ngayBD, ngayKT, false);

        try {
            kmctr.capnhatMaKhuyenmai(KM);
            JOptionPane.showMessageDialog(null, "Sửa mã khuyến mãi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi không thể sửa mã khuyến mãi", "Thông báo", JOptionPane.ERROR_MESSAGE);

        }

        this.reset();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void dsMaKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsMaKMMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        indexOfList = source.rowAtPoint(evt.getPoint());
        String s1 = source.getModel().getValueAt(indexOfList, 0).toString();
        String s2 = source.getModel().getValueAt(indexOfList, 1).toString();
        String s3 = source.getModel().getValueAt(indexOfList, 2).toString();
        String s4 = source.getModel().getValueAt(indexOfList, 3).toString();
        String s5 = source.getModel().getValueAt(indexOfList, 4).toString();
        String s6 = source.getModel().getValueAt(indexOfList, 5).toString();

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            cal1.setTime(sdf.parse(s5));
            cal2.setTime(sdf.parse(s6));
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenmaiView.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtMKM.setText(s1);
        txtTKM.setText(s2);
        txtDKKM.setText(s3);
        txtPTKM.setText(s4);
        dateBD.setCalendar(cal1);
        dateKT.setCalendar(cal2);

        txtMKM.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);


    }//GEN-LAST:event_dsMaKMMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa mã khuyến mãi " + txtMKM.getText() + " này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            try {
                kmctr.xoaMaKhuyenmai(txtMKM.getText());
                JOptionPane.showMessageDialog(null, "Xóa mã khuyến mãi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi không thể xóa hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

            this.reset();
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Khuyenmai km = kmctr.layMaKhuyenmai(searchBox.getText());
        if (km == null) {
            JOptionPane.showMessageDialog(null, "Mã khuyến mãi không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } else {
            txtMKM.setText(km.getMaKhuyenmai());
            txtTKM.setText(km.getTenKhuyenmai());
            txtDKKM.setText(String.valueOf(km.getDieukienKhuyenmai()));
            txtPTKM.setText(String.valueOf(km.getPhantramKhuyenmai()));

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                cal1.setTime(sdf.parse(km.getNgayBatdau().toString()));
                cal2.setTime(sdf.parse(km.getNgayKetthuc().toString()));
            } catch (ParseException ex) {
                Logger.getLogger(KhuyenmaiView.class.getName()).log(Level.SEVERE, null, ex);
            }

            dateBD.setCalendar(cal1);
            dateKT.setCalendar(cal2);

            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            txtMKM.setEditable(false);
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dateBD;
    private com.toedter.calendar.JDateChooser dateKT;
    private javax.swing.JTable dsMaKM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTextField txtDKKM;
    private javax.swing.JTextField txtMKM;
    private javax.swing.JTextField txtPTKM;
    private javax.swing.JTextField txtTKM;
    // End of variables declaration//GEN-END:variables
}
