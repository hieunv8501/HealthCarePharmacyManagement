/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Components.AutoCompletion;
import Components.ReadExcelNhacungcap;
import Components.WriteExcelNhacungcap;
import static Components.WriteExcelNhacungcap.writeExcel;
import Controllers.NhacungcapController;
import Controllers.XaController;
import Models.Huyen;
import Models.Nhacungcap;
import Models.Xa;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.*;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author HauPC
 */
public class NhacungcapView extends javax.swing.JPanel {

    /**
     * Creates new form NhacungcapView
     */
   
    ArrayList<Nhacungcap> dsNhacungcap;
    public NhacungcapView() {
        initComponents();
        
       
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhacungcap.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tableNhacungcap.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableNhacungcap.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tableNhacungcap.getTableHeader().setOpaque(false);
        tableNhacungcap.getTableHeader().setBackground(Color.YELLOW);
        showXa();
        ShowData();
        
        ShowSearchComboBox();
        
        //txtMaNhacungcap.addKeyListener(new KeyCustom());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhacungcap = new javax.swing.JTable();
        panelThongtinNhacungcap = new javax.swing.JPanel();
        labelTennhacungcap = new javax.swing.JLabel();
        labelHuyen = new javax.swing.JLabel();
        lableSdt = new javax.swing.JLabel();
        labelFax = new javax.swing.JLabel();
        labelManhacungcap = new javax.swing.JLabel();
        txtMaNhacungcap = new javax.swing.JTextField();
        txtTenNhacungcap = new javax.swing.JTextField();
        txtSodienthoai = new javax.swing.JTextField();
        txtfax = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtXa = new javax.swing.JComboBox<>();
        btnThemfile = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        labelTimKiem = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        JcomboboxSearch = new javax.swing.JComboBox<>();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(245, 245, 244));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhà cung cấp", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        tableNhacungcap.setBackground(new java.awt.Color(204, 255, 255));
        tableNhacungcap.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tableNhacungcap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Huyện", "Số điện thoại", "Fax"
            }
        ));
        tableNhacungcap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhacungcapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhacungcap);
        tableNhacungcap.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        panelThongtinNhacungcap.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));

        labelTennhacungcap.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelTennhacungcap.setText("Tên nhà cung cấp: ");

        labelHuyen.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelHuyen.setText("Xã:");

        lableSdt.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        lableSdt.setText("Số điện thoại:");

        labelFax.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelFax.setText("Fax:");

        labelManhacungcap.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        labelManhacungcap.setText("Mã nhà cung cấp:");

        txtMaNhacungcap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaNhacungcap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhacungcapActionPerformed(evt);
            }
        });
        txtMaNhacungcap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaNhacungcapKeyPressed(evt);
            }
        });

        txtTenNhacungcap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenNhacungcap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenNhacungcapKeyPressed(evt);
            }
        });

        txtSodienthoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSodienthoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSodienthoaiKeyPressed(evt);
            }
        });

        txtfax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtfax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfaxActionPerformed(evt);
            }
        });
        txtfax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfaxKeyPressed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 255, 0));
        btnLuu.setText("Lưu");

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 0, 0));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Hủy");

        txtXa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelThongtinNhacungcapLayout = new javax.swing.GroupLayout(panelThongtinNhacungcap);
        panelThongtinNhacungcap.setLayout(panelThongtinNhacungcapLayout);
        panelThongtinNhacungcapLayout.setHorizontalGroup(
            panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                        .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lableSdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHuyen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSodienthoai)
                            .addComponent(txtXa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                        .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelManhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTennhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                                .addComponent(labelFax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(9, 9, 9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLuu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua)
                                .addGap(33, 33, 33)
                                .addComponent(btnXoa))
                            .addComponent(txtMaNhacungcap)
                            .addComponent(txtTenNhacungcap)
                            .addComponent(txtfax, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        panelThongtinNhacungcapLayout.setVerticalGroup(
            panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongtinNhacungcapLayout.createSequentialGroup()
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelManhacungcap)
                    .addComponent(txtMaNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTennhacungcap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHuyen)
                    .addComponent(txtXa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableSdt)
                    .addComponent(txtSodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThongtinNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLuu)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(jButton2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnThemfile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_ms_excel_30px.png"))); // NOI18N
        btnThemfile.setText("Thêm từ file");
        btnThemfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemfileActionPerformed(evt);
            }
        });

        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_downloads_30px.png"))); // NOI18N
        btnXuatFile.setText("Xuất file");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 0, 51));
        btnHuy.setText("Hủy");

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 153, 0));
        btnThoat.setText("Thoát");

        labelTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTimKiem.setText("Tìm kiếm");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        JcomboboxSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTimKiem)
                        .addGap(71, 71, 71)
                        .addComponent(JcomboboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnTimKiem))
                    .addComponent(panelThongtinNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnThemfile)
                            .addGap(27, 27, 27)
                            .addComponent(btnXuatFile)
                            .addGap(83, 83, 83)
                            .addComponent(btnHuy)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThoat))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 365, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JcomboboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTimKiem))
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addComponent(panelThongtinNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXuatFile)
                        .addComponent(btnThemfile)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableNhacungcapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhacungcapMouseClicked
        // TODO add your handling code here:
        //chon dong trong bang nha cung cap se hien thi du lieu len text field
        int selectedrow=tableNhacungcap.getSelectedRow();
        int selectedMaNhacungcap=Integer.parseInt(tableNhacungcap.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsNhacungcap.size();i++)
        {
            if(dsNhacungcap.get(i).getMaNhacungcap()==selectedMaNhacungcap)
            {
                selected=i;
            }
        }
        if(selected>=0)
        {
            txtMaNhacungcap.setText(String.valueOf(dsNhacungcap.get(selected).getMaNhacungcap()));
            txtTenNhacungcap.setText(dsNhacungcap.get(selected).getTenNhacungcap());
             DefaultComboBoxModel xaComboBoxModel=(DefaultComboBoxModel) txtXa.getModel();
             
            for(int i=0;i<xaComboBoxModel.getSize();i++)
            {
                 Xa xa=(Xa) xaComboBoxModel.getElementAt(i);
                if(xa.getMaXa()==(dsNhacungcap.get(selected).getXa()).getMaXa())
                {
                    txtXa.setSelectedIndex(i);
                    break;
                }
            }
            txtSodienthoai.setText(String.valueOf(dsNhacungcap.get(selected).getSoDienthoai()));
            txtfax.setText(String.valueOf(dsNhacungcap.get(selected).getFax()));
        }

    }//GEN-LAST:event_tableNhacungcapMouseClicked

    private void txtMaNhacungcapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhacungcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhacungcapActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String maNhacungcapString=txtMaNhacungcap.getText();
        int maNhacungcap=-1;
        try{
        maNhacungcap=Integer.parseInt(maNhacungcapString);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Mã nhà cung cấp không hợp lệ","Thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        String tenNhacungcap=txtTenNhacungcap.getText();
        String soDienThoai=txtSodienthoai.getText();
        Xa xa=(Xa) txtXa.getSelectedItem();
        String fax=txtfax.getText();
        if(maNhacungcapString.equals("")||tenNhacungcap.equals("")||soDienThoai.equals("")||xa.equals("")||fax.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Vui lòng không được bỏ trống các ô thông tin!!!","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        Nhacungcap nhacungcap=new Nhacungcap(maNhacungcap,tenNhacungcap,xa,soDienThoai,fax);
        try {
            NhacungcapController.themNhacungcap(nhacungcap);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Thêm nhà cung cấp không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"Thêm nhà cung cấp"+nhacungcap.getTenNhacungcap()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ShowData();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selectedrow=tableNhacungcap.getSelectedRow();
        int selectedMaNhacungcap=Integer.parseInt(tableNhacungcap.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsNhacungcap.size();i++)
        {
            if(dsNhacungcap.get(i).getMaNhacungcap()==selectedMaNhacungcap)
            {
                selected=i;
            }
        }
        String maNhacungcapString=txtMaNhacungcap.getText();
        int maNhacungcap=Integer.parseInt(maNhacungcapString);
        String tenNhacungcap=txtTenNhacungcap.getText();
        String soDienThoai=txtSodienthoai.getText();
        Xa xa=(Xa) txtXa.getSelectedItem();
        String fax=txtfax.getText();
        if(maNhacungcapString.equals("")||tenNhacungcap.equals("")||soDienThoai.equals("")||xa.equals("")||fax.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Vui lòng không được bỏ trống các ô thông tin!!!","Thông báo",JOptionPane.WARNING_MESSAGE);
        }
        Nhacungcap nhacungcap=new Nhacungcap(maNhacungcap,tenNhacungcap,xa,soDienThoai,fax);
        int maNhacungcapCu=(dsNhacungcap.get(selected).getMaNhacungcap());
        try {
            NhacungcapController.capnhatNhacungcap(nhacungcap,maNhacungcapCu);
            JOptionPane.showMessageDialog(this,"Cap nhat nhà cung cấp"+nhacungcap.getTenNhacungcap()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ShowData();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Cập nhật nhà cung cấp không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int selectedrow=tableNhacungcap.getSelectedRow();
        int selectedMaNhacungcap=Integer.parseInt(tableNhacungcap.getModel().getValueAt(selectedrow,0).toString());
        int selected=-1;
        for(int i=0;i<dsNhacungcap.size();i++)
        {
            if(dsNhacungcap.get(i).getMaNhacungcap()==selectedMaNhacungcap)
            {
                selected=i;
            }
        }
        int maNhacungcap=dsNhacungcap.get(selected).getMaNhacungcap();
        try {
            NhacungcapController.xoaNhacungcap(maNhacungcap);
            JOptionPane.showMessageDialog(this,"Xóa nhà cung cấp"+dsNhacungcap.get(selected).getTenNhacungcap()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ShowData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Xóa nhà cung cấp"+dsNhacungcap.get(selected).getTenNhacungcap()+" không thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ShowData();

    }//GEN-LAST:event_btnXoaActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        System.out.println(evt.getKeyChar());
        if (evt.isControlDown() && evt.getKeyCode() == 65) {
        System.out.println("Select All"); 
}
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
         System.out.println(evt.getKeyChar());
        if (evt.isControlDown() && evt.getKeyCode() == 65) {
        System.out.println("Select All"); 
        }
    }//GEN-LAST:event_formKeyReleased

    private void txtfaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfaxActionPerformed

    private void txtMaNhacungcapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNhacungcapKeyPressed
        // TODO add your handling code here:
        System.out.println(evt.getKeyCode());
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            txtTenNhacungcap.requestFocus();
        }
    }//GEN-LAST:event_txtMaNhacungcapKeyPressed

    private void txtTenNhacungcapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNhacungcapKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            txtXa.requestFocus();
        }
    }//GEN-LAST:event_txtTenNhacungcapKeyPressed

    private void txtSodienthoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSodienthoaiKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            txtfax.requestFocus();
        }
    }//GEN-LAST:event_txtSodienthoaiKeyPressed

    private void txtfaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfaxKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            btnThem.requestFocus();
        }
        if(evt.isControlDown()&&evt.getKeyCode()==KeyEvent.VK_S)
        {
            btnThem.doClick();
        }
    }//GEN-LAST:event_txtfaxKeyPressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        //Tìm kiếm nhà cung cấp.
        String searchText=String.valueOf(JcomboboxSearch.getSelectedItem());
      DefaultTableModel tblModel=(DefaultTableModel) tableNhacungcap.getModel();
        tblModel.getDataVector().removeAllElements();
        tblModel.fireTableDataChanged();
       dsNhacungcap=NhacungcapController.timkiemNhacungcap(searchText);
        if(!dsNhacungcap.isEmpty()){
                dsNhacungcap.forEach((nhacungcap1)->{
                 if(!nhacungcap1.isDaXoa())
                 {
                tblModel.addRow(new Object[]{nhacungcap1.getMaNhacungcap(),nhacungcap1.getTenNhacungcap(),nhacungcap1.getXa(),nhacungcap1.getSoDienthoai(),nhacungcap1.getFax()});
                 }
                });
        }
        else
        {
          JOptionPane.showMessageDialog(this,"Danh sách nhà cung cấp rỗng","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        //Tham khảo tại https://stackhowto.com/export-data-from-jtable-to-excel-in-java/
          WriteExcelNhacungcap writeExcelNhacungcap=new WriteExcelNhacungcap();
          final List<Nhacungcap> nhacungcaps = NhacungcapController.getDanhSachNhacungcap();
           JFileChooser fchoose = new JFileChooser();
           int option = fchoose.showOpenDialog(fchoose);
           if(option == JFileChooser.APPROVE_OPTION){
             String name = fchoose.getSelectedFile().getName(); 
             String path = fchoose.getSelectedFile().getParentFile().getPath();
             String file = path + "\\" + name + ".xlsx"; 
             final String excelFilePath = file;
             try{
                 writeExcelNhacungcap.writeExcel(nhacungcaps, excelFilePath);
             }
             catch(IOException e)
             {
                 JOptionPane.showMessageDialog(this,e.toString(),"Lỗi",JOptionPane.ERROR_MESSAGE);
             }
             
           }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnThemfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemfileActionPerformed
        // TODO add your handling code here:
           JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter filefilter= new FileNameExtensionFilter("File excel","xlsx","xls","csv");
        fileChooser.setFileFilter(filefilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x=fileChooser.showDialog(this, "Open");
        
        if(x==JFileChooser.APPROVE_OPTION)
        {
            File file=fileChooser.getSelectedFile();
             if(!file.exists())
            {
                int result = JOptionPane.showConfirmDialog(this,"The file "+file.getName()+" không tồn tại","Thông báo",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);

            }
            else{
                try{
            
            ReadExcelNhacungcap c=new ReadExcelNhacungcap();
            ArrayList<Nhacungcap>dsnhaNhacungcaps=c.readExcel(file.getAbsolutePath());
            dsnhaNhacungcaps.forEach(Nhacungcap->{
                try {
                    NhacungcapController.themNhacungcap(Nhacungcap);
                } catch (Exception ex) {
                    Logger.getLogger(NhacungcapView.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            JOptionPane.showMessageDialog(this,"Thêm danh sách nhà cung cấp từ file: "+ file.getAbsolutePath()+" thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
               ShowData();
                }
                 catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,e.toString(),"Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        }
           
        }
             
    }//GEN-LAST:event_btnThemfileActionPerformed
        public void ShowData()
    {
        DefaultTableModel tblModel=(DefaultTableModel) tableNhacungcap.getModel();
        tblModel.getDataVector().removeAllElements();
        tblModel.fireTableDataChanged();
       dsNhacungcap=NhacungcapController.getDanhSachNhacungcap();
        if(!dsNhacungcap.isEmpty()){
                dsNhacungcap.forEach((nhacungcap1)->{
                 if(!nhacungcap1.isDaXoa())
                 {
                tblModel.addRow(new Object[]{nhacungcap1.getMaNhacungcap(),nhacungcap1.getTenNhacungcap(),nhacungcap1.getXa(),nhacungcap1.getSoDienthoai(),nhacungcap1.getFax()});
                 }
                });
        }
        else
        {
          JOptionPane.showMessageDialog(this,"Danh sách nhà cung cấp rỗng","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        tableNhacungcap.setAutoCreateRowSorter(true);
    }
         public void ShowSearchComboBox()
    {
       DefaultComboBoxModel jcomboBoxModel= (DefaultComboBoxModel) JcomboboxSearch.getModel();
        jcomboBoxModel.removeAllElements();
        dsNhacungcap.forEach(Nhacungcap->{
                   jcomboBoxModel.addElement(Nhacungcap.getMaNhacungcap());
                    jcomboBoxModel.addElement(Nhacungcap.getTenNhacungcap());
                     jcomboBoxModel.addElement(Nhacungcap.getSoDienthoai());
                      jcomboBoxModel.addElement(Nhacungcap.getXa().getTenXa());
                       jcomboBoxModel.addElement(Nhacungcap.getFax());
        }
        );
         
       
          AutoCompletion searchAutoCompletion = null;
           JcomboboxSearch.setModel(jcomboBoxModel);
           searchAutoCompletion.enable(JcomboboxSearch);
    }
      public int getMaNhacungcapMoi()
      {
          int maNhacungcapMoi=1;
           if(!dsNhacungcap.isEmpty())
           {
                for(int i=0;i<dsNhacungcap.size();i++)
                {
                    if(dsNhacungcap.get(i).getMaNhacungcap()>maNhacungcapMoi)
                    {
                        maNhacungcapMoi=dsNhacungcap.get(i).getMaNhacungcap();
                    }
                }
        }
           return maNhacungcapMoi;
        
      }
      public void showXa()
      {
          DefaultComboBoxModel xaComboBoxModel=(DefaultComboBoxModel) txtXa.getModel();
         xaComboBoxModel.removeAllElements();
         XaController xaController=new XaController();
         ArrayList<Xa> danhsachXa=xaController.getDanhsachXa();
         danhsachXa.forEach(xa->{
         xaComboBoxModel.addElement(xa);
         });
      }
    
     class KeyCustom implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
             System.out.println(e.getKeyChar());
        }
        @Override
        public void keyPressed(KeyEvent e) {
              System.out.println(e.getKeyChar());
        if (e.isControlDown() && e.getKeyCode() == 65) {
        System.out.println("Select All");
        }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
         }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcomboboxSearch;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemfile;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelHuyen;
    private javax.swing.JLabel labelManhacungcap;
    private javax.swing.JLabel labelTennhacungcap;
    private javax.swing.JLabel labelTimKiem;
    private javax.swing.JLabel lableSdt;
    private javax.swing.JPanel panelThongtinNhacungcap;
    private javax.swing.JTable tableNhacungcap;
    private javax.swing.JTextField txtMaNhacungcap;
    private javax.swing.JTextField txtSodienthoai;
    private javax.swing.JTextField txtTenNhacungcap;
    private javax.swing.JComboBox<String> txtXa;
    private javax.swing.JTextField txtfax;
    // End of variables declaration//GEN-END:variables
}

