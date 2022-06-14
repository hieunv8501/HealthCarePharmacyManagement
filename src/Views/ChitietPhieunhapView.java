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
        if (!DangnhapView.quyenLogin.getChitietQuyen().contains("qlPhieuNhap")) {
            btnThem.setEnabled(false);
        }

        txtMaPN.setEditable(false);
        txtDongia.setEditable(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        showData(pnCtrl.layDanhsachChitietPhieunhapTheoMaPhieuNhap(maPhieunhap));

        tblCTPN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                String malo = getSelectedRow(1);
                if (malo != null) {
                    showInfo(malo);
                }
            }
        });
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

    public void setVariable(int _mpn) {
        txtMaPN.setText(String.valueOf(this.maPhieunhap));
        List<Thuoc> dst;
        dst = PhieunhapController.layDanhSachThuoc();
        for (Thuoc t : dst) {
            if (t instanceof Thuoc) {
                Thuoc th = (Thuoc) t;
                cbbThuoc.addItem(String.valueOf(th.getMaThuoc()) + " - " + th.getTenThuoc());
            }
        }
    }

    private void showInfo(String _malo) {

        if (_malo != null) {
            for (ChitietPhieunhap ctpn : dsctpn) {
                if (String.valueOf(ctpn.getMaLo()).equals(_malo)) {
                    dsncc = NhacungcapController.getDanhSachNhacungcap();
                    dsdvt = DonvitinhController.getDanhSachDonvitinh();
                    dsnv = NhanvienController.getDanhSachNhanvien();
                    dst = hdCtrl.layDanhSachThuoc();
                    for (Thuoc t : dst) {
                        cbbThuoc.addItem(String.valueOf(t.getMaThuoc()) + " - " + t.getTenThuoc());
                        cbbThuoc.setSelectedItem(t.getMaThuoc() == ctpn.getMaThuoc()? t : "");
                    }
                    
                    for (Nhanvien nv : dsnv) {
                        cbbNhanVien.addItem(String.valueOf(nv.getMaNhanvien() + " - " + nv.getTenNhanvien()));
                       //cbbNhanVien.setSelectedItem(nv.getMaNhanvien() == ? nv : "");
                    }

                    for (Nhacungcap ncc : dsncc) {
                        cbbNhaCungCap.addItem(String.valueOf(ncc.getMaNhacungcap() + " - " + ncc.getTenNhacungcap()));
                    }

                    for (Donvitinh dvt : dsdvt) {
                        cbbDVT.addItem(String.valueOf(dvt.getMaDonvitinh() + " - " + dvt.getTenDonvitinh()));
                    }
                    // show info
                    txtMaPN.setText(String.valueOf(ctpn.getMaPhieunhap()));
                    txtMaLN.setText(String.valueOf(ctpn.getMaLo()));
                    txtDongia.setText(String.valueOf(ctpn.getDongia()));
                    System.out.println(ctpn.getSoluong());

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

        txtMaPN = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbThuoc = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spinSoluong = new com.toedter.components.JSpinField();
        jLabel4 = new javax.swing.JLabel();
        txtMaLN = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbbNhaCungCap = new javax.swing.JComboBox<>();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbDVT = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMaPN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDongia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Ðơn vị:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Đơn giá:");

        cbbThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbThuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThuocItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Chọn thuốc:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Mã lô nhập:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Số lượng:");

        spinSoluong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Mã phiếu nhập:");

        txtMaLN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Chọn nhà cung cấp:");

        cbbNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbNhaCungCap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNhaCungCapItemStateChanged(evt);
            }
        });

        cbbNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNhanVienItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Nhân viên lập");

        cbbDVT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbDVT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDVTItemStateChanged(evt);
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

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spinSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaLN, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbDVT, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDongia))))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnThem)
                        .addGap(49, 49, 49)
                        .addComponent(btnHuy)
                        .addGap(56, 56, 56)
                        .addComponent(btnSua)
                        .addGap(50, 50, 50)
                        .addComponent(btnXoa)
                        .addGap(48, 48, 48)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(spinSoluong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnHuy)
                    .addComponent(btnSua)
                    .addComponent(btnThem)
                    .addComponent(btnXoa))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThuocItemStateChanged
//        Donvitinh dvt = new Donvitinh();
//        String maThuoc = cbbThuoc.getSelectedItem().toString().split(" - ")[0];
//
//        dvt = pnCtrl.layDonvitinh(Integer.valueOf(maThuoc));
//        txt.setText(dvt.getTenDonvitinh());
//
//        Thuoc thuoc = new Thuoc();
//
//        thuoc = pnCtrl.layGiathuoc(Integer.valueOf(maThuoc));
//        txtDongia.setText(String.valueOf(thuoc.getGiaBan()));
//
//        comboLonhap.removeAllItems();
//        List<LoNhap> dsln;
//        dsln = hdctr.layDanhSachLonhap(Integer.valueOf(spinSoluong.getValue()), Integer.valueOf(maThuoc));
//
//        for (LoNhap t : dsln) {
//            if (t instanceof LoNhap) {
//                LoNhap ln = (LoNhap) t;
//                comboLonhap.addItem("Mã lô: " + String.valueOf(ln.getMaLo()) + " - Số lượng còn lại: " + ln.getSoluongConlai());
//            }
//        }
    }//GEN-LAST:event_cbbThuocItemStateChanged

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

    private void cbbNhaCungCapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNhaCungCapItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNhaCungCapItemStateChanged

    private void cbbNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNhanVienItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNhanVienItemStateChanged

    private void cbbDVTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDVTItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDVTItemStateChanged

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//        // TODO add your handling code here:
//        txt.setText(String.valueOf(hdctr.getMaxMHD()));
//        btnLuu.setEnabled(true);
//        btnHuy.setEnabled(true);
//        btnThem.setEnabled(false);
//        btnChonKH.setEnabled(true);
//        btnChonMKM.setEnabled(true);
//
//        this.setValueForCombobox();
//
//        Calendar cld = Calendar.getInstance();
//        dateLap.setCalendar(cld);
//
//        Date date = new Date();
//        Calendar calendar = GregorianCalendar.getInstance();
//        calendar.setTime(date);
//        gio.setValue(calendar.get(Calendar.HOUR_OF_DAY));
//        phut.setValue(calendar.get(Calendar.MINUTE));
//        giay.setValue(calendar.get(Calendar.SECOND));

    }//GEN-LAST:event_btnThemActionPerformed

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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

//        if ((int) gio.getValue() < 0 || (int) gio.getValue() > 24 || (int) phut.getValue() < 0 || (int) phut.getValue() > 60 || (int) giay.getValue() < 0 || (int) giay.getValue() > 60) {
//            JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//
//        } else {
//            String MNV = comboNhanvien.getSelectedItem().toString().split(" - ")[0];
//            String MKH = txtMKH.getText().split(" - ")[0];
//            String MKM;
//            if (txtMKM.getText().equals("")) {
//                MKM = null;
//            } else {
//                MKM = txtMKM.getText();
//            }
//
//            dateLap.getCalendar().set(Calendar.HOUR, (int) gio.getValue());
//            dateLap.getCalendar().set(Calendar.MINUTE, (int) phut.getValue());
//            dateLap.getCalendar().set(Calendar.SECOND, (int) giay.getValue());
//            Hoadon hd = new Hoadon(Integer.parseInt(txtMHD.getText()), Integer.parseInt(MNV), Integer.parseInt(MKH), MKM, dateLap.getCalendar());
//            hdctr.capnhatHoadon(hd);
//            this.maHoadon = Integer.valueOf(txtMHD.getText());
//            this.reset();
//        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
//        int input = JOptionPane.showConfirmDialog(null,
//            "Bạn có chắc muốn xóa thuốc này khỏi hóa đơn không không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (input == 0) {
//            String maThuoc = comboThuoc.getSelectedItem().toString().split(" - ")[0];
//            String maLo1 = comboLonhap.getSelectedItem().toString().split(" - ")[0];
//            String maLo2 = maLo1.toString().split(": ")[1];
//
//            ChitietHoadon cthd = new ChitietHoadon(maHoadon, Integer.parseInt(maThuoc), Integer.parseInt(maLo2), 0, 0, false);
//            hdctr.xoaCTHD(cthd);
//            this.reset();
//        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbDVT;
    private javax.swing.JComboBox<String> cbbNhaCungCap;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JComboBox<String> cbbThuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField spinSoluong;
    private javax.swing.JTable tblCTPN;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMaLN;
    private javax.swing.JTextField txtMaPN;
    // End of variables declaration//GEN-END:variables
}
