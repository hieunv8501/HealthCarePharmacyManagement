package Views;

import Components.ExcelExportFunction;
import Controllers.HoadonController;
import Helpers.*;

import Models.Hoadon;
import Models.Nhanvien;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
public class HoadonView extends javax.swing.JPanel {

    /**
     * Creates new form HoadonView
     */
    private DefaultTableModel modelHD;
    HoadonController hdctr = new HoadonController();
    private int indexOfList;
    private int maHoadon;
    public String nhanVien;
    private  List<Hoadon> dshd;
    public HoadonView() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dsHoaDon.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) dsHoaDon.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dsHoaDon.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        dsHoaDon.getTableHeader().setOpaque(false);
        dsHoaDon.getTableHeader().setBackground(Color.YELLOW);
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlHoaDon")) {
            btnThem.setEnabled(false);
            btnChonKH.setEnabled(false);
            btnChonMKM.setEnabled(false);
        }
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMHD.setEditable(false);
        txtMKH.setEditable(false);
        btnChonKH.setEnabled(false);

        txtMKM.setEditable(false);
        btnChonMKM.setEnabled(false);
        btnChonKH.addActionListener((ae) -> {
            ChonKhachhangForm ckh = new ChonKhachhangForm(txtMKH);
        });
        btnChonMKM.addActionListener((ae) -> {
            ChonMaKhuyenmaiForm ckh = new ChonMaKhuyenmaiForm(txtMKM);
        });

//        btnTaiXuongExcel.addActionListener((ActionEvent ae) -> {
//            new ExcelExportFunction().xuatFileExcelHoadon();
//        });
        this.reset();
    }

    public <T> void showData(List<T> list, DefaultTableModel model) {
        model.setRowCount(0);
        for (T t : list) {
            if (t instanceof Hoadon) {
                Hoadon hd = (Hoadon) t;
//                String[] date = hd.getNgayLap().toString().split("-");
//                String ngayBD = date[2] + "/" + date[1] + "/" + date[0];
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatted = format1.format(hd.getNgayLap().getTime());
                String nhanvien = hd.getMaNhanvien() + " - " + hd.getTenNhanvien();
                String khachhang = hd.getMaKhachhang() + " - " + hd.getTenKhachhang();

                model.addRow(new Object[]{
                    hd.getMaHoadon(), nhanvien, khachhang, hd.getMaKhuyenmai(), formatted, hd.getTongTien()
                });

            }
        }
    }

    public void reset() {
        modelHD = (DefaultTableModel) dsHoaDon.getModel();
        dshd = hdctr.layDanhsachHD();
        this.showData(dshd, modelHD);
        ShowSearchTextBox();
    }

    public void setValueForCombobox() {

        comboNhanvien.removeAllItems();
//        comboKhachhang.removeAllItems();
//        comboMaKM.removeAllItems();

        List<Nhanvien> dsnv;
        dsnv = hdctr.layDanhSachMNV();
        for (Nhanvien t : dsnv) {
            if (t instanceof Nhanvien) {
                Nhanvien nv = (Nhanvien) t;
                comboNhanvien.addItem(String.valueOf(nv.getMaNhanvien()) + " - " + nv.getTenNhanvien());
            }
        }
        comboNhanvien.setSelectedItem(nhanVien);

//        List<Khachhang> dskh;
//        dskh = hdctr.layDanhSachMKH();
//        for (Khachhang t : dskh) {
//            if (t instanceof Khachhang) {
//                Khachhang kh = (Khachhang) t;
//                comboKhachhang.addItem(String.valueOf(kh.getMaKhachhang()) + " - " + kh.getTenKhachhang());
//            }
//        }
//        KhuyenmaiController kmctr = new KhuyenmaiController();
//        List<Khuyenmai> dsmkm;
//        dsmkm = kmctr.layDanhsachMKM();
//        comboMaKM.addItem("");
//        for (Khuyenmai t : dsmkm) {
//            if (t instanceof Khuyenmai) {
//                Khuyenmai km = (Khuyenmai) t;
//                comboMaKM.addItem(String.valueOf(km.getMaKhuyenmai()));
//            }
//        }
    }
public void ShowSearchTextBox() {
        
        Set<String> hash_Set = new HashSet<String>();
        dshd.forEach(hoadon1 -> {
              SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatted = format1.format(hoadon1.getNgayLap().getTime());
           hash_Set.add(String.valueOf(hoadon1.getMaHoadon()));
            hash_Set.add(hoadon1.getTenKhachhang());
             hash_Set.add(hoadon1.getTenNhanvien());
            hash_Set.add(hoadon1.getMaKhuyenmai());
             hash_Set.add(formatted);
              hash_Set.add(String.valueOf(hoadon1.getNgayLap().get(Calendar.YEAR)));           
        }
        );
         txtSearchBox.clearItemSuggestion();
        Iterator value = hash_Set.iterator();
         while (value.hasNext()) {
            txtSearchBox.addItemSuggestion(String.valueOf(value.next()));
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

        dateLap = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dsHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        comboNhanvien = new javax.swing.JComboBox<>();
        gio = new javax.swing.JSpinner();
        phut = new javax.swing.JSpinner();
        giay = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMHD = new javax.swing.JTextField();
        btnXemCT = new javax.swing.JButton();
        btnChonKH = new javax.swing.JButton();
        txtMKH = new javax.swing.JTextField();
        btnChonMKM = new javax.swing.JButton();
        txtMKM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSearchBox = new com.raven.chart.TextFieldSuggestion();
        btnTimKiem = new javax.swing.JButton();

        dateLap.setDateFormatString("dd-MM-yyyy");
        dateLap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 22), new java.awt.Color(0, 0, 204))); // NOI18N

        dsHoaDon.setBackground(new java.awt.Color(204, 255, 255));
        dsHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dsHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Nhân viên lập", "Khách hàng", "Mã khuyến mãi", "Ngày lập", "Tổng tiền"
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
        dsHoaDon.setRowHeight(30);
        dsHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dsHoaDon);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Nhân viên lập:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Khách hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel3.setText("Áp mã khuyến mãi:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Ngày lập:");

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        comboNhanvien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNhanvienActionPerformed(evt);
            }
        });

        gio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        phut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        giay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel6.setText("Giờ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel7.setText("Phút:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel8.setText("Giây:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel4.setText("Mã hóa đơn:");

        txtMHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnXemCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXemCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_agreement_30px.png"))); // NOI18N
        btnXemCT.setText("Xem chi tiết");
        btnXemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTActionPerformed(evt);
            }
        });

        btnChonKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        txtMKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKHActionPerformed(evt);
            }
        });

        btnChonMKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonMKM.setText("Chọn");
        btnChonMKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonMKMActionPerformed(evt);
            }
        });

        txtMKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKMActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Thông tin tìm kiếm");

        txtSearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchBoxKeyPressed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnTimKiem))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnThem)
                                .addGap(80, 80, 80)
                                .addComponent(btnHuy)
                                .addGap(80, 80, 80)
                                .addComponent(btnLuu))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboNhanvien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMHD, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnChonKH)))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtMKM))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(gio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(dateLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(phut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giay)))))
                                .addGap(18, 18, 18)
                                .addComponent(btnChonMKM))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSua)
                                .addGap(80, 80, 80)
                                .addComponent(btnXoa)
                                .addGap(80, 80, 80)
                                .addComponent(btnXemCT)
                                .addGap(58, 58, 58)))))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(jLabel9)
                    .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtMKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnChonKH))))))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnLuu)
                            .addComponent(btnHuy)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(btnChonMKM)
                            .addComponent(txtMKM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dateLap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(gio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(giay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnXemCT))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        txtMHD.setText(String.valueOf(hdctr.getMaxMHD()));
        btnLuu.setEnabled(true);
        btnHuy.setEnabled(true);
        btnThem.setEnabled(false);
        btnChonKH.setEnabled(true);
        btnChonMKM.setEnabled(true);

        this.setValueForCombobox();

        Calendar cld = Calendar.getInstance();
        dateLap.setCalendar(cld);

        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        gio.setValue(calendar.get(Calendar.HOUR_OF_DAY));
        phut.setValue(calendar.get(Calendar.MINUTE));
        giay.setValue(calendar.get(Calendar.SECOND));


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (txtMKH.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

        } else {

            if ((int) gio.getValue() < 0 || (int) gio.getValue() > 24 || (int) phut.getValue() < 0 || (int) phut.getValue() > 60 || (int) giay.getValue() < 0 || (int) giay.getValue() > 60) {
                JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

            } else {
                String MNV = comboNhanvien.getSelectedItem().toString().split(" - ")[0];
                String MKH = txtMKH.getText().split(" - ")[0];

                String MKM;
                if (txtMKM.getText().equals("")) {
                    MKM = null;
                } else {
                    MKM = txtMKM.getText();
                }

                dateLap.getCalendar().set(Calendar.HOUR, (int) gio.getValue());
                dateLap.getCalendar().set(Calendar.MINUTE, (int) phut.getValue());
                dateLap.getCalendar().set(Calendar.SECOND, (int) giay.getValue());
                Hoadon hd = new Hoadon(Integer.parseInt(MNV), Integer.parseInt(MKH), MKM, dateLap.getCalendar());
                try {
                    hdctr.themHoaDon(hd);
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi không thể thêm hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                this.maHoadon = Integer.valueOf(txtMHD.getText());
                ChitietHoadonView cthd = new ChitietHoadonView();
                cthd.setVariable(this.maHoadon);
                cthd.setVisible(true);
                this.reset();
            }
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if ((int) gio.getValue() < 0 || (int) gio.getValue() > 24 || (int) phut.getValue() < 0 || (int) phut.getValue() > 60 || (int) giay.getValue() < 0 || (int) giay.getValue() > 60) {
            JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

        } else {
            String MNV = comboNhanvien.getSelectedItem().toString().split(" - ")[0];
            String MKH = txtMKH.getText().split(" - ")[0];
            String MKM;
            if (txtMKM.getText().equals("")) {
                MKM = null;
            } else {
                MKM = txtMKM.getText();
            }

            dateLap.getCalendar().set(Calendar.HOUR, (int) gio.getValue());
            dateLap.getCalendar().set(Calendar.MINUTE, (int) phut.getValue());
            dateLap.getCalendar().set(Calendar.SECOND, (int) giay.getValue());
            Hoadon hd = new Hoadon(Integer.parseInt(txtMHD.getText()), Integer.parseInt(MNV), Integer.parseInt(MKH), MKM, dateLap.getCalendar());
            try {
                hdctr.capnhatHoadon(hd);
                JOptionPane.showMessageDialog(null, "Sửa hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi không thể sửa hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }

            this.maHoadon = Integer.valueOf(txtMHD.getText());
            this.reset();
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn tải lại trang hay không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            comboNhanvien.removeAllItems();
//            comboKhachhang.removeAllItems();
//            comboMaKM.removeAllItems();
            dateLap.setCalendar(null);
            gio.setValue(0);
            phut.setValue(0);
            giay.setValue(0);
            txtMHD.setText(null);

            btnLuu.setEnabled(false);
            btnSua.setEnabled(false);
            if (DangnhapView.quyenLogin.getChitietQuyen().contains("qlHoaDon")) {
                btnThem.setEnabled(true);
            }
            btnChonKH.setEnabled(false);
            txtMKH.setText("");
            txtMKM.setText("");
            btnChonMKM.setEnabled(false);

            this.reset();

        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void dsHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsHoaDonMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        indexOfList = source.rowAtPoint(evt.getPoint());
        String s1 = source.getModel().getValueAt(indexOfList, 0).toString();
        String s2 = source.getModel().getValueAt(indexOfList, 1).toString();
        String s3 = source.getModel().getValueAt(indexOfList, 2).toString();
        String s4 = "";
        if (source.getModel().getValueAt(indexOfList, 3) != null) {
            s4 = source.getModel().getValueAt(indexOfList, 3).toString();
        }
        String s5 = source.getModel().getValueAt(indexOfList, 4).toString();
//        String s6 = source.getModel().getValueAt(indexOfList, 5).toString();

        Calendar cal1 = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            cal1.setTime(sdf.parse(s5));
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenmaiView.class.getName()).log(Level.SEVERE, null, ex);
        }
//        comboNhanvien.set;
//        txtTKM.setText(s2);
//        txtDKKM.setText(s3);
//        txtPTKM.setText(s4);
//        dateBD.setCalendar(cal1);
        dateLap.setCalendar(cal1);

        gio.setValue(cal1.get(Calendar.HOUR_OF_DAY));
        phut.setValue(cal1.get(Calendar.MINUTE));
        giay.setValue(cal1.get(Calendar.SECOND));
        this.setValueForCombobox();
        txtMHD.setText(s1);
        comboNhanvien.setSelectedItem(s2);
        txtMKH.setText(s3);

        txtMKM.setText(s4);
        btnChonKH.setEnabled(true);
        btnChonMKM.setEnabled(true);

        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(true);
        if (DangnhapView.quyenLogin.getChitietQuyen().contains("qlHoaDon")) {
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            btnHuy.setEnabled(true);
        }


    }//GEN-LAST:event_dsHoaDonMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hóa đơn " + txtMHD.getText() + " này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            try {
                hdctr.xoaHoaDon(Integer.parseInt(txtMHD.getText()));
                JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi không thể xóa hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }

            this.reset();
            comboNhanvien.removeAllItems();
//            comboKhachhang.removeAllItems();
//            comboMaKM.removeAllItems();
            dateLap.setCalendar(null);
            gio.setValue(0);
            phut.setValue(0);
            giay.setValue(0);
            txtMHD.setText(null);

            btnHuy.setEnabled(false);
            btnLuu.setEnabled(false);
            btnSua.setEnabled(false);
            btnThem.setEnabled(true);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
        // TODO add your handling code here:
        this.maHoadon = Integer.valueOf(txtMHD.getText());
        ChitietHoadonView cthd = new ChitietHoadonView();

        cthd.setVisible(true);
        cthd.setVariable(this.maHoadon);

        this.reset();
    }//GEN-LAST:event_btnXemCTActionPerformed

    private void comboNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboNhanvienActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnChonKHActionPerformed

    private void txtMKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKHActionPerformed

    private void btnChonMKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonMKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChonMKMActionPerformed

    private void txtMKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKMActionPerformed

    private void txtSearchBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBoxKeyPressed
        // TODO add your handling code here:
        char c= evt.getKeyChar();
        if(c==KeyEvent.VK_ENTER)
        {
            btnTimKiem.doClick();
        }
    }//GEN-LAST:event_txtSearchBoxKeyPressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchBox.getText();
        if(searchText.equals("")||searchText.equals(null))
        {
            this.reset();
        }
        else
        {
            DefaultTableModel tblModel = (DefaultTableModel) dsHoaDon.getModel();
            tblModel.getDataVector().removeAllElements();
            tblModel.fireTableDataChanged();
            if(dshd==null)
            {
                this.reset();
            }
            //dsThuoc = ThuocController.timkiemThuoc(searchText.toLowerCase());
            if (!dshd.isEmpty()) {
                dshd.forEach((hoadon1) -> {
                 if(hoadon1.getMaKhuyenmai()!=null){
                 
                    if (!hoadon1.isDaXoa()&&(String.valueOf(hoadon1.getMaHoadon()).contains(searchText)||hoadon1.getTenKhachhang().contains(searchText)||hoadon1.getMaKhuyenmai().contains(searchText)||hoadon1.getTenNhanvien().contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.DAY_OF_MONTH)).contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.MONTH)).contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.YEAR)).contains(searchText))) {                     
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatted = format1.format(hoadon1.getNgayLap().getTime());
                String nhanvien = hoadon1.getMaNhanvien() + " - " + hoadon1.getTenNhanvien();
                String khachhang = hoadon1.getMaKhachhang() + " - " + hoadon1.getTenKhachhang();
                tblModel.addRow(new Object[]{
                    hoadon1.getMaHoadon(), nhanvien, khachhang, hoadon1.getMaKhuyenmai(), formatted, hoadon1.getTongTien()});
                    }
                 }
                 else
                 {
                       if (!hoadon1.isDaXoa()&&(String.valueOf(hoadon1.getMaHoadon()).contains(searchText)||hoadon1.getTenKhachhang().contains(searchText)||hoadon1.getTenNhanvien().contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.DAY_OF_MONTH)).contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.MONTH)).contains(searchText)||String.valueOf(hoadon1.getNgayLap().get(Calendar.YEAR)).contains(searchText))) {                     
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatted = format1.format(hoadon1.getNgayLap().getTime());
                String nhanvien = hoadon1.getMaNhanvien() + " - " + hoadon1.getTenNhanvien();
                String khachhang = hoadon1.getMaKhachhang() + " - " + hoadon1.getTenKhachhang();
                tblModel.addRow(new Object[]{
                    hoadon1.getMaHoadon(), nhanvien, khachhang, hoadon1.getMaKhuyenmai(), formatted, hoadon1.getTongTien()});
                    }
                 }
                });
                
            } else {
                JOptionPane.showMessageDialog(this, "Danh sách hóa đơn rỗng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonMKM;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> comboNhanvien;
    private com.toedter.calendar.JDateChooser dateLap;
    private javax.swing.JTable dsHoaDon;
    private javax.swing.JSpinner giay;
    private javax.swing.JSpinner gio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner phut;
    private javax.swing.JTextField txtMHD;
    private javax.swing.JTextField txtMKH;
    private javax.swing.JTextField txtMKM;
    private com.raven.chart.TextFieldSuggestion txtSearchBox;
    // End of variables declaration//GEN-END:variables
}
