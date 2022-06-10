/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controllers.DonvitinhController;
import Models.Donvitinh;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HauPC
 */
public class DonvitinhView extends javax.swing.JPanel {

    /**
     * Creates new form DonvitinhView
     */
    ArrayList<Donvitinh> dsDonvitinh=new ArrayList<Donvitinh>();
    public DonvitinhView() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableDanhSachDonvitinh.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tableDanhSachDonvitinh.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableDanhSachDonvitinh.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tableDanhSachDonvitinh.getTableHeader().setOpaque(false);
        tableDanhSachDonvitinh.getTableHeader().setBackground(Color.YELLOW);
         tableDanhSachDonvitinh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaDonvitinh.setEditable(false);
        ShowData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelThongtinDonvitinh = new javax.swing.JPanel();
        labelMaDonvitinh = new javax.swing.JLabel();
        labelTendonvitinh = new javax.swing.JLabel();
        txtMaDonvitinh = new javax.swing.JTextField();
        txtTenDonvitinh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtGiaTriDonvitinh = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        panelDanhsachDonvitinh = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDanhSachDonvitinh = new javax.swing.JTable();
        btnThemFile = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        panelThongtinDonvitinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đơn vị tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        labelMaDonvitinh.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelMaDonvitinh.setText("Mã đơn vị tính");

        labelTendonvitinh.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelTendonvitinh.setText("Tên đơn vị tính");

        txtMaDonvitinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTenDonvitinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Mô tả:");

        txtGiaTriDonvitinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 0, 0));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 255, 0));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThongtinDonvitinhLayout = new javax.swing.GroupLayout(panelThongtinDonvitinh);
        panelThongtinDonvitinh.setLayout(panelThongtinDonvitinhLayout);
        panelThongtinDonvitinhLayout.setHorizontalGroup(
            panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                        .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33))
                                    .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                                        .addComponent(labelMaDonvitinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongtinDonvitinhLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTendonvitinh)
                                .addGap(18, 18, 18)))
                        .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaTriDonvitinh, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(txtTenDonvitinh)
                            .addComponent(txtMaDonvitinh)))
                    .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnHuy)
                        .addGap(32, 32, 32)
                        .addComponent(btnLuu)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnXoa))
        );
        panelThongtinDonvitinhLayout.setVerticalGroup(
            panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongtinDonvitinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaDonvitinh)
                    .addComponent(txtMaDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTendonvitinh))
                .addGap(18, 18, 18)
                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGiaTriDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa))
                    .addGroup(panelThongtinDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHuy)
                        .addComponent(btnLuu)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDanhsachDonvitinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đơn vị tính", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tableDanhSachDonvitinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tableDanhSachDonvitinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã đơn vị tính", "Tên đơn vị tính", "Giá trị"
            }
        ));
        tableDanhSachDonvitinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDanhSachDonvitinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDanhSachDonvitinh);

        btnThemFile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_ms_excel_30px.png"))); // NOI18N
        btnThemFile.setText("Thêm từ file");

        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_downloads_30px.png"))); // NOI18N
        btnXuatFile.setText("Xuất file");

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThoat.setText("Thoát");

        javax.swing.GroupLayout panelDanhsachDonvitinhLayout = new javax.swing.GroupLayout(panelDanhsachDonvitinh);
        panelDanhsachDonvitinh.setLayout(panelDanhsachDonvitinhLayout);
        panelDanhsachDonvitinhLayout.setHorizontalGroup(
            panelDanhsachDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhsachDonvitinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemFile)
                .addGap(18, 18, 18)
                .addComponent(btnXuatFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(btnThoat)
                .addGap(20, 20, 20))
            .addGroup(panelDanhsachDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhsachDonvitinhLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelDanhsachDonvitinhLayout.setVerticalGroup(
            panelDanhsachDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhsachDonvitinhLayout.createSequentialGroup()
                .addContainerGap(291, Short.MAX_VALUE)
                .addGroup(panelDanhsachDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemFile)
                    .addComponent(btnXuatFile)
                    .addComponent(btnThoat))
                .addContainerGap())
            .addGroup(panelDanhsachDonvitinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhsachDonvitinhLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDanhsachDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelThongtinDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(503, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelThongtinDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelDanhsachDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        txtMaDonvitinh.setText(String.valueOf(getMaDonvitinhMoi()));
        btnLuu.setEnabled(true);
        btnHuy.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaDonvitinh.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selectedrow=tableDanhSachDonvitinh.getSelectedRow();
        int selectedMaDonvitinh=Integer.parseInt(tableDanhSachDonvitinh.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsDonvitinh.size();i++)
        {
            if(dsDonvitinh.get(i).getMaDonvitinh()==selectedMaDonvitinh)
            {
                selected=i;
            }
        }
        String maDonvitinhString=txtMaDonvitinh.getText();
        int maDonvitinh=Integer.parseInt(maDonvitinhString);
        String tenDonvitinh=txtTenDonvitinh.getText();
        String giatri=txtGiaTriDonvitinh.getText();
        if(maDonvitinhString.equals("")||tenDonvitinh.equals("")||giatri.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Vui lòng không được bỏ trống các ô thông tin!!!","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        Donvitinh nhacungcap=new Donvitinh(maDonvitinh,tenDonvitinh,giatri);
        int maDonvitinhCu=(dsDonvitinh.get(selected).getMaDonvitinh());
        try {
            DonvitinhController.capnhatDonvitinh(nhacungcap,maDonvitinhCu);
            JOptionPane.showMessageDialog(this,"Cap nhat đơn vị tính "+nhacungcap.getTenDonvitinh()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ShowData();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Cập nhật đơn vị tính không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int selectedrow=tableDanhSachDonvitinh.getSelectedRow();
        int selectedMaDonvitinh=Integer.parseInt(tableDanhSachDonvitinh.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsDonvitinh.size();i++)
        {
            if(dsDonvitinh.get(i).getMaDonvitinh()==selectedMaDonvitinh)
            {
                selected=i;
            }
        }
        int maDonvitinh=dsDonvitinh.get(selected).getMaDonvitinh();
        try {
            DonvitinhController.xoaDonvitinh(maDonvitinh);
            JOptionPane.showMessageDialog(this,"Xóa đơn vị tính "+dsDonvitinh.get(selected).getTenDonvitinh()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ShowData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Xóa đơn vị tính "+dsDonvitinh.get(selected).getTenDonvitinh()+" không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ShowData();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableDanhSachDonvitinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDanhSachDonvitinhMouseClicked
        // TODO add your handling code here:
        int selectedrow=tableDanhSachDonvitinh.getSelectedRow();
        int selectedMaDonvitinh=Integer.parseInt(tableDanhSachDonvitinh.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsDonvitinh.size();i++)
        {
            if(dsDonvitinh.get(i).getMaDonvitinh()==selectedMaDonvitinh)
            {
                selected=i;
            }
        }
        if(selected>=0)
        {
            txtMaDonvitinh.setText(String.valueOf(dsDonvitinh.get(selected).getMaDonvitinh()));
            txtTenDonvitinh.setText(dsDonvitinh.get(selected).getTenDonvitinh());
            txtGiaTriDonvitinh.setText(dsDonvitinh.get(selected).getGiatri());
        }
        txtMaDonvitinh.setEnabled(false);
        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnHuy.setEnabled(true);
    }//GEN-LAST:event_tableDanhSachDonvitinhMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
            "Bạn có chắc muốn hủy hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            if (input == 0) {
                txtMaDonvitinh.setText("");
                txtTenDonvitinh.setText("");
                txtGiaTriDonvitinh.setText("");

                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);
                btnSua.setEnabled(false);
                btnThem.setEnabled(true);
                btnXoa.setEnabled(false);
                tableDanhSachDonvitinh.getSelectionModel().clearSelection();

            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:

        String maDonvitinhString=txtMaDonvitinh.getText();
        String tenDonvitinh=txtTenDonvitinh.getText();
        String giatri=txtGiaTriDonvitinh.getText();
        if(maDonvitinhString.equals("")||tenDonvitinh.equals("")||txtGiaTriDonvitinh.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Vui lòng không bỏ trống các trường dữ liệu","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        int maDonvitinh;
        try {
            maDonvitinh=Integer.parseInt(maDonvitinhString);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Lỗi định dạng số","Thông báo",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Donvitinh donvitinhMoi=new Donvitinh(maDonvitinh,tenDonvitinh,giatri);
        try {
            DonvitinhController.themDonvitinh(donvitinhMoi);
            JOptionPane.showMessageDialog(this,"Thêm đơn vị tính "+tenDonvitinh+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this,"Lỗi, không thêm được đơn vị tính "+tenDonvitinh+" !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);

        }
        ShowData();
    }//GEN-LAST:event_btnLuuActionPerformed
       public void ShowData()
    {
        DefaultTableModel tblModel=(DefaultTableModel) tableDanhSachDonvitinh.getModel();
        tblModel.getDataVector().removeAllElements();
        tblModel.fireTableDataChanged();
       dsDonvitinh=DonvitinhController.getDanhSachDonvitinh();
        if(!dsDonvitinh.isEmpty()){
                dsDonvitinh.forEach((donvitinh1)->{
                if(!donvitinh1.isDaXoa())
                tblModel.addRow(new Object[]{donvitinh1.getMaDonvitinh(),donvitinh1.getTenDonvitinh(),donvitinh1.getGiatri()});
                });
        }
        else
        {
          JOptionPane.showMessageDialog(this,"Danh sách đơn vị tính rỗng","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
         btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaDonvitinh.setEditable(false);
        btnThem.setEnabled(true);
        
    }
       public int getMaDonvitinhMoi()
    {
        int maDonvitinhMoi=0;
        for(Donvitinh donvitinh:dsDonvitinh)
        {
            if(donvitinh.getMaDonvitinh()>maDonvitinhMoi)
            {
                maDonvitinhMoi=donvitinh.getMaDonvitinh();
            }
        }
        return maDonvitinhMoi+1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemFile;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMaDonvitinh;
    private javax.swing.JLabel labelTendonvitinh;
    private javax.swing.JPanel panelDanhsachDonvitinh;
    private javax.swing.JPanel panelThongtinDonvitinh;
    private javax.swing.JTable tableDanhSachDonvitinh;
    private javax.swing.JTextField txtGiaTriDonvitinh;
    private javax.swing.JTextField txtMaDonvitinh;
    private javax.swing.JTextField txtTenDonvitinh;
    // End of variables declaration//GEN-END:variables
}
