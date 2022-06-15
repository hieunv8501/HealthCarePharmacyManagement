package Controllers;

import DBConnection.DBConnection;
import Models.Nhanvien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhanvienController {

    private static ArrayList<Nhanvien> dsnv = new ArrayList<>();
    private Nhanvien nhanvienDA = new Nhanvien();

    public NhanvienController() {
        dsnv = nhanvienDA.readDB();
    }

    public String[] getHeaders() {
        return new String[]{"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Trạng thái"};
    }

    public void readDB() {
        dsnv = nhanvienDA.readDB();
    }

    public String getNextID() {
        return "NV" + String.valueOf(this.dsnv.size() + 1);
    }

    public static Nhanvien getNhanVien(int manv) {
        for (Nhanvien nv : dsnv) {
            if (nv.getMaNhanvien()== manv) {
                return nv;
            }
        }
        return null;
    }
    
    
//
//    public ArrayList<Nhanvien> search(String value, String type, LocalDate _ngay1, LocalDate _ngay2) {
//        ArrayList<Nhanvien> result = new ArrayList<>();
//
//        dsnv.forEach((nv) -> {
//            if (type.equals("Tất cả")) {
//                if (nv.getMaNhanvien().contains(value.toLowerCase())
//                        || nv.getTenNV().toLowerCase().contains(value.toLowerCase())
//                        || nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())
//                        || nv.getDiaChi().toLowerCase().contains(value.toLowerCase())
//                        || nv.getSDT().toLowerCase().contains(value.toLowerCase())
//                        || String.valueOf(nv.getTrangThai() == 1 ? "Ẩn" : "Hiện").toLowerCase().contains(value.toLowerCase())) {
//                    result.add(nv);
//                }
//            } else {
//                switch (type) {
//                    case "Mã nhân viên":
//                        if (nv.getMaNV().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                    case "Tên nhân viên":
//                        if (nv.getTenNV().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                    case "Ngày sinh":
//                        if (nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                    case "Địa chỉ":
//                        if (nv.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                    case "Số điện thoại":
//                        if (nv.getSDT().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                    case "Trạng thái":
//                        if (String.valueOf(nv.getTrangThai() == 0 ? "Hiện" : "Ẩn").toLowerCase().contains(value.toLowerCase())) {
//                            result.add(nv);
//                        }
//                        break;
//                }
//            }
//        });
//
//        //Ngay sinh
//        for (int i = result.size() - 1; i >= 0; i--) {
//            NhanVien nv = result.get(i);
//            LocalDate ngaysinh = nv.getNgaySinh();
//
//            Boolean ngayKhongThoa = (_ngay1 != null && ngaysinh.isBefore(_ngay1)) || (_ngay2 != null && ngaysinh.isAfter(_ngay2));
//
//            if (ngayKhongThoa) {
//                result.remove(nv);
//            }
//        }
//
//        return result;
//    }
//
//    public Boolean add(NhanVien nv) {
//        Boolean ok = qlnvDAO.add(nv);
//
//        if (ok) {
//            dsnv.add(nv);
//        }
//        return ok;
//    }

//    public Boolean add(String manv, String tennv, LocalDate ngaysinh, String diachi, String sdt, int trangthai) {
//        NhanVien nv = new NhanVien(manv, tennv, ngaysinh, diachi, sdt, trangthai);
//        return add(nv);
//    }
//
//    public Boolean delete(String manv) {
//        Boolean ok = qlnvDAO.delete(manv);
//
//        if (ok) {
//            for (int i = (dsnv.size() - 1); i >= 0; i--) {
//                if (dsnv.get(i).getMaNV().equals(manv)) {
//                    dsnv.remove(i);
//                }
//            }
//        }
//        return ok;
//    }
//
//    public Boolean update(String manv, String tennv, LocalDate ngaysinh, String diachi, String sdt, int trangthai) {
//        Boolean ok = qlnvDAO.update(manv, tennv, ngaysinh, diachi, sdt, trangthai);
//
//        if (ok) {
//            dsnv.forEach((nv) -> {
//                if (nv.getMaNV().equals(manv)) {
//                    nv.setTenNV(tennv);
//                    nv.setNgaySinh(ngaysinh);
//                    nv.setDiaChi(diachi);
//                    nv.setSDT(sdt);
//                    nv.setTrangThai(trangthai);
//                }
//            });
//        }
//
//        return ok;
//    }
//
//    public Boolean updateTrangThai(String manv, int trangthai) {
//        Boolean ok = qlnvDAO.updateTrangThai(manv, trangthai);
//
//        if (ok) {
//            dsnv.forEach((nv) -> {
//                if (nv.getMaNV().equals(manv)) {
//                    nv.setTrangThai(trangthai);
//                }
//            });
//        }
//
//        return ok;
//    }
//
    public static ArrayList<Nhanvien> getDanhSachNhanvien() {
        return dsnv;
    }
    
    public static ArrayList<Nhanvien> getDSNV() {
        ArrayList<Nhanvien> dsNhanvien = new ArrayList<>();
        String query = "SELECT * FROM nhanvien WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {

                while (rs.next()) {
                    int manv = rs.getInt("MaNhanVien");
                    String tennv = rs.getString("TenNhanVien");
                    LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
                    int xa = rs.getInt("MaXa");    
                    XaController xaCtrl = new XaController();
                    int maLoaiNV = rs.getInt("MaLoaiNhanVien");
                    LoaiNhanvienController loaiNVCtrl = new LoaiNhanvienController();
                    String sdt = rs.getString("SoDienThoai");
                    String gioitinh = rs.getString("GioiTinh");     
                    String bangcap = rs.getString("BangCap");  
                    long luong = rs.getLong("Luong");                                    
                    dsNhanvien.add(new Nhanvien(manv, tennv, ngaysinh, sdt, gioitinh, bangcap, loaiNVCtrl.getLoaiNhanvien(maLoaiNV), xaCtrl.getXa(xa), luong, false));

                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsNhanvien;
    }
    
    public static boolean themNhanvien(Nhanvien nhanvien) {
        LocalDate ngay = nhanvien.getNgaySinh();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedString = ngay.format(formatter);

        String command = "INSERT INTO nhanvien (TenNhanVien, MaLoaiNhanVien, NgaySinh, MaXa, SoDienThoai, GioiTinh, BangCap) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, nhanvien.getTenNhanvien());
            pre.setInt(2, nhanvien.getLoaiNhanvien().getMaLoaiNhanvien());
            pre.setString(3, formattedString);
            pre.setInt(4, nhanvien.getXa().getMaXa());
            pre.setString(5, nhanvien.getSoDienThoai());
            pre.setString(6, nhanvien.getGioiTinh());
            pre.setString(7, nhanvien.getBangCap());
            pre.executeUpdate();
            
            con.closeConnection();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public static boolean suaNhanvien(Nhanvien nhanvien) {
        LocalDate ngay = nhanvien.getNgaySinh();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedString = ngay.format(formatter);
        
        String command = "UPDATE nhanvien SET TenNhanVien = ?, MaLoaiNhanVien = ?, NgaySinh = ?, MaXa = ?, SoDienThoai = ?, GioiTinh = ?, BangCap =? Where MaNhanVien = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setString(1, nhanvien.getTenNhanvien());
            pre.setInt(2, nhanvien.getLoaiNhanvien().getMaLoaiNhanvien());
            pre.setString(3, formattedString);
            pre.setInt(4, nhanvien.getXa().getMaXa());
            pre.setString(5, nhanvien.getSoDienThoai());
            pre.setString(6, nhanvien.getGioiTinh());
            pre.setString(7, nhanvien.getBangCap());
            pre.setInt(8, nhanvien.getMaNhanvien());
            pre.executeUpdate();
            
            con.closeConnection();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public static boolean xoaNhanvien(int maNhanvien) {
        String command = "UPDATE nhanvien SET DaXoa = 1 WHERE MaNhanVien = " + maNhanvien;
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.executeUpdate();
            con.closeConnection();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
