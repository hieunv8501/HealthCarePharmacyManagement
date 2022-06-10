package Controllers;

import DBConnection.DBConnection;
import java.sql.CallableStatement;
import Models.LoNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LonhapController {

    public LoNhap layLonhapTheoMaThuoc(int maThuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT thuoc.MaThuoc, TenThuoc, SUM(SoLuongConLai) AS SoLuongConLai, donvitinh.TenDonviTinh, dvt.TenDonviTinh AS TenDonViQuiDoi, thuoc.TiLeQuiDoi FROM lonhap, thuoc, chitietphieunhap, donvitinh, donvitinh dvt WHERE thuoc.DaXoa = 0 AND lonhap.DaXoa = 0 AND thuoc.MaThuoc = lonhap.MaThuoc AND lonhap.MaThuoc = chitietphieunhap.MaThuoc AND lonhap.MaPhieuNhap = chitietphieunhap.MaPhieuNhap AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND dvt.MaDonViTinh = thuoc.MaDonViQuiDoi AND thuoc.MaThuoc = " + maThuoc + " AND NgayHetHan >= '" + formatter.format(date) + "' GROUP BY thuoc.MaThuoc, TenThuoc, donvitinh.TenDonviTinh,  dvt.TenDonviTinh, thuoc.TiLeQuiDoi";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    String tenDonviQuidoi = rs.getString("TenDonViQuiDoi");
                    int tiLeQuiDoi = rs.getInt("TiLeQuiDoi");

                    LoNhap ln = new LoNhap();
                    ln.setMaThuoc(maThuoc);
                    ln.setTenThuoc(tenThuoc);
                    ln.setSoluongConlai(soluongConlai);
                    ln.setTenDonviBanLe(tenDonvitinh);
                    ln.setTenDonvitinh(tenDonviQuidoi);
                    ln.setTiLeQuidoi(tiLeQuiDoi);
                    return ln;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

        public int tinhSoLoaiThuoc() {
        String query = "SELECT COUNT(*) AS SoLuong from thuoc WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    int soLuong = rs.getInt("SoLuong");
                    return soLuong;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return -1;
    }
    
    public LoNhap layLonhapTheoMaThuocHetHSD(int maThuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT thuoc.MaThuoc, TenThuoc, SUM(SoLuongConLai) AS SoLuongConLai, donvitinh.TenDonviTinh, dvt.TenDonviTinh AS TenDonViQuiDoi, thuoc.TiLeQuiDoi FROM lonhap, thuoc, chitietphieunhap, donvitinh, donvitinh dvt WHERE thuoc.DaXoa = 0 AND lonhap.DaXoa = 0 AND thuoc.MaThuoc = lonhap.MaThuoc AND lonhap.MaThuoc = chitietphieunhap.MaThuoc AND lonhap.MaPhieuNhap = chitietphieunhap.MaPhieuNhap AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND dvt.MaDonViTinh = thuoc.MaDonViQuiDoi AND thuoc.MaThuoc = " + maThuoc + " AND NgayHetHan < '" + formatter.format(date) + "' GROUP BY thuoc.MaThuoc, TenThuoc, donvitinh.TenDonviTinh,  dvt.TenDonviTinh, thuoc.TiLeQuiDoi";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    String tenDonviQuidoi = rs.getString("TenDonViQuiDoi");
                    int tiLeQuiDoi = rs.getInt("TiLeQuiDoi");

                    LoNhap ln = new LoNhap();
                    ln.setMaThuoc(maThuoc);
                    ln.setTenThuoc(tenThuoc);
                    ln.setSoluongConlai(soluongConlai);
                    ln.setTenDonviBanLe(tenDonvitinh);
                    ln.setTenDonvitinh(tenDonviQuidoi);
                    ln.setTiLeQuidoi(tiLeQuiDoi);
                    return ln;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public void xoaLonhap(int maLo) {
        String command = "UPDATE lonhap SET DaXoa = 1 WHERE MaLo = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);

            pre.setInt(1, maLo);

            pre.executeUpdate();
            System.out.println("Xóa lô nhập thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoNhap layLoNhap(int maLo) {

        String query = "SELECT * FROM lonhap Where DaXoa = 0 AND MaLo = '" + maLo + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    LoNhap ln = new LoNhap();
                    ln.setSoluongConlai(soluongConlai);
                    ln.setMaLo(maLo);
                    return ln;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public ArrayList<LoNhap> layDanhsachLNConHSD() {
        ArrayList<LoNhap> dsln = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT MaLo, thuoc.MaThuoc, TenThuoc, SoLuongConLai, TiLeQuiDoi, donvitinh.TenDonviTinh, NgaySanXuat, NgayHetHan, dvt.TenDonviTinh AS TDVT FROM thuoc, lonhap, donvitinh, chitietphieunhap, donvitinh AS dvt WHERE thuoc.MaThuoc = lonhap.MaThuoc AND thuoc.MaDonViQuiDoi = donvitinh.MaDonViTinh AND chitietphieunhap.MaPhieuNhap = lonhap.MaPhieuNhap AND chitietphieunhap.MaThuoc = lonhap.MaThuoc AND dvt.MaDonViTinh = thuoc.MaDonViTinh AND lonhap.DaXoa = 0 AND NgayHetHan >= '" + formatter.format(date) + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maLo = rs.getInt("MaLo");
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    int tileQuidoi = rs.getInt("TiLeQuiDoi");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    String tenDonviBanLe = rs.getString("TDVT");
                    LocalDate ngaySanXuat = rs.getDate("NgaySanXuat").toLocalDate();
                    LocalDate ngayHetHan = rs.getDate("NgayHetHan").toLocalDate();

                    dsln.add(new LoNhap(maLo, maThuoc, tenThuoc, soluongConlai, tileQuidoi, tenDonvitinh, tenDonviBanLe, ngaySanXuat, ngayHetHan, false));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsln;
    }

    public ArrayList<LoNhap> layDanhsachThuocSapHetHang() throws SQLException {
        ArrayList<LoNhap> dsln = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
//        String query = "SELECT thuoc.MaThuoc, TenThuoc, SUM(SoLuongConLai) AS SoLuongConLai, TenDonviTinh FROM lonhap, thuoc, chitietphieunhap, donvitinh WHERE thuoc.DaXoa = 0 AND lonhap.DaXoa = 0 AND thuoc.MaThuoc = lonhap.MaThuoc AND lonhap.MaThuoc = chitietphieunhap.MaThuoc AND lonhap.MaPhieuNhap = chitietphieunhap.MaPhieuNhap AND donvitinh.MaDonViTinh = thuoc.MaDonViTinh AND NgayHetHan >= '" + formatter.format(date) + "' GROUP BY thuoc.MaThuoc, TenThuoc, TenDonviTinh HAVING SUM(SoLuongConLai) < 40  ";
        DBConnection con = new DBConnection();
        try {
            CallableStatement call = con.getConn().prepareCall("{call sp_LayThuocSapHetHang(?)}");
            call.setString(1, formatter.format(date));
            ResultSet rs = call.executeQuery();
            
            if (rs != null) {

                while (rs.next()) {
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    int soluongConlai = rs.getInt("SoLuong");

                    LoNhap ln = new LoNhap();
                    ln.setMaThuoc(maThuoc);
                    ln.setTenThuoc(tenThuoc);
                    ln.setSoluongConlai(soluongConlai);
                    ln.setTenDonviBanLe(tenDonvitinh);

                    dsln.add(ln);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsln;
    }

    public ArrayList<LoNhap> layDanhsachLNHetHSD() {
        ArrayList<LoNhap> dsln = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String query = "SELECT MaLo, thuoc.MaThuoc, TenThuoc, SoLuongConLai, TiLeQuiDoi, donvitinh.TenDonviTinh, NgaySanXuat, NgayHetHan, dvt.TenDonviTinh AS TDVT FROM thuoc, lonhap, donvitinh, chitietphieunhap, donvitinh AS dvt WHERE thuoc.MaThuoc = lonhap.MaThuoc AND thuoc.MaDonViQuiDoi = donvitinh.MaDonViTinh AND chitietphieunhap.MaPhieuNhap = lonhap.MaPhieuNhap AND chitietphieunhap.MaThuoc = lonhap.MaThuoc AND dvt.MaDonViTinh = thuoc.MaDonViTinh AND lonhap.DaXoa = 0 AND NgayHetHan < '" + formatter.format(date) + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maLo = rs.getInt("MaLo");
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc = rs.getString("TenThuoc");
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    int tileQuidoi = rs.getInt("TiLeQuiDoi");
                    String tenDonvitinh = rs.getString("TenDonViTinh");
                    String tenDonviBanLe = rs.getString("TDVT");
                    LocalDate ngaySanXuat = rs.getDate("NgaySanXuat").toLocalDate();
                    LocalDate ngayHetHan = rs.getDate("NgayHetHan").toLocalDate();

                    dsln.add(new LoNhap(maLo, maThuoc, tenThuoc, soluongConlai, tileQuidoi, tenDonvitinh, tenDonviBanLe, ngaySanXuat, ngayHetHan, false));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsln;
    }

}
