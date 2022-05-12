package Controllers;

import java.util.Calendar;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.Hoadon;
import Models.ChitietHoadon;
import java.text.SimpleDateFormat;

public class HoadonController {

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
            System.out.println("Thêm hóa đơn thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themCTHD(ChitietHoadon CTHD) {
        String command = "INSERT INTO chitiethoadon(MaThuoc, MaLo, MaDonViTinh, SoLuong, DonGia) VALUES (?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, CTHD.getMaThuoc());
            pre.setInt(2, CTHD.getMaLo());
            pre.setInt(3, CTHD.getMaDonvitinh());
            pre.setInt(4, CTHD.getSoluong());
            pre.setFloat(5, CTHD.getDongia());

            pre.executeUpdate();
            System.out.println("Thêm chi tiết hóa đơn thành công");
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
            System.out.println("Cập nhật hóa đơn thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatCTHD(ChitietHoadon CTHD) {
        String command = "UPDATE chitiethoadon SET SoLuong = ?, DonGia = ? WHERE MaHoaDon = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, CTHD.getSoluong());
            pre.setFloat(2, CTHD.getDongia());
            pre.setInt(3, CTHD.getMaHoadon());

            pre.executeUpdate();
            System.out.println("Cập nhật hóa đơn thành công");
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
            System.out.println("Xóa hóa đơn thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaCTHD(ChitietHoadon CTHD) {
        String command = "UPDATE hoadon SET DaXoa = 1 WHERE MaHoaDon = ? AND MaThuoc = ? AND MaLo = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, CTHD.getMaHoadon());
            pre.setFloat(2, CTHD.getMaThuoc());
            pre.setInt(3, CTHD.getMaLo());
            pre.executeUpdate();
            System.out.println("Xóa hóa đơn thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HoadonController HD = new HoadonController();
    }
}
