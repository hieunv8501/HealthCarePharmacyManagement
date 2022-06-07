package Controllers;

import DBConnection.DBConnection;
import Models.Donvitinh;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DonvitinhController {

    private static ArrayList<Donvitinh> dsDonvitinhs = new ArrayList<>();

    public static Donvitinh getDonvitinh(int _maDonvitinh) {
        for (Donvitinh donvitinh : dsDonvitinhs) {
            if (donvitinh.getMaDonvitinh() == _maDonvitinh) {
                return donvitinh;
            }
        }
        return null;
    }

    public static void themDonvitinh(Donvitinh donvitinh) {
        String command = "INSERT INTO donvitinh(MaDonvitinh,TenDonvitinh,GiaTri)  values (?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, donvitinh.getMaDonvitinh());
            pre.setString(2, donvitinh.getTenDonvitinh());
            pre.setString(3, donvitinh.getGiatri());
            pre.executeUpdate();
            System.out.println("Thêm đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void xoaDonvitinh(int maDonvitinh) {
        String command = "UPDATE donvitinh set DaXoa=1 where MaDonvitinh=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, maDonvitinh);
            pre.executeUpdate();
            System.out.println("Xóa đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void capnhatDonvitinh(Donvitinh donvitinh, int maDonvitinhCu) {
        String command = "UPDATE donvitinh SET MaDonViTinh=?, TenDonvitinh=?, GiaTri=? WHERE MaDonViTinh=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, donvitinh.getMaDonvitinh());
            pre.setString(2, donvitinh.getTenDonvitinh());
            pre.setString(3, donvitinh.getGiatri());
            pre.setInt(4, maDonvitinhCu);
            pre.executeUpdate();
            System.out.println("Cập nhật đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Donvitinh> getDanhSachDonvitinh() {
        Donvitinh donvitinhDA = new Donvitinh();
        return donvitinhDA.readDB();
    }
}
