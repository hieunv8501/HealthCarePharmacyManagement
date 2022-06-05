package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Taikhoan {
    private String taikhoan, matkhau, maQuyen;
    private int maNhanvien;
    private boolean daXoa;
    private DBConnection taikhoanConnection;
    
    public Taikhoan(){}
    
    public Taikhoan(String taikhoan, String matkhau, int maNhanvien, String maQuyen, boolean daXoa) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.maNhanvien = maNhanvien;
        this.maQuyen = maQuyen;
        this.daXoa = daXoa;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    
    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    public ArrayList<Taikhoan> readDB() {
        taikhoanConnection = new DBConnection();
        ArrayList<Taikhoan> dstk = new ArrayList<>();
        try {
            String qry = "SELECT * FROM taikhoan";
            ResultSet rs = taikhoanConnection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    String tentk = rs.getString("TenTaiKhoan");
                    String matkhau = rs.getString("MatKhau");
                    int manv = rs.getInt("MaNhanVien");
                    String maquyen = rs.getString("MaQuyen");                    
                    boolean daxoa = rs.getBoolean("DaXoa");                 
                    dstk.add(new Taikhoan(tentk, matkhau, manv, maquyen, daxoa));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
        } finally {
            taikhoanConnection.closeConnection();
        }
        return dstk;
    }

    public Boolean themTaiKhoan(Taikhoan tk) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("INSERT INTO taikhoan (TenTaiKhoan, MatKhau, MaNhanVien, MaQuyen) VALUES ('"
                + tk.getTaikhoan()+ "', '" + tk.getMatkhau()+ "', '" + tk.getMaNhanvien()+ "', '" + tk.getMaQuyen() + "');");
        taikhoanConnection.closeConnection();
        return ok;
    }

    public Boolean softDelete(String tentaikhoan) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("UPDATE taikhoan SET DaXoa = 1 WHERE taikhoan.TenTaiKhoan = '" + tentaikhoan + "'");
        taikhoanConnection.closeConnection();
        return ok;
    }
    
    public Boolean xoaTaiKhoan(String tentaikhoan) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("DELETE from taikhoan WHERE taikhoan.TenTaiKhoan = '" + tentaikhoan + "'");
        taikhoanConnection.closeConnection();
        return ok;
    }

    public Boolean capnhatTaiKhoan(Taikhoan tk) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("UPDATE taikhoan SET MatKhau='" + tk.getMatkhau() + "', MaNhanVien = '" + tk.getMaNhanvien()
                + "', MaQuyen = '" + tk.getMaQuyen() + "', DaXoa = '" + tk.isDaXoa() + "' WHERE TenTaiKhoan='" + tk.getTaikhoan() + "'");
        taikhoanConnection.closeConnection(); 
        return ok;
    }
    public Boolean resetMatKhau(Taikhoan tk, String hashedString){
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("UPDATE taikhoan SET MatKhau='" + hashedString + "' WHERE TenTaiKhoan='" + tk.getTaikhoan() + "'");
        taikhoanConnection.closeConnection(); 
        return ok;
    }
    public void close() {
        taikhoanConnection.closeConnection();
    }
}
