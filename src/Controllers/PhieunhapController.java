package Controllers;

import Models.Phieunhap;
import Models.Nhanvien;
import Models.Thuoc;
import Models.LoNhap;
import Models.Donvitinh;
import Models.ChitietPhieunhap;

import java.util.Calendar;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PhieunhapController {

    public Donvitinh layDonvitinh(int maThuoc) {
        String sqlCommand = "SELECT * FROM donvitinh, thuoc Where thuoc.DaXoa = 0 AND MaThuoc = '" + maThuoc + "' AND thuoc.MaDonViTinh = donvitinh.MaDonViTinh";
        DBConnection conn = new DBConnection();
        try {
            ResultSet rs = conn.sqlQuery(sqlCommand);
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
            conn.closeConnection();
        }
        return null;
    }

    public ArrayList<Thuoc> layDanhSachThuoc() {
        ArrayList<Thuoc> dsth = new ArrayList<>();
        String sqlCommand = "SELECT * FROM thuoc WHERE DaXoa = 0";
        DBConnection conn = new DBConnection();
        try {
            ResultSet rs = conn.sqlQuery(sqlCommand);
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
            conn.closeConnection();
        }
        return dsth;
    }

    public void themPhieuNhap(Phieunhap pn) {
        Calendar cal1 = pn.getNgaynhap();

        String ngayNhap;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ngayNhap = fm.format(cal1.getTime());

        String sqlCommand = "INSERT INTO phieunhap(MaPhieuNhap, MaNhaCungCap, MaNhanVien, NgayNhap) VALUES (?,?,?,?)";
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, pn.getMaPhieunhap());
            pre.setInt(2, pn.getMaNhacungcap());
            pre.setInt(3, pn.getMaNhanvien());
            pre.setString(4, ngayNhap);
            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themChitietPhieunhap(ChitietPhieunhap ctpn) {
        String sqlCommand = "INSERT INTO chitietphieunhap(MaPhieuNhap, MaThuoc, NgaySanXuat, NgayHetHan, SoLuong, DonGia) VALUES (?,?,?,?,?)";

        Calendar cal1 = ctpn.getNgaySanxuat();
        Calendar cal2 = ctpn.getNgayHethan();

        String nsx, nhh;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nsx = fm.format(cal1.getTime());
        nhh = fm.format(cal2.getTime());
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, ctpn.getMaPhieunhap());
            pre.setInt(2, ctpn.getMaThuoc());
            pre.setString(3, nsx);
            pre.setString(4, nhh);
            pre.setInt(5, ctpn.getSoluong());
            pre.setFloat(6, ctpn.getDongia());

            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatPhieunhap(Phieunhap pn) {
        Calendar cal1 = pn.getNgaynhap();

        String ngayNhap;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ngayNhap = fm.format(cal1.getTime());
        String sqlCommand = "UPDATE phieunhap SET MaNhaCungCap = ?, MaNhanVien = ?, NgayNhap = ? WHERE MaPhieuNhap = ?";
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, pn.getMaNhacungcap());
            pre.setInt(2, pn.getMaNhanvien());
            pre.setString(3, ngayNhap);
            pre.setInt(4, pn.getMaPhieunhap());

            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatChitietPhieunhap(ChitietPhieunhap ctpn) {
        String sqlCommand = "UPDATE chitietphieunhap SET SoLuong = ?, DonGia = ?, NgaySanXuat = ?, NgayHetHan = ? WHERE MaPhieuNhap = ? AND MaThuoc = ?";

        Calendar cal1 = ctpn.getNgaySanxuat();
        Calendar cal2 = ctpn.getNgayHethan();

        String nsx, nhh;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nsx = fm.format(cal1.getTime());
        nhh = fm.format(cal2.getTime());

        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, ctpn.getSoluong());
            pre.setFloat(2, ctpn.getDongia());
            pre.setString(3, nsx);
            pre.setString(4, nhh);
            pre.setInt(5, ctpn.getMaPhieunhap());
            pre.setInt(6, ctpn.getMaThuoc());

            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaPhieunhap(int _maPhieunhap) {
        String sqlCommand = "UPDATE phieunhap SET DaXoa = 1 WHERE MaPhieuNhap = " + _maPhieunhap;
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaChitietPhieunhap(ChitietPhieunhap ctpn) {
        String sqlCommand = "UPDATE chitietphieunhap SET DaXoa = 1 WHERE MaPhieuNhap = ? AND MaThuoc = ?";
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, ctpn.getMaPhieunhap());
            pre.setInt(2, ctpn.getMaThuoc());
            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Phieunhap> layDanhsachPhieuNhap() {
        ArrayList<Phieunhap> dspn = new ArrayList<>();
        String sqlCommand = "SELECT * FROM phieunhap, nhanvien WHERE phieunhap.DaXoa = 0 AND nhanvien.MaNhanVien = phieunhap.MaNhanVien";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(sqlCommand);
            if (rs != null) {
                while (rs.next()) {
                    int maPhieunhap = rs.getInt("MaPhieuNhap");
                    int maNhanvien = rs.getInt("MaNhanVien");
                    int maNhacungcap = rs.getInt("MaNhaCungCap");
                    NhanvienController nvCtrl = new NhanvienController();
                    NhacungcapController nccCtrl = new NhacungcapController();
                    String date = rs.getString("NgayNhap");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    cal.setTime(sdf.parse(date));

                    float tongTien = rs.getFloat("TongTien");
                    dspn.add(new Phieunhap(maPhieunhap, nccCtrl.getNhacungcap(maNhacungcap), nvCtrl.getNhanVien(maNhanvien), cal, tongTien, false));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dspn;
    }

    public ArrayList<ChitietPhieunhap> layDanhsachChitietPhieunhap(int maPhieunhap) {
        ArrayList<ChitietPhieunhap> dsctpn = new ArrayList<>();
        String sqlCommand = "SELECT * FROM chitietphieunhap, thuoc, lonhap, donvitinh WHERE chitietphieunhap.DaXoa = 0 AND thuoc.MaThuoc = chitietphieunhap.MaThuoc AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND chitietphieunhap.MaPhieuNhap = lonhap.MaPhieuNhap AND MaPhieuNhap = '" + maPhieunhap + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(sqlCommand);
            if (rs != null) {
                while (rs.next()) {
                    int maLo = rs.getInt("MaLo");
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenLoaiThuoc = rs.getString("TenLoaiThuoc");
                    int soLuong = rs.getInt("SoLuong");
                    float donGia = rs.getFloat("DonGia");
                    int maDonvitinh = rs.getInt("MaDonViTinh");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    ChitietPhieunhap ctpn = new ChitietPhieunhap(maPhieunhap, maLo, maThuoc, tenThuoc, tenLoaiThuoc, maDonvitinh, tenDonvitinh, soLuong, donGia, false);
                    dsctpn.add(ctpn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsctpn;
    }

    public ArrayList<Nhanvien> layDanhsachNhanvien() {
        ArrayList<Nhanvien> dsnv = new ArrayList<>();
        String sqlCommand = "SELECT * FROM nhanvien WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(sqlCommand);
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

}
