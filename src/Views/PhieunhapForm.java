package Views;

import Components.DateButton;
import Components.WritePDF;
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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class PhieunhapForm extends JPanel {

    public PhieunhapForm() {
        initComponents();
        PhieuNhapHang pnhPanel = new PhieuNhapHang();
        ChonSanPhamPanel cspPanel = new ChonSanPhamPanel();
        pnhPanel.setTarget(cspPanel);
        cspPanel.setTarget(pnhPanel);
    }

    class ChonSanPhamPanel extends JPanel {

        public String donviTinh, donviQuidoi;
        PhieuNhapHang target2;
        int defaultDongia = 0, defaultSoLuongValue = 1;

        public ChonSanPhamPanel() {
            //Table s???n ph???m nh???p
            initTable(tblSanphamNhap);

            //addDocumentListener(txtTimkiem);
            btnLammoi1.addActionListener((ae) -> {
                refreshTable(tblSanphamNhap);
            });

            //set Editable
            txtTongtien.setEditable(false);
            txtTongtien.setText(PriceFormatter.format(0));
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

//                    Calendar cld1 = Calendar.getInstance();
//                    Calendar cld2 = Calendar.getInstance();
//                    dateNSX.setCalendar(cld1);
//                    dateNHH.setCalendar(cld2);
//                    Date date1 = new Date();
//                    Date date2 = new Date();
//                    Calendar calendar1 = GregorianCalendar.getInstance();
//                    Calendar calendar2 = GregorianCalendar.getInstance();
//                    calendar1.setTime(date1);
//                    calendar2.setTime(date2);
                    dateNSX.getCalendar().set(Calendar.HOUR, 0);
                    dateNSX.getCalendar().set(Calendar.MINUTE, 0);
                    dateNSX.getCalendar().set(Calendar.SECOND, 0);

                    dateNHH.getCalendar().set(Calendar.HOUR, 0);
                    dateNHH.getCalendar().set(Calendar.MINUTE, 0);
                    dateNHH.getCalendar().set(Calendar.SECOND, 0);

                    if (soluongNhap > 0 && dongiaNhap > 0) {
//addChiTiet(int mapn, int mathuoc, Calendar ngaysanxuat, Calendar ngayhethan, int soluong, float dongia)
//                        System.err.println("Ki???m tra d??? li???u: " + mapn + " " + mathuoc + " " + dateNSX.getCalendar() + " " + dateNHH.getCalendar() + " " + soluongNhap + " " + dongiaNhap);
                        this.target2.addChiTiet(mapn, mathuoc, dateNSX.getCalendar(), dateNHH.getCalendar(), soluongNhap, dongiaNhap);
                    } else if (soluongNhap <= 0) {
                        JOptionPane.showMessageDialog(spnSoluongNhap, "S??? l?????ng nh???p ph???i l?? s??? d????ng!");
                        spnSoluongNhap.requestFocus();
                    } else if (dongiaNhap < 0) {
                        JOptionPane.showMessageDialog(spnDongiaNhap, "????n gi?? nh???p kh??ng ???????c ??m!");
                        spnSoluongNhap.requestFocus();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(spnSoluongNhap, "S??? l?????ng ph???i l?? s??? nguy??n!");
                    spnSoluongNhap.requestFocus();
                }
            });

            tblSanphamNhap.addMouseListener(new MouseAdapter() {
                //Hi???n th??? s???n ph???m d??ng
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
            this.target2 = target;
        }

        public void refreshTable(JTable tbl) {
            dsctpnCSP = pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc();
            //setDataToTable(lnCtrl.timTatCaThuoc("", "T???t c???", -1, -1, -1, -1, 0), tblSanphamNhap);
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
                // show h??nh
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
                        txtNCC.setText(NhacungcapController.getNhacungcap(thuoc.getMaNhacungcap()).getTenNhacungcap() + " - " + String.valueOf(thuoc.getMaNhacungcap()));
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
            dsctpnCSP = pnCtrl.layTatCaChiTietLoNhapTheoMaThuoc();
            setDataToTable(dsctpnCSP, tblSanphamNhap);
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
            //setDataToTable(lnCtrl.search(txtTimKiem.getText(), "T???t c???", -1, -1, -1, -1, 0), tblSanphamNhap);
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

        ChonSanPhamPanel target1;

        public PhieuNhapHang() {

            //Table phi???u nh???p
            initTable(tblPhieunhap);
            // Button ch???n nh?? cung c???p
            btnChonNCC.addActionListener((ae) -> {
                ChonNhaCungCapHelper chonNCC = new ChonNhaCungCapHelper(txtNCC);
                chonNCC.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        int _maNCC = Integer.parseInt(txtNCC.getText());
                        nhacungcap = NhacungcapController.getNhacungcap(_maNCC);
                        if (nhacungcap != null) {
                            txtNCC.setText(nhacungcap.getTenNhacungcap() + " - " + String.valueOf(_maNCC));
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
            var tg = LocalDate.now().toString() + " " + LocalTime.now().toString();
            txtThoigiannhap.setText(String.valueOf(tg));
            String tongtien = "0.0";
            //txtNCC.setText(NhacungcapController.getNhacungcap(Thuoc.getMaNhacungcap()).getTenNhacungcap() + "(" + String.valueOf(thuoc.getMaNhacungcap()) + ")");
            txtTongtien.setText(tongtien);

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    //txtThoigiannhap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    if (txtNhanvienlap.getText().equals("")
                            //|| txtNCC.getText().equals("")
                            || txtTongtien.getText().equals("")
                            || txtTongtien.getText().equals("0.0")) {
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

            //table Phi???u nh???p
            btnXoa.addActionListener((ae) -> {
                btnXoaOnClick();
            });
            btnSua.addActionListener((ae) -> {
                btnSuaOnClick();
            });
            btnLammoi.addActionListener((ae) -> {
                setDataToTable(dsctpnPNH, tblPhieunhap);
            });

            btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_cancel_30px_1.png")));
            btnNhaphang.setIcon(new ImageIcon(this.getClass().getResource("/Images/icons8_us_dollar_30px.png")));

            btnHuy.addActionListener((ae) -> {
                btnHuyOnClick();
            });
            btnNhaphang.addActionListener((ae) -> {
                btnNhapHangOnClick();
            });

        }

        public void setTarget(ChonSanPhamPanel target) {
            this.target1 = target;
        }

        public void addChiTiet(int mapn, int mathuoc, Calendar ngaysanxuat, Calendar ngayhethan, int soluong, float dongia) {
            System.err.println(ngaysanxuat + "     " + ngayhethan);
            Boolean daTontai = false; // check xem trong danh s??ch chi ti???t phi???u nh???p ???? c?? s???n ph???m n??y ch??a
            if (!dsctpnPNH.isEmpty()) {
                for (ChitietPhieunhap ctpn : dsctpnPNH) {
                    if (ctpn.getMaThuoc() == mathuoc) {
                        int tongSoLuong = soluong + ctpn.getSoluong();
                        ctpn.setSoluong(tongSoLuong); // c?? r???i th?? thay ?????i s??? l?????ng
                        daTontai = true;
                    }
                }
                if (!daTontai) { // n???u ch??a c?? th?? th??m m???i
                    ChitietPhieunhap ctpn = new ChitietPhieunhap(mapn, mathuoc, soluong, dongia, ngaysanxuat, ngayhethan);
                    dsctpnPNH.add(ctpn);
                }
            } else {
                if (!daTontai) { // n???u ch??a c?? th?? th??m m???i
                    ChitietPhieunhap ctpn = new ChitietPhieunhap(mapn, mathuoc, soluong, dongia, ngaysanxuat, ngayhethan);
                    dsctpnPNH.add(ctpn);
                }
            }
            // c???p nh???t l???i table
            setDataToTable(dsctpnPNH, tblPhieunhap);
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
//                Calendar nsx = ctpn.getNgaySanxuat();
//                Calendar nhh = ctpn.getNgayHethan();
                int soluong = ctpn.getSoluong();
                float dongia = ctpn.getDongia();
                float thanhtien = soluong * dongia;

//                Date dateNSX = nsx.getInstance().getTime();
//                Date dateNHH = nhh.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strNSX = sdf.format(ctpn.getNgaySanxuat().getTime());
                String strNHH = sdf.format(ctpn.getNgayHethan().getTime());

                tbModel.addRow(new Object[]{
                    String.valueOf(stt),
                    _mathuoc,
                    tensp,
                    strNSX,
                    strNHH,
                    String.valueOf(soluong),
                    String.valueOf(dongia),
                    String.valueOf(thanhtien),});
                stt++;
                tongtien += thanhtien;
            }

            // check tong tien
            tbModel.addRow(new String[]{"", "", "", "", "", "", "", ""});
            tbModel.addRow(new String[]{"", "", "", "", "", "", "T???ng ti???n", String.valueOf(tongtien)});
            txtTongtien.setText(String.valueOf(tongtien));
        }

        private void btnNhapHangOnClick() {

            Calendar calendar = Calendar.getInstance();
            calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());

            Phieunhap pn = new Phieunhap(
                    Integer.parseInt(txtMaphieunhap.getText()),
                    NhacungcapController.getNhacungcap(Integer.parseInt(txtNCC.getText().split(" - ")[1])),
                    NhanvienController.getNhanVien(nhanvien.getMaNhanvien()),
                    //LocalDate to Calendar
                    calendar,
                    Float.parseFloat(txtTongtien.getText()),
                    false);
            PhieunhapController pnCtrl = new PhieunhapController();

            pnCtrl.themPhieuNhap(pn);

            for (ChitietPhieunhap ct : dsctpnPNH) {
                System.out.println(ct.getMaPhieunhap());
                System.out.println(ct.getMaThuoc());
                System.out.println(ct.getSoluong());
                System.out.println(ct.getDongia());

                System.out.println(ct.getNgaySanxuat().toString());
                System.out.println(ct.getNgayHethan().toString());

                pnCtrl.themChitietPhieunhap(ct);
            }

            int reply = JOptionPane.showConfirmDialog(getRootPane(),
                    "Nh???p h??ng th??nh c??ng, b???n c?? mu???n IN PHI???U NH???P?", "Th??nh c??ng",
                    JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.OK_OPTION) {
                 new WritePDF().writePhieuNhap(Integer.parseInt(txtMaphieunhap.getText()));
            }
            txtMaphieunhap.setText(String.valueOf(PhieunhapController.getNextID())); // l???y m?? cho phi???u nh???p m???i
            clear();

            refreshAll();
        }

        private void btnXoaOnClick() {
            int i = tblPhieunhap.getSelectedRow();
            if (i >= 0 && i < dsctpnPNH.size()) {
                dsctpnPNH.remove(i);
                setDataToTable(dsctpnPNH, tblPhieunhap);
            }
        }

        private void btnSuaOnClick() {
            int i = tblPhieunhap.getSelectedRow();
            if (i >= 0 && i < dsctpnPNH.size()) {
                ChitietPhieunhap ct = dsctpnPNH.get(i);
                this.target1.showInfo(String.valueOf(ct.getMaThuoc()));

                //dsctpn.remove(i);
                setDataToTable(dsctpnPNH, tblPhieunhap);
            }
        }

        public void clear() {
            txtNCC.setText("");
            txtTongtien.setText("");
            dsctpnPNH.clear();
            setDataToTable(dsctpnPNH, tblPhieunhap);
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
            txtTongtien.setText("0.0");
            txtNCC.setText("");
            txtThoigiannhap.setText("");
        }

        public void refreshTable(JTable tbl) {
            //ThuocController.getDanhSachThuoc();
            //setDataToTable(ThuocController.timTatCaThuoc("", "T???t c???", -1, -1, -1, -1, 0), tblSanphamNhap);
        }

    }

    PhieunhapController pnCtrl = new PhieunhapController();
    ThuocController thuocCtrl = new ThuocController();
    LoaithuocController loaiThuocController = new LoaithuocController();
    NhacungcapController nccCtrl = new NhacungcapController();
    NhanvienController nvCtrl = new NhanvienController();
    LonhapController lnCtrl = new LonhapController();
    ArrayList<ChitietPhieunhap> dsctpnCSP = new ArrayList<>();
    ArrayList<ChitietPhieunhap> dsctpnPNH = new ArrayList<>();

    Nhanvien nhanvien;
    Nhacungcap nhacungcap;
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
        btnHuy = new javax.swing.JButton();
        btnNhaphang = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();

        lblHinhanhThuoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSanphamNhap.setAutoCreateRowSorter(true);
        tblSanphamNhap.setBackground(new java.awt.Color(204, 255, 255));
        tblSanphamNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tblSanphamNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? thu???c", "Lo???i thu???c", "T??n thu???c", "????n v??? t??nh", "????n v??? b??n l???", "T??? l??? quy ?????i", "S??? l?????ng c??n l???i"
            }
        ));
        tblSanphamNhap.setRowHeight(30);
        tblSanphamNhap.setSelectionBackground(new java.awt.Color(0, 0, 255));
        jScrollPane2.setViewportView(tblSanphamNhap);

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblPhieunhap.setAutoCreateRowSorter(true);
        tblPhieunhap.setBackground(new java.awt.Color(204, 255, 255));
        tblPhieunhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tblPhieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? thu???c", "T??n thu???c", "Ng??y s???n xu???t", "Ng??y h???t h???n", "S??? l?????ng", "????n gi??", "Th??nh ti???n"
            }
        ));
        tblPhieunhap.setRowHeight(30);
        tblPhieunhap.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jScrollPane1.setViewportView(tblPhieunhap);

        btnLammoi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLammoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnLammoi1.setText("L??m m???i");
        btnLammoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoi1ActionPerformed(evt);
            }
        });

        dateNSX.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ng??y s???n xu???t", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        dateNSX.setForeground(new java.awt.Color(255, 0, 0));
        dateNSX.setDateFormatString("dd-MM-yyyy");
        dateNSX.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateNSX.setMinimumSize(new java.awt.Dimension(56, 56));

        dateNHH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ng??y h???t h???n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        dateNHH.setForeground(new java.awt.Color(255, 0, 0));
        dateNHH.setDateFormatString("dd-MM-yyyy");
        dateNHH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dateNHH.setMinimumSize(new java.awt.Dimension(56, 56));

        txtLoaithuoc.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtLoaithuoc.setForeground(new java.awt.Color(255, 0, 0));
        txtLoaithuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lo???i thu???c", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnSoluongNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnSoluongNhap.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnSoluongNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "S??? l?????ng nh???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtNCC.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNCC.setForeground(new java.awt.Color(255, 0, 51));
        txtNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nh?? cung c???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        txtNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCCActionPerformed(evt);
            }
        });

        txtTongtien.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTongtien.setForeground(new java.awt.Color(255, 0, 0));
        txtTongtien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T???ng ti???n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        btnChonNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonNCC.setText("Ch???n");

        cbbDVT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "????n v??? t??nh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        cbbDVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDVTActionPerformed(evt);
            }
        });

        cbbDonviquydoi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "????n v??? quy ?????i", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnTilequydoi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnTilequydoi.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnTilequydoi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??? l??? quy ?????i", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        spnDongiaNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        spnDongiaNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "????n gi?? nh???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtMaphieunhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMaphieunhap.setForeground(new java.awt.Color(255, 0, 0));
        txtMaphieunhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "M?? phi???u nh???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtThoigiannhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtThoigiannhap.setForeground(new java.awt.Color(255, 0, 0));
        txtThoigiannhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th???i gian nh???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtNhanvienlap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNhanvienlap.setForeground(new java.awt.Color(255, 0, 0));
        txtNhanvienlap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nh??n vi??n nh???p", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtMathuoc.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMathuoc.setForeground(new java.awt.Color(255, 0, 0));
        txtMathuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "M?? thu???c", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        txtTenthuoc.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenthuoc.setForeground(new java.awt.Color(255, 0, 0));
        txtTenthuoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??n thu???c", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaithuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoigiannhap, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNhanvienlap, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonNCC))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbDonviquydoi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnTilequydoi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnDongiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateNHH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(txtLoaithuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dateNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateNHH, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spnDongiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnTilequydoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbDonviquydoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnLammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeLoginIcon.png"))); // NOI18N
        btnHuy.setText("H???y");

        btnNhaphang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhaphang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-categorize-30.png"))); // NOI18N
        btnNhaphang.setText("Nh???p thu???c");
        btnNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaphangActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("X??a");

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_database_restore_30px.png"))); // NOI18N
        btnSua.setText("S???a");

        btnLammoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        btnLammoi.setText("L??m m???i");

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
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(570, 570, 570)
                                        .addComponent(btnThem)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 34, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCCActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnNhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaphangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhaphangActionPerformed

    private void btnLammoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLammoi1ActionPerformed

    private void cbbDVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDVTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDVTActionPerformed


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
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
