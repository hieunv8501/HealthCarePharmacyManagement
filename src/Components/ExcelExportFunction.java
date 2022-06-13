package Components;

import Controllers.HoadonController;
import Controllers.KhachhangController;
import Controllers.KhuyenmaiController;
import Controllers.LoaithuocController;
import Controllers.NhacungcapController;
import Controllers.NhanvienController;
import Controllers.PhieunhapController;
import Controllers.QuyenController;
import Models.Quyen;
import Controllers.TaikhoanController;
import Controllers.ThuocController;
import Models.ChitietHoadon;
import Models.ChitietPhieunhap;
import Models.Hoadon;
import Models.Khachhang;
import Models.Khuyenmai;
import Models.LoaiThuoc;
import Models.Nhacungcap;
import Models.Nhanvien;
import Models.Phieunhap;
import Models.Taikhoan;
import Models.Thuoc;
import Views.TableLayout;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;

public class ExcelExportFunction {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private String getFile(String fileName) {
        fd.setFile(fileName);
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    //Style for header
    private CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        //Sử dụng phương thức getWorkbook
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        return cellStyle;
    }

    // Xuất file Excel Nhà cung cấp    
    public void xuatFileExcelNhacungcap() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile("Nhacungcap.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");
            CellStyle cellStyle = createStyleForHeader(sheet);

            ArrayList<Nhacungcap> list = NhacungcapController.getDanhSachNhacungcap();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Mã nhà cung cấp");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên nhà cung cấp");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue("Mã xã (Địa chỉ)");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Fax");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Nhacungcap ncc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.NUMERIC).setCellValue(ncc.getMaNhacungcap());
                row.createCell(2, CellType.STRING).setCellValue(ncc.getTenNhacungcap());
                row.createCell(3, CellType.NUMERIC).setCellValue(ncc.getXa().getMaXa());
                row.createCell(4, CellType.STRING).setCellValue(ncc.getSoDienthoai());
                row.createCell(5, CellType.STRING).setCellValue(ncc.getFax());
                row.createCell(6, CellType.BOOLEAN).setCellValue(ncc.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Nhân viên
    public void xuatFileExcelNhanvien() {
        fd.setTitle("Xuất dữ liệu nhân viên ra excel");
        String url = getFile("Nhanvien.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhân viên");
            CellStyle cellStyle = createStyleForHeader(sheet);

            ArrayList<Nhanvien> list = NhanvienController.getDanhSachNhanvien();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên nhân viên");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue("Mã loại nhân viên");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue("Mã xã (Địa chỉ)");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Bằng cấp");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue("Lương");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(10, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Nhanvien nv : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.NUMERIC).setCellValue(nv.getMaNhanvien());
                row.createCell(2, CellType.STRING).setCellValue(nv.getTenNhanvien());
                row.createCell(3, CellType.NUMERIC).setCellValue(nv.getLoaiNhanvien());
                row.createCell(4, CellType.STRING).setCellValue(String.valueOf(nv.getNgaySinh()));
                row.createCell(5, CellType.NUMERIC).setCellValue(nv.getXa().getMaXa());
                row.createCell(6, CellType.STRING).setCellValue(nv.getSoDienThoai());
                row.createCell(7, CellType.STRING).setCellValue(nv.getGioiTinh());
                row.createCell(8, CellType.STRING).setCellValue(nv.getBangCap());
                row.createCell(9, CellType.STRING).setCellValue(nv.getLuong());
                row.createCell(10, CellType.BOOLEAN).setCellValue(nv.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Khách hàng
    public void xuatFileExcelKhachhang() {
        fd.setTitle("Xuất dữ liệu khách hàng ra excel");
        String url = getFile("Khachhang.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khách hàng");
            CellStyle cellStyle = createStyleForHeader(sheet);

            ArrayList<Khachhang> list = KhachhangController.layDSKhachHang();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("Mã khách hàng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên khách hàng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("Mã xã (Địa chỉ)");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.BOOLEAN);
            cell.setCellValue("Khách quen");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(0, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Khachhang kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.NUMERIC).setCellValue(kh.getMaKhachhang());
                row.createCell(2, CellType.STRING).setCellValue(kh.getTenKhachhang());
                row.createCell(3, CellType.STRING).setCellValue(kh.getGioitinh());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatted = format1.format(kh.getNgaySinh());
                row.createCell(4, CellType.STRING).setCellValue(formatted);
                row.createCell(5, CellType.STRING).setCellValue((kh.getSoDienthoai()));
                row.createCell(6, CellType.NUMERIC).setCellValue((kh.getXa()));
                row.createCell(7, CellType.BOOLEAN).setCellValue((kh.isKhachQuen()));
                row.createCell(8, CellType.BOOLEAN).setCellValue((kh.isDaXoa()));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Tài khoản
    public void xuatFileExcelTaikhoan() {
        fd.setTitle("Xuất dữ liệu tài khoản ra excel");
        String url = getFile("Taikhoan.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài khoản");
            CellStyle cellStyle = createStyleForHeader(sheet);
            ArrayList<Taikhoan> list = TaikhoanController.getDanhSachTaiKhoan();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên tài khoản");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mật khẩu");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã quyền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Taikhoan tk : list) {
                rownum++;
                row = sheet.createRow(rownum);

                int manv = tk.getNv().getMaNhanvien();
                String maq = tk.getQ().getMaQuyen();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(tk.getTaikhoan());
                row.createCell(2, CellType.STRING).setCellValue(tk.getMatkhau());
                row.createCell(3, CellType.STRING).setCellValue(manv + " - " + NhanvienController.getNhanVien(manv).getTenNhanvien());
                row.createCell(4, CellType.STRING).setCellValue(maq + " - " + QuyenController.getQuyen(maq).getTenQuyen());
                row.createCell(5, CellType.BOOLEAN).setCellValue(tk.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Khuyến mãi
    public void xuatFileExcelKhuyenmai() {
        fd.setTitle("Xuất dữ liệu khuyến mãi ra excel");
        String url = getFile("Khuyenmai.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khuyến mãi");
            CellStyle cellStyle = createStyleForHeader(sheet);
            ArrayList<Khuyenmai> list = KhuyenmaiController.layDanhsachMKMAll();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khuyến mãi");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên khuyến mãi");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue("Điều kiện");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue("Phần trăm");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày bắt đầu");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày kết thúc");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Khuyenmai km : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(km.getMaKhuyenmai());
                row.createCell(2, CellType.STRING).setCellValue(km.getTenKhuyenmai());
                row.createCell(3, CellType.NUMERIC).setCellValue(km.getDieukienKhuyenmai());
                row.createCell(4, CellType.NUMERIC).setCellValue(km.getPhantramKhuyenmai());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String formatted1 = format1.format(km.getNgayBatdau());
                String formatted2 = format1.format(km.getNgayBatdau());
                row.createCell(5, CellType.STRING).setCellValue(formatted1);
                row.createCell(6, CellType.STRING).setCellValue(formatted2);
                row.createCell(7, CellType.BOOLEAN).setCellValue(km.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Thuốc
    public void xuatFileExcelThuoc() {
        fd.setTitle("Xuất dữ liệu thuốc ra excel");
        String url = getFile("Thuoc.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Thuốc");
            CellStyle cellStyle = createStyleForHeader(sheet);
            LoaithuocController loaithuocCtrl = new LoaithuocController();
            ArrayList<Thuoc> list = ThuocController.getDanhSachThuoc();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tên thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mô tả");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Độ tuổi");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Hình ảnh");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Đơn vị tính");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Đơn vị qui đổi");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Tỉ lệ quy đổi");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Nhà cung cấp");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Loại thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Thuoc thuoc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                int maloai = thuoc.getLoaiThuoc().getMaLoaiThuoc();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.NUMERIC).setCellValue(thuoc.getMaThuoc());
                row.createCell(2, CellType.STRING).setCellValue(thuoc.getTenThuoc());
                row.createCell(3, CellType.STRING).setCellValue(thuoc.getMota());
                row.createCell(4, CellType.STRING).setCellValue(thuoc.getDotuoi());
                row.createCell(5, CellType.STRING).setCellValue(thuoc.getHinhanh());
                row.createCell(6, CellType.STRING).setCellValue(thuoc.getDonvitinh().getMaDonvitinh() + " - " + thuoc.getDonvitinh().getTenDonvitinh());
                row.createCell(7, CellType.STRING).setCellValue(thuoc.getDonviQuydoi().getMaDonvitinh() + " - " + thuoc.getDonviQuydoi().getTenDonvitinh());
                row.createCell(8, CellType.NUMERIC).setCellValue(thuoc.getTileQuydoi());
                row.createCell(9, CellType.STRING).setCellValue(thuoc.getNhacungcap().getMaNhacungcap() + " - " + thuoc.getNhacungcap().getTenNhacungcap());
                row.createCell(10, CellType.STRING).setCellValue(maloai + " - " + loaithuocCtrl.getLoaiThuoc(maloai).getTenLoaiThuoc());
                row.createCell(11, CellType.NUMERIC).setCellValue(thuoc.getGiaBan());
                row.createCell(11, CellType.BOOLEAN).setCellValue(thuoc.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Loại thuốc
    public void xuatFileExcelLoaithuoc() {
        fd.setTitle("Xuất dữ liệu loại thuốc ra excel");
        String url = getFile("Loaithuoc.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loại thuốc");
            CellStyle cellStyle = createStyleForHeader(sheet);
            ArrayList<LoaiThuoc> list = LoaithuocController.getDanhSachLoaiThuoc();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Mã loại thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên loại thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mô tả");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (LoaiThuoc lt : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(rownum);
                cell.setCellStyle(cellStyle);
                row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(lt.getMaLoaiThuoc());
                cell.setCellStyle(cellStyle);
                row.createCell(2, CellType.STRING);
                cell.setCellValue(lt.getTenLoaiThuoc());
                cell.setCellStyle(cellStyle);
                row.createCell(3, CellType.STRING);
                cell.setCellValue(lt.getMota());
                cell.setCellStyle(cellStyle);
                row.createCell(4, CellType.STRING);
                cell.setCellValue(lt.isDaXoa());
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Quyền
    public void xuatFileExcelQuyen() {
        fd.setTitle("Xuất dữ liệu quyền ra excel");
        String url = getFile("Quyen.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quyền");
            CellStyle cellStyle = createStyleForHeader(sheet);
            ArrayList<Quyen> list = QuyenController.getDanhSachQuyen();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã quyền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên quyền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Chi tiết quyền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            for (Quyen q : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(q.getMaQuyen());
                row.createCell(2, CellType.STRING).setCellValue(q.getTenQuyen());
                row.createCell(3, CellType.STRING).setCellValue(q.getChitietQuyen());
                row.createCell(4, CellType.BOOLEAN).setCellValue(q.isDaXoa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Hóa đơn
    public void xuatFileExcelHoadon() {
        fd.setTitle("Xuất dữ liệu hóa đơn ra excel");
        String url = getFile("Hoadon.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");
            CellStyle cellStyle = createStyleForHeader(sheet);

            HoadonController hdCtrl = new HoadonController();
            ArrayList<Hoadon> list = hdCtrl.layDanhsachHD();

            int rownum = 0;
            int sttHoaDon = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Mã hóa đơn");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue("Mã khách hàng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã khuyến mãi");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày lập");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue("Tổng tiền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Trạng thái");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue("Số lượng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10, CellType.NUMERIC);
            cell.setCellValue("Đơn giá");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(11, CellType.NUMERIC);
            cell.setCellValue("Thành tiền");
            cell.setCellStyle(cellStyle);

            for (Hoadon hd : list) {
                rownum++;
                sttHoaDon++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
                row.createCell(1, CellType.STRING).setCellValue(hd.getMaHoadon());
                row.createCell(2, CellType.STRING).setCellValue(hd.getMaNhanvien() + " - " + NhanvienController.getNhanVien(hd.getMaNhanvien()).getTenNhanvien());
                row.createCell(3, CellType.STRING).setCellValue(hd.getMaKhachhang() + " - " + KhachhangController.layKhachHang(hd.getMaKhachhang()).getTenKhachhang());
                row.createCell(4, CellType.STRING).setCellValue(hd.getMaKhuyenmai() + " - " + KhuyenmaiController.layMaKhuyenmai(hd.getMaKhuyenmai()).getTenKhuyenmai());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatted = format1.format(hd.getNgayLap().getTime());
                row.createCell(5, CellType.STRING).setCellValue(formatted);
                row.createCell(6, CellType.NUMERIC).setCellValue(hd.getTongTien());
                row.createCell(7, CellType.BOOLEAN).setCellValue(hd.isDaXoa());

                for (ChitietHoadon cthd : hdCtrl.layDanhsachCTHD(hd.getMaHoadon())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    int mathuoc = cthd.getMaThuoc();

                    row.createCell(8, CellType.STRING).setCellValue(mathuoc + " - " + ThuocController.getThuoc(mathuoc).getTenThuoc());
                    row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getSoluong());
                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getDongia());
                    row.createCell(11, CellType.NUMERIC).setCellValue(cthd.getDongia() * cthd.getSoluong());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Phiếu nhập
    public void xuatFileExcelPhieunhap() {
        fd.setTitle("Xuất dữ liệu phiếu nhập ra excel");
        String url = getFile("Phieunhap.xls");
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");
            CellStyle cellStyle = createStyleForHeader(sheet);
            PhieunhapController pnCtrl = new PhieunhapController();
            ArrayList<Phieunhap> list = PhieunhapController.layDanhsachPhieuNhapAll();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            Cell cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Mã hóa đơn");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã nhà cung cấp");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày lập");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue("Tổng tiền");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6, CellType.BOOLEAN);
            cell.setCellValue("Đã xóa");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Loại thuốc");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Đơn vị tính");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10, CellType.NUMERIC);
            cell.setCellValue("Số lượng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(11, CellType.NUMERIC);
            cell.setCellValue("Đơn giá");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(12, CellType.NUMERIC);
            cell.setCellValue("Thành tiền");
            cell.setCellStyle(cellStyle);

            for (Phieunhap pn : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(pn.getMaPhieunhap());
                row.createCell(2, CellType.STRING).setCellValue(pn.getNcc().getMaNhacungcap() + " - " + pn.getNcc().getTenNhacungcap());
                row.createCell(3, CellType.STRING).setCellValue(pn.getNv().getMaNhanvien() + " - " + pn.getNv().getTenNhanvien());

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String formatted = format1.format(pn.getNgayNhap().getTime());

                row.createCell(4, CellType.STRING).setCellValue(formatted);
                row.createCell(5, CellType.NUMERIC).setCellValue(pn.getTongTien());
                row.createCell(6, CellType.BOOLEAN).setCellValue(pn.isDaXoa());

                for (ChitietPhieunhap ctpn : pnCtrl.layDanhsachChitietPhieunhapTheoMaPhieuNhap(pn.getMaPhieunhap())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    int mathuoc = ctpn.getMaThuoc();
                    var thuoc = ThuocController.getThuoc(mathuoc);
                    var dvt = ThuocController.getThuoc(mathuoc);

                    row.createCell(7, CellType.STRING).setCellValue(mathuoc + " - " + thuoc.getTenThuoc());
                    row.createCell(8, CellType.STRING).setCellValue(thuoc.getLoaiThuoc().getMaLoaiThuoc() + " - " + thuoc.getLoaiThuoc().getTenLoaiThuoc());
                    row.createCell(9, CellType.STRING).setCellValue(dvt.getDonviQuydoi().getTenDonvitinh() + " " + dvt.getTileQuydoi() + " " + dvt.getDonvitinh().getTenDonvitinh());
                    row.createCell(10, CellType.NUMERIC).setCellValue(ctpn.getSoluong());
                    row.createCell(11, CellType.NUMERIC).setCellValue(ctpn.getDongia());
                    row.createCell(12, CellType.NUMERIC).setCellValue(ctpn.getDongia() * ctpn.getSoluong());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelExportFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
