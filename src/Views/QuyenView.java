package Views;

import Components.ExcelExportFunction;
import Components.ExcelImportFunction;
import Models.Quyen;
import Controllers.QuyenController;
import Helpers.QuyenViewHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class QuyenView extends JPanel {

    QuyenController quyenCtrl = new QuyenController();
    DefaultTableModel tbModel;

    public QuyenView() {
        initComponents();
        tbModel = (DefaultTableModel) tblQuyen.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblQuyen.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tblQuyen.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblQuyen.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tblQuyen.getTableHeader().setOpaque(false);
        tblQuyen.getTableHeader().setBackground(Color.YELLOW);
        tblQuyen.setUpdateSelectionOnSort(true);
        tblQuyen.setFillsViewportHeight(true);
        tblQuyen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        txtTimKiem.setBorder(BorderFactory.createTitledBorder(" ")); //tạo border rỗng

        // buttons
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlQuyen")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnTaiLenExcel.setEnabled(false);
            btnTaiXuongExcel.setEnabled(false);
        }
        btnTaiLenExcel.addActionListener((ActionEvent ae) -> {
            new ExcelImportFunction().docFileExcelQuyen();
        });
        btnTaiXuongExcel.addActionListener((ActionEvent ae) -> {
            new ExcelExportFunction().xuatFileExcelQuyen();
        });
        tblQuyen.addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String maquyen = getSelectedRow(1);
                if (maquyen != null) {
                    showInfo(maquyen);
                }
            }
        });
        refresh();
    }

//    private int countSpaceChar(String str) {
//        int count = 0;
//        for(int i=0;i<str.length();i++){
//            if (str.[i].equals(" ")) count++;
//        }
//        return count;
//    }
    private void refresh() {
        quyenCtrl.readDB();
        txtMaQuyen.setText("");
        txtTenQuyen.setText("");
        txtChitietquyen.setText("");
        loadDataToTable();
    }

    String getStringFromSb(StringBuilder sb, int stringLength) {
        String returnStr = sb.substring(0, stringLength);
        sb.delete(0, stringLength);
        return returnStr;
    }

    private void showInfo(String _maquyen) {
        if (_maquyen != null) {
            // show hình
            var dsQuyen = QuyenController.getDanhSachQuyen();
            for (Quyen q : dsQuyen) {
                if (q.getMaQuyen().equals(_maquyen)) {
                    // show info
                    txtMaQuyen.setText(q.getMaQuyen());
                    txtTenQuyen.setText(q.getTenQuyen());

                    String tempStr1 = q.getChitietQuyen();
                    String tempStr2 = q.getChitietQuyen();
                    String str1 = null, str2 = null;
                    int count = tempStr1.length() - tempStr1.replace(" ", "").length();
                    int nextIndexString = 0;
                    if (count >= 6 && count <= 12) {
                        int countTemp = 0;
                        for (int i = 0; i < tempStr2.length(); i++) {
                            if (tempStr2.charAt(i) == (char) 32) {
                                if (countTemp == 6) {
                                    nextIndexString = i;
                                    break;
                                }
                                countTemp++;
                            }

                        }
                        str1 = tempStr2.substring(0, nextIndexString);
                        str2 = tempStr2.substring(nextIndexString + 1, tempStr2.length());
                        txtChitietquyen.setText(str1 + System.getProperty("line.separator") + str2);
                    } else if (count < 6) {
                        txtChitietquyen.setText(q.getChitietQuyen());
                    }
                    return;
                }
            }
        }
    }

    private void loadDataToTable() {
        tbModel.setRowCount(0);
        var dsQuyen = quyenCtrl.getDanhSachQuyen();
        int stt = 0;
        for (Quyen q : dsQuyen) {
            stt++;
            tbModel.addRow(new String[]{
                String.valueOf(stt),
                q.getMaQuyen(),
                q.getTenQuyen(),
                q.getChitietQuyen(),
                (q.isDaXoa() ? "Đã tạm khóa" : "Bình thường"),});
        }
    }

//    private void txSearchOnChange() {
//        setDataToTable(quyenCtrl.search(txtTimKiem.getText(), cbbChonTimKiem.getSelectedItem().toString()), this.tblQuyen);
//    }
    public String getSelectedRow(int col) {
        int i = tblQuyen.getSelectedRow();
        if (i >= 0) {
            int realI = tblQuyen.convertRowIndexToModel(i);
            return tblQuyen.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaQuyen = new javax.swing.JTextField();
        txtTenQuyen = new javax.swing.JTextField();
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
        tblQuyen = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChitietquyen = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(1200, 745));

        txtMaQuyen.setEditable(false);
        txtMaQuyen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaQuyen.setForeground(new java.awt.Color(255, 0, 0));
        txtMaQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtTenQuyen.setEditable(false);
        txtTenQuyen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenQuyen.setForeground(new java.awt.Color(255, 0, 0));
        txtTenQuyen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTenQuyen.setAlignmentX(0.0F);
        txtTenQuyen.setAlignmentY(0.0F);
        txtTenQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        txtTenQuyen.setMargin(new java.awt.Insets(0, 0, 0, 0));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
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

        cbbChonTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbChonTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã Quyền", "Tên Quyền", "Chi Tiết Quyền", "Quyền Đã Ẩn" }));
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
        btnTimKiem.setText("Tìm Kiếm");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách quyền hệ thống", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblQuyen.setAutoCreateRowSorter(true);
        tblQuyen.setBackground(new java.awt.Color(204, 255, 255));
        tblQuyen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 0), null, null));
        tblQuyen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tblQuyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã quyền", "Tên quyền", "Chi tiết quyền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblQuyen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblQuyen.setGridColor(new java.awt.Color(0, 0, 0));
        tblQuyen.setRowHeight(40);
        tblQuyen.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tblQuyen.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQuyen.setShowGrid(true);
        jScrollPane2.setViewportView(tblQuyen);
        tblQuyen.getAccessibleContext().setAccessibleParent(tblQuyen);

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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        jScrollPane1.setForeground(new java.awt.Color(255, 0, 0));
        jScrollPane1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        txtChitietquyen.setEditable(false);
        txtChitietquyen.setBackground(new java.awt.Color(240, 240, 240));
        txtChitietquyen.setColumns(20);
        txtChitietquyen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtChitietquyen.setForeground(new java.awt.Color(255, 0, 0));
        txtChitietquyen.setRows(5);
        jScrollPane1.setViewportView(txtChitietquyen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(339, 339, 339)
                                .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnTimKiem))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 156, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addGap(39, 39, 39)
                                .addComponent(btnSua)
                                .addGap(39, 39, 39)
                                .addComponent(btnXoa)
                                .addGap(39, 39, 39)
                                .addComponent(btnLamMoi)
                                .addGap(39, 39, 39)
                                .addComponent(btnTaiLenExcel)
                                .addGap(39, 39, 39)
                                .addComponent(btnTaiXuongExcel)))
                        .addContainerGap(169, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenQuyen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaQuyen))
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMaQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtTenQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLenExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiXuongExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        QuyenViewHelper quyenHelper = new QuyenViewHelper("Thêm", "");
        quyenHelper.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                refresh();
            }
        });
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String maquyen = getSelectedRow(1);
        if (maquyen != null) {
            QuyenViewHelper suaq = new QuyenViewHelper("Sửa", maquyen);
            suaq.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn quyền nào để sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String maquyen = getSelectedRow(1);
        if (maquyen != null) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa quyền " + maquyen + " ?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new QuyenController().delete(maquyen);
                refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn quyền nào để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed


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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblQuyen;
    private javax.swing.JTextArea txtChitietquyen;
    private javax.swing.JTextField txtMaQuyen;
    private javax.swing.JTextField txtTenQuyen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
