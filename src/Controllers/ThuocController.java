package Controllers;

import Models.Thuoc;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ThuocController {

    public static Thuoc getThuoc(int maThuoc) {
        for (Thuoc q : getDanhSachThuoc()) {
            if (q.getMaThuoc() == maThuoc) return q;
        }
        return null;
    }

    public static void themThuoc(Thuoc thuoc) {

        String command = "insert into thuoc(MaThuoc,TenThuoc,Mota,DoTuoi,HinhAnh,MaDonViTinh,MaDonViQuiDoi,TiLeQuiDoi,MaNhaCungCap,MaLoaiThuoc,GiaBan) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, thuoc.getTenThuoc());
            pre.setString(2, thuoc.getTenThuoc());
            pre.setString(3, thuoc.getMota());
            pre.setString(4, thuoc.getDotuoi());
            pre.setString(5, thuoc.getHinhanh());
            pre.setInt(6, thuoc.getDonvitinh().getMaDonvitinh());
            pre.setInt(7, thuoc.getDonviQuydoi().getMaDonvitinh());
            pre.setInt(8, thuoc.getTileQuydoi());
            pre.setInt(9, thuoc.getNhacungcap().getMaNhacungcap());
            pre.setInt(10, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            pre.setFloat(11, thuoc.getGiaBan());
            pre.executeUpdate();
            System.out.println("Thêm mã thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void timTatCaThuoc() {

    }

    public static void capnhatThuoc(Thuoc thuoc, int maThuocCu) {
        String command = "Update thuoc set MaThuoc=?,TenThuoc=?,Mota=?,DoTuoi=?,HinhAnh=?,MaDonViTinh=?,MaDonViQuiDoi=?,TiLeQuiDoi=?,MaNhaCungCap=?,MaLoaiThuoc=?,GiaBan=? where MaThuoc=? ";

        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, thuoc.getMaThuoc());
            pre.setString(2, thuoc.getTenThuoc());
            pre.setString(3, thuoc.getMota());
            pre.setString(4, thuoc.getDotuoi());
            pre.setString(5, thuoc.getHinhanh());
            pre.setInt(6, thuoc.getDonvitinh().getMaDonvitinh());
            pre.setInt(7, thuoc.getDonviQuydoi().getMaDonvitinh());
            pre.setInt(8, thuoc.getTileQuydoi());
            pre.setInt(9, thuoc.getNhacungcap().getMaNhacungcap());
            pre.setInt(10, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            pre.setFloat(11, thuoc.getGiaBan());
            pre.setInt(12, maThuocCu);
            pre.executeUpdate();
            System.out.println("Cập nhật thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void xoaThuoc(int maThuoc) {
        String command = "UPDATE thuoc SET DaXoa = 1 WHERE MaThuoc = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);

            pre.setInt(1, maThuoc);
            pre.executeUpdate();
            System.out.println("Xóa thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Thuoc> getDanhSachThuoc() {
        Thuoc c = new Thuoc();
        return c.readDB();
    }
}
