package Controllers;

import java.util.Calendar;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.Hoadon;
import Models.ChitietHoadon;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HoadonController {

    public ArrayList<Hoadon> layDanhsachHD() {
        ArrayList<Hoadon> dshd = new ArrayList<>();
        String query = "SELECT * FROM hoadon";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maHoadon = rs.getInt("MaHoaDon");
                    int maNhanvien = rs.getInt("MaNhanVien");
                    int maKhachhang = rs.getInt("MaKhachHang");
                    String maKhuyenmai = rs.getString("MaKhuyenMai");

                    String date = rs.getString("NgayLap");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    cal.setTime(sdf.parse(date));// all done
//                    LocalDate ngayLap = rs.getDate("NgayLap").toLocalDate();

                    float tongTien = rs.getFloat("TongTien");
                    boolean daxoa = rs.getInt("DaXoa") == 1 ? true : false;
                    dshd.add(new Hoadon(maHoadon, maNhanvien, maKhachhang, maKhuyenmai, cal, tongTien, daxoa));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dshd;
    }
//
//    public static void main(String[] args) {
//        HoadonController hd = new HoadonController();
//        ArrayList<Hoadon> HD = new ArrayList<>();
//        HD = hd.layDanhsachHD();
//        for (Hoadon str : HD) {
//            System.out.println(str.getNgayLap());
//        }
//    }

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

}
