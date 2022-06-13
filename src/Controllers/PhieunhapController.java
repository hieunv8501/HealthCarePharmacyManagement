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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PhieunhapController {

    private static ArrayList<Phieunhap> dspn = new ArrayList<>();

    public ArrayList<ChitietPhieunhap> layTatCaChiTietLoNhapTheoMaThuoc() {
        ArrayList<ChitietPhieunhap> dsctpn = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT thuoc.MaThuoc, thuoc.TenThuoc, TenLoaiThuoc, SUM(SoLuongConLai) AS SoLuongConLai, donvitinh.TenDonviTinh, dvt.TenDonviTinh AS TenDonViQuiDoi, thuoc.TiLeQuiDoi, HinhAnh FROM lonhap, thuoc, loaithuoc, chitietphieunhap, donvitinh, donvitinh dvt WHERE thuoc.DaXoa = 0 AND lonhap.DaXoa = 0 AND thuoc.MaThuoc = lonhap.MaThuoc AND lonhap.MaThuoc = chitietphieunhap.MaThuoc AND lonhap.MaPhieuNhap = chitietphieunhap.MaPhieuNhap AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND dvt.MaDonViTinh = thuoc.MaDonViQuiDoi AND thuoc.MaThuoc = lonhap.MaThuoc AND thuoc.MaLoaiThuoc = loaithuoc.MaLoaiThuoc AND NgayHetHan >= '" + formatter.format(date) + "' GROUP BY thuoc.MaThuoc, loaithuoc.TenLoaiThuoc, donvitinh.TenDonviTinh, dvt.TenDonviTinh, thuoc.TenThuoc, thuoc.TiLeQuiDoi, HinhAnh";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenLoaithuoc = rs.getString("TenLoaiThuoc");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    String tenDonvitinh = rs.getString("TenDonviTinh");
                    String tenDonviQuidoi = rs.getString("TenDonViQuiDoi");
                    int tileQuydoi = rs.getInt("TiLeQuiDoi");
                    String hinhAnh = rs.getString("HinhAnh");

                    ChitietPhieunhap ctpn = new ChitietPhieunhap();
                    ctpn.setMaThuoc(maThuoc);
                    ctpn.setTenThuoc(tenThuoc);
                    ctpn.setTenloaiThuoc(tenLoaithuoc);
                    ctpn.setSoLuongConLai(soluongConlai);
                    ctpn.setTenDonvitinh(tenDonvitinh);
                    ctpn.setTenDonvibanle(tenDonviQuidoi);
                    ctpn.setTileQuidoi(tileQuydoi);
                    ctpn.setHinhAnh(hinhAnh);
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

    public static int getNextID() {
        dspn = layDanhsachPhieuNhapAll();
        int i = 0;
        int nextMaPhieunhap = dspn.size() + i;
        boolean flag = false;
        while (!flag) {
            for (Phieunhap pn : dspn) {
                if (pn.getMaPhieunhap() == nextMaPhieunhap) {
                    i++;
                    nextMaPhieunhap = dspn.size() + i;
                    flag = false;
                    break;
                }
            }
            flag = true;
        }
        return nextMaPhieunhap;
    }

    public static ArrayList<Thuoc> layDanhSachThuoc() {
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
        Calendar cal1 = pn.getNgayNhap();

        String ngayNhap;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ngayNhap = fm.format(cal1.getTime());

        String sqlCommand = "INSERT INTO phieunhap(MaPhieuNhap, MaNhaCungCap, MaNhanVien, NgayNhap) VALUES (?,?,?,?)";
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, pn.getMaPhieunhap());
            pre.setInt(2, pn.getNcc().getMaNhacungcap());
            pre.setInt(3, pn.getNv().getMaNhanvien());
            pre.setString(4, ngayNhap);
            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themChitietPhieunhap(ChitietPhieunhap ctpn) {
        String sqlCommand = "INSERT INTO chitietphieunhap(MaPhieuNhap, MaThuoc, NgaySanXuat, NgayHetHan, SoLuong, DonGia) VALUES (?,?,?,?,?)";

        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, ctpn.getMaPhieunhap());
            pre.setInt(2, ctpn.getMaThuoc());
            pre.setString(3, ctpn.getNgaySanxuat().toString());
            pre.setString(4, ctpn.getNgayHethan().toString());
            pre.setInt(5, ctpn.getSoluong());
            pre.setFloat(6, ctpn.getDongia());

            pre.executeUpdate();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatPhieunhap(Phieunhap pn) {
        Calendar cal1 = pn.getNgayNhap();

        String ngayNhap;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ngayNhap = fm.format(cal1.getTime());
        String sqlCommand = "UPDATE phieunhap SET MaNhaCungCap = ?, MaNhanVien = ?, NgayNhap = ? WHERE MaPhieuNhap = ?";
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, pn.getNcc().getMaNhacungcap());
            pre.setInt(2, pn.getNv().getMaNhanvien());
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

//        Calendar cal1 = ctpn.getNgaySanxuat();
//        Calendar cal2 = ctpn.getNgayHethan();
//
//        String nsx, nhh;
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        nsx = fm.format(cal1.getTime());
//        nhh = fm.format(cal2.getTime());
        try {
            DBConnection conn = new DBConnection();
            PreparedStatement pre = conn.getConn().prepareStatement(sqlCommand);
            pre.setInt(1, ctpn.getSoluong());
            pre.setFloat(2, ctpn.getDongia());
            pre.setString(3, ctpn.getNgaySanxuat().toString());
            pre.setString(4, ctpn.getNgayHethan().toString());
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

    public static ArrayList<Phieunhap> layDanhsachPhieuNhap() {
        ArrayList<Phieunhap> dspn = new ArrayList<>();
        String sqlCommand = "SELECT * FROM phieunhap WHERE phieunhap.DaXoa = 0";
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
                    Boolean daXoa = rs.getBoolean("DaXoa");
                    dspn.add(new Phieunhap(maPhieunhap, nccCtrl.getNhacungcap(maNhacungcap), nvCtrl.getNhanVien(maNhanvien), cal, tongTien, daXoa));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dspn;
    }

    public static ArrayList<Phieunhap> layDanhsachPhieuNhapAll() {
        ArrayList<Phieunhap> dspn = new ArrayList<>();
        String sqlCommand = "SELECT * FROM phieunhap";
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
                    Boolean daXoa = rs.getBoolean("DaXoa");
                    dspn.add(new Phieunhap(maPhieunhap, nccCtrl.getNhacungcap(maNhacungcap), nvCtrl.getNhanVien(maNhanvien), cal, tongTien, daXoa));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dspn;
    }

    public ArrayList<ChitietPhieunhap> layDanhsachChitietPhieunhapTheoMaPhieuNhap(int maPhieunhap) {
        ArrayList<ChitietPhieunhap> dsctpn = new ArrayList<>();
        String sqlCommand = "SELECT lonhap.MaLo, chitietphieunhap.MaThuoc,thuoc.TenThuoc, loaithuoc.TenLoaiThuoc, SoLuong, DonGia, dvt.TenDonviTinh as TenDonViTinh, thuoc.TiLeQuiDoi, donvitinh.TenDonviTinh as TenDonViBanLe, NgaySanXuat, NgayHetHan, (chitietphieunhap.DonGia*chitietphieunhap.SoLuong) as ThanhTien, chitietphieunhap.DaXoa FROM chitietphieunhap, thuoc, loaithuoc, lonhap, donvitinh, donvitinh dvt, phieunhap WHERE chitietphieunhap.DaXoa = 0 AND loaithuoc.MaLoaiThuoc = thuoc.MaLoaiThuoc AND lonhap.MaThuoc = chitietphieunhap.MaThuoc AND chitietphieunhap.MaPhieuNhap = lonhap.MaPhieuNhap AND lonhap.MaPhieuNhap = phieunhap.MaPhieuNhap AND thuoc.MaDonViQuiDoi = dvt.MaDonViTinh AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND thuoc.MaThuoc = chitietphieunhap.MaThuoc AND phieunhap.MaPhieuNhap = '" + maPhieunhap + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(sqlCommand);
            if (rs != null) {
                while (rs.next()) {
                    int maLo = rs.getInt("MaLo");
                    int maThuoc = rs.getInt("MaThuoc");
                    int soLuong = rs.getInt("SoLuong");
                    float donGia = rs.getFloat("DonGia");
                    float thanhtien = rs.getFloat("ThanhTien");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    String tenDonvibanle = rs.getString("TenDonViBanLe");
                    int tileQuydoi = rs.getInt("TiLeQuiDoi");
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenLoaithuoc = rs.getString("TenLoaiThuoc");
                    Calendar ngaySanxuat = Calendar.getInstance();
                    Calendar ngayHethan = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    ngaySanxuat.setTime(sdf.parse(rs.getString("NgaySanXuat")));
                    ngayHethan.setTime(sdf.parse(rs.getString("NgayHetHan")));
                    Boolean daXoa = rs.getBoolean("DaXoa");
                    dsctpn.add(new ChitietPhieunhap(maLo, maPhieunhap, maThuoc, tenThuoc, tenLoaithuoc, tenDonvitinh, tenDonvibanle, tileQuydoi, soLuong, donGia, thanhtien, ngaySanxuat, ngayHethan, daXoa));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsctpn;
    }

    public static ArrayList<Nhanvien> layDanhsachNhanvien() {
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
