package Controllers;

import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.Khuyenmai;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhuyenmaiController {

    public ArrayList<Khuyenmai> layDanhsachMKM() {
        ArrayList<Khuyenmai> dsmkm = new ArrayList<>();
        String query = "SELECT * FROM khuyenmai Where DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    String maKhuyenmai = rs.getString("MaKhuyenMai");
                    String tenKhuyenmai = rs.getString("TenKhuyenMai");
                    float dkKhuyenmai = rs.getFloat("DieuKienKhuyenMai");
                    float ptKhuyenmai = rs.getFloat("PhanTramKhuyenMai");
                    LocalDate ngayBD = rs.getDate("NgayBatDau").toLocalDate();
                    LocalDate ngayKT = rs.getDate("NgayKetThuc").toLocalDate();

                    boolean daxoa = rs.getInt("DaXoa") == 1 ? true : false;
                    dsmkm.add(new Khuyenmai(maKhuyenmai, tenKhuyenmai, dkKhuyenmai, ptKhuyenmai, ngayBD, ngayKT, daxoa));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsmkm;
    }

    public Khuyenmai layMaKhuyenmai(String MKM) {

        String query = "SELECT * FROM khuyenmai Where DaXoa = 0 AND MaKhuyenMai = '" + MKM + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    String maKhuyenmai = rs.getString("MaKhuyenMai");
                    String tenKhuyenmai = rs.getString("TenKhuyenMai");
                    float dkKhuyenmai = rs.getFloat("DieuKienKhuyenMai");
                    float ptKhuyenmai = rs.getFloat("PhanTramKhuyenMai");
                    LocalDate ngayBD = rs.getDate("NgayBatDau").toLocalDate();
                    LocalDate ngayKT = rs.getDate("NgayKetThuc").toLocalDate();

                    Khuyenmai mkm = new Khuyenmai(maKhuyenmai, tenKhuyenmai, dkKhuyenmai, ptKhuyenmai, ngayBD, ngayKT, false);
                    return mkm;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }

    public void themMaKhuyenmai(Khuyenmai KM) {
        LocalDate date1 = KM.getNgayBatdau();
        LocalDate date2 = KM.getNgayKetthuc();

//        String dateBDFormat, dateKTFormat;
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//        dateBDFormat = fm.format(cal1.getTime());
//        dateKTFormat = fm.format(cal2.getTime());
        String command = "INSERT INTO khuyenmai(MaKhuyenMai, TenKhuyenMai, DieuKienKhuyenMai, PhanTramKhuyenMai, NgayBatDau, NgayKetThuc) VALUES (?,?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KM.getMaKhuyenmai());
            pre.setString(2, KM.getTenKhuyenmai());
            pre.setFloat(3, KM.getDieukienKhuyenmai());
            pre.setFloat(4, KM.getPhantramKhuyenmai());
            pre.setString(5, date1.toString());
            pre.setString(6, date2.toString());

            pre.executeUpdate();
            System.out.println("Thêm mã khuyến mãi thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhatMaKhuyenmai(Khuyenmai KM) {
        LocalDate date1 = KM.getNgayBatdau();
        LocalDate date2 = KM.getNgayKetthuc();

//        String dateBDFormat, dateKTFormat;
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//        dateBDFormat = fm.format(cal1.getTime());
//        dateKTFormat = fm.format(cal2.getTime());
        String command = "UPDATE khuyenmai SET TenKhuyenMai = ?, DieuKienKhuyenMai = ?, PhanTramKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaKhuyenMai = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KM.getTenKhuyenmai());
            pre.setFloat(2, KM.getDieukienKhuyenmai());
            pre.setFloat(3, KM.getPhantramKhuyenmai());
            pre.setString(4, date1.toString());
            pre.setString(5, date2.toString());
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
