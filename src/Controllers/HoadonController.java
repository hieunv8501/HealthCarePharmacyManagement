package Controllers;

import java.util.Calendar;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.Hoadon;
import Models.Nhanvien;
import Models.Khachhang;
import Models.Thuoc;
import Models.LoNhap;
import Models.Donvitinh;

import Models.ChitietHoadon;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HoadonController {

    public Khachhang layThongKhachhang(int maHoaDon) {

        DBConnection con = new DBConnection();
        try {
            CallableStatement call = con.getConn().prepareCall("{call sp_ThongTinHoaDon(?)}");
            call.setInt(1, maHoaDon);
            ResultSet rs = call.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String gioiTinh = rs.getString("GioiTinh");
                    String soDienThoai = rs.getString("SoDienThoai");
                    int maXa = rs.getInt("MaXa");

                    LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();

                    Khachhang kh = new Khachhang();
                    kh.setNgaySinh(ngaySinh);
                    kh.setGioitinh(gioiTinh);
                    kh.setSoDienthoai(soDienThoai);
                    DiaChiController diachi = new DiaChiController();
                    kh.setXa(diachi.layDoiTuongXa(maXa));
                    return kh;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public Hoadon layThongTinHoaDon(int maHoaDon) {

        DBConnection con = new DBConnection();
        try {
            CallableStatement call = con.getConn().prepareCall("{call sp_ThongTinHoaDon(?)}");
            call.setInt(1, maHoaDon);
            ResultSet rs = call.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String tenNhanVien = rs.getString("TenNhanVien");
                    String tenKhachHang = rs.getString("TenKhachHang");
                    float tongTien = rs.getFloat("TongTien");
                    float tienChuaGiam = rs.getFloat("TienChuaGiam");

                    String date = rs.getString("NgayLap");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    cal.setTime(sdf.parse(date));

                    Hoadon hd = new Hoadon();
                    hd.setTenNhanvien(tenNhanVien);
                    hd.setTenKhachhang(tenKhachHang);
                    hd.setTongTien(tongTien);
                    hd.setTongTienChuaGiam(tienChuaGiam);
                    hd.setNgayLap(cal);
                    return hd;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public Donvitinh layDonvitinh(int maThuoc) {

        String query = "SELECT * FROM donvitinh, thuoc Where thuoc.DaXoa = 0 AND MaThuoc = '" + maThuoc + "' AND thuoc.MaDonViTinh = donvitinh.MaDonViTinh";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    Donvitinh dvt = new Donvitinh();
                    dvt.setTenDonvitinh(tenDonvitinh);
                    return dvt;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public Thuoc layGiathuoc(int maThuoc) {

        String query = "SELECT * FROM thuoc Where DaXoa = 0 AND MaThuoc = '" + maThuoc + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    float giaBan = rs.getFloat("GiaBan");
                    Thuoc dvt = new Thuoc();
                    dvt.setGiaBan(giaBan);
                    return dvt;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public ArrayList<Thuoc> layDanhSachThuoc() {
        ArrayList<Thuoc> dsth = new ArrayList<>();
        String query = "SELECT * FROM thuoc WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    Thuoc thuoc = new Thuoc();
                    thuoc.setMaThuoc(maThuoc);
                    thuoc.setTenThuoc(tenThuoc);
                    dsth.add(thuoc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsth;
    }

    public ArrayList<LoNhap> layDanhSachLonhap(int soLuong, int maThuoc) {
        ArrayList<LoNhap> dsln = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT * FROM lonhap, chitietphieunhap WHERE lonhap.DaXoa = 0 AND chitietphieunhap.MaThuoc = lonhap.MaThuoc AND chitietphieunhap.MaPhieuNhap = lonhap.MaPhieuNhap AND NgayHetHan >= '" + formatter.format(date) + "' AND lonhap.MaThuoc = " + maThuoc + " AND SoLuongConLai >= " + soLuong;
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maLo = rs.getInt("MaLo");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    LoNhap lonhap = new LoNhap();
                    lonhap.setMaLo(maLo);
                    lonhap.setSoluongConlai(soluongConlai);
                    dsln.add(lonhap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsln;
    }

    public int getMaxMHD() {
        String query = "SELECT MAX(MaHoaDon) AS MaxMHD FROM hoadon";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int max = rs.getInt("MaxMHD");
                    return max + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return 0;
    }

    public ArrayList<Hoadon> layDanhsachHD() {
        ArrayList<Hoadon> dshd = new ArrayList<>();
        String query = "SELECT * FROM hoadon, nhanvien, khachhang WHERE hoadon.DaXoa = 0 AND nhanvien.MaNhanVien = hoadon.MaNhanVien AND hoadon.MaKhachHang = khachhang.MaKhachHang";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maHoadon = rs.getInt("MaHoaDon");
                    int maNhanvien = rs.getInt("MaNhanVien");
                    int maKhachhang = rs.getInt("MaKhachHang");
                    String tenNhanvien = rs.getString("TenNhanVien");
                    String tenKhachhang = rs.getString("TenKhachHang");
                    String maKhuyenmai = rs.getString("MaKhuyenMai");

                    String date = rs.getString("NgayLap");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    cal.setTime(sdf.parse(date));
//                    LocalDate ngayLap = rs.getDate("NgayLap").toLocalDate();

                    float tongTien = rs.getFloat("TongTien");
                    dshd.add(new Hoadon(maHoadon, maNhanvien, tenNhanvien, maKhachhang, tenKhachhang, maKhuyenmai, cal, tongTien, false));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dshd;
    }

    public ArrayList<ChitietHoadon> layDanhsachCTHD(int maHoadon) {
        ArrayList<ChitietHoadon> dscthd = new ArrayList<>();
        String query = "SELECT * FROM chitiethoadon, thuoc, donvitinh WHERE chitiethoadon.DaXoa = 0 AND thuoc.MaThuoc = chitiethoadon.MaThuoc AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND MaHoaDon = '" + maHoadon + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maThuoc = rs.getInt("MaThuoc");
                    int maLo = rs.getInt("MaLo");
                    int soLuong = rs.getInt("SoLuong");
                    float donGia = rs.getFloat("DonGia");
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenDonvitinh = rs.getString("TenDonViTinh");

                    ChitietHoadon cthd = new ChitietHoadon(maHoadon, maThuoc, maLo, soLuong, donGia, false);
                    cthd.setTenThuoc(tenThuoc);
                    cthd.setTenDonvitinh(tenDonvitinh);
                    dscthd.add(cthd);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dscthd;
    }

    public ArrayList<Nhanvien> layDanhSachMNV() {
        ArrayList<Nhanvien> dsnv = new ArrayList<>();
        String query = "SELECT * FROM nhanvien WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maNhanvien = rs.getInt("MaNhanVien");
                    String tenNhanVien = rs.getString("TenNhanVien");

                    Nhanvien nhanvien = new Nhanvien();
                    nhanvien.setMaNhanvien(maNhanvien);
                    nhanvien.setTenNhanvien(tenNhanVien);

                    dsnv.add(nhanvien);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsnv;
    }

    public ArrayList<Khachhang> layDanhSachMKH() {
        ArrayList<Khachhang> dskh = new ArrayList<>();
        String query = "SELECT * FROM khachhang WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maKhachhang = rs.getInt("MaKhachHang");
                    String tenKhachhang = rs.getString("TenKhachHang");

                    Khachhang khachhang = new Khachhang();
                    khachhang.setMaKhachhang(maKhachhang);
                    khachhang.setTenKhachhang(tenKhachhang);

                    dskh.add(khachhang);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dskh;
    }

    public void themHoaDon(Hoadon HD) {
        Calendar cal1 = HD.getNgayLap();

        String dateFormat;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat = fm.format(cal1.getTime());

        String command = "INSERT INTO hoadon(MaNhanVien, MaKhachHang, MaKhuyenMai, NgayLap) VALUES (?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, HD.getMaNhanvien());
            pre.setInt(2, HD.getMaKhachhang());
            pre.setString(3, HD.getMaKhuyenmai());
            pre.setString(4, dateFormat);
            pre.executeUpdate();
            System.out.println("Th??m h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themCTHD(ChitietHoadon CTHD) {
        String command = "INSERT INTO chitiethoadon(MaHoaDon, MaThuoc, MaLo, SoLuong, DonGia) VALUES (?,?,?,?,?)";

        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            System.out.println(CTHD.getMaThuoc());
            pre.setInt(1, CTHD.getMaHoadon());
            pre.setInt(2, CTHD.getMaThuoc());
            pre.setInt(3, CTHD.getMaLo());
            pre.setInt(4, CTHD.getSoluong());
            pre.setFloat(5, CTHD.getDongia());

            pre.executeUpdate();
            System.out.println("Th??m chi ti???t h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatHoadon(Hoadon HD) {
        Calendar cal1 = HD.getNgayLap();

        String dateFormat;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat = fm.format(cal1.getTime());
        String command = "UPDATE hoadon SET MaNhanVien = ?, MaKhachHang = ?, MaKhuyenMai = ?, NgayLap = ? WHERE MaHoaDon = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, HD.getMaNhanvien());
            pre.setInt(2, HD.getMaKhachhang());
            pre.setString(3, HD.getMaKhuyenmai());
            pre.setString(4, dateFormat);
            pre.setInt(5, HD.getMaHoadon());

            pre.executeUpdate();
            System.out.println("C???p nh???t h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatCTHD(ChitietHoadon CTHD) {
        String command = "UPDATE chitiethoadon SET SoLuong = ?, DonGia = ? WHERE MaHoaDon = ? AND MaThuoc = ? AND MaLo = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, CTHD.getSoluong());
            pre.setFloat(2, CTHD.getDongia());
            pre.setInt(3, CTHD.getMaHoadon());
            pre.setInt(4, CTHD.getMaThuoc());
            pre.setInt(5, CTHD.getMaLo());

            pre.executeUpdate();
            System.out.println("C???p nh???t h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaHoaDon(int maHoadon) {
        String command = "UPDATE hoadon SET DaXoa = 1 WHERE MaHoaDon = " + maHoadon;
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.executeUpdate();
            System.out.println("X??a h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaCTHD(ChitietHoadon CTHD) {
        String command = "UPDATE ChitietHoadon SET DaXoa = 1 WHERE MaHoaDon = ? AND MaThuoc = ? AND MaLo = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, CTHD.getMaHoadon());
            pre.setInt(2, CTHD.getMaThuoc());
            pre.setInt(3, CTHD.getMaLo());
            pre.executeUpdate();
            System.out.println("X??a h??a ????n th??nh c??ng");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
