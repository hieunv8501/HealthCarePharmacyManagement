package Controllers;

import DBConnection.DBConnection;
import Models.Khachhang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class KhachhangController {
    
    
    public void themKhachHang(Khachhang KH) {
        
        LocalDate ngay = KH.getNgaySinh();
        String dateFormat;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat = df.format(ngay);
        
        String command = "INSERT INTO khachhang (TenKhachHang, GioiTinh, NgaySinh, SoDienThoai, MaXa) values (?, ?, ?, ? ,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KH.getTenKhachhang());
            pre.setString(2, KH.getGioitinh());
            pre.setString(3, dateFormat);
            pre.setString(4, KH.getSoDienthoai());
            pre.setInt(5, KH.getXa());
            pre.executeUpdate();
            System.out.println("Thêm khách hàng thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void suaKhachhang(Khachhang KH) {
        LocalDate ngay = KH.getNgaySinh();
        String dateFormat;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat = df.format(ngay);
        
        String command = "UPDATE khachhang SET TenKhachHang = ?, GioiTinh = ?, NgaySinh = ?, SoDienThoai = ?, MaXa = ? WHERE MaKhachHang = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, KH.getTenKhachhang());
            pre.setString(2, KH.getGioitinh());
            pre.setString(3, dateFormat);
            pre.setString(4, KH.getSoDienthoai());
            pre.setInt(5, KH.getXa());
            pre.setInt(6, KH.getMaKhachhang());
            pre.executeUpdate();
            System.out.println("Cập nhật khách hàng thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void xoaKhachhang(int maKhachhang) {
        String command = "UPDATE khachhang SET DaXoa = 1 WHERE MaKhachHang = " + maKhachhang;
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.executeUpdate();
            System.out.println("Xóa Khách hàng thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Khachhang> layDSKhachHang() {
        ArrayList<Khachhang> dsKhachhang = new ArrayList<>();
        String query = "SELECT * FROM khachhang WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int maKhachhang = rs.getInt("MaKhachHang");
                    String tenKhachhang = rs.getString("TenKhachHang");
                    String gioiTinh = rs.getString("GioiTinh");
                    LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                    String soDienThoai = rs.getString("SoDienThoai");
                    int maXa = rs.getInt("MaXa");
                    boolean khachQuen = rs.getBoolean("KhachQuen");
                    boolean daXoa = rs.getBoolean("DaXoa");
                    
                    dsKhachhang.add(new Khachhang());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsKhachhang;
    }
    
    public Khachhang layKhachHang(int maKH) {
        for(Khachhang kh : layDSKhachHang()){
            if (kh.getMaKhachhang() == maKH) return kh;
        }
        return null;
    }
}
