package Components;

import Controllers.KhachhangController;
import Controllers.LoaiNhanvienController;
import Controllers.LoaithuocController;
import Controllers.NhacungcapController;
import Controllers.NhanvienController;
import Controllers.QuyenController;
import Controllers.TaikhoanController;
import Controllers.XaController;
import Models.Khachhang;
import Models.LoaiThuoc;
import Models.Nhacungcap;
import Models.Nhanvien;
import Models.Quyen;
import Models.Taikhoan;
import Views.TableLayout;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelImportFunction {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);
    XaController xaCtrl = new XaController();

    public ExcelImportFunction() {

    }

    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    // Đọc file excel quyền
    public void docFileExcelQuyen() {
        fd.setTitle("Nhập dữ liệu quyền từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String chitiet = cellIterator.next().getStringCellValue();
                    String daXoa = String.valueOf(cellIterator.next().getBooleanCellValue());

                    QuyenController quyenCtrl = new QuyenController();

                    Quyen qOld = QuyenController.getQuyen(ma);
                    if (qOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            TableLayout tbl = new TableLayout();
                            tbl.setColumnsWidth(new double[]{.5, .5, 1, 2, 4, 1});
                            tbl.setHeaders(new String[]{"", "STT", "Mã quyền", "Tên quyền", "Chi tiết quyền", "Trạng thái"});
                            tbl.addRow(new String[]{
                                "Cũ:", String.valueOf(stt),
                                qOld.getMaQuyen(),
                                qOld.getTenQuyen(),
                                qOld.getChitietQuyen(),
                                String.valueOf(qOld.isDaXoa()),});
                            tbl.addRow(new String[]{
                                "Mới:", String.valueOf(stt), ma, ten, chitiet, daXoa,});

                            MyJOptionPane mop = new MyJOptionPane(tbl, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            quyenCtrl.update(ma, ten, chitiet);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        Quyen q = new Quyen(ma, ten, chitiet);
                        quyenCtrl.add(q);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

    //Đọc file excel Tài khoản
    public void docFileExcelTaiKhoan() {
        fd.setTitle("Nhập dữ liệu tài khoản từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String taikhoan = cellIterator.next().getStringCellValue();
                    String matkhau = cellIterator.next().getStringCellValue();
                    int manv = Integer.parseInt(cellIterator.next().getStringCellValue().split(" - ")[0]);
                    String maquyen = cellIterator.next().getStringCellValue().split(" - ")[0];
                    String trangthai = String.valueOf(cellIterator.next().getBooleanCellValue());

                    TaikhoanController tkCtrl = new TaikhoanController();
                    Taikhoan tkOld = tkCtrl.getTaiKhoan(taikhoan);

                    if (tkOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            TableLayout tbl = new TableLayout();
                            tbl.setColumnsWidth(new double[]{.5, .5, 2, 4, 1, 1, 1});
                            tbl.setHeaders(new String[]{"", "STT", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền", "Trạng thái"});
                            tbl.addRow(new String[]{
                                "Cũ:", String.valueOf(stt), tkOld.getTaikhoan(),
                                tkOld.getMatkhau(),
                                String.valueOf(tkOld.getNv().getMaNhanvien()),
                                tkOld.getQ().getMaQuyen(),
                                String.valueOf(tkOld.isDaXoa())});
                            tbl.addRow(new String[]{
                                "Mới:", String.valueOf(stt), taikhoan, matkhau, String.valueOf(manv), maquyen, trangthai
                            });

                            MyJOptionPane mop = new MyJOptionPane(tbl, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {

                            Taikhoan tk = new Taikhoan(taikhoan, matkhau, NhanvienController.getNhanVien(manv), QuyenController.getQuyen(maquyen), Boolean.parseBoolean(trangthai));
                            tkCtrl.capnhatTaiKhoan(tk);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        Taikhoan tk = new Taikhoan(taikhoan, matkhau, NhanvienController.getNhanVien(manv), QuyenController.getQuyen(maquyen), Boolean.parseBoolean(trangthai));
                        tkCtrl.themTaiKhoan(tk);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    //Đọc file excel Khách hàng

    public void docFileExcelKhachhang() {
        fd.setTitle("Nhập dữ liệu khách hàng từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    int stt = (int) cellIterator.next().getNumericCellValue();
                    int makh = (int) cellIterator.next().getNumericCellValue();
                    String tenkh = cellIterator.next().getStringCellValue();
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String ngaySinh = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    int diachikh = Integer.parseInt(cellIterator.next().getStringCellValue().split(" - ")[0]);
                    String khachquen = String.valueOf(cellIterator.next().getBooleanCellValue());
                    String trangthai = String.valueOf(cellIterator.next().getBooleanCellValue());

                    KhachhangController khCtrl = new KhachhangController();
                    Khachhang khOLD = KhachhangController.layKhachHang(makh);

                    if (khOLD != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            TableLayout tbl = new TableLayout();
                            tbl.setColumnsWidth(new double[]{.5, .5, 1, 2, 1, 2, 2, 3, 1, 1});
                            tbl.setHeaders(new String[]{"", "STT", "Mã KH", "Tên KH", "Giới tính", "Ngày sinh", "SĐT", "Địa chỉ", "Khách quen", "Trạng thái"});
                            tbl.addRow(new String[]{
                                "Cũ:", String.valueOf(stt),
                                String.valueOf(khOLD.getMaKhachhang()),
                                khOLD.getTenKhachhang(),
                                khOLD.getGioitinh(),
                                khOLD.getNgaySinh().toString(),
                                khOLD.getSoDienthoai(),
                                khOLD.getDiaChi().split(" - ")[0],
                                String.valueOf(khOLD.isKhachQuen()),
                                String.valueOf(khOLD.isDaXoa()),});
                            tbl.addRow(new String[]{
                                "Mới:", String.valueOf(stt), String.valueOf(makh), tenkh, gioitinh, ngaySinh, sdt, xaCtrl.getXa(diachikh).getTenXa(), khachquen, trangthai
                            });

                            MyJOptionPane mop = new MyJOptionPane(tbl, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate dateNS = LocalDate.parse(ngaySinh, formatter);
                            Khachhang kh = new Khachhang(makh, tenkh, gioitinh, sdt, dateNS, xaCtrl.getXa(diachikh), Boolean.parseBoolean(khachquen), Boolean.parseBoolean(trangthai));
                            khCtrl.suaKhachhang(kh);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dateNS = LocalDate.parse(ngaySinh, formatter);
                        Khachhang kh = new Khachhang(makh, tenkh, gioitinh, sdt, dateNS, xaCtrl.getXa(diachikh), Boolean.parseBoolean(khachquen), Boolean.parseBoolean(trangthai));
                        khCtrl.themKhachHang(kh);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

    //Đọc file excel Nhân viên
    public void docFileExcelNhanvien() {
        fd.setTitle("Nhập dữ liệu nhân viên từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    int ma = (int) cellIterator.next().getNumericCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    int maloainv = (int) cellIterator.next().getNumericCellValue();
                    LocalDate ngaysinh = LocalDate.parse(cellIterator.next().getStringCellValue());
                    int diachi = (int) cellIterator.next().getNumericCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String bangcap = cellIterator.next().getStringCellValue();
                    String luong = cellIterator.next().getStringCellValue();
                    String trangthai = String.valueOf(cellIterator.next().getBooleanCellValue());

                    NhanvienController nvCtrl = new NhanvienController();
                    Nhanvien nvOld = NhanvienController.getNhanVien(ma);

                    if (nvOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            TableLayout tbl = new TableLayout();
                            tbl.setColumnsWidth(new double[]{.5, .5, 1.5, 1, 1, 2, 2, 1, 1, 2, 1});
                            tbl.setHeaders(new String[]{"", "Mã NV", "Tên NV", "Mã loại NV", "Ngày sinh", "Địa chỉ", "SDT", "Giới tính", "Bằng cấp", "Lương", "Trạng thái"});
                            tbl.addRow(new String[]{
                                "Cũ:", String.valueOf(stt),
                                String.valueOf(nvOld.getMaNhanvien()),
                                nvOld.getTenNhanvien(),
                                String.valueOf(nvOld.getLoaiNhanvien()),
                                String.valueOf(nvOld.getNgaySinh()),
                                nvOld.getXa().getDiaChi(),
                                nvOld.getSoDienThoai(),
                                nvOld.getGioiTinh(),
                                nvOld.getBangCap(),
                                String.valueOf(nvOld.getLuong()),
                                String.valueOf(nvOld.isDaXoa()),});
                            tbl.addRow(new String[]{
                                "Mới:", String.valueOf(stt), String.valueOf(ma), ten, String.valueOf(maloainv), String.valueOf(ngaysinh), String.valueOf(diachi), sdt, gioitinh, bangcap, luong, trangthai
                            });

                            MyJOptionPane mop = new MyJOptionPane(tbl, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            Nhanvien nv = new Nhanvien(ma, ten, ngaysinh, sdt, gioitinh, bangcap, NhanvienController.getNhanVien(ma).getLoaiNhanvien(), xaCtrl.getXa(diachi), Long.parseLong(luong), Boolean.parseBoolean(trangthai));
                            NhanvienController.suaNhanvien(nv);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        Nhanvien nv = new Nhanvien(ma, ten, ngaysinh, sdt, gioitinh, bangcap, NhanvienController.getNhanVien(ma).getLoaiNhanvien(), xaCtrl.getXa(diachi), Long.parseLong(luong), Boolean.parseBoolean(trangthai));
                        NhanvienController.themNhanvien(nv);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

    //Đọc file excel Loại thuốc
    public void docFileExcelLoaiThuoc() {
        fd.setTitle("Nhập dữ liệu loại thuốc từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    int maloaithuoc = (int) cellIterator.next().getNumericCellValue();
                    String tenloaithuoc = cellIterator.next().getStringCellValue();
                    String mota = cellIterator.next().getStringCellValue();
                    String daxoa = String.valueOf(cellIterator.next().getStringCellValue());

                    LoaithuocController ltCtrl = new LoaithuocController();
                    LoaiThuoc tlOld = ltCtrl.getLoaiThuoc(maloaithuoc);

                    if (tlOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            TableLayout tbl = new TableLayout();
                            tbl.setColumnsWidth(new double[]{.5, .5, 5, 3, 1});
                            tbl.setHeaders(new String[]{"", "Mã loại thuốc", "Tên loại thuốc", "Mô tả", "Đã xóa"});
                            tbl.addRow(new String[]{
                                "Cũ:",
                                String.valueOf(tlOld.getMaLoaiThuoc()),
                                tlOld.getTenLoaiThuoc(),
                                tlOld.getMota(),
                                String.valueOf(tlOld.isDaXoa()),});
                            tbl.addRow(new String[]{
                                "Mới:", String.valueOf(maloaithuoc), tenloaithuoc, mota, daxoa
                            });

                            MyJOptionPane mop = new MyJOptionPane(tbl, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            LoaiThuoc lt = new LoaiThuoc(maloaithuoc, tenloaithuoc, mota, Boolean.parseBoolean(daxoa));
                            LoaithuocController.capnhatLoaiThuoc(lt, maloaithuoc);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        LoaiThuoc lt = new LoaiThuoc(maloaithuoc, tenloaithuoc, mota, Boolean.parseBoolean(daxoa));
                        LoaithuocController.themLoaiThuoc(lt);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

}
