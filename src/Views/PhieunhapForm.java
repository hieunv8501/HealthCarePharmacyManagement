package Views;

import Controllers.DonvitinhController;
import Controllers.LoaithuocController;
import Controllers.LonhapController;
import Controllers.NhacungcapController;
import Controllers.NhanvienController;
import Controllers.PhieunhapController;
import Controllers.ThuocController;
import Helpers.ChonNhaCungCapHelper;
import Helpers.PriceFormatter;
import Models.ChitietPhieunhap;
import Models.Donvitinh;
import Models.Nhacungcap;
import Models.Nhanvien;
import Models.Phieunhap;
import Models.Thuoc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieunhapForm extends JPanel {

    ChonSanPhamPanel target;

    public void setTarget(ChonSanPhamPanel target) {
        this.target = target;
    }

    public PhieunhapForm() {
        initComponents();

        PhieuNhapHang pnhPanel = new PhieuNhapHang();
        ChonSanPhamPanel cspPanel = new ChonSanPhamPanel();
        //pnhPanel.setTarget(cspPanel);
        //cspPanel.setTarget(pnhPanel);
    }

    class ChonSanPhamPanel extends JPanel {

        public String donviTinh, donviQuidoi;
        PhieuNhapHang target;
        int defaultDongia = 0, defaultSoLuongValue = 1;

        public ChonSanPhamPanel() {
            //Table sản phẩm nhập
            initTable(tblSanphamNhap);

            //addDocumentListener(txtTimkiem);
            btnLammoi1.addActionListener((ae) -> {
                refreshTable(tblSanphamNhap);
            });

            //set Editable
            txtTongtien.setEditable(false);
            //txtTongtien.setText(PriceFormatter.format(0));
            txtMathuoc.setEditable(false);
            txtLoaithuoc.setEditable(false);
            txtTenthuoc.setEditable(false);
            spnDongiaNhap.setValue(defaultDongia);
            //spnSoluongNhap.setValue(defaultSoLuongValue);
            btnThem.addActionListener((ae) -> {
                try {
                    int mathuoc = Integer.parseInt(txtMathuoc.getText());
                    int mapn = Integer.parseInt(txtMaphieunhap.getText());
                    try {
                        spnSoluongNhap.commitEdit();
                        spnDongiaNhap.commitEdit();
                        spnTilequydoi.commitEdit();
                    } catch (java.text.ParseException e) {
                    }
                    int soluongNhap = (Integer) spnSoluongNhap.getValue();
                    int dongiaNhap = (Integer) spnDongiaNhap.getValue();
                    int tileQuydoi = (Integer) spnTilequydoi.getValue();

                    DateTimeFormatter formatter;
                    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);
                    //System.out.println(sdf.format(dateNSX.getDateEditor().getUiComponent()));
                    //System.out.println(LocalDate.parse(sdf.format(((JTextField) dateNHH.getDateEditor().getUiComponent()).getText())));
                    LocalDate dateStrNSX = LocalDate.parse(((JTextField) dateNSX.getDateEditor().getUiComponent()).getText(), formatter);
                    LocalDate dateStrNHH = LocalDate.parse(((JTextField) dateNHH.getDateEditor().getUiComponent()).getText(), formatter);
                    if (soluongNhap > 0 && dongiaNhap > 0) {

                        //System.out.println("Kiểm tra dữ liệu: " + mapn + " " + mathuoc + " " + dateStrNSX + " " + dateStrNHH + " " + soluongNhap + " " + dongiaNhap);
                        this.target.addChiTiet(mapn, mathuoc, dateStrNSX, dateStrNHH, soluongNhap, dongiaNhap);
                    } else if (soluongNhap <= 0) {
                        JOptionPane.showMessageDialog(spnSoluongNhap, "Số lượng nhập phải là số dương!");
                        spnSoluongNhap.requestFocus();
                    } else if (dongiaNhap < 0) {
                        JOptionPane.showMessageDialog(spnDongiaNhap, "Đơn giá nhập không được âm!");
                        spnSoluongNhap.requestFocus();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(spnSoluongNhap, "Số lượng phải là số nguyên!");
                    spnSoluongNhap.requestFocus();
                }
            });

            tblSanphamNhap.addMouseListener(new MouseAdapter() {
                //Hiển thị sản phẩm dòng
                @Override
                public void mouseReleased(MouseEvent me) {
                    String masp = getSelectedThuoc(0);
                    if (masp != null) {
                        showInfo(masp);
                    }
                }
            });

            clear();
        }

        public void setTarget(PhieuNhapHang target) {
            this.target = target;
        }

        public void refreshTable(JTable tbl) {
            pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc();
            //setDataToTable(lnCtrl.timTatCaThuoc("", "Tất cả", -1, -1, -1, -1, 0), tblSanphamNhap);
        }

        public void refreshAll() {
            refreshTable(tblSanphamNhap);
            txtMathuoc.setText("");
            txtLoaithuoc.setText("");
            txtTenthuoc.setText("");

            spnDongiaNhap.setValue(defaultDongia);
            spnSoluongNhap.setValue(defaultSoLuongValue);
            lblHinhanhThuoc.setIcon(null);
        }

        public String getSelectedThuoc(int col) {
            int i = tblSanphamNhap.getSelectedRow();
            if (i >= 0) {
                int realI = tblSanphamNhap.convertRowIndexToModel(i);
                return tblSanphamNhap.getModel().getValueAt(realI, col).toString();
            }
            return null;
        }

        public void showInfo(String mathuoc) {
            // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
            if (mathuoc != null) {
                // show hình
                for (ChitietPhieunhap thuoc : pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc()) {
                    if (String.valueOf(thuoc.getMaThuoc()).equals(mathuoc)) {
                        int w = lblHinhanhThuoc.getWidth();
                        int h = lblHinhanhThuoc.getHeight();
                        //ImageIcon img = new ImageIcon(getClass().getResource("/Images/Thuoc_Images/" + thuoc.getHinhAnh()));
                        //Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                        //  lblHinhanhThuoc.setIcon(new ImageIcon(imgScaled));

                        // show info
                        txtMathuoc.setText(String.valueOf(thuoc.getMaThuoc()));
                        txtTenthuoc.setText(thuoc.getTenThuoc());
                        txtLoaithuoc.setText(thuoc.getTenloaiThuoc());
                        donviTinh = thuoc.getTenDonvitinh();
                        donviQuidoi = thuoc.getTenDonvibanle();
                        showComboboxDonVi();

                        spnTilequydoi.setValue(thuoc.getTileQuidoi());
                        spnSoluongNhap.setValue(defaultSoLuongValue);
                        spnDongiaNhap.setValue(defaultDongia);
                        //spnDongiaNhap.setValue(PriceFormatter.format(0));
                        return;
                    }
                }
            }
        }

        public void showComboboxDonVi() {
            cbbDVT.removeAllItems();
            cbbDonviquydoi.removeAllItems();

            List<Donvitinh> dsDVT;
            dsDVT = DonvitinhController.getDanhSachDonvitinh();
            for (Donvitinh t : dsDVT) {
                if (t instanceof Donvitinh) {
                    Donvitinh dvt = (Donvitinh) t;
                    cbbDVT.addItem(String.valueOf(dvt.getMaDonvitinh()) + " - " + dvt.getTenDonvitinh());
                    cbbDonviquydoi.addItem(String.valueOf(dvt.getMaDonvitinh()) + " - " + dvt.getTenDonvitinh());
                }
            }
            for (Donvitinh t : dsDVT) {
                if (t instanceof Donvitinh) {
                    Donvitinh dvt = (Donvitinh) t;
                    if (dvt.getTenDonvitinh().equals(donviTinh)) {
                        cbbDVT.setSelectedItem(dvt.getMaDonvitinh() + " - " + dvt.getTenDonvitinh());
                    }
                    if (dvt.getTenDonvitinh().equals(donviQuidoi)) {
                        cbbDonviquydoi.setSelectedItem(dvt.getMaDonvitinh() + " - " + dvt.getTenDonvitinh());
                    }
                }
            }
        }

        public void clear() {
            txtNCC.setText("");
            txtTongtien.setText("");
            dsctpn.clear();
            setDataToTable(pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc(), tblSanphamNhap);
        }

        private void setDataToTable(ArrayList<ChitietPhieunhap> dsctpn, JTable tbl) {
            tbModel = (DefaultTableModel) tbl.getModel();
            tbModel.setRowCount(0);
            for (ChitietPhieunhap ctpn : dsctpn) {
                if (ctpn.isDaXoa() == false) {
                    tbModel.addRow(new Object[]{
                        ctpn.getMaThuoc(),
                        ctpn.getTenloaiThuoc(),
                        ctpn.getTenThuoc(),
                        ctpn.getTenDonvitinh(),
                        ctpn.getTenDonvibanle(),
                        ctpn.getTileQuidoi(),
                        String.valueOf(ctpn.getSoLuongConLai()),});
                }
            }
        }

        private void addDocumentListener(JTextField tx) {
            // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
            tx.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }
            });
        }

        public void txSearchOnChange() {
            //setDataToTable(lnCtrl.search(txtTimKiem.getText(), "Tất cả", -1, -1, -1, -1, 0), tblSanphamNhap);
        }

        public String getSelectedSanPham(int col) {
            int i = tblSanphamNhap.getSelectedRow();
            if (i >= 0) {
                int realI = tblSanphamNhap.convertRowIndexToModel(i);
                return tblSanphamNhap.getModel().getValueAt(realI, col).toString();
            }
            return null;
        }

    }

    class PhieuNhapHang extends JPanel {
        public PhieuNhapHang() {

            //Table phiếu nhập
            initTable(tblPhieunhap);
            // Button chọn nhà cung cấp
            btnChonNCC.addActionListener((ae) -> {
                ChonNhaCungCapHelper chonNCC = new ChonNhaCungCapHelper(txtNCC);
                chonNCC.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        int _maNCC = Integer.parseInt(txtNCC.getText());
                        nhacungcap = NhacungcapController.getNhacungcap(_maNCC);
                        if (nhacungcap != null) {
                            txtNCC.setText(nhacungcap.getTenNhacungcap() + " (" + nhacungcap.getMaNhacungcap() + ")");
                        }
                    }
                });
            });

            // set Text
            if (DangnhapView.nhanVienLogin != null) {
                nhanvien = DangnhapView.nhanVienLogin;
                txtNhanvienlap.setText(nhanvien.getTenNhanvien() + " (" + nhanvien.getMaNhanvien() + ")");
            }
            txtMaphieunhap.setText(String.valueOf(PhieunhapController.getNextID()));
            txtThoigiannhap.setText((LocalDate.now().toString() + " " + LocalTime.now().toString()));

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    //txtThoigiannhap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("YYYY-mm-dd HH:mm:ss")));
                    //txtThoigiannhap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    if (txtNhanvienlap.getText().equals("")
                            || txtNCC.getText().equals("")
                            || txtTongtien.getText().equals("")
                            || txtTongtien.getText().equals("0")) {
                        btnNhaphang.setEnabled(false);
                    } else {
                        btnNhaphang.setEnabled(true);
                    }
                }
            }, 0, 1000);

            // set editable
            txtMaphieunhap.setEditable(false);
            txtNhanvienlap.setEditable(false);
            txtNCC.setEditable(false);
            txtThoigiannhap.setEditable(false);
            //txtGiolap.setEditable(false);

            //table Phiếu nhập
            btnXoa.addActionListener((ae) -> {
                btnXoaOnClick();
            });
            btnSua.addActionListener((ae) -> {
                btnSuaOnClick();
            });
            btnLammoi.addActionListener((ae) -> {
                setDataToTable(dsctpn, tblPhieunhap);
            });

//        tblPhieunhap.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
//        tblPhieunhap.setAlignment(0, JLabel.CENTER);
//        tblPhieunhap.setAlignment(1, JLabel.CENTER);
//        tblPhieunhap.setAlignment(3, JLabel.CENTER);
//        tblPhieunhap.setAlignment(4, JLabel.RIGHT);
//        tblPhieunhap.setAlignment(5, JLabel.RIGHT);
            btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
            btnNhaphang.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_us_dollar_30px.png")));

            btnHuy.addActionListener((ae) -> {
                btnHuyOnClick();
            });
            btnNhaphang.addActionListener((ae) -> {
                btnNhapHangOnClick();
            });

        }

//        public void setTarget(ChonSanPhamPanel target) {
//            this.target = target;
//        }
        
        public void addChiTiet(int mapn, int mathuoc, LocalDate ngaysanxuat, LocalDate ngayhethan, int soluong, float dongia) {
            Boolean daTontai = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
            for (ChitietPhieunhap ctpn : pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc()) {
                if (ctpn.getMaThuoc() == mathuoc) {
                    int tongSoLuong = soluong + ctpn.getSoluong();
                    ctpn.setSoluong(tongSoLuong); // có rồi thì thay đổi số lượng
                    daTontai = true;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate dateStrNSX = LocalDate.parse(sdf.format(((JTextField) dateNSX.getDateEditor().getUiComponent()).getText()));
            LocalDate dateStrNHH = LocalDate.parse(sdf.format(((JTextField) dateNHH.getDateEditor().getUiComponent()).getText()));

            if (!daTontai) { // nếu chưa có thì thêm mới

                ChitietPhieunhap ctpn = new ChitietPhieunhap(mapn, mathuoc, soluong, dongia, dateStrNSX, dateStrNHH);
                dsctpn.add(ctpn);
            }
            // cập nhật lại table
            setDataToTable(dsctpn, tblPhieunhap);
        }

        public void setDataToTable(ArrayList<ChitietPhieunhap> dsctpn, JTable tbl) {
            tbModel = (DefaultTableModel) tbl.getModel();
            tbModel.setRowCount(0);
            float tongtien = 0;
            int stt = 1;
            for (ChitietPhieunhap ctpn : dsctpn) {
                int _mathuoc = ctpn.getMaThuoc();
                Thuoc thuoc = ThuocController.getThuoc(_mathuoc);
                String tensp = thuoc.getTenThuoc();
                int soluong = ctpn.getSoluong();
                float dongia = ctpn.getDongia();
                float thanhtien = soluong * dongia;

                tbModel.addRow(new Object[]{
                    String.valueOf(stt),
                    _mathuoc,
                    tensp,
                    String.valueOf(soluong),
                    String.valueOf(dongia),
                    String.valueOf(thanhtien),});
                stt++;
                tongtien += thanhtien;
            }

            // check tong tien
            tbModel.addRow(new String[]{"", "", "", "", "", ""});
            tbModel.addRow(new String[]{"", "", "", "", "Tổng tiền", String.valueOf(tongtien)});
            txtTongtien.setText(String.valueOf(tongtien));
        }

        private void btnNhapHangOnClick() {
            Date date = Date.from(LocalDate.parse(txtThoigiannhap.getText()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Phieunhap pn = new Phieunhap(
                    Integer.parseInt(txtMaphieunhap.getText()),
                    NhacungcapController.getNhacungcap(nhacungcap.getMaNhacungcap()),
                    NhanvienController.getNhanVien(nhanvien.getMaNhanvien()),
                    //LocalDate to Calendar
                    calendar,
                    Float.parseFloat(txtTongtien.getText()),
                    false);
            PhieunhapController pnCtrl = new PhieunhapController();
            pnCtrl.themPhieuNhap(pn);

            for (ChitietPhieunhap ct : dsctpn) {
                pnCtrl.themChitietPhieunhap(ct);
            }

            int reply = JOptionPane.showConfirmDialog(getRootPane(),
                    "Nhập hàng thành công, bạn có muốn IN PHIẾU NHẬP?", "Thành công",
                    JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.OK_OPTION) {
                // WritePDF().writePhieuNhap(txtMaphieunhap.getText());
            }
            txtMaphieunhap.setText(String.valueOf(PhieunhapController.getNextID())); // lấy mã cho phiếu nhập mới
            clear();

            refreshAll();
        }

        private void btnXoaOnClick() {
            int i = tblPhieunhap.getSelectedRow();
            if (i >= 0 && i < dsctpn.size()) {
                dsctpn.remove(i);
                setDataToTable(dsctpn, tblPhieunhap);
            }
        }

        private void btnSuaOnClick() {
            int i = tblPhieunhap.getSelectedRow();
            if (i >= 0 && i < dsctpn.size()) {
                ChitietPhieunhap ct = dsctpn.get(i);
                //this.target.showInfo(ct.getThuoc().getMaThuoc(), ct.getSoluong());

                //dsctpn.remove(i);
                setDataToTable(dsctpn, tblPhieunhap);
            }
        }

        public void clear() {
            txtNCC.setText("");
            txtTongtien.setText("");
            dsctpn.clear();
            setDataToTable(dsctpn, tblPhieunhap);
        }

        private void btnHuyOnClick() {
            clear();
        }

        public void refreshAll() {
            refreshTable(tblSanphamNhap);
            txtMathuoc.setText("");
            txtLoaithuoc.setText("");
            txtTenthuoc.setText("");
            spnDongiaNhap.getModel().setValue(1);
            spnSoluongNhap.getModel().setValue(1);
            txtMaphieunhap.setText("");
            txtNhanvienlap.setText("");
            txtNCC.setText("");
            txtThoigiannhap.setText("");
        }

//        public void setTarget(ChonSanPhamPanel target) {
//            this.target = target;
//        }
        public void refreshTable(JTable tbl) {
            //ThuocController.getDanhSachThuoc();
            //setDataToTable(ThuocController.timTatCaThuoc("", "Tất cả", -1, -1, -1, -1, 0), tblSanphamNhap);
        }

       

    }

    PhieunhapController pnCtrl = new PhieunhapController();
    ThuocController thuocCtrl = new ThuocController();
    LoaithuocController loaiThuocController = new LoaithuocController();
    NhacungcapController nccCtrl = new NhacungcapController();
    NhanvienController nvCtrl = new NhanvienController();
    LonhapController lnCtrl = new LonhapController();

    Nhanvien nhanvien;
    Nhacungcap nhacungcap;
    ArrayList<ChitietPhieunhap> dsctpn = new ArrayList<>();
    DefaultTableModel tbModel;

    private void initTable(JTable tbl) {
        tbModel = (DefaultTableModel) tbl.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl.setDefaultRenderer(String.class, centerRenderer);
        ((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbl.getTableHeader().setOpaque(false);
        tbl.getTableHeader().setBackground(Color.YELLOW);
        tbl.setUpdateSelectionOnSort(true);
        tbl.setFillsViewportHeight(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHinhanhThuoc = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanphamNhap = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieunhap = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnLammoi1 = new javax.swing.JButton();
        dateNSX = new com.toedter.calendar.JDateChooser();
        dateNHH = new com.toedter.calendar.JDateChooser();
        txtLoaithuoc = new javax.swing.JTextField();
        spnSoluongNhap = new javax.swing.JSpinner();
        txtNCC = new javax.swing.JTextField();
        txtTongtien = new javax.swing.JTextField();
        btnChonNCC = new javax.swing.JButton();
        cbbDVT = new javax.swing.JComboBox<>();
        cbbDonviquydoi = new javax.swing.JComboBox<>();
        spnTilequydoi = new javax.swing.JSpinner();
        spnDongiaNhap = new javax.swing.JSpinner();
        txtMaphieunhap = new javax.swing.JTextField();
        txtThoigiannhap = new javax.swing.JTextField();
        txtNhanvienlap = new javax.swing.JTextField();
        txtMathuoc = new javax.swing.JTextField();
        txtTenthuoc = new javax.swing.JTextField();
        txtTimkiem = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        btnNhaphang = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();

        lblHinhanhThuoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSanphamNhap.setAutoCreateRowSorter(true);
        tblSanphamNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        tblSanphamNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuốc", "Loại thuốc", "Tên thuốc", "Đơn vị tính", "Đơn vị bán lẻ", "Tỉ lệ quy đổi", "Số lượng còn lại"
            }
        ));
        jScrollPane2.setViewportView(tblSanphamNhap);

        btnThem.setText("Thêm");

        tblPhieunhap.setAutoCreateRowSorter(true);
        tblPhieunhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        tblPhieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Lô nhập", "Mã thuốc", "Tên thuốc", "Ngày sản xuất", "Ngày hết hạn", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblPhieunhap);

        btnLammoi1.setText("Làm mới");

        dateNSX.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày sản xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        dateNSX.setDateFormatString("dd-MM-yyyy");
        dateNSX.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateNSX.setMinimumSize(new java.awt.Dimension(56, 56));

        dateNHH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày hết hạn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        dateNHH.setDateFormatString("dd-MM-yyyy");
        dateNHH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateNHH.setMinimumSize(new java.awt.Dimension(56, 56));

        txtLoaithuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại thuốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnSoluongNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnSoluongNhap.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnSoluongNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        txtNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCCActionPerformed(evt);
            }
        });

        txtTongtien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        btnChonNCC.setText("Chọn");

        cbbDVT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn vị tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        cbbDonviquydoi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn vị quy đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnTilequydoi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnTilequydoi.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnTilequydoi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tỉ lệ quy đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnDongiaNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnDongiaNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtMaphieunhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtThoigiannhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời gian nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtNhanvienlap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtMathuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã thuốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtTenthuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên thuốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtTimkiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbDonviquydoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnTilequydoi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnDongiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateNHH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(586, 586, 586))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLoaithuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMaphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThoigiannhap, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNhanvienlap, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonNCC)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtThoigiannhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNhanvienlap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnChonNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoaithuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addComponent(dateNHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnDongiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnTilequydoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbDonviquydoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimkiem)
                    .addComponent(btnLammoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnHuy.setText("Hủy");

        btnNhaphang.setText("Nhập hàng");

        btnXoa.setText("Xóa");

        btnSua.setText("Sửa");

        btnLammoi.setText("Làm mới");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHinhanhThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLammoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(576, 576, 576)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinhanhThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 27, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNCC;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnLammoi1;
    private javax.swing.JButton btnNhaphang;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbDVT;
    private javax.swing.JComboBox<String> cbbDonviquydoi;
    private com.toedter.calendar.JDateChooser dateNHH;
    private com.toedter.calendar.JDateChooser dateNSX;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhanhThuoc;
    private javax.swing.JSpinner spnDongiaNhap;
    private javax.swing.JSpinner spnSoluongNhap;
    private javax.swing.JSpinner spnTilequydoi;
    private javax.swing.JTable tblPhieunhap;
    private javax.swing.JTable tblSanphamNhap;
    private javax.swing.JTextField txtLoaithuoc;
    private javax.swing.JTextField txtMaphieunhap;
    private javax.swing.JTextField txtMathuoc;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtNhanvienlap;
    private javax.swing.JTextField txtTenthuoc;
    private javax.swing.JTextField txtThoigiannhap;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
