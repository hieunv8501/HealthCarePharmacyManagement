package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Nhanvien {
    private int maNhanvien;
    private String tenNhanvien, soDienThoai, gioiTinh, bangCap;
    private LocalDate ngaySinh;
    private int loaiNhanvien;
    private int xa;
    private long luong;
    private boolean daXoa;
    DBConnection nhanvienConnection;;

    public Nhanvien() {
    }

    public Nhanvien(int maNhanvien, String tenNhanvien, LocalDate ngaySinh, String soDienThoai, String gioiTinh, String bangCap, int MaLoaiNhanvien, int maxa, long luong, boolean daXoa) {
        this.maNhanvien = maNhanvien;
        this.tenNhanvien = tenNhanvien;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.bangCap = bangCap;
        this.loaiNhanvien = loaiNhanvien;
        this.xa = xa;
        this.luong = luong;
        this.daXoa = daXoa;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getTenNhanvien() {
        return tenNhanvien;
    }

    public void setTenNhanvien(String tenNhanvien) {
        this.tenNhanvien = tenNhanvien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getBangCap() {
        return bangCap;
    }

    public void setBangCap(String bangCap) {
        this.bangCap = bangCap;
    }

    public int getLoaiNhanvien() {
        return loaiNhanvien;
    }

    public void setLoaiNhanvien(int loaiNhanvien) {
        this.loaiNhanvien = loaiNhanvien;
    }

    public int getXa() {
        return this.xa;
    }
    
//    public String getHuyen() {
//        return xa.getHuyen();
//    }
//    
//    public String getTinh() {
//        return xa.getTinh();
//    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public ArrayList<Nhanvien> readDB() {
        ArrayList<Nhanvien> dsnv = new ArrayList<>();
        nhanvienConnection = new DBConnection();
        try {
            String qry = "SELECT * FROM nhanvien";
            ResultSet rs = nhanvienConnection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    int manv = rs.getInt("MaNhanVien");
                    String tennv = rs.getString("TenNhanVien");
                    LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
                    int xa = rs.getInt("MaXa");      
                    int loainhanvien = rs.getInt("MaLoaiNhanVien");
                    String sdt = rs.getString("SoDienThoai");
                    String gioitinh = rs.getString("GioiTinh");      
                    String bangcap = rs.getString("BangCap");    
                    long luong = rs.getLong("Luong");                                    
                    boolean daxoa = rs.getInt("DaXoa") == 1? true : false;
                    dsnv.add(new Nhanvien(manv, tennv, ngaysinh, sdt, gioitinh, bangcap, loainhanvien, xa, luong, daxoa));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } finally {
            nhanvienConnection.closeConnection();
        }
        return dsnv;
    }

//    public ArrayList<Nhanvien> search(String columnName, String value) {
//        nhanvienConnection = new DBConnection();
//        ArrayList<Nhanvien> dsnv = new ArrayList<>();
//
//        try {
//            String qry = "SELECT * FROM nhanvien WHERE " + columnName + " LIKE '%" + value + "%'";
//            ResultSet r = nhanvienConnection.sqlQuery(qry);
//            if (r != null) {
//                while (r.next()) {
//                    int manv = r.getInt("MaNhanVien");
//                    String tennv = r.getString("TenNhanVien");
//                    LocalDate ngaysinh = r.getDate("NgaySinh").toLocalDate();
//                    String xa = r.getString("Xa");      
//                    String loainhanvien = r.getString("LoaiNhanVien");
//                    String sdt = r.getString("SoDienThoai");
//                    String gioitinh = r.getString("GioiTinh");      
//                    String bangcap = r.getString("BangCap");    
//                    long luong = r.getLong("Luong");                                    
//                    int trangthai = r.getInt("TrangThai");
//                    //dsnv.themNhanvien(new Nhanvien(manv, tennv, ngaysinh, diachi, sdt, gioitinh, bangcap, tt));
//                }
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhân viên");
//        } finally {
//            nhanvienConnection.closeConnection();
//        }
//
//        return dsnv;
//    }

//    public Boolean themNhanvien(Nhanvien nv) {
//        nhanvienConnection = new DBConnection();
//        Boolean ok = nhanvienConnection.sqlUpdate("INSERT INTO nhanvien (MaNhanVien, TenNhanVien, MaLoaiNhanVien, NgaySinh, MaXa, SoDienThoai, GioiTinh, BangCap) VALUES ('"
//                + nv.getMaNhanvien()+ "', '"
//                + nv.getTenNhanvien()+ "', '"
//                + nv.getLoaiNhanvien()+ "', '"
//                + nv.getNgaySinh() + "', '" 
//                + nv.getXa()+ "', '" 
//                + nv.getSoDienThoai()+ "', '" 
//                + nv.isDaXoa()+ "');");
//        nhanvienConnection.closeConnection();
//        return ok;
//    }

//    public Boolean xoaNhanvien(int manv) {
//        nhanvienConnection = new DBConnection();
//        Boolean ok = nhanvienConnection.sqlUpdate("DELETE FROM nhanvien WHERE nhanvien.MaNhanVien = '" + manv + "'");
//        nhanvienConnection.closeConnection();
//        return ok;
//    }
//
//    public Boolean suaNhanvien(int MaNV, String TenNV, LocalDate NgaySinh, String DiaChi, String SDT, int trangthai) {
//        nhanvienConnection = new DBConnection();
//        Boolean ok = nhanvienConnection.sqlUpdate("UPDATE nhanvien SET "
//                + "TenNhanVien='" + TenNV
//                + "',NgaySinh='" + NgaySinh 
//                + "',DiaChi='" + DiaChi 
//                + "',SoDienThoai='" + SDT 
//                + "',TrangThai='" + trangthai 
//                + "' where MaNhanVien='" + MaNV + "'");
//        nhanvienConnection.closeConnection();
//        return ok;
//    }
    
    public Boolean updateTrangThai(int manv, int trangthai) {
        nhanvienConnection = new DBConnection();
        Boolean ok = nhanvienConnection.sqlUpdate("UPDATE nhanvien SET "
                + "TrangThai='" + trangthai 
                + "' where MaNhanVien='" + manv + "'");
        nhanvienConnection.closeConnection();
        return ok;
    }

    public void close() {
        nhanvienConnection.closeConnection();
    }
    
}
