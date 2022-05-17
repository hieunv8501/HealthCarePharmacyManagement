package Controllers;

import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.Khuyenmai;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KhuyenmaiController {

    public void themMaKhuyenmai(Khuyenmai KM) {
        Calendar cal1 = KM.getNgayBatdau();
        Calendar cal2 = KM.getNgayKetthuc();

        String dateBDFormat, dateKTFormat;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        dateBDFormat = fm.format(cal1.getTime());
        dateKTFormat = fm.format(cal2.getTime());
        String command = "INSERT INTO khuyenmai(MaKhuyenMai, TenKhuyenMai, DieuKienKhuyenMai, PhanTramKhuyenMai, NgayBatDau, NgayKetThuc) VALUES (?,?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KM.getMaKhuyenmai());
            pre.setString(2, KM.getTenKhuyenmai());
            pre.setFloat(3, KM.getDieukienKhuyenmai());
            pre.setFloat(4, KM.getPhantramKhuyenmai());
            pre.setString(5, dateBDFormat);
            pre.setString(6, dateKTFormat);

            pre.executeUpdate();
            System.out.println("Thêm mã khuyến mãi thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatMaKhuyenmai(Khuyenmai KM) {
        Calendar cal1 = KM.getNgayBatdau();
        Calendar cal2 = KM.getNgayKetthuc();

        String dateBDFormat, dateKTFormat;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        dateBDFormat = fm.format(cal1.getTime());
        dateKTFormat = fm.format(cal2.getTime());

        String command = "UPDATE khuyenmai SET TenKhuyenMai = ?, DieuKienKhuyenMai = ?, PhanTramKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaKhuyenMai = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KM.getTenKhuyenmai());
            pre.setFloat(2, KM.getDieukienKhuyenmai());
            pre.setFloat(3, KM.getPhantramKhuyenmai());
            pre.setString(4, dateBDFormat);
            pre.setString(5, dateKTFormat);
            pre.setString(6, KM.getMaKhuyenmai());

            pre.executeUpdate();
            System.out.println("Cập nhật mã khuyến mãi thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaMaKhuyenmai(String maKhuyemMai) {
        String command = "UPDATE khuyenmai SET DaXoa = 1 WHERE MaKhuyenMai = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);

            pre.setString(1, maKhuyemMai);

            pre.executeUpdate();
            System.out.println("Xóa mã khuyến mãi thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}